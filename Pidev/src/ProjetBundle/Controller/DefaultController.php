<?php

namespace ProjetBundle\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\Controller;

class DefaultController extends Controller
{
    public function indexAction()

        {
            // replace this example code with whatever you need
            return $this->render('default/index.html.twig', [
                'base_dir' => realpath($this->getParameter('kernel.project_dir')).DIRECTORY_SEPARATOR,
            ]);
    }
    public function redirectAction(){
        $authChecker = $this->container->get('security.authorization_checker');
        if($authChecker->isGranted('ROLE_ADMIN')){
            return $this->redirecttoRoute("projet_homepage");
        } elseif ($authChecker->isGranted('ROLE_PARENT')){
            return $this->redirecttoRoute("show_front");

        }
        elseif ($authChecker->isGranted('ROLE_USER')){
            return $this->redirecttoRoute("Show_Profil");

        }
        else {
            return $this->redirecttoRoute("fos_user_security_login");
        }


    }
}
