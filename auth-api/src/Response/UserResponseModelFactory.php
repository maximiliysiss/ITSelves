<?php
/**
 * @author      Nickolay Mikhaylov <sonny@milton.pro>
 * @copyright   Copyright (c) 2019, Darvin Studio
 * @link        https://www.darvin-studio.ru
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

namespace App\Response;


use App\Entity\AbstractUser;
use App\Entity\User;
use GuzzleHttp\Client;

class UserResponseModelFactory
{
    /**
     * @var Client
     */
    protected $client;

    public function __construct()
    {
        $this->client = new Client([
            'base_uri' => 'http://85.143.11.233:8001',
        ]);
    }

    public function createUserResponse(AbstractUser $user)
    {
        if ($user instanceof User) {
            return new UserResponse($user, json_decode($this->client->request('get', '/Houses/' . '1')->getBody()->getContents(), true));
        }

        return $user;
    }
}
