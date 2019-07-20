<?php
/**
 * @author      Nickolay Mikhaylov <sonny@milton.pro>
 * @copyright   Copyright (c) 2019, Darvin Studio
 * @link        https://www.darvin-studio.ru
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

namespace App;


use App\Entity\Operator;
use Doctrine\ORM\EntityManagerInterface;
use Symfony\Component\Console\Command\Command;
use Symfony\Component\Console\Input\InputInterface;
use Symfony\Component\Console\Output\OutputInterface;

class CreateOperatorCommand extends Command
{
    /**
     * @var EntityManagerInterface
     */
    protected $em;

    protected static $defaultName = 'app:operator:create';

    /**
     * CreateOperatorCommand constructor.
     *
     * @param EntityManagerInterface $em
     * @param null|string $name
     */
    public function __construct(EntityManagerInterface $em, ?string $name = null)
    {
        parent::__construct($name);
        $this->em = $em;
    }

    protected function configure()
    {
        $this->addArgument('name');
        $this->addArgument('key');
    }

    /**
     * @param InputInterface $input
     * @param OutputInterface $output
     *
     * @return int|null|void
     *
     * @throws \Exception
     */
    protected function execute(InputInterface $input, OutputInterface $output)
    {
        $name = $input->getArgument('name');
        $key = $input->getArgument('key');

        $operator = new Operator($name, 'test', 'test');
        $operator->setUserKey($key);

        $this->em->persist($operator);
        $this->em->flush();
    }
}
