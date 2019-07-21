<?php
/**
 * @author      Nickolay Mikhaylov <sonny@milton.pro>
 * @copyright   Copyright (c) 2019, Darvin Studio
 * @link        https://www.darvin-studio.ru
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

namespace App\DataFixtures;


use App\Entity\Worker;
use Doctrine\Bundle\FixturesBundle\Fixture;
use Doctrine\Common\Persistence\ObjectManager;

/**
 * Class WorkerFixtures
 */
class WorkerFixtures extends Fixture
{
    /**
     * @param ObjectManager $manager
     *
     * @throws \Exception
     */
    public function load(ObjectManager $manager)
    {
        $names = ['Абакум', 'Абрам', 'Абросим', 'Аввакум', 'Август', 'Авдей', 'Авдий', 'Авель', 'Авенир', 'Аверий', 'Аверкий', 'Аверьян', 'Авксентий', 'Аврам', 'Аврелиан', 'Автоном', 'Агап', 'Агапий', 'Агапит', 'Агафангел', 'Агафон', 'Аггей', 'Адам', 'Адриан', 'Азар', 'Азарий', 'Акакий', 'Акила', 'Аким', 'Акиндин', 'Акинф', 'Акинфий', 'Аксён', 'Аксентий', 'Александр', 'Алексей', 'Алексий', 'Альберт', 'Альфред', 'Амвросий', 'Амос', 'Амфилохий', 'Ананий', 'Анастасий', 'Анатолий', 'Андрей', 'Андриан', 'Андрон', 'Андроний', 'Андроник', 'Анект', 'Анемподист', 'Аникей', 'Аникий', 'Аникита', 'Анисий', 'Анисим', 'Антиох', 'Антип', 'Антипа', 'Антипий', 'Антон', 'Антонин', 'Антроп', 'Антропий', 'Ануфрий', 'Аполлинарий', 'Аполлон', 'Аполлос', 'Ардалион', 'Ардальон', 'Ареф', 'Арефий', 'Арий', 'Аристарх', 'Аристид', 'Аркадий', 'Арнольд', 'Арон', 'Арсений', 'Арсентий', 'Артамон', 'Артём', 'Артемий', 'Артур', 'Архип', 'Асаф', 'Асафий', 'Аскольд', 'Афанасий', 'Афиноген', 'Афинодор', 'Африкан', 'Бажен', 'Бенедикт', 'Богдан', 'Болеслав', 'Бонифат', 'Бонифатий', 'Борис', 'Борислав', 'Бронислав', 'Будимир', 'Вавила', 'Вадим', 'Валентин', 'Валериан', 'Валерий', 'Варлам', 'Варламий', 'Варнава', 'Варсоноф', 'Варсонофий', 'Варфоломей', 'Василий', 'Вассиан', 'Велизар', 'Велимир', 'Венедикт', 'Вениамин', 'Венцеслав', 'Веньямин', 'Викентий', 'Виктор', 'Викторий', 'Викул', 'Викула', 'Вилен', 'Виленин', 'Вильгельм', 'Виссарион', 'Вит', 'Виталий', 'Витовт', 'Витольд', 'Владилен', 'Владимир'];

        for ($i=0; $i<20; $i++) {
            $firstName = $names[mt_rand(0, count($names) - 1)];
            $middleName = $names[mt_rand(0, count($names) - 1)] . 'ович';
            $lastName = $names[mt_rand(0, count($names) - 1)] . 'ов';

            $name = implode(' ', [$firstName, $middleName, $lastName]);
            $email = $this->generateEmail($name);
            $phone = $this->generatePhoneNumber();

            $worker = new Worker($name, $phone, $email);
            $manager->persist($worker);
        }

        $manager->flush();
    }

    /**
     * @param $name
     *
     * @return string
     */
    private function generateEmail($name)
    {
        return sprintf('%s%d@example.com', $this->translit($name), mt_rand(10, 99));
    }

    /**
     * @return string
     */
    private function generatePhoneNumber()
    {
        return sprintf('+7-9%d-%d-%d-%d', mt_rand(10, 99), mt_rand(100, 999), mt_rand(10, 99), mt_rand(10, 99));
    }

    /**
     * @param $text
     *
     * @return string
     */
    private function translit($text)
    {
        $tr = [
            "А"=>"a", "Б"=>"b", "В"=>"v", "Г"=>"g", "Д"=>"d",
            "Е"=>"e", "Ё"=>"yo", "Ж"=>"zh", "З"=>"z", "И"=>"i",
            "Й"=>"j", "К"=>"k", "Л"=>"l", "М"=>"m", "Н"=>"n",
            "О"=>"o", "П"=>"p", "Р"=>"r", "С"=>"s", "Т"=>"t",
            "У"=>"u", "Ф"=>"f", "Х"=>"kh", "Ц"=>"ts", "Ч"=>"ch",
            "Ш"=>"sh", "Щ"=>"sch", "Ъ"=>"", "Ы"=>"y", "Ь"=>"",
            "Э"=>"e", "Ю"=>"yu", "Я"=>"ya", "а"=>"a", "б"=>"b",
            "в"=>"v", "г"=>"g", "д"=>"d", "е"=>"e", "ё"=>"yo",
            "ж"=>"zh", "з"=>"z", "и"=>"i", "й"=>"j", "к"=>"k",
            "л"=>"l", "м"=>"m", "н"=>"n", "о"=>"o", "п"=>"p",
            "р"=>"r", "с"=>"s", "т"=>"t", "у"=>"u", "ф"=>"f",
            "х"=>"kh", "ц"=>"ts", "ч"=>"ch", "ш"=>"sh", "щ"=>"sch",
            "ъ"=>"", "ы"=>"y", "ь"=>"", "э"=>"e", "ю"=>"yu",
            "я"=>"ya", " "=>"-", "."=>"", ","=>"", "/"=>"-",
            ":"=>"", ";"=>"","—"=>"", "–"=>"-"];

        return strtr($text, $tr);
    }
}
