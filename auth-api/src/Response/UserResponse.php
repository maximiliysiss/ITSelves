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


use App\Entity\User;
use Swagger\Annotations as SWG;

class UserResponse
{
    /**
     * @var string
     *
     * @SWG\Property(type="string")
     */
    protected $name;

    /**
     * @var string
     *
     * @SWG\Property(type="string")
     */
    protected $phone;

    /**
     * @var string
     *
     * @SWG\Property(type="string")
     */
    protected $email;

    /**
     * @var string
     *
     * @SWG\Property()
     */
    protected $address;

    public function __construct(User $user, $address)
    {
        $this->name = $user->getName();
        $this->phone = $user->getPhone();
        $this->email = $user->getEmail();
        $this->address = $address;
    }
}
