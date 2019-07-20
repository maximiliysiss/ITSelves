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


use App\Entity\User;
use App\Request\UpdateUserRequest;
use JMS\Serializer\Serializer;
use JMS\Serializer\SerializerInterface;
use Nelmio\ApiDocBundle\Annotation\Model;
use Swagger\Annotations as SWG;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\JsonResponse;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\HttpKernel\Exception\NotFoundHttpException;
use Symfony\Component\Routing\Annotation\Route;

/**
 * Class UserController
 *
 * @Route("/user", name="user_")
 *
 * @SWG\Tag(name="User")
 */
class UserController extends AbstractController
{
    /**
     * @var Serializer
     */
    protected $serializer;

    /**
     * AuthController constructor.
     *
     * @param SerializerInterface $serializer
     */
    public function __construct(SerializerInterface $serializer)
    {
        $this->serializer = $serializer;
    }

    /**
     * @Route("/{id}/", name="update", methods={"put"})
     *
     * @param $id
     * @param UpdateUserRequest $request
     *
     * @SWG\Response(
     *     response="200",
     *     description="Обновляет пользователя",
     *     @Model(type=User::class, groups={"all"})
     * )
     * @SWG\Response(
     *     response="404",
     *     description="Пользователь не найден"
     * )
     *
     * @SWG\Parameter(
     *     name="Запрос токена",
     *     in="body",
     *     @Model(type=UpdateUserRequest::class)
     * )
     *
     * @return JsonResponse
     */
    public function updateUser($id, UpdateUserRequest $request)
    {
        /** @var User $user */
        $user = $this->getUserRepository()->find($id);

        if (empty($user)) {
            throw new NotFoundHttpException('Пользователь не найден');
        }

        $user->updateFromRequest($request);

        $this->getDoctrine()->getManager()->flush();

        return JsonResponse::fromJsonString(
            $this->serializer->serialize($user, 'json')
        );
    }

    /**
     * @Route("/{id}/", name="remove", methods={"delete"})
     *
     * @SWG\Response(
     *     response="204",
     *     description="Удаляет пользователя"
     * )
     * @SWG\Response(
     *     response="404",
     *     description="Пользователь не найден"
     * )
     *
     * @param $id
     *
     * @return Response
     */
    public function removeUser($id)
    {
        /** @var User $user */
        $user = $this->getUserRepository()->find($id);

        if (empty($user)) {
            throw new NotFoundHttpException('Пользователь не найден');
        }

        $em = $this->getDoctrine()->getManager();
        $em->remove($user);
        $em->flush();

        return new Response(null, Response::HTTP_NO_CONTENT);
    }

    /**
     * @return \App\Repository\UserRepository|\Doctrine\Common\Persistence\ObjectRepository
     */
    private function getUserRepository()
    {
        return $this->getDoctrine()->getRepository(User::class);
    }
}
