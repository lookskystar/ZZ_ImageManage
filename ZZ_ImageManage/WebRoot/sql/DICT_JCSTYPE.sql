-- Create table
create table DICT_JCSTYPE
(
  ID         NUMBER not null,
  JCNUM      VARCHAR2(200),
  JCSTYPE    VARCHAR2(200),
  JCIDENTIFY VARCHAR2(200)
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
