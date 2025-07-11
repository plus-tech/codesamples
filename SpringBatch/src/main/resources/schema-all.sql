create table users (
  id number
 ,username varchar2(64) unique
 ,password varchar2(255) not null
 ,enabled number(1) default(1)
);

create table authorities (
  username varchar(64) not null
 ,authority varchar(64) not null
);

create sequence users_seq
 start with 1
 increment by 1
 nocache
 order
 nocycle;