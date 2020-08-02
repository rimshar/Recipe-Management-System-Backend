-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema Final_Project
-- -----------------------------------------------------
-- Recipe management app for SDA JavaRiga Final Project

-- -----------------------------------------------------
-- Schema Final_Project
--
-- Recipe management app for SDA JavaRiga Final Project
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `Final_Project` DEFAULT CHARACTER SET utf8 COLLATE utf8_bin ;
USE `Final_Project` ;

-- -----------------------------------------------------
-- Table `Final_Project`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Final_Project`.`user` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(85) NOT NULL,
  `surname` VARCHAR(85) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Final_Project`.`recipe`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Final_Project`.`recipe` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `instructions` TEXT(10000) NULL,
  `link` VARCHAR(245) NULL,
  `owner_id` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_recipe_user1_idx` (`owner_id` ASC) VISIBLE,
  CONSTRAINT `fk_recipe_user1`
    FOREIGN KEY (`owner_id`)
    REFERENCES `Final_Project`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Final_Project`.`ingredient`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Final_Project`.`ingredient` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Final_Project`.`measurement_unit`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Final_Project`.`measurement_unit` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Final_Project`.`recipe_ingredient`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Final_Project`.`recipe_ingredient` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `recipe_id` INT UNSIGNED NOT NULL,
  `ingredient_id` INT UNSIGNED NOT NULL,
  `quantity` DECIMAL(10) NOT NULL,
  `measurement_unit_id` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`id`, `recipe_id`, `ingredient_id`),
  INDEX `fk_recipe_has_ingredient_ingredient1_idx` (`ingredient_id` ASC) VISIBLE,
  INDEX `fk_recipe_has_ingredient_recipe_idx` (`recipe_id` ASC) VISIBLE,
  INDEX `fk_recipe_has_ingredient_measurement_units1_idx` (`measurement_unit_id` ASC) VISIBLE,
  CONSTRAINT `fk_recipe_has_ingredient_recipe`
    FOREIGN KEY (`recipe_id`)
    REFERENCES `Final_Project`.`recipe` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_recipe_has_ingredient_ingredient1`
    FOREIGN KEY (`ingredient_id`)
    REFERENCES `Final_Project`.`ingredient` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_recipe_has_ingredient_measurement_units1`
    FOREIGN KEY (`measurement_unit_id`)
    REFERENCES `Final_Project`.`measurement_unit` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Final_Project`.`ingredient_has_measurement_unit`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Final_Project`.`ingredient_has_measurement_unit` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `ingredient_id` INT UNSIGNED NOT NULL,
  `measurement_unit_id` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`id`, `ingredient_id`, `measurement_unit_id`),
  INDEX `fk_ingredient_has_measurement_unit_measurement_unit1_idx` (`measurement_unit_id` ASC) VISIBLE,
  INDEX `fk_ingredient_has_measurement_unit_ingredient1_idx` (`ingredient_id` ASC) VISIBLE,
  CONSTRAINT `fk_ingredient_has_measurement_unit_ingredient1`
    FOREIGN KEY (`ingredient_id`)
    REFERENCES `Final_Project`.`ingredient` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_ingredient_has_measurement_unit_measurement_unit1`
    FOREIGN KEY (`measurement_unit_id`)
    REFERENCES `Final_Project`.`measurement_unit` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

INSERT INTO user(name, surname, email, password)
VALUES('Roberts', 'Rimsa', 'rimshar@test.com', 'testpassword'),
('Janis', 'Vilkajs', 'jayvilkajs@test.com', 'beggingtobehacked'),
('Raimonds', 'Liepins', 'raimonds666@test.com', 'sounencriptedwow');

INSERT INTO ingredient(name)
VALUES('cheese'),
('milk'),
('egg');

INSERT INTO measurement_unit(name)
VALUES('gram'),
('piece'),
('cup');

INSERT INTO recipe(name, instructions, link, user_id)
VALUES('omelette', 'whip up the egg and milk and just cook it on a pan', 'https://www.incredibleegg.org/recipe/basic-french-omelet/', 1),
('omelette au fromage', 'take the eggs,
smash em,
add the milk,
whip it,
sprinkle with grated cheese', 'https://www.food.com/recipe/cheese-omelette-omelette-au-fromage-271739', 2),
('boiled egg', 'put egg in water,
boil the water,
voila!', 'https://www.simplyrecipes.com/recipes/how_to_make_perfect_hard_boiled_eggs/', 3);
