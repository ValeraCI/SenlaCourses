CREATE SCHEMA cd;


create table cd.bookings(
    facid int not null,
    starttime timestamp not null,
    slots int not null
);

insert into cd.bookings(facid, starttime, slots) values(3, '2010-07-03 11:00:00', 2);
insert into cd.bookings(facid, starttime, slots) values(4, '2010-07-03 08:00:00', 2);
insert into cd.bookings(facid, starttime, slots) values(6, '2010-07-03 18:00:00', 2);
insert into cd.bookings(facid, starttime, slots) values(7, '2011-08-03 19:00:00', 2);
insert into cd.bookings(facid, starttime, slots) values(8, '2011-08-03 10:00:00', 1);
insert into cd.bookings(facid, starttime, slots) values(8, '2011-08-03 15:00:00', 1);
insert into cd.bookings(facid, starttime, slots) values(0, '2011-08-04 09:00:00', 3);
insert into cd.bookings(facid, starttime, slots) values(0, '2011-08-04 15:00:00', 3);
insert into cd.bookings(facid, starttime, slots) values(4, '2012-08-04 13:30:00', 2);
insert into cd.bookings(facid, starttime, slots) values(4, '2012-09-04 15:00:00', 2);
insert into cd.bookings(facid, starttime, slots) values(4, '2012-09-04 17:30:00', 2);
insert into cd.bookings(facid, starttime, slots) values(6, '2012-09-04 12:30:00', 2);
insert into cd.bookings(facid, starttime, slots) values(6, '2012-09-04 14:00:00', 2);
insert into cd.bookings(facid, starttime, slots) values(6, '2012-09-04 15:30:00', 2);
insert into cd.bookings(facid, starttime, slots) values(7, '2012-09-04 14:00:00', 2);
insert into cd.bookings(facid, starttime, slots) values(8, '2012-09-04 12:00:00', 1);
insert into cd.bookings(facid, starttime, slots) values(8, '2012-09-04 18:00:00', 1);
insert into cd.bookings(facid, starttime, slots) values(1, '2012-09-05 17:30:00', 3);
insert into cd.bookings(facid, starttime, slots) values(2, '2012-09-05 09:30:00', 3);
insert into cd.bookings(facid, starttime, slots) values(3, '2012-09-05 09:00:00', 2);
insert into cd.bookings(facid, starttime, slots) values(3, '2012-09-05 19:00:00', 2);
insert into cd.bookings(facid, starttime, slots) values(4, '2012-09-05 18:30:00', 2);
insert into cd.bookings(facid, starttime, slots) values(6, '2012-09-05 13:00:00', 2);
insert into cd.bookings(facid, starttime, slots) values(6, '2012-09-05 14:30:00', 2);
insert into cd.bookings(facid, starttime, slots) values(7, '2012-09-05 18:30:00', 2);
insert into cd.bookings(facid, starttime, slots) values(8, '2012-09-05 12:30:00', 1);
insert into cd.bookings(facid, starttime, slots) values(7, '2012-01-04 14:00:00', 2);
insert into cd.bookings(facid, starttime, slots) values(8, '2012-02-04 12:00:00', 1);
insert into cd.bookings(facid, starttime, slots) values(8, '2012-03-04 18:00:00', 1);
insert into cd.bookings(facid, starttime, slots) values(1, '2012-04-05 17:30:00', 3);
insert into cd.bookings(facid, starttime, slots) values(2, '2012-05-05 09:30:00', 3);
insert into cd.bookings(facid, starttime, slots) values(3, '2012-06-05 09:00:00', 2);
insert into cd.bookings(facid, starttime, slots) values(3, '2012-07-05 19:00:00', 2);
insert into cd.bookings(facid, starttime, slots) values(4, '2012-08-05 18:30:00', 2);
insert into cd.bookings(facid, starttime, slots) values(6, '2012-12-05 13:00:00', 2);
insert into cd.bookings(facid, starttime, slots) values(6, '2012-11-05 14:30:00', 2);
insert into cd.bookings(facid, starttime, slots) values(7, '2012-09-05 18:30:00', 2);
insert into cd.bookings(facid, starttime, slots) values(8, '2012-09-05 12:30:00', 1);
insert into cd.bookings(facid, starttime, slots) values(0, '2012-09-06 08:00:00', 3);
insert into cd.bookings(facid, starttime, slots) values(0, '2012-09-06 14:00:00', 3);
insert into cd.bookings(facid, starttime, slots) values(0, '2012-09-06 15:30:00', 3);
insert into cd.bookings(facid, starttime, slots) values(2, '2012-09-06 17:00:00', 3);
insert into cd.bookings(facid, starttime, slots) values(3, '2012-09-06 11:00:00', 2);
insert into cd.bookings(facid, starttime, slots) values(4, '2012-09-06 12:00:00', 2);
insert into cd.bookings(facid, starttime, slots) values(6, '2012-09-06 14:00:00', 2);
insert into cd.bookings(facid, starttime, slots) values(7, '2012-09-06 08:30:00', 2);
insert into cd.bookings(facid, starttime, slots) values(7, '2012-11-06 13:30:00', 2);
insert into cd.bookings(facid, starttime, slots) values(8, '2013-10-06 15:30:00', 1);
insert into cd.bookings(facid, starttime, slots) values(0, '2013-10-07 08:30:00', 3);
insert into cd.bookings(facid, starttime, slots) values(0, '2013-10-07 12:30:00', 3);

select facid, extract(month from starttime) as month, sum(slots) as "Total Slots"
from cd.bookings
where extract(year from starttime) = 2012
group by facid, month
order by facid, month;
