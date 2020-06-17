<?php

namespace ProjetBundle\Controller;

use CMEN\GoogleChartsBundle\GoogleCharts\Charts\PieChart;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use ProjetBundle\Entity\Classe;
use ProjetBundle\Form\ClasseType;
use Symfony\Component\HttpFoundation\JsonResponse;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Serializer\Normalizer\ObjectNormalizer;
use Symfony\Component\Serializer\Serializer;


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
    public function allAction()
    {
        $event = $this->getDoctrine()->getManager()->getRepository('ProjetBundle:Classe')->findAll();
        $serializer = new Serializer([new ObjectNormalizer()]);
        $formatted = $serializer->normalize($event);
        return new JsonResponse($formatted);
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


    public function graphAction()
    {
        $em= $this->getDoctrine()->getManager();
        $classes = $em->getRepository('ProjetBundle:Classe')->findAll();
        $pieChart = new PieChart();
        $queryx = $em->createQuery("SELECT classe.nomclasse ,classe.activite | length FROM ProjetBundle:Classe classe ");
        $views = $queryx->getResult();



        $pieChart->getData()->setArrayToDataTable($views,'nomclasse','activite | length');
        $pieChart->getOptions()->setTitle('Nombres des activités par classe');
        $pieChart->getOptions()->setHeight(500);
        $pieChart->getOptions()->setWidth(900);
        $pieChart->getOptions()->getTitleTextStyle()->setBold(true);
        $pieChart->getOptions()->getTitleTextStyle()->setColor('#009900');
        $pieChart->getOptions()->getTitleTextStyle()->setItalic(true);
        $pieChart->getOptions()->getTitleTextStyle()->setFontName('Arial');
        $pieChart->getOptions()->getTitleTextStyle()->setFontSize(20);

        return $this->render('@Projet/Classe/chart.html.twig', array(
            'classes' => $classes,
            'piechart' => $pieChart,
        ));
    }
    public function frontClasseAction(){
        $em= $this->getDoctrine()->getManager();
        $classe =$em->getRepository('ProjetBundle:Classe')->findAll();
        return $this->render('@Projet/Classe/frontClasse.html.twig',array(
            'classe'=> $classe));
    }
    public function showdetailClasseAction($id){
        $em= $this->getDoctrine()->getManager();
        $classe =$em->getRepository('ProjetBundle:Classe')->find($id);
        return $this->render('@Projet/Classe/show_detailClasse.html.twig',array(
            'classe'=> $classe));
    }





}
