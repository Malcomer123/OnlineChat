-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : mar. 31 jan. 2023 à 00:36
-- Version du serveur : 10.4.21-MariaDB
-- Version de PHP : 8.0.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `fatidounia`
--

-- --------------------------------------------------------

--
-- Structure de la table `mail`
--

CREATE TABLE `mail` (
  `ID` bigint(20) NOT NULL,
  `OBJET` varchar(50) NOT NULL,
  `CONTENUE` varchar(5000) NOT NULL,
  `ID_INTERVENANT` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `mail`
--

INSERT INTO `mail` (`ID`, `OBJET`, `CONTENUE`, `ID_INTERVENANT`) VALUES
(1, 'nouvelle tache', 'Dans le monde professionnel, il arrive souvent que l\'on soit amené à envoyer un document en pièce-jointe à un collaborateur ou à un supérieur. Or pas question d\'envoyer la pièce-jointe seule : celle-ci doit être accompagné d\'un corps de mail professionnel et sérieux. Pour vous aider, voici donc un exemple de mail professionnel avec pièce-jointe.', 4),
(2, 'transfert', 'Savoir écrire un bon mail professionnel en français est très important au Maroc. En effet, une partie importante des échanges par écrit se fait dans cette langue, et sa bonne maîtrise est un élément qui vous permettra de vous distinguer dans l\'entreprise. Ainsi, voici quelques conseils pour bien rédiger votre mail professionnel en français', 4),
(3, 'deplacement', 'Ainsi, il existe diverses formules de politesse pour conclure un mail professionnel en français. Si vous avez un doute sur la bonne formule à adopter, essayez de demander conseil à une personne d\'expérience qui sera à même de vous guider.', 4),
(4, 'augmentation', 'Combien d’emails non lus avez vous dans votre boite de réception? 100? 200? ou peut être vous êtes comme moi et vous avez un nombre incalculable d’emails, 2845.\r\n\r\nOn reçoit des dizaines d’emails de relance chaque jour, et beaucoup d’entre eux ne sont jamais ouverts. Les gens sont inondés  d’informations – plus que jamais auparavant dans l’histoire! Mais nous ne disposons pas de plus de temps pour absorber tout cela.', 4);

-- --------------------------------------------------------

--
-- Structure de la table `projet`
--

CREATE TABLE `projet` (
  `ID` bigint(20) NOT NULL,
  `NOM` varchar(20) NOT NULL,
  `CLIENT` varchar(15) NOT NULL,
  `BUDJET` double NOT NULL,
  `RISQUE` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `projet`
--

INSERT INTO `projet` (`ID`, `NOM`, `CLIENT`, `BUDJET`, `RISQUE`) VALUES
(2, 'paraDounia', 'Fatima', 100000, 'Pas de risque '),
(3, 'BclicApp', 'Dounia', 50000, 'Money');

-- --------------------------------------------------------

--
-- Structure de la table `tache`
--

CREATE TABLE `tache` (
  `ID` bigint(20) NOT NULL,
  `OBJET` varchar(20) NOT NULL,
  `DESCRIPTION` varchar(80) NOT NULL,
  `DATE_DEBUT` date NOT NULL,
  `DATE_FIN` date NOT NULL,
  `PRIORITE` int(11) NOT NULL,
  `STATUS` tinyint(1) NOT NULL,
  `ID_PROJET` bigint(20) NOT NULL,
  `ID_INTERVENANT` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `tache`
--

INSERT INTO `tache` (`ID`, `OBJET`, `DESCRIPTION`, `DATE_DEBUT`, `DATE_FIN`, `PRIORITE`, `STATUS`, `ID_PROJET`, `ID_INTERVENANT`) VALUES
(7, 'Réunion de projet', 'Faire une réunion pour citer les besoins', '2023-01-01', '2023-01-01', 3, 0, 3, 4),
(8, 'Définition du projet', 'faire une définition du projet', '2023-01-02', '2023-01-07', 1, 0, 3, 4),
(9, 'Développer le concep', 'Faire une conception du projet', '2023-01-08', '2023-01-12', 2, 1, 3, 4),
(10, 'Développer les fonct', 'développer toutes les fonctions', '2023-01-09', '2023-01-24', 4, 0, 3, 4),
(11, 'Tester', 'faire une teste du projet ', '2023-01-25', '2023-01-26', 5, 0, 3, 4);

-- --------------------------------------------------------

--
-- Structure de la table `utilisateur`
--

CREATE TABLE `utilisateur` (
  `ID` bigint(20) NOT NULL,
  `USERNAME` varchar(15) NOT NULL,
  `PASSWORD` varchar(15) NOT NULL,
  `NOM` varchar(20) NOT NULL,
  `PRENOM` varchar(20) NOT NULL,
  `CIN` varchar(10) NOT NULL,
  `EMAIL` varchar(15) NOT NULL,
  `TELEPHONE` varchar(10) NOT NULL,
  `DATE_NAISSANCE` date NOT NULL,
  `DATE_EMBAUCHE` date NOT NULL,
  `DIPLOME` varchar(30) NOT NULL,
  `SALAIRE` double NOT NULL,
  `ROLE` varchar(10) NOT NULL,
  `ID_PROJET` bigint(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `utilisateur`
--

INSERT INTO `utilisateur` (`ID`, `USERNAME`, `PASSWORD`, `NOM`, `PRENOM`, `CIN`, `EMAIL`, `TELEPHONE`, `DATE_NAISSANCE`, `DATE_EMBAUCHE`, `DIPLOME`, `SALAIRE`, `ROLE`, `ID_PROJET`) VALUES
(4, 'salma25', 'soulaima89', 'rahmaoui', 'salma', 'da4556', 'selma@gmail.com', '055558965', '2023-01-04', '2023-01-27', 'master', 8200, 'user', 3),
(10, 'marwan23', 'marwanmor', 'hattim', 'marwan', 'mr256', 'mer23@gmail.com', '052569858', '2023-01-03', '2023-01-15', 'lp', 5898, 'user', 3),
(11, 'Dodo', 'dounia123@', 'EL ABBASSI', 'Dounia', 'AD319494', 'dounia123@gmail', '0641790192', '2023-01-06', '2023-01-12', 'MASTER', 10000, 'admin', 3),
(12, 'Tetim', 'fatima123@', 'OUBAOUAN', 'Fatima', 'AD156987', 'fatima123@gmail', '0615830165', '2013-01-09', '2023-01-01', 'MASTER', 10000, 'admin', 2),
(13, 'imi', 'imane123@', 'BOUSTILA', 'Imane', 'AD15697', 'imane123@gmail.', '0696781523', '2013-01-09', '2023-01-01', 'Licence', 5000, 'user', 3),
(14, 'weam', 'weam123@', 'MOULOUDI', 'Weam', 'AM159632', 'weam123@gmail.c', '0689723165', '2013-01-03', '2023-01-03', 'Licence', 5000, 'user', 3),
(15, 'ouma', 'oumaima123@', 'ALOUCHE', 'Oumaima', 'AQ859763', 'oumaima123@gmai', '0789231659', '2015-01-21', '2023-01-18', 'MASTER', 9000, 'user', 3),
(16, 'Ahmed', 'ahmed123@', 'BENTAJJA', 'Ahmed', 'AM15679', 'ahmed123@gmail.', '0689765312', '2016-01-07', '2023-01-01', 'DUT', 3000, 'user', 3);

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `mail`
--
ALTER TABLE `mail`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `ID_INTERVENANT` (`ID_INTERVENANT`);

--
-- Index pour la table `projet`
--
ALTER TABLE `projet`
  ADD PRIMARY KEY (`ID`);

--
-- Index pour la table `tache`
--
ALTER TABLE `tache`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `ID_PROJET` (`ID_PROJET`),
  ADD KEY `ID_INTERVENANT` (`ID_INTERVENANT`);

--
-- Index pour la table `utilisateur`
--
ALTER TABLE `utilisateur`
  ADD PRIMARY KEY (`ID`),
  ADD UNIQUE KEY `USERNAME` (`USERNAME`),
  ADD KEY `ID_PROJET` (`ID_PROJET`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `mail`
--
ALTER TABLE `mail`
  MODIFY `ID` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT pour la table `projet`
--
ALTER TABLE `projet`
  MODIFY `ID` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT pour la table `tache`
--
ALTER TABLE `tache`
  MODIFY `ID` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT pour la table `utilisateur`
--
ALTER TABLE `utilisateur`
  MODIFY `ID` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `mail`
--
ALTER TABLE `mail`
  ADD CONSTRAINT `mail_ibfk_1` FOREIGN KEY (`ID_INTERVENANT`) REFERENCES `utilisateur` (`ID`);

--
-- Contraintes pour la table `tache`
--
ALTER TABLE `tache`
  ADD CONSTRAINT `tache_ibfk_1` FOREIGN KEY (`ID_PROJET`) REFERENCES `projet` (`ID`),
  ADD CONSTRAINT `tache_ibfk_2` FOREIGN KEY (`ID_INTERVENANT`) REFERENCES `utilisateur` (`ID`);

--
-- Contraintes pour la table `utilisateur`
--
ALTER TABLE `utilisateur`
  ADD CONSTRAINT `utilisateur_ibfk_1` FOREIGN KEY (`ID_PROJET`) REFERENCES `projet` (`ID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
