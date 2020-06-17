<?php


namespace ProjetBundle\Controller;



use ProjetBundle\Entity\Enfant;
use ProjetBundle\Form\EnfantType;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\Serializer\Normalizer\DateTimeNormalizer;
use Symfony\Component\Serializer\Serializer;
use Symfony\Component\Serializer\Normalizer\ObjectNormalizer;
use Symfony\Component\HttpFoundation\JsonResponse;
use Symfony\Component\Validator\Constraints\DateTime;
use Symfony\Component\Serializer\Encoder\XmlEncoder;
use Symfony\Component\Serializer\Encoder\JsonEncoder;
use Symfony\Component\HttpFoundation\Response;




class AcceuilController extends Controller
{


    public function indexAction()
    {
        return $this->render("@Projet/Acceuil/acceuil.html.twig");
    }

    public function AdminAction()
    {
        return $this->render("@Projet/Acceuil/admin.html.twig");
    }
}





