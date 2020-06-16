<?php

namespace ProjetBundle\Entity;

use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\Validator\Constraints as Assert;
use Symfony\Bridge\Doctrine\Validator\Constraints\UniqueEntity;
use Symfony\Component\Serializer\Encoder\JsonEncoder;
use Symfony\Component\HttpFoundation\Response;



/**
 * Participant
 *
 * @ORM\Table(name="participant")
 * @ORM\Entity(repositoryClass="ProjetBundle\Repository\ParticipantRepository")
 * @UniqueEntity(fields="Email",message="mail déja utilisé")
 */
class Participant
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
     *
     * @var string
     *
     * @ORM\Column(name="Nom", type="string", length=255)
     *
     * @Assert\NotBlank(message="Le nom est obligatoire")
     */
    private $nomP;

    /**
     * @var string
     *
     * @ORM\Column(name="Prenom", type="string", length=255)
     * @Assert\NotBlank(message="Le prenom est obligatoire")
     */

    private $prenom;



  /*  /**
     *
     * @var string
     *
     * @ORM\Column(name="NomEvent", type="string", length=255)
     *
     * @Assert\NotBlank(message="Le nom est obligatoire")
     */
  //  private $nomEvent;


    /**
     * @var string
     *
     * @ORM\Column(name="Email", type="string", length=255)
     * @Assert\NotBlank(message="mail déja utilisé")
     */
    private $Email;

    /**
     * @return string
     */
    public function getEmail()
    {
        return $this->Email;
    }

    /**
     * @param string $Email
     */
    public function setEmail($Email)
    {
        $this->Email = $Email;
    }


    /**
     * @ORM\ManyToOne(targetEntity="ProjetBundle\Entity\Evenement",inversedBy="participant")
     */

    private $evenement;

    /**
     * @return string
     */
    public function getEvenement()
    {
        return $this->evenement;
    }

    /**
     * @param string $evenement
     */
    public function setEvenement($evenement)
    {
        $this->evenement = $evenement;
    }

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
    public function getNomP()
    {
        return $this->nomP;
    }

    /**
     * @param string $nomP
     */
    public function setNomP($nomP)
    {
        $this->nomP = $nomP;
    }


   /* /**
     * @return string
     */
   /* public function getNomEvent()
    {
        return $this->nomEvent;
    }

    /**
     * @param string $nomEvent
     */
   /* public function setNomEvent($nomEvent)
    {
        $this->nomEvent = $nomEvent;
    }*/



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


}

