<?php

namespace ProjetBundle\Form;

use ProjetBundle\Entity\EmploiDuTemps;
use Symfony\Bridge\Doctrine\Form\Type\EntityType;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;
use Symfony\Component\Form\Extension\Core\Type\DateTimeType;
use Symfony\Component\Form\Extension\Core\Type\ChoiceType;


class EnfantType extends AbstractType
{
    /**
     * {@inheritdoc}
     */


    public function buildForm(FormBuilderInterface $builder, array $options)
    {
        $builder->add('nom')
                ->add('prenom')
                ->add('DateNaissance')

            ->add('EmploiDuTemps', EntityType::class,[
                'class' => EmploiDuTemps::class,
                'choice_label' => 'Classe'])

                ->add('Valider', SubmitType::class);
    }

    /**
     * {@inheritdoc}
     */
    public function configureOptions(OptionsResolver $resolver)
    {
        $resolver->setDefaults(array(
            'data_class' => 'ProjetBundle\Entity\Enfant'
        ));
    }

    /**
     * {@inheritdoc}
     */
    public function getBlockPrefix()
    {
        return 'projetbundle_enfant';
    }
}
