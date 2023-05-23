BEGIN TRANSACTION;

INSERT INTO users (first_name, last_name, username, password_hash, email, city, role) VALUES ('first1', 'last1','user1','user1', 'email1', 'city1', 'ROLE_USER');
INSERT INTO users (first_name, last_name, username, password_hash, email, city, role) VALUES ('first2', 'last2', 'user2','user2', 'email2', 'city2', 'ROLE_USER');
INSERT INTO users (first_name, last_name, username, password_hash, email, city, role) VALUES ('first3', 'last3', 'user3','user3', 'email3', 'city3', 'ROLE_USER');

COMMIT TRANSACTION;
