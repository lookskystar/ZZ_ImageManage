-- Create table
create table DEVICES_INFO
(
  DEVICES_ID    NUMBER not null,
  DEVICES_TYPE  VARCHAR2(20),
  DEVICE_CODE   VARCHAR2(20),
  DEVICE_STATUS NUMBER,
  RECEIVER_ID   NUMBER,
  RECEIVER_NAME VARCHAR2(20),
  RECEIVER_TIME VARCHAR2(20),
  AREA_ID       NUMBER,
  IS_DELETE     NUMBER default 0,
  DEVICES_NOTE  VARCHAR2(256),
  RETURN_ID     NUMBER,
  RETURN_NAME   VARCHAR2(20),
  RETURN_TIME   VARCHAR2(20),
  TEAM_ID       NUMBER,
  TEAM_NAME     VARCHAR2(20)
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
comment on column DEVICES_INFO.DEVICE_STATUS
  is '状态0:库存 1：使用 2：维修 3:报废';
comment on column DEVICES_INFO.IS_DELETE
  is '删除标识 0:正常 1:已删除';
-- Create/Recreate primary, unique and foreign key constraints 
alter table DEVICES_INFO
  add constraint DEVICES_INFO_PK primary key (DEVICES_ID)
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
