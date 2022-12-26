CREATE SCHEMA cd;


create table cd.bookings(
    facid int not null,
    starttime timestamp not null,
    slots int not null
);

insert into cd.bookings(facid, starttime, slots) values(3, '2012-01-01 11:00:00', 2);
insert into cd.bookings(facid, starttime, slots) values(4, '2012-01-10 08:00:00', 2);
insert into cd.bookings(facid, starttime, slots) values(6, '2012-01-20 18:00:00', 2);
insert into cd.bookings(facid, starttime, slots) values(7, '2012-02-01 19:00:00', 2);
insert into cd.bookings(facid, starttime, slots) values(8, '2012-02-10 10:00:00', 1);
insert into cd.bookings(facid, starttime, slots) values(8, '2012-02-20 15:00:00', 1);
insert into cd.bookings(facid, starttime, slots) values(0, '2012-03-01 09:00:00', 3);
insert into cd.bookings(facid, starttime, slots) values(0, '2012-03-10 15:00:00', 3);
insert into cd.bookings(facid, starttime, slots) values(4, '2012-03-20 13:30:00', 2);
insert into cd.bookings(facid, starttime, slots) values(4, '2012-04-01 15:00:00', 2);
insert into cd.bookings(facid, starttime, slots) values(4, '2012-04-10 17:30:00', 2);
insert into cd.bookings(facid, starttime, slots) values(6, '2012-04-20 12:30:00', 2);
insert into cd.bookings(facid, starttime, slots) values(6, '2012-05-01 14:00:00', 2);
insert into cd.bookings(facid, starttime, slots) values(6, '2012-05-10 15:30:00', 2);
insert into cd.bookings(facid, starttime, slots) values(7, '2012-05-20 14:00:00', 2);
insert into cd.bookings(facid, starttime, slots) values(8, '2012-06-01 12:00:00', 1);
insert into cd.bookings(facid, starttime, slots) values(8, '2012-06-10 18:00:00', 1);
insert into cd.bookings(facid, starttime, slots) values(1, '2012-06-20 17:30:00', 3);
insert into cd.bookings(facid, starttime, slots) values(2, '2012-07-01 09:30:00', 3);
insert into cd.bookings(facid, starttime, slots) values(3, '2012-07-10 09:00:00', 2);
insert into cd.bookings(facid, starttime, slots) values(3, '2012-07-20 19:00:00', 2);
insert into cd.bookings(facid, starttime, slots) values(4, '2012-08-01 18:30:00', 2);
insert into cd.bookings(facid, starttime, slots) values(6, '2012-08-10 13:00:00', 2);
insert into cd.bookings(facid, starttime, slots) values(6, '2012-08-20 14:30:00', 2);
insert into cd.bookings(facid, starttime, slots) values(7, '2012-07-05 18:30:00', 2);
insert into cd.bookings(facid, starttime, slots) values(8, '2012-08-05 12:30:00', 1);
insert into cd.bookings(facid, starttime, slots) values(0, '2012-09-06 08:00:00', 3);
insert into cd.bookings(facid, starttime, slots) values(0, '2012-10-06 14:00:00', 3);
insert into cd.bookings(facid, starttime, slots) values(0, '2012-11-06 15:30:00', 3);
insert into cd.bookings(facid, starttime, slots) values(2, '2012-12-06 17:00:00', 3);
insert into cd.bookings(facid, starttime, slots) values(3, '2012-11-06 11:00:00', 2);
insert into cd.bookings(facid, starttime, slots) values(4, '2012-10-06 12:00:00', 2);
insert into cd.bookings(facid, starttime, slots) values(6, '2012-09-06 14:00:00', 2);
insert into cd.bookings(facid, starttime, slots) values(7, '2012-08-06 08:30:00', 2);
insert into cd.bookings(facid, starttime, slots) values(7, '2012-02-06 13:30:00', 2);
insert into cd.bookings(facid, starttime, slots) values(8, '2012-01-06 15:30:00', 1);
insert into cd.bookings(facid, starttime, slots) values(0, '2012-02-07 08:30:00', 3);
insert into cd.bookings(facid, starttime, slots) values(0, '2012-03-07 12:30:00', 3);
insert into cd.bookings(facid, starttime, slots) values(0, '2012-01-07 14:30:00', 3);
insert into cd.bookings(facid, starttime, slots) values(1, '2012-02-07 08:30:00', 3);
insert into cd.bookings(facid, starttime, slots) values(2, '2012-03-07 09:00:00', 3);
insert into cd.bookings(facid, starttime, slots) values(2, '2012-04-07 11:30:00', 3);
insert into cd.bookings(facid, starttime, slots) values(2, '2012-05-07 16:00:00', 3);
insert into cd.bookings(facid, starttime, slots) values(3, '2012-06-07 12:30:00', 2);
insert into cd.bookings(facid, starttime, slots) values(4, '2012-07-07 11:30:00', 2);
insert into cd.bookings(facid, starttime, slots) values(4, '2012-08-07 14:00:00', 2);
insert into cd.bookings(facid, starttime, slots) values(4, '2012-09-07 17:30:00', 2);
insert into cd.bookings(facid, starttime, slots) values(6, '2012-08-07 08:30:00', 2);
insert into cd.bookings(facid, starttime, slots) values(6, '2012-07-07 10:30:00', 2);
insert into cd.bookings(facid, starttime, slots) values(6, '2012-06-07 14:30:00', 2);
insert into cd.bookings(facid, starttime, slots) values(6, '2012-05-07 16:00:00', 2);
insert into cd.bookings(facid, starttime, slots) values(7, '2012-04-07 11:30:00', 2);
insert into cd.bookings(facid, starttime, slots) values(8, '2012-11-07 16:00:00', 1);
insert into cd.bookings(facid, starttime, slots) values(8, '2012-03-07 17:30:00', 2);
insert into cd.bookings(facid, starttime, slots) values(0, '2012-02-08 13:00:00', 3);
insert into cd.bookings(facid, starttime, slots) values(0, '2012-01-08 17:30:00', 3);
insert into cd.bookings(facid, starttime, slots) values(1, '2012-01-08 15:00:00', 3);
insert into cd.bookings(facid, starttime, slots) values(1, '2012-02-08 17:30:00', 3);
insert into cd.bookings(facid, starttime, slots) values(3, '2012-01-08 11:30:00', 2);
insert into cd.bookings(facid, starttime, slots) values(3, '2012-03-08 18:30:00', 2);
insert into cd.bookings(facid, starttime, slots) values(3, '2012-04-08 19:30:00', 2);
insert into cd.bookings(facid, starttime, slots) values(4, '2012-05-08 11:00:00', 2);
insert into cd.bookings(facid, starttime, slots) values(4, '2012-06-08 16:30:00', 2);
insert into cd.bookings(facid, starttime, slots) values(4, '2012-07-08 18:00:00', 2);
insert into cd.bookings(facid, starttime, slots) values(4, '2012-08-08 19:30:00', 2);
insert into cd.bookings(facid, starttime, slots) values(6, '2012-09-08 14:00:00', 2);
insert into cd.bookings(facid, starttime, slots) values(6, '2012-09-08 18:30:00', 2);
insert into cd.bookings(facid, starttime, slots) values(7, '2012-09-08 11:00:00', 2);
insert into cd.bookings(facid, starttime, slots) values(7, '2012-09-08 16:30:00', 2);
insert into cd.bookings(facid, starttime, slots) values(8, '2012-09-08 10:00:00', 1);
insert into cd.bookings(facid, starttime, slots) values(8, '2012-09-08 16:30:00', 1);
insert into cd.bookings(facid, starttime, slots) values(0, '2012-07-09 12:30:00', 3);
insert into cd.bookings(facid, starttime, slots) values(0, '2012-06-09 15:30:00', 3);
insert into cd.bookings(facid, starttime, slots) values(0, '2012-05-09 19:00:00', 3);
insert into cd.bookings(facid, starttime, slots) values(1, '2012-04-09 13:00:00', 3);
insert into cd.bookings(facid, starttime, slots) values(1, '2012-03-09 19:00:00', 3);
insert into cd.bookings(facid, starttime, slots) values(2, '2012-02-09 09:00:00', 6);
insert into cd.bookings(facid, starttime, slots) values(2, '2012-01-09 19:00:00', 3);
insert into cd.bookings(facid, starttime, slots) values(3, '2012-02-09 17:00:00', 2);
insert into cd.bookings(facid, starttime, slots) values(3, '2012-02-09 18:30:00', 2);
insert into cd.bookings(facid, starttime, slots) values(4, '2012-03-09 11:00:00', 2);
insert into cd.bookings(facid, starttime, slots) values(4, '2012-04-09 14:30:00', 2);
insert into cd.bookings(facid, starttime, slots) values(6, '2012-05-09 14:30:00', 2);
insert into cd.bookings(facid, starttime, slots) values(7, '2012-06-09 15:30:00', 2);
insert into cd.bookings(facid, starttime, slots) values(7, '2012-07-09 18:30:00', 4);
insert into cd.bookings(facid, starttime, slots) values(8, '2012-07-09 09:30:00', 1);
insert into cd.bookings(facid, starttime, slots) values(8, '2012-07-09 16:30:00', 1);
insert into cd.bookings(facid, starttime, slots) values(8, '2012-07-09 20:00:00', 1);
insert into cd.bookings(facid, starttime, slots) values(0, '2012-07-10 11:30:00', 3);
insert into cd.bookings(facid, starttime, slots) values(0, '2012-07-10 16:00:00', 3);
insert into cd.bookings(facid, starttime, slots) values(3, '2012-07-10 08:00:00', 2);
insert into cd.bookings(facid, starttime, slots) values(3, '2012-07-10 11:00:00', 2);
insert into cd.bookings(facid, starttime, slots) values(3, '2012-07-10 15:30:00', 2);
insert into cd.bookings(facid, starttime, slots) values(3, '2012-07-10 16:30:00', 2);
insert into cd.bookings(facid, starttime, slots) values(3, '2012-07-10 18:00:00', 2);
insert into cd.bookings(facid, starttime, slots) values(4, '2012-07-10 10:00:00', 2);
insert into cd.bookings(facid, starttime, slots) values(4, '2012-07-10 11:30:00', 2);
insert into cd.bookings(facid, starttime, slots) values(4, '2012-07-10 15:00:00', 2);
insert into cd.bookings(facid, starttime, slots) values(4, '2012-07-10 17:00:00', 4);
insert into cd.bookings(facid, starttime, slots) values(5, '2012-07-10 08:30:00', 2);
insert into cd.bookings(facid, starttime, slots) values(6, '2012-07-10 14:30:00', 2);
insert into cd.bookings(facid, starttime, slots) values(6, '2012-07-10 19:00:00', 2);
insert into cd.bookings(facid, starttime, slots) values(7, '2012-07-10 08:30:00', 2);
insert into cd.bookings(facid, starttime, slots) values(7, '2012-07-10 17:30:00', 2);
insert into cd.bookings(facid, starttime, slots) values(8, '2012-07-10 11:30:00', 1);
insert into cd.bookings(facid, starttime, slots) values(8, '2012-07-10 12:00:00', 1);
insert into cd.bookings(facid, starttime, slots) values(8, '2012-07-10 19:30:00', 1);
insert into cd.bookings(facid, starttime, slots) values(0, '2012-07-11 08:00:00', 3);
insert into cd.bookings(facid, starttime, slots) values(0, '2012-07-11 10:00:00', 3);
insert into cd.bookings(facid, starttime, slots) values(0, '2012-07-11 12:00:00', 3);
insert into cd.bookings(facid, starttime, slots) values(0, '2012-07-11 14:00:00', 3);
insert into cd.bookings(facid, starttime, slots) values(0, '2012-07-11 15:30:00', 3);
insert into cd.bookings(facid, starttime, slots) values(0, '2012-07-11 18:30:00', 3);
insert into cd.bookings(facid, starttime, slots) values(1, '2012-07-11 12:30:00', 3);
insert into cd.bookings(facid, starttime, slots) values(1, '2012-07-11 16:00:00', 3);
insert into cd.bookings(facid, starttime, slots) values(4, '2012-07-11 08:00:00', 2);
insert into cd.bookings(facid, starttime, slots) values(4, '2012-07-11 09:00:00', 2);
insert into cd.bookings(facid, starttime, slots) values(4, '2012-07-11 11:00:00', 2);
insert into cd.bookings(facid, starttime, slots) values(4, '2012-07-11 15:00:00', 2);
insert into cd.bookings(facid, starttime, slots) values(5, '2012-07-11 17:00:00', 2);
insert into cd.bookings(facid, starttime, slots) values(6, '2012-07-11 14:00:00', 2);
insert into cd.bookings(facid, starttime, slots) values(6, '2012-07-11 19:30:00', 2);
insert into cd.bookings(facid, starttime, slots) values(7, '2012-07-11 08:00:00', 2);
insert into cd.bookings(facid, starttime, slots) values(7, '2012-07-11 14:00:00', 2);
insert into cd.bookings(facid, starttime, slots) values(7, '2012-07-11 16:30:00', 2);
insert into cd.bookings(facid, starttime, slots) values(8, '2012-07-11 11:00:00', 1);
insert into cd.bookings(facid, starttime, slots) values(8, '2012-07-11 13:00:00', 1);
insert into cd.bookings(facid, starttime, slots) values(0, '2012-07-12 13:30:00', 3);
insert into cd.bookings(facid, starttime, slots) values(0, '2012-07-12 16:30:00', 3);
insert into cd.bookings(facid, starttime, slots) values(1, '2012-07-12 11:30:00', 3);
insert into cd.bookings(facid, starttime, slots) values(2, '2012-07-12 09:00:00', 3);



select facid, extract(month from starttime) as month, sum(slots) as slots
from cd.bookings
where extract(year from starttime) = 2012
group by facid, month

union

select facid, null, sum(slots) as slots
from cd.bookings
where extract(year from starttime) = 2012
group by facid

union

select null, null, sum(slots) as slots
from cd.bookings
where extract(year from starttime) = 2012
order by facid, month;
