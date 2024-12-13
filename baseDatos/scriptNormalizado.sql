CREATE DATABASE timeFast;
USE timeFast;

CREATE TABLE Persona
(
    id              INT PRIMARY KEY AUTO_INCREMENT,
    nombre          VARCHAR(50),
    apellidoPaterno VARCHAR(50),
    apellidoMaterno VARCHAR(50),
    correo          VARCHAR(100),
    foto            LONGBLOB,
    CURP            VARCHAR(18) UNIQUE
);

CREATE TABLE Direccion
(
    id           INT PRIMARY KEY AUTO_INCREMENT,
    calle        VARCHAR(100),
    numero       VARCHAR(10),
    colonia      VARCHAR(50),
    ciudad       VARCHAR(50),
    estado       VARCHAR(50),
    codigoPostal VARCHAR(10)
);

CREATE TABLE Cliente
(
    id          INT PRIMARY KEY AUTO_INCREMENT,
    idPersona   INT,
    telefono    VARCHAR(15),
    idDireccion INT,
    FOREIGN KEY (idDireccion) REFERENCES Direccion (id) ON DELETE CASCADE,
    FOREIGN KEY (idPersona) REFERENCES Persona (id) ON DELETE CASCADE
);

CREATE TABLE Colaborador
(
    id         INT PRIMARY KEY AUTO_INCREMENT,
    noPersonal INT UNIQUE,
    idPersona  INT,
    contrasena VARCHAR(255),
    FOREIGN KEY (idPersona) REFERENCES Persona (id) ON DELETE CASCADE
);

CREATE TABLE RolColaborador
(
    id            INT PRIMARY KEY AUTO_INCREMENT,
    rol           VARCHAR(50),
    numLicencia   VARCHAR(20),
    idColaborador INT,
    FOREIGN KEY (idColaborador) REFERENCES Colaborador (id) ON DELETE CASCADE
);

CREATE TABLE Unidad
(
    id                INT PRIMARY KEY AUTO_INCREMENT,
    marca             VARCHAR(50),
    modelo            VARCHAR(50),
    anio              INT,
    VIN               VARCHAR(17) UNIQUE,
    numIdentificacion VARCHAR(50) UNIQUE,
    tipo              VARCHAR(50),
    foto              LONGBLOB,
    idConductor       INT,
    FOREIGN KEY (idConductor) REFERENCES Colaborador (id) ON DELETE SET NULL
);

CREATE TABLE Envio
(
    id          INT PRIMARY KEY AUTO_INCREMENT,
    idOrigen    INT,
    idDestino   INT,
    idCliente   INT,
    idConductor INT,
    costo       DECIMAL(10, 2),
    fecha       DATE,
    numGuia     VARCHAR(50) UNIQUE,
    FOREIGN KEY (idOrigen) REFERENCES Direccion (id) ON DELETE CASCADE,
    FOREIGN KEY (idDestino) REFERENCES Direccion (id) ON DELETE CASCADE,
    FOREIGN KEY (idCliente) REFERENCES Cliente (id) ON DELETE CASCADE,
    FOREIGN KEY (idConductor) REFERENCES Colaborador (id) ON DELETE SET NULL
);

CREATE TABLE EstadoEnvio
(
    id          INT PRIMARY KEY AUTO_INCREMENT,
    idEnvio     INT,
    fecha       DATE,
    descripcion VARCHAR(50),
    FOREIGN KEY (idEnvio) REFERENCES Envio(id) ON DELETE CASCADE
);

CREATE TABLE Paquete
(
    id          INT PRIMARY KEY AUTO_INCREMENT,
    idEnvio     INT,
    descripcion TEXT,
    dimensiones VARCHAR(50),
    peso        DECIMAL(10, 2),
    FOREIGN KEY (idEnvio) REFERENCES Envio (id) ON DELETE CASCADE
);
