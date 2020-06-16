<?php

namespace ProjetBundle\Form;

use Doctrine\DBAL\Types\Type;
use Symfony\Bridge\Doctrine\Form\Type\EntityType;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\Extension\Core\Type\ChoiceType;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;
use Symfony\Component\Form\Extension\Core\Type\TextareaType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;

class EvenementType extends AbstractType
{
    /**
     * {@inheritdoc}
     */
    public function buildForm(FormBuilderInterface $builder, array $options)
    {
        $builder
            ->add('nomE')
            ->add('type',ChoiceType::class,array(
                'choices'=>array(
                'Sport'=>'Sport',
                'Festival'=>'Festival',
                    'Culturel'=>'Culturel',
                    'Loisir'=>'Loisir'),
                'placeholder'=>true,
                'required'=>true,
            ))
            ->add('dateDebut')
            ->add('dateFin')
            ->add('description',TextareaType::class)
            ->add('nbrPlaces')
            ->add('Ajouter Evenement',SubmitType::class);
    }/**
     * {@inheritdoc}
     */
    public function configureOptions(OptionsResolver $resolver)
    {
        $resolver->setDefaults(array(
            'data_class' => 'ProjetBundle\Entity\Evenement'
        ));
    }

    /**
     * {@inheritdoc}
     */
    public function getBlockPrefix()
    {
        return 'projetbundle_evenement';
    }


}
