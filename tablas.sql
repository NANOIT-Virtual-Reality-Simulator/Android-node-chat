-- phpMyAdmin SQL Dump
-- version 3.5.2.2
-- http://www.phpmyadmin.net
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 22-05-2014 a las 00:11:58
-- Versión del servidor: 5.5.27
-- Versión de PHP: 5.4.7

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de datos: `chat_pablo`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `contactos`
--

CREATE TABLE IF NOT EXISTS `contactos` (
  `contacto` varchar(20) NOT NULL,
  `pertenencia` varchar(20) NOT NULL,
  `tipo` int(11) NOT NULL DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `contactos`
--

INSERT INTO `contactos` (`contacto`, `pertenencia`, `tipo`) VALUES
('miguel', 'pablo', 1),
('pablo', 'miguel', 1),
('prueba', 'pablo', 2),
('prueba', 'miguel', 2),
('prueba', 'juan', 2),
('Aitor', 'pablo', 1),
('pablo', 'Aitor', 1),
('prueba2', 'pablo', 2),
('juan', 'pablo', 1),
('pablo', 'juan', 1),
('prueba3', 'pablo', 2),
('prueba3', 'luis', 2),
('prueba3', 'Aitor', 2),
('prueba3', 'juan', 2),
('prueba3', 'Miguel', 2),
('prueba3', 'Z', 2);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `grupo`
--

CREATE TABLE IF NOT EXISTS `grupo` (
  `nombre` varchar(20) NOT NULL,
  `propietario` varchar(20) NOT NULL,
  PRIMARY KEY (`nombre`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `grupo`
--

INSERT INTO `grupo` (`nombre`, `propietario`) VALUES
('Prueba', 'pablo'),
('prueba2', 'pablo'),
('prueba3', 'pablo');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `mensajes`
--

CREATE TABLE IF NOT EXISTS `mensajes` (
  `de` varchar(20) NOT NULL,
  `para` varchar(20) NOT NULL,
  `mensaje` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `mensajes`
--

INSERT INTO `mensajes` (`de`, `para`, `mensaje`) VALUES
('pablo', 'miguel', 'Hola\n'),
('pablo', 'miguel', 'que tal\n'),
('miguel', 'pablo', 'Hola'),
('pablo', 'miguel', 'algo\n'),
('pablo', 'miguel', 'prueba\n'),
('pablo', 'miguel', 'algo 2'),
('miguel', 'pablo', 'Hola'),
('miguel', 'pablo', 'Hola'),
('pablo', 'miguel', 'algo 2'),
('pablo', 'miguel', 'aaaaa'),
('pablo', 'miguel', 'hola\n'),
('pablo', 'miguel', 'hola\n'),
('pablo', 'miguel', 'que pasa\n'),
('pablo', 'prueba', 'hola'),
('miguel', 'prueba', 'A'),
('pablo', 'prueba', 'b'),
('miguel', 'prueba', 'h'),
('pablo', 'prueba', 'g'),
('miguel', 'prueba', 'j'),
('pablo', 'prueba', 'k'),
('pablo', 'prueba', 'm'),
('miguel', 'prueba', 'Hola'),
('miguel', 'prueba', 'cdf'),
('pablo', 'prueba', 'q'),
('pablo', 'prueba', 'r');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `sala`
--

CREATE TABLE IF NOT EXISTS `sala` (
  `numero` int(11) NOT NULL AUTO_INCREMENT,
  `contacto1` varchar(20) NOT NULL,
  `contacto2` varchar(20) NOT NULL,
  PRIMARY KEY (`numero`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=5 ;

--
-- Volcado de datos para la tabla `sala`
--

INSERT INTO `sala` (`numero`, `contacto1`, `contacto2`) VALUES
(2, 'pablo', 'miguel'),
(3, 'Aitor', 'pablo'),
(4, 'juan', 'pablo');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuarios`
--

CREATE TABLE IF NOT EXISTS `usuarios` (
  `nombre` varchar(20) NOT NULL,
  `contrasena` varchar(20) NOT NULL,
  PRIMARY KEY (`nombre`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `usuarios`
--

INSERT INTO `usuarios` (`nombre`, `contrasena`) VALUES
('Aitor', '5678'),
('Algo', '3456'),
('Guille', '1234'),
('Jose', '4567'),
('luis', '3456'),
('miguel', '2345'),
('P', '12345'),
('pablo', '1234'),
('Pepe', '3456'),
('Z', '1234');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
