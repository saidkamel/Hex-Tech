<?php

namespace ProjetBundle\Controller;

use ProjetBundle\Entity\Evenement;
use ProjetBundle\Entity\Participant;
use ProjetBundle\Form\EvenementType;
use ProjetBundle\Form\ParticipantType;
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

class ParticipantController extends Controller
{
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
     return $this->render("@Projet/Evenement/fAfficheE.html.twig",array('evenement'=>$result));
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
        return $this->render("@Projet/Evenement/fRechercheE.html.twig",array('evenement'=>$evenement));
    }

    public function AjoutParticipantAction(Request $request,$id)
    {
        $em= $this->getDoctrine()->getManager();
        $evenement = $em->getRepository(Evenement::class)->find($id);
        $max= $evenement->getNbrPlaces();

      //  $query->setParameter('nomE',$evenement->getNomE());



        $participant = new Participant();
        $participant->setEvenement($evenement);
        //$evenement = new Evenement();
        $form = $this->createForm(ParticipantType::class,$participant);
        $form->handleRequest($request);

        if($form->isSubmitted()  )
        {

               // $evenement->setNbrParticipants($request->get('nbrParticipants')+1);
            $query = $em->createQuery(
                'UPDATE EvenementBundle:Evenement e 
            
            SET e.nbrParticipants = :nbrParticipants
            where e.nomE= :event
            '
            )->setParameter('nbrParticipants', $evenement->getNbrParticipants() + 1 )->
            setParameter('event',$evenement->getNomE())
                ->execute();
            $em =$this->getDoctrine()->getManager();
            $em->persist($participant);
            $em->flush();
            #$this ->redirectToRoute('participant_AjoutP');


        }

        return $this->render("@Projet/Evenement/fAjoutP.html.twig",array('form'=>$form->createView(),'evenement'=>$evenement));
    }



    public function formAction(Request $request)
    {
        $form = $this->createFormBuilder()
        ->add('nomP')
            ->add('Email',EmailType::class)
        ->add('prenom')
        ->add('Confirmer',SubmitType::class)
        ->getForm()
        ;
        $form->handleRequest($request);
        if($form->isSubmitted()&& $form->isValid())
        {
            $data = $form->getData();
            dump($data);
            $message = \Swift_Message::newInstance()
                ->setSubject('Evenement')
                ->setFrom($data['Email'])
                ->setTo('mondhermallek97@gmail.com')
                ->setBody($form->getData()['nomP'],
                    'text/Plain'
                )
            ;
            $this->get('mailer')->send($message);
        }

        return $this->render("@Projet/Evenement/form.html.twig",array('form'=>$form->createView(),'our_form'=>$form));


    }
    public function  MailAction(Request $request)
    {
        {
            $form = $this->createFormBuilder()
                ->add('nomP')
                ->add('prenom')
                ->add('Email', EmailType::class)
                ->add('Message', TextareaType::class)
                ->add('Envoyer', SubmitType::class)
                ->getForm();
            $form->handleRequest($request);
            if ($form->isSubmitted() && $form->isValid()) {
                $data = $form->getData();
                dump($data);
                $message = \Swift_Message::newInstance()
                    ->setSubject('Evenement')
                    ->setFrom($data['Email'])
                    ->setTo('mondhermallek97@gmail.com')
                    ->setBody(
                        $form->getData()['Message'],
                        'text/Plain'
                    );
                $this->get('mailer')->send($message);
            }
            $em = $this->getDoctrine()->getManager();
            #$evenement = $em ->getRepository("EvenementBundle:Evenement")->findAll();
            $dql = "SELECT ev FROM ProjetBundle:Evenement ev";
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

            return $this->render("@Projet/Participant/Mail.html.twig", array('form' => $form->createView(), 'our_form' => $form, 'evenement' => $result));


        }
    }
   /* public function allAction()
    {
        $participant = $this->getDoctrine()->getManager()->getRepository('EvenementBundle:Participant')->findAll();
        $serializer = new Serializer([new ObjectNormalizer()]);
        $formatted = $serializer->normalize($participant);
        return new JsonResponse($formatted);
    }*/
    /*public function allAction(){


        $em=$this->getDoctrine()->getManager();
        $part=$em->getRepository('EvenementBundle:Participant')->findAll();
        $medial=array();

        foreach ( $part as $medias)
        {   $a=array(
            "id" => $medias->getId(),
            "nomP" => $medias->getNomP(),
            "prenom" => $medias->getPrenom(),
            "Email" => $medias->getEmail(),
            "evenement"=>$medias->getEvenement(),

        );
            array_push($medial,$a);
        }
        $serializer = new Serializer([new ObjectNormalizer()]);
        $formatted = $serializer->normalize($part);
        return new JsonResponse($formatted);
    }*/
    public function allAction()
    {
        $em= $this->getDoctrine()->getManager();
        $profilmedicale =$em->getRepository("ProjetBundle:Participant")->findAll();


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
