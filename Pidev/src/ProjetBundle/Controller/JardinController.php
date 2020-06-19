<?php

namespace ProjetBundle\Controller;

use CMEN\GoogleChartsBundle\GoogleCharts\Charts\PieChart;
use GuzzleHttp\Psr7\UploadedFile;
use ProjetBundle\Entity\Jardin;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\Method;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\Route;
use Symfony\Component\HttpFoundation\File\File;
use Symfony\Component\HttpFoundation\Request;

/**
 * Jardin controller.
 *
 * @Route("jardin")
 */
class JardinController extends Controller
{
    /**
     * Lists all jardin entities.
     *
     * @Route("/show_jardin", name="jardin_index")
     * @Method("GET")
     */
    public function indexAction()
    {
        $em = $this->getDoctrine()->getManager();

        $jardins = $em->getRepository('ProjetBundle:Jardin')->findAll();
        $pieChart = new PieChart();
        $queryx = $em->createQuery("SELECT p.nom ,p.capacite FROM JardinBundle:Jardin p ");
        $views = $queryx->getResult();


        $pieChart->getData()->setArrayToDataTable($views,'nom','capacite');
        $pieChart->getOptions()->setTitle('Courbe Des Capacités Des Jardins');
        $pieChart->getOptions()->setHeight(500);
        $pieChart->getOptions()->setWidth(900);
        $pieChart->getOptions()->getTitleTextStyle()->setBold(true);
        $pieChart->getOptions()->getTitleTextStyle()->setColor('#009900');
        $pieChart->getOptions()->getTitleTextStyle()->setItalic(true);
        $pieChart->getOptions()->getTitleTextStyle()->setFontName('Arial');
        $pieChart->getOptions()->getTitleTextStyle()->setFontSize(20);

        return $this->render('jardin/index.html.twig', array(
            'jardins' => $jardins,
            'piechart' => $pieChart,
        ));
    }

    /**
     * Lists all jardin entities.
     *
     * @Route("/front", name="jardin_front")
     * @Method("GET")
     */
    public function frontAction()
    {
        $em = $this->getDoctrine()->getManager();

        $jardins = $em->getRepository('ProjetBundle:Jardin')->findAll();


        return $this->render('jardin/front.html.twig', array(
            'jardins' => $jardins,
        ));
    }


    /**
     * Creates a new jardin entity.
     *
     * @Route("/newJardin", name="jardin_new")
     * @Method({"GET", "POST"})
     */
    public function newAction(Request $request)
    {
        $jardin = new Jardin();
        $form = $this->createForm('ProjetBundle\Form\JardinType', $jardin);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            /**
             * @var UploadedFile $file
             */
            $file=$jardin->getImage();
            $fileName = md5(uniqid()).'.'.$file->guessExtension();

            $file->move(
                $this->getParameter('image_directory'),$fileName
            );

            $jardin->setImage($fileName);
            $em = $this->getDoctrine()->getManager();
            $em->persist($jardin);
            $em->flush();

            return $this->redirectToRoute('jardin_show', array('id' => $jardin->getId()));
        }

        return $this->render('jardin/new.html.twig', array(
            'jardin' => $jardin,
            'form' => $form->createView(),
        ));
    }

    /**
     * Finds and displays a jardin entity.
     *
     * @Route("/{id}", name="jardin_show")
     * @Method("GET")
     */
    public function showAction(Jardin $jardin)
    {
        $deleteForm = $this->createDeleteForm($jardin);

        return $this->render('jardin/show.html.twig', array(
            'jardin' => $jardin,
            'delete_form' => $deleteForm->createView(),
        ));
    }

    /**
     * Displays a form to edit an existing jardin entity.
     *
     * @Route("/{id}/edit", name="jardin_edit")
     * @Method({"GET", "POST"})
     */
    public function editAction(Request $request, Jardin $jardin)
    {
        $img=$jardin->getImage();
        $jardin->setImage(
            new File($this->getParameter('image_directory').'/'.$jardin->getImage()
            ));
        $deleteForm = $this->createDeleteForm($jardin);
        $editForm = $this->createForm('ProjetBundle\Form\JardinType', $jardin);
        $editForm->handleRequest($request);

        if ($editForm->isSubmitted() && $editForm->isValid()) {
            if($jardin->getImage() !== null) {
                //Type hint
                /** @var Symfony\Component\HttpFoundation\File\UploadedFile $newImage*/
                $newImage=$jardin->getImage();
                $newImageName= md5(uniqid()).'.'.$newImage->guessExtension();
                $newImage->move($this->getParameter('image_directory'), $newImageName);
                $jardin->setImage($newImageName);
            } else {
                //Restore old file name
                $jardin->setImage($img);
            }
            $this->getDoctrine()->getManager()->flush();

            return $this->redirectToRoute('jardin_edit', array('id' => $jardin->getId()));
        }

        return $this->render('jardin/edit.html.twig', array(
            'jardin' => $jardin,
            'edit_form' => $editForm->createView(),
            'delete_form' => $deleteForm->createView(),
        ));
    }

    /**
     * Deletes a jardin entity.
     *
     * @Route("/{id}", name="jardin_delete")
     * @Method("DELETE")
     */
    public function deleteAction(Request $request, Jardin $jardin)
    {
        $form = $this->createDeleteForm($jardin);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $em = $this->getDoctrine()->getManager();
            $em->remove($jardin);
            $em->flush();
        }

        return $this->redirectToRoute('jardin_index');
    }

    /**
     * Creates a form to delete a jardin entity.
     *
     * @param Jardin $jardin The jardin entity
     *
     * @return \Symfony\Component\Form\Form The form
     */
    private function createDeleteForm(Jardin $jardin)
    {
        return $this->createFormBuilder()
            ->setAction($this->generateUrl('jardin_delete', array('id' => $jardin->getId())))
            ->setMethod('DELETE')
            ->getForm()
        ;
    }
}
