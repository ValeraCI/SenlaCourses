CREATE SCHEMA cd;

create table cd.facilities(
    facid int primary key,
    name varchar(100) not null
);

insert into cd.facilities(facid, name) values(0, 'Tennis Court 1');
insert into cd.facilities(facid, name) values(1, 'Tennis Court 2');
insert into cd.facilities(facid, name) values(2, 'Badminton Court');
insert into cd.facilities(facid, name) values(3, 'Table Tennis');
insert into cd.facilities(facid, name) values(4, 'Massage Room 1');
insert into cd.facilities(facid, name) values(5, 'Massage Room 2');
insert into cd.facilities(facid, name) values(6, 'Squash Court');
insert into cd.facilities(facid, name) values(7, 'Snooker Table');
insert into cd.facilities(facid, name) values(8, 'Pool Table');




create table cd.members(
    memid int primary key,
    surname varchar(200) not null,
    firstname varchar(200) not null,
    recommendedby int references cd.members(memid)
);
 
insert into cd.members(memid, surname, firstname) values(0 , 'GUEST', 'GUEST');
insert into cd.members(memid, surname, firstname) values(1 , 'Smith', 'Darren');
insert into cd.members(memid, surname, firstname) values(2 , 'Smith', 'Tracy');
insert into cd.members(memid, surname, firstname) values(3 , 'Rownam', 'Tim');
insert into cd.members(memid, surname, firstname) values(4 , 'Joplette', 'Janice');
insert into cd.members(memid, surname, firstname) values(5 , 'Butters', 'Gerald');
insert into cd.members(memid, surname, firstname) values(6 , 'Tracy', 'Burton');
insert into cd.members(memid, surname, firstname) values(7 , 'Dare', 'Nancy');
insert into cd.members(memid, surname, firstname) values(8 , 'Boothe', 'Tim');
insert into cd.members(memid, surname, firstname) values(9 , 'Stibbons', 'Ponder');
insert into cd.members(memid, surname, firstname) values(10 , 'Owen', 'Charles');
insert into cd.members(memid, surname, firstname) values(11 , 'Jones', 'David');
insert into cd.members(memid, surname, firstname) values(12 , 'Baker', 'Anne');
insert into cd.members(memid, surname, firstname) values(13 , 'Farrell', 'Jemima');
insert into cd.members(memid, surname, firstname) values(14 , 'Smith', 'Jack');
insert into cd.members(memid, surname, firstname) values(15 , 'Bader', 'Florence');
insert into cd.members(memid, surname, firstname) values(16 , 'Baker', 'Timothy');
insert into cd.members(memid, surname, firstname) values(17 , 'Pinker', 'David');
insert into cd.members(memid, surname, firstname) values(20 , 'Genting', 'Matthew');
insert into cd.members(memid, surname, firstname) values(21 , 'Mackenzie', 'Anna');
insert into cd.members(memid, surname, firstname) values(22 , 'Coplin', 'Joan');
insert into cd.members(memid, surname, firstname) values(24 , 'Sarwin', 'Ramnaresh');
insert into cd.members(memid, surname, firstname) values(26 , 'Jones', 'Douglas');
insert into cd.members(memid, surname, firstname) values(27 , 'Rumney', 'Henrietta');
insert into cd.members(memid, surname, firstname) values(28 , 'Farrell', 'David');
insert into cd.members(memid, surname, firstname) values(29 , 'Worthington-Smyth', 'Henry');
insert into cd.members(memid, surname, firstname) values(30 , 'Purview', 'Millicent');
insert into cd.members(memid, surname, firstname) values(33 , 'Tupperware', 'Hyacinth');
insert into cd.members(memid, surname, firstname) values(35 , 'Hunt', 'John');
insert into cd.members(memid, surname, firstname) values(36 , 'Crumpet', 'Erica');
insert into cd.members(memid, surname, firstname) values(37 , 'Smith', 'Darren');

create table cd.bookings(
    facid int references cd.facilities(facid),
    memid int references cd.members(memid)
);

insert into cd.bookings(facid, memid) values(3 , 1);
insert into cd.bookings(facid, memid) values(4 , 1);
insert into cd.bookings(facid, memid) values(6 , 0);
insert into cd.bookings(facid, memid) values(7 , 1);
insert into cd.bookings(facid, memid) values(8 , 1);
insert into cd.bookings(facid, memid) values(8 , 1);
insert into cd.bookings(facid, memid) values(0 , 2);
insert into cd.bookings(facid, memid) values(0 , 2);
insert into cd.bookings(facid, memid) values(4 , 3);
insert into cd.bookings(facid, memid) values(4 , 0);
insert into cd.bookings(facid, memid) values(4 , 0);
insert into cd.bookings(facid, memid) values(6 , 0);
insert into cd.bookings(facid, memid) values(6 , 0);
insert into cd.bookings(facid, memid) values(6 , 1);
insert into cd.bookings(facid, memid) values(7 , 2);
insert into cd.bookings(facid, memid) values(8 , 2);
insert into cd.bookings(facid, memid) values(8 , 3);
insert into cd.bookings(facid, memid) values(1 , 0);
insert into cd.bookings(facid, memid) values(2 , 1);
insert into cd.bookings(facid, memid) values(3 , 3);
insert into cd.bookings(facid, memid) values(3 , 1);
insert into cd.bookings(facid, memid) values(4 , 3);
insert into cd.bookings(facid, memid) values(6 , 0);
insert into cd.bookings(facid, memid) values(6 , 1);
insert into cd.bookings(facid, memid) values(7 , 2);
insert into cd.bookings(facid, memid) values(8 , 3);
insert into cd.bookings(facid, memid) values(0 , 0);
insert into cd.bookings(facid, memid) values(0 , 0);
insert into cd.bookings(facid, memid) values(0 , 2);
insert into cd.bookings(facid, memid) values(2 , 1);
insert into cd.bookings(facid, memid) values(3 , 1);
insert into cd.bookings(facid, memid) values(4 , 3);
insert into cd.bookings(facid, memid) values(6 , 1);
insert into cd.bookings(facid, memid) values(7 , 2);
insert into cd.bookings(facid, memid) values(7 , 2);
insert into cd.bookings(facid, memid) values(8 , 3);
insert into cd.bookings(facid, memid) values(0 , 2);
insert into cd.bookings(facid, memid) values(0 , 0);
insert into cd.bookings(facid, memid) values(0 , 2);
insert into cd.bookings(facid, memid) values(1 , 3);
insert into cd.bookings(facid, memid) values(2 , 1);
insert into cd.bookings(facid, memid) values(2 , 1);
insert into cd.bookings(facid, memid) values(2 , 1);
insert into cd.bookings(facid, memid) values(3 , 2);
insert into cd.bookings(facid, memid) values(4 , 3);
insert into cd.bookings(facid, memid) values(4 , 3);
insert into cd.bookings(facid, memid) values(4 , 0);
insert into cd.bookings(facid, memid) values(6 , 0);
insert into cd.bookings(facid, memid) values(6 , 1);
insert into cd.bookings(facid, memid) values(6 , 1);
insert into cd.bookings(facid, memid) values(6 , 0);
insert into cd.bookings(facid, memid) values(7 , 2);
insert into cd.bookings(facid, memid) values(8 , 3);
insert into cd.bookings(facid, memid) values(8 , 3);
insert into cd.bookings(facid, memid) values(0 , 3);
insert into cd.bookings(facid, memid) values(0 , 2);
insert into cd.bookings(facid, memid) values(1 , 1);
insert into cd.bookings(facid, memid) values(1 , 1);
insert into cd.bookings(facid, memid) values(3 , 1);
insert into cd.bookings(facid, memid) values(3 , 3);
insert into cd.bookings(facid, memid) values(3 , 1);
insert into cd.bookings(facid, memid) values(4 , 0);
insert into cd.bookings(facid, memid) values(4 , 2);
insert into cd.bookings(facid, memid) values(4 , 0);
insert into cd.bookings(facid, memid) values(4 , 0);
insert into cd.bookings(facid, memid) values(6 , 0);
insert into cd.bookings(facid, memid) values(6 , 0);
insert into cd.bookings(facid, memid) values(7 , 2);
insert into cd.bookings(facid, memid) values(7 , 1);
insert into cd.bookings(facid, memid) values(8 , 3);
insert into cd.bookings(facid, memid) values(8 , 3);
insert into cd.bookings(facid, memid) values(0 , 2);
insert into cd.bookings(facid, memid) values(0 , 2);
insert into cd.bookings(facid, memid) values(0 , 2);
insert into cd.bookings(facid, memid) values(1 , 0);
insert into cd.bookings(facid, memid) values(1 , 1);
insert into cd.bookings(facid, memid) values(2 , 1);
insert into cd.bookings(facid, memid) values(2 , 0);
insert into cd.bookings(facid, memid) values(3 , 3);
insert into cd.bookings(facid, memid) values(3 , 3);
insert into cd.bookings(facid, memid) values(4 , 2);
insert into cd.bookings(facid, memid) values(4 , 3);
insert into cd.bookings(facid, memid) values(6 , 0);
insert into cd.bookings(facid, memid) values(7 , 1);
insert into cd.bookings(facid, memid) values(7 , 0);
insert into cd.bookings(facid, memid) values(8 , 3);
insert into cd.bookings(facid, memid) values(8 , 3);
insert into cd.bookings(facid, memid) values(8 , 3);
insert into cd.bookings(facid, memid) values(0 , 0);
insert into cd.bookings(facid, memid) values(0 , 0);
insert into cd.bookings(facid, memid) values(3 , 2);
insert into cd.bookings(facid, memid) values(3 , 1);
insert into cd.bookings(facid, memid) values(3 , 3);
insert into cd.bookings(facid, memid) values(3 , 2);
insert into cd.bookings(facid, memid) values(3 , 1);
insert into cd.bookings(facid, memid) values(4 , 0);
insert into cd.bookings(facid, memid) values(4 , 4);
insert into cd.bookings(facid, memid) values(4 , 0);
insert into cd.bookings(facid, memid) values(4 , 3);
insert into cd.bookings(facid, memid) values(5 , 0);
insert into cd.bookings(facid, memid) values(6 , 0);
insert into cd.bookings(facid, memid) values(6 , 0);
insert into cd.bookings(facid, memid) values(7 , 4);
insert into cd.bookings(facid, memid) values(7 , 2);
insert into cd.bookings(facid, memid) values(8 , 0);
insert into cd.bookings(facid, memid) values(8 , 3);
insert into cd.bookings(facid, memid) values(8 , 3);
insert into cd.bookings(facid, memid) values(0 , 4);
insert into cd.bookings(facid, memid) values(0 , 2);
insert into cd.bookings(facid, memid) values(0 , 0);
insert into cd.bookings(facid, memid) values(0 , 0);
insert into cd.bookings(facid, memid) values(0 , 2);
insert into cd.bookings(facid, memid) values(0 , 2);
insert into cd.bookings(facid, memid) values(1 , 0);
insert into cd.bookings(facid, memid) values(1 , 0);
insert into cd.bookings(facid, memid) values(4 , 1);
insert into cd.bookings(facid, memid) values(4 , 0);
insert into cd.bookings(facid, memid) values(4 , 3);
insert into cd.bookings(facid, memid) values(4 , 0);
insert into cd.bookings(facid, memid) values(5 , 4);
insert into cd.bookings(facid, memid) values(6 , 0);
insert into cd.bookings(facid, memid) values(6 , 0);
insert into cd.bookings(facid, memid) values(7 , 0);
insert into cd.bookings(facid, memid) values(7 , 0);
insert into cd.bookings(facid, memid) values(7 , 0);
insert into cd.bookings(facid, memid) values(8 , 4);
insert into cd.bookings(facid, memid) values(8 , 3);
insert into cd.bookings(facid, memid) values(0 , 0);
insert into cd.bookings(facid, memid) values(0 , 2);
insert into cd.bookings(facid, memid) values(1 , 1);
insert into cd.bookings(facid, memid) values(2 , 1);
insert into cd.bookings(facid, memid) values(2 , 1);
insert into cd.bookings(facid, memid) values(3 , 3);
insert into cd.bookings(facid, memid) values(4 , 1);
insert into cd.bookings(facid, memid) values(6 , 0);
insert into cd.bookings(facid, memid) values(7 , 2);
insert into cd.bookings(facid, memid) values(7 , 4);
insert into cd.bookings(facid, memid) values(7 , 4);
insert into cd.bookings(facid, memid) values(8 , 3);
insert into cd.bookings(facid, memid) values(0 , 2);

select distinct (firstname || ' ' || surname) as member, name as facility
from cd.members mem		
join cd.bookings bk on bk.memid = mem.memid
join cd.facilities fac on fac.facid = bk.facid
where name like 'Tennis%'
order by member, facility;