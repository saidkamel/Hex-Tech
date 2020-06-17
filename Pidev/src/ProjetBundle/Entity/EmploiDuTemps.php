<?php

namespace ProjetBundle\Entity;

use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\Validator\Constraints as Assert;

/**
 * EmploiDuTemps
 *
 * @ORM\Table(name="emploi_du_temps")
 * @ORM\Entity(repositoryClass="ProjetBundle\Repository\EmploiDuTempsRepository")
 */
class EmploiDuTemps
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
     * @Assert\File(maxSize="500k", mimeTypes={"image/jpeg", "image/jpg", "image/png", "image/GIF"})
     * @ORM\Column(name="img", type="string", length=500)
     */
    private $image;

    /**
     * @var string
     *
     * @ORM\Column(name="Classe", type="string", length=255)
     */
    private $classe;



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
     * Set image
     *
     * @param string $image
     *
     * @return EmploiDuTemps
     */
    public function setImage($image)
    {
        $this->image = $image;

        return $this;
    }

    /**
     * Get image
     *
     * @return string
     */
    public function getImage()
    {
        return $this->image;
    }

    /**
     * Set classe
     *
     * @param string $classe
     *
     * @return EmploiDuTemps
     */
    public function setClasse($classe)
    {
        $this->classe = $classe;

        return $this;
    }

    /**
     * Get classe
     *
     * @return string
     */
    public function getClasse()
    {
        return $this->classe;
    }




    /**
     * Add emploiDuTemp
     *
     * @param \ProjetBundle\Entity\EmploiDuTemps $emploiDuTemp
     *
     * @return EmploiDuTemps
     */
    public function addEmploiDuTemp(\ProjetBundle\Entity\EmploiDuTemps $emploiDuTemp)
    {
        $this->EmploiDuTemps[] = $emploiDuTemp;

        return $this;
    }

    /**
     * Remove emploiDuTemp
     *
     * @param \ProjetBundle\Entity\EmploiDuTemps $emploiDuTemp
     */
    public function removeEmploiDuTemp(\ProjetBundle\Entity\EmploiDuTemps $emploiDuTemp)
    {
        $this->EmploiDuTemps->removeElement($emploiDuTemp);
    }

    /**
     * Get emploiDuTemps
     *
     * @return \Doctrine\Common\Collections\Collection
     */
    public function getEmploiDuTemps()
    {
        return $this->EmploiDuTemps;
    }
}
