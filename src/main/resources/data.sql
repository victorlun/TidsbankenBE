
SELECT * FROM employee;
/*
 --Dummy data for 'employee' table
INSERT INTO employee (employee_id, first_name, last_name, email, work_role, auth_role) VALUES (1, 'Alice', 'Johnson', 'alice.johnson@email.com', 'Senior Manager', 'MANAGER');
INSERT INTO employee (employee_id, manager_id, first_name, last_name, email, work_role, auth_role) VALUES (2, 1, 'Bob', 'Smith', 'bob.smith@email.com', 'Junior Developer', 'EMPLOYEE');
INSERT INTO employee (employee_id, manager_id, first_name, last_name, email, work_role, auth_role) VALUES (3, 1, 'Charlie', 'Brown', 'charlie.brown@email.com', 'Mid-level Developer', 'EMPLOYEE');
INSERT INTO employee (employee_id, manager_id, first_name, last_name, email, work_role, auth_role) VALUES (4, 1, 'David', 'Williams', 'david.williams@email.com', 'Mid-level Manager', 'MANAGER');
INSERT INTO employee (employee_id, manager_id, first_name, last_name, email, work_role, auth_role) VALUES (5, 4, 'Emily', 'Davis', 'emily.davis@email.com', 'Junior Developer', 'EMPLOYEE');
INSERT INTO employee (employee_id, manager_id, first_name, last_name, email, work_role, auth_role) VALUES (6, 4, 'Fiona', 'Clark', 'fiona.clark@email.com', 'Junior Developer', 'EMPLOYEE');
INSERT INTO employee (employee_id, manager_id, first_name, last_name, email, work_role, auth_role) VALUES (7, 4, 'George', 'Garcia', 'george.garcia@email.com', 'Mid-level Developer', 'EMPLOYEE');
INSERT INTO employee (employee_id, manager_id, first_name, last_name, email, work_role, auth_role) VALUES (8, 4, 'Helen', 'Baker', 'helen.baker@email.com', 'Senior Developer', 'EMPLOYEE');

--Dummy data for 'blocked_period' table
INSERT INTO blocked_period (startDate, endDate, employee_id) VALUES ('2023-06-01', '2023-06-07', 1);
INSERT INTO blocked_period (startDate, endDate, employee_id) VALUES ('2023-07-01', '2023-07-07', 1);
INSERT INTO blocked_period (startDate, endDate, employee_id) VALUES ('2023-08-01', '2023-08-07', 4);
INSERT INTO blocked_period (startDate, endDate, employee_id) VALUES ('2023-09-01', '2023-09-07', 4);

--Dummy data for 'vacation_request' table
INSERT INTO vacation_request (startDate, endDate, employee_id, status, vacation_type, notes) VALUES ('2023-07-09', '2023-07-1', 5, 'HANDLED', 'PARENTAL_LEAVE', 'Parental leave for vacation.');
INSERT INTO vacation_request (startDate, endDate, employee_id, status, vacation_type, notes) VALUES ('2023-08-01', '2023-08-07', 4, 'HANDLED', 'VACATION_LEAVE', 'Annual leave for vacation.');
INSERT INTO vacation_request (startDate, endDate, employee_id, status, vacation_type, notes) VALUES ('2023-09-01', '2023-09-07', 6, 'PENDING', 'SICK_LEAVE', 'Recovering from illness.');
INSERT INTO vacation_request (startDate, endDate, employee_id, status, vacation_type, notes) VALUES ('2023-10-01', '2023-10-07', 7, 'HANDLED', 'PUBLIC_HOLIDAY', 'Extended public holiday.');

-- Dummy data for 'vacation_response' table
INSERT INTO vacation_response (vacation_response_id, vacation_request_id, vacation_response, response_comment) VALUES (1, 1, 'APPROVED', 'Enjoy your vacation!');
INSERT INTO vacation_response (vacation_response_id, vacation_request_id, vacation_response, response_comment) VALUES (2, 2, 'DENIED', 'Sorry, we need you during that period.');
INSERT INTO vacation_response (vacation_response_id, vacation_request_id, vacation_response, response_comment) VALUES (4, 4, 'APPROVED', 'Your public holiday extension is approved.');
*/