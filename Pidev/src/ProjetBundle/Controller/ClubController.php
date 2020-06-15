<?php


namespace ProjetBundle\Controller;



use AppBundle\Entity\Message;
use ProjetBundle\Entity\Club;
use ProjetBundle\Entity\Inscription;
use ProjetBundle\Entity\Media;
use ProjetBundle\Entity\Subscription;
use ProjetBundle\Form\ClubType;
use ProjetBundle\Form\SubscriptionType;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;
use Symfony\Component\Form\Extension\Core\Type\TextareaType;
use Symfony\Component\HttpFoundation\JsonResponse;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\Serializer\Normalizer\ObjectNormalizer;
use Symfony\Component\Serializer\Serializer;
use Symfony\Component\HttpFoundation\Response;


class ClubController extends Controller
{
    public function ajoutAction(Request $request){

        $club = new Club();
        $form=$this->createForm(ClubType::class,$club);
        if($request->isMethod('POST') && $form->handleRequest($request)->isSubmitted() && $form->isValid()) {

            $em = $this->getDoctrine()->getManager();
            $em->persist($club);
            $em->flush();
            return $this->redirecttoRoute("club_search");
        }
        return $this->render("@Projet/Club/ajout.html.twig",array(
            'form'=>$form->createView()
        ) );


    }


    public function deleteAction(Request $request, $id){

        $club=new Club();
        $em=$this->getDoctrine()->getManager();
        $club=$em->getRepository("ProjetBundle:Club")->find($id);
        $em->remove($club);
        $em->flush();
        return $this->redirectToRoute("club_search");

    }

    public function updateAction(Request $request, $id){
        $em=$this->getDoctrine()->getManager();
        $club=$em->getRepository("ProjetBundle:Club")->find($id);
        $form=$this->createForm(ClubType::class,$club);
        if($request->isMethod('POST') && $form->handleRequest($request)->isSubmitted() && $form->isValid()) {

            $em = $this->getDoctrine()->getManager();
            $em->persist($club);
            $em->flush();
            return $this->redirecttoRoute("club_search");
        }
        return $this->render("@Projet/Club/update.html.twig",array(
            'form'=>$form->createView()
        ) );



    }


    public function searchAction(Request $request){
        $em = $this->getDoctrine()->getManager();
        $clubs=$em->getRepository("ProjetBundle:Club")->findAll();
        if($request->isMethod('Post')){
            $nom=$request->get('nom');
            $clubs=$em->getRepository("ProjetBundle:Club")->findBy(array("nom"=>$nom));
        }
        return $this->render("@Projet/Club/search.html.twig",array(
            'clubs'=>$clubs));

    }


    public function listAction(Request $request){
        $em = $this->getDoctrine()->getManager();
        $clubs=$em->getRepository("ProjetBundle:Club")->findAll();
        if($request->isMethod('Post')){
            $nom=$request->get('nom');
            $clubs=$em->getRepository("ProjetBundle:Club")->findBy(array("nom"=>$nom));
        }
        return $this->render("@Projet/Club/affich_user.html.twig",array(
            'clubs'=>$clubs));


    }
    public function trouverIdAction($id){
        $repository=$this->getDoctrine()->getManager()->getRepository(Media::class);
        $clubs = $repository->myfindid($id);
        return $this->render('@Projet/Media/affiche_media_user.html.twig',array(
            'clubs'=>$clubs));
    }
    public function subscriptionAction(Request $request){

        $subscription = new Subscription();
        $form=$this->createForm(SubscriptionType::class,$subscription);
        if($request->isMethod('POST') && $form->handleRequest($request)->isSubmitted() && $form->isValid()) {

            $em = $this->getDoctrine()->getManager();
            $em->persist($subscription);
            $em->flush();
            return $this->redirecttoRoute("user_club");
        }
        return $this->render("@Projet/Club/subscription.html.twig",array(
            'form'=>$form->createView()
        ) );


    }


    public function deleteuserAction($id){
        $em=$this->getDoctrine()->getManager();
        $media=$em->getRepository("ProjetBundle:Subscription")->find($id);
        $em->remove($media);
        $em->flush();
        return $this->redirectToRoute('user_club');
    }
    public function deleteuserrAction($id){
        $em=$this->getDoctrine()->getManager();
        $media=$em->getRepository("ProjetBundle:Subscription")->find($id);
        $em->remove($media);
        $em->flush();
        return $this->redirectToRoute('admin_club');
    }

    public function userdashAction(Request $request){
        $em = $this->getDoctrine()->getManager();
        $clubs=$em->getRepository("ProjetBundle:Subscription")->findAll();
        if($request->isMethod('Post')){
            $nomEnfant=$request->get('nomEnfant');
            $clubs=$em->getRepository("ProjetBundle:Subscription")->findBy(array("nomEnfant"=>$nomEnfant));
        }
        return $this->render("@Projet/Club/club_user.html.twig",array(
            'clubs'=>$clubs));

    }
    public function userdashbAction(Request $request){
        $em = $this->getDoctrine()->getManager();
        $clubs=$em->getRepository("ProjetBundle:Subscription")->findAll();
        if($request->isMethod('Post')){
            $nomEnfant=$request->get('nomEnfant');
            $clubs=$em->getRepository("ProjetBundle:Subscription")->findBy(array("nomEnfant"=>$nomEnfant));
        }
        return $this->render("@Projet/Club/admin_user.html.twig",array(
            'clubs'=>$clubs));

    }

    public function AllAction()
    {
        $club = $this->getDoctrine()->getManager()
            ->getRepository("ProjetBundle:Club")
            ->findAll();
        $serializer = new Serializer([new ObjectNormalizer()]);
        $formatted = $serializer->normalize($club);
        return new JsonResponse($formatted);
    }
    public function newAction(Request $request)
    {
        $em = $this->getDoctrine()->getManager();
        $Inscription = new Inscription();
        $Inscription ->setNomEnfant($request->get('nomEnfant'));
        $Inscription ->setClub($request->get('Club'));
        $em->persist($Inscription);
        $em->flush();
        $normalizer = new ObjectNormalizer();
        $normalizer->setCircularReferenceLimit(2);

        $normalizer->setCircularReferenceHandler(function ($object) {
            return $object->getId();
        });
        $serializer = new Serializer([$normalizer]);
        $formatted = $serializer->normalize($Inscription);
        return new JsonResponse($formatted);

    }
    public function SubscriptionJsonAction(Request $request)
    {
        $club = $this->getDoctrine()->getManager()
            ->getRepository("ProjetBundle:Subscription")

            ->findAll();
        $normalizer = new ObjectNormalizer();
        $normalizer->setCircularReferenceLimit(2);

        $normalizer->setCircularReferenceHandler(function ($object) {
            return $object->getId();
        });
        $serializer = new Serializer([$normalizer]);
        $formatted = $serializer->normalize($club);
        return new JsonResponse($formatted);
    }
    public function InscriptionAction()
    {
        $club = $this->getDoctrine()->getManager()
            ->getRepository("ProjetBundle:Inscription")
            ->findAll();
        $serializer = new Serializer([new ObjectNormalizer()]);
        $formatted = $serializer->normalize($club);
        return new JsonResponse($formatted);
    }
    public function SupprimerJsonAction($id)
    {
        $em = $this->getDoctrine()->getManager();
        $event = $em->getRepository("ProjetBundle:Subscription")
            ->findOneBy(array('id' => $id));
        $em = $this->getDoctrine()->getManager();
        $this->getDoctrine()->getManager()->remove($event);
        $em->remove($event);
        $em->flush();
        $serializer = new Serializer([new ObjectNormalizer()]);
        $formatted = $serializer->normalize($event);
        return new JsonResponse($formatted);
    }


    public function InscriptionJsonAction(Request $request,$id)
    {
        $em = $this->getDoctrine()->getManager();
        $club = $em->getRepository("ProjetBundle:Club")->find($id);
        $em = $this->getDoctrine()->getManager();
        $Subscription = new Subscription();

        $Subscription->setNomEnfant($request->get('NomEnfant'));
        $Subscription->setClub($club);
        $em->persist($Subscription);
        $em->flush();
        $serializer = new Serializer([new ObjectNormalizer()]);
        $formatted = $serializer->normalize($Subscription);
        return new JsonResponse($formatted);
    }
    public function mobileAfficherSubAction(){


        $em=$this->getDoctrine()->getManager();
        $subscription=$em->getRepository('ProjetBundle:Subscription')->findAll();
        $medial=array();

        foreach ( $subscription as $medias)
        {   $a=array(
            "id" => $medias->getId(),
            "Club" => $medias->getClub()->getNom(),
            "NomEnfant" => $medias->getNomEnfant(),

        );
            array_push($medial,$a);
        }
        $serializer = new Serializer([new ObjectNormalizer()]);
        $formatted = $serializer->normalize($subscription);
        return new JsonResponse($formatted);
    }
    public function mobileAfficheroneSubAction($id){
        $em=$this->getDoctrine()->getManager();
        $subscription=$em->getRepository('ProjetBundle:Subscription')->find($id);
        $response = new Response();
        $response->headers->set("Content-Type","application/json");
        if ($subscription!=null)
        {

            $response->setContent(json_encode(
                array(
            "id" => $subscription->getId(),
            "Club" => $subscription->getClub()->getNom(),
            "NomEnfant" => $subscription->getNomEnfant(),

                )));
            return $response;
        }
        return $response->setContent("{\"event\":{\"id\":0}}");
    }










}

