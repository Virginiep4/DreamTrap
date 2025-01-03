-- phpMyAdmin SQL Dump
-- version 4.5.4.1
-- http://www.phpmyadmin.net
--
-- Client :  localhost
-- Généré le :  Ven 27 Décembre 2024 à 10:20
-- Version du serveur :  5.7.11
-- Version de PHP :  5.6.18

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `dreamtrap`
--

-- --------------------------------------------------------

--
-- Structure de la table `avoir`
--

CREATE TABLE `avoir` (
  `idnom` tinyint(100) NOT NULL,
  `idprogression` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Contenu de la table `avoir`
--

INSERT INTO `avoir` (`idnom`, `idprogression`) VALUES
(3, 12);

-- --------------------------------------------------------

--
-- Structure de la table `item`
--

CREATE TABLE `item` (
  `idItem` int(11) NOT NULL,
  `nom` varchar(45) DEFAULT NULL,
  `prix` smallint(255) DEFAULT NULL,
  `description` varchar(450) DEFAULT NULL,
  `Acquis` tinyint(4) DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Contenu de la table `item`
--

INSERT INTO `item` (`idItem`, `nom`, `prix`, `description`, `Acquis`) VALUES
(1, 'poing', 20, 'Une poing tranchante', 0),
(2, 'poing', 20, 'Une poing tranchante', 0),
(3, 'poing', 20, 'Une poing tranchante', 0),
(4, 'poing', 20, 'Une poing tranchante', 0);

-- --------------------------------------------------------

--
-- Structure de la table `joueur`
--

CREATE TABLE `joueur` (
  `idnom` tinyint(100) NOT NULL,
  `niveau` tinyint(3) DEFAULT '1',
  `etoiles` int(255) DEFAULT NULL,
  `nom` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Contenu de la table `joueur`
--

INSERT INTO `joueur` (`idnom`, `niveau`, `etoiles`, `nom`) VALUES
(1, 1, 0, 'dede'),
(2, 1, 0, 'dede'),
(3, 1, 0, 'dede');

-- --------------------------------------------------------

--
-- Structure de la table `posseder`
--

CREATE TABLE `posseder` (
  `idnom` tinyint(100) NOT NULL,
  `idItem` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Contenu de la table `posseder`
--

INSERT INTO `posseder` (`idnom`, `idItem`) VALUES
(1, 1);

-- --------------------------------------------------------

--
-- Structure de la table `progression`
--

CREATE TABLE `progression` (
  `idprogression` int(11) NOT NULL,
  `nom_joueur` varchar(45) DEFAULT NULL,
  `temps` time DEFAULT NULL,
  `win` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Contenu de la table `progression`
--

INSERT INTO `progression` (`idprogression`, `nom_joueur`, `temps`, `win`) VALUES
(1, NULL, '00:00:00', '0'),
(2, NULL, '00:00:00', '0'),
(3, NULL, '00:00:00', '0'),
(10, NULL, '00:00:00', '0'),
(11, NULL, '00:00:00', '0'),
(12, 'dede', '00:00:00', '0');

--
-- Index pour les tables exportées
--

--
-- Index pour la table `avoir`
--
ALTER TABLE `avoir`
  ADD PRIMARY KEY (`idnom`,`idprogression`),
  ADD KEY `fk_Joueur_has_progression_progression1` (`idprogression`);

--
-- Index pour la table `item`
--
ALTER TABLE `item`
  ADD PRIMARY KEY (`idItem`);

--
-- Index pour la table `joueur`
--
ALTER TABLE `joueur`
  ADD PRIMARY KEY (`idnom`);

--
-- Index pour la table `posseder`
--
ALTER TABLE `posseder`
  ADD PRIMARY KEY (`idnom`,`idItem`),
  ADD KEY `fk_Joueur_has_item_item1` (`idItem`);

--
-- Index pour la table `progression`
--
ALTER TABLE `progression`
  ADD PRIMARY KEY (`idprogression`);

--
-- AUTO_INCREMENT pour les tables exportées
--

--
-- AUTO_INCREMENT pour la table `item`
--
ALTER TABLE `item`
  MODIFY `idItem` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT pour la table `joueur`
--
ALTER TABLE `joueur`
  MODIFY `idnom` tinyint(100) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT pour la table `progression`
--
ALTER TABLE `progression`
  MODIFY `idprogression` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;
--
-- Contraintes pour les tables exportées
--

--
-- Contraintes pour la table `avoir`
--
ALTER TABLE `avoir`
  ADD CONSTRAINT `fk_Joueur_has_progression_Joueur1` FOREIGN KEY (`idnom`) REFERENCES `joueur` (`idnom`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_Joueur_has_progression_progression1` FOREIGN KEY (`idprogression`) REFERENCES `progression` (`idprogression`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Contraintes pour la table `posseder`
--
ALTER TABLE `posseder`
  ADD CONSTRAINT `fk_Joueur_has_item_Joueur1` FOREIGN KEY (`idnom`) REFERENCES `joueur` (`idnom`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_Joueur_has_item_item1` FOREIGN KEY (`idItem`) REFERENCES `item` (`idItem`) ON DELETE NO ACTION ON UPDATE NO ACTION;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
