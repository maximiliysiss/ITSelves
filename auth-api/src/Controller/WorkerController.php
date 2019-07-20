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


use App\Request\WorkerRequest;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

/**
 * Class WorkerController
 *
 * @Route("/worker", name="worker_")
 */
class WorkerController extends AbstractController
{
    /**
     * @Route("/", name="create", methods={"POST"})
     *
     * @param WorkerRequest $request
     *
     * @return Response
     */
    public function createAction(WorkerRequest $request)
    {
        return new Response();
    }
}
