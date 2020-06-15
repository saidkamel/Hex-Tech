<?php

namespace ProjetBundle\Controller;

use ProjetBundle\Entity\Profilmedicale;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use ProjetBundle\Entity\Pediatre;
use ProjetBundle\Form\PediatreType;
use Symfony\Component\HttpFoundation\Request;



class PediatreController extends Controller
{
    public function indexAction()
    {
        return $this->render('@Projet/Pediatre/index.html.twig');
    }

    public function showAction(){
        $em= $this->getDoctrine()->getManager();
        $pediatre =$em->getRepository('ProjetBundle:Pediatre')->findAll();
        return $this->render('@Projet/Pediatre/showpediatre.html.twig',array(
            'pediatre'=> $pediatre));
    }




    /*public function showbyIdAction($id){
        $em= $this->getDoctrine()->getManager();
        $car =$em->getRepository('TestBundle:car')->find($id);
        return $this->render('@Test/Default/showcarbyid.html.twig',array(
            'car'=> $car));
    }*/
    public function addAction(Request $request)
    {
        $pediatre = new pediatre();
        $form = $this->createForm(PediatreType::class, $pediatre);
        $form->handleRequest($request);


        if ($request->isMethod('POST') && $form->isSubmitted() && $form->isValid()) {
            $em = $this->getDoctrine()->getManager();
            $em->persist($pediatre);
            $em->flush();
            $this->addFlash('info', 'Created Successfully !');
            return $this->redirectToRoute('Show_Pediatre');
        }


        return $this->render('@Projet/Pediatre/addpediatre.html.twig',array(
            'Form'=> $form->createView()));

    }


    public function editAction(Request $request,$id)
    {

        $em=$this->getDoctrine()->getManager();
        $pediatre= $em->getRepository('ProjetBundle:Pediatre')->find($id);
        $form=$this->createForm(PediatreType::class,$pediatre);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $em = $this->getDoctrine()->getManager();
            $em->persist($pediatre);
            $em->flush();
            $this->addFlash('info', 'Created Successfully !');
            return $this->redirectToRoute('Show_Pediatre');
        }


        return $this->render('@Projet/Pediatre/editPediatre.html.twig',array(
            'Form'=> $form->createView()));




    }

    public function deleteAction($qdt)
    {

        $em= $this->getDoctrine()->getManager();
        $pediatre =$em->getRepository('ProjetBundle:Pediatre')->find($qdt);

        $em->remove($pediatre);
        $em->flush();
        return $this->redirectToRoute('Show_Pediatre');


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

}
