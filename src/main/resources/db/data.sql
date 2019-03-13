insert into PROJECTS_DB.client (client_id, client_guid, client_name)
values (1, '0c16fdba-3f83-4af3-a41e-2834ea5d0874', 'PZU');
insert into PROJECTS_DB.client (client_id, client_guid, client_name)
values (2, 'c0ed05e5-a6d1-488a-9f1c-d9a152ea4617', 'UNIQA');
insert into PROJECTS_DB.client (client_id, client_guid, client_name)
values (3, 'a394ee5c-f2bc-45d0-83d8-c92d8c5ba312', 'HISCOX');

insert into PROJECTS_DB.project (project_id, comments, project_guid, project_name, project_rate, department_id)
values (1, 'first', '7067a443-d7a1-4dde-b38f-76ce5494978c', 'firstPro', 232, '324d');
insert into PROJECTS_DB.project (project_id, comments, project_guid, project_name, project_rate, department_id)
values (2, 'second', '8b03fb47-3467-43a1-a4d0-b029002b0de9', 'secondPro', 245, 'dgd4');

insert into projects_db.client_project (project_id, client_id)
values (1, 1);
insert into projects_db.client_project (project_id, client_id)
values (2, 2);
