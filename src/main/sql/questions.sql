use iqds;
-- show variables like '%char%';

alter table knowledge MODIFY knowledgeName TEXT CHARACTER SET utf8;
alter table question MODIFY title TEXT CHARACTER SET utf8;
alter table answerItem MODIFY title TEXT CHARACTER SET utf8;

insert into knowledge(knowledgeName) values('常识');
insert into question(title, questionType, knowledgeId) values("社会主义法实施的基本要求是（）", 0, 1);
insert into answerItem(title, isTrue, questionId) values("司法机关严格适用法律", 0, 1);
insert into answerItem(title, isTrue, questionId) values("国家各类机关依法办事", 0, 1);
insert into answerItem(title, isTrue, questionId) values("各级干部模范带头守法", 0, 1);
insert into answerItem(title, isTrue, questionId) values("全体公民和一切组织自觉守法", 1, 1);
