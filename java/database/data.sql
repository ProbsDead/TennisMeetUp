BEGIN TRANSACTION;

INSERT INTO users(username, password_hash, first_name, last_name, email, city, role) VALUES ('user','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC', 'miranda', 
																							 'lieblein', 'miranda.lieblein@gmail.com', 'Burlington','ROLE_USER');
INSERT INTO users(username, password_hash, first_name, last_name, email, city, role) VALUES ('admin','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC', 'tennis', 
																							 'player','tennis@aol.com','Chicago', 'ROLE_USER');

INSERT INTO groups(group_name, created_by, city, location, is_public) VALUES ('Volley Girls', 1, 'Burlington', '45 Tennis Ct, South Burlington, VT 05403', false);
INSERT INTO groups(group_name, created_by, city, location, is_public) VALUES ('Chicago LOVE', 2, 'Chicago', '123 Main Ave, Chicago, IL 56792', true);
INSERT INTO groups(group_name, created_by, city, location, is_public) VALUES ('Atlanta Newbies', 1, 'Atlanta', '1689 Court Road, North Briarcliff, GA 30879', true);

INSERT INTO events(event_name, description, start_time, location, created_by) VALUES ('Partner Practice', 'Take the opportunity to practice with different
																								  partners from the club!', '2023-06-10 15:00:00', 'Tennis Club House', 2);
INSERT INTO events(event_name, description, start_time, location, created_by) VALUES ('Casual Learning Session', 'Beginner levels welcomed!', '2023-05-30 10:00:00', 'Georgia Tech Tennis Court', 1);
INSERT INTO events(event_name, description, start_time, location, created_by) VALUES ('Practice for upcoming contest', 'Come for a SLAMMIN GOOD TIME and be sure to have fun and get GUD! We are having beers after practice', 
																					  '2023-05-18 08:00:00', 'Tennis Club House', 1);

--> user 1 is an Admin for group 1 
--> user 2 is an Admin for group 2 and a Member of group 1
INSERT INTO groups_player(group_id, user_id, role) VALUES (1, 1, 'ROLE_ADMIN');
INSERT INTO groups_player(group_id, user_id, role) VALUES (2, 2, 'ROLE_ADMIN');
INSERT INTO groups_player(group_id, user_id, role) VALUES (1, 2, 'ROLE_MEMBER');

INSERT INTO groups_events(event_id, group_id) VALUES (1,1);
INSERT INTO groups_events(event_id, group_id) VALUES (2,2);
INSERT INTO groups_events(event_id, group_id) VALUES (3,1);

--> add request later (or add from frontend once that's there)

INSERT INTO user_event(user_id, event_id) VALUES(1, 3);
INSERT INTO user_event(user_id, event_id) VALUES(2, 3);
INSERT INTO user_event(user_id, event_id) VALUES(1, 1);


INSERT INTO match(event_id, score, winner, match_length) VALUES(3, '3 : 1', 1, 150);

--> for match_1, two users, user1 and user2 will be participating
INSERT INTO match_user(user_id, match_id) VALUES(1, 1);
INSERT INTO match_user(user_id, match_id) VALUES(2, 1);


COMMIT TRANSACTION;
