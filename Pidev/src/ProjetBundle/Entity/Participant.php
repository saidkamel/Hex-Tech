<?php

namespace ProjetBundle\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Participant
 *
 * @ORM\Table(name="participant")
 * @ORM\Entity
 */
class Participant
{
    /**
     * @var integer
     *
     * @ORM\Column(name="Id_p", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $idP;

    /**
     * @var string
     *
     * @ORM\Column(name="Nom_p", type="string", length=50, nullable=false)
     */
    private $nomP;

    /**
     * @var string
     *
     * @ORM\Column(name="Prenom_p", type="string", length=50, nullable=false)
     */
    private $prenomP;

    /**
     * @var string
     *
     * @ORM\Column(name="mdp_p", type="string", length=60, nullable=false)
     */
    private $mdpP;

    /**
     * @var string
     *
     * @ORM\Column(name="Mail", type="string", length=255, nullable=false)
     */
    private $mail;


}

