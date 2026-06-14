-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Erstellungszeit: 14. Jun 2026 um 23:54
-- Server-Version: 10.4.28-MariaDB
-- PHP-Version: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Datenbank: `2DSpiel`
--

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `user`
--

CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `passwort` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Daten für Tabelle `user`
--

INSERT INTO `user` (`id`, `name`, `email`, `passwort`) VALUES
(1, 'S25_LaufAnimation', 's', '$2y$10$KfOEsxEb5DvrZpsXRn3F8OdM/F8O.rFPCWImEkoyzwfzGSMZvcYWe'),
(2, 'S', 'S', '$2y$10$SVyGRYgQuoP50VHYnHK5jOzEVvTcN65YU2S0.kJOY7o0MmReVad.G'),
(6, 'x', 'x', '$2y$10$e85irL8Zj0JbjZlP11b8UOB9lDru7Vajr2mXYMjp1gMA.gg1/nfr2'),
(7, 'c', 'c', '$2y$10$PYBMsmXIHDp5B/zKs5x3Vu3RmBolpM16VF4K42jmTUcOnYftcAXua'),
(8, 'W', 'W', '$2y$10$lwypN6CTu.wPIZquMysrlOH6JTpuNwQcS8pugikZeTq0Qm.wS5hWO'),
(9, 'z', 'z', '$2y$10$rUL.llowQ.8cZsIaxxec/OBYKuMWIW5LGP8Ygdg5eVA87Cf3eIShW');

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `welten`
--

CREATE TABLE `welten` (
  `id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `seed` int(255) NOT NULL,
  `userID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Indizes der exportierten Tabellen
--

--
-- Indizes für die Tabelle `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- Indizes für die Tabelle `welten`
--
ALTER TABLE `welten`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_welten_user` (`userID`);

--
-- AUTO_INCREMENT für exportierte Tabellen
--

--
-- AUTO_INCREMENT für Tabelle `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT für Tabelle `welten`
--
ALTER TABLE `welten`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- Constraints der exportierten Tabellen
--

--
-- Constraints der Tabelle `welten`
--
ALTER TABLE `welten`
  ADD CONSTRAINT `fk_welten_user` FOREIGN KEY (`userID`) REFERENCES `user` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
