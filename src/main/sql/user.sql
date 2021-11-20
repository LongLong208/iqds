use iqds;

drop table if exists user;
create table user(
    userId int primary key auto_increment,
    userName varchar(16),
    userPwd varchar(10)
);
use iqds;
insert into user(userName, userPwd) values
    ("aaa", "123");
