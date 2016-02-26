-- Create table
create table DICT_AREAS
(
  AREA_ID      NUMBER not null,
  AREA_NAME    VARCHAR2(20),
  FTP_IP       VARCHAR2(20),
  FTP_PORT     NUMBER,
  FTP_USERNAME VARCHAR2(20),
  FTP_PASSWORD VARCHAR2(20),
  AREA_LEVEL   NUMBER,
  PARENT_ID    NUMBER,
  AREA_NOTE    VARCHAR2(20),
  VIDEO_PATH   VARCHAR2(100)
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
comment on column DICT_AREAS.AREA_LEVEL
  is '1:动车段 2:动车所';
