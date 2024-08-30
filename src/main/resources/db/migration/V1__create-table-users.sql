create table users(
	id bigserial primary key,
	username varchar(100) not null,
	password varchar(255) not null
);