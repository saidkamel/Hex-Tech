<?php


namespace ProjetBundle\Entity;
use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\Validator\Constraints as Assert;

/**
 * Class Media
 * @ORM\Entity(repositoryClass="ProjetBundle\Repository\MediaRepository")
 */

class Media
{
    /**
     *@ORM\Id
     *@ORM\Column(type="integer")
     *@ORM\GeneratedValue
     */
    private $id;
    /**
     * @var string
     * @Assert\File(maxSize="500k", mimeTypes={"image/jpeg", "image/jpg", "image/png", "image/GIF"})
     * @ORM\Column(name="img", type="string", length=500)
     */
    private $img;
    /**
     * @var string
     * @Assert\File(maxSize="500k", mimeTypes={"image/jpeg", "image/jpg", "image/png", "image/GIF"})
     * @ORM\Column(name="img1", type="string", length=500)
     */
    private $img1;
    /**
     * @var string
     * @Assert\File(maxSize="500k", mimeTypes={"image/jpeg", "image/jpg", "image/png", "image/GIF"})
     * @ORM\Column(name="img2", type="string", length=500)
     */
    private $img2;
    /**
     * @var string
     * @Assert\File(maxSize="500k", mimeTypes={"image/jpeg", "image/jpg", "image/png", "image/GIF"})
     * @ORM\Column(name="img3", type="string", length=500)
     */
    private $img3;
    /**
     * @ORM\ManyToOne(targetEntity="Club")
     * @ORM\JoinColumn(name="id_club",referencedColumnName="id")
     */
    private $Club;

    /**
     * Get Club
     *
     * @return \ClubBundle\Entity\Club
     */


    public function getClub()
    {
        return $this->Club;
    }

    /**
     * Set Club
     *
     * @param \ClubBundle\Entity\Club $Club
     *
     */
    public function setClub($Club)
    {
        $this->Club = $Club;
    }


    /**
     * @return string
     */
    public function getImg1()
    {
        return $this->img1;
    }

    /**
     * @param string $img1
     */
    public function setImg1($img1)
    {
        $this->img1 = $img1;
    }

    /**
     * @return string
     */
    public function getImg2()
    {
        return $this->img2;
    }

    /**
     * @param string $img2
     */
    public function setImg2($img2)
    {
        $this->img2 = $img2;
    }

    /**
     * @return string
     */
    public function getImg3()
    {
        return $this->img3;
    }

    /**
     * @param string $img3
     */
    public function setImg3($img3)
    {
        $this->img3 = $img3;
    }


    /**
     * @return mixed
     */
    public function getId()
    {
        return $this->id;
    }

    /**
     * @param mixed $id
     */
    public function setId($id)
    {
        $this->id = $id;
    }
    /**
     * @return string
     */
    public function getImg()
    {
        return $this->img;
    }

    /**
     * @param string $img
     */
    public function setImg($img)
    {
        $this->img = $img;
    }
    public function __toString() {
        return "img";

}



}