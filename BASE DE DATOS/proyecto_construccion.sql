-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 12-01-2024 a las 05:00:13
-- Versión del servidor: 10.4.32-MariaDB
-- Versión de PHP: 8.0.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `proyecto_construccion`
--

DELIMITER $$
--
-- Procedimientos
--
CREATE DEFINER=`root`@`localhost` PROCEDURE `AceptarSolicitudAmistad` (IN `p_request_id` INT)   BEGIN
    DECLARE sender_user_id INT;
    DECLARE receiver_user_id INT;

    -- Obtener el sender_id y receiver_id de la solicitud
    SELECT sender_id, receiver_id
    INTO sender_user_id, receiver_user_id
    FROM Solicitudes_Amistad
    WHERE request_id = p_request_id;

    -- Actualizar el estado de la solicitud a 'aceptado'
    UPDATE Solicitudes_Amistad
    SET status = 'aceptado'
    WHERE request_id = p_request_id;

    -- Insertar la relación de amistad en la tabla Amigos
    INSERT INTO Amigos (user_id, friend_id)
    VALUES (sender_user_id, receiver_user_id);

    -- Puedes agregar la inversa también si quieres (opcional)
    -- INSERT INTO Amigos (user_id, friend_id)
    -- VALUES (receiver_user_id, sender_user_id);
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `ComentarMensaje` (IN `p_message_id` INT, IN `p_user_id` INT, IN `p_content` TEXT)   BEGIN
    INSERT INTO Comentarios (message_id, user_id, content, comment_date)
    VALUES (p_message_id, p_user_id, p_content, NOW());
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `CrearUsuario` (IN `p_username` VARCHAR(255), IN `p_email` VARCHAR(255), IN `p_password` VARCHAR(255), IN `p_nombres` VARCHAR(255), IN `p_apellidos` VARCHAR(255))   BEGIN
    INSERT INTO Usuarios (username, email, password, registration_date, nombre, apellido)
    VALUES (p_username, p_email, p_password, NOW(), p_nombres, p_apellidos);
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `EnviarSolicitudAmistad` (IN `p_sender_id` INT, IN `p_receiver_id` INT)   BEGIN
    DECLARE v_status VARCHAR(255);  -- Declarar variable para el estado de la solicitud

    -- Verificar si ya existe una solicitud pendiente entre los usuarios
    SELECT status INTO v_status
    FROM Solicitudes_Amistad
    WHERE (sender_id = p_sender_id AND receiver_id = p_receiver_id)
       OR (sender_id = p_receiver_id AND receiver_id = p_sender_id);

    -- Si no existe, insertar una nueva solicitud
    IF v_status IS NULL THEN
        INSERT INTO Solicitudes_Amistad (sender_id, receiver_id, status)
        VALUES (p_sender_id, p_receiver_id, 'pendiente');
    END IF;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `LoginUsuario` (IN `p_username` VARCHAR(255), IN `p_password` VARCHAR(255), OUT `p_LoginExitoso` BIT)   BEGIN
    -- Inicializar la variable de salida
    SET p_LoginExitoso = 0;

    -- Verificar si las credenciales son válidas
    IF EXISTS (
        SELECT 1
        FROM Usuarios
        WHERE username = p_username AND password = p_password AND estado = 'A'
    ) THEN
        -- Las credenciales son válidas
        SET p_LoginExitoso = 1;
    END IF;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `ObtenerDatosUsuario` (IN `p_input_username` VARCHAR(255), OUT `p_output_username` VARCHAR(255), OUT `p_nombre` VARCHAR(255), OUT `p_apellido` VARCHAR(255))   BEGIN
    -- Inicializar las variables de salida
    SET p_output_username = NULL;
    SET p_nombre = NULL;
    SET p_apellido = NULL;

    -- Verificar si el usuario existe
    IF EXISTS (
        SELECT 1
        FROM Usuarios
        WHERE username = p_input_username
    ) THEN
        -- Obtener los datos del usuario
        SELECT username, nombre, apellido
        INTO p_output_username, p_nombre, p_apellido
        FROM Usuarios
        WHERE username = p_input_username;
    END IF;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `ObtenerMensajesForo` ()   BEGIN
    SELECT * FROM Mensajes;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `ObtenerMensajesUsuario` (IN `p_user_id` INT)   BEGIN
    SELECT *
    FROM Mensajes
    WHERE user_id = p_user_id;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `PublicarMensaje` (IN `p_user_id` INT, IN `p_content` TEXT)   BEGIN
    INSERT INTO Mensajes (user_id, content, publish_date)
    VALUES (p_user_id, p_content, NOW());
END$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `amigos`
--

CREATE TABLE `amigos` (
  `friendship_id` int(11) NOT NULL,
  `user_id` int(11) DEFAULT NULL,
  `friend_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `amigos`
--

INSERT INTO `amigos` (`friendship_id`, `user_id`, `friend_id`) VALUES
(1, NULL, NULL),
(2, NULL, NULL),
(3, NULL, NULL),
(4, NULL, NULL),
(5, NULL, NULL);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `comentarios`
--

CREATE TABLE `comentarios` (
  `comment_id` int(11) NOT NULL,
  `message_id` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `content` text DEFAULT NULL,
  `comment_date` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `comentarios`
--

INSERT INTO `comentarios` (`comment_id`, `message_id`, `user_id`, `content`, `comment_date`) VALUES
(1, 1, 3, 'Contestacion del mensaje de prueba', '2024-01-05 23:20:25');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `mensajes`
--

CREATE TABLE `mensajes` (
  `message_id` int(11) NOT NULL,
  `user_id` int(11) DEFAULT NULL,
  `content` text DEFAULT NULL,
  `publish_date` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `mensajes`
--

INSERT INTO `mensajes` (`message_id`, `user_id`, `content`, `publish_date`) VALUES
(1, 3, 'Este es un mensaje de prueba', '2024-01-05 23:12:47');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `perfiles`
--

CREATE TABLE `perfiles` (
  `profile_id` int(11) NOT NULL,
  `user_id` int(11) DEFAULT NULL,
  `profile_picture` varchar(255) DEFAULT NULL,
  `privacy_settings` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `registros_acceso`
--

CREATE TABLE `registros_acceso` (
  `log_id` int(11) NOT NULL,
  `user_id` int(11) DEFAULT NULL,
  `ip_address` varchar(255) DEFAULT NULL,
  `login_date` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `solicitudes_amistad`
--

CREATE TABLE `solicitudes_amistad` (
  `request_id` int(11) NOT NULL,
  `sender_id` int(11) DEFAULT NULL,
  `receiver_id` int(11) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `solicitudes_amistad`
--

INSERT INTO `solicitudes_amistad` (`request_id`, `sender_id`, `receiver_id`, `status`) VALUES
(5, 2, 3, 'pendiente');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tokens_sesion`
--

CREATE TABLE `tokens_sesion` (
  `token_id` int(11) NOT NULL,
  `user_id` int(11) DEFAULT NULL,
  `token` varchar(255) DEFAULT NULL,
  `expiration_date` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuarios`
--

CREATE TABLE `usuarios` (
  `user_id` int(11) NOT NULL,
  `username` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `registration_date` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `estado` char(1) DEFAULT 'A',
  `nombre` varchar(255) DEFAULT NULL,
  `apellido` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `usuarios`
--

INSERT INTO `usuarios` (`user_id`, `username`, `email`, `password`, `registration_date`, `estado`, `nombre`, `apellido`) VALUES
(2, 'john_doe', 'john.doe@example.com', 'password123', '2024-01-06 18:59:09', 'A', 'Luis', 'Pilco'),
(3, 'milena_mariscal', 'mileena@example.com', 'password321', '2024-01-06 18:59:09', 'A', 'Patricia', 'Ponce'),
(4, 'gdfgfd', 'gdfgdfgdfg', 'gfdgfdgd', '2024-01-09 03:30:08', 'A', 'gfdgdf', 'gfdgdfg');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `amigos`
--
ALTER TABLE `amigos`
  ADD PRIMARY KEY (`friendship_id`),
  ADD KEY `user_id` (`user_id`),
  ADD KEY `friend_id` (`friend_id`);

--
-- Indices de la tabla `comentarios`
--
ALTER TABLE `comentarios`
  ADD PRIMARY KEY (`comment_id`),
  ADD KEY `message_id` (`message_id`),
  ADD KEY `user_id` (`user_id`);

--
-- Indices de la tabla `mensajes`
--
ALTER TABLE `mensajes`
  ADD PRIMARY KEY (`message_id`),
  ADD KEY `user_id` (`user_id`);

--
-- Indices de la tabla `perfiles`
--
ALTER TABLE `perfiles`
  ADD PRIMARY KEY (`profile_id`),
  ADD UNIQUE KEY `user_id` (`user_id`);

--
-- Indices de la tabla `registros_acceso`
--
ALTER TABLE `registros_acceso`
  ADD PRIMARY KEY (`log_id`),
  ADD KEY `user_id` (`user_id`);

--
-- Indices de la tabla `solicitudes_amistad`
--
ALTER TABLE `solicitudes_amistad`
  ADD PRIMARY KEY (`request_id`),
  ADD KEY `sender_id` (`sender_id`),
  ADD KEY `receiver_id` (`receiver_id`);

--
-- Indices de la tabla `tokens_sesion`
--
ALTER TABLE `tokens_sesion`
  ADD PRIMARY KEY (`token_id`),
  ADD KEY `user_id` (`user_id`);

--
-- Indices de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  ADD PRIMARY KEY (`user_id`),
  ADD UNIQUE KEY `username` (`username`),
  ADD UNIQUE KEY `email` (`email`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `amigos`
--
ALTER TABLE `amigos`
  MODIFY `friendship_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT de la tabla `comentarios`
--
ALTER TABLE `comentarios`
  MODIFY `comment_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `mensajes`
--
ALTER TABLE `mensajes`
  MODIFY `message_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `perfiles`
--
ALTER TABLE `perfiles`
  MODIFY `profile_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `registros_acceso`
--
ALTER TABLE `registros_acceso`
  MODIFY `log_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `solicitudes_amistad`
--
ALTER TABLE `solicitudes_amistad`
  MODIFY `request_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT de la tabla `tokens_sesion`
--
ALTER TABLE `tokens_sesion`
  MODIFY `token_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  MODIFY `user_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `amigos`
--
ALTER TABLE `amigos`
  ADD CONSTRAINT `amigos_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `usuarios` (`user_id`),
  ADD CONSTRAINT `amigos_ibfk_2` FOREIGN KEY (`friend_id`) REFERENCES `usuarios` (`user_id`);

--
-- Filtros para la tabla `comentarios`
--
ALTER TABLE `comentarios`
  ADD CONSTRAINT `comentarios_ibfk_1` FOREIGN KEY (`message_id`) REFERENCES `mensajes` (`message_id`),
  ADD CONSTRAINT `comentarios_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `usuarios` (`user_id`);

--
-- Filtros para la tabla `mensajes`
--
ALTER TABLE `mensajes`
  ADD CONSTRAINT `mensajes_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `usuarios` (`user_id`);

--
-- Filtros para la tabla `perfiles`
--
ALTER TABLE `perfiles`
  ADD CONSTRAINT `perfiles_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `usuarios` (`user_id`);

--
-- Filtros para la tabla `registros_acceso`
--
ALTER TABLE `registros_acceso`
  ADD CONSTRAINT `registros_acceso_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `usuarios` (`user_id`);

--
-- Filtros para la tabla `solicitudes_amistad`
--
ALTER TABLE `solicitudes_amistad`
  ADD CONSTRAINT `solicitudes_amistad_ibfk_1` FOREIGN KEY (`sender_id`) REFERENCES `usuarios` (`user_id`),
  ADD CONSTRAINT `solicitudes_amistad_ibfk_2` FOREIGN KEY (`receiver_id`) REFERENCES `usuarios` (`user_id`);

--
-- Filtros para la tabla `tokens_sesion`
--
ALTER TABLE `tokens_sesion`
  ADD CONSTRAINT `tokens_sesion_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `usuarios` (`user_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
