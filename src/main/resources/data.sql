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
