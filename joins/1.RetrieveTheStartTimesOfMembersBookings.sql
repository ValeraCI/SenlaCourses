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
    starttime timestamp not null
);



insert into cd.bookings(memid, starttime) values(1, '2012-07-21 13:30:00');
insert into cd.bookings(memid, starttime) values(2, '2012-07-21 08:00:00');
insert into cd.bookings(memid, starttime) values(0, '2012-07-21 09:00:00');
insert into cd.bookings(memid, starttime) values(3, '2012-07-21 10:30:00');
insert into cd.bookings(memid, starttime) values(4, '2012-07-21 14:00:00');
insert into cd.bookings(memid, starttime) values(5, '2012-07-21 16:00:00');
insert into cd.bookings(memid, starttime) values(6, '2012-07-21 17:00:00');
insert into cd.bookings(memid, starttime) values(7, '2012-07-21 19:00:00');
insert into cd.bookings(memid, starttime) values(8, '2012-07-21 08:00:00');
insert into cd.bookings(memid, starttime) values(9, '2012-07-21 09:30:00');
insert into cd.bookings(memid, starttime) values(10, '2012-07-21 12:00:00');
insert into cd.bookings(memid, starttime) values(11, '2012-07-21 09:30:00');
insert into cd.bookings(memid, starttime) values(12, '2012-07-21 11:30:00');
insert into cd.bookings(memid, starttime) values(13, '2012-07-21 18:00:00');
insert into cd.bookings(memid, starttime) values(14, '2012-07-21 19:30:00');
insert into cd.bookings(memid, starttime) values(15, '2012-07-22 10:00:00');
insert into cd.bookings(memid, starttime) values(16, '2012-07-22 16:00:00');
insert into cd.bookings(memid, starttime) values(17, '2012-07-22 18:00:00');
insert into cd.bookings(memid, starttime) values(28, '2012-07-22 08:30:00');
insert into cd.bookings(memid, starttime) values(28, '2012-07-22 10:30:00');
insert into cd.bookings(memid, starttime) values(20, '2012-07-22 18:30:00');
insert into cd.bookings(memid, starttime) values(21, '2012-07-22 08:30:00');
insert into cd.bookings(memid, starttime) values(22, '2012-07-22 13:30:00');
insert into cd.bookings(memid, starttime) values(28, '2012-07-22 16:30:00');
insert into cd.bookings(memid, starttime) values(24, '2012-07-22 11:30:00');
insert into cd.bookings(memid, starttime) values(28, '2012-07-22 14:00:00');
insert into cd.bookings(memid, starttime) values(26, '2012-07-22 08:00:00');
insert into cd.bookings(memid, starttime) values(27, '2012-07-22 10:30:00');
insert into cd.bookings(memid, starttime) values(28, '2012-07-22 12:00:00');
insert into cd.bookings(memid, starttime) values(29, '2012-07-22 13:00:00');
insert into cd.bookings(memid, starttime) values(30, '2012-07-22 16:30:00');
insert into cd.bookings(memid, starttime) values(30, '2012-07-22 18:00:00');
insert into cd.bookings(memid, starttime) values(30, '2012-07-22 19:30:00');
insert into cd.bookings(memid, starttime) values(33, '2012-07-22 10:30:00');
insert into cd.bookings(memid, starttime) values(30, '2012-07-22 14:30:00');
insert into cd.bookings(memid, starttime) values(35, '2012-07-22 16:30:00');
insert into cd.bookings(memid, starttime) values(36, '2012-07-22 10:30:00');
insert into cd.bookings(memid, starttime) values(37, '2012-07-22 12:00:00');


select starttime 
from cd.bookings 
join cd.members on cd.bookings.memid = cd.members.memid 
where cd.members.firstname ='David' and cd.members.surname = 'Farrell';