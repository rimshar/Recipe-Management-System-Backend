INSERT INTO user(username, email, password)
VALUES('rimshar', 'rimshar@test.com', 'testpassword'),
('JV', 'jayvilkajs@test.com', 'beggingtobehacked'),
('raimunal', 'raimonds666@test.com', 'sounencriptedwow'),
('hasan', 'oscar@test.com', 'seriouspassword');

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
