<?php

namespace ProjetBundle\Form;

use Symfony\Component\Form\Extension\Core\Type\FileType;
use \Symfony\Component\Form\Extension\Core\Type\TextType;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;

class JardinType extends AbstractType
{
    /**
     * {@inheritdoc}
     */
    public function buildForm(FormBuilderInterface $builder, array $options)
    {
        $builder
            ->add('nom',TextType::class,['attr'=>['class'=>'form-control ']])
            ->add('capacite',TextType::class,['attr'=>['class'=>'form-control ']])
            ->add('localisation',TextType::class,['attr'=>['class'=>'form-control ']])
            ->add('num',TextType::class,['attr'=>['class'=>'form-control ']])
            ->add('mail',TextType::class,['attr'=>['class'=>'form-control ']])
            ->add('activite',TextType::class,['attr'=>['class'=>'form-control ']])
            ->add('activite',TextType::class,['attr'=>['class'=>'form-control ']])
            ->add('image',FileType::class,[
            'required' => false,]
    );
    }/**
     * {@inheritdoc}
     */
    public function configureOptions(OptionsResolver $resolver)
    {
        $resolver->setDefaults(array(
            'data_class' => 'ProjetBundle\Entity\Jardin'
        ));
    }

    /**
     * {@inheritdoc}
     */
    public function getBlockPrefix()
    {
        return 'projetbundle_jardin';
    }


}
