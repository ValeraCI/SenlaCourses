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


create table cd.bookings(
    facid int references cd.facilities(facid),
    starttime timestamp not null
);


insert into cd.bookings(facid, starttime) values(1 , '2012-07-05 17:30:00');
insert into cd.bookings(facid, starttime) values(2 , '2012-07-05 09:30:00');
insert into cd.bookings(facid, starttime) values(3 , '2012-07-05 09:00:00');
insert into cd.bookings(facid, starttime) values(3 , '2012-07-05 19:00:00');
insert into cd.bookings(facid, starttime) values(4 , '2012-07-05 18:30:00');
insert into cd.bookings(facid, starttime) values(6 , '2012-07-05 13:00:00');
insert into cd.bookings(facid, starttime) values(6 , '2012-07-05 14:30:00');
insert into cd.bookings(facid, starttime) values(7 , '2012-07-05 18:30:00');
insert into cd.bookings(facid, starttime) values(8 , '2012-07-05 12:30:00');
insert into cd.bookings(facid, starttime) values(0 , '2012-07-06 08:00:00');
insert into cd.bookings(facid, starttime) values(0 , '2012-07-06 14:00:00');
insert into cd.bookings(facid, starttime) values(0 , '2012-07-06 15:30:00');
insert into cd.bookings(facid, starttime) values(2 , '2012-07-06 17:00:00');
insert into cd.bookings(facid, starttime) values(3 , '2012-07-06 11:00:00');
insert into cd.bookings(facid, starttime) values(4 , '2012-08-06 12:00:00');
insert into cd.bookings(facid, starttime) values(6 , '2012-08-06 14:00:00');
insert into cd.bookings(facid, starttime) values(7 , '2012-08-06 08:30:00');
insert into cd.bookings(facid, starttime) values(7 , '2012-08-06 13:30:00');
insert into cd.bookings(facid, starttime) values(8 , '2012-08-06 15:30:00');
insert into cd.bookings(facid, starttime) values(0 , '2012-08-07 08:30:00');
insert into cd.bookings(facid, starttime) values(0 , '2012-08-07 12:30:00');
insert into cd.bookings(facid, starttime) values(0 , '2012-08-07 14:30:00');
insert into cd.bookings(facid, starttime) values(1 , '2012-09-07 08:30:00');
insert into cd.bookings(facid, starttime) values(2 , '2012-09-07 09:00:00');
insert into cd.bookings(facid, starttime) values(2 , '2012-09-07 11:30:00');
insert into cd.bookings(facid, starttime) values(2 , '2012-09-07 16:00:00');
insert into cd.bookings(facid, starttime) values(3 , '2012-09-20 12:30:00');
insert into cd.bookings(facid, starttime) values(4 , '2012-09-21 11:30:00');
insert into cd.bookings(facid, starttime) values(0 , '2012-09-21 14:00:00');
insert into cd.bookings(facid, starttime) values(1 , '2012-09-21 17:30:00');
insert into cd.bookings(facid, starttime) values(0 , '2012-09-21 08:30:00');
insert into cd.bookings(facid, starttime) values(0 , '2012-09-22 08:30:00');
insert into cd.bookings(facid, starttime) values(1 , '2012-10-07 10:30:00');
insert into cd.bookings(facid, starttime) values(6 , '2012-10-07 14:30:00');
insert into cd.bookings(facid, starttime) values(6 , '2012-10-07 16:00:00');
insert into cd.bookings(facid, starttime) values(7 , '2012-10-07 11:30:00');
insert into cd.bookings(facid, starttime) values(8 , '2012-10-07 16:00:00');
insert into cd.bookings(facid, starttime) values(8 , '2012-10-07 17:30:00');
insert into cd.bookings(facid, starttime) values(0 , '2012-11-08 13:00:00');
insert into cd.bookings(facid, starttime) values(0 , '2013-01-08 17:30:00');
insert into cd.bookings(facid, starttime) values(1 , '2013-01-08 15:00:00');


select starttime as start, name
from cd.bookings
join cd.facilities on cd.bookings.facid = cd.facilities.facid
where name like 'Tennis%' and starttime >= '2012-09-21' and starttime < '2012-09-22' 
order by starttime;