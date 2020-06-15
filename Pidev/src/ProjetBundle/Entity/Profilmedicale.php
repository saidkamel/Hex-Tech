<?php

namespace ProjetBundle\Entity;

use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\Validator\Constraints as Assert;

/**
 * Profilmedicale
 *
 * @ORM\Table(name="profilmedicale")
 * @ORM\Entity(repositoryClass="ProjetBundle\Repository\ProfilmedicaleRepository")
 */
class Profilmedicale
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
     * @var int
     *
     * @ORM\Column(name="taille", type="integer")
     * @Assert\NotBlank()
     *
     */
    private $taille;

    /**
     * @var int
     *
     * @ORM\Column(name="poids", type="integer")
     * @Assert\NotBlank()
     *
     */
    private $poids;

    /**
     * @var string
     *
     * @ORM\Column(name="maladie", type="string", length=255,nullable=true)
     *
     */
    private $maladie;

    /**
     * @var string
     *
     * @ORM\Column(name="etat", type="string", length=255)
     *
     */
    private $etat;


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
     * Set taille
     *
     * @param integer $taille
     *
     * @return Profilmedicale
     */
    public function setTaille($taille)
    {
        $this->taille = $taille;

        return $this;
    }

    /**
     * Get taille
     *
     * @return int
     */
    public function getTaille()
    {
        return $this->taille;
    }

    /**
     * Set poids
     *
     * @param integer $poids
     *
     * @return Profilmedicale
     */
    public function setPoids($poids)
    {
        $this->poids = $poids;

        return $this;
    }

    /**
     * Get poids
     *
     * @return int
     */
    public function getPoids()
    {
        return $this->poids;
    }

    /**
     * Set maladie
     *
     * @param string $maladie
     *
     * @return Profilmedicale
     */
    public function setMaladie($maladie)
    {
        $this->maladie = $maladie;

        return $this;
    }

    /**
     * Get maladie
     *
     * @return string
     */
    public function getMaladie()
    {
        return $this->maladie;
    }

    /**
     * Set etat
     *
     * @param string $etat
     *
     * @return Profilmedicale
     */
    public function setEtat($etat)
    {
        $this->etat = $etat;

        return $this;
    }

    /**
     * Get etat
     *
     * @return string
     */
    public function getEtat()
    {
        return $this->etat;
    }
    /**
     * One profil has One enfant.
     * @ORM\OneToOne(targetEntity="Enfant", inversedBy="profilmedicale")
     * @ORM\JoinColumn(name="enfant_id", referencedColumnName="id")
     */
    private $enfant;

    /**
     * @return mixed
     */
    public function getEnfant()
    {
        return $this->enfant;
    }

    /**
     * @param mixed $enfant
     */
    public function setEnfant($enfant)
    {
        $this->enfant = $enfant;
    }
    /**
     *
     * @ORM\ManyToOne(targetEntity="Pediatre")
     * @ORM\JoinColumn(name="pediatre_id", referencedColumnName="id", nullable=true, onDelete="SET NULL")
     */
    private $idpediatre;

    /**
     * @return mixed
     */
    public function getIdpediatre()
    {
        return $this->idpediatre;
    }

    /**
     * @param mixed $idpediatre
     */
    public function setIdpediatre($id_pediatre)
    {
        $this->idpediatre = $id_pediatre;
    }

}

