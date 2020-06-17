<?php

namespace ProjetBundle\Controller;

use ProjetBundle\Entity\EmploiDuTemps;
use ProjetBundle\Form\EmploiDuTempsType;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\HttpFoundation\JsonResponse;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\Form\Extension\Core\Type\FileType;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;
use Symfony\Component\Serializer\Normalizer\ObjectNormalizer;
use Symfony\Component\Serializer\Serializer;
use Symfony\Component\HttpFoundation\Response;


class EmploiDuTempsController extends Controller
{
    public function ajoutAction(Request $request){

        $emploi = new EmploiDuTemps();
        $form=$this->createFormBuilder($emploi)
            ->add('classe')
            ->add('image', FileType::class, array('data_class'=>null, 'required'=>false))
            ->add('Valider', SubmitType::class)
            ->getForm();
        $form->handleRequest($request);
        if($form->isSubmitted()) {
                $file = $emploi->getImage();
                $fileName = md5(uniqid()).'.'.$file->guessExtension();
                $file->move($this->getParameter('photos_directory'), $fileName);
            $emploi->setImage($fileName);

            $em = $this->getDoctrine()->getManager();
            $em->persist($emploi);
            $em->flush();
            return $this->redirecttoRoute("EmploiDuTemps_search");
        }
        return $this->render("@Projet/EmploiDuTemps/ajout.html.twig",array(
            'f'=>$form->createView()
        ) );


    }

    public function deleteAction(Request $request, $id){

        $emploi=new EmploiDuTemps();
        $em=$this->getDoctrine()->getManager();
        $emploi=$em->getRepository("ProjetBundle:EmploiDuTemps")->find($id);
        $em->remove($emploi);
        $em->flush();
        return $this->redirectToRoute("EmploiDuTemps_search");

    }

    public function updateAction(Request $request, $id){
        $em=$this->getDoctrine()->getManager();
        $emploi=$em->getRepository("ProjetBundle:EmploiDuTemps")->find($id);
        $form=$this->createForm(EmploiDuTempsType::class,$emploi);
        $form->handleRequest($request);
        if($form->isSubmitted()) {

            $em = $this->getDoctrine()->getManager();
            $em->persist($emploi);
            $em->flush();
            return $this->redirecttoRoute("EmploiDuTemps_search");
        }
        return $this->render("@Projet/EmploiDuTemps/update.html.twig",array(
            'form'=>$form->createView()
        ) );



    }


    public function listAction(Request $request){
        $em = $this->getDoctrine()->getManager();
        $emploi=$em->getRepository("ProjetBundle:EmploiDuTemps")->findAll();
        if($request->isMethod('Post')){
            $classe=$request->get('Classe');
            $emploi=$em->getRepository("ProjetBundle:EmploiDuTemps")->findBy(array("classe"=>$classe));
        }
        return $this->render("@Projet/EmploiDuTemps/affiche_emploi.html.twig",array(
            'EmploiDuTemps'=>$emploi));

    }

    public function AllAction()
    {
        $emploi = $this->getDoctrine()->getManager()
            ->getRepository("ProjetBundle:EmploiDuTemps")
            ->findAll();
        $result=[];
        foreach ($emploi as $p){
            $tmp=array(["id" => $p->getId(),
                "img" =>$p->getImage(),
                "Classe" =>$p->getClasse(),

            ]);
            $result = array_merge($result,$tmp);
        }
        $serializer = new Serializer([ new ObjectNormalizer()]);
        $formatted = $serializer->normalize($result);
        return new JsonResponse($formatted);

    }
    public function searchAction(Request $request){
        $em = $this->getDoctrine()->getManager();
        $M=$em->getRepository("ProjetBundle:EmploiDuTemps")->findAll();
        if($request->isMethod('Post')){
            $nom=$request->get('Classe');
            $M=$em->getRepository("ProjetBundle:EmploiDuTemps")->findBy(array("nom"=>$nom));
        }
        return $this->render("@Projet/EmploiDuTemps/affiche_emploi.html.twig",array(
            'clubs'=>$M));

    }
    public function searchUTAction(Request $request){
        $em = $this->getDoctrine()->getManager();
        $M=$em->getRepository("ProjetBundle:EmploiDuTemps")->findAll();
        if($request->isMethod('Post')){
            $nom=$request->get('Classe');
            $M=$em->getRepository("ProjetBundle:EmploiDuTemps")->findBy(array("nom"=>$nom));
        }
        return $this->render("@Projet/EmploiDuTemps/affiche_emploi_UT.html.twig",array(
            'clubs'=>$M));

    }

    public function AfficherAllEmploiAction(){
        $em=$this->getDoctrine()->getManager();
        $emploi=$em->getRepository('ProjetBundle:EmploiDuTemps')->findAll();
        $response = new Response();
        $medial=array();

        $response->headers->set("Content-Type","application/json");
        foreach ( $emploi as $emploi)
        {   $a=array(
            "id" => $emploi->getId(),
            "img" => $emploi->getImage(),
            "Classe" => $emploi->getClasse(),
        );
            array_push($medial,$a);
        }
        $response->setContent(json_encode( array(
            "EmploiDuTemps" => $medial)));
        return $response;
    }

}
