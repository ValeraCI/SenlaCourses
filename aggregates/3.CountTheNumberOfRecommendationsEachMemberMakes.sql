CREATE SCHEMA cd;

create table cd.members(
    recommendedby int
);

insert into cd.members(recommendedby) values(null);
insert into cd.members(recommendedby) values(null);
insert into cd.members(recommendedby) values(null);
insert into cd.members(recommendedby) values(null);
insert into cd.members(recommendedby) values(1);
insert into cd.members(recommendedby) values(1);
insert into cd.members(recommendedby) values(null);
insert into cd.members(recommendedby) values(4);
insert into cd.members(recommendedby) values(3);
insert into cd.members(recommendedby) values(6);
insert into cd.members(recommendedby) values(1);
insert into cd.members(recommendedby) values(4);
insert into cd.members(recommendedby) values(9);
insert into cd.members(recommendedby) values(null);
insert into cd.members(recommendedby) values(1);
insert into cd.members(recommendedby) values(9);
insert into cd.members(recommendedby) values(13);
insert into cd.members(recommendedby) values(13);
insert into cd.members(recommendedby) values(5);
insert into cd.members(recommendedby) values(1);
insert into cd.members(recommendedby) values(16);
insert into cd.members(recommendedby) values(15);
insert into cd.members(recommendedby) values(11);
insert into cd.members(recommendedby) values(20);
insert into cd.members(recommendedby) values(null);
insert into cd.members(recommendedby) values(2);
insert into cd.members(recommendedby) values(2);
insert into cd.members(recommendedby) values(null);
insert into cd.members(recommendedby) values(30);
insert into cd.members(recommendedby) values(2);
insert into cd.members(recommendedby) values(null);


select recommendedby, count(*) 
from cd.members
where recommendedby is not null
group by recommendedby
order by recommendedby;