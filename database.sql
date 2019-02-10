create table courses
(
  courseid          varchar(50)  not null,
  coursename        varchar(100) not null
    primary key,
  coursedescription varchar(200) not null,
  courseduration    int(5)       null
);

create table batches
(
  courseid          varchar(20)  not null,
  coursename        varchar(200) not null,
  batchid           varchar(20)  not null
    primary key,
  batchstartingdate varchar(200) not null,
  batchDescription  varchar(200) not null,
  batchcapacity     int(5)       null,
  constraint batches_ibfk_1
  foreign key (coursename) references courses (coursename)
);

create index coursename
  on batches (coursename);

create table studentdetails
(
  studentid           varchar(50)  not null
    primary key,
  namewithinitials    varchar(100) not null,
  fullname            varchar(200) not null,
  address             varchar(200) not null,
  city                varchar(200) not null,
  telephonenumber     int(10)      null,
  mobile1             int(10)      null,
  email               varchar(200) not null,
  dateofbirth         date         null,
  gender              varchar(20)  not null,
  nic                 varchar(10)  not null,
  school              varchar(200) not null,
  university          varchar(200) not null,
  faculty             varchar(200) not null,
  coursename          varchar(200) not null,
  batchid             varchar(20)  not null,
  higherQualification varchar(100) not null
);

create table gurdian
(
  studentid    varchar(200) not null,
  gurdiantname varchar(300) not null
    primary key,
  telnumber    varchar(10)  not null,
  mob1number   varchar(10)  not null,
  mob2number   varchar(10)  not null,
  email        varchar(300) null,
  designation  varchar(300) null,
  workingplace varchar(300) null,
  address      varchar(300) null,
  constraint gurdian_ibfk_1
  foreign key (studentid) references studentdetails (studentid)
);

create index studentid
  on gurdian (studentid);

create table qualifications
(
  studentid     varchar(200) not null,
  qualification varchar(300) not null,
  institute     varchar(200) not null,
  awarddate     date         null,
  specilization varchar(300) null,
  constraint qualifications_ibfk_1
  foreign key (studentid) references studentdetails (studentid)
);

create index studentid
  on qualifications (studentid);

create table registration
(
  studentid varchar(50) not null,
  batchid   varchar(20) not null,
  constraint registration_ibfk_1
  foreign key (studentid) references studentdetails (studentid),
  constraint registration_ibfk_2
  foreign key (batchid) references batches (batchid)
);

create index batchid
  on registration (batchid);

create index studentid
  on registration (studentid);

