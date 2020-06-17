<?php


namespace ProjetBundle\Controller;



use ProjetBundle\Entity\Enfant;
use ProjetBundle\Form\EnfantType;
use ProjetBundle\Form\EnfantUTType;
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




class EnfantController extends Controller
{
    public function AllAction()
    {
        $enfant = $this->getDoctrine()->getManager()
            ->getRepository("ProjetBundle:Enfant")
            ->findAll();
        $result=[];
        foreach ($enfant as $p){
            $tmp=array(["id" => $p->getId(),
                "Nom" =>$p->getNom(),
                "Prenom" =>$p->getPrenom(),
                "DateNaissance"=>$p->getDateNaissance()->format('d-m-y'),

            ]);
            $result = array_merge($result,$tmp);
        }
        $serializer = new Serializer([ new ObjectNormalizer()]);
        $formatted = $serializer->normalize($result);
        return new JsonResponse($formatted);

    }

    public function DeleteEnfantAction($id, Request $request)
    {

        $em = $this->getDoctrine()->getManager();
        $enfant = $em->getRepository("ProjetBundle:Enfant")->find($id);

        $em->remove($enfant);
        $em->flush();

        $serializer = new Serializer([new ObjectNormalizer()]);
        $formatted = $serializer->normalize($enfant);
        return new JsonResponse($formatted);
    }

    public function ModifierEnfantAction(Request $request, Enfant $enfant)
    {
        $editForm = $this->createForm('ProjetBundle\Form\EnfantType', $enfant);
        $editForm->handleRequest($request);
        $enfant->setNom($request->get('Nom'));
        $enfant->setPrenom($request->get('Prenom'));
        $enfant->setDateNaissance(\DateTime::createFromFormat('d/m/Y',$request->get('DateNaissance')));

        $this->getDoctrine()->getManager()->flush();
        $serializer = new Serializer([new ObjectNormalizer()]);
        $formatted = $serializer->normalize($enfant);
        return new JsonResponse($formatted);


    }

    public function newenfantAction(Request $request)
    {
        $em = $this->getDoctrine()->getManager();
        $Inscription = new Enfant();
        $Inscription = new Enfant();
        $Inscription ->setNom($request->get('Nom'));
        $Inscription ->setPrenom($request->get('Prenom'));
        $Inscription->setDateNaissance(\DateTime::createFromFormat('d/m/Y',$request->get('DateNaissance'))); 

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
    public function ajoutAction(Request $request){

        $enfant = new Enfant();
        $form=$this->createForm(EnfantType::class,$enfant);
        $form->handleRequest($request);
        if($form->isSubmitted()) {


            $em = $this->getDoctrine()->getManager();
            $em->persist($enfant);
            $em->flush();
            return $this->redirecttoRoute("enfant_search");
        }
        return $this->render("@Projet/Enfant/ajout.html.twig",array(
            'form'=>$form->createView()
        ) );
    }

    public function ajoutUTAction(Request $request){

        $enfant = new Enfant();
        $form=$this->createForm(EnfantUTType::class,$enfant);
        $form->handleRequest($request);
        if($form->isSubmitted()) {


            $em = $this->getDoctrine()->getManager();
            $em->persist($enfant);
            $em->flush();
            return $this->redirecttoRoute("enfant_search_UT");
        }
        return $this->render("@Projet/Enfant/ajoutUT.html.twig",array(
            'form'=>$form->createView()
        ) );
    }

    public function deleteAction(Request $request, $id){

        $enfant=new Enfant();
        $em=$this->getDoctrine()->getManager();
        $enfant=$em->getRepository("ProjetBundle:Enfant")->find($id);
        $em->remove($enfant);
        $em->flush();
        return $this->redirectToRoute("enfant_search");

    }

    public function deleteUTAction(Request $request, $id){

        $enfant=new Enfant();
        $em=$this->getDoctrine()->getManager();
        $enfant=$em->getRepository("ProjetBundle:Enfant")->find($id);
        $em->remove($enfant);
        $em->flush();
        return $this->redirectToRoute("enfant_search_UT");

    }

    public function updateAction(Request $request, $id){
        $em=$this->getDoctrine()->getManager();
        $enfant=$em->getRepository("ProjetBundle:Enfant")->find($id);
        $form=$this->createForm(EnfantType::class,$enfant);
        $form->handleRequest($request);
        if($form->isSubmitted()) {

            $em = $this->getDoctrine()->getManager();
            $em->persist($enfant);
            $em->flush();
            return $this->redirecttoRoute("enfant_search");
        }
        return $this->render("@Projet/enfant/update.html.twig",array(
            'form'=>$form->createView()
        ) );
    }

    public function updateUTAction(Request $request, $id){
        $em=$this->getDoctrine()->getManager();
        $enfant=$em->getRepository("ProjetBundle:Enfant")->find($id);
        $form=$this->createForm(EnfantUTType::class,$enfant);
        $form->handleRequest($request);
        if($form->isSubmitted()) {

            $em = $this->getDoctrine()->getManager();
            $em->persist($enfant);
            $em->flush();
            return $this->redirecttoRoute("enfant_search_UT");
        }
        return $this->render("@Projet/enfant/update_UT.html.twig",array(
            'form'=>$form->createView()
        ) );



    }
    public function listUTAction(Request $request){
        $em = $this->getDoctrine()->getManager();
        $enfant=$em->getRepository("ProjetBundle:Enfant")->findAll();
        if($request->isMethod('Post')){
            $nom=$request->get('nom');
            $enfant=$em->getRepository("ProjetBundle:Enfant")->findBy(array("nom"=>$nom));
        }
        return $this->render("@Projet/Enfant/affiche_UT.html.twig",array(
            'enfants'=>$enfant));

    }

    public function listAction(Request $request){
        $em = $this->getDoctrine()->getManager();
        $enfant=$em->getRepository("ProjetBundle:Enfant")->findAll();
        if($request->isMethod('Post')){
            $nom=$request->get('nom');
            $enfant=$em->getRepository("ProjetBundle:Enfant")->findBy(array("nom"=>$nom));
        }
        return $this->render("@Projet/Enfant/affiche.html.twig",array(
            'enfants'=>$enfant));

    }



}





