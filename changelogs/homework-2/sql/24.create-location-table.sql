create table "location"(
	music_id bigint primary key references music(id) on delete cascade,
	path varchar(100) not null unique
);