<?php

// src/ClubBundle/Entity/Club.php
namespace ProjetBundle\Entity;
use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\Validator\Constraints as Assert;
use Symfony\Bridge\Doctrine\Validator\Constraints\UniqueEntity;

/**
 * Class Club
 * @ORM\Entity
 * @UniqueEntity("nom") // c'est ici que je declare le champs unique
 */
class Club
{
    /**
     *@ORM\Id
     *@ORM\Column(type="integer")
     *@ORM\GeneratedValue
     */
    private $id;
    /**
     *@ORM\Column(type="string", length=20, unique=true)
     * @Assert\NotBlank()
     * @ORM\Column(name="nom", unique=true)
     */
    protected $nom;
    /**
     *@ORM\Column(type="string",length=255)
     * @Assert\NotBlank
     * @Assert\Length(
     *     min=20,
     *     minMessage= "marque length must be at least {{20}} characters long "
     * )
     */
    private $description;

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
    public function getNom()
    {
        return $this->nom;
    }

    /**
     * @param mixed $nom
     */
    public function setNom($nom)
    {
        $this->nom = $nom;
    }

    /**
     * @return mixed
     */
    public function getDescription()
    {
        return $this->description;
    }

    /**
     * @param mixed $description
     */
    public function setDescription($description)
    {
        $this->description = $description;
    }
    public function __toString() {
        return $this->nom;
    }



}