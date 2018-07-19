drop table sys_group;

drop table sys_group_menu;

drop table sys_group_user;

drop table sys_menu;

drop table sys_user;

create table sys_group (
id                   VARCHAR(50)                    not null,
group_name           VARCHAR(50),
status               CHAR(1),
remark               VARCHAR(4000),
primary key (id)
);

create table sys_group_menu (
id                   VARCHAR(50)                    not null,
menuid               VARCHAR(50),
groupid              VARCHAR(50),
primary key (id)
);

create table sys_group_user (
id                   VARCHAR(50)                    not null,
userid               VARCHAR(50),
groupid              VARCHAR(50),
primary key (id)
);

create table sys_menu (
id                   VARCHAR(50)                    not null,
name                 VARCHAR(50),
pid                  VARCHAR(50),
url                  VARCHAR(50),
type                 CHAR(1),
status               CHAR(1),
seq                  NUMERIC(4,0),
mlevel               NUMERIC(4,0),
icon                 VARCHAR(500),
css                  VARCHAR(500),
remark               VARCHAR(4000),
create_time          VARCHAR(20),
create_by            VARCHAR(50),
primary key (id)
);

create table sys_user (
id                   VARCHAR(50)                    not null,
username             VARCHAR(50),
password             VARCHAR(100),
name                 VARCHAR(50),
tel                  VARCHAR(20),
email                VARCHAR(20),
create_time          VARCHAR(20),
create_by            VARCHAR(50),
status               CHAR(1),
remark               VARCHAR(4000),
primary key (id)
);

