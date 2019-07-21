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
use App\Request\UploadPhotoRequest;
use App\Response\PhotoResponse;
use JMS\Serializer\Serializer;
use JMS\Serializer\SerializerInterface;
use Nelmio\ApiDocBundle\Annotation\Model;
use Nelmio\ApiDocBundle\Annotation\Security;
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
 *
 * @Security(name="Token")
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
        $this->denyAccessUnlessGranted('ROLE_OPERATOR');
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
        $this->denyAccessUnlessGranted('ROLE_OPERATOR');
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
     * @Route("/photo/upload/", name="upload_photo", methods={"post"})
     *
     * @SWG\Response(
     *     response="204",
     *     description="Загружает изображение"
     * )
     * @SWG\Response(
     *     response="404",
     *     description="Изображение не найдено"
     * )
     *
     * @SWG\Parameter(
     *     name="Загрузка фото",
     *     in="body",
     *     @Model(type=UploadPhotoRequest::class)
     * )
     *
     * @param UploadPhotoRequest $request
     *
     * @return Response
     */
    public function uploadPhotoAction(UploadPhotoRequest $request)
    {
        $photo = $request->get('photo');
        /** @var User $user */
        $user = $this->getUser();
        $user->setPhoto($photo);

        $this->getDoctrine()->getManager()->flush();

        return new Response(null, Response::HTTP_NO_CONTENT);
    }

    /**
     * @Route("/photo/", name="get_photo", methods={"get"})
     *
     * @SWG\Response(
     *     response="200",
     *     description="Возвращает изображение пользователя",
     * )
     * @SWG\Response(
     *     response="404",
     *     description="Изображение не найдено"
     * )
     *
     * @return JsonResponse
     */
    public function loadPhotoAction()
    {
        /** @var User $user */
        $user = $this->getUser();

        if (!$user->havePhoto()) {
            throw new NotFoundHttpException('Фото пользователя не найдено');
        }

        return JsonResponse::fromJsonString(
            $this->serializer->serialize(new PhotoResponse($user->getPhoto()), 'json')
        );
    }

    /**
     * @return \App\Repository\UserRepository|\Doctrine\Common\Persistence\ObjectRepository
     */
    private function getUserRepository()
    {
        return $this->getDoctrine()->getRepository(User::class);
    }
}
