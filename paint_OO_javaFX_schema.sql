SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema paint-oo
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `paint-oo` ;
CREATE SCHEMA IF NOT EXISTS `paint-oo` DEFAULT CHARACTER SET latin1 ;
USE `paint-oo` ;

-- -----------------------------------------------------
-- Table `paint-oo`.`person`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `paint-oo`.`person` ;

CREATE TABLE IF NOT EXISTS `paint-oo`.`person` (
  `username` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`username`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `paint-oo`.`shape`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `paint-oo`.`shape` ;

CREATE TABLE IF NOT EXISTS `paint-oo`.`shape` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `x1` INT(11) NOT NULL,
  `y1` INT(11) NOT NULL,
  `x2` INT(11) NULL DEFAULT NULL,
  `y2` INT(11) NULL DEFAULT NULL,
  `color` VARCHAR(45) NOT NULL,
  `shapeType` VARCHAR(45) NOT NULL,
  `person_username` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `person_username_idx` (`person_username` ASC),
  CONSTRAINT `person_username`
    FOREIGN KEY (`person_username`)
    REFERENCES `paint-oo`.`person` (`username`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 12
DEFAULT CHARACTER SET = latin1;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
