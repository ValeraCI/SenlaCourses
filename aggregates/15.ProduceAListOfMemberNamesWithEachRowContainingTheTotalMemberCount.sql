CREATE SCHEMA cd;

create table cd.members(
    surname varchar(200) not null,
    firstname varchar(200) not null
);

insert into cd.members(surname, firstname) values('GUEST', 'GUEST');
insert into cd.members(surname, firstname) values('Smith', 'Darren');
insert into cd.members(surname, firstname) values('Smith', 'Tracy');
insert into cd.members(surname, firstname) values('Rownam', 'Tim');
insert into cd.members(surname, firstname) values('Joplette', 'Janice');
insert into cd.members(surname, firstname) values('Butters', 'Gerald');
insert into cd.members(surname, firstname) values('Tracy', 'Burton');
insert into cd.members(surname, firstname) values('Dare', 'Nancy');
insert into cd.members(surname, firstname) values('Boothe', 'Tim');
insert into cd.members(surname, firstname) values('Stibbons', 'Ponder');
insert into cd.members(surname, firstname) values('Owen', 'Charles');
insert into cd.members(surname, firstname) values('Jones', 'David');
insert into cd.members(surname, firstname) values('Baker', 'Anne');
insert into cd.members(surname, firstname) values('Farrell', 'Jemima');
insert into cd.members(surname, firstname) values('Smith', 'Jack');
insert into cd.members(surname, firstname) values('Bader', 'Florence');
insert into cd.members(surname, firstname) values('Baker', 'Timothy');
insert into cd.members(surname, firstname) values('Pinker', 'David');
insert into cd.members(surname, firstname) values('Genting', 'Matthew');
insert into cd.members(surname, firstname) values('Mackenzie', 'Anna');
insert into cd.members(surname, firstname) values('Coplin', 'Joan');
insert into cd.members(surname, firstname) values('Sarwin', 'Ramnaresh');
insert into cd.members(surname, firstname) values('Jones', 'Douglas');
insert into cd.members(surname, firstname) values('Rumney', 'Henrietta');
insert into cd.members(surname, firstname) values('Farrell', 'David');
insert into cd.members(surname, firstname) values('Worthington-Smyth', 'Henry');
insert into cd.members(surname, firstname) values('Purview', 'Millicent');
insert into cd.members(surname, firstname) values('Tupperware', 'Hyacinth');
insert into cd.members(surname, firstname) values('Hunt', 'John');
insert into cd.members(surname, firstname) values('Crumpet', 'Erica');
insert into cd.members(surname, firstname) values('Smith', 'Darren');


select count(*) over(), firstname, surname
from cd.members;

