-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 16-02-2023 a las 14:57:11
-- Versión del servidor: 10.1.38-MariaDB
-- Versión de PHP: 5.6.40

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `p21`
--
CREATE DATABASE IF NOT EXISTS `p21` DEFAULT CHARACTER SET utf8 COLLATE utf8_bin;
USE `p21`;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `name` varchar(50) COLLATE utf8_bin NOT NULL,
  `surnames` varchar(255) COLLATE utf8_bin NOT NULL,
  `mail` varchar(255) COLLATE utf8_bin NOT NULL,
  `phone` varchar(50) COLLATE utf8_bin NOT NULL,
  `pass` varchar(255) COLLATE utf8_bin NOT NULL,
  `role` tinyint(1) NOT NULL,
  `lang` varchar(50) COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Volcado de datos para la tabla `users`
--

INSERT INTO `users` (`id`, `name`, `surnames`, `mail`, `phone`, `pass`, `role`, `lang`) VALUES
(1, 'Admin', 'The Admin', 'admin@mail.com', '000000000', 'TpBOWO9Z9l0vw3b6ICMt2ha9vz/OoLp40gDxRs/LSPCWzEeWYehRhXQhInrjuHgt', 1, 'en'),
(2, 'Adam', 'Alda Almirante', 'adam@mail.com', '666554433', 'ZoglyH60ViSdTsKMBvF8iZ1uOlYMSKuJ+wv90W8VrsMihjNmaKcfJ6eS5paIU3ph', 0, 'en'),
(3, 'Betty', 'Bueno Baños', 'betty@mail.com', '555443322', '+RkUhOfPBRrtVhw2ENAkxj+7gmCI/K2bQEa/nwHTm88605SnbwNs1xXijIEno0qS', 0, 'es'),
(4, 'Charlie', 'Corral Casar', 'charlie@mail.com', '444332211', '4KiTMULo5Oqj6Ysz4U1Z2Xw5QMmzinZrL/60cH9N2PB5sHkm/TVV9T/GWN0bgJnz', 0, 'es'),
(5, 'Cecil', 'Cinta Coso', 'cecil@mail.com', '420332811', '+5DAZC0gLmFuWMPf/1Fyyay8IktYj8x5gfUOvmX19ukNsIc0MLsATwcjfobiClDK', 0, 'es'),
(6, 'Diane', 'Dueñas Donoso', 'diane@mail.com', '333221100', 'Y6op3BH0iagkccymeVTibelnHmJBRU03m0FSQtgVAC7belL1bUJwPW/kBrqKEoCb', 0, 'en'),
(7, 'Eric', 'Estepa España', 'eric@mail.com', '111223344', '6+3SMSF0VZ5I1OcBaEjpaBih05Z/KarvUPbIe1Ugcy+xzO2FXzW4dJArBBvxepkr', 0, 'es');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `mail` (`mail`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
