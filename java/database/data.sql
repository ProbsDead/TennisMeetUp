BEGIN TRANSACTION;

INSERT INTO users(username, password_hash, first_name, last_name, email, state, city, role) VALUES ('user','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC', 'miranda', 
																							 'lieblein', 'miranda.lieblein@gmail.com', 'VT', 'Burlington','ROLE_USER');
INSERT INTO users(username, password_hash, first_name, last_name, email,state, city, role) VALUES ('admin','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC', 'tennis', 
																							 'player','tennis@aol.com','IL', 'Chicago', 'ROLE_USER');

INSERT INTO groups(group_name, created_by, city, state, location, is_public, about) VALUES ('Volley Girls', 1, 'Burlington', 'VT','45 Tennis Ct, South Burlington, VT 05403', false, 'This is a group for women and girls
																					 who love tennis and are looking for a casual to competative environment they can hone their skills.');
INSERT INTO groups(group_name, created_by, city, state, location, is_public, about) VALUES ('Chicago LOVE', 2, 'Chicago', 'IL', '123 Main Ave, Chicago, IL 56792', true, 'Lets bring tennis to the inner city and make it accessible
																					 to everyone');
INSERT INTO groups(group_name, created_by, city, state, location, is_public, about) VALUES ('Atlanta Newbies', 1, 'Atlanta', 'GA', '1689 Court Road, North Briarcliff, GA 30879', true, 'Just starting to learn? We can help! We meet
																					 every week to practice, give tips, and meet other newbies.');

INSERT INTO events(event_name, description, start_time, end_time, location, created_by) VALUES ('Partner Practice', 'Take the opportunity to practice with different partners from the club!', '2023-06-30 15:00:00', '2023-06-30 17:00:00', 'Tennis Club House', 2);
INSERT INTO events(event_name, description, start_time, end_time, location, created_by) VALUES ('Casual Learning Session', 'Beginner levels welcomed!', '2023-07-12 10:00:00', '2023-07-12 12:00:00','Georgia Tech Tennis Court', 1);
INSERT INTO events(event_name, description, start_time, end_time, location, created_by) VALUES ('Practice for upcoming contest', 'Come for a SLAMMIN GOOD TIME and be sure to have fun and get GUD! We are having beers after practice', 
																					  '2023-05-18 08:00:00', '2023-05-18 10:00:00', 'Tennis Club House', 1);

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

INSERT INTO requests (group_id, joining_user_id, status, invite_or_request) VALUES (1,2,'Pending','Request');
INSERT INTO requests (group_id, joining_user_id, admin_user_id, status, invite_or_request) VALUES (1,1,1,'Approved', 'Request');


COMMIT TRANSACTION;
