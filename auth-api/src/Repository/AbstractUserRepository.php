<?php
/**
 * @author      Nickolay Mikhaylov <sonny@milton.pro>
 * @copyright   Copyright (c) 2019, Darvin Studio
 * @link        https://www.darvin-studio.ru
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

namespace App\Repository;


use App\Entity\AbstractUser;
use Doctrine\ORM\EntityRepository;

/**
 * Class UserRepository
 */
class AbstractUserRepository extends EntityRepository
{
    /**
     * @param $token
     *
     * @return AbstractUser
     *
     * @throws \Doctrine\ORM\NonUniqueResultException
     */
    public function getUserByToken($token)
    {
        $qb = $this->createQueryBuilder('o')
            ->where('o.token = :token')
            ->setParameter('token', $token);

        return $qb->getQuery()->getOneOrNullResult();
    }
}
