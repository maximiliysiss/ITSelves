<?php
/**
 * @author      Nickolay Mikhaylov <sonny@milton.pro>
 * @copyright   Copyright (c) 2019, Darvin Studio
 * @link        https://www.darvin-studio.ru
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

namespace App\Controller;


use App\Entity\AbstractUser;
use App\Entity\User;
use App\Request\LoginRequest;
use App\Request\RegistrationRequest;
use App\Request\VerifyTokenRequest;
use App\Response\UserResponseModelFactory;
use App\Response\TokenResponse;
use JMS\Serializer\Serializer;
use JMS\Serializer\SerializerInterface;
use Nelmio\ApiDocBundle\Annotation\Model;
use Nelmio\ApiDocBundle\Annotation\Security;
use Swagger\Annotations as SWG;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\JsonResponse;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\Security\Core\Exception\BadCredentialsException;

/**
 * Class AuthController
 */
class AuthController extends AbstractController
{
    /**
     * @var Serializer
     */
    protected $serializer;

    /**
     * @var UserResponseModelFactory
     */
    protected $userResponseModelFactory;

    /**
     * AuthController constructor.
     *
     * @param SerializerInterface $serializer
     * @param UserResponseModelFactory $userResponseModelFactory
     */
    public function __construct(SerializerInterface $serializer, UserResponseModelFactory $userResponseModelFactory)
    {
        $this->serializer = $serializer;
        $this->userResponseModelFactory = $userResponseModelFactory;
    }

    /**
     * @Route("/register/", name="register", methods={"post"})
     *
     * @SWG\Response(
     *     response="200",
     *     description="Регистрирует пользователя",
     *     @Model(type=User::class, groups={"all"})
     * )
     *
     * @SWG\Parameter(
     *     name="Запрос на регистрацию",
     *     in="body",
     *     allowEmptyValue=false,
     *     @Model(type=RegistrationRequest::class)
     * )
     *
     * @SWG\Tag(name="WebClient")
     *
     * @Security(name="Token")
     *
     * @param RegistrationRequest $request
     *
     * @return JsonResponse
     *
     * @throws \Exception
     */
    public function registrationAction(RegistrationRequest $request)
    {
        $this->denyAccessUnlessGranted('ROLE_OPERATOR');
        /** @var User $user */
        $user = User::createFromRequest($request);

        $user->createKey();

        $em = $this->getDoctrine()->getManager();
        $em->persist($user);
        $em->flush();

       return JsonResponse::fromJsonString(
           $this->serializer->serialize($user, 'json')
       );
    }

    /**
     * @Route("/login/", name="login", methods={"post"})
     *
     * @SWG\Response(
     *     response="200",
     *     description="Возвращает токен по ключу",
     *     @Model(type=TokenResponse::class)
     * )
     * @SWG\Response(
     *     response="403",
     *     description="Невалидный код",
     * )
     * @SWG\Parameter(
     *     name="Запрос токена",
     *     in="body",
     *     @Model(type=LoginRequest::class)
     * )
     *
     * @SWG\Tag(name="Android")
     *
     * @param LoginRequest $request
     *
     * @return JsonResponse
     *
     * @throws \Exception
     */
    public function loginAction(LoginRequest $request)
    {
        $key = $request->get('key');

        /** @var AbstractUser $user */
        $user = $this->getAbstractUserRepository()->findOneBy([
            'userKey' => $key,
        ]);

        if (empty($user)) {
            throw new BadCredentialsException("Неверный ключ");
        }

        $user->createToken();
        $this->getDoctrine()->getManager()->flush();

        return JsonResponse::fromJsonString(
            $this->serializer->serialize(
                new TokenResponse($user->getToken()), 'json'
            )
        );
    }

    /**
     * @Route("/auth/", name="auth", methods={"post"})
     *
     * @SWG\Response(
     *     response="200",
     *     description="Проверка токена. Возвращает пользователя по токену.",
     *     @Model(type=User::class, groups={"all"})
     * )
     * @SWG\Response(
     *     response="403",
     *     description="Невалидный токен",
     * )
     * @SWG\Parameter(
     *     name="Аутентификация",
     *     in="body",
     *     @Model(type=VerifyTokenRequest::class)
     * )
     *
     * @SWG\Tag(name="Internal")
     *
     * @param VerifyTokenRequest $request
     *
     * @return JsonResponse
     *
     * @throws \Exception
     * @throws \GuzzleHttp\Exception\GuzzleException
     */
    public function verifyTokenAction(VerifyTokenRequest $request)
    {
        $token = $request->get('token');

        /** @var AbstractUser $user */
        $user = $this->getAbstractUserRepository()->findOneBy([
            'token' => $token,
        ]);

        if (empty($user) || $user->isTokenExpired()) {
            throw new BadCredentialsException("Неверный токен");
        }

        $user = $this->userResponseModelFactory->createUserResponse($user);

        return JsonResponse::fromJsonString(
            $this->serializer->serialize(
                $user, 'json'
            )
        );
    }

    /**
     * @return \App\Repository\AbstractUserRepository|\Doctrine\Common\Persistence\ObjectRepository
     */
    private function getAbstractUserRepository()
    {
        return $this->getDoctrine()->getRepository(AbstractUser::class);
    }

    /**
     * @return \App\Repository\UserRepository|\Doctrine\Common\Persistence\ObjectRepository
     */
    private function getUserRepository()
    {
        return $this->getDoctrine()->getRepository(User::class);
    }
}
