-- MySQL Script generated by MySQL Workbench
-- Mon Jun 14 23:53:45 2021
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema payments_db
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema payments_db
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `payments_db` DEFAULT CHARACTER SET utf8 ;
USE `payments_db` ;

-- -----------------------------------------------------
-- Table `payments_db`.`user_info`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `payments_db`.`user_info` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(60) NULL DEFAULT NULL,
  `last_name` VARCHAR(60) NULL DEFAULT NULL,
  `income` DECIMAL(15,2) NULL DEFAULT NULL,
  `tax_payer_number` VARCHAR(10) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `tax_payer_number_UNIQUE` (`tax_payer_number` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 36
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `payments_db`.`user_type`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `payments_db`.`user_type` (
  `type_id` INT NOT NULL,
  `type` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`type_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `payments_db`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `payments_db`.`user` (
  `user_id` INT NOT NULL AUTO_INCREMENT,
  `login` VARCHAR(60) NOT NULL,
  `password` VARCHAR(40) NOT NULL,
  `is_active` TINYINT NULL DEFAULT NULL,
  `user_type` INT NULL DEFAULT NULL,
  `user_info` INT NULL DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE INDEX `login_UNIQUE` (`login` ASC) VISIBLE,
  INDEX `fk_user_user_type_idx` (`user_type` ASC) VISIBLE,
  INDEX `fk_user_user_info1_idx` (`user_info` ASC) VISIBLE,
  CONSTRAINT `fk_user_user_info1`
    FOREIGN KEY (`user_info`)
    REFERENCES `payments_db`.`user_info` (`id`),
  CONSTRAINT `fk_user_user_type`
    FOREIGN KEY (`user_type`)
    REFERENCES `payments_db`.`user_type` (`type_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 47
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `payments_db`.`bank_card`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `payments_db`.`bank_card` (
  `card_id` INT NOT NULL AUTO_INCREMENT,
  `card_number` VARCHAR(16) NOT NULL,
  `card_iban` VARCHAR(34) NOT NULL,
  `card_balance` DECIMAL(15,2) NOT NULL DEFAULT '0.00',
  `credit_limit_current` DECIMAL(15,2) NOT NULL DEFAULT '0.00',
  `credit_limit_available` DECIMAL(15,2) NOT NULL DEFAULT '0.00',
  `is_blocked` TINYINT NOT NULL DEFAULT '0',
  `start_date` DATE NULL DEFAULT NULL,
  `end_date` DATE NULL DEFAULT NULL,
  `user` INT NOT NULL,
  `is_request_sent` TINYINT NOT NULL DEFAULT '0',
  PRIMARY KEY (`card_id`),
  UNIQUE INDEX `card_iban_UNIQUE` (`card_iban` ASC) VISIBLE,
  UNIQUE INDEX `card_number_UNIQUE` (`card_number` ASC) VISIBLE,
  INDEX `fk_bank_card_user1_idx` (`user` ASC) VISIBLE,
  CONSTRAINT `fk_bank_card_user1`
    FOREIGN KEY (`user`)
    REFERENCES `payments_db`.`user` (`user_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 56
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `payments_db`.`locale`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `payments_db`.`locale` (
  `id_loc` INT NOT NULL,
  `lang_code` VARCHAR(2) NULL DEFAULT NULL,
  PRIMARY KEY (`id_loc`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `payments_db`.`payment_state`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `payments_db`.`payment_state` (
  `payment_state_id` INT NOT NULL AUTO_INCREMENT,
  `state_name` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`payment_state_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `payments_db`.`payment_type`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `payments_db`.`payment_type` (
  `payment_type_id` INT NOT NULL AUTO_INCREMENT,
  `payment_commission` DECIMAL(2,2) NOT NULL DEFAULT '0.00',
  PRIMARY KEY (`payment_type_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 4
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `payments_db`.`payment`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `payments_db`.`payment` (
  `payment_id` INT NOT NULL AUTO_INCREMENT,
  `payment_sum` DECIMAL(15,2) NOT NULL,
  `description` VARCHAR(150) NULL DEFAULT NULL,
  `payment_datetime` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `sender_card` INT NOT NULL,
  `recipient_card` INT NOT NULL,
  `payment_type` INT NOT NULL DEFAULT '1',
  `payment_state` INT NOT NULL DEFAULT '2',
  PRIMARY KEY (`payment_id`),
  INDEX `fk_payment_bank_card1_idx` (`sender_card` ASC) VISIBLE,
  INDEX `fk_payment_bank_card2_idx` (`recipient_card` ASC) VISIBLE,
  INDEX `fk_payment_payment_type1_idx` (`payment_type` ASC) VISIBLE,
  INDEX `fk_payment_payment_state1_idx` (`payment_state` ASC) VISIBLE,
  CONSTRAINT `fk_payment_bank_card1`
    FOREIGN KEY (`sender_card`)
    REFERENCES `payments_db`.`bank_card` (`card_id`),
  CONSTRAINT `fk_payment_bank_card2`
    FOREIGN KEY (`recipient_card`)
    REFERENCES `payments_db`.`bank_card` (`card_id`),
  CONSTRAINT `fk_payment_payment_state1`
    FOREIGN KEY (`payment_state`)
    REFERENCES `payments_db`.`payment_state` (`payment_state_id`),
  CONSTRAINT `fk_payment_payment_type1`
    FOREIGN KEY (`payment_type`)
    REFERENCES `payments_db`.`payment_type` (`payment_type_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 78
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `payments_db`.`payment_type_locale`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `payments_db`.`payment_type_locale` (
  `payment_type_id` INT NOT NULL,
  `locale_id_loc` INT NOT NULL,
  `payment_type` VARCHAR(45) NULL DEFAULT NULL,
  INDEX `fk_payment_type_has_locale_locale1_idx` (`locale_id_loc` ASC) VISIBLE,
  INDEX `fk_payment_type_has_locale_payment_type1_idx` (`payment_type_id` ASC) VISIBLE,
  CONSTRAINT `fk_payment_type_has_locale_locale1`
    FOREIGN KEY (`locale_id_loc`)
    REFERENCES `payments_db`.`locale` (`id_loc`),
  CONSTRAINT `fk_payment_type_has_locale_payment_type1`
    FOREIGN KEY (`payment_type_id`)
    REFERENCES `payments_db`.`payment_type` (`payment_type_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
