create table saved_album(
	account_id bigint references account(id) on delete cascade not null,
	album_id bigint references album(id) on delete cascade not null,
	primary key(account_id, album_id)
);
