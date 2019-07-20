<?php
/**
 * @author      Nickolay Mikhaylov <sonny@milton.pro>
 * @copyright   Copyright (c) 2019, Darvin Studio
 * @link        https://www.darvin-studio.ru
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

namespace App\Request;


use Swagger\Annotations as SWG;
use Symfony\Component\Validator\Constraints as Assert;

class UpdateUserRequest extends JsonRequest
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
    protected $email;

    /**
     * @var string
     *
     * @SWG\Property(type="string")
     */
    protected $phone;


    /**
     * @return \Symfony\Component\Validator\Constraint|\Symfony\Component\Validator\Constraint[]|Assert\Collection
     */
    public function rules()
    {
        return new Assert\Collection([
            'name' => new Assert\NotNull(),
            'email' => new Assert\Email(),
            'phone' => new Assert\NotNull(),
        ]);
    }
}
