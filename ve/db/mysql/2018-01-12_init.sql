prompt PL/SQL Developer import file
prompt Created on 2018年1月12日 by ve
set feedback off
set define off
prompt Disabling triggers for SYS_GROUP...
alter table SYS_GROUP disable all triggers;
prompt Disabling triggers for SYS_GROUP_MENU...
alter table SYS_GROUP_MENU disable all triggers;
prompt Disabling triggers for SYS_GROUP_USER...
alter table SYS_GROUP_USER disable all triggers;
prompt Disabling triggers for SYS_MENU...
alter table SYS_MENU disable all triggers;
prompt Disabling triggers for SYS_USER...
alter table SYS_USER disable all triggers;
prompt Deleting SYS_USER...
delete from SYS_USER;
commit;
prompt Deleting SYS_MENU...
delete from SYS_MENU;
commit;
prompt Deleting SYS_GROUP_USER...
delete from SYS_GROUP_USER;
commit;
prompt Deleting SYS_GROUP_MENU...
delete from SYS_GROUP_MENU;
commit;
prompt Deleting SYS_GROUP...
delete from SYS_GROUP;
commit;
insert into SYS_GROUP (ID, GROUP_NAME, STATUS, REMARK)
values ('e65334aa-5462-4cdd-acd4-48d8eafca867', '系统管理员', '1', null);
insert into SYS_GROUP (ID, GROUP_NAME, STATUS, REMARK)
values ('847f988d-4b98-41ad-b950-3499fa49a6d8', '普通管理员', '1', null);
insert into SYS_GROUP (ID, GROUP_NAME, STATUS, REMARK)
values ('cabd2d12-706f-4ef2-82bf-554222a7875a', '用户', '1', null);
commit;
prompt 3 records loaded
prompt Loading SYS_GROUP_MENU...
insert into SYS_GROUP_MENU (ID, MENUID, GROUPID)
values ('d9b43ff3-1aa2-491a-8848-1fcfdc9d9c65', '4', '847f988d-4b98-41ad-b950-3499fa49a6d8');
insert into SYS_GROUP_MENU (ID, MENUID, GROUPID)
values ('9b209d2b-e421-4228-b2a5-8e58ed357870', '41', '847f988d-4b98-41ad-b950-3499fa49a6d8');
insert into SYS_GROUP_MENU (ID, MENUID, GROUPID)
values ('45228f68-9605-4e58-a5a0-617e59d7dd89', '42', '847f988d-4b98-41ad-b950-3499fa49a6d8');
insert into SYS_GROUP_MENU (ID, MENUID, GROUPID)
values ('9251ae8a-dbd9-445f-99c5-763c889a4be4', '46', '847f988d-4b98-41ad-b950-3499fa49a6d8');
insert into SYS_GROUP_MENU (ID, MENUID, GROUPID)
values ('c3b34b03-f512-4390-bff6-fcbb8f3ee238', '43', '847f988d-4b98-41ad-b950-3499fa49a6d8');
insert into SYS_GROUP_MENU (ID, MENUID, GROUPID)
values ('99d2390b-d631-4dd6-8e7a-c904f4ef252f', '44', '847f988d-4b98-41ad-b950-3499fa49a6d8');
insert into SYS_GROUP_MENU (ID, MENUID, GROUPID)
values ('7236a181-0f3c-41d9-9b74-7a91858c32d3', '45', '847f988d-4b98-41ad-b950-3499fa49a6d8');
insert into SYS_GROUP_MENU (ID, MENUID, GROUPID)
values ('d51610c6-d4be-4661-9a8d-2c204758c8b2', '47', '847f988d-4b98-41ad-b950-3499fa49a6d8');
insert into SYS_GROUP_MENU (ID, MENUID, GROUPID)
values ('37ba7d26-95d0-4394-acf4-00570fcc498c', '1', '847f988d-4b98-41ad-b950-3499fa49a6d8');
insert into SYS_GROUP_MENU (ID, MENUID, GROUPID)
values ('5fc44dec-a134-460c-9bc5-1e86d30184ba', '11', '847f988d-4b98-41ad-b950-3499fa49a6d8');
insert into SYS_GROUP_MENU (ID, MENUID, GROUPID)
values ('8b7b743c-eb29-4543-b1cc-1e747c8eee88', '12', '847f988d-4b98-41ad-b950-3499fa49a6d8');
insert into SYS_GROUP_MENU (ID, MENUID, GROUPID)
values ('476d35ea-cc5b-41e1-96ee-666279208422', '2', '847f988d-4b98-41ad-b950-3499fa49a6d8');
insert into SYS_GROUP_MENU (ID, MENUID, GROUPID)
values ('54b47331-c177-461e-844d-3d6c0db26a67', '21', '847f988d-4b98-41ad-b950-3499fa49a6d8');
insert into SYS_GROUP_MENU (ID, MENUID, GROUPID)
values ('0b8fb09c-cdb0-4e6b-909f-9d6746e49a63', '26', '847f988d-4b98-41ad-b950-3499fa49a6d8');
insert into SYS_GROUP_MENU (ID, MENUID, GROUPID)
values ('1776ad1a-615e-4074-8ca8-5329e05d2f3d', '23', '847f988d-4b98-41ad-b950-3499fa49a6d8');
insert into SYS_GROUP_MENU (ID, MENUID, GROUPID)
values ('4dd921bd-059e-4970-929c-247c4e183d4a', '24', '847f988d-4b98-41ad-b950-3499fa49a6d8');
insert into SYS_GROUP_MENU (ID, MENUID, GROUPID)
values ('6265c1de-187c-48b2-a4ce-d395a5990b19', '25', '847f988d-4b98-41ad-b950-3499fa49a6d8');
insert into SYS_GROUP_MENU (ID, MENUID, GROUPID)
values ('49983f3a-c9ca-4073-ba27-124ce8e3fb52', '3', '847f988d-4b98-41ad-b950-3499fa49a6d8');
insert into SYS_GROUP_MENU (ID, MENUID, GROUPID)
values ('6a93479a-371b-4643-ab6c-3788250e01a5', '31', '847f988d-4b98-41ad-b950-3499fa49a6d8');
insert into SYS_GROUP_MENU (ID, MENUID, GROUPID)
values ('a7d04d33-bd3d-4170-bb8c-8bb5826b1425', '5', 'e65334aa-5462-4cdd-acd4-48d8eafca867');
insert into SYS_GROUP_MENU (ID, MENUID, GROUPID)
values ('ba41d0a0-cd9b-4cd1-9441-da6410a7dfc2', '52', 'e65334aa-5462-4cdd-acd4-48d8eafca867');
insert into SYS_GROUP_MENU (ID, MENUID, GROUPID)
values ('14da705b-368e-43c0-8bac-1f22e3d19d2c', '51', 'e65334aa-5462-4cdd-acd4-48d8eafca867');
insert into SYS_GROUP_MENU (ID, MENUID, GROUPID)
values ('13ed43ed-334e-4cf3-bcb3-a9db635ac69c', '53', 'e65334aa-5462-4cdd-acd4-48d8eafca867');
insert into SYS_GROUP_MENU (ID, MENUID, GROUPID)
values ('3967a826-4775-4714-8049-186553736a8a', '56', 'e65334aa-5462-4cdd-acd4-48d8eafca867');
insert into SYS_GROUP_MENU (ID, MENUID, GROUPID)
values ('d3b1fb52-dae1-4d7e-b0f8-9472706ef6ce', '54', 'e65334aa-5462-4cdd-acd4-48d8eafca867');
insert into SYS_GROUP_MENU (ID, MENUID, GROUPID)
values ('0fc171d9-77ad-434f-a946-91b6809b72b2', '55', 'e65334aa-5462-4cdd-acd4-48d8eafca867');
insert into SYS_GROUP_MENU (ID, MENUID, GROUPID)
values ('7a9e6f3b-72d1-4291-99a4-c91de7a0197e', '57', 'e65334aa-5462-4cdd-acd4-48d8eafca867');
insert into SYS_GROUP_MENU (ID, MENUID, GROUPID)
values ('00462284-7c17-4906-80ec-9a7da00f03d5', '4', 'e65334aa-5462-4cdd-acd4-48d8eafca867');
insert into SYS_GROUP_MENU (ID, MENUID, GROUPID)
values ('a8573937-b055-43f4-adee-6f154adc6c33', '41', 'e65334aa-5462-4cdd-acd4-48d8eafca867');
insert into SYS_GROUP_MENU (ID, MENUID, GROUPID)
values ('848f84bd-bc1a-446d-8a2d-db9180924373', '42', 'e65334aa-5462-4cdd-acd4-48d8eafca867');
insert into SYS_GROUP_MENU (ID, MENUID, GROUPID)
values ('539a2846-e48d-47e3-ba7f-1e8e6f1dfbd8', '46', 'e65334aa-5462-4cdd-acd4-48d8eafca867');
insert into SYS_GROUP_MENU (ID, MENUID, GROUPID)
values ('1cfb4add-ed68-4575-9257-33acdafb9352', '43', 'e65334aa-5462-4cdd-acd4-48d8eafca867');
insert into SYS_GROUP_MENU (ID, MENUID, GROUPID)
values ('4054d4bf-3614-41ac-958d-a38755b839d7', '44', 'e65334aa-5462-4cdd-acd4-48d8eafca867');
insert into SYS_GROUP_MENU (ID, MENUID, GROUPID)
values ('16754c47-5eba-4c59-8024-2b0c86b7d32d', '45', 'e65334aa-5462-4cdd-acd4-48d8eafca867');
insert into SYS_GROUP_MENU (ID, MENUID, GROUPID)
values ('ef8e05b7-c077-4994-9afa-8876059888d6', '47', 'e65334aa-5462-4cdd-acd4-48d8eafca867');
insert into SYS_GROUP_MENU (ID, MENUID, GROUPID)
values ('8ffdcc8a-1b55-43ea-9746-8618f544f5ab', '1', 'e65334aa-5462-4cdd-acd4-48d8eafca867');
insert into SYS_GROUP_MENU (ID, MENUID, GROUPID)
values ('ec6b5081-55b4-498f-849a-eaa6aaa7e59a', '11', 'e65334aa-5462-4cdd-acd4-48d8eafca867');
insert into SYS_GROUP_MENU (ID, MENUID, GROUPID)
values ('2f67e03e-ae8d-4284-a1a9-bd7ed151b641', '12', 'e65334aa-5462-4cdd-acd4-48d8eafca867');
insert into SYS_GROUP_MENU (ID, MENUID, GROUPID)
values ('8248cb66-7a3c-45e9-b99e-c823d06c951a', '2', 'e65334aa-5462-4cdd-acd4-48d8eafca867');
insert into SYS_GROUP_MENU (ID, MENUID, GROUPID)
values ('7aec9646-7efc-4ea6-bce1-821bc4c99ffe', '21', 'e65334aa-5462-4cdd-acd4-48d8eafca867');
insert into SYS_GROUP_MENU (ID, MENUID, GROUPID)
values ('31efc429-324b-4ccc-ac2c-a03cee58f173', '26', 'e65334aa-5462-4cdd-acd4-48d8eafca867');
insert into SYS_GROUP_MENU (ID, MENUID, GROUPID)
values ('b1f2abff-e3e1-44e7-ac89-b3cdb6f4b8c3', '23', 'e65334aa-5462-4cdd-acd4-48d8eafca867');
insert into SYS_GROUP_MENU (ID, MENUID, GROUPID)
values ('a2b7adea-5d90-4a35-9f32-617fc62fef15', '24', 'e65334aa-5462-4cdd-acd4-48d8eafca867');
insert into SYS_GROUP_MENU (ID, MENUID, GROUPID)
values ('e02419ea-3397-4a2c-8885-f36897131e18', '25', 'e65334aa-5462-4cdd-acd4-48d8eafca867');
insert into SYS_GROUP_MENU (ID, MENUID, GROUPID)
values ('d3e89271-a68f-4a59-88d3-7bb2d49fdf57', '3', 'e65334aa-5462-4cdd-acd4-48d8eafca867');
insert into SYS_GROUP_MENU (ID, MENUID, GROUPID)
values ('ce58a80d-aa33-425c-88bc-ba195a775f06', '34', 'e65334aa-5462-4cdd-acd4-48d8eafca867');
insert into SYS_GROUP_MENU (ID, MENUID, GROUPID)
values ('6468fe15-e05a-4755-bf6f-d49f3771e912', '33', 'e65334aa-5462-4cdd-acd4-48d8eafca867');
insert into SYS_GROUP_MENU (ID, MENUID, GROUPID)
values ('0e7091cd-3e0d-4114-8633-46de978b5f54', '32', 'e65334aa-5462-4cdd-acd4-48d8eafca867');
insert into SYS_GROUP_MENU (ID, MENUID, GROUPID)
values ('f16b6ff2-0794-4821-a9a3-bff29772c88c', '31', 'e65334aa-5462-4cdd-acd4-48d8eafca867');
insert into SYS_GROUP_MENU (ID, MENUID, GROUPID)
values ('12c3724f-7ca4-496e-ad6e-f29754ca714d', '35', 'e65334aa-5462-4cdd-acd4-48d8eafca867');
insert into SYS_GROUP_MENU (ID, MENUID, GROUPID)
values ('fbb73061-3332-4649-a200-6c74d3b6928d', '4', 'cabd2d12-706f-4ef2-82bf-554222a7875a');
insert into SYS_GROUP_MENU (ID, MENUID, GROUPID)
values ('89a50208-65c6-4840-9afb-6cc6c2f1d521', '41', 'cabd2d12-706f-4ef2-82bf-554222a7875a');
insert into SYS_GROUP_MENU (ID, MENUID, GROUPID)
values ('ba4c113d-c2a3-4720-9c32-2dd6570264a6', '42', 'cabd2d12-706f-4ef2-82bf-554222a7875a');
insert into SYS_GROUP_MENU (ID, MENUID, GROUPID)
values ('76bc18fe-2a57-4eba-91b9-c18727c44a91', '46', 'cabd2d12-706f-4ef2-82bf-554222a7875a');
insert into SYS_GROUP_MENU (ID, MENUID, GROUPID)
values ('ca9a15dc-9129-498b-b82b-98987518945d', '43', 'cabd2d12-706f-4ef2-82bf-554222a7875a');
insert into SYS_GROUP_MENU (ID, MENUID, GROUPID)
values ('388aae6b-1e5d-4e93-a5ac-814d3bc616d1', '44', 'cabd2d12-706f-4ef2-82bf-554222a7875a');
insert into SYS_GROUP_MENU (ID, MENUID, GROUPID)
values ('43ccdf3a-8325-41bf-b485-1bd25ace66cb', '45', 'cabd2d12-706f-4ef2-82bf-554222a7875a');
insert into SYS_GROUP_MENU (ID, MENUID, GROUPID)
values ('cbb4ef29-ade5-44d7-b60b-2b0f9011e149', '47', 'cabd2d12-706f-4ef2-82bf-554222a7875a');
insert into SYS_GROUP_MENU (ID, MENUID, GROUPID)
values ('4fa39d78-a996-48ba-9068-f565379568c0', '1', 'cabd2d12-706f-4ef2-82bf-554222a7875a');
insert into SYS_GROUP_MENU (ID, MENUID, GROUPID)
values ('1d66b5fd-d54d-492a-b15b-8f6231f97c3e', '11', 'cabd2d12-706f-4ef2-82bf-554222a7875a');
insert into SYS_GROUP_MENU (ID, MENUID, GROUPID)
values ('229963de-293c-43b9-90c6-9f9e727ad181', '4', '6a23fe81-42ef-41a0-a72a-7463bb669d5f');
insert into SYS_GROUP_MENU (ID, MENUID, GROUPID)
values ('14df5009-a75a-4925-bb5f-1d7f9b88e64d', '41', '6a23fe81-42ef-41a0-a72a-7463bb669d5f');
insert into SYS_GROUP_MENU (ID, MENUID, GROUPID)
values ('19679c6d-7137-4d3a-a612-a776722eeabc', '42', '6a23fe81-42ef-41a0-a72a-7463bb669d5f');
insert into SYS_GROUP_MENU (ID, MENUID, GROUPID)
values ('0afc48ea-817e-4682-ac60-64fc0d364724', '43', '6a23fe81-42ef-41a0-a72a-7463bb669d5f');
insert into SYS_GROUP_MENU (ID, MENUID, GROUPID)
values ('b04d6201-83de-4baf-bc12-158bd8664ce9', '46', '6a23fe81-42ef-41a0-a72a-7463bb669d5f');
insert into SYS_GROUP_MENU (ID, MENUID, GROUPID)
values ('4d06f585-1794-4b13-8ecd-0c6279f21c16', '44', '6a23fe81-42ef-41a0-a72a-7463bb669d5f');
insert into SYS_GROUP_MENU (ID, MENUID, GROUPID)
values ('6ba1bf02-0353-49c4-afbc-25b9eedfb32f', '45', '6a23fe81-42ef-41a0-a72a-7463bb669d5f');
insert into SYS_GROUP_MENU (ID, MENUID, GROUPID)
values ('5dc7c30c-796e-450a-bb50-31e9d96d9a99', '1', '6a23fe81-42ef-41a0-a72a-7463bb669d5f');
insert into SYS_GROUP_MENU (ID, MENUID, GROUPID)
values ('248abbf7-5b7d-4a99-9ba0-15e7849252fa', '11', '6a23fe81-42ef-41a0-a72a-7463bb669d5f');
insert into SYS_GROUP_MENU (ID, MENUID, GROUPID)
values ('6dd5cc53-e1fa-4977-9f00-8880ee7d633b', '3', '6a23fe81-42ef-41a0-a72a-7463bb669d5f');
insert into SYS_GROUP_MENU (ID, MENUID, GROUPID)
values ('fbe91844-3d97-4b8d-a709-a30cc639f5f7', '35', '6a23fe81-42ef-41a0-a72a-7463bb669d5f');
commit;
prompt 71 records loaded
prompt Loading SYS_GROUP_USER...
prompt Table is empty
prompt Loading SYS_MENU...
insert into SYS_MENU (ID, NAME, PID, URL, TYPE, STATUS, SEQ, MLEVEL, ICON, CSS, REMARK, CREATE_TIME, CREATE_BY)
values ('32', '菜单权限', '3', '/jsp/sys/menu/index.jsp', '1', '1', 3, 3, null, null, null, null, null);
insert into SYS_MENU (ID, NAME, PID, URL, TYPE, STATUS, SEQ, MLEVEL, ICON, CSS, REMARK, CREATE_TIME, CREATE_BY)
values ('33', '角色管理', '3', '/jsp/sys/group/list.jsp', '1', '1', 2, 3, null, null, null, null, null);
insert into SYS_MENU (ID, NAME, PID, URL, TYPE, STATUS, SEQ, MLEVEL, ICON, CSS, REMARK, CREATE_TIME, CREATE_BY)
values ('34', '用户管理', '3', '/jsp/sys/user/list.jsp', '1', '1', 1, 3, null, null, null, null, null);
insert into SYS_MENU (ID, NAME, PID, URL, TYPE, STATUS, SEQ, MLEVEL, ICON, CSS, REMARK, CREATE_TIME, CREATE_BY)
values ('3', '系统管理', '-1', null, '1', '1', 99, 2, null, null, null, null, null);
insert into SYS_MENU (ID, NAME, PID, URL, TYPE, STATUS, SEQ, MLEVEL, ICON, CSS, REMARK, CREATE_TIME, CREATE_BY)
values ('31', '操作日志', '3', '/jsp/sys/syslog/log_list.jsp', '1', '1', 4, 3, null, null, null, null, null);
insert into SYS_MENU (ID, NAME, PID, URL, TYPE, STATUS, SEQ, MLEVEL, ICON, CSS, REMARK, CREATE_TIME, CREATE_BY)
values ('35', '修改密码', '3', '/jsp/sys/user/updateNewPW.jsp', '1', '1', 5, 3, null, null, null, null, null);
commit;
prompt 6 records loaded
prompt Loading SYS_USER...
prompt Table is empty
prompt Enabling triggers for SYS_GROUP...
alter table SYS_GROUP enable all triggers;
prompt Enabling triggers for SYS_GROUP_MENU...
alter table SYS_GROUP_MENU enable all triggers;
prompt Enabling triggers for SYS_GROUP_USER...
alter table SYS_GROUP_USER enable all triggers;
prompt Enabling triggers for SYS_MENU...
alter table SYS_MENU enable all triggers;
prompt Enabling triggers for SYS_USER...
alter table SYS_USER enable all triggers;
set feedback on
set define on
prompt Done.
