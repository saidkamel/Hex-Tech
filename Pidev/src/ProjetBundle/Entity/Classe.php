<?php

namespace ProjetBundle\Entity;

use Doctrine\Common\Collections\ArrayCollection;
use Doctrine\ORM\Mapping as ORM;

/**
 * Classe
 *
 * @ORM\Table(name="classe")
 * @ORM\Entity
 */
class Classe
{
    /**
     * @var integer
     *
     * @ORM\Column(name="id", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="AUTO")
     */
    private $idclasse;

    /**
     * @var string
     *
     * @ORM\Column(name="NomClasse", type="string", length=100, nullable=false)
     */
    private $nomclasse;
    /**
     * @ORM\ManyToMany(targetEntity="Activite",inversedBy="Classe")
     * @ORM\JoinTable(name="ClasseJoinActivite")
     */
    private $activite;

    public function __construct()
    {
        $this->activite = new ArrayCollection();
    }

    /**
     * @return int
     */
    public function getIdclasse()
    {
        return $this->idclasse;
    }

    /**
     * @param int $idclasse
     */
    public function setIdclasse($idclasse)
    {
        $this->idclasse = $idclasse;
    }

    /**
     * @return string
     */
    public function getNomclasse()
    {
        return $this->nomclasse;
    }

    /**
     * @param string $nomclasse
     */
    public function setNomclasse($nomclasse)
    {
        $this->nomclasse = $nomclasse;
    }

    /**
     * @return ArrayCollection
     */
    public function getActivite()
    {
        return $this->activite;
    }

    /**
     * @param ArrayCollection $activite
     */
    public function setActivite($activite)
    {
        $this->activite = $activite;
    }

}
