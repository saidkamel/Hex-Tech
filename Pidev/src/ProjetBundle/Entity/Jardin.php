<?php

namespace ProjetBundle\Entity;

use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\Validator\Constraints as Assert;
use Symfony\Bridge\Doctrine\Validator\Constraints\UniqueEntity;



/**
 * Jardin
 *
 * @ORM\Table(name="jardin")
 * @ORM\Entity(repositoryClass="ProjetBundle\Repository\JardinRepository")
 * @UniqueEntity(fields="mail", message="mail deja utilisé")
 * @UniqueEntity(fields="num", message="num de tel deja utilisé.")
 * @UniqueEntity(fields="localisation", message="Localitation Déja occupé")

 */
class Jardin
{
    /**
     * @var int
     *
     * @ORM\Column(name="id", type="integer")
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="AUTO")
     */
    private $id;

    /**
     * @var string
     *
     * @ORM\Column(name="nom", type="string", length=255)
     * @Assert\NotBlank()
     */
    private $nom;

    /**
     * @var int
     * @Assert\NotBlank()
     * @ORM\Column(name="capacite", type="integer")
     */
    private $capacite;

    /**
     * @var string
     * @Assert\NotBlank()
     * @ORM\Column(name="localisation", type="string", length=255, unique=true)
     */
    private $localisation;

    /**
     * @var int
     * @Assert\Range(
     *      min = 100000000,
     *      max = 999999999,
     *      minMessage = "Le Numero de CIN doit composé de 9 chiffres",
     *      maxMessage = "Le Numero de CIN doit composé de 9 chiffres"
     * )     * @Assert\NotBlank()
     * @ORM\Column(name="num", type="integer", unique=true)
     */
    private $num;

    /**
     * @var string
     * @Assert\NotBlank(message="mail deja utilisé")
     * @ORM\Column(name="mail", type="string", length=255, unique=true)
     */
    private $mail;

    /**
     * @var string
     * @Assert\Image()
     * @ORM\Column(name="image", type="string", length=255)
     */
    private $image;

    /**
     * @var string
     * @Assert\NotBlank()
     * @ORM\Column(name="activite", type="string", length=255)
     */
    private $activite;


    /**
     * Get id
     *
     * @return int
     */
    public function getId()
    {
        return $this->id;
    }

    /**
     * Set nom
     *
     * @param string $nom
     *
     * @return Jardin
     */
    public function setNom($nom)
    {
        $this->nom = $nom;

        return $this;
    }

    /**
     * Get nom
     *
     * @return string
     */
    public function getNom()
    {
        return $this->nom;
    }

    /**
     * Set capacite
     *
     * @param integer $capacite
     *
     * @return Jardin
     */
    public function setCapacite($capacite)
    {
        $this->capacite = $capacite;

        return $this;
    }

    /**
     * Get capacite
     *
     * @return int
     */
    public function getCapacite()
    {
        return $this->capacite;
    }

    /**
     * Set localisation
     *
     * @param string $localisation
     *
     * @return Jardin
     */
    public function setLocalisation($localisation)
    {
        $this->localisation = $localisation;

        return $this;
    }

    /**
     * Get localisation
     *
     * @return string
     */
    public function getLocalisation()
    {
        return $this->localisation;
    }

    /**
     * Set num
     *
     * @param integer $num
     *
     * @return Jardin
     */
    public function setNum($num)
    {
        $this->num = $num;

        return $this;
    }

    /**
     * Get num
     *
     * @return int
     */
    public function getNum()
    {
        return $this->num;
    }

    /**
     * Set mail
     *
     * @param string $mail
     *
     * @return Jardin
     */
    public function setMail($mail)
    {
        $this->mail = $mail;

        return $this;
    }

    /**
     * Get mail
     *
     * @return string
     */
    public function getMail()
    {
        return $this->mail;
    }

    /**
     * Set activite
     *
     * @param string $activite
     *
     * @return Jardin
     */
    public function setActivite($activite)
    {
        $this->activite = $activite;

        return $this;
    }

    /**
     * Get activite
     *
     * @return string
     */
    public function getActivite()
    {
        return $this->activite;
    }

    /**
     * Set image
     *
     * @param string $image
     *
     * @return Jardin
     */
    public function setImage($image)
    {
        $this->image = $image;

        return $this;
    }

    /**
     * Get image
     *
     * @return string
     */
    public function getImage()
    {
        return $this->image;
    }
}
