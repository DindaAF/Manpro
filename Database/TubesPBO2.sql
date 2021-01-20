-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema TubesPBO2
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `TubesPBO2` ;

-- -----------------------------------------------------
-- Schema TubesPBO2
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `TubesPBO2` DEFAULT CHARACTER SET utf8 ;
USE `TubesPBO2` ;

-- -----------------------------------------------------
-- Table `TubesPBO2`.`User`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `TubesPBO2`.`User` (
  `IdUser` VARCHAR(10) NOT NULL,
  `NamaUser` VARCHAR(100) NOT NULL,
  `Username` VARCHAR(50) NOT NULL,
  `Password` VARCHAR(50) NOT NULL,
  `Saldo` INT NOT NULL,
  `Role` VARCHAR(10) NULL,
  PRIMARY KEY (`IdUser`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `TubesPBO2`.`Film`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `TubesPBO2`.`Film` (
  `IdFilm` VARCHAR(50) NOT NULL,
  `Judul` VARCHAR(100) NOT NULL,
  `Rilis` VARCHAR(10) NOT NULL,
  `Deskripsi` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`IdFilm`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `TubesPBO2`.`Studio`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `TubesPBO2`.`Studio` (
  `IdStudio` INT NOT NULL AUTO_INCREMENT,
  `JamTayang` VARCHAR(100) NOT NULL,
  `Harga` INT NOT NULL,
  `NamaStudio` VARCHAR(45) NOT NULL,
  `Film_IdFilm` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`IdStudio`, `Film_IdFilm`),
  INDEX `fk_Studio_Film1_idx` (`Film_IdFilm` ASC) ,
  CONSTRAINT `fk_Studio_Film1`
    FOREIGN KEY (`Film_IdFilm`)
    REFERENCES `TubesPBO2`.`Film` (`IdFilm`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `TubesPBO2`.`Ticket`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `TubesPBO2`.`Ticket` (
  `IdTicket` INT NOT NULL AUTO_INCREMENT,
  `User_IdUser` VARCHAR(10) NOT NULL,
  `Studio_IdStudio` INT NOT NULL,
  `Studio_Film_IdFilm` VARCHAR(50) NOT NULL,
  `Jam` VARCHAR(45) NOT NULL,
  `harga` INT NOT NULL,
  `jumlahpesan` INT NULL,
  `totalbayar` INT NULL,
  PRIMARY KEY (`IdTicket`, `User_IdUser`, `Studio_IdStudio`, `Studio_Film_IdFilm`),
  INDEX `fk_Transaksi_User1_idx` (`User_IdUser` ASC) ,
  INDEX `fk_Transaksi_Studio1_idx` (`Studio_IdStudio` ASC, `Studio_Film_IdFilm` ASC) ,
  CONSTRAINT `fk_Transaksi_User1`
    FOREIGN KEY (`User_IdUser`)
    REFERENCES `TubesPBO2`.`User` (`IdUser`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Transaksi_Studio1`
    FOREIGN KEY (`Studio_IdStudio` , `Studio_Film_IdFilm`)
    REFERENCES `TubesPBO2`.`Studio` (`IdStudio` , `Film_IdFilm`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `TubesPBO2`.`User`
-- -----------------------------------------------------
START TRANSACTION;
USE `TubesPBO2`;
INSERT INTO `TubesPBO2`.`User` (`IdUser`, `NamaUser`, `Username`, `Password`, `Saldo`, `Role`) VALUES ('m-001', 'hosea', 'hosea', 'hosea', 2000000, 'member');
INSERT INTO `TubesPBO2`.`User` (`IdUser`, `NamaUser`, `Username`, `Password`, `Saldo`, `Role`) VALUES ('m-002', 'yolanda', 'yolan', 'yolan123', 200000, 'member');
INSERT INTO `TubesPBO2`.`User` (`IdUser`, `NamaUser`, `Username`, `Password`, `Saldo`, `Role`) VALUES ('m-003', 'dinda', 'dinda', 'dinda', 50000, 'member');
INSERT INTO `TubesPBO2`.`User` (`IdUser`, `NamaUser`, `Username`, `Password`, `Saldo`, `Role`) VALUES ('a-001', 'admin', 'admin', 'admin', 0, 'admin');

COMMIT;


-- -----------------------------------------------------
-- Data for table `TubesPBO2`.`Film`
-- -----------------------------------------------------
START TRANSACTION;
USE `TubesPBO2`;
INSERT INTO `TubesPBO2`.`Film` (`IdFilm`, `Judul`, `Rilis`, `Deskripsi`) VALUES ('21-001', 'WW2', '2020', ' Fantasy');
INSERT INTO `TubesPBO2`.`Film` (`IdFilm`, `Judul`, `Rilis`, `Deskripsi`) VALUES ('21-002', 'Dilan', '2019', 'Romance');
INSERT INTO `TubesPBO2`.`Film` (`IdFilm`, `Judul`, `Rilis`, `Deskripsi`) VALUES ('21-003', 'Frozen', '2020', 'Fantasy');
INSERT INTO `TubesPBO2`.`Film` (`IdFilm`, `Judul`, `Rilis`, `Deskripsi`) VALUES ('21-004', 'CTS', '2019', 'Comedy');
INSERT INTO `TubesPBO2`.`Film` (`IdFilm`, `Judul`, `Rilis`, `Deskripsi`) VALUES ('21-005', 'Susah Sinyal', '2018', 'Comedy');

COMMIT;


-- -----------------------------------------------------
-- Data for table `TubesPBO2`.`Studio`
-- -----------------------------------------------------
START TRANSACTION;
USE `TubesPBO2`;
INSERT INTO `TubesPBO2`.`Studio` (`IdStudio`, `JamTayang`, `Harga`, `NamaStudio`, `Film_IdFilm`) VALUES (1, '12.00-13.30', 50000, 'Studio 1', '21-002');
INSERT INTO `TubesPBO2`.`Studio` (`IdStudio`, `JamTayang`, `Harga`, `NamaStudio`, `Film_IdFilm`) VALUES (DEFAULT, '14.00-15.30', 55000, 'Studio 2', '21-001');
INSERT INTO `TubesPBO2`.`Studio` (`IdStudio`, `JamTayang`, `Harga`, `NamaStudio`, `Film_IdFilm`) VALUES (DEFAULT, '19.00-20.30', 45000, 'Studio 3', '21-004');

COMMIT;

