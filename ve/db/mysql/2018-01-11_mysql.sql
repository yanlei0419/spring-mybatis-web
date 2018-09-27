/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2018/1/11 17:25:28                           */
/*==============================================================*/


drop table if exists sys_group;

drop table if exists sys_group_menu;

drop table if exists sys_group_user;

drop table if exists sys_menu;

drop table if exists sys_user;

/*==============================================================*/
/* Table: sys_group                                             */
/*==============================================================*/
create table sys_group
(
   id                   varchar(50) not null comment '主键',
   group_name           varchar(50) comment '组名',
   status               char(1),
   remark               varchar(4000) comment '备注',
   primary key (id)
);

alter table sys_group comment '角色';

/*==============================================================*/
/* Table: sys_group_menu                                        */
/*==============================================================*/
create table sys_group_menu
(
   id                   varchar(50) not null comment '主键',
   menuid               varchar(50) comment '菜单id',
   groupid              varchar(50) comment '组id',
   primary key (id)
);

alter table sys_group_menu comment '菜单与角色关系';

/*==============================================================*/
/* Table: sys_group_user                                        */
/*==============================================================*/
create table sys_group_user
(
   id                   varchar(50) not null comment '主键',
   userid               varchar(50) comment '用户id',
   groupid              varchar(50) comment '组id',
   primary key (id)
);

alter table sys_group_user comment '用户与角色关系表';

/*==============================================================*/
/* Table: sys_menu                                              */
/*==============================================================*/
create table sys_menu
(
   id                   varchar(50) not null comment '主键',
   name                 varchar(50) comment '名称',
   pid                  varchar(50) comment '父菜单',
   url                  varchar(50) comment '地址',
   type                 char(1) comment '菜单类别',
   status               char(1) comment '是否启用',
   seq                  numeric(4,0),
   mlevel               numeric(4,0),
   icon                 varchar(500),
   css                  varchar(500),
   remark               varchar(4000) comment '备注',
   create_time          varchar(20) comment '创建时间',
   create_by            varchar(50) comment '创建人',
   primary key (id)
);

alter table sys_menu comment '菜单表';

/*==============================================================*/
/* Table: sys_user                                              */
/*==============================================================*/
create table sys_user
(
   id                   varchar(50) not null comment '主键',
   username             varchar(50) comment '用户名',
   password             varchar(100) comment '密码',
   name                 varchar(50) comment '姓名',
   tel                  varchar(20) comment '联系电话',
   email                varchar(20) comment '邮箱',
   create_time          varchar(20) comment '创建时间',
   create_by            varchar(50) comment '创建人',
   status               char(1) comment '启用状态',
   remark               varchar(4000) comment '备注',
   primary key (id)
);

alter table sys_user comment '用户表';
drop table sys_log cascade constraints;

/*==============================================================*/
/* Table: sys_log                                               */
/*==============================================================*/
create table sys_log 
(
   id                   VARCHAR2(50)         not null,
   operate_module       VARCHAR2(400),
   operate_content      VARCHAR2(4000),
   operate_type         CHAR(1),
   operate_by           VARCHAR2(50),
   operate_time         VARCHAR2(20),
   operate_ip           VARCHAR2(50),
   operate_remark       VARCHAR2(4000),
   constraint PK_SYS_LOG primary key (id)
);

comment on table sys_log is
'操作日志表';

comment on column sys_log.id is'主键';

comment on column sys_log.operate_module is'模块名';

comment on column sys_log.operate_content is'操作内容';

comment on column sys_log.operate_type is'操作类型';

comment on column sys_log.operate_by is'操作人';

comment on column sys_log.operate_time is'操作时间';

comment on column sys_log.operate_ip is'操作人ip';

comment on column sys_log.operate_remark is'备注';

