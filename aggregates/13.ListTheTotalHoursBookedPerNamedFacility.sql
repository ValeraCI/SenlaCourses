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
insert into cd.bookings(facid, slots) values(7, 2);
insert into cd.bookings(facid, slots) values(7, 2);
insert into cd.bookings(facid, slots) values(8, 1);
insert into cd.bookings(facid, slots) values(8, 1);
insert into cd.bookings(facid, slots) values(0, 3);
insert into cd.bookings(facid, slots) values(0, 3);
insert into cd.bookings(facid, slots) values(0, 3);
insert into cd.bookings(facid, slots) values(1, 3);
insert into cd.bookings(facid, slots) values(1, 3);
insert into cd.bookings(facid, slots) values(2, 6);
insert into cd.bookings(facid, slots) values(2, 3);
insert into cd.bookings(facid, slots) values(3, 2);
insert into cd.bookings(facid, slots) values(3, 2);
insert into cd.bookings(facid, slots) values(4, 2);
insert into cd.bookings(facid, slots) values(4, 2);
insert into cd.bookings(facid, slots) values(6, 2);
insert into cd.bookings(facid, slots) values(7, 2);
insert into cd.bookings(facid, slots) values(7, 4);
insert into cd.bookings(facid, slots) values(8, 1);
insert into cd.bookings(facid, slots) values(8, 1);
insert into cd.bookings(facid, slots) values(8, 1);
insert into cd.bookings(facid, slots) values(0, 3);
insert into cd.bookings(facid, slots) values(0, 3);
insert into cd.bookings(facid, slots) values(3, 2);
insert into cd.bookings(facid, slots) values(3, 2);
insert into cd.bookings(facid, slots) values(3, 2);
insert into cd.bookings(facid, slots) values(3, 2);
insert into cd.bookings(facid, slots) values(3, 2);
insert into cd.bookings(facid, slots) values(4, 2);
insert into cd.bookings(facid, slots) values(4, 2);

select bk.facid, name, trim(to_char(sum(bk.slots)/2.0, '9999999999999999D99')) as "Total Hours"
from cd.bookings bk join cd.facilities fc on bk.facid = fc.facid
group by bk.facid, fc.name
order by bk.facid