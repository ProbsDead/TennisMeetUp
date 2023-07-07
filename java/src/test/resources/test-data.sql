BEGIN TRANSACTION;

INSERT INTO users (first_name, last_name, username, password_hash, email, state, city, role, goal) VALUES ('first1', 'last1','user1','user1', 'email1', 'NY', 'city1', 'ROLE_USER', 'goal1');
INSERT INTO users (first_name, last_name, username, password_hash, email, state, city, role, goal) VALUES ('first2', 'last2', 'user2','user2', 'email2','VT', 'city2', 'ROLE_USER', 'goal2');
INSERT INTO users (first_name, last_name, username, password_hash, email, state, city, role, goal) VALUES ('first3', 'last3', 'user3','user3', 'email3', 'MI', 'city3', 'ROLE_USER', 'goal3');

INSERT INTO groups (group_id, group_name, city, state, location, created_by, is_public, about, group_image, image_type) VALUES (1, 'Volley Girls', 'Burlington', 'VT', '45 Tennis Ct, South Burlington, VT 05403', 1, false,
    'This is a group for women and girls who love tennis and are looking for a casual to competative environment they can hone their skills.',NULL, NULL);

INSERT INTO groups (group_id, group_name, city, state, location, created_by, is_public, about, group_image, image_type) VALUES (2, 'Chicago LOVE', 'Chicago', 'IL', '123 Main Ave, Chicago, IL 56792', 2, true,
    'Lets bring tennis to the inner city and make it accessible to everyone', NULL, NULL);

INSERT INTO groups (group_id, group_name, city, state, location, created_by, is_public, about, group_image, image_type) VALUES (3, 'Atlanta Newbies', 'Atlanta', 'GA', '1689 Court Road, North Briarcliff, GA 30879', 1, true,
    'Just starting to learn? We can help! We meet every week to practice, give tips, and meet other newbies.', NULL, NULL);


COMMIT TRANSACTION;
