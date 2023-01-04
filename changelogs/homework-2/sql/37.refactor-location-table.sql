ALTER TABLE "location" RENAME TO locations;

ALTER TABLE locations RENAME COLUMN music_id TO song_id;

UPDATE locations
SET path = REPLACE(path, 'M:', '.');