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

/**
 * Class User
 *
 * @ORM\Entity()
 */
class User extends AbstractUser
{
    /**
     * @var string
     *
     * @ORM\Column()
     */
    protected $email;

    /**
     * @var string
     *
     * @ORM\Column()
     */
    protected $phone;

    /**
     * @var string
     */
    protected $address;
}
