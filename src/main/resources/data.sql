SELECT * FROM employee;
--Dummy data for 'employee' table
/*
INSERT INTO employee (employee_id, manager_id, first_name, last_name, email, work_role, auth_role) VALUES (1, 1, 'Alice', 'Johnson', 'alice.johnson@email.com', 'Senior Manager', 'MANAGER');
INSERT INTO employee (employee_id, manager_id, first_name, last_name, email, work_role, auth_role) VALUES (2, 1, 'Bob', 'Smith', 'bob.smith@email.com', 'Junior Developer', 'EMPLOYEE');
INSERT INTO employee (employee_id, manager_id, first_name, last_name, email, work_role, auth_role) VALUES (3, 1, 'Charlie', 'Brown', 'charlie.brown@email.com', 'Mid-level Developer', 'EMPLOYEE');
INSERT INTO employee (employee_id, manager_id, first_name, last_name, email, work_role, auth_role) VALUES (4, 1, 'David', 'Williams', 'david.williams@email.com', 'Mid-level Manager', 'MANAGER');
INSERT INTO employee (employee_id, manager_id, first_name, last_name, email, work_role, auth_role) VALUES (5, 1, 'Emily', 'Davis', 'emily.davis@email.com', 'Junior Developer', 'EMPLOYEE');
INSERT INTO employee (employee_id, manager_id, first_name, last_name, email, work_role, auth_role) VALUES (6, 1, 'Fiona', 'Clark', 'fiona.clark@email.com', 'Junior Developer', 'EMPLOYEE');
INSERT INTO employee (employee_id, manager_id, first_name, last_name, email, work_role, auth_role) VALUES (7, 1, 'George', 'Garcia', 'george.garcia@email.com', 'Mid-level Developer', 'EMPLOYEE');
INSERT INTO employee (employee_id, manager_id, first_name, last_name, email, work_role, auth_role) VALUES (8, 1, 'Helen', 'Baker', 'helen.baker@email.com', 'Senior Developer', 'EMPLOYEE');

--Dummy data for 'blocked_period' table
INSERT INTO blocked_period (start_date, end_date, employee_id) VALUES ('2023-09-27', '2023-09-28', 1);

--Dummy data for 'vacation_request' table
INSERT INTO vacation_request (start_date, end_date, employee_id, vacation_type, notes) VALUES ('2023-07-09', '2023-07-15', 5, 'PARENTAL_LEAVE', 'Parental leave for vacation.');
INSERT INTO vacation_request (start_date, end_date, employee_id, vacation_type, notes) VALUES ('2023-08-01', '2023-08-07', 4, 'VACATION_LEAVE', 'Annual leave for vacation.');
INSERT INTO vacation_request (start_date, end_date, employee_id, vacation_type, notes) VALUES ('2023-09-01', '2023-09-07', 6, 'SICK_LEAVE', 'Recovering from illness.');
INSERT INTO vacation_request (start_date, end_date, employee_id, vacation_type, notes) VALUES ('2023-10-01', '2023-10-07', 7, 'PUBLIC_HOLIDAY', 'Extended public holiday.');
INSERT INTO vacation_request (start_date, end_date, employee_id, vacation_type, notes) VALUES ('2023-09-22', '2023-09-22', 5, 'SICK_LEAVE', 'Doctors appointment');


-- Dummy data for 'vacation_response' table
INSERT INTO vacation_response (vacation_response_id, vacation_request_id, vacation_response, response_comment) VALUES (1, 1, 'APPROVED', 'Enjoy your vacation!');
INSERT INTO vacation_response (vacation_response_id, vacation_request_id, vacation_response, response_comment) VALUES (2, 2, 'DENIED', 'Sorry, we need you during that period.');
INSERT INTO vacation_response (vacation_response_id, vacation_request_id, vacation_response, response_comment) VALUES (3, 4, 'APPROVED', 'Your public holiday extension is approved.');



INSERT INTO vacation_request (start_date, end_date, employee_id, vacation_type, notes) VALUES ('2023-10-10', '2023-10-12', 5, 'PARENTAL_LEAVE', 'Need some time of to help my son with homework');
INSERT INTO vacation_response (vacation_response_id, vacation_request_id, vacation_response, response_comment) VALUES (4, 6, 'DENIED', 'This is not a valid reason for parental leave...');


 */