<?php


namespace ProjetBundle\Controller;




use ProjetBundle\Entity\Media;
use Symfony\Bridge\Doctrine\Form\Type\EntityType;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\HttpFoundation\JsonResponse;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\Form\Extension\Core\Type\FileType;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;
use Symfony\Component\Form\Extension\Core\Type\TextType;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Serializer\Normalizer\ObjectNormalizer;
use Symfony\Component\Serializer\Serializer;


class MediaController extends Controller
{
    public function addAction(Request $request)
    {
        $media=new Media();
        $form=$this->createFormBuilder($media)
            ->add('club',EntityType::class,array(
                'class'=>'ProjetBundle:Club',
                'choice_label'=>'nom',
                'multiple'=>false,

            ))
            ->add('img', FileType::class, array('data_class'=>null, 'required'=>false))
            ->add('img1', FileType::class, array('data_class'=>null, 'required'=>false))
            ->add('img2', FileType::class, array('data_class'=>null, 'required'=>false))
            ->add('img3', FileType::class, array('data_class'=>null, 'required'=>false))
            ->add('Submit', SubmitType::class)
            ->getForm();
        $form->handleRequest($request);
        if($form->isSubmitted() && $form->isValid())
        {
            $file = $media->getImg();
            $fileName = md5(uniqid()).'.'.$file->guessExtension();
            $file->move($this->getParameter('photos_directory'), $fileName);
            $media->setImg($fileName);

            $file = $media->getImg1();
            $fileName = md5(uniqid()).'.'.$file->guessExtension();
            $file->move($this->getParameter('photos_directory'), $fileName);
            $media->setImg1($fileName);

            $file = $media->getImg2();
            $fileName = md5(uniqid()).'.'.$file->guessExtension();
            $file->move($this->getParameter('photos_directory'), $fileName);
            $media->setImg2($fileName);

            $file = $media->getImg3();
            $fileName = md5(uniqid()).'.'.$file->guessExtension();
            $file->move($this->getParameter('photos_directory'), $fileName);
            $media->setImg3($fileName);

            $em = $this->getDoctrine()->getManager();
            $em->persist($media);
            $em->flush();

            return $this->redirect($this->generateUrl('club_search'));
        }
        return($this->render("@Projet/Media/ajout_media.html.twig",['f'=>$form->createView()]));


    }
    public function supprimerAction($id){
        $em=$this->getDoctrine()->getManager();
        $media=$em->getRepository("ProjetBundle:Media")->find($id);
        $em->remove($media);
        $em->flush();
        return $this->redirectToRoute('media_aff');
    }


    public function modifierAction(Request $request,$id){
        $em=$this->getDoctrine()->getManager();
        $media=$em->getRepository("ProjetBundle:Media")->find($id);
        $form=$this->createFormBuilder($media)
            ->add('club',EntityType::class,array(
                'class'=>'ProjetBundle:Club',
                'choice_label'=>'nom',
                'multiple'=>false,

            ))
            ->add('img', FileType::class, array('data_class'=>null, 'required'=>true))
            ->add('img1', FileType::class, array('data_class'=>null, 'required'=>false))
            ->add('img2', FileType::class, array('data_class'=>null, 'required'=>false))
            ->add('img3', FileType::class, array('data_class'=>null, 'required'=>false))
            ->add('Submit', SubmitType::class)
            ->getForm();
        $form->handleRequest($request);
        if($form->isSubmitted() && $form->isValid()) {
            $file = $media->getImg();
            $fileName = md5(uniqid()) . '.' . $file->guessExtension();
            $file->move($this->getParameter('photos_directory'), $fileName);
            $media->setImg($fileName);

            $file = $media->getImg1();
            $fileName = md5(uniqid()) . '.' . $file->guessExtension();
            $file->move($this->getParameter('photos_directory'), $fileName);
            $media->setImg1($fileName);

            $file = $media->getImg2();
            $fileName = md5(uniqid()) . '.' . $file->guessExtension();
            $file->move($this->getParameter('photos_directory'), $fileName);
            $media->setImg2($fileName);

            $file = $media->getImg3();
            $fileName = md5(uniqid()) . '.' . $file->guessExtension();
            $file->move($this->getParameter('photos_directory'), $fileName);
            $media->setImg3($fileName);

            $em = $this->getDoctrine()->getManager();
            $em->persist($media);
            $em->flush();

            return $this->redirect($this->generateUrl('club_search'));
        }

        return $this->render("@Projet/Media/update_media.html.twig",array(
            'form'=>$form->createView()
        ) );



    }

    public function searchAction(Request $request){
        $em = $this->getDoctrine()->getManager();
        $M=$em->getRepository("ProjetBundle:Media")->findAll();
        if($request->isMethod('Post')){
            $nom=$request->get('nom');
            $M=$em->getRepository("ProjetBundle:Media")->findBy(array("nom"=>$nom));
        }
        return $this->render("@Projet/Media/affiche_media.html.twig",array(
            'clubs'=>$M));

    }
    public function trouverIdAction($id){
        $repository=$this->getDoctrine()->getManager()->getRepository(Media::class);
        $clubs = $repository->myfindid($id);
        return $this->render('@Projet/Media/affiche_media.html.twig',array(
            'clubs'=>$clubs));

    }








    public function afficheAction()
    {
        $em=$this->getDoctrine()->getManager();
        $M=$em->getRepository("ClubBundle:Media")->findAll();
        return $this->render('@Club/Media/affiche_media.html.twig',array(
            'M'=>$M));
    }





    public function mobileAfficherAllMediaAction(){
        $em=$this->getDoctrine()->getManager();
        $media=$em->getRepository('ClubBundle:Media')->findAll();
        $response = new Response();
        $medial=array();

        $response->headers->set("Content-Type","application/json");
        foreach ( $media as $medias)
        {   $a=array(
            "id" => $medias->getId(),
            "Club" => $medias->getClub()->getNom(),
            "img" => $medias->getImg(),
            "img1" => $medias->getImg1(),
            "img2" => $medias->getImg2(),
            "img3" => $medias->getImg3(),

        );
            array_push($medial,$a);
        }
        $response->setContent(json_encode( array(
            "media" => $medial)));
        return $response;
    }
    public function mobileAfficherOneMediaAction($id){
        $em=$this->getDoctrine()->getManager();
        $media=$em->getRepository('ClubBundle:Media')->find($id);
        $medial=array();
        foreach ( $media as $medias)
        {   $a=array(
            "id" => $medias->getId(),
            "Club" => $medias->getClub()->getNom(),
            "img" => $medias->getImg(),
            "img1" => $medias->getImg1(),
            "img2" => $medias->getImg2(),
            "img3" => $medias->getImg3(),

        );
            array_push($medial,$a);
        }
        $em->persist($media);
        $em->flush();
        $serializer = new Serializer([new ObjectNormalizer()]);
        $formatted = $serializer->normalize($media);
        return new JsonResponse($formatted);

    }










}