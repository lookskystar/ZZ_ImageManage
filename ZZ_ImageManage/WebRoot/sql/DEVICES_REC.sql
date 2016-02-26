-- Create table
create table DEVICES_REC
(
  DEVICES_RECID NUMBER not null,
  DEVICES_ID    NUMBER,
  USER_ID       NUMBER,
  USER_NAME     VARCHAR2(20),
  USE_TIME      VARCHAR2(20),
  USE_TYPE      NUMBER,
  REC_NOTE      VARCHAR2(20)
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
comment on column DEVICES_REC.USE_TYPE
  is '类型 0:领取 1：归还 2：送修 3：返修回来  4：报废';
-- Create/Recreate primary, unique and foreign key constraints 
alter table DEVICES_REC
  add constraint DEVICES_REC_PK primary key (DEVICES_RECID)
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
