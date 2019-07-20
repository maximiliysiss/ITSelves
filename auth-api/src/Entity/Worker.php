<?php
/**
 * @author      Nickolay Mikhaylov <sonny@milton.pro>
 * @copyright   Copyright (c) 2019, Darvin Studio
 * @link        https://www.darvin-studio.ru
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

namespace App\Entity;


use App\Request\WorkerRequest;
use Doctrine\ORM\Mapping as ORM;

/**
 * Class Worker
 *
 * @ORM\Entity(repositoryClass="App\Repository\WorkerRepository")
 */
class Worker extends AbstractUser
{
    /**
     * @param WorkerRequest $request
     *
     * @return Worker
     *
     * @throws \Exception
     */
    public static function createFromRequest(WorkerRequest $request)
    {
        return new self($request->get('name'), $request->get('phone'), $request->get('email'));
    }

    /**
     * @param WorkerRequest $request
     */
    public function updateFromRequest(WorkerRequest $request)
    {
        $this->setName($request->get('name'));
        $this->setPhone($request->get('phone'));
        $this->setEmail($request->get('email'));
    }

    public function getRole()
    {
        return 'ROLE_WORKER';
    }
}
