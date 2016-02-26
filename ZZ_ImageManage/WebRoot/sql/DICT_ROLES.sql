-- Create table
create table DICT_ROLES
(
  ROLE_ID    NUMBER not null,
  ROLE_NAME  VARCHAR2(20),
  ROLE_PY    VARCHAR2(20),
  ROLE_LEVEL NUMBER,
  ROLE_NOTE  VARCHAR2(20)
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
comment on column DICT_ROLES.ROLE_LEVEL
  is '角色级别 0:车间级 1:段级';
-- Create/Recreate primary, unique and foreign key constraints 
alter table DICT_ROLES
  add constraint DICT_ROLES_PK primary key (ROLE_ID)
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
