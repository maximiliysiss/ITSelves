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
     * @ORM\Column(nullable=true)
     */
    protected $userKey;

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
     * @throws \Exception
     */
    public function __construct(string $name)
    {
        parent::__construct($name);
    }


    public function createKey()
    {
        $parts = [];
        for ($i = 0; $i<4; $i++) {
            $parts []= bin2hex(openssl_random_pseudo_bytes(2));
        }
        $this->userKey = implode('-', $parts);
    }
}
