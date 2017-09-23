SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mineiro
-- -----------------------------------------------------
USE `mineiro` ;

-- -----------------------------------------------------
-- Table `mineiro`.`Usuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mineiro`.`Usuario` (
  `usuario_id` INT NOT NULL AUTO_INCREMENT,
  `matricula` VARCHAR(45) NOT NULL,
  `nome` VARCHAR(100) NULL,
  `senha` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`usuario_id`),
  UNIQUE INDEX `matricula_UNIQUE` (`matricula` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mineiro`.`Grupo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mineiro`.`Grupo` (
  `grupo_id` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(100) NOT NULL,
  `sigla` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`grupo_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mineiro`.`UsuarioGrupo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mineiro`.`UsuarioGrupo` (
  `usuario_id` INT NOT NULL,
  `grupo_id` INT NOT NULL,
  PRIMARY KEY (`usuario_id`, `grupo_id`),
  INDEX `fk_Usuario_has_Grupo_Grupo1_idx` (`grupo_id` ASC),
  INDEX `fk_Usuario_has_Grupo_Usuario_idx` (`usuario_id` ASC),
  CONSTRAINT `fk_Usuario_has_Grupo_Usuario`
    FOREIGN KEY (`usuario_id`)
    REFERENCES `mineiro`.`Usuario` (`usuario_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Usuario_has_Grupo_Grupo1`
    FOREIGN KEY (`grupo_id`)
    REFERENCES `mineiro`.`Grupo` (`grupo_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
