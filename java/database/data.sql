BEGIN TRANSACTION;

INSERT INTO users(username, password_hash, email, city, role) VALUES ('user','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','miranda.lieblein@gmail.com', 
																	  'Burlington','ROLE_USER');
INSERT INTO users(username, password_hash, email, city, role) VALUES ('admin','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','tennis@aol.com',
																	  'Chicago', 'ROLE_ADMIN');

INSERT INTO groups(group_name, created_by, city, location, is_public) VALUES ('Volley Girls', 1, 'Burlington', '45 Tennis Ct, South Burlington, VT 05403', false);
INSERT INTO groups(group_name, created_by, city, location, is_public) VALUES ('Chicago LOVE', 2, 'Chicago', '123 Main Ave, Chicago, IL 56792', true);

INSERT INTO events(event_name, description, start_time, location, created_by) VALUES ('Partner Practice', 'Take the opportunity to practice with different
																								  partners from the club!', '2023-06-10 15:00:00', 'Tennis Club House', 1); 
																								  
INSERT INTO 





COMMIT TRANSACTION;
