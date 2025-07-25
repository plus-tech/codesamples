--
-- DDL 

CREATE TABLE Departments (
   Department_Id NUMBER(4)
 , Department_Name VARCHAR2(20)
 , Manager_Id NUMBER(6)
)
 NOLOGGING
 PARALLEL
;

CREATE TABLE Employees (
   Employee_Id NUMBER(6)
 , First_Name VARCHAR2(20)
 , Last_Name VARCHAR2(20)
 , Salary NUMBER(8)
 , Hire_Date CHAR(10)
 , Manager_Id NUMBER(6)
 , Department_Id NUMBER(4)
)
 NOLOGGING
 PARALLEL
;


--
-- DML

INSERT INTO Departments
WITH tabrows AS (
 SELECT 10, 'Administration', 100 FROM DUAL UNION ALL
 SELECT 20, 'Marketing', 200 FROM DUAL UNION ALL
 SELECT 99, 'Dummy', null FROM DUAL
)
SELECT * FROM tabrows
;

INSERT ALL
 INTO Employees VALUES(100, 'David', 'OConnell', 4800, '2010/01/01', null, 10)
 INTO Employees VALUES(101, 'Susan', 'Grant', 6500, '2010/12/31', 100, 10)
 INTO Employees VALUES(200, 'Jennifer', 'Whalen', 4400, '2010/01/01', null, 20)
 INTO Employees VALUES(201, 'Bruce', 'Hartstein', 6000, '2010/12/31', 200, 20)
 INTO Employees VALUES(202, 'Pat', 'Fay', 6000, '2010/12/31', 200, 20)
SELECT * FROM DUAL
;
