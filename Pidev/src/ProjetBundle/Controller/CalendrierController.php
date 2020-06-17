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




class CalendrierController extends Controller
{

    public function calendarAction(Request $request){
        $em = $this->getDoctrine()->getManager();
        $enfant=$em->getRepository("ProjetBundle:Enfant")->findAll();
        if($request->isMethod('Post')){
            $nom=$request->get('nom');
            $enfant=$em->getRepository("ProjetBundle:Enfant")->findBy(array("nom"=>$nom));
        }
        return $this->render("@Projet/Calendrier/calendar.html.twig",array(
            'enfants'=>$enfant));

    }

    public function calendarUTAction(Request $request){
        $em = $this->getDoctrine()->getManager();
        $enfant=$em->getRepository("ProjetBundle:Enfant")->findAll();
        if($request->isMethod('Post')){
            $nom=$request->get('nom');
            $enfant=$em->getRepository("ProjetBundle:Enfant")->findBy(array("nom"=>$nom));
        }
        return $this->render("@Projet/Calendrier/calendar_UT.html.twig",array(
            'enfants'=>$enfant));

    }
}





