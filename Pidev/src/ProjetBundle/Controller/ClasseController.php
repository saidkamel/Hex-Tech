<?php

namespace ProjetBundle\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use ProjetBundle\Entity\Classe;
use ProjetBundle\Form\ClasseType;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;


class ClasseController extends Controller
{
    public function indexAction()
    {
        return $this->render('@Projet/Classe/index.html.twig');
    }

    public function showAction(){
        $em= $this->getDoctrine()->getManager();
        $classe =$em->getRepository('ProjetBundle:Classe')->findAll();
        return $this->render('@Projet/Classe/showclasse.html.twig',array(
            'classe'=> $classe));
    }

 /*public function showbyIdAction($id){
        $em= $this->getDoctrine()->getManager();
        $car =$em->getRepository('TestBundle:car')->find($id);
        return $this->render('@Test/Default/showcarbyid.html.twig',array(
            'car'=> $car));
    }*/
    public function addAction(Request $request)
    {
        $classe = new classe();
        $form = $this->createForm(ClasseType::class, $classe);
        $form->handleRequest($request);


        if ($form->isSubmitted() ) {
            $em = $this->getDoctrine()->getManager();
            $em->persist($classe);
            $em->flush();
            $this->addFlash('info', 'Created Successfully !');
            return $this->redirectToRoute('Show_Classe');
        }


        return $this->render('@Projet/Classe/addclasse.html.twig',array(
            'Form'=> $form->createView()));

    }


    public function editAction(Request $request,$id)
    {

        $em=$this->getDoctrine()->getManager();
        $classe= $em->getRepository('ProjetBundle:Classe')->find($id);
        $form=$this->createForm(ClasseType::class,$classe);
        $form->handleRequest($request);

        if ($form->isSubmitted() ) {
            $em = $this->getDoctrine()->getManager();
            $em->persist($classe);
            $em->flush();
            $this->addFlash('info', 'Created Successfully !');
            return $this->redirectToRoute('Show_Classe');
        }


        return $this->render('@Projet/Classe/editclasse.html.twig',array(
            'Form'=> $form->createView()));




    }

    public function deleteAction($qdt)
    {

        $em= $this->getDoctrine()->getManager();
        $classe =$em->getRepository('ProjetBundle:Classe')->find($qdt);
        $em->remove($classe);
        $em->flush();
        return $this->redirectToRoute('Show_Classe');


    }




}
