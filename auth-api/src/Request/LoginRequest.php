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


use Fesor\RequestObject\PayloadResolver;
use Fesor\RequestObject\RequestObject;
use Swagger\Annotations as SWG;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\Validator\Constraints as Assert;

/**
 * Class LoginRequest
 */
class LoginRequest extends RequestObject implements PayloadResolver
{
    /**
     * @var string
     *
     * @SWG\Property(type="string")
     */
    protected $key;

    /**
     * @return \Symfony\Component\Validator\Constraint|\Symfony\Component\Validator\Constraint[]|Assert\Collection
     */
    public function rules()
    {
        return new Assert\Collection([
            'key' => new Assert\NotNull(),
        ]);
    }

    /**
     * @param Request $request
     *
     * @return array|mixed
     */
    public function resolvePayload(Request $request)
    {
        return json_decode($request->getContent(), true) ?? [];
    }
}
