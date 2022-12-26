CREATE SCHEMA cd;

create table cd.members(
    memid int primary key,
    surname varchar(200) not null,
    firstname varchar(200) not null,
    recommendedby int references cd.members(memid)
);
 
insert into cd.members(memid, surname, firstname, recommendedby) values(0 , 'GUEST', 'GUEST', null);
insert into cd.members(memid, surname, firstname, recommendedby) values(1 , 'Smith', 'Darren', null);
insert into cd.members(memid, surname, firstname, recommendedby) values(2 , 'Smith', 'Tracy', null);
insert into cd.members(memid, surname, firstname, recommendedby) values(3 , 'Rownam', 'Tim', null);
insert into cd.members(memid, surname, firstname, recommendedby) values(4 , 'Joplette', 'Janice', 1);
insert into cd.members(memid, surname, firstname, recommendedby) values(5 , 'Butters', 'Gerald', 1);
insert into cd.members(memid, surname, firstname, recommendedby) values(6 , 'Tracy', 'Burton', null);
insert into cd.members(memid, surname, firstname, recommendedby) values(7 , 'Dare', 'Nancy', 4);
insert into cd.members(memid, surname, firstname, recommendedby) values(8 , 'Boothe', 'Tim', 3);
insert into cd.members(memid, surname, firstname, recommendedby) values(9 , 'Stibbons', 'Ponder', 6);
insert into cd.members(memid, surname, firstname, recommendedby) values(10 , 'Owen', 'Charles', 1);
insert into cd.members(memid, surname, firstname, recommendedby) values(11 , 'Jones', 'David', 4);
insert into cd.members(memid, surname, firstname, recommendedby) values(12 , 'Baker', 'Anne', 9);
insert into cd.members(memid, surname, firstname, recommendedby) values(13 , 'Farrell', 'Jemima', null);
insert into cd.members(memid, surname, firstname, recommendedby) values(14 , 'Smith', 'Jack', 1);
insert into cd.members(memid, surname, firstname, recommendedby) values(15 , 'Bader', 'Florence', 9);
insert into cd.members(memid, surname, firstname, recommendedby) values(16 , 'Baker', 'Timothy', 13);
insert into cd.members(memid, surname, firstname, recommendedby) values(17 , 'Pinker', 'David', 13);
insert into cd.members(memid, surname, firstname, recommendedby) values(20 , 'Genting', 'Matthew', 5);
insert into cd.members(memid, surname, firstname, recommendedby) values(21 , 'Mackenzie', 'Anna', 1);
insert into cd.members(memid, surname, firstname, recommendedby) values(22 , 'Coplin', 'Joan', 16);
insert into cd.members(memid, surname, firstname, recommendedby) values(24 , 'Sarwin', 'Ramnaresh', 15);
insert into cd.members(memid, surname, firstname, recommendedby) values(26 , 'Jones', 'Douglas', 11);
insert into cd.members(memid, surname, firstname, recommendedby) values(27 , 'Rumney', 'Henrietta', 20);
insert into cd.members(memid, surname, firstname, recommendedby) values(28 , 'Farrell', 'David', null);
insert into cd.members(memid, surname, firstname, recommendedby) values(29 , 'Worthington-Smyth', 'Henry', 2);
insert into cd.members(memid, surname, firstname, recommendedby) values(30 , 'Purview', 'Millicent', 2);
insert into cd.members(memid, surname, firstname, recommendedby) values(33 , 'Tupperware', 'Hyacinth', null);
insert into cd.members(memid, surname, firstname, recommendedby) values(35 , 'Hunt', 'John', 30);
insert into cd.members(memid, surname, firstname, recommendedby) values(36 , 'Crumpet', 'Erica', 2);
insert into cd.members(memid, surname, firstname, recommendedby) values(37 , 'Smith', 'Darren', null);

select distinct memb2.firstname, memb2.surname
from cd.members memb1 
join cd.members memb2 on memb1.recommendedby = memb2.memid 
order by memb2.surname, memb2.firstname;