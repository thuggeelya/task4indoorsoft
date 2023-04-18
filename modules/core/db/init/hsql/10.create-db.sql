-- begin PROJECT
create table PROJECT (
    ID varchar(36) not null,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    NAME varchar(100) not null,
    --
    primary key (ID)
)^
-- end PROJECT
-- begin EMPLOYEE
create table EMPLOYEE (
    ID varchar(36) not null,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    LAST_NAME varchar(100) not null,
    NAME varchar(50) not null,
    PATRONYMIC varchar(255),
    --
    primary key (ID)
)^
-- end EMPLOYEE
-- begin PROJECT_EMPLOYEE
create table PROJECT_EMPLOYEE (
    ID varchar(36) not null,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    EMPLOYEE_ID varchar(36) not null,
    PROJECT_ID varchar(36) not null,
    --
    primary key (EMPLOYEE_ID, PROJECT_ID)
)^
-- end PROJECT_EMPLOYEE
