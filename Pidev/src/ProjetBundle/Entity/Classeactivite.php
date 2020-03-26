<?php

namespace ProjetBundle\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Classeactivite
 *
 * @ORM\Table(name="classeactivite", indexes={@ORM\Index(name="FK_ac", columns={"IdActivite"})})
 * @ORM\Entity
 */
class Classeactivite
{
    /**
     * @var integer
     *
     * @ORM\Column(name="IdClasse", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="NONE")
     */
    private $idclasse;

    /**
     * @var integer
     *
     * @ORM\Column(name="IdActivite", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="NONE")
     */
    private $idactivite;


}

