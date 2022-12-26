CREATE SCHEMA cd;

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
    memid int references cd.members(memid),
    starttime timestamp not null,
    slots int not null
);

insert into cd.bookings(memid, starttime, slots) values(1, '2012-07-03 11:00:00', 2);
insert into cd.bookings(memid, starttime, slots) values(1, '2012-07-03 08:00:00', 2);
insert into cd.bookings(memid, starttime, slots) values(0, '2012-07-03 18:00:00', 2);
insert into cd.bookings(memid, starttime, slots) values(1, '2012-07-03 19:00:00', 2);
insert into cd.bookings(memid, starttime, slots) values(1, '2012-07-03 10:00:00', 1);
insert into cd.bookings(memid, starttime, slots) values(1, '2012-07-03 15:00:00', 1);
insert into cd.bookings(memid, starttime, slots) values(2, '2012-07-04 09:00:00', 3);
insert into cd.bookings(memid, starttime, slots) values(2, '2012-07-04 15:00:00', 3);
insert into cd.bookings(memid, starttime, slots) values(3, '2012-07-04 13:30:00', 2);
insert into cd.bookings(memid, starttime, slots) values(0, '2012-07-04 15:00:00', 2);
insert into cd.bookings(memid, starttime, slots) values(0, '2012-07-04 17:30:00', 2);
insert into cd.bookings(memid, starttime, slots) values(0, '2012-07-04 12:30:00', 2);
insert into cd.bookings(memid, starttime, slots) values(0, '2012-07-04 14:00:00', 2);
insert into cd.bookings(memid, starttime, slots) values(1, '2012-07-04 15:30:00', 2);
insert into cd.bookings(memid, starttime, slots) values(2, '2012-07-04 14:00:00', 2);
insert into cd.bookings(memid, starttime, slots) values(2, '2012-07-04 12:00:00', 1);
insert into cd.bookings(memid, starttime, slots) values(3, '2012-07-04 18:00:00', 1);
insert into cd.bookings(memid, starttime, slots) values(0, '2012-07-05 17:30:00', 3);
insert into cd.bookings(memid, starttime, slots) values(1, '2012-07-05 09:30:00', 3);
insert into cd.bookings(memid, starttime, slots) values(3, '2012-07-05 09:00:00', 2);
insert into cd.bookings(memid, starttime, slots) values(1, '2012-07-05 19:00:00', 2);
insert into cd.bookings(memid, starttime, slots) values(3, '2012-07-05 18:30:00', 2);
insert into cd.bookings(memid, starttime, slots) values(0, '2012-07-05 13:00:00', 2);
insert into cd.bookings(memid, starttime, slots) values(1, '2012-07-05 14:30:00', 2);
insert into cd.bookings(memid, starttime, slots) values(2, '2012-07-05 18:30:00', 2);
insert into cd.bookings(memid, starttime, slots) values(3, '2012-07-05 12:30:00', 1);
insert into cd.bookings(memid, starttime, slots) values(0, '2012-07-06 08:00:00', 3);
insert into cd.bookings(memid, starttime, slots) values(0, '2012-07-06 14:00:00', 3);
insert into cd.bookings(memid, starttime, slots) values(2, '2012-07-06 15:30:00', 3);
insert into cd.bookings(memid, starttime, slots) values(1, '2012-07-06 17:00:00', 3);
insert into cd.bookings(memid, starttime, slots) values(1, '2012-07-06 11:00:00', 2);
insert into cd.bookings(memid, starttime, slots) values(3, '2012-07-06 12:00:00', 2);
insert into cd.bookings(memid, starttime, slots) values(1, '2012-07-06 14:00:00', 2);
insert into cd.bookings(memid, starttime, slots) values(2, '2012-07-06 08:30:00', 2);
insert into cd.bookings(memid, starttime, slots) values(2, '2012-07-06 13:30:00', 2);
insert into cd.bookings(memid, starttime, slots) values(3, '2012-07-06 15:30:00', 1);
insert into cd.bookings(memid, starttime, slots) values(2, '2012-07-07 08:30:00', 3);
insert into cd.bookings(memid, starttime, slots) values(0, '2012-07-07 12:30:00', 3);
insert into cd.bookings(memid, starttime, slots) values(2, '2012-07-07 14:30:00', 3);
insert into cd.bookings(memid, starttime, slots) values(3, '2012-07-07 08:30:00', 3);
insert into cd.bookings(memid, starttime, slots) values(1, '2012-07-07 09:00:00', 3);
insert into cd.bookings(memid, starttime, slots) values(1, '2012-07-07 11:30:00', 3);
insert into cd.bookings(memid, starttime, slots) values(1, '2012-07-07 16:00:00', 3);
insert into cd.bookings(memid, starttime, slots) values(2, '2012-07-07 12:30:00', 2);
insert into cd.bookings(memid, starttime, slots) values(3, '2012-07-07 11:30:00', 2);
insert into cd.bookings(memid, starttime, slots) values(3, '2012-07-07 14:00:00', 2);
insert into cd.bookings(memid, starttime, slots) values(0, '2012-07-07 17:30:00', 2);
insert into cd.bookings(memid, starttime, slots) values(0, '2012-07-07 08:30:00', 2);
insert into cd.bookings(memid, starttime, slots) values(1, '2012-07-07 10:30:00', 2);
insert into cd.bookings(memid, starttime, slots) values(1, '2012-08-07 14:30:00', 2);
insert into cd.bookings(memid, starttime, slots) values(0, '2012-08-07 16:00:00', 2);
insert into cd.bookings(memid, starttime, slots) values(2, '2012-08-07 11:30:00', 2);
insert into cd.bookings(memid, starttime, slots) values(3, '2012-08-07 16:00:00', 1);
insert into cd.bookings(memid, starttime, slots) values(3, '2012-08-07 17:30:00', 2);
insert into cd.bookings(memid, starttime, slots) values(3, '2012-08-08 13:00:00', 3);
insert into cd.bookings(memid, starttime, slots) values(2, '2012-08-08 17:30:00', 3);
insert into cd.bookings(memid, starttime, slots) values(1, '2012-09-08 15:00:00', 3);
insert into cd.bookings(memid, starttime, slots) values(1, '2012-09-08 17:30:00', 3);
insert into cd.bookings(memid, starttime, slots) values(1, '2012-07-08 11:30:00', 2);
insert into cd.bookings(memid, starttime, slots) values(3, '2012-09-08 18:30:00', 2);
insert into cd.bookings(memid, starttime, slots) values(1, '2012-09-08 19:30:00', 2);
insert into cd.bookings(memid, starttime, slots) values(0, '2012-09-08 11:00:00', 2);
insert into cd.bookings(memid, starttime, slots) values(2, '2012-09-08 16:30:00', 2);
insert into cd.bookings(memid, starttime, slots) values(0, '2012-09-08 18:00:00', 2);
insert into cd.bookings(memid, starttime, slots) values(0, '2012-09-08 19:30:00', 2);
insert into cd.bookings(memid, starttime, slots) values(0, '2012-09-08 14:00:00', 2);
insert into cd.bookings(memid, starttime, slots) values(0, '2012-09-08 18:30:00', 2);
insert into cd.bookings(memid, starttime, slots) values(2, '2012-09-08 11:00:00', 2);
insert into cd.bookings(memid, starttime, slots) values(1, '2012-10-08 16:30:00', 2);
insert into cd.bookings(memid, starttime, slots) values(3, '2012-10-08 10:00:00', 1);
insert into cd.bookings(memid, starttime, slots) values(3, '2012-10-08 16:30:00', 1);
insert into cd.bookings(memid, starttime, slots) values(2, '2012-10-09 12:30:00', 3);
insert into cd.bookings(memid, starttime, slots) values(2, '2012-10-09 15:30:00', 3);
insert into cd.bookings(memid, starttime, slots) values(2, '2012-11-09 19:00:00', 3);
insert into cd.bookings(memid, starttime, slots) values(0, '2012-11-09 13:00:00', 3);
insert into cd.bookings(memid, starttime, slots) values(1, '2012-11-09 19:00:00', 3);
insert into cd.bookings(memid, starttime, slots) values(1, '2012-11-09 09:00:00', 6);
insert into cd.bookings(memid, starttime, slots) values(0, '2012-11-09 19:00:00', 3);
insert into cd.bookings(memid, starttime, slots) values(3, '2012-11-09 17:00:00', 2);
insert into cd.bookings(memid, starttime, slots) values(3, '2012-11-09 18:30:00', 2);
insert into cd.bookings(memid, starttime, slots) values(2, '2012-11-09 11:00:00', 2);
insert into cd.bookings(memid, starttime, slots) values(3, '2012-11-09 14:30:00', 2);
insert into cd.bookings(memid, starttime, slots) values(0, '2012-11-09 14:30:00', 2);
insert into cd.bookings(memid, starttime, slots) values(1, '2012-11-09 15:30:00', 2);
insert into cd.bookings(memid, starttime, slots) values(0, '2012-11-09 18:30:00', 4);
insert into cd.bookings(memid, starttime, slots) values(3, '2012-11-09 09:30:00', 1);
insert into cd.bookings(memid, starttime, slots) values(3, '2012-12-09 16:30:00', 1);
insert into cd.bookings(memid, starttime, slots) values(3, '2012-12-09 20:00:00', 1);
insert into cd.bookings(memid, starttime, slots) values(0, '2012-12-10 11:30:00', 3);
insert into cd.bookings(memid, starttime, slots) values(0, '2012-12-10 16:00:00', 3);
insert into cd.bookings(memid, starttime, slots) values(2, '2012-12-10 08:00:00', 2);
insert into cd.bookings(memid, starttime, slots) values(1, '2012-12-10 11:00:00', 2);



select surname, firstname, mem.memid, min(starttime) as starttime
from cd.members mem join cd.bookings bk on mem.memid = bk.memid
where starttime >= '2012-09-01'
group by surname, firstname, mem.memid
order by memid
