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


use App\Entity\Worker;
use App\Request\WorkerRequest;
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
 * Class WorkerController
 *
 * @Route("/worker", name="worker_")
 *
 * @SWG\Tag(name="Worker")
 *
 * @Security(name="Token")
 */
class WorkerController extends AbstractController
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
     * @Route("/", name="create", methods={"post"})
     *
     * @SWG\Response(
     *     response="200",
     *     description="Создает рабочего",
     *     @Model(type=Worker::class, groups={"all"})
     * )
     * @SWG\Parameter(
     *     name="Запрос на создание рабочего",
     *     in="body",
     *     allowEmptyValue=false,
     *     @Model(type=WorkerRequest::class)
     * )
     *
     * @param WorkerRequest $request
     *
     * @return Response
     *
     * @throws \Exception
     */
    public function createAction(WorkerRequest $request)
    {
        $this->denyAccessUnlessGranted('ROLE_OPERATOR');
        $worker = Worker::createFromRequest($request);

        $em = $this->getDoctrine()->getManager();
        $em->persist($worker);
        $em->flush();

        return JsonResponse::fromJsonString(
            $this->serializer->serialize($worker, 'json')
        );
    }

    /**
     * @Route("/", name="list", methods={"get"})
     *
     * @SWG\Response(
     *     response="200",
     *     description="Возращает список рабочих",
     *     @SWG\Schema(
     *      type="array",
     *      @SWG\Items(ref=@Model(type=Worker::class, groups={"all"}))
     *     )
     * )
     *
     * @return Response
     */
    public function listAction()
    {
        $workers = $this->getWorkerRepository()->findAll();

        return JsonResponse::fromJsonString(
            $this->serializer->serialize($workers, 'json')
        );
    }

    /**
     * @Route("/{id}/", name="show", methods={"get"})
     *
     * @SWG\Response(
     *     response="200",
     *     description="Возвращает рабочего по ID",
     *     @Model(type=Worker::class, groups={"all"})
     * )
     * @SWG\Response(
     *     response="404",
     *     description="Рабочий не найден"
     * )
     *
     * @param $id
     *
     * @return JsonResponse
     */
    public function showAction($id)
    {
        $worker = $this->getWorkerRepository()->find($id);

        if (empty($worker)) {
            throw new NotFoundHttpException('Рабочий не найден');
        }

        return JsonResponse::fromJsonString(
            $this->serializer->serialize($worker, 'json')
        );
    }


    /**
     * @Route("/{id}/", name="remove", methods={"delete"})
     *
     * @SWG\Response(
     *     response="204",
     *     description="Удаляет рабочего по ID",
     * )
     * @SWG\Response(
     *     response="404",
     *     description="Рабочий не найден"
     * )
     *
     * @param $id
     *
     * @return Response
     */
    public function removeAction($id)
    {
        $this->denyAccessUnlessGranted('ROLE_OPERATOR');
        $worker = $this->getWorkerRepository()->find($id);

        if (empty($worker)) {
            throw new NotFoundHttpException('Рабочий не найден');
        }

        $em = $this->getDoctrine()->getManager();
        $em->remove($worker);
        $em->flush();

        return new Response(null, Response::HTTP_NO_CONTENT);
    }

    /**
     * @Route("/{id}/", name="update", methods={"put"})
     *
     * @SWG\Response(
     *     response="200",
     *     description="Обновляет рабочего по ID",
     *     @Model(type=Worker::class, groups={"all"})
     * )
     * @SWG\Response(
     *     response="404",
     *     description="Рабочий не найден"
     * )
     *
     * @SWG\Parameter(
     *     name="Запрос на редактирование рабочего",
     *     in="body",
     *     allowEmptyValue=false,
     *     @Model(type=WorkerRequest::class)
     * )
     *
     * @param $id
     *
     * @return JsonResponse
     */
    public function updateAction($id)
    {
        $this->denyAccessUnlessGranted('ROLE_OPERATOR');
        /** @var Worker $worker */
        $worker = $this->getWorkerRepository()->find($id);

        if (empty($worker)) {
            throw new NotFoundHttpException('Рабочий не найден');
        }

        return JsonResponse::fromJsonString(
            $this->serializer->serialize($worker, 'json')
        );
    }

    /**
     * @return \Doctrine\Common\Persistence\ObjectRepository
     */
    private function getWorkerRepository()
    {
        return $this->getDoctrine()->getRepository(Worker::class);
    }
}
