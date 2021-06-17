

drop database tennis;
--
-- Base de donn�es: `tennis`
--
CREATE DATABASE IF NOT EXISTS `tennis` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `tennis`;

-- --------------------------------------------------------

--
-- Structure de la table `arbitre`
--

CREATE TABLE IF NOT EXISTS `arbitre` (
  `Identifiant` int(11) NOT NULL AUTO_INCREMENT,
  `Nom` char(30) NOT NULL,
  `Prenom` char(30) NOT NULL,
  `Niveau` int(11) NOT NULL,
  `Nation` varchar(15) NOT NULL,
  PRIMARY KEY (`Identifiant`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='Arbitres de tennis' AUTO_INCREMENT=5 ;

--
-- Contenu de la table `arbitre`
--

INSERT INTO `arbitre` (`Identifiant`, `Nom`, `Prenom`, `Niveau`, `Nation`) VALUES
(1, 'Dupont', 'Paul', 4, 'France'),
(2, 'Durant', 'Pierre', 2, 'France'),
(3, 'Thomasson', 'Aline', 0, 'France'),
(4, 'Palacios', 'Carolina', 0, 'Espagne');

-- --------------------------------------------------------

--
-- Structure de la table `court`
--

CREATE TABLE IF NOT EXISTS `court` (
  `Numero` int(11) NOT NULL AUTO_INCREMENT,
  `Nom` varchar(30) NOT NULL,
  PRIMARY KEY (`Numero`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='Table des courts' AUTO_INCREMENT=4 ;

--
-- Contenu de la table `court`
--

INSERT INTO `court` (`Numero`, `Nom`) VALUES
(1, 'Philippe Chatrier'),
(2, 'Suzanne Lenglen'),
(3, 'Court n� 1');

-- --------------------------------------------------------

--
-- Structure de la table `joueur`
--

CREATE TABLE IF NOT EXISTS `joueur` (
  `identifiant` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(30) NOT NULL,
  `prenom` varchar(30) NOT NULL,
  `sexe` char(1) NOT NULL,
  `nation` varchar(15) NOT NULL,
  `classement` mediumint(8) unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`identifiant`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='table des joueurs' AUTO_INCREMENT=6 ;

--
-- Contenu de la table `joueur`
--

INSERT INTO `joueur` (`identifiant`, `nom`, `prenom`, `sexe`, `nation`, `classement`) VALUES
(1, 'Nadal', 'Rafael', 'M', 'Espagne', 0),
(2, 'Djokovic', 'Novak', 'M', 'Serbie', 0),
(3, 'Monfils', 'Ga�l', 'M', 'France', 0),
(4, 'Williams', 'S�r�na', 'F', 'Etats Unis', 0),
(5, 'Bertens', 'Kiki', 'F', 'Pays Bas', 0);

-- --------------------------------------------------------

--
-- Structure de la table `match`
--

CREATE TABLE IF NOT EXISTS `match` (
  `identifiant` int(11) NOT NULL AUTO_INCREMENT,
  `refjoueur1` int(11) NOT NULL,
  `refjoueur2` int(11) NOT NULL,
  `refarbitre` int(11) NOT NULL,
  `nocourt` int(11) NOT NULL,
  `datematch` date NOT NULL,
  `nbsetsjoueur1` int(11) NOT NULL,
  `nbsetsjoueur2` int(11) NOT NULL,
  PRIMARY KEY (`identifiant`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='Table des matches' ;

--
-- Contenu de la table `match`
--

INSERT INTO `match` (`identifiant`, `reftype`, `datetournoi`, `nocourt`, `refarbitre`, `refjoueur1`, `refjoueur2`, `nbsetsjoueur1`, `nbsetsjoueur2`) VALUES
(1, 1, '2016-04-10', 1, 4, 1, 2, 0, 0);

-- --------------------------------------------------------

--
-- Structure de la table `typetournoi`
--

CREATE TABLE IF NOT EXISTS typetableau (
  Identifiant int(2) NOT NULL,
  NomTableau varchar(20) NOT NULL,
  Nbsets int(2) NOT NULL,
  PRIMARY KEY (Identifiant)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Table des types de match';

--
-- Contenu de la table `typetournoi`
--

INSERT INTO typetableau (Identifiant, NomTableau, Nbsets) VALUES
(1, 'Simple Messieurs', 3),
(2, 'Simple Dames', 2),
(4, 'Simple Juniors', 2),
(10, 'Double Messieurs', 3),
(20, 'Double Dames', 2),
(30, 'Doubles mixtes', 3),
(40, 'Doubles Juniors', 2);

-- --------------------------------------------------------

--
-- Structure de la table `utilisateur`
--

CREATE TABLE IF NOT EXISTS `utilisateur` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `login` varchar(30) NOT NULL,
  `password` varchar(30) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `login` (`login`,`password`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Contenu de la table `utilisateur`
--

INSERT INTO `utilisateur` (`id`, `login`, `password`) VALUES
(1, 'admin', 'admin');

--
-- Contraintes pour les tables export�es
  ALTER TABLE `joueur`
  ADD UNIQUE KEY `nom` (`nom`,`prenom`);


--
-- Contraintes pour la table `match`
--
ALTER TABLE `match`
  ADD CONSTRAINT `match_ibfk_1` FOREIGN KEY (`refarbitre`) REFERENCES `arbitre` (`Identifiant`),
  ADD CONSTRAINT `match_ibfk_2` FOREIGN KEY (`reftype`) REFERENCES `typetournoi` (`Referencetype`),
  ADD CONSTRAINT `match_ibfk_3` FOREIGN KEY (`nocourt`) REFERENCES `court` (`Numero`),
  ADD CONSTRAINT `match_ibfk_4` FOREIGN KEY (`refjoueur1`) REFERENCES `joueur` (`identifiant`),
  ADD CONSTRAINT `match_ibfk_5` FOREIGN KEY (`refjoueur2`) REFERENCES `joueur` (`identifiant`);
