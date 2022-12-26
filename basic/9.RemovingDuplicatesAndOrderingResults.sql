CREATE SCHEMA cd;

create table cd.members(
    surname varchar(200) not null
);
 
insert into cd.members(surname) values('GUEST');
insert into cd.members(surname) values('Smith');
insert into cd.members(surname) values('Smith');
insert into cd.members(surname) values('Rownam');
insert into cd.members(surname) values('Joplette');
insert into cd.members(surname) values('Butters');
insert into cd.members(surname) values('Tracy');
insert into cd.members(surname) values('Dare');
insert into cd.members(surname) values('Boothe');
insert into cd.members(surname) values('Stibbons');
insert into cd.members(surname) values('Owen');
insert into cd.members(surname) values('Jones');
insert into cd.members(surname) values('Baker');
insert into cd.members(surname) values('Farrell');
insert into cd.members(surname) values('Smith');
insert into cd.members(surname) values('Bader');
insert into cd.members(surname) values('Baker');
insert into cd.members(surname) values('Pinker');
insert into cd.members(surname) values('Genting');
insert into cd.members(surname) values('Mackenzie');
insert into cd.members(surname) values('Coplin');
insert into cd.members(surname) values('Sarwin');
insert into cd.members(surname) values('Jones');
insert into cd.members(surname) values('Rumney');
insert into cd.members(surname) values('Farrell');
insert into cd.members(surname) values('Worthington-Smyth');
insert into cd.members(surname) values('Purview');
insert into cd.members(surname) values('Tupperware');
insert into cd.members(surname) values('Hunt');
insert into cd.members(surname) values('Crumpet');
insert into cd.members(surname) values('Smith');


select DISTINCT surname from cd.members order by surname LIMIT 10;