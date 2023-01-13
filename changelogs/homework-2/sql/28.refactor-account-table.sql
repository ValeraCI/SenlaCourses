ALTER TABLE account RENAME TO accounts;

ALTER TABLE accounts 
ADD COLUMN role_id int references roles(id) not null 
default 3;

UPDATE accounts SET role_id=1 WHERE role='owner';
UPDATE accounts SET role_id=2 WHERE role='administrator';

ALTER TABLE accounts DROP COLUMN role;
