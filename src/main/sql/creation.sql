drop database if exists iqds;
create database iqds;

SET FOREIGN_KEY_CHECKS = 0;

-- 用户表
use iqds;
drop table if exists user;
create table user(
    userId int primary key auto_increment,
    userName varchar(16),
    userPwd varchar(10)
);

-- 管理员表
use iqds;
drop table if exists manager;
create table manager(
    managerId int primary key auto_increment,
    userId int,
    
    foreign key(userId) references user(userId)
);

-- 题目表
use iqds;
drop table if exists question;
create table question(
    questionId int primary key auto_increment,
    title varchar(256),
    questionType int,
    knowledgeId int,

    foreign key(knowledgeId) references knowledge(knowledgeId)
);

-- 答案选项表
use iqds;

drop table if exists answerItem;
create table answerItem(
    answerItemId int primary key auto_increment,
    title varchar(1024),
    isTrue boolean,
    questionId int,

    foreign key(questionId) references question(questionId)
);

-- 知识点表
use iqds;
SET FOREIGN_KEY_CHECKS = 0;
drop table if exists knowledge;
SET FOREIGN_KEY_CHECKS = 1;
create table knowledge(
    knowledgeId int primary key auto_increment,
    knowledgeName varchar(256)
);


-- 答题表
use iqds;
drop table if exists questionDone;
create table questionDone(
    questionId int,
    userId int,
    doneTimes int default '0',
    correctTimes int default '0',

    primary key(questionId, userId),
    foreign key(userId) references user(userId),
    foreign key(questionId) references question(questionId)
);


/**** TEMPLE ****
use iqds;
drop table if exists ___;
create table ___(

);
**** TEMPLE ****/

SET FOREIGN_KEY_CHECKS = 1;
