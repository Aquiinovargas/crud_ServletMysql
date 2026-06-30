USE actividad02;

CREATE TABLE usuario (
    idUsuario INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL,
    apPaterno VARCHAR(50) NOT NULL,
    apMaterno VARCHAR(50),
    correo VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    estado VARCHAR(10) NOT NULL DEFAULT 'activo'
);
