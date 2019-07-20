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

use App\Request\RegistrationRequest;
use App\Request\UpdateUserRequest;
use Doctrine\ORM\Mapping as ORM;
use JMS\Serializer\Annotation as JMS;

/**
 * Class User
 *
 * @ORM\Entity(repositoryClass="App\Repository\UserRepository")
 *
 * @JMS\ExclusionPolicy("ALL")
 */
class User extends AbstractUser
{
    /**
     * @var string
     *
     * @JMS\Expose()
     * @JMS\Groups(groups={"all"})
     */
    protected $address;

    /**
     * User constructor.
     *
     * @param string $name
     *
     * @param string $phone
     * @param string $email
     * @throws \Exception
     */
    public function __construct(string $name, string $phone, string $email)
    {
        parent::__construct($name, $phone, $email);
    }

    /**
     * @param RegistrationRequest $request
     *
     * @return User
     *
     * @throws \Exception
     */
    public static function createFromRequest(RegistrationRequest $request)
    {
        return new self($request->get('name'), $request->get('phone'), $request->get('email'));
    }

    /**
     * @param UpdateUserRequest $request
     */
    public function updateFromRequest(UpdateUserRequest $request)
    {
        $this->setName($request->get('name'));
        $this->setPhone($request->get('phone'));
        $this->setEmail($request->get('email'));
    }

    public function createKey()
    {
        $parts = [];
        for ($i = 0; $i<4; $i++) {
            $parts []= bin2hex(openssl_random_pseudo_bytes(2));
        }
        $this->userKey = implode('-', $parts);
    }

    /**
     * @return string
     */
    public function getRole()
    {
        return 'ROLE_USER';
    }
}
