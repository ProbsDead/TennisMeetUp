BEGIN TRANSACTION;

DROP TABLE IF EXISTS users_ingredients, users_recipes, users, ingredients, recipes, meal_plan, 
meal_plan_recipes, users_meal_plan, grocery_list,recipe_ingredients CASCADE;

CREATE TABLE users (
	user_id SERIAL,
	username varchar(50) NOT NULL UNIQUE,
	password_hash varchar(200) NOT NULL,
	role varchar(50) NOT NULL,
	
	CONSTRAINT PK_user PRIMARY KEY (user_id)
);

COMMIT TRANSACTION;
