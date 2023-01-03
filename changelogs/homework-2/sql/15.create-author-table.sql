create table author(
	music_id bigint references music(id) on delete cascade not null,
	account_id bigint references account(id) not null,
	primary key(music_id, account_id)
);