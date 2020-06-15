<?php

namespace ProjetBundle\Controller;

use Doctrine\ORM\EntityRepository;
use Symfony\Bridge\Doctrine\Form\Type\EntityType;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use ProjetBundle\Entity\Profilmedicale;
use ProjetBundle\Form\ProfilmedicaleType;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\JsonResponse;
use Symfony\Component\Security\Core\Authentication\Token\UsernamePasswordToken;
use Symfony\Component\Security\Http\Event\InteractiveLoginEvent;
use Symfony\Component\Serializer\Normalizer\ObjectNormalizer;
use Symfony\Component\Serializer\Serializer;
use Symfony\Component\Serializer\Encoder\JsonEncoder;

use Symfony\Component\HttpFoundation\Response;
use AppBundle\Entity\User;




class ProfilmedicaleController extends Controller
{
    public function indexAction()
    {
        return $this->render('@Projet/Profilmedicale/index.html.twig');
    }

    public function showAction(){
        $em= $this->getDoctrine()->getManager();
        $profilmedicale =$em->getRepository('ProjetBundle:Profilmedicale')->findAll();
        return $this->render('@Projet/Profilmedicale/showprofil.html.twig',array(
            'profilmedicale'=> $profilmedicale));
    }


    public function addAction(Request $request)
    {
        $profilmedicale = new profilmedicale();
        $form = $this->createForm(ProfilmedicaleType::class, $profilmedicale);
        $form->handleRequest($request);


        if ($form->isSubmitted() && $form->isValid()) {
            $em = $this->getDoctrine()->getManager();
            $em->persist($profilmedicale);
            $enfant=$profilmedicale->getEnfant();
            $enfant->setProfilmedicale($profilmedicale);
            $em->persist($enfant);
            $em->flush();
            $this->addFlash('info', 'Created Successfully !');
            return $this->redirectToRoute('Show_Profil');
        }


        return $this->render('@Projet/Profilmedicale/addprofil.html.twig',array(
            'Form'=> $form->createView()));

    }


    public function editAction(Request $request,$id)
    {

        $em=$this->getDoctrine()->getManager();
        $profilmedicale= $em->getRepository('ProjetBundle:Profilmedicale')->find($id);
        $oldenfant=$profilmedicale->getEnfant();
        //$form=$this->createForm(ProfilmedicaleType::class,$profilmedicale);
        $form = $this->createFormBuilder($profilmedicale)
            ->add('enfant',EntityType::class,array(
            'class'=>'ProjetBundle:Enfant'
        ,
            'choice_label'=>'id',

            'multiple'=>false
        ))

            ->add('taille')
            ->add('poids')
            ->add('maladie')
            ->add('etat')
            ->add('idpediatre',EntityType::class,array(
                'class'=>'ProjetBundle:Pediatre',
                'label'=>'Pediatre',


                'query_builder'=> function(EntityRepository $arg){
                    return $arg->createQueryBuilder('e');

                }
            ,
                'choice_label'=>'nom',

                'multiple'=>false
            ))
            ->add('Submit',SubmitType::class)
            ->getForm();


        $form->handleRequest($request);


        if ($form->isSubmitted() && $form->isValid()) {
            $em = $this->getDoctrine()->getManager();
            $oldenfant->setProfilmedicale(NULL);
            $em->persist($oldenfant);
            $em->persist($profilmedicale);
            $enfant=$profilmedicale->getEnfant();
            $enfant->setProfilmedicale($profilmedicale);
            $em->persist($enfant);
            $em->flush();
            $this->addFlash('info', 'Created Successfully !');
            return $this->redirectToRoute('Show_Profil');
        }


        return $this->render('@Projet/Profilmedicale/editProfil.html.twig',array(
            'Form'=> $form->createView()));




    }

    public function deleteAction($qdt)
    {

        $em= $this->getDoctrine()->getManager();
        $profilmedicale =$em->getRepository('ProjetBundle:Profilmedicale')->find($qdt);
        $enfant=$profilmedicale->getEnfant();
        $enfant->setProfilmedicale(NULL);
        $em->persist($enfant);
        $em->remove($profilmedicale);
        $em->flush();
        return $this->redirectToRoute('Show_Profil');


    }
    public function showfrontAction()
    {
        $em= $this->getDoctrine()->getManager();
        $enfant =$em->getRepository('ProjetBundle:Enfant')->findAll();
        return $this->render('@Projet/Profilmedicale/front.html.twig',array(
            'enfant'=> $enfant));
    }
    public function showdetailAction($id)
    {
        $em= $this->getDoctrine()->getManager();
        $enfant =$em->getRepository('ProjetBundle:Enfant')->find($id);
        return $this->render('@Projet/Profilmedicale/showdetail.html.twig',array(
            'enfant'=> $enfant));
    }
    public function allAction()
    {
        $em= $this->getDoctrine()->getManager();
        $profilmedicale =$em->getRepository("ProjetBundle:Profilmedicale")->findAll();


        $normalizer = array(new ObjectNormalizer());
        $normalizer[0]->setCircularReferenceLimit(2);

        $normalizer[0]->setCircularReferenceHandler(function ($object){
            return $object->getId();
        }); 


        $serializer = new Serializer($normalizer,array(new JsonEncoder()));
        $formatted = $serializer->serialize($profilmedicale,'json');

        return new Response($formatted);




    }
    /* $serializer = new Serializer ([ new ObjectNormalizer()]);
           $formatted = $serializer->normalize($profilmedicale);
           return new JsonResponse($formatted);*/
    public function toPDFAction(Request $request, int $id)
    {
        $snappy = $this->get('knp_snappy.pdf');
        $filename = 'myFirstSnappyPDF';
        $url = 'http://localhost/pidev/web/app_dev.php/showdetail/'.$id;


        return new Response(
            $snappy->getOutput($url),
            200,
            array(
                'Content-Type'          => 'application/pdf',
                'Content-Disposition'   => 'inline; filename="'.$filename.'.pdf"'
            )
        );

    }

    public function getOneAction($id)
    {
        $em= $this->getDoctrine()->getManager();
        $profilmedicale =$em->getRepository("ProjetBundle:Profilmedicale")->find($id);


        $normalizer = array(new ObjectNormalizer());
        $normalizer[0]->setCircularReferenceLimit(2);

        $normalizer[0]->setCircularReferenceHandler(function ($object){
            return $object->getId();
        });


        $serializer = new Serializer($normalizer,array(new JsonEncoder()));
        $formatted = $serializer->serialize($profilmedicale,'json');

        return new Response($formatted);




    }



}
