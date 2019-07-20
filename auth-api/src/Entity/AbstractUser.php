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
 * @ORM\Table(name="users")
 * @ORM\InheritanceType(value="SINGLE_TABLE")
 *
 * @JMS\ExclusionPolicy("ALL")
 */
class AbstractUser
{
    /**
     * @var int
     *
     * @ORM\Id()
     * @ORM\Column(type="integer")
     * @ORM\GeneratedValue()
     *
     * @JMS\Expose()
     * @JMS\Groups(groups={"all"})
     */
    protected $id;

    /**
     * @var string|null
     *
     * @ORM\Column(nullable=true)
     */
    protected $token;

    /**
     * @var string
     *
     * @ORM\Column()
     *
     * @JMS\Expose()
     * @JMS\Groups(groups={"all"})
     */
    protected $name;

    /**
     * @var string
     *
     * @ORM\Column(nullable=true)
     */
    protected $user_key;

    /**
     * @var string
     *
     * @ORM\Column(type="datetime_immutable")
     *
     * @JMS\Expose()
     * @JMS\Groups(groups={"all"})
     */
    protected $createdAt;

    /**
     * AbstractUser constructor.
     *
     * @param string $name
     * @param $key
     *
     * @throws \Exception
     */
    public function __construct($name)
    {
        $this->name = $name;
        $this->createdAt = new \DateTimeImmutable();
    }

    /**
     * @return AbstractUser
     */
    public function createToken()
    {
        $this->token = bin2hex(openssl_random_pseudo_bytes(32));

        return $this;
    }

    /**
     * @return int
     */
    public function getId(): int
    {
        return $this->id;
    }

    /**
     * @param int $id
     * @return AbstractUser
     */
    public function setId(int $id): AbstractUser
    {
        $this->id = $id;
        return $this;
    }

    /**
     * @return null|string
     */
    public function getToken(): ?string
    {
        return $this->token;
    }

    /**
     * @param null|string $token
     * @return AbstractUser
     */
    public function setToken(?string $token): AbstractUser
    {
        $this->token = $token;
        return $this;
    }

    /**
     * @return string
     */
    public function getName(): string
    {
        return $this->name;
    }

    /**
     * @param string $name
     * @return AbstractUser
     */
    public function setName(string $name): AbstractUser
    {
        $this->name = $name;
        return $this;
    }
}
