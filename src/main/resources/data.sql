
SELECT * FROM period;

--Dummy data for 'period' table
INSERT INTO period (start_date, end_date) VALUES ('2023-05-01', '2023-05-07');
INSERT INTO period (start_date, end_date) VALUES ('2023-06-01', '2023-06-07');
INSERT INTO period (start_date, end_date) VALUES ('2023-07-01', '2023-07-07');
INSERT INTO period (start_date, end_date) VALUES ('2023-08-01', '2023-08-07');
INSERT INTO period (start_date, end_date) VALUES ('2023-09-01', '2023-09-07');

-- Dummy data for 'employee' table
INSERT INTO employee (employee_id, first_name, last_name, email, work_role, auth_role) VALUES (12, 'Alice', 'Manager', 'alice.manager@email.com', 'Senior Manager', 'MANAGER');
INSERT INTO employee (employee_id, manager_id, first_name, last_name, email, work_role, auth_role) VALUES (2, 1, 'Bob', 'Developer', 'bob.developer@email.com', 'Junior Developer', 'EMPLOYEE');
INSERT INTO employee (employee_id, manager_id, first_name, last_name, email, work_role, auth_role) VALUES (3, 1, 'Charlie', 'Developer', 'charlie.developer@email.com', 'Mid-level Developer', 'EMPLOYEE');
INSERT INTO employee (employee_id, manager_id, first_name, last_name, email, work_role, auth_role) VALUES (4, 1, 'David', 'Manager', 'david.manager@email.com', 'Mid-level Manager', 'MANAGER');
INSERT INTO employee (employee_id, manager_id, first_name, last_name, email, work_role, auth_role) VALUES (5, 4, 'Emily', 'Developer', 'emily.developer@email.com', 'Junior Developer', 'EMPLOYEE');
INSERT INTO employee (employee_id, manager_id, first_name, last_name, email, work_role, auth_role) VALUES (6, 4, 'Fiona', 'Developer', 'fiona.developer@email.com', 'Junior Developer', 'EMPLOYEE');

--Dummy data for 'blocked_period' table
INSERT INTO blocked_period (period_id, employee_id) VALUES (1, 1);
INSERT INTO blocked_period (period_id, employee_id) VALUES (2, 4);

--Dummy data for 'vacation_request' table
INSERT INTO vacation_request (period_id, employee_id, status, vacation_type, notes) VALUES (3, 1, 'PENDING', 'PARENTAL_LEAVE', 'Parental leave for vacation.');
INSERT INTO vacation_request (period_id, employee_id, status, vacation_type, notes) VALUES (4, 1, 'HANDLED', 'VACATION_LEAVE', 'Annual leave for vacation.');

-- Dummy data for 'vacation_response' table
INSERT INTO vacation_response (vacation_response_id, vacation_request_id, vacation_response, response_comment) VALUES (2, 2, 'DENIED', 'Sorry, we need you during that period.');
