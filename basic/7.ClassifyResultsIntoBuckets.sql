CREATE SCHEMA cd;

create table cd.facilities(
    name varchar(100) not null,
    monthlymaintenance numeric not null
);
 
insert into cd.facilities(name, monthlymaintenance) values('Tennis Court 1', 200);
insert into cd.facilities(name, monthlymaintenance) values('Tennis Court 2', 200);
insert into cd.facilities(name, monthlymaintenance) values('Badminton Court', 50);
insert into cd.facilities(name, monthlymaintenance) values('Table Tennis', 10);
insert into cd.facilities(name, monthlymaintenance) values('Massage Room 1', 3000);
insert into cd.facilities(name, monthlymaintenance) values('Massage Room 2', 3000);
insert into cd.facilities(name, monthlymaintenance) values('Squash Court', 80);
insert into cd.facilities(name, monthlymaintenance) values('Snooker Table', 15);
insert into cd.facilities(name, monthlymaintenance) values('Pool Table', 15);

select name, case when monthlymaintenance <= 100 then 'cheap' else 'expensive' end as cost from cd.facilities;
