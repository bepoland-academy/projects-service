insert into PROJECTS_DB.client (client_id, client_guid, client_name)
values (1, '0c16fdba-3f83-4af3-a41e-2834ea5d0874', 'PZU');
insert into PROJECTS_DB.client (client_id, client_guid, client_name)
values (2, 'c0ed05e5-a6d1-488a-9f1c-d9a152ea4617', 'UNIQA');
insert into PROJECTS_DB.client (client_id, client_guid, client_name)
values (3, 'a394ee5c-f2bc-45d0-83d8-c92d8c5ba312', 'HISCOX');

insert into PROJECTS_DB.project (project_id, comments, project_guid, project_name, project_rate, department_guid,
                                 client_id, active)
values (1, 'first', '7067a443-d7a1-4dde-b38f-76ce5494978c', 'firstPro', 232, '14708cec-0315-415a-a0d3-17abe1d5df53', 1,
        true);
insert into PROJECTS_DB.project (project_id, comments, project_guid, project_name, project_rate, department_guid,
                                 client_id, active)
values (2, 'second', '8b03fb47-3467-43a1-a4d0-b029002b0de9', 'secondPro', 245, '77065bda-b226-4b39-9092-94f48aaaa042',
        2, false);

