ALTER TABLE music_in_album RENAME TO songs_in_albums;

ALTER TABLE songs_in_albums RENAME COLUMN music_id TO song_id;