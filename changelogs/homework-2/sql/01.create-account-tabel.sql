create table account(
	id bigint primary key generated by default as identity,
	nickname varchar(50) not null unique,
	registration_date date not null,
	role varchar(20)
);