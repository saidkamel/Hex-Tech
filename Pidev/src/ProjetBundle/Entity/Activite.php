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
    /**
     * @ORM\ManyToMany(targetEntity="Classe",mappedBy="Activite")
     */
    private $classe;
    public function __construct()
    {
        $this->classe = new ArrayCollection();
    }

}

