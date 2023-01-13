create table music_in_album(
	music_id bigint references music(id) on delete cascade not null,
	album_id bigint references album(id) on delete cascade not null,
	primary key(music_id, album_id)
);
