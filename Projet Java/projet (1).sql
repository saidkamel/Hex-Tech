-- phpMyAdmin SQL Dump
-- version 4.9.2
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le :  lun. 02 mars 2020 à 18:43
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
  `IdActivite` int(11) NOT NULL AUTO_INCREMENT,
  `NomActivite` varchar(100) NOT NULL,
  `Description` text NOT NULL,
  `Type` varchar(100) NOT NULL,
  PRIMARY KEY (`IdActivite`)
) ENGINE=MyISAM AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `activite`
--

INSERT INTO `activite` (`IdActivite`, `NomActivite`, `Description`, `Type`) VALUES
(17, 'aze', 'sqd', 'Culture'),
(18, 'activite2', 'desc', 'Dessin'),
(15, 'activite1', 'balbal', 'Dessin'),
(16, 'activite2', 'hahah', 'Sport');

-- --------------------------------------------------------

--
-- Structure de la table `badwords`
--

DROP TABLE IF EXISTS `badwords`;
CREATE TABLE IF NOT EXISTS `badwords` (
  `mot` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `badwords`
--

INSERT INTO `badwords` (`mot`) VALUES
('bad'),
('bad'),
('bad'),
('bad'),
('bad'),
('bad'),
('bad'),
('bad'),
('bad'),
('bad'),
('bad'),
('bad'),
('bad'),
('bad'),
('bad'),
('bad'),
('bad'),
('bad'),
('bad'),
('bad'),
('bad');

-- --------------------------------------------------------

--
-- Structure de la table `classe`
--

DROP TABLE IF EXISTS `classe`;
CREATE TABLE IF NOT EXISTS `classe` (
  `IdClasse` int(11) NOT NULL AUTO_INCREMENT,
  `NomClasse` varchar(100) NOT NULL,
  PRIMARY KEY (`IdClasse`)
) ENGINE=MyISAM AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `classe`
--

INSERT INTO `classe` (`IdClasse`, `NomClasse`) VALUES
(16, '3A10'),
(1, 'vide');

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
(16, 1),
(16, 14),
(16, 15),
(16, 16),
(16, 17),
(16, 18),
(19, 11),
(19, 13);

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
  `comm` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `commentaire`
--

INSERT INTO `commentaire` (`nom`, `prenom`, `num`, `sujet`, `comm`) VALUES
('jiji', 'baccouuche', 95959595, 'bruit', 'bad'),
('jiji', 'baccouuche', 95959595, 'bruit', 'bad'),
('jiji', 'baccouuche', 95959595, 'bruit', 'bad'),
('jiji', 'baccouuche', 95959595, 'bruit', 'bad'),
('jiji', 'baccouuche', 95959595, 'bruit', 'bad'),
('jiji', 'baccouuche', 95959595, 'bruit', 'bad'),
('jiji', 'baccouuche', 95959595, 'bruit', 'bad'),
('jiji', 'baccouuche', 95959595, 'bruit', 'bad'),
('noussa', 'baccouuche', 959595955, 'music', 'good'),
('said', '', 0, '', 'hello'),
('said', '', 0, '', 'azerqsfdqdf'),
('jihed', '', 0, '', 'reclamatiion'),
('jihed', '', 0, '', 'reclamatiion');

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
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `enfant`
--

INSERT INTO `enfant` (`id`, `nom`, `prenom`, `DateNaissance`, `idParent`, `idclasse`) VALUES
(7, 'said3', 'kamel', '2020-02-14', 155, 16),
(8, 'ssss', 'aaaa', '2020-02-02', 169, 16),
(4, 'said', 'kamel', '2020-02-02', 174, 16),
(9, 'newmodif', 'dddd', '2020-02-27', 169, 16),
(12, 'said3', 'kamel', '2020-02-14', 155, 16),
(11, 'newkid', 'holo', '2010-02-05', 169, 1),
(13, 'enfant', 'prenom', '2014-01-30', 170, 16),
(14, 'saidssss', 'kamelss', '2020-01-27', 170, 16),
(16, 'rachidtt', 'hamzaaa', '2020-02-04', 155, 16),
(17, 'said', 'hamza', '2020-02-05', 174, 16);

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
) ENGINE=InnoDB AUTO_INCREMENT=74 DEFAULT CHARSET=utf8mb4;

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
(73, 'mondher', 'Culturel', '2020-01-27', '2020-02-28', 'd', 1);

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
  `commentaire` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `jardins`
--

DROP TABLE IF EXISTS `jardins`;
CREATE TABLE IF NOT EXISTS `jardins` (
  `id` int(11) NOT NULL,
  `nom` varchar(20) NOT NULL,
  `capacite` int(11) NOT NULL,
  `num` int(11) NOT NULL,
  `mail` varchar(50) NOT NULL,
  `activite` varchar(20) NOT NULL,
  `localisation` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

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
  `Id_p` int(11) NOT NULL AUTO_INCREMENT,
  `Nom_p` varchar(50) NOT NULL,
  `Prenom_p` varchar(50) NOT NULL,
  `mdp_p` varchar(60) NOT NULL,
  `Mail` varchar(255) NOT NULL,
  PRIMARY KEY (`Id_p`)
) ENGINE=InnoDB AUTO_INCREMENT=57 DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `participant`
--

INSERT INTO `participant` (`Id_p`, `Nom_p`, `Prenom_p`, `mdp_p`, `Mail`) VALUES
(1, 'Mallekk', 'Mondherr', '1256', ''),
(24, 'bilel', 'mallek', '46464', ''),
(37, 'ml', 'po', '545', ''),
(50, 'm', 'm', '5', 'mmm@es'),
(51, 'mm', 'p', '458', 'mail'),
(52, '', '', '', ''),
(53, 'mon', 'pppp', '5', 'mail'),
(54, 'mon', 'mail', '5', 'mail'),
(55, 'mon', 'ppp', '5', 'mail'),
(56, 'mon', 'mon', '125', 'mondhermallek97@gmail.com');

-- --------------------------------------------------------

--
-- Structure de la table `reclamation`
--

DROP TABLE IF EXISTS `reclamation`;
CREATE TABLE IF NOT EXISTS `reclamation` (
  `cin` int(11) NOT NULL,
  `nom` varchar(20) NOT NULL,
  `prenom` varchar(20) NOT NULL,
  `note` int(11) NOT NULL,
  `motif` varchar(20) NOT NULL,
  `description` varchar(200) NOT NULL,
  `date` date NOT NULL,
  PRIMARY KEY (`cin`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `reclamation`
--

INSERT INTO `reclamation` (`cin`, `nom`, `prenom`, `note`, `motif`, `description`, `date`) VALUES
(0, 'null', 'null', 5, 'jeu', 'barcha', '2020-02-11'),
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
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
