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
 * Class Operator
 *
 * @ORM\Entity()
 */
class Operator extends Worker
{
    /**
     * @return string
     */
    public function getRole()
    {
        return 'ROLE_OPERATOR';
    }

}
