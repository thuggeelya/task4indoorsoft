alter table PROJECT add column DELETED_BY varchar(50) ;
alter table PROJECT add column UPDATE_TS timestamp ;
alter table PROJECT add column DELETE_TS timestamp ;
alter table PROJECT add column UPDATED_BY varchar(50) ;
alter table PROJECT add column CREATED_BY varchar(50) ;
alter table PROJECT add column CREATE_TS timestamp ;
alter table PROJECT add column VERSION integer ^
update PROJECT set VERSION = 0 where VERSION is null ;
alter table PROJECT alter column VERSION set not null ;
