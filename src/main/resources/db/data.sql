insert into PROJECTS_DB.project_role (project_role_id,project_role_guid, name) values
(1,'4ce62627-5d4f-47a7-89d6-4093a092d78b','Junior Java Developer'),
(2,'e708be40-4f2b-4f6f-b848-6dd2fa09bb20', 'Junior Frontend Developer'),
(3,'47520d35-98e8-4953-b09f-ab70f5935764', 'Solution Architect'),
(4,'ca8cb011-c54d-4f90-9d2f-f063c6028aaf', 'Scrum Master'),
(5,'f1194145-8aff-47a4-9d64-9b1ecf64ce70', 'Senior Java Developer'),
(6,'13437d8c-a7ba-4b9f-9864-b84cf3738256', 'Senior Frontend Developer');

insert into PROJECTS_DB.client (client_id, client_guid, client_name)
values (1, '0c16fdba-3f83-4af3-a41e-2834ea5d0874', 'PZU');
insert into PROJECTS_DB.client (client_id, client_guid, client_name)
values (2, 'c0ed05e5-a6d1-488a-9f1c-d9a152ea4617', 'UNIQA');
insert into PROJECTS_DB.client (client_id, client_guid, client_name)
values (3, 'a394ee5c-f2bc-45d0-83d8-c92d8c5ba312', 'HISCOX');


insert into PROJECTS_DB.project (project_id, comments, project_guid, project_name, department_guid, client_id, active, off_site_only) values
(1,'Curabitur sit amet scelerisque risus, sollicitudin egestas urna. Curabitur nec sem non metus hendrerit tincidunt. Nam non diam sed est consequat blandit. Etiam nisl eros, porta sit amet pharetra nec, placerat id mi. Fusce pulvinar sit amet justo eget sodales. Praesent felis sem, pulvinar vel est vel, ornare vulputate nulla.','88a9a983-8337-4462-9383-faf747c24b46','Refactoring Financial Department Remote System.','14708cec-0315-415a-a0d3-17abe1d5df53',1, true,1),
(2,'Vestibulum pulvinar, quam in molestie sodales, velit erat faucibus odio, sit amet porta elit lacus in massa. Pellentesque vitae elit.','954928d4-b2d5-4e30-ae39-d2516c57ce2e','Build New Policy Checker.','77065bda-b226-4b39-9092-94f48aaaa042',1,true,0),
(3,'Proin facilisis dolor.','585c5ddf-63f4-480e-8c6c-1a3eb91f9e66','CRUD For New Customers.','d31182ae-6dca-4153-83b1-fe949e5aade1',1,true,1),
(4,'Praesent imperdiet ipsum a ultrices vestibulum. Praesent efficitur ullamcorper dignissim. Cras turpis ligula, ultrices ut posuere commodo, aliquet eget justo. Donec ligula odio.','7f7f01aa-f49b-465a-80d4-05890de1a74c','Financial Analysis Of Company Credits.','14708cec-0315-415a-a0d3-17abe1d5df53',2,true,1),
(5,'Uisque ut vestibulum quam. Quisque elementum lorem in dui venenatis, sit amet imperdiet odio congue. Quisque interdum at diam sodales tempor. Phasellus pellentesque.','f69b330a-f184-44ea-8849-8006af8f1089','Implementation of Backbase Forms System.','77065bda-b226-4b39-9092-94f48aaaa042',2,true,0),
(6,'','5d13910d-c6c7-40b3-9e15-507328206939','Loans Calculator Implementation.','d31182ae-6dca-4153-83b1-fe949e5aade1',2,true,1),
(7,'Pellentesque euismod interdum massa, at lacinia dui cursus rutrum. In nisl tellus, bibendum eget massa a, ultricies lacinia ligula. Mauris a ligula dui. Pellentesque egestas justo eu massa fringilla molestie. Proin congue iaculis aliquet. Fusce congue orci orci. Curabitur turpis.','a3049ab3-7c87-40d8-9624-6e998684f773','New Insurances Analysis.','d31182ae-6dca-4153-83b1-fe949e5aade1',3,true,0),
(8,'Vivamus in mauris lacinia, imperdiet orci id, gravida erat. Aenean bibendum, urna in sagittis commodo, nunc orci porttitor orci, ac iaculis justo purus.','a4a0aad6-4f79-4543-903e-347c9edd7b96','Refactoring CAR-AND-HOME Policy Manager.','77065bda-b226-4b39-9092-94f48aaaa042',3,true,1),
(9,'','666536ba-906a-4fc3-85bf-e68e3e88c28c','SALESFORCE Courses for junior developers.','d31182ae-6dca-4153-83b1-fe949e5aade1',3,true,1);


insert into PROJECTS_DB.project_rate (project_rate_id, project_id, project_role_id, rates, on_site_RATE) values
(1,2,3,500.0,21),
(2,2,2,350.0,32),
(3,1,4,200.0,43),
(4,1,1,250.0,54),
(5,5,5,400.0,65),
(6,5,5,380.0,76),
(7,7,2,250.0,87),
(8,8,2,300.0,98),
(9,9,2,240.0,89),
(10,5,5,560.0,78);

insert into PROJECTS_DB.project_assignment (project_assignment_id,project_rate_id,user_guid) values
(1,1,'1e3185a2-ba44-4709-b8de-a1b0a7ad15f5'),
(2,6,'64616e59-86cd-432e-a534-ad2ec72a006a'),
(3,7,'a724077c-3526-4948-8a4f-66826bcfa968'),
(4,8,'af197078-ef3e-46e6-893f-e016e05c895f'),
(5,4,'1e3185a2-ba44-4709-b8de-a1b0a7ad15f5'),
(6,2,'64616e59-86cd-432e-a534-ad2ec72a006a'),
(7,1,'a724077c-3526-4948-8a4f-66826bcfa968'),
(8,10,'af197078-ef3e-46e6-893f-e016e05c895f');