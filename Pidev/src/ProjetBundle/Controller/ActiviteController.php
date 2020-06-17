<?php

namespace ProjetBundle\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use ProjetBundle\Entity\Activite;
use ProjetBundle\Form\ActiviteType;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\HttpFoundation\JsonResponse;
use Symfony\Component\Serializer\Normalizer\ObjectNormalizer;
use Symfony\Component\Serializer\Serializer;

class ActiviteController extends Controller
{
    public function indexAction(Request $request)
    {   $em= $this->getDoctrine()->getManager();
        $query =$em->getRepository('ProjetBundle:Activite')->findAll();
        $paginator = $this->get('knp_paginator');
        $activite = $paginator->paginate(
            $query, /* query NOT result */
            $request->query->getInt('page', 1), /*page number*/
            3 /*limit per page*/
        );
        return $this->render('@Projet/Default/index.html.twig', array(
            'activite'=> $activite));
    }
    public function affAction()
    {
        $event = $this->getDoctrine()->getManager()->getRepository('ProjetBundle:Activite')->findAll();
        $serializer = new Serializer([new ObjectNormalizer()]);
        $formatted = $serializer->normalize($event);
        return new JsonResponse($formatted);
    }
    public function newAction(Request $request)
    {
        $em = $this->getDoctrine()->getManager();
        $Inscription = new Activite();
        $Inscription = new Activite();
        $Inscription->setNomActivite($request->get('NomActivite'));
        $Inscription->setDescription($request->get('Description'));
        $Inscription->setType($request->get('Type'));
        $em->persist($Inscription);
        $em->flush();
        $normalizer = new ObjectNormalizer();
        $normalizer->setCircularReferenceLimit(2);
        $normalizer->setCircularReferenceHandler(function ($object) {
            return $object->getIdactivite();
        });
        $serializer = new Serializer([$normalizer]);
        $formatted = $serializer->normalize($Inscription);
        return new JsonResponse($formatted);
    }
    public function showAction(){
        $em= $this->getDoctrine()->getManager();
        $activite =$em->getRepository('ProjetBundle:Activite')->findAll();
        return $this->render('@Projet/Activite/showactivite.html.twig',array(
            'activite'=> $activite));
    }






    /*public function showbyIdAction($id){
        $em= $this->getDoctrine()->getManager();
        $car =$em->getRepository('TestBundle:car')->find($id);
        return $this->render('@Test/Default/showcarbyid.html.twig',array(
            'car'=> $car));
    }*/
    public function addAction(Request $request)
    {
        $activite = new activite();
        $form = $this->createForm(ActiviteType::class, $activite);
        $form->handleRequest($request);


        if ($form->isSubmitted() ) {
            $em = $this->getDoctrine()->getManager();
            $em->persist($activite);
            $em->flush();
            $this->addFlash('info', 'Created Successfully !');
            return $this->redirectToRoute('Show_Activite');
        }


        return $this->render('@Projet/Activite/addactivite.html.twig',array(
            'Form'=> $form->createView()));

    }


    public function editAction(Request $request,$id)
    {

        $em=$this->getDoctrine()->getManager();
        $activite= $em->getRepository('ProjetBundle:Activite')->find($id);
        $form=$this->createForm(ActiviteType::class,$activite);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $em = $this->getDoctrine()->getManager();
            $em->persist($activite);
            $em->flush();
            $this->addFlash('info', 'Created Successfully !');
            return $this->redirectToRoute('Show_Activite');
        }


        return $this->render('@Projet/Activite/editactivite.html.twig',array(
            'Form'=> $form->createView()));




    }

    public function deleteAction($qdt)
    {

        $em= $this->getDoctrine()->getManager();
        $activite =$em->getRepository('ProjetBundle:Activite')->find($qdt);
        $em->remove($activite);
        $em->flush();
        return $this->redirectToRoute('Show_Activite');


    }


    /*
        public function searchAction(Request $request)
        {
            $em = $this->getDoctrine()->getManager();
            $requestString = $request->get('q');
            $car =  $em->getRepository('TestBundle:car')->find($requestString);
            if(!$car) {
                $result['car']['error'] = "Post Not found :( ";
            } else {
                $result['id'] = $car->getId();
                $result['nom'] = $car->getnom();
                $result['marque'] = $car->getmarque();
                $result['age'] = $car->getage();

            }
            return new Response(json_encode($result));
        }*/

    public function toPDFAction(){
        $snappy = $this->get("knp_snappy.pdf");
        $filename = "Liste-activite";
        $websiteurl = "http://localhost/Pidev1/web/app_dev.php/showactivite";
        return new Response (
            $snappy->getOutPut($websiteurl),200,array(
                'Content-Type'=>'application/pdf',
                'Content-Disposition'=>'inline; filename="'.$filename.'.pdf"'
            )

        );
    }

}
