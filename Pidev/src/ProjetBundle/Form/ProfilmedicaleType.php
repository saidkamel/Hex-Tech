<?php

namespace ProjetBundle\Form;

use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\Extension\Core\Type\ChoiceType;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;
use Symfony\Bridge\Doctrine\Form\Type\EntityType;
use Doctrine\ORM\EntityRepository;


class ProfilmedicaleType extends AbstractType
{
    /**
     * {@inheritdoc}
     */
    public function buildForm(FormBuilderInterface $builder, array $options)
    {

        $builder->add('enfant',EntityType::class,array(
            'class'=>'ProjetBundle:Enfant',


            'query_builder'=> function(EntityRepository $arg){
                return $arg->createQueryBuilder('e')
                    ->where('e.profilmedicale is null');
            }
        ,
            'choice_label'=>'id',

            'multiple'=>false
        ))

            ->add('taille')
            ->add('poids')
            ->add('maladie')
            ->add('etat', ChoiceType::class,[
                'choices'=>[
                    "Bonne santé"=>"Bonne santé",
                    "Malade"=>"Malade",
                ],
                ])
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
            ->add('Submit',SubmitType::class);





    }

    /**
     * {@inheritdoc}
     */
    public function configureOptions(OptionsResolver $resolver)
    {
        $resolver->setDefaults(array(
            'data_class' => 'ProjetBundle\Entity\Profilmedicale'
        ));
    }

    /**
     * {@inheritdoc}
     */
    public function getBlockPrefix()
    {
        return 'projetbundle_profilmedicale';
    }


}
