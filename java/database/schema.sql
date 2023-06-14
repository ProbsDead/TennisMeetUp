BEGIN TRANSACTION;

DROP TABLE IF EXISTS groups_player, groups_events, requests, user_event, match_user, users, groups, events, match CASCADE;

CREATE TABLE users (
	user_id SERIAL,
	first_name varchar(100) NOT NULL,
	last_name varchar(100) NOT NULL,
	username varchar(50) NOT NULL UNIQUE,
	password_hash varchar(200) NOT NULL,
	email varchar(100) NOT NULL,
	state varchar (20) NOT NULL,
	city varchar(100) NOT NULL,
	role varchar(50) NOT NULL,
	goal varchar(500) NULL,
	
	CONSTRAINT PK_user PRIMARY KEY (user_id)
);
CREATE TABLE groups (
	group_id serial,
	group_name varchar (250) NOT NULL,
	city varchar (50) NOT NULL,
	state char(2) NOT NULL,
	location varchar (250) NULL,
	created_by int NOT NULL,
	is_public boolean DEFAULT true,
	about text NULL,
	
	CONSTRAINT PK_groups PRIMARY KEY (group_id)
);

CREATE TABLE groups_player (
	group_id int NOT NULL,
	user_id int NOT NULL,
	role varchar (50) NOT NULL,
	
	CONSTRAINT PK_groups_player PRIMARY KEY (group_id, user_id),
	CONSTRAINT FK_groups_player_group_id FOREIGN KEY (group_id) REFERENCES groups(group_id),
	CONSTRAINT FK_groups_player_user_id FOREIGN KEY (user_id) REFERENCES users (user_id)
);

CREATE TABLE events (
	event_id serial,
	event_name varchar(250) NOT NULL,
	description varchar(300) NOT NULL,
	start_time timestamp NOT NULL,
	end_time timestamp NOT NULL,
	location varchar (250) NOT NULL,
	created_by int NOT NULL,
	
	CONSTRAINT PK_events PRIMARY KEY (event_id)
);
CREATE TABLE groups_events(
	group_id int NOT NULL,
	event_id int NOT NULL,
	
	CONSTRAINT PK_groups_event PRIMARY KEY (group_id, event_id),
	CONSTRAINT FK_groups_event_group_id FOREIGN KEY (group_id) REFERENCES groups(group_id),
	CONSTRAINT FK_groups_event_event_id FOREIGN KEY (event_id) REFERENCES events (event_id)
);

CREATE TABLE requests(
	request_id serial,
	group_id int NOT NULL,
	joining_user_id int NOT NULL,
	admin_user_id int, 
	status varchar(50) NOT NULL,
	invite_or_request varchar(10) NOT NULL,
	
	CONSTRAINT PK_requests PRIMARY KEY (request_id),
	CONSTRAINT FK_requests_group_id FOREIGN KEY (group_id) REFERENCES groups (group_id),
	CONSTRAINT FK_requests_admin_user_id FOREIGN KEY (admin_user_id) REFERENCES users(user_id),
	CONSTRAINT FK_requests_joining_user_id FOREIGN KEY (joining_user_id) REFERENCES users(user_id)
);

CREATE TABLE user_event (
	user_id int NOT NULL,
	event_id int NOT NULL,
	
	CONSTRAINT PK_user_event PRIMARY KEY (user_id, event_id),
	CONSTRAINT FK_user_event_user_id FOREIGN KEY (user_id) REFERENCES users(user_id),
	CONSTRAINT FK_user_event_event_id FOREIGN KEY (event_id) REFERENCES events (event_id)
);

CREATE TABLE match (
	match_id serial,
	event_id int NOT NULL,
	score varchar(50),
	winner int,
	winner_two int,
	match_length int NULL,
	
	CONSTRAINT PK_match PRIMARY KEY (match_id),
	CONSTRAINT FK_match_event_id FOREIGN KEY (event_id) REFERENCES events (event_id)
);

CREATE TABLE match_user (
	user_id int NOT NULL,
	match_id int NOT NULL,
	
	CONSTRAINT PK_match_user PRIMARY KEY (user_id, match_id),
	CONSTRAINT FK_match_user_user_id FOREIGN KEY (user_id) REFERENCES users (user_id),
	CONSTRAINT FK_match_user_match_id FOREIGN KEY (match_id) REFERENCES match (match_id)
);

COMMIT TRANSACTION;
