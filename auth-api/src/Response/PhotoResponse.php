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

/**
 * Class PhotoResponse
 */
class PhotoResponse
{
    /**
     * @var string
     *
     * @SWG\Property(type="string", description="Base64 encoded image")
     */
    protected $photo;

    /**
     * PhotoResponse constructor.
     *
     * @param $photo
     */
    public function __construct($photo)
    {
        $this->photo = $photo;
    }
}
