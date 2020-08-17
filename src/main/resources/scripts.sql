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



-- Table `Final_Project`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Final_Project`.`user` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(85) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `password` VARCHAR(300) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE,
  UNIQUE INDEX `username_UNIQUE` (`username` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Final_Project`.`recipe`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Final_Project`.`recipe` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `instructions` TEXT(10000) NULL,
  `link` VARCHAR(245) NULL,
  `picture_link` VARCHAR(245) NULL,
  `user_id` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_recipe_user1_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `fk_recipe_user1`
    FOREIGN KEY (`user_id`)
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
  PRIMARY KEY (`id`),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Final_Project`.`measurement_unit`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Final_Project`.`measurement_unit` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC) VISIBLE)
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
-- Table `Final_Project`.`role`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Final_Project`.`role` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `role` VARCHAR(90) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `role_UNIQUE` (`role` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Final_Project`.`user_has_role`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Final_Project`.`user_has_role` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `user_id` INT UNSIGNED NOT NULL,
  `role_id` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`id`, `user_id`, `role_id`),
  INDEX `fk_user_has_role_role1_idx` (`role_id` ASC) VISIBLE,
  INDEX `fk_user_has_role_user1_idx` (`user_id` ASC) VISIBLE,
  UNIQUE INDEX `user_id_UNIQUE` (`user_id` ASC) VISIBLE,
  CONSTRAINT `fk_user_has_role_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `Final_Project`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_has_role_role1`
    FOREIGN KEY (`role_id`)
    REFERENCES `Final_Project`.`role` (`id`)
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



SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;



INSERT INTO user(username, email, password)
VALUES('Roberts', 'rimshar@test.com', '$2a$10$X8T3Yy.BxAFS2UGOftGsZuUqQ818bjI3qne9KoaqLejdx8WSKKQYe'),
('Janis', 'jayvilkajs@test.com', '$2a$10$zNTPiMA1fxSOJp631qhCfOXQBL1sBMupKIv9MFpg/PavkM.Jr4oyS'),
('Raimonds', 'raimonds666@test.com', '$2a$10$jDbV/nmDijud0hwLqRBnPuIzJYGwY8AUVPeZq8U3RGEHl.JsrS5jG'),
('Oskars', 'oskarssssszzzz@test.com', '$2a$10$XjY9zcN4VeIput1iQcl8IulHwflIxMnqWPXPIZN97JYvYBG0Hmx5i');

INSERT INTO ingredient(name)
VALUES('cheese'),
('milk'),
('egg');

INSERT INTO measurement_unit(name)
VALUES('gram'),
('piece'),
('cup'),
('millilitre');

INSERT INTO recipe(name, instructions, link, user_id, picture_link)
VALUES('omelette', 'whip up the egg and milk and just cook it on a pan', 'https://www.incredibleegg.org/recipe/basic-french-omelet/', 1, 'https://www.healthyfood.com/wp-content/uploads/2018/02/Basic-omelette-1024x656.jpg'),
('omelette au fromage', 'take the eggs,
smash em,
add the milk,
whip it,
sprinkle with grated cheese', 'https://www.food.com/recipe/cheese-omelette-omelette-au-fromage-271739', 2, 'https://food-images.files.bbci.co.uk/food/recipes/cheeseomelette_80621_16x9.jpg'),
('boiled egg', 'put egg in water,
boil the water,
voila!', 'https://www.simplyrecipes.com/recipes/how_to_make_perfect_hard_boiled_eggs/', 3, 'https://minimalistbaker.com/wp-content/uploads/2020/01/Hard-Boiled-Eggs-SQUARE.jpg');

INSERT INTO ingredient_has_measurement_unit(ingredient_id, measurement_unit_id)
VALUES (1, 1),
(2, 3),
(3, 2);

INSERT INTO recipe_ingredient(recipe_id, ingredient_id, measurement_unit_id, quantity)
VALUES (1, 3, 2, 3),
(1, 2, 4, 50),
(2, 3, 2, 3),
(2, 2, 4, 50),
(2, 1, 1, 50),
(3, 3, 2, 1);

INSERT INTO role(role)
VALUES('ROLE_USER'),
('ROLE_ADMIN');


INSERT INTO user_has_role(user_id, role_id)
VALUES(1, 1),
(2, 2),
(3, 2),
(4, 2);


CREATE VIEW v_ingredients_in_recipe
            		(
            		 id,
            		 name,
                     username,
            		 ingredient_count,
                     picture_link
            		)
            AS
            	SELECT
            		r.id,
            		r.name,
                    u.username,
            		COUNT(i.id),
                    r.picture_link

            	FROM recipe r
            	     INNER JOIN recipe_ingredient i ON r.id = i.recipe_id
                     INNER JOIN user u ON r.user_id = u.id
            	GROUP BY r.id
                ORDER BY COUNT(i.id) DESC;
