use iqds;
drop table if exists manager;
create table manager(
    managerId int primary key auto_increment,
    userId int,
    
    foreign key(userId) references user(userId)
);
use iqds;
insert into manager(userId) values
    (1);
