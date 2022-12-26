CREATE SCHEMA cd;

create table cd.facilities(
    facid int primary key,
    membercost numeric not null,
    guestcost numeric not null
);
 
insert into cd.facilities(facid, membercost, guestcost) values(0, 5, 25);
insert into cd.facilities(facid, membercost, guestcost) values(1, 5, 25);
insert into cd.facilities(facid, membercost, guestcost) values(2, 0, 15.5);
insert into cd.facilities(facid, membercost, guestcost) values(3, 10, 15);
insert into cd.facilities(facid, membercost, guestcost) values(4, 35, 80);
insert into cd.facilities(facid, membercost, guestcost) values(5, 35, 80);
insert into cd.facilities(facid, membercost, guestcost) values(6, 3.5, 17.5);
insert into cd.facilities(facid, membercost, guestcost) values(7, 0, 5);
insert into cd.facilities(facid, membercost, guestcost) values(8, 10, 15);


create table cd.bookings(
    facid int references cd.facilities(facid),
    memid int not null,
    slots int not null,
    starttime timestamp not null
);

insert into cd.bookings(facid, memid, starttime, slots) values(3, 1, '2012-07-01 11:00:00', 2);
insert into cd.bookings(facid, memid, starttime, slots) values(4, 1, '2012-08-01 08:00:00', 2);
insert into cd.bookings(facid, memid, starttime, slots) values(6, 0, '2012-08-01 18:00:00', 2);
insert into cd.bookings(facid, memid, starttime, slots) values(7, 1, '2012-08-02 19:00:00', 2);
insert into cd.bookings(facid, memid, starttime, slots) values(8, 1, '2012-08-02 10:00:00', 1);
insert into cd.bookings(facid, memid, starttime, slots) values(8, 1, '2012-08-03 15:00:00', 1);
insert into cd.bookings(facid, memid, starttime, slots) values(0, 2, '2012-08-04 09:00:00', 3);
insert into cd.bookings(facid, memid, starttime, slots) values(0, 2, '2012-08-04 15:00:00', 3);
insert into cd.bookings(facid, memid, starttime, slots) values(4, 3, '2012-08-04 13:30:00', 2);
insert into cd.bookings(facid, memid, starttime, slots) values(4, 0, '2012-08-04 15:00:00', 2);
insert into cd.bookings(facid, memid, starttime, slots) values(4, 0, '2012-08-04 17:30:00', 2);
insert into cd.bookings(facid, memid, starttime, slots) values(6, 0, '2012-08-04 12:30:00', 2);
insert into cd.bookings(facid, memid, starttime, slots) values(6, 0, '2012-08-04 14:00:00', 2);
insert into cd.bookings(facid, memid, starttime, slots) values(6, 1, '2012-08-04 15:30:00', 2);
insert into cd.bookings(facid, memid, starttime, slots) values(7, 2, '2012-08-04 14:00:00', 2);
insert into cd.bookings(facid, memid, starttime, slots) values(8, 2, '2012-08-04 12:00:00', 1);
insert into cd.bookings(facid, memid, starttime, slots) values(8, 3, '2012-08-04 18:00:00', 1);
insert into cd.bookings(facid, memid, starttime, slots) values(1, 0, '2012-08-05 17:30:00', 3);
insert into cd.bookings(facid, memid, starttime, slots) values(2, 1, '2012-08-05 09:30:00', 3);
insert into cd.bookings(facid, memid, starttime, slots) values(3, 3, '2012-08-05 09:00:00', 2);
insert into cd.bookings(facid, memid, starttime, slots) values(3, 1, '2012-08-05 19:00:00', 2);
insert into cd.bookings(facid, memid, starttime, slots) values(4, 3, '2012-08-05 18:30:00', 2);
insert into cd.bookings(facid, memid, starttime, slots) values(6, 0, '2012-08-05 13:00:00', 2);
insert into cd.bookings(facid, memid, starttime, slots) values(6, 1, '2012-08-05 14:30:00', 2);
insert into cd.bookings(facid, memid, starttime, slots) values(7, 2, '2012-08-05 18:30:00', 2);
insert into cd.bookings(facid, memid, starttime, slots) values(8, 3, '2012-08-05 12:30:00', 1);
insert into cd.bookings(facid, memid, starttime, slots) values(0, 0, '2012-08-06 08:00:00', 3);
insert into cd.bookings(facid, memid, starttime, slots) values(0, 0, '2012-08-06 14:00:00', 3);
insert into cd.bookings(facid, memid, starttime, slots) values(0, 2, '2012-08-06 15:30:00', 3);
insert into cd.bookings(facid, memid, starttime, slots) values(2, 1, '2012-08-06 17:00:00', 3);
insert into cd.bookings(facid, memid, starttime, slots) values(3, 1, '2012-08-06 11:00:00', 2);
insert into cd.bookings(facid, memid, starttime, slots) values(4, 3, '2012-08-06 12:00:00', 2);
insert into cd.bookings(facid, memid, starttime, slots) values(6, 1, '2012-08-06 14:00:00', 2);
insert into cd.bookings(facid, memid, starttime, slots) values(7, 2, '2012-08-06 08:30:00', 2);
insert into cd.bookings(facid, memid, starttime, slots) values(7, 2, '2012-08-06 13:30:00', 2);
insert into cd.bookings(facid, memid, starttime, slots) values(8, 3, '2012-08-06 15:30:00', 1);
insert into cd.bookings(facid, memid, starttime, slots) values(0, 2, '2012-08-07 08:30:00', 3);
insert into cd.bookings(facid, memid, starttime, slots) values(0, 0, '2012-08-07 12:30:00', 3);
insert into cd.bookings(facid, memid, starttime, slots) values(0, 2, '2012-08-07 14:30:00', 3);
insert into cd.bookings(facid, memid, starttime, slots) values(1, 3, '2012-08-07 08:30:00', 3);
insert into cd.bookings(facid, memid, starttime, slots) values(2, 1, '2012-08-07 09:00:00', 3);
insert into cd.bookings(facid, memid, starttime, slots) values(2, 1, '2012-08-07 11:30:00', 3);
insert into cd.bookings(facid, memid, starttime, slots) values(2, 1, '2012-08-07 16:00:00', 3);
insert into cd.bookings(facid, memid, starttime, slots) values(3, 2, '2012-08-07 12:30:00', 2);
insert into cd.bookings(facid, memid, starttime, slots) values(4, 3, '2012-08-07 11:30:00', 2);
insert into cd.bookings(facid, memid, starttime, slots) values(4, 3, '2012-08-07 14:00:00', 2);
insert into cd.bookings(facid, memid, starttime, slots) values(4, 0, '2012-08-07 17:30:00', 2);
insert into cd.bookings(facid, memid, starttime, slots) values(6, 0, '2012-08-07 08:30:00', 2);
insert into cd.bookings(facid, memid, starttime, slots) values(6, 1, '2012-08-07 10:30:00', 2);
insert into cd.bookings(facid, memid, starttime, slots) values(6, 1, '2012-08-07 14:30:00', 2);
insert into cd.bookings(facid, memid, starttime, slots) values(6, 0, '2012-08-07 16:00:00', 2);
insert into cd.bookings(facid, memid, starttime, slots) values(7, 2, '2012-08-07 11:30:00', 2);
insert into cd.bookings(facid, memid, starttime, slots) values(8, 3, '2012-08-07 16:00:00', 1);
insert into cd.bookings(facid, memid, starttime, slots) values(8, 3, '2012-08-07 17:30:00', 2);
insert into cd.bookings(facid, memid, starttime, slots) values(0, 3, '2012-08-08 13:00:00', 3);
insert into cd.bookings(facid, memid, starttime, slots) values(0, 2, '2012-08-08 17:30:00', 3);
insert into cd.bookings(facid, memid, starttime, slots) values(1, 1, '2012-08-08 15:00:00', 3);
insert into cd.bookings(facid, memid, starttime, slots) values(1, 1, '2012-08-08 17:30:00', 3);
insert into cd.bookings(facid, memid, starttime, slots) values(3, 1, '2012-08-08 11:30:00', 2);
insert into cd.bookings(facid, memid, starttime, slots) values(3, 3, '2012-08-08 18:30:00', 2);
insert into cd.bookings(facid, memid, starttime, slots) values(3, 1, '2012-08-08 19:30:00', 2);
insert into cd.bookings(facid, memid, starttime, slots) values(4, 0, '2012-08-08 11:00:00', 2);
insert into cd.bookings(facid, memid, starttime, slots) values(4, 2, '2012-08-08 16:30:00', 2);
insert into cd.bookings(facid, memid, starttime, slots) values(4, 0, '2012-08-08 18:00:00', 2);
insert into cd.bookings(facid, memid, starttime, slots) values(4, 0, '2012-08-08 19:30:00', 2);
insert into cd.bookings(facid, memid, starttime, slots) values(6, 0, '2012-08-08 14:00:00', 2);
insert into cd.bookings(facid, memid, starttime, slots) values(6, 0, '2012-08-08 18:30:00', 2);
insert into cd.bookings(facid, memid, starttime, slots) values(7, 2, '2012-08-08 11:00:00', 2);
insert into cd.bookings(facid, memid, starttime, slots) values(7, 1, '2012-08-08 16:30:00', 2);
insert into cd.bookings(facid, memid, starttime, slots) values(8, 3, '2012-08-08 10:00:00', 1);
insert into cd.bookings(facid, memid, starttime, slots) values(8, 3, '2012-08-08 16:30:00', 1);
insert into cd.bookings(facid, memid, starttime, slots) values(0, 2, '2012-08-09 12:30:00', 3);
insert into cd.bookings(facid, memid, starttime, slots) values(0, 2, '2012-08-09 15:30:00', 3);
insert into cd.bookings(facid, memid, starttime, slots) values(0, 2, '2012-08-09 19:00:00', 3);
insert into cd.bookings(facid, memid, starttime, slots) values(1, 0, '2012-08-09 13:00:00', 3);
insert into cd.bookings(facid, memid, starttime, slots) values(1, 1, '2012-08-09 19:00:00', 3);
insert into cd.bookings(facid, memid, starttime, slots) values(2, 1, '2012-08-09 09:00:00', 6);
insert into cd.bookings(facid, memid, starttime, slots) values(2, 0, '2012-08-09 19:00:00', 3);
insert into cd.bookings(facid, memid, starttime, slots) values(3, 3, '2012-08-09 17:00:00', 2);
insert into cd.bookings(facid, memid, starttime, slots) values(3, 3, '2012-08-09 18:30:00', 2);
insert into cd.bookings(facid, memid, starttime, slots) values(4, 2, '2012-08-09 11:00:00', 2);
insert into cd.bookings(facid, memid, starttime, slots) values(4, 3, '2012-08-09 14:30:00', 2);
insert into cd.bookings(facid, memid, starttime, slots) values(6, 0, '2012-08-09 14:30:00', 2);
insert into cd.bookings(facid, memid, starttime, slots) values(7, 1, '2012-08-09 15:30:00', 2);
insert into cd.bookings(facid, memid, starttime, slots) values(7, 0, '2012-08-09 18:30:00', 4);
insert into cd.bookings(facid, memid, starttime, slots) values(8, 3, '2012-08-09 09:30:00', 1);
insert into cd.bookings(facid, memid, starttime, slots) values(8, 3, '2012-08-09 16:30:00', 1);
insert into cd.bookings(facid, memid, starttime, slots) values(8, 3, '2012-08-09 20:00:00', 1);
insert into cd.bookings(facid, memid, starttime, slots) values(0, 0, '2012-08-10 11:30:00', 3);
insert into cd.bookings(facid, memid, starttime, slots) values(0, 0, '2012-08-10 16:00:00', 3);
insert into cd.bookings(facid, memid, starttime, slots) values(3, 2, '2012-08-10 08:00:00', 2);
insert into cd.bookings(facid, memid, starttime, slots) values(3, 1, '2012-08-10 11:00:00', 2);
insert into cd.bookings(facid, memid, starttime, slots) values(3, 3, '2012-08-10 15:30:00', 2);
insert into cd.bookings(facid, memid, starttime, slots) values(3, 2, '2012-08-10 16:30:00', 2);
insert into cd.bookings(facid, memid, starttime, slots) values(3, 1, '2012-08-10 18:00:00', 2);
insert into cd.bookings(facid, memid, starttime, slots) values(4, 0, '2012-08-10 10:00:00', 2);
insert into cd.bookings(facid, memid, starttime, slots) values(4, 4, '2012-08-10 11:30:00', 2);
insert into cd.bookings(facid, memid, starttime, slots) values(4, 0, '2012-08-10 15:00:00', 2);
insert into cd.bookings(facid, memid, starttime, slots) values(4, 3, '2012-08-10 17:00:00', 4);
insert into cd.bookings(facid, memid, starttime, slots) values(5, 0, '2012-08-10 08:30:00', 2);
insert into cd.bookings(facid, memid, starttime, slots) values(6, 0, '2012-08-10 14:30:00', 2);
insert into cd.bookings(facid, memid, starttime, slots) values(6, 0, '2012-08-10 19:00:00', 2);
insert into cd.bookings(facid, memid, starttime, slots) values(7, 4, '2012-08-10 08:30:00', 2);
insert into cd.bookings(facid, memid, starttime, slots) values(7, 2, '2012-08-10 17:30:00', 2);
insert into cd.bookings(facid, memid, starttime, slots) values(8, 0, '2012-08-10 11:30:00', 1);
insert into cd.bookings(facid, memid, starttime, slots) values(8, 3, '2012-08-10 12:00:00', 1);
insert into cd.bookings(facid, memid, starttime, slots) values(8, 3, '2012-08-10 19:30:00', 1);
insert into cd.bookings(facid, memid, starttime, slots) values(0, 4, '2012-08-11 08:00:00', 3);
insert into cd.bookings(facid, memid, starttime, slots) values(0, 2, '2012-08-11 10:00:00', 3);
insert into cd.bookings(facid, memid, starttime, slots) values(0, 0, '2012-08-11 12:00:00', 3);
insert into cd.bookings(facid, memid, starttime, slots) values(0, 0, '2012-08-11 14:00:00', 3);
insert into cd.bookings(facid, memid, starttime, slots) values(0, 2, '2012-08-11 15:30:00', 3);
insert into cd.bookings(facid, memid, starttime, slots) values(0, 2, '2012-08-11 18:30:00', 3);
insert into cd.bookings(facid, memid, starttime, slots) values(1, 0, '2012-08-11 12:30:00', 3);
insert into cd.bookings(facid, memid, starttime, slots) values(1, 0, '2012-08-11 16:00:00', 3);
insert into cd.bookings(facid, memid, starttime, slots) values(4, 1, '2012-08-11 08:00:00', 2);
insert into cd.bookings(facid, memid, starttime, slots) values(4, 0, '2012-08-11 09:00:00', 2);
insert into cd.bookings(facid, memid, starttime, slots) values(4, 3, '2012-08-11 11:00:00', 2);
insert into cd.bookings(facid, memid, starttime, slots) values(4, 0, '2012-08-11 15:00:00', 2);
insert into cd.bookings(facid, memid, starttime, slots) values(5, 4, '2012-08-12 17:00:00', 2);
insert into cd.bookings(facid, memid, starttime, slots) values(6, 0, '2012-08-13 14:00:00', 2);
insert into cd.bookings(facid, memid, starttime, slots) values(6, 0, '2012-08-14 19:30:00', 2);
insert into cd.bookings(facid, memid, starttime, slots) values(7, 0, '2012-08-15 08:00:00', 2);
insert into cd.bookings(facid, memid, starttime, slots) values(7, 0, '2012-08-16 14:00:00', 2);
insert into cd.bookings(facid, memid, starttime, slots) values(7, 0, '2012-08-17 16:30:00', 2);
insert into cd.bookings(facid, memid, starttime, slots) values(8, 4, '2012-08-18 11:00:00', 1);
insert into cd.bookings(facid, memid, starttime, slots) values(8, 3, '2012-08-19 13:00:00', 1);
insert into cd.bookings(facid, memid, starttime, slots) values(0, 0, '2012-08-20 13:30:00', 3);
insert into cd.bookings(facid, memid, starttime, slots) values(0, 2, '2012-08-21 16:30:00', 3);
insert into cd.bookings(facid, memid, starttime, slots) values(1, 1, '2012-08-22 11:30:00', 3);
insert into cd.bookings(facid, memid, starttime, slots) values(2, 1, '2012-08-23 09:00:00', 3);
insert into cd.bookings(facid, memid, starttime, slots) values(2, 1, '2012-08-24 18:30:00', 3);
insert into cd.bookings(facid, memid, starttime, slots) values(3, 3, '2012-08-25 18:00:00', 2);
insert into cd.bookings(facid, memid, starttime, slots) values(4, 1, '2012-08-26 16:00:00', 2);
insert into cd.bookings(facid, memid, starttime, slots) values(6, 0, '2012-08-27 12:00:00', 4);
insert into cd.bookings(facid, memid, starttime, slots) values(7, 2, '2012-08-28 08:00:00', 2);
insert into cd.bookings(facid, memid, starttime, slots) values(7, 4, '2012-08-29 13:30:00', 2);
insert into cd.bookings(facid, memid, starttime, slots) values(7, 4, '2012-08-30 16:00:00', 2);
insert into cd.bookings(facid, memid, starttime, slots) values(8, 3, '2012-08-31 16:30:00', 1);
insert into cd.bookings(facid, memid, starttime, slots) values(0, 2, '2012-08-31 10:30:00', 3);
insert into cd.bookings(facid, memid, starttime, slots) values(0, 4, '2012-08-30 14:00:00', 3);
insert into cd.bookings(facid, memid, starttime, slots) values(0, 3, '2012-08-29 17:00:00', 3);
insert into cd.bookings(facid, memid, starttime, slots) values(1, 1, '2012-08-28 15:00:00', 3);
insert into cd.bookings(facid, memid, starttime, slots) values(2, 1, '2012-08-27 09:00:00', 3);
insert into cd.bookings(facid, memid, starttime, slots) values(2, 0, '2012-08-26 15:00:00', 3);
insert into cd.bookings(facid, memid, starttime, slots) values(2, 1, '2012-08-25 16:30:00', 3);
insert into cd.bookings(facid, memid, starttime, slots) values(4, 0, '2012-08-24 11:00:00', 2);
insert into cd.bookings(facid, memid, starttime, slots) values(4, 0, '2012-08-23 13:30:00', 2);
insert into cd.bookings(facid, memid, starttime, slots) values(4, 0, '2012-08-22 15:00:00', 2);
insert into cd.bookings(facid, memid, starttime, slots) values(4, 3, '2012-08-21 16:00:00', 2);
insert into cd.bookings(facid, memid, starttime, slots) values(4, 4, '2012-08-20 17:30:00', 2);
insert into cd.bookings(facid, memid, starttime, slots) values(6, 0, '2012-08-19 09:30:00', 2);
insert into cd.bookings(facid, memid, starttime, slots) values(7, 0, '2012-08-18 08:00:00', 2);
insert into cd.bookings(facid, memid, starttime, slots) values(7, 1, '2012-08-17 11:00:00', 2);
insert into cd.bookings(facid, memid, starttime, slots) values(7, 4, '2012-08-16 12:30:00', 2);
insert into cd.bookings(facid, memid, starttime, slots) values(8, 0, '2012-08-15 15:30:00', 1);
insert into cd.bookings(facid, memid, starttime, slots) values(8, 2, '2012-08-14 18:30:00', 1);
insert into cd.bookings(facid, memid, starttime, slots) values(0, 2, '2012-08-13 08:30:00', 3);
insert into cd.bookings(facid, memid, starttime, slots) values(0, 4, '2012-08-14 11:30:00', 3);
insert into cd.bookings(facid, memid, starttime, slots) values(0, 3, '2012-08-15 15:00:00', 3);
insert into cd.bookings(facid, memid, starttime, slots) values(1, 3, '2012-08-16 10:30:00', 3);
insert into cd.bookings(facid, memid, starttime, slots) values(1, 3, '2012-08-17 12:30:00', 3);

select date, avgrev from (
	select 	dategen.date as date,
		avg(revdata.rev) over(order by dategen.date rows 14 preceding) as avgrev
	from
		(select cast(generate_series(timestamp '2012-07-10', '2012-08-31','1 day') as date) as date
		) as dategen
		left join
			(select cast(starttime as date) as date,
				sum(case
					when memid = 0 then slots * guestcost
					else slots * membercost
				end) as rev

				from cd.bookings bk
				join cd.facilities fc
					on bk.facid = fc.facid
				group by cast(starttime as date)
			) as revdata
			on dategen.date = revdata.date
	) as subq
	where date >= '2012-08-01'
order by date;