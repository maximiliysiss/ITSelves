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


use Swagger\Annotations as SWG;

class TokenResponse
{
    /**
     * @var string
     *
     * @SWG\Property(type="string")
     */
    protected $token;

    /**
     * TokenResponse constructor.
     *
     * @param $token
     */
    public function __construct($token)
    {
        $this->token = $token;
    }
}
