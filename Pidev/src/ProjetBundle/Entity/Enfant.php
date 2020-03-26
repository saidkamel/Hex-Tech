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
     * @var \DateTime
     *
     * @ORM\Column(name="DateNaissance", type="date", nullable=false)
     */
    private $datenaissance;

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


}

