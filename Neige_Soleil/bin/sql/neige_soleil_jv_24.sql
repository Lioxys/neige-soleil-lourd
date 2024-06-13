-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : jeu. 25 jan. 2024 à 22:08
-- Version du serveur : 10.4.32-MariaDB
-- Version de PHP : 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `neige_soleil_jv_24`
--

-- --------------------------------------------------------

--
-- Structure de la table `appartement`
--

CREATE TABLE `appartement` (
  `idhabitation` int(8) NOT NULL,
  `etage` int(2) DEFAULT NULL,
  `num_palier` int(2) DEFAULT NULL,
  `num_batiment` int(2) DEFAULT NULL,
  `adresse` varchar(50) DEFAULT NULL,
  `code_postal` int(5) DEFAULT NULL,
  `ville` varchar(30) DEFAULT NULL,
  `superficie` float(6,2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `appartement`
--

INSERT INTO `appartement` (`idhabitation`, `etage`, `num_palier`, `num_batiment`, `adresse`, `code_postal`, `ville`, `superficie`) VALUES
(2, 2, 5, NULL, '3 rond point blabla appart', 77176, 'savigny', 20.00);

--
-- Déclencheurs `appartement`
--
DELIMITER $$
CREATE TRIGGER `insert_appartement` BEFORE INSERT ON `appartement` FOR EACH ROW BEGIN
    if new.idhabitation is null or new.idhabitation in (select idhabitation from habitation) or new.idhabitation = 0 then
set new.idhabitation = ifnull((select idhabitation from habitation where idhabitation >= all(select idhabitation from habitation)), 0) +1 ;
end if;
insert into habitation values(new.idhabitation, 'appartement', 'disponible');
end
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Structure de la table `archive_reservation`
--

CREATE TABLE `archive_reservation` (
  `idreservation` int(8) NOT NULL,
  `prix` float DEFAULT NULL,
  `nb_personne` int(2) DEFAULT NULL,
  `iduser` int(5) DEFAULT NULL,
  `idhabitation` int(5) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `archive_reservation`
--

INSERT INTO `archive_reservation` (`idreservation`, `prix`, `nb_personne`, `iduser`, `idhabitation`) VALUES
(5, 727, 7, 1, 1),
(8, 727, 7, 2, 1),
(9, 727, 7, 2, 1),
(10, 727, 7, 2, 1),
(11, 727, 7, 2, 1),
(12, 727, 7, 2, 1);

-- --------------------------------------------------------

--
-- Structure de la table `client`
--

CREATE TABLE `client` (
  `iduser` int(5) NOT NULL,
  `nom` varchar(50) NOT NULL,
  `prenom` varchar(50) NOT NULL,
  `adresse` varchar(70) DEFAULT NULL,
  `cp` char(5) DEFAULT NULL,
  `ville` varchar(30) DEFAULT NULL,
  `telephone` char(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `client`
--

INSERT INTO `client` (`iduser`, `nom`, `prenom`, `adresse`, `cp`, `ville`, `telephone`) VALUES
(2, 'pilorge', 'ludkas', '3 rond point du gresivaudan', '77127', 'lieusaint', '0633056426');

--
-- Déclencheurs `client`
--
DELIMITER $$
CREATE TRIGGER `insert_client` BEFORE INSERT ON `client` FOR EACH ROW BEGIN
    if new.iduser is null or new.iduser in (select iduser from user) or new.iduser = 0 then
set new.iduser = ifnull((select iduser from user where iduser >= all(select iduser from user)), 0) +1 ;
end if;
insert into user values(new.iduser, 'client', new.nom, new.prenom, 0);
end
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Structure de la table `habitation`
--

CREATE TABLE `habitation` (
  `idhabitation` int(8) NOT NULL,
  `type` enum('maison','appartement') DEFAULT NULL,
  `etat` enum('disponible','reserve') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `habitation`
--

INSERT INTO `habitation` (`idhabitation`, `type`, `etat`) VALUES
(1, 'maison', 'disponible'),
(2, 'appartement', 'disponible');

-- --------------------------------------------------------

--
-- Structure de la table `maison`
--

CREATE TABLE `maison` (
  `idhabitation` int(8) NOT NULL,
  `superficie` float(6,2) DEFAULT NULL,
  `adresse` varchar(50) DEFAULT NULL,
  `code_postal` int(5) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `maison`
--

INSERT INTO `maison` (`idhabitation`, `superficie`, `adresse`, `code_postal`) VALUES
(1, 35.00, '3 rond-point blabla', 77176);

--
-- Déclencheurs `maison`
--
DELIMITER $$
CREATE TRIGGER `insert_maison` BEFORE INSERT ON `maison` FOR EACH ROW BEGIN
    if new.idhabitation is null or new.idhabitation in (select idhabitation from habitation) or new.idhabitation = 0 then
set new.idhabitation = ifnull((select idhabitation from habitation where idhabitation >= all(select idhabitation from habitation)), 0) +1 ;
end if;
insert into habitation values(new.idhabitation, 'maison', 'disponible');
end
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Structure de la table `proprietaire`
--

CREATE TABLE `proprietaire` (
  `iduser` int(5) NOT NULL,
  `nom` varchar(50) NOT NULL,
  `prenom` varchar(50) NOT NULL,
  `adresse` varchar(70) DEFAULT NULL,
  `cp` char(5) DEFAULT NULL,
  `ville` varchar(30) DEFAULT NULL,
  `telephone` char(10) DEFAULT NULL,
  `nbhab` int(5) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `proprietaire`
--

INSERT INTO `proprietaire` (`iduser`, `nom`, `prenom`, `adresse`, `cp`, `ville`, `telephone`, `nbhab`) VALUES
(1, 'pilorge', 'nathan', '3 rond point de la thierache', '77176', 'savigny', '0674566347', 3);

--
-- Déclencheurs `proprietaire`
--
DELIMITER $$
CREATE TRIGGER `insert_proprietaire` BEFORE INSERT ON `proprietaire` FOR EACH ROW BEGIN
    if new.iduser is null or new.iduser in (select iduser from user) or new.iduser = 0 then
set new.iduser = ifnull((select iduser from user where iduser >= all(select iduser from user)), 0) +1 ;
end if;
insert into user values(new.iduser, 'proprietaire', new.nom, new.prenom, 0);
end
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Structure de la table `reservation`
--

CREATE TABLE `reservation` (
  `idreservation` int(8) NOT NULL,
  `prix` float DEFAULT NULL,
  `nb_personne` int(2) DEFAULT NULL,
  `iduser` int(5) DEFAULT NULL,
  `idhabitation` int(5) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déclencheurs `reservation`
--
DELIMITER $$
CREATE TRIGGER `decrement_reservation` BEFORE DELETE ON `reservation` FOR EACH ROW begin
    update user set nbreservation=nbreservation-1 where iduser=old.iduser;
    update habitation set etat='disponible' where idhabitation=old.idhabitation;
end
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `increment_archive_reservation` AFTER INSERT ON `reservation` FOR EACH ROW begin
    insert into archive_reservation values(new.idreservation, new.prix, new.nb_personne, new.iduser, new.idhabitation);
end
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `increment_reservation` BEFORE INSERT ON `reservation` FOR EACH ROW begin 
    if new.idhabitation in (select idhabitation from habitation where etat='reserve') THEN 
    signal sqlstate'45000' set message_text='habitation d?j? reserv?e';
    end if;
    update user set nbreservation=nbreservation+1 where iduser=new.iduser;
    update habitation set etat='reserve' where idhabitation=new.idhabitation;
end
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Structure de la table `user`
--

CREATE TABLE `user` (
  `iduser` int(5) NOT NULL,
  `role` enum('proprietaire','client') DEFAULT NULL,
  `nom` varchar(50) DEFAULT NULL,
  `prenom` varchar(50) DEFAULT NULL,
  `nbreservation` int(3) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `user`
--

INSERT INTO `user` (`iduser`, `role`, `nom`, `prenom`, `nbreservation`) VALUES
(1, 'proprietaire', 'pilorge', 'nathan', 0),
(2, 'client', 'pilorge', 'ludkas', 0);

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `appartement`
--
ALTER TABLE `appartement`
  ADD PRIMARY KEY (`idhabitation`);

--
-- Index pour la table `archive_reservation`
--
ALTER TABLE `archive_reservation`
  ADD PRIMARY KEY (`idreservation`);

--
-- Index pour la table `client`
--
ALTER TABLE `client`
  ADD PRIMARY KEY (`iduser`);

--
-- Index pour la table `habitation`
--
ALTER TABLE `habitation`
  ADD PRIMARY KEY (`idhabitation`);

--
-- Index pour la table `maison`
--
ALTER TABLE `maison`
  ADD PRIMARY KEY (`idhabitation`);

--
-- Index pour la table `proprietaire`
--
ALTER TABLE `proprietaire`
  ADD PRIMARY KEY (`iduser`);

--
-- Index pour la table `reservation`
--
ALTER TABLE `reservation`
  ADD PRIMARY KEY (`idreservation`),
  ADD KEY `iduser` (`iduser`),
  ADD KEY `idhabitation` (`idhabitation`);

--
-- Index pour la table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`iduser`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `appartement`
--
ALTER TABLE `appartement`
  MODIFY `idhabitation` int(8) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT pour la table `archive_reservation`
--
ALTER TABLE `archive_reservation`
  MODIFY `idreservation` int(8) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT pour la table `client`
--
ALTER TABLE `client`
  MODIFY `iduser` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT pour la table `habitation`
--
ALTER TABLE `habitation`
  MODIFY `idhabitation` int(8) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT pour la table `maison`
--
ALTER TABLE `maison`
  MODIFY `idhabitation` int(8) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT pour la table `proprietaire`
--
ALTER TABLE `proprietaire`
  MODIFY `iduser` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT pour la table `reservation`
--
ALTER TABLE `reservation`
  MODIFY `idreservation` int(8) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT pour la table `user`
--
ALTER TABLE `user`
  MODIFY `iduser` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `reservation`
--
ALTER TABLE `reservation`
  ADD CONSTRAINT `reservation_ibfk_1` FOREIGN KEY (`iduser`) REFERENCES `user` (`iduser`),
  ADD CONSTRAINT `reservation_ibfk_2` FOREIGN KEY (`idhabitation`) REFERENCES `habitation` (`idhabitation`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
