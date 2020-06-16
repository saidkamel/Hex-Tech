<?php

namespace ProjetBundle\Entity;

use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\Validator\Constraints as Assert;
use Symfony\Bridge\Doctrine\Validator\Constraints\UniqueEntity;
use Symfony\Component\Serializer\Encoder\JsonEncoder;
use Symfony\Component\HttpFoundation\Response;


/**
 * Evenement
 *
 *
 * @ORM\Table(name="evenement")
 * @ORM\Entity(repositoryClass="ProjetBundle\Repository\EvenementRepository")
 */
class Evenement
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
     * @ORM\Column(name="Nom", type="string", length=255, unique=true)
     */
    private $nomE;



    /**
     * @var string
     *
     * @ORM\Column(name="Type", type="string", length=255)
     */
    private $type;

    /**
     * @var \DateTime
     *
     * @ORM\Column(name="Date_Debut", type="date", unique=true)
     */
    private $dateDebut;

    /**
     * @var \DateTime
     *
     * @ORM\Column(name="Date_Fin", type="date", unique=true)
     */
    private $dateFin;

    /**
     * @var string
     *
     * @ORM\Column(name="Description", type="string", length=255, nullable=true, unique=false)
     */
    private $description;

    /**
     * @var int
     *
     * @ORM\Column(name="NbrParticipants", type="integer", nullable=true)
     */
    private $nbrParticipants;

    /**
     * @var int
     *
     * @ORM\Column(name="nbrPlaces", type="integer", nullable=true)
     */
    private $nbrPlaces;



    /**
     * @ORM\OneToMany(targetEntity="ProjetBundle\Entity\Participant",mappedBy="evenement")
     */

    private $participant;


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
     * @return int
     */
    public function getNbrPlaces()
    {
        return $this->nbrPlaces;
    }

    /**
     * @param int $nbrPlaces
     */
    public function setNbrPlaces($nbrPlaces)
    {
        $this->nbrPlaces = $nbrPlaces;
    }

    /**
     * Set nomE
     *
     * @param string $nomE
     *
     * @return Evenement
     */
    public function setNomE($nomE)
    {
        $this->nomE = $nomE;

        return $this;
    }

    /**
     * Get nomE
     *
     * @return string
     */
    public function getNomE()
    {
        return $this->nomE;
    }

    /**
     * Set type
     *
     * @param string $type
     *
     * @return Evenement
     */
    public function setType($type)
    {
        $this->type = $type;

        return $this;
    }

    /**
     * Get type
     *
     * @return string
     */
    public function getType()
    {
        return $this->type;
    }

    /**
     * Set dateDebut
     *
     * @param \DateTime $dateDebut
     *
     * @return Evenement
     */
    public function setDateDebut($dateDebut)
    {
        $this->dateDebut = $dateDebut;

        return $this;
    }

    /**
     * Get dateDebut
     *
     * @return \DateTime
     */
    public function getDateDebut()
    {
        return $this->dateDebut;
    }

    /**
     * Set dateFin
     *
     * @param \DateTime $dateFin
     *
     * @return Evenement
     */
    public function setDateFin($dateFin)
    {
        $this->dateFin = $dateFin;

        return $this;
    }

    /**
     * Get dateFin
     *
     * @return \DateTime
     */
    public function getDateFin()
    {
        return $this->dateFin;
    }

    /**
     * Set description
     *
     * @param string $description
     *
     * @return Evenement
     */
    public function setDescription($description)
    {
        $this->description = $description;

        return $this;
    }

    /**
     * Get description
     *
     * @return string
     */
    public function getDescription()
    {
        return $this->description;
    }

    /**
     * Set nbrParticipants
     *
     * @param integer $nbrParticipants
     *
     * @return Evenement
     */
    public function setNbrParticipants($nbrParticipants)
    {
        $this->nbrParticipants = $nbrParticipants;

        return $this;
    }

    /**
     * Get nbrParticipants
     *
     * @return int
     */
    public function getNbrParticipants()
    {
        return $this->nbrParticipants;
    }
    /**
     * Set participant
     *
     * @param string $participant
     *
     * @return Evenement
     */
    public function setParticipant($participant)
    {
        $this->participant = $participant;

        return $this;
    }

    /**
     * Get participant
     *
     * @return string
     */
    public function getParticipant()
    {
        return $this->participant;
    }
    public function __toString()
    {
        // TODO: Implement __toString() method.
        return $this->nomE;
    }

    /**
     * Add nbrParticipants
     *
     * @param integer $nbrParticipants
     *
     * @return Evenement
     */
    public function AddNbrParticipants()
    {
        $this->nbrParticipants+=1;

        return $this;
    }
    /**
     * Remove nbrParticipants
     *
     * @param integer $nbrParticipants
     *
     * @return Evenement
     */
    public function RemoveNbrParticipants()
    {
        $this->nbrParticipants-=1;

        return $this;
    }

}

