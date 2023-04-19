-- begin PROJECT
create table PROJECT (
    ID varchar(36) not null,
    --
    NAME varchar(100) not null,
    --
    primary key (ID)
)^
-- end PROJECT
-- begin EMPLOYEE
create table EMPLOYEE (
    ID varchar(36) not null,
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
    --
    EMPLOYEE_ID varchar(36) not null,
    PROJECT_ID varchar(36) not null,
    --
    primary key (ID)
)^
-- end PROJECT_EMPLOYEE
