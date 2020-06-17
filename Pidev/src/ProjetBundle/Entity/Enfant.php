<?php

namespace ProjetBundle\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Enfant
 *
 * @ORM\Table(name="enfant")
 * @ORM\Entity
 */
class Enfant
{
    /**
     * @var integer
     *
     * @ORM\Column(name="id", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $id;

    /**
     * @var string
     *
     * @ORM\Column(name="nom", type="string", length=100, nullable=false)
     */
    private $nom;

    /**
     * @var string
     *
     * @ORM\Column(name="prenom", type="string", length=100, nullable=false)
     */
    private $prenom;

    /**
     * Set nom
     *
     *   @param string $DateNaissance
     *
     * @return \ProjetBundle\Entity\Enfant
     */


    /**
     * @var \DateTime
     *
     * @ORM\Column(name="DateNaissance", type="date", unique=true)
     */
    private $DateNaissance;


    /**
     * @var integer
     *
     * @ORM\Column(name="idParent", type="integer", nullable=true)
     */
    private $idparent = 'NULL';

    /**
     * @var integer
     *
     * @ORM\Column(name="idclasse", type="integer", nullable=true)
     */
    private $idclasse = 'NULL';


    /**
     * @return int
     */
    public function getId()
    {
        return $this->id;
    }

    /**
     * @param int $id
     */
    public function setId($id)
    {
        $this->id = $id;
    }

    /**
     * @return string
     */
    public function getNom()
    {
        return $this->nom;
    }

    /**
     * @param string $nom
     */
    public function setNom($nom)
    {
        $this->nom = $nom;
    }

    /**
     * @return string
     */
    public function getPrenom()
    {
        return $this->prenom;
    }

    /**
     * @param string $prenom
     */
    public function setPrenom($prenom)
    {
        $this->prenom = $prenom;
    }

    /**
     * @return \DateTime
     */
    public function getDatenaissance()
    {
        return $this->DateNaissance;
    }

    /**
     * @param \DateTime $datenaissance
     */
    public function setDatenaissance($datenaissance)
    {
        $this->DateNaissance = $datenaissance;
    }

    /**
     * @return int
     */
    public function getIdparent()
    {
        return $this->idparent;
    }

    /**
     * @param int $idparent
     */
    public function setIdparent($idparent)
    {
        $this->idparent = $idparent;
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
     * One enfant has One profilemedicale.
     * @ORM\OneToOne(targetEntity="Profilmedicale", mappedBy="enfant")
     * @ORM\JoinColumn(name="Pmedicale_id", referencedColumnName="id")
     */
    private $profilmedicale;

      /**
       * @return mixed
       */
    public function getProfilmedicale()
    {
        return $this->profilmedicale;
    }

    /**
     * @param mixed $profilmedicale
     */
    public function setProfilmedicale($profilmedicale)
    {
        $this->profilmedicale = $profilmedicale;
    }
    public function getAge()
    {
        $now = new \DateTime('now');
        $age = $this->getDatenaissance();
        $difference = $now->diff($age);

        return $difference->format('%y years, %m months, %d days old.');
    }
    /**
     * Get nom
     *
     * @return string
     */
    /**
     * @ORM\ManyToOne(targetEntity="ProjetBundle\Entity\EmploiDuTemps", inversedBy="enfants")
     * @ORM\JoinColumn(name="EmploiDuTemps_id", referencedColumnName="id")
     */
    public $EmploiDuTemps;

}

