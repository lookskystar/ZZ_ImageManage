-- Create table
create table DICT_USERS
(
  USER_ID  NUMBER not null,
  USERNAME VARCHAR2(20),
  PASSWORD VARCHAR2(20),
  ROLE_ID  NUMBER,
  AREA_ID  NUMBER,
  NAME     VARCHAR2(20),
  TEAM_ID  NUMBER,
  IDNUM    VARCHAR2(20),
  ISUSE    NUMBER default 1
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64
    next 1
    minextents 1
    maxextents unlimited
  );
-- Add comments to the columns 
comment on column DICT_USERS.ISUSE
  is '0禁用   1启用';
-- Create/Recreate primary, unique and foreign key constraints 
alter table DICT_USERS
  add constraint DICT_USERS_PK primary key (USER_ID)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
