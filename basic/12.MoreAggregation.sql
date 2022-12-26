CREATE SCHEMA cd;

create table cd.members(
    surname varchar(200) not null,
    firstname varchar(200) not null,
    joindate timestamp not null
);

insert into cd.members(surname, firstname, joindate) values('GUEST', 'GUEST', '2012-07-01 00:00:00');
insert into cd.members(surname, firstname, joindate) values('Smith', 'Darren', '2012-07-02 12:02:05');
insert into cd.members(surname, firstname, joindate) values('Smith', 'Tracy', '2012-07-02 12:08:23');
insert into cd.members(surname, firstname, joindate) values('Rownam', 'Tim', '2012-07-03 09:32:15');
insert into cd.members(surname, firstname, joindate) values('Joplette', 'Janice', '2012-07-03 10:25:05');
insert into cd.members(surname, firstname, joindate) values('Butters', 'Gerald', '2012-07-09 10:44:09');
insert into cd.members(surname, firstname, joindate) values('Tracy', 'Burton', '2012-07-15 08:52:55');
insert into cd.members(surname, firstname, joindate) values('Dare', 'Nancy', '2012-07-25 08:59:12');
insert into cd.members(surname, firstname, joindate) values('Boothe', 'Tim', '2012-07-25 16:02:35');
insert into cd.members(surname, firstname, joindate) values('Stibbons', 'Ponder', '2012-07-25 17:09:05');
insert into cd.members(surname, firstname, joindate) values('Owen', 'Charles', '2012-08-03 19:42:37');
insert into cd.members(surname, firstname, joindate) values('Jones', 'David', '2012-08-06 16:32:55');
insert into cd.members(surname, firstname, joindate) values('Baker', 'Anne', '2012-08-10 14:23:22');
insert into cd.members(surname, firstname, joindate) values('Farrell', 'Jemima', '2012-08-10 14:28:01');
insert into cd.members(surname, firstname, joindate) values('Smith', 'Jack', '2012-08-10 16:22:05');
insert into cd.members(surname, firstname, joindate) values('Bader', 'Florence', '2012-08-10 17:52:03');
insert into cd.members(surname, firstname, joindate) values('Baker', 'Timothy', '2012-08-15 10:34:25');
insert into cd.members(surname, firstname, joindate) values('Pinker', 'David', '2012-08-16 11:32:47');
insert into cd.members(surname, firstname, joindate) values('Genting', 'Matthew', '2012-08-19 14:55:55');
insert into cd.members(surname, firstname, joindate) values('Mackenzie', 'Anna', '2012-08-26 09:32:05');
insert into cd.members(surname, firstname, joindate) values('Coplin', 'Joan', '2012-08-29 08:32:41');
insert into cd.members(surname, firstname, joindate) values('Sarwin', 'Ramnaresh', '2012-09-01 08:44:42');
insert into cd.members(surname, firstname, joindate) values('Jones', 'Douglas', '2012-09-02 18:43:05');
insert into cd.members(surname, firstname, joindate) values('Rumney', 'Henrietta', '2012-09-05 08:42:35');
insert into cd.members(surname, firstname, joindate) values('Farrell', 'David', '2012-09-15 08:22:05');
insert into cd.members(surname, firstname, joindate) values('Worthington-Smyth', 'Henry', '2012-09-17 12:27:15');
insert into cd.members(surname, firstname, joindate) values('Purview', 'Millicent', '2012-09-18 19:04:01');
insert into cd.members(surname, firstname, joindate) values('Tupperware', 'Hyacinth', '2012-09-18 19:32:05');
insert into cd.members(surname, firstname, joindate) values('Hunt', 'John', '2012-09-19 11:32:45');
insert into cd.members(surname, firstname, joindate) values('Crumpet', 'Erica', '2012-09-22 08:36:38');
insert into cd.members(surname, firstname, joindate) values('Smith', 'Darren', '2012-09-26 18:08:45');


select firstname, surname, joindate from cd.members where joindate = (select max(joindate) from cd.members);