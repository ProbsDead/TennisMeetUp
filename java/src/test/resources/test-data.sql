BEGIN TRANSACTION;

INSERT INTO users (first_name, last_name, username, password_hash, email, state, city, role) VALUES ('first1', 'last1','user1','user1', 'email1', 'state1', 'city1', 'ROLE_USER');
INSERT INTO users (first_name, last_name, username, password_hash, email, state, city, role) VALUES ('first2', 'last2', 'user2','user2', 'email2','state2', 'city2', 'ROLE_USER');
INSERT INTO users (first_name, last_name, username, password_hash, email, state, city, role) VALUES ('first3', 'last3', 'user3','user3', 'email3', 'state3', 'city3', 'ROLE_USER');

INSERT INTO groups (group_name, created_by, city, location, is_public, about) VALUES ('group1', 1, 'city1', 'location1', true, 'about1');
INSERT INTO groups (group_name, created_by, city, location, is_public, about) VALUES ('group2', 2, 'city2', 'location2', false, 'about2');
INSERT INTO groups (group_name, created_by, city, location, is_public, about) VALUES ('group3', 3, 'city3', 'location3', true, 'about3');

COMMIT TRANSACTION;
