<?php


namespace ProjetBundle\Entity;
use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\Validator\Constraints as Assert;

/**
 * Class Subscription
 * @ORM\Entity
 */
class Subscription

{
    /**
     * @ORM\Id
     * @ORM\Column(type="integer")
     * @ORM\GeneratedValue
     */
    private $id;
    /**
     * @ORM\Column(type="string",length=20)
     */
    private $nomEnfant;
    /**
     * @ORM\ManyToOne(targetEntity="Club")
     * @ORM\JoinColumn(name="id_club",referencedColumnName="id")
     */
    private $Club;

    /**
     * @return mixed
     */
    public function getClub()
    {
        return $this->Club;
    }

    /**
     * @param mixed $Club
     */
    public function setClub($Club)
    {
        $this->Club = $Club;
    }

    /**
     * @return mixed
     */
    public function getId()
    {
        return $this->id;
    }

    /**
     * @param mixed $id
     */
    public function setId($id)
    {
        $this->id = $id;
    }

    /**
     * @return mixed
     */
    public function getNomEnfant()
    {
        return $this->nomEnfant;
    }

    /**
     * @param mixed $nomEnfant
     */
    public function setNomEnfant($nomEnfant)
    {
        $this->nomEnfant = $nomEnfant;
    }

}