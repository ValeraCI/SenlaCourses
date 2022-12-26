CREATE SCHEMA cd;

create table cd.bookings(
    facid int not null,
    slots int not null
);

insert into cd.bookings(facid, slots) values(3, 2);
insert into cd.bookings(facid, slots) values(4, 2);
insert into cd.bookings(facid, slots) values(6, 2);
insert into cd.bookings(facid, slots) values(7, 2);
insert into cd.bookings(facid, slots) values(8, 1);
insert into cd.bookings(facid, slots) values(8, 1);
insert into cd.bookings(facid, slots) values(0, 3);
insert into cd.bookings(facid, slots) values(0, 3);
insert into cd.bookings(facid, slots) values(4, 2);
insert into cd.bookings(facid, slots) values(4, 2);
insert into cd.bookings(facid, slots) values(4, 2);
insert into cd.bookings(facid, slots) values(6, 2);
insert into cd.bookings(facid, slots) values(6, 2);
insert into cd.bookings(facid, slots) values(6, 2);
insert into cd.bookings(facid, slots) values(7, 2);
insert into cd.bookings(facid, slots) values(8, 1);
insert into cd.bookings(facid, slots) values(8, 1);
insert into cd.bookings(facid, slots) values(1, 3);
insert into cd.bookings(facid, slots) values(2, 3);
insert into cd.bookings(facid, slots) values(3, 2);
insert into cd.bookings(facid, slots) values(3, 2);
insert into cd.bookings(facid, slots) values(4, 2);
insert into cd.bookings(facid, slots) values(6, 2);
insert into cd.bookings(facid, slots) values(6, 2);
insert into cd.bookings(facid, slots) values(7, 2);
insert into cd.bookings(facid, slots) values(8, 1);
insert into cd.bookings(facid, slots) values(0, 3);
insert into cd.bookings(facid, slots) values(0, 3);
insert into cd.bookings(facid, slots) values(0, 3);
insert into cd.bookings(facid, slots) values(2, 3);
insert into cd.bookings(facid, slots) values(3, 2);
insert into cd.bookings(facid, slots) values(4, 2);
insert into cd.bookings(facid, slots) values(6, 2);
insert into cd.bookings(facid, slots) values(7, 2);
insert into cd.bookings(facid, slots) values(7, 2);
insert into cd.bookings(facid, slots) values(8, 1);
insert into cd.bookings(facid, slots) values(0, 3);
insert into cd.bookings(facid, slots) values(0, 3);
insert into cd.bookings(facid, slots) values(0, 3);
insert into cd.bookings(facid, slots) values(1, 3);
insert into cd.bookings(facid, slots) values(2, 3);
insert into cd.bookings(facid, slots) values(2, 3);
insert into cd.bookings(facid, slots) values(2, 3);
insert into cd.bookings(facid, slots) values(3, 2);
insert into cd.bookings(facid, slots) values(4, 2);
insert into cd.bookings(facid, slots) values(4, 2);
insert into cd.bookings(facid, slots) values(4, 2);
insert into cd.bookings(facid, slots) values(6, 2);
insert into cd.bookings(facid, slots) values(6, 2);
insert into cd.bookings(facid, slots) values(6, 2);
insert into cd.bookings(facid, slots) values(6, 2);
insert into cd.bookings(facid, slots) values(7, 2);
insert into cd.bookings(facid, slots) values(8, 1);
insert into cd.bookings(facid, slots) values(8, 2);
insert into cd.bookings(facid, slots) values(0, 3);
insert into cd.bookings(facid, slots) values(0, 3);
insert into cd.bookings(facid, slots) values(1, 3);
insert into cd.bookings(facid, slots) values(1, 3);
insert into cd.bookings(facid, slots) values(3, 2);
insert into cd.bookings(facid, slots) values(3, 2);
insert into cd.bookings(facid, slots) values(3, 2);
insert into cd.bookings(facid, slots) values(4, 2);
insert into cd.bookings(facid, slots) values(4, 2);
insert into cd.bookings(facid, slots) values(4, 2);
insert into cd.bookings(facid, slots) values(4, 2);
insert into cd.bookings(facid, slots) values(6, 2);
insert into cd.bookings(facid, slots) values(6, 2);

select facid, total from(
	select facid, total, rank() over(order by total desc) 
	from(
		select facid, sum(slots) as total
		from cd.bookings
		group by facid
  	) as tb1
) as tb2
where rank = 1;