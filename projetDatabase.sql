-- phpMyAdmin SQL Dump
-- version 4.9.2
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le :  jeu. 18 juin 2020 à 11:52
-- Version du serveur :  10.4.10-MariaDB
-- Version de PHP :  7.3.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `projet`
--

-- --------------------------------------------------------

--
-- Structure de la table `activite`
--

DROP TABLE IF EXISTS `activite`;
CREATE TABLE IF NOT EXISTS `activite` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `NomActivite` varchar(100) NOT NULL,
  `Description` text NOT NULL,
  `Type` varchar(100) NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=MyISAM AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `activite`
--

INSERT INTO `activite` (`Id`, `NomActivite`, `Description`, `Type`) VALUES
(17, 'aze', 'sqd', 'Sport'),
(15, 'activite1', 'balbal', 'Dessin'),
(16, 'activite2', 'hahah', 'Sport'),
(20, 'ssssssssss', 'az', 'Dessin');

-- --------------------------------------------------------

--
-- Structure de la table `classe`
--

DROP TABLE IF EXISTS `classe`;
CREATE TABLE IF NOT EXISTS `classe` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `NomClasse` varchar(100) NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=MyISAM AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `classe`
--

INSERT INTO `classe` (`Id`, `NomClasse`) VALUES
(16, '3A10'),
(1, 'vide'),
(24, '3A20');

-- --------------------------------------------------------

--
-- Structure de la table `classeactivite`
--

DROP TABLE IF EXISTS `classeactivite`;
CREATE TABLE IF NOT EXISTS `classeactivite` (
  `IdClasse` int(11) NOT NULL,
  `IdActivite` int(11) NOT NULL,
  PRIMARY KEY (`IdClasse`,`IdActivite`),
  KEY `FK_ac` (`IdActivite`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `classeactivite`
--

INSERT INTO `classeactivite` (`IdClasse`, `IdActivite`) VALUES
(16, 15),
(16, 16),
(16, 17),
(16, 20);

-- --------------------------------------------------------

--
-- Structure de la table `classejoinactivite`
--

DROP TABLE IF EXISTS `classejoinactivite`;
CREATE TABLE IF NOT EXISTS `classejoinactivite` (
  `classe_id` int(11) NOT NULL,
  `activite_id` int(11) NOT NULL,
  PRIMARY KEY (`classe_id`,`activite_id`),
  KEY `IDX_503DAEC98F5EA509` (`classe_id`),
  KEY `IDX_503DAEC99B0F88B1` (`activite_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `classejoinactivite`
--

INSERT INTO `classejoinactivite` (`classe_id`, `activite_id`) VALUES
(16, 15),
(16, 16),
(16, 17),
(16, 20),
(24, 15),
(24, 17);

-- --------------------------------------------------------

--
-- Structure de la table `club`
--

DROP TABLE IF EXISTS `club`;
CREATE TABLE IF NOT EXISTS `club` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `description` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UNIQ_B8EE38726C6E55B5` (`nom`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `club`
--

INSERT INTO `club` (`id`, `nom`, `description`) VALUES
(2, 'Musique', 'Ceci est un club de musique'),
(16, 'Enactus', 'ceci est un club de dessd'),
(17, 'controle saisie', 'this is atleast 20 charactersssssssssssssssssssssssssssss'),
(18, 'sssssss', 'aaaaaaaaaaaaaaaaaaaaaaaaa');

-- --------------------------------------------------------

--
-- Structure de la table `comment`
--

DROP TABLE IF EXISTS `comment`;
CREATE TABLE IF NOT EXISTS `comment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `thread_id` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `body` longtext COLLATE utf8_unicode_ci NOT NULL,
  `ancestors` varchar(1024) COLLATE utf8_unicode_ci NOT NULL,
  `depth` int(11) NOT NULL,
  `created_at` datetime NOT NULL,
  `state` int(11) NOT NULL,
  `author_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `IDX_9474526CE2904019` (`thread_id`),
  KEY `IDX_9474526CF675F31B` (`author_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `comment`
--

INSERT INTO `comment` (`id`, `thread_id`, `body`, `ancestors`, `depth`, `created_at`, `state`, `author_id`) VALUES
(1, 'foo', 'mekki', '', 0, '2020-05-25 16:40:17', 0, NULL),
(2, 'foo', 'Le club de dessin est magnifique', '', 0, '2020-05-25 16:41:53', 0, NULL),
(3, 'foo', 'Merci de commenter uniquement sur le sujet', '1', 1, '2020-05-25 16:43:43', 0, NULL),
(4, 'foo', 'Musique', '', 0, '2020-05-25 17:10:13', 0, 2),
(5, 'foo', 'great', '4', 1, '2020-05-25 17:21:48', 0, 2);

-- --------------------------------------------------------

--
-- Structure de la table `commentaire`
--

DROP TABLE IF EXISTS `commentaire`;
CREATE TABLE IF NOT EXISTS `commentaire` (
  `nom` varchar(20) NOT NULL,
  `prenom` varchar(20) NOT NULL,
  `num` int(11) NOT NULL,
  `sujet` varchar(20) NOT NULL,
  `comm` varchar(255) NOT NULL,
  `id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `commentaire`
--

INSERT INTO `commentaire` (`nom`, `prenom`, `num`, `sujet`, `comm`, `id`) VALUES
('jiji', 'baccouuche', 95959595, 'bruit', 'bad', 1),
('jiji', 'baccouuche', 95959595, 'bruit', 'bad', 2),
('jiji', 'baccouuche', 95959595, 'bruit', 'bad', 3),
('jiji', 'baccouuche', 95959595, 'bruit', 'bad', 4),
('jiji', 'baccouuche', 95959595, 'bruit', 'bad', 5),
('jiji', 'baccouuche', 95959595, 'bruit', 'bad', 6),
('jiji', 'baccouuche', 95959595, 'bruit', 'bad', 7),
('jiji', 'baccouuche', 95959595, 'bruit', 'bad', 8),
('noussa', 'baccouuche', 959595955, 'music', 'good', 9),
('said', '', 0, '', 'hello', 10),
('said', '', 0, '', 'azerqsfdqdf', 11),
('jihed', '', 0, '', 'reclamatiion', 12),
('jihed', '', 0, '', 'reclamatiion', 13);

-- --------------------------------------------------------

--
-- Structure de la table `emploi_du_temps`
--

DROP TABLE IF EXISTS `emploi_du_temps`;
CREATE TABLE IF NOT EXISTS `emploi_du_temps` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `img` varchar(500) COLLATE utf8_unicode_ci NOT NULL,
  `Classe` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `emploi_du_temps`
--

INSERT INTO `emploi_du_temps` (`id`, `img`, `Classe`) VALUES
(7, '73e3c0e9b63d9178ee10452b78f15fe6.png', '3A10'),
(13, '6ef89e7ae36ceea663371c177fc56f8f.png', '3A12'),
(14, 'eaf7af5ee1d03445f51609f340cc9df3.jpeg', '3A20');

-- --------------------------------------------------------

--
-- Structure de la table `employe`
--

DROP TABLE IF EXISTS `employe`;
CREATE TABLE IF NOT EXISTS `employe` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(20) NOT NULL,
  `prenom` varchar(20) NOT NULL,
  `cin` int(8) NOT NULL,
  `titre` varchar(20) NOT NULL,
  `salaire` int(11) NOT NULL,
  `email` varchar(50) NOT NULL,
  `absence` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=40 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `employe`
--

INSERT INTO `employe` (`id`, `nom`, `prenom`, `cin`, `titre`, `salaire`, `email`, `absence`) VALUES
(3, 'seid', 'jeff', 0, 'directeur', 0, '', 0),
(2, 'kamel', 'ala', 0, 'serveur', 2300, '', 0),
(4, 'Exposito', 'Ester', 0, 'mainquin', 350, 'hello', 1),
(5, 'Ronaldo', 'Cristiano', 12345678, 'boss', 999999999, 'ronaldo@potogaze.madera', 0),
(9, 'kamel', 'said', 0, 'interupteur', 5123, 'said@esprit.tn', 4),
(23, 'boyka', 'yuri', 7486499, 'fighter', 150, 'ala.kamel41@gmail.com', 10),
(31, 'said', 'kamel', 12345678, 'aaaaa', 444444, 'said.kamel@esprit.tn', 1),
(32, 'saida', 'kamelll', 87654321, 'titrebigboi', 1234, 'said.kamel@esprit.tn', 1),
(33, 'saida', 'kamelll', 87654321, 'titrebigboi', 1234, 'said.kamel@esprit.tn', 1),
(34, 'ak', 'sept', 12345678, 'directeur', 9999, 'aksept@esprit.tn', 1),
(35, 'test', 'test', 98765432, 'test', 500, 'ala.kamel41@gmail.com', 0),
(36, 'test', 'test', 98765432, 'test', 500, 'ala.kamel41@gmail.com', 0),
(37, 'test', 'test', 98765432, 'test', 500, 'ala.kamel41@gmail.com', 0),
(38, 'test', 'test', 98765432, 'test', 500, 'ala.kamel41@gmail.com', 0),
(39, 'testt', 'testt', 12345678, 'testt', 600, 'ala.kamel41@gmail.com', 0);

-- --------------------------------------------------------

--
-- Structure de la table `enfant`
--

DROP TABLE IF EXISTS `enfant`;
CREATE TABLE IF NOT EXISTS `enfant` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(100) NOT NULL,
  `prenom` varchar(100) NOT NULL,
  `DateNaissance` date NOT NULL,
  `idParent` int(11) DEFAULT NULL,
  `idclasse` int(11) DEFAULT NULL,
  `Pmedicale_id` int(11) DEFAULT NULL,
  `EmploiDuTemps_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UNIQ_34B70CA295C7E888` (`Pmedicale_id`),
  KEY `IDX_34B70CA281608C57` (`EmploiDuTemps_id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `enfant`
--

INSERT INTO `enfant` (`id`, `nom`, `prenom`, `DateNaissance`, `idParent`, `idclasse`, `Pmedicale_id`, `EmploiDuTemps_id`) VALUES
(9, 'mobilemodif', 'mobileprenom', '2015-02-27', 169, 16, 15, 7),
(11, 'newkid', 'holo', '2010-02-05', 169, 1, 16, NULL),
(12, 'said3', 'kamel', '2020-02-14', 155, 16, 17, NULL),
(13, 'enfant', 'prenom', '2014-01-30', 170, 16, 18, NULL),
(14, 'saidssss', 'kamelss', '2020-01-27', 170, 16, 19, NULL),
(16, 'rachidtt', 'hamzaaa', '2020-02-04', 155, 16, 23, NULL),
(17, 'said', 'hamza', '2020-02-05', 155, 16, 21, NULL),
(21, 'said', 'kamel', '2020-06-19', 155, 16, NULL, NULL),
(22, 'zz', 'ss', '2020-06-09', 155, 1, NULL, NULL);

-- --------------------------------------------------------

--
-- Structure de la table `evenement`
--

DROP TABLE IF EXISTS `evenement`;
CREATE TABLE IF NOT EXISTS `evenement` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `Nom` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `Type` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `Date_Debut` date NOT NULL,
  `Date_Fin` date NOT NULL,
  `Description` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `NbrParticipants` int(11) DEFAULT NULL,
  `nbrPlaces` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UNIQ_B26681E54231355` (`Nom`),
  UNIQUE KEY `UNIQ_B26681EDC77C221` (`Date_Debut`),
  UNIQUE KEY `UNIQ_B26681EDD1991F2` (`Date_Fin`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `evenement`
--

INSERT INTO `evenement` (`id`, `Nom`, `Type`, `Date_Debut`, `Date_Fin`, `Description`, `NbrParticipants`, `nbrPlaces`) VALUES
(23, 'event1', 'Sport', '2015-01-01', '2017-01-01', 'description', 1, 20),
(25, 'event2', 'Loisir', '2016-01-03', '2017-01-20', 'description', 0, 30);

-- --------------------------------------------------------

--
-- Structure de la table `event`
--

DROP TABLE IF EXISTS `event`;
CREATE TABLE IF NOT EXISTS `event` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Nom` varchar(50) NOT NULL,
  `Type` varchar(50) NOT NULL,
  `Date_Debut` date NOT NULL,
  `Date_Fin` date NOT NULL,
  `Description` varchar(255) NOT NULL,
  `nbr_participant` int(11) NOT NULL,
  PRIMARY KEY (`Id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=75 DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `event`
--

INSERT INTO `event` (`Id`, `Nom`, `Type`, `Date_Debut`, `Date_Fin`, `Description`, `nbr_participant`) VALUES
(60, 'Evenement', 'losir', '2020-01-30', '2020-02-29', 'desc', 207),
(62, 'Evenement2', 'sport', '2020-02-21', '2020-02-22', 'desc', 2),
(63, 'sddf', 'Culturel', '2020-02-21', '2020-02-22', 'de', 3),
(65, 'Event', 'sport', '2020-01-28', '2020-03-07', 'desc', 1),
(66, 'Event3', 'Education', '2020-02-21', '2020-02-22', 'balblablabla', 0),
(67, 'Event8', 'Education', '2020-02-21', '2020-02-22', 'balblablabla', 0),
(68, 'Event7', 'Education', '2020-02-21', '2020-02-22', 'balblablabla', 0),
(70, ':kedfje', 'lizrh', '2020-01-28', '2020-02-28', 'de', 0),
(71, 'mon', 'Sport', '2020-01-27', '2020-02-29', 'd', 2),
(73, 'mondher', 'Culturel', '2020-01-27', '2020-02-28', 'd', 1),
(74, 'event888', 'Sport', '2020-06-02', '2020-06-12', '', 0);

-- --------------------------------------------------------

--
-- Structure de la table `forum`
--

DROP TABLE IF EXISTS `forum`;
CREATE TABLE IF NOT EXISTS `forum` (
  `nom` varchar(20) NOT NULL,
  `prenom` int(20) NOT NULL,
  `num` int(11) NOT NULL,
  `sujet` varchar(100) NOT NULL,
  `commentaire` varchar(255) NOT NULL,
  `id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `fos_user`
--

DROP TABLE IF EXISTS `fos_user`;
CREATE TABLE IF NOT EXISTS `fos_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(180) COLLATE utf8_unicode_ci NOT NULL,
  `username_canonical` varchar(180) COLLATE utf8_unicode_ci NOT NULL,
  `email` varchar(180) COLLATE utf8_unicode_ci NOT NULL,
  `email_canonical` varchar(180) COLLATE utf8_unicode_ci NOT NULL,
  `enabled` tinyint(1) NOT NULL,
  `salt` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `password` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `last_login` datetime DEFAULT NULL,
  `confirmation_token` varchar(180) COLLATE utf8_unicode_ci DEFAULT NULL,
  `password_requested_at` datetime DEFAULT NULL,
  `roles` longtext COLLATE utf8_unicode_ci NOT NULL COMMENT '(DC2Type:array)',
  `nom` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `prenom` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UNIQ_957A647992FC23A8` (`username_canonical`),
  UNIQUE KEY `UNIQ_957A6479A0D96FBF` (`email_canonical`),
  UNIQUE KEY `UNIQ_957A6479C05FB297` (`confirmation_token`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `fos_user`
--

INSERT INTO `fos_user` (`id`, `username`, `username_canonical`, `email`, `email_canonical`, `enabled`, `salt`, `password`, `last_login`, `confirmation_token`, `password_requested_at`, `roles`, `nom`, `prenom`) VALUES
(7, 'said', 'said', 'sai@esprit.tn', 'sai@esprit.tn', 1, NULL, '$2y$13$Eb81k6tgvVJv8dBAhGZkyuxWUg.q03apoad5TacJu2KDWv1NLEuda', '2020-06-18 11:29:55', NULL, NULL, 'a:1:{i:0;s:10:\"ROLE_ADMIN\";}', 'said', 'kamel'),
(8, 'kamel', 'kamel', 'said.kamel@esprit.tn', 'said.kamel@esprit.tn', 1, NULL, '$2y$13$h1epGIwl3D0TaVd74tqXRuctkghtjR7DWF3uk6d.OHHAwzdsoxgOu', '2020-06-17 20:35:09', NULL, NULL, 'a:1:{i:0;s:11:\"ROLE_PARENT\";}', 'said', 'kamel'),
(9, 'saidsaid', 'saidsaid', 'said@gmail.com', 'said@gmail.com', 1, NULL, '$2y$13$O2GWL6BtkoAm6aHw6dh6/.fnaRLJNhrBEuX1onNsgUugCz2cPwfse', '2020-05-29 14:04:53', NULL, NULL, 'a:1:{i:0;s:11:\"ROLE_PARENT\";}', 'said1', 'kamel');

-- --------------------------------------------------------

--
-- Structure de la table `inscription`
--

DROP TABLE IF EXISTS `inscription`;
CREATE TABLE IF NOT EXISTS `inscription` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nom_enfant` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `club` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Structure de la table `jardin`
--

DROP TABLE IF EXISTS `jardin`;
CREATE TABLE IF NOT EXISTS `jardin` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `capacite` int(11) NOT NULL,
  `localisation` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `num` int(11) NOT NULL,
  `mail` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `image` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `activite` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UNIQ_2C224438BFD3CE8F` (`localisation`),
  UNIQUE KEY `UNIQ_2C224438DC43AF6E` (`num`),
  UNIQUE KEY `UNIQ_2C2244385126AC48` (`mail`)
) ENGINE=InnoDB AUTO_INCREMENT=56 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `jardin`
--

INSERT INTO `jardin` (`id`, `nom`, `capacite`, `localisation`, `num`, `mail`, `image`, `activite`) VALUES
(9, 'jiji', 47, 'soukra', 55, 'feriel', 'fff', 'fjjf'),
(17, 'simpson', 80, 'tunis', 989898988, 'jihed@€sprit', '08ebd3d886dced19ea146f7caa66f8f8.jpeg', 'art'),
(36, 'jihh', 55, 'aaa', 6666, 'aaaa', '', 'aaa');

-- --------------------------------------------------------

--
-- Structure de la table `jardins`
--

DROP TABLE IF EXISTS `jardins`;
CREATE TABLE IF NOT EXISTS `jardins` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(20) NOT NULL,
  `capacite` int(11) NOT NULL,
  `num` int(11) NOT NULL,
  `mail` varchar(50) NOT NULL,
  `activite` varchar(20) NOT NULL,
  `localisation` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=81 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `jardins`
--

INSERT INTO `jardins` (`id`, `nom`, `capacite`, `num`, `mail`, `activite`, `localisation`) VALUES
(1, 'papillon', 50, 95128049, 'jihed.baccouche@esprit.tn', 'sport', 'esprit'),
(2, 'flower', 20, 55, 'jihed@esprit.tn', 'tennis', 'isc'),
(20, 'fleurs', 20, 55, 'jihed@esprit.tn', 'tennis', 'isc'),
(40, 'ballon', 50, 95128049, 'jihed.baccouche@esprit.tn', 'sport', 'esprit'),
(66, 'jihed', 5, 95030300, 'jihed@esprit.tn', 'aaa', 'aaa'),
(80, 'papillon1', 50, 95128049, 'jihed.baccouche@esprit.tn', 'sport', 'esprittt');

-- --------------------------------------------------------

--
-- Structure de la table `media`
--

DROP TABLE IF EXISTS `media`;
CREATE TABLE IF NOT EXISTS `media` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_club` int(11) DEFAULT NULL,
  `img` varchar(500) COLLATE utf8_unicode_ci NOT NULL,
  `img1` varchar(500) COLLATE utf8_unicode_ci NOT NULL,
  `img2` varchar(500) COLLATE utf8_unicode_ci NOT NULL,
  `img3` varchar(500) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`),
  KEY `IDX_6A2CA10C33CE2470` (`id_club`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `media`
--

INSERT INTO `media` (`id`, `id_club`, `img`, `img1`, `img2`, `img3`) VALUES
(2, 2, '7ca6db8ea99db9712e1280b6d8bd1cda.jpeg', 'ca6d77224f89b2a18aea455fede90e84.jpeg', '928c1a3d6c6c01fadde67c9d113e89e0.jpeg', 'f664cbd96e5e0d72821dff009524aabc.png'),
(4, 17, '82edb5c9c6aaa1c911344eae93cb5879.jpeg', '9eb2658cb8fd7e0e3b96a82a377fdfca.jpeg', '20ac474daf43c8117a4b38b2fa43e5e8.jpeg', 'ece58eae2f4a517fb41e45dc98125270.jpeg'),
(5, 18, 'd200f3b25144db6d3fb0245eac0d453e.jpeg', '96ba9cb4787e82c061b5ee7d4fdda59f.jpeg', 'b2a041f2304c6c0f8f15870e7ba4bc2d.jpeg', '08f309d8bac15fcada6b0e755c7b5848.jpeg');

-- --------------------------------------------------------

--
-- Structure de la table `parent`
--

DROP TABLE IF EXISTS `parent`;
CREATE TABLE IF NOT EXISTS `parent` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Nom` varchar(20) NOT NULL,
  `Prenom` varchar(20) NOT NULL,
  `Email` varchar(50) NOT NULL,
  `Phone` varchar(20) NOT NULL,
  `Cin` varchar(8) NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=175 DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `parent`
--

INSERT INTO `parent` (`Id`, `Nom`, `Prenom`, `Email`, `Phone`, `Cin`) VALUES
(155, 'mekki', 'ben moussa', 'mekki.benmoussa1@esprit.tn', '21658788196', ''),
(169, 'said', 'kamel', 'said.kamel@esprit.tn', '2654', '321456'),
(170, 'said', 'kamel', 'saiidkamel01@gmail.com', '2222', '325'),
(171, 'ss', 'aa', 'said', '2', '123'),
(172, 'said', 'kamel', 'said@esprit.tn', '21658627818', '12345678'),
(173, 'said', 'kamel', 'saiid@esprit.tn', '21658627818', '12345678'),
(174, 'mekki', 'benmoussa', 'mekkii.benmoussa@gmail.com', '21658788196', '14754523');

-- --------------------------------------------------------

--
-- Structure de la table `participant`
--

DROP TABLE IF EXISTS `participant`;
CREATE TABLE IF NOT EXISTS `participant` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `Nom` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `Prenom` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `Email` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `evenement_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `IDX_D79F6B11FD02F13` (`evenement_id`)
) ENGINE=InnoDB AUTO_INCREMENT=166 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `participant`
--

INSERT INTO `participant` (`id`, `Nom`, `Prenom`, `Email`, `evenement_id`) VALUES
(163, 'said', 'kamel', 'said.kamel@esprit.tn', 23),
(164, 'h', 'h', 'nom.prenom@esprit.tn', 23),
(165, 'ss', 'aaa', 'nom.prenom@esprit.tn', 23);

-- --------------------------------------------------------

--
-- Structure de la table `pediatre`
--

DROP TABLE IF EXISTS `pediatre`;
CREATE TABLE IF NOT EXISTS `pediatre` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `prenom` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `email` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `Telephone` int(11) NOT NULL,
  `adresse` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UNIQ_E708AB82E7927C74` (`email`),
  UNIQUE KEY `UNIQ_E708AB82C7FE72B3` (`Telephone`),
  UNIQUE KEY `UNIQ_E708AB82C35F0816` (`adresse`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `pediatre`
--

INSERT INTO `pediatre` (`id`, `nom`, `prenom`, `email`, `Telephone`, `adresse`) VALUES
(8, 'said', 'kamel', 'said.kamel@gmail.com', 58627818, 'Tunis rue blabla 45'),
(11, 'pediatre', 'prenom', 'said@ped.com', 28627818, 'adresse 2'),
(14, 'said', 'qqqqqqq', 'said.kamel@gmail2.com', 58627817, 'Tunis rue blabla 456'),
(16, 'george', 'kambel', 'geroge.kambel@gmail.com', 58627819, 'adresse 1'),
(17, 'qsdqs', 'azer', 'azeaze@gmail.com', 22307818, 'qsd'),
(18, 'kamel', 'said', 'said.kamel2@gmail.com', 58627813, 'adresse 12');

-- --------------------------------------------------------

--
-- Structure de la table `profilmedicale`
--

DROP TABLE IF EXISTS `profilmedicale`;
CREATE TABLE IF NOT EXISTS `profilmedicale` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `taille` int(11) NOT NULL,
  `poids` int(11) NOT NULL,
  `maladie` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `etat` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `enfant_id` int(11) DEFAULT NULL,
  `pediatre_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UNIQ_954F379B450D2529` (`enfant_id`),
  KEY `IDX_954F379BC7D8E729` (`pediatre_id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `profilmedicale`
--

INSERT INTO `profilmedicale` (`id`, `taille`, `poids`, `maladie`, `etat`, `enfant_id`, `pediatre_id`) VALUES
(15, 100, 50, NULL, 'Bonne santé', 9, 8),
(16, 100, 40, NULL, 'Bonne santé', 11, 8),
(17, 100, 30, NULL, 'Bonne santé', 12, 8),
(18, 100, 35, NULL, 'Bonne santé', 13, 8),
(19, 100, 25, NULL, 'Bonne santé', 14, 8),
(21, 100, 18, NULL, 'Bonne santé', 17, 8),
(23, 120, 50, NULL, 'Bonne santé', 16, 11);

-- --------------------------------------------------------

--
-- Structure de la table `reclamation`
--

DROP TABLE IF EXISTS `reclamation`;
CREATE TABLE IF NOT EXISTS `reclamation` (
  `cin` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(20) NOT NULL,
  `prenom` varchar(20) NOT NULL,
  `note` int(11) NOT NULL,
  `motif` varchar(20) NOT NULL,
  `description` varchar(200) NOT NULL,
  `date` date NOT NULL,
  PRIMARY KEY (`cin`)
) ENGINE=InnoDB AUTO_INCREMENT=967 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `reclamation`
--

INSERT INTO `reclamation` (`cin`, `nom`, `prenom`, `note`, `motif`, `description`, `date`) VALUES
(1, 'null', 'null', 5, 'jeu', 'barcha', '2020-02-11'),
(10, 'madi', 'madi', 5, 'jeu', 'barcha', '2020-02-11'),
(11, 'mad55i', 'madi', 5, 'jeu', 'barcha', '2020-02-12'),
(45, 'said', 'kamel', 666, 'jeuz', 'newdes**zears', '2020-02-25'),
(50, 'med', 'med', 55, 'bla', 'prof', '2020-02-11'),
(55, 'ines', 'boussabeh', 10, 'retard', '..', '2020-02-11'),
(66, 'jihed', 'baccouche', 2, 'violance', '..', '2020-02-11'),
(110, 'mad5i', 'madi', 5, 'jeu', 'barcha', '2020-02-12'),
(966, 'jihed', 'baccouche', 6, 'violance', '..', '2020-02-11');

-- --------------------------------------------------------

--
-- Structure de la table `subscription`
--

DROP TABLE IF EXISTS `subscription`;
CREATE TABLE IF NOT EXISTS `subscription` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nom_enfant` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `id_club` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `IDX_A3C664D333CE2470` (`id_club`)
) ENGINE=InnoDB AUTO_INCREMENT=96 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `subscription`
--

INSERT INTO `subscription` (`id`, `nom_enfant`, `id_club`) VALUES
(94, 'azzzzzz', 17);

-- --------------------------------------------------------

--
-- Structure de la table `thread`
--

DROP TABLE IF EXISTS `thread`;
CREATE TABLE IF NOT EXISTS `thread` (
  `id` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `permalink` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `is_commentable` tinyint(1) NOT NULL,
  `num_comments` int(11) NOT NULL,
  `last_comment_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `thread`
--

INSERT INTO `thread` (`id`, `permalink`, `is_commentable`, `num_comments`, `last_comment_at`) VALUES
('foo', 'http://127.0.0.1:8000/subscription', 1, 5, '2020-05-25 17:21:48');

-- --------------------------------------------------------

--
-- Structure de la table `user`
--

DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(100) NOT NULL,
  `password` varchar(20) NOT NULL,
  `role` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=818 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `user`
--

INSERT INTO `user` (`id`, `email`, `password`, `role`) VALUES
(1, 'admin', 'admin', 'admin'),
(2, 'said.kamel1@esprit.tn', 'azerty', 'parent'),
(803, 'said.kamel@esprit.tn', '12345678', 'employe'),
(806, 'aksept@esprit.tn', '12345678', 'employe'),
(807, 'said@esprit.tn', 'aze', 'parent'),
(808, 'said', '325', 'parent'),
(809, 'said', '123', 'parent'),
(810, 'said@esprit.tn', '12345678', 'parent'),
(811, 'saiid@esprit.tn', '12345678', 'parent'),
(816, 'ala.kamel41@gmail.com', '12345678', 'employe'),
(817, 'mekkii.benmoussa@gmail.com', '14754523', 'parent');

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `comment`
--
ALTER TABLE `comment`
  ADD CONSTRAINT `FK_9474526CE2904019` FOREIGN KEY (`thread_id`) REFERENCES `thread` (`id`),
  ADD CONSTRAINT `FK_9474526CF675F31B` FOREIGN KEY (`author_id`) REFERENCES `user` (`id`);

--
-- Contraintes pour la table `enfant`
--
ALTER TABLE `enfant`
  ADD CONSTRAINT `FK_34B70CA281608C57` FOREIGN KEY (`EmploiDuTemps_id`) REFERENCES `emploi_du_temps` (`id`),
  ADD CONSTRAINT `FK_34B70CA295C7E888` FOREIGN KEY (`Pmedicale_id`) REFERENCES `profilmedicale` (`id`);

--
-- Contraintes pour la table `media`
--
ALTER TABLE `media`
  ADD CONSTRAINT `FK_6A2CA10C33CE2470` FOREIGN KEY (`id_club`) REFERENCES `club` (`id`);

--
-- Contraintes pour la table `participant`
--
ALTER TABLE `participant`
  ADD CONSTRAINT `FK_D79F6B11FD02F13` FOREIGN KEY (`evenement_id`) REFERENCES `evenement` (`id`);

--
-- Contraintes pour la table `profilmedicale`
--
ALTER TABLE `profilmedicale`
  ADD CONSTRAINT `FK_954F379B450D2529` FOREIGN KEY (`enfant_id`) REFERENCES `enfant` (`id`),
  ADD CONSTRAINT `FK_954F379BC7D8E729` FOREIGN KEY (`pediatre_id`) REFERENCES `pediatre` (`id`) ON DELETE SET NULL;

--
-- Contraintes pour la table `subscription`
--
ALTER TABLE `subscription`
  ADD CONSTRAINT `FK_A3C664D333CE2470` FOREIGN KEY (`id_club`) REFERENCES `club` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
