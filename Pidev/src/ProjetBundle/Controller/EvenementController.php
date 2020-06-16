<?php

namespace ProjetBundle\Controller;

use ProjetBundle\Entity\Evenement;
use ProjetBundle\Entity\Participant;
use ProjetBundle\Form\EvenementType;
use ProjetBundle\Form\ParticipantType;
#use http\Env\Response;
use JsonSchema\Constraints\ObjectConstraint;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\Form\Extension\Core\Type\EmailType;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;
use Symfony\Component\Form\Extension\Core\Type\TextareaType;
use Symfony\Component\HttpFoundation\JsonResponse;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\Serializer\Normalizer\ObjectNormalizer;
use Symfony\Component\Serializer\Serializer;
use Symfony\Component\Serializer\Encoder\JsonEncoder;
use Symfony\Component\HttpFoundation\Response;

class EvenementController extends Controller
{
    public function AjoutEvenementAction(Request $request)
    {
           $evenement = new Evenement();
           $evenement->setNbrParticipants(0);
           $form = $this->createForm(EvenementType::class,$evenement);
           $form->handleRequest($request);

           if($form->isSubmitted())
           {
               $em =$this->getDoctrine()->getManager();
               $em->persist($evenement);
               $em->flush();
               $this ->redirectToRoute('evenement_AfficherE');
           }

           return $this->render("@Projet/Evenement/ajoutE.html.twig",array('form'=>$form->createView()));
    }



    public function AjoutParticipantAction(Request $request,$id)
    {
        $em= $this->getDoctrine()->getManager();
        $evenement = $em->getRepository(Evenement::class)->find($id);


        $participant = new Participant();
        $form = $this->createForm(ParticipantType::class,$participant);
        $form->handleRequest($request);

        if($form->isSubmitted())
        {
            $em =$this->getDoctrine()->getManager();
            $em->persist($evenement);
            $em->persist($participant);
            $em->flush();
            $this ->redirectToRoute('evenement_AfficheP');

        }
        $serializer = new Serializer([new ObjectNormalizer()]);
        $formatted = $serializer->normalize($participant);
        return new JsonResponse($formatted);
        //return $this->render("@Evenement/Participant/AjoutP.html.twig",array('form'=>$form->createView()));
    }




    public function AfficheEvenementAction(Request $request)
    {
      $em = $this->getDoctrine()->getManager();
      $dql = "SELECT ev FROM ProjetBundle:Evenement ev";
      $query = $em->createQuery($dql);
        /**
         * @var $paginator \Knp\Component\Pager\Paginator
         */
      $paginator = $this->get('knp_paginator');
      dump(get_class($paginator));
      $result =$paginator->paginate(
          $query,
          $request->query->getInt('page',1),
          $request->query->getInt('limit',5)

      );
      return $this->render("@Projet/Evenement/AfficheE.html.twig",array('evenement'=>$result));
    }




    public function  AfficheParticipantAction(Request $request)
    {
        $em = $this->getDoctrine()->getManager();
        #$evenement = $em ->getRepository("EvenementBundle:Participant")->findAll();
        $dql = "SELECT pr FROM ProjetBundle:Participant pr";
        $query = $em->createQuery($dql);
        /**
         * @var $paginator \Knp\Component\Pager\Paginator
         */
        $paginator = $this->get('knp_paginator');
        dump(get_class($paginator));
        $result =$paginator->paginate(
            $query,
            $request->query->getInt('page',1),
            $request->query->getInt('limit',10)
        );
        return $this->render("@Projet/Participant/AfficheP.html.twig",array('participant'=>$result));
    }





    public function FrontAction()
    {
        $em = $this->getDoctrine()->getManager();
        $participant = $em ->getRepository("ProjetBundle:Participant")->findAll();
        return $this->render("@Projet/Evenement/Front.html.twig",array('participant'=>$participant));
    }




    public function SupprimeParticipantAction($id)
    {
        $em = $this->getDoctrine()->getManager();
        $participant =  $em->getRepository(Participant::class)->find($id);
        $evenement =  $em->getRepository(Evenement::class)->find($participant->getEvenement()->getId());

        $query = $em->createQuery(
            'UPDATE ProjetBundle:Evenement e 
            
            SET e.nbrParticipants = :nbrParticipants
            where e.nomE= :event
            '
        )->setParameter('nbrParticipants', $evenement->getNbrParticipants() - 1 )->
        setParameter('event',$evenement->getNomE())
            ->execute();
        $em ->remove($participant);
        $em->flush();
        return $this->redirectToRoute('evenement_AfficheP');
    }




    public function SupprimeEvenementAction($id)
    {
        $participant = new Participant();
        $evenement = new Evenement();
        $participant->getEvenement();
        $participant->setEvenement(NULL);
        $evenement=NULL;

            $em = $this->getDoctrine()->getManager();
            $evenement = $em->getRepository(Evenement::class)->find($id);
            $em->remove($evenement);
            $em->flush();
            return $this->redirectToRoute('evenement_AfficherE');

    }



    public function ModifieParticipantAction(Request $request,$id)
    {

        $em=$this->getDoctrine()->getManager();
        $participant= $em->getRepository('ProjetBundle:Participant')->find($id);
        $form= $this->createForm(ParticipantType::class,$participant);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid())
        {
            $em = $this->getDoctrine()->getManager();
            $em->persist($participant);
            $em->flush();
            $this->addFlash('info', 'Created Successfully !');
           // return $this->redirectToRoute('evenement_AfficheP');


        }
        $participant ->setNomP($request->get('nomP'));
        $participant ->setPrenom($request->get('prenom'));
        $participant ->setEmail($request->get('Email'));
        $em->persist($participant);
        $em->flush();

        $serializer = new Serializer([new ObjectNormalizer()]);
        $formatted = $serializer->normalize($participant);
        return new JsonResponse($formatted,200);


        //return $this->render('@Evenement/Participant/ModifieP.html.twig',array('Form'=> $form->createView()));

    }




    public function ModifieEvenementAction(Request $request,$id)
    {
        $evenement = new Evenement();
        $em=$this->getDoctrine()->getManager();
        $evenement= $em->getRepository('ProjetBundle:Evenement')->find($id);
        $form= $this->createForm(EvenementType::class,$evenement);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid())
        {
            $em = $this->getDoctrine()->getManager();
            $em->persist($evenement);
            $em->flush();
            $this->addFlash('info', 'Created Successfully !');
            return $this->redirectToRoute('evenement_AfficherE');
        }


        return $this->render('@Projet/Evenement/ModifierE.html.twig',array('Form'=> $form->createView()));

    }




    public function RechercheEvenementAction(Request $request)
    {
        $em= $this->getDoctrine()->getManager();
        $evenement = $em->getRepository(Evenement::class)->findAll();

        if($request->isMethod("POST"))
        {
           $nom = $request->get('nom');
           $evenement = $em->getRepository("ProjetBundle:Evenement")->findBy(array('nomE'=>$nom));
        }
        return $this->render("@Projet/Evenement/RechercheE.html.twig",array('evenement'=>$evenement));
    }

    public function RechercheParticipantAction(Request $request)
    {
        $em= $this->getDoctrine()->getManager();
        $participant = $em->getRepository(Participant::class)->findAll();
        if($request->isMethod("POST"))
        {
            $nom = $request->get('nom');
            $participant = $em->getRepository("ProjetBundle:Participant")->findBy(array('nomP'=>$nom));
        }
        return $this->render("@Projet/Participant/RechercheP.html.twig",array('participant'=>$participant));
    }





    public function  MailAction(Request $request)
    {
        {
            $form = $this->createFormBuilder()
                ->add('Email', EmailType::class)
                ->add('Message',TextareaType::class)
                ->add('Envoyer', SubmitType::class)
                ->getForm();
            $form->handleRequest($request);
            if($form->isSubmitted()&& $form->isValid())
            {
                $data = $form->getData();
                dump($data);
                $message = \Swift_Message::newInstance()
                    ->setSubject('Evenement')
                    ->setFrom('mondhermallek97@gmail.com')
                    ->setTo($data['Email'])
                    ->setBody(
                        $form->getData()['Message'],
                        'text/Plain'
                    )
                ;
                $this->get('mailer')->send($message);
            }
            $em = $this->getDoctrine()->getManager();
            $dql = "SELECT pr FROM ProjetBundle:Participant pr ";

            $query = $em->createQuery($dql);
            /**
             * @var $paginator \Knp\Component\Pager\Paginator
             */
            $paginator = $this->get('knp_paginator');
            dump(get_class($paginator));
            $result = $paginator->paginate(
                $query,
                $request->query->getInt('page', 1),
                $request->query->getInt('limit', 5)
            );

            return $this->render("@Projet/Evenement/Mail.html.twig", array('form'=>$form->createView(),'our_form'=>$form,'participant' => $result));

        }
    }


  /*  public function allAction()
    {
        $event = $this->getDoctrine()->getManager()->getRepository('EvenementBundle:Evenement')->findAll();
        $serializer = new Serializer([new ObjectNormalizer()]);
        $formatted = $serializer->normalize($event);
        return new JsonResponse($formatted);
    }*/

    /*public function alllAction()
    {
        $em= $this->getDoctrine()->getManager();
        $evenement =$em->getRepository('EvenementBundle:Evenement')->findAll();

        $normalizer = array(new ObjectNormalizer());
        $normalizer[0]->setCircularReferenceLimit(2);

        $normalizer[0]->setCircularReferenceHandler(function ($object){
            return $object->getId();
        });

        $serializer = new Serializer($normalizer,array(new JsonEncoder()));
        $formatted = $serializer->serialize($evenement,'json');

        return new Response($formatted);

    }*/


    public function newAction(Request $request,$id)
    {
        $em = $this->getDoctrine()->getManager();
        $event = $em->getRepository('ProjetBundle:Evenement')->find($id);
        $em = $this->getDoctrine()->getManager();
        $participant = new Participant();
        $participant ->setNomP($request->get('nomP'));
        $participant ->setPrenom($request->get('prenom'));
        $participant ->setEmail($request->get('Email'));
        $participant->setEvenement($event);


        $em->persist($participant);
        $em->flush();


        $normalizer = new ObjectNormalizer();
        $normalizer->setCircularReferenceLimit(2);
        $normalizer->setCircularReferenceHandler(function ($object) {
            return $object->getId();
        });
        $serializer = new Serializer([$normalizer]);
        $formatted = $serializer->normalize($event);
        return new JsonResponse($formatted);

        //$serializer = new Serializer([new ObjectNormalizer()]);
        //$formatted = $serializer->normalize($participant);
        //return new JsonResponse($formatted);

    }

    public function deleteAction(Request $request)
    { $em = $this->getDoctrine()->getManager();
        $idReviewFromUrl=$request->get('id');
        $review=$em->getRepository('ProjetBundle:Participant')->find($idReviewFromUrl);
        $em->remove($review);
        $em->flush();
        return new JsonResponse('works', 200);
    }



    public function editAction(Request $request)
    {    $em = $this->getDoctrine()->getManager();
        $idReviewFromUrl=$request->get('id');
        $task=$em->getRepository('ProjetBundle:Participant')->find($idReviewFromUrl);
        $task = new Participant();
        $task ->setId($request->get('id'));

        $task ->setNomP($request->get('nomP'));
        $task ->setPrenom($request->get('prenom'));
        $task ->setEmail($request->get('Email'));

        $em->flush();

        $serializer = new Serializer([new ObjectNormalizer()]);
        $formatted = $serializer->normalize($task);
        return new JsonResponse($formatted,200);
    }

  /*  public function allAction(){

        $em=$this->getDoctrine()->getManager();
        $subscription=$em->getRepository('EvenementBundle:Evenement')->findAll();
        $medial=array();

        foreach ( $subscription as $medias)
        {   $a=array(
            "id" => $medias->getId(),
            "nomE" => $medias->getNomE(),
            "type" => $medias->getType(),
            "dateDebut" => $medias->getDateDebut(),
            "dateFin" => $medias->getDateFin(),
            "description" => $medias->getDescription(),
            "nbrParticipants" => $medias->getNbrParticipants(),
            "nbrPlaces" =>$medias->getNbrPlaces(),


        );
            array_push($medial,$a);
        }
        $serializer = new Serializer([new ObjectNormalizer()]);
        $formatted = $serializer->normalize($subscription);
        return new JsonResponse($formatted);
    }*/


    public function allAction()
    {
        $em= $this->getDoctrine()->getManager();
        $profilmedicale =$em->getRepository("ProjetBundle:Evenement")->findAll();


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
