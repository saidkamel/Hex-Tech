<?php

namespace ProjetBundle\Entity;

use Doctrine\Common\Collections\ArrayCollection;
use Doctrine\ORM\Mapping as ORM;

/**
 * Activite
 *
 * @ORM\Table(name="activite")
 * @ORM\Entity
 */
class Activite
{
    /**
     * @var integer
     *
     * @ORM\Column(name="id", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="AUTO")
     */

    private $idactivite;

    /**
     * @var string
     *
     * @ORM\Column(name="NomActivite", type="string", length=100, nullable=false)
     */
    private $nomactivite;

    /**
     * @var string
     *
     * @ORM\Column(name="Description", type="text", length=65535, nullable=false)
     */
    private $description;

    /**
     * @var string
     *
     * @ORM\Column(name="Type", type="string", length=100, nullable=false)
     */
    private $type;
   /* /**
     * @ORM\ManyToMany(targetEntity="Classe",mappedBy="Activite")
     */
   /* private $classe;
    public function __construct()
    {
        $this->classe = new ArrayCollection();
    }*/

    /**
     * @return int
     */
    public function getIdactivite()
    {
        return $this->idactivite;
    }

    /**
     * @param int $idactivite
     */
    public function setIdactivite($idactivite)
    {
        $this->idactivite = $idactivite;
    }

    /**
     * @return string
     */
    public function getNomactivite()
    {
        return $this->nomactivite;
    }

    /**
     * @param string $nomactivite
     */
    public function setNomactivite($nomactivite)
    {
        $this->nomactivite = $nomactivite;
    }

    /**
     * @return string
     */
    public function getDescription()
    {
        return $this->description;
    }

    /**
     * @param string $description
     */
    public function setDescription($description)
    {
        $this->description = $description;
    }

    /**
     * @return string
     */
    public function getType()
    {
        return $this->type;
    }

    /**
     * @param string $type
     */
    public function setType($type)
    {
        $this->type = $type;
    }

    /**
     * @return ArrayCollection
     */
    public function getClasse()
    {
        return $this->classe;
    }

    /**
     * @param ArrayCollection $classe
     */
    public function setClasse($classe)
    {
        $this->classe = $classe;
    }



}

