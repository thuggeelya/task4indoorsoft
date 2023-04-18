alter table PROJECT_EMPLOYEE add constraint FK_PROJECT_EMPLOYEE_ON_EMPLOYEE foreign key (EMPLOYEE_ID) references EMPLOYEE(ID);
alter table PROJECT_EMPLOYEE add constraint FK_PROJECT_EMPLOYEE_ON_PROJECT foreign key (PROJECT_ID) references PROJECT(ID);
create index IDX_PROJECT_EMPLOYEE_ON_EMPLOYEE on PROJECT_EMPLOYEE (EMPLOYEE_ID);
create index IDX_PROJECT_EMPLOYEE_ON_PROJECT on PROJECT_EMPLOYEE (PROJECT_ID);