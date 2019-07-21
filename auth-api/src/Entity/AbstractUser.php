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
use Symfony\Component\Security\Core\User\UserInterface;

/**
 * Class User
 *
 * @ORM\Entity(repositoryClass="App\Repository\AbstractUserRepository")
 * @ORM\Table(name="users")
 * @ORM\InheritanceType(value="SINGLE_TABLE")
 *
 * @JMS\ExclusionPolicy("ALL")
 * @JMS\Discriminator(disabled=true)
 */
abstract class AbstractUser implements UserInterface
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
     * @var \DateTimeImmutable|null
     *
     * @ORM\Column(type="datetime_immutable", nullable=true)
     */
    protected $tokenExpiresAt;

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
     * @ORM\Column(type="datetime_immutable")
     *
     * @JMS\Expose()
     * @JMS\Groups(groups={"all"})
     */
    protected $createdAt;

    /**
     * @var string
     *
     * @ORM\Column()
     *
     * @JMS\Expose()
     * @JMS\Groups(groups={"all"})
     */
    protected $email;

    /**
     * @var string
     *
     * @ORM\Column()
     *
     * @JMS\Expose()
     * @JMS\Groups(groups={"all"})
     */
    protected $phone;

    /**
     * @var string
     *
     * @ORM\Column(nullable=true)
     */
    protected $userKey;

    /**
     * AbstractUser constructor.
     *
     * @param string $name
     * @param $phone
     * @param $email
     * @throws \Exception
     */
    public function __construct($name, $phone, $email)
    {
        $this->name = $name;
        $this->phone = $phone;
        $this->email = $email;
        $this->createdAt = new \DateTimeImmutable();
    }

    /**
     * @return AbstractUser
     *
     * @throws \Exception
     */
    public function createToken()
    {
        $this->token = bin2hex(openssl_random_pseudo_bytes(32));
        $this->tokenExpiresAt = new \DateTimeImmutable('tomorrow');

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

    /**
     * @return string
     */
    public function getCreatedAt(): string
    {
        return $this->createdAt;
    }

    /**
     * @param string $createdAt
     * @return AbstractUser
     */
    public function setCreatedAt(string $createdAt): AbstractUser
    {
        $this->createdAt = $createdAt;
        return $this;
    }

    /**
     * @return string
     */
    public function getEmail(): string
    {
        return $this->email;
    }

    /**
     * @param string $email
     * @return AbstractUser
     */
    public function setEmail(string $email): AbstractUser
    {
        $this->email = $email;
        return $this;
    }

    /**
     * @return string
     */
    public function getPhone(): string
    {
        return $this->phone;
    }

    /**
     * @param string $phone
     * @return AbstractUser
     */
    public function setPhone(string $phone): AbstractUser
    {
        $this->phone = $phone;
        return $this;
    }

    /**
     * @return \DateTimeImmutable|null
     */
    public function getTokenExpiresAt(): ?\DateTimeImmutable
    {
        return $this->tokenExpiresAt;
    }

    /**
     * @param \DateTimeImmutable|null $tokenExpiresAt
     *
     * @return AbstractUser
     */
    public function setTokenExpiresAt(?\DateTimeImmutable $tokenExpiresAt): AbstractUser
    {
        $this->tokenExpiresAt = $tokenExpiresAt;

        return $this;
    }

    /**
     * @return bool
     *
     * @throws \Exception
     */
    public function isTokenExpired(): bool
    {
        return $this->tokenExpiresAt < new \DateTimeImmutable();
    }

    /**
     * @return string
     */
    public function getUserKey(): ?string
    {
        return $this->userKey;
    }

    /**
     * @param string $userKey
     * @return AbstractUser
     */
    public function setUserKey(string $userKey): AbstractUser
    {
        $this->userKey = $userKey;
        return $this;
    }

    /**
     * @JMS\VirtualProperty(name="role")
     *
     * @return string
     */
    public abstract function getRole();

    public function getUsername()
    {
        // do nothing
        return null;
    }

    public function getPassword()
    {
        // do nothing
        return null;
    }

    public function getRoles()
    {
        return [$this->getRole()];
    }

    public function getSalt()
    {
        // do nothing
        return null;
    }

    public function eraseCredentials()
    {
        // do nothing
        //return null;
    }
}
