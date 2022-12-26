CREATE SCHEMA cd;


create table cd.bookings(
    facid int not null,
    slots int not null
);

insert into cd.bookings(facid, slots) values(0, 30);
insert into cd.bookings(facid, slots) values(0, 3);
insert into cd.bookings(facid, slots) values(4, 204);
insert into cd.bookings(facid, slots) values(4, 2);
insert into cd.bookings(facid, slots) values(4, 20);
insert into cd.bookings(facid, slots) values(6, 2);
insert into cd.bookings(facid, slots) values(6, 206);
insert into cd.bookings(facid, slots) values(6, 2);
insert into cd.bookings(facid, slots) values(7, 20);
insert into cd.bookings(facid, slots) values(8, 1);
insert into cd.bookings(facid, slots) values(8, 109);
insert into cd.bookings(facid, slots) values(1, 3);
insert into cd.bookings(facid, slots) values(2, 30);
insert into cd.bookings(facid, slots) values(3, 2);
insert into cd.bookings(facid, slots) values(3, 209);
insert into cd.bookings(facid, slots) values(4, 2);
insert into cd.bookings(facid, slots) values(6, 20);
insert into cd.bookings(facid, slots) values(6, 2);
insert into cd.bookings(facid, slots) values(7, 207);
insert into cd.bookings(facid, slots) values(8, 1);
insert into cd.bookings(facid, slots) values(0, 30);
insert into cd.bookings(facid, slots) values(0, 3);
insert into cd.bookings(facid, slots) values(0, 304);
insert into cd.bookings(facid, slots) values(2, 3);
insert into cd.bookings(facid, slots) values(3, 20);
insert into cd.bookings(facid, slots) values(4, 2);
insert into cd.bookings(facid, slots) values(6, 205);
insert into cd.bookings(facid, slots) values(7, 2);
insert into cd.bookings(facid, slots) values(7, 20);
insert into cd.bookings(facid, slots) values(8, 1);
insert into cd.bookings(facid, slots) values(0, 305);
insert into cd.bookings(facid, slots) values(0, 3);
insert into cd.bookings(facid, slots) values(0, 30);
insert into cd.bookings(facid, slots) values(1, 3);
insert into cd.bookings(facid, slots) values(2, 305);
insert into cd.bookings(facid, slots) values(2, 3);
insert into cd.bookings(facid, slots) values(2, 30);
insert into cd.bookings(facid, slots) values(3, 2);
insert into cd.bookings(facid, slots) values(4, 200);
insert into cd.bookings(facid, slots) values(4, 2);
insert into cd.bookings(facid, slots) values(4, 20);
insert into cd.bookings(facid, slots) values(6, 2);
insert into cd.bookings(facid, slots) values(6, 200);
insert into cd.bookings(facid, slots) values(6, 2);
insert into cd.bookings(facid, slots) values(6, 20);
insert into cd.bookings(facid, slots) values(7, 2);
insert into cd.bookings(facid, slots) values(8, 100);



select facid, sum(slots) as "Total Slots"
from cd.bookings
group by facid
having sum(slots) = (select max(ts) from
						(select facid, sum(slots) as ts
						from cd.bookings
						group by facid) as tb1
					)