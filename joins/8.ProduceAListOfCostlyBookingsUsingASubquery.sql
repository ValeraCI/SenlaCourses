CREATE SCHEMA cd;


create table cd.facilities(
    facid int primary key,
    name varchar(100) not null,
    membercost numeric not null,
    guestcost numeric not null
);
 
insert into cd.facilities(facid, name, membercost, guestcost) values(0, 'Tennis Court 1', 5, 25);
insert into cd.facilities(facid, name, membercost, guestcost) values(1, 'Tennis Court 2', 5, 25);
insert into cd.facilities(facid, name, membercost, guestcost) values(2, 'Badminton Court', 0, 15.5);
insert into cd.facilities(facid, name, membercost, guestcost) values(3, 'Table Tennis', 0, 5);
insert into cd.facilities(facid, name, membercost, guestcost) values(4, 'Massage Room 1', 35, 80);
insert into cd.facilities(facid, name, membercost, guestcost) values(5, 'Massage Room 2', 35, 80);
insert into cd.facilities(facid, name, membercost, guestcost) values(6, 'Squash Court', 3.5, 17.5);
insert into cd.facilities(facid, name, membercost, guestcost) values(7, 'Snooker Table', 0, 5);
insert into cd.facilities(facid, name, membercost, guestcost) values(8, 'Pool Table', 0, 5);


create table cd.members(
    memid int primary key,
    surname varchar(200) not null,
    firstname varchar(200) not null
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
    memid int references cd.members(memid),
    starttime timestamp not null,
    slots int not null
);

insert into cd.bookings(facid, memid, starttime, slots) values(3 , 1, '2012-08-03 11:00:00', 2);
insert into cd.bookings(facid, memid, starttime, slots) values(4 , 1, '2012-08-03 08:00:00', 2);
insert into cd.bookings(facid, memid, starttime, slots) values(6 , 0, '2012-09-13 18:00:00', 2);
insert into cd.bookings(facid, memid, starttime, slots) values(7 , 1, '2012-09-13 19:00:00', 2);
insert into cd.bookings(facid, memid, starttime, slots) values(8 , 1, '2012-09-14 10:00:00', 1);
insert into cd.bookings(facid, memid, starttime, slots) values(8 , 1, '2012-09-14 15:00:00', 1);
insert into cd.bookings(facid, memid, starttime, slots) values(0 , 2, '2012-09-14 09:00:00', 3);
insert into cd.bookings(facid, memid, starttime, slots) values(0 , 2, '2012-09-14 15:00:00', 3);
insert into cd.bookings(facid, memid, starttime, slots) values(4 , 3, '2012-09-14 13:30:00', 2);
insert into cd.bookings(facid, memid, starttime, slots) values(4 , 0, '2012-09-14 15:00:00', 2);
insert into cd.bookings(facid, memid, starttime, slots) values(4 , 0, '2012-09-14 17:30:00', 2);
insert into cd.bookings(facid, memid, starttime, slots) values(6 , 0, '2012-09-14 12:30:00', 2);
insert into cd.bookings(facid, memid, starttime, slots) values(6 , 0, '2012-09-14 14:00:00', 2);
insert into cd.bookings(facid, memid, starttime, slots) values(6 , 1, '2012-09-14 15:30:00', 2);
insert into cd.bookings(facid, memid, starttime, slots) values(7 , 2, '2012-09-14 14:00:00', 2);
insert into cd.bookings(facid, memid, starttime, slots) values(8 , 2, '2012-09-14 12:00:00', 1);
insert into cd.bookings(facid, memid, starttime, slots) values(8 , 3, '2012-09-14 18:00:00', 1);
insert into cd.bookings(facid, memid, starttime, slots) values(1 , 0, '2012-09-14 17:30:00', 3);
insert into cd.bookings(facid, memid, starttime, slots) values(2 , 1, '2012-09-15 09:30:00', 3);
insert into cd.bookings(facid, memid, starttime, slots) values(3 , 3, '2012-09-15 09:00:00', 2);
insert into cd.bookings(facid, memid, starttime, slots) values(3 , 1, '2012-09-15 19:00:00', 2);
insert into cd.bookings(facid, memid, starttime, slots) values(4 , 3, '2012-10-15 18:30:00', 2);
insert into cd.bookings(facid, memid, starttime, slots) values(6 , 0, '2012-10-15 13:00:00', 2);


select firstname || ' ' || surname as member, name as facility, cost
from(
	 select firstname, surname, name, starttime,
	 case
	 	when mem.memid = 0 then slots * guestcost
	  	else slots * membercost 
	 end as cost
	 from cd.members mem
	 join cd.bookings bk on mem.memid = bk.memid
	 join cd.facilities fac on bk.facid = fac.facid
  	) as pt	
where cost > 30 and starttime >= '2012-09-14' and starttime < '2012-09-15'
order by cost desc;