--
-- DDL 

CREATE TABLE Departments (
   Department_Id NUMBER(4)
 , Department_Name VARCHAR2(20)
 , Manager_Id NUMBER(6)
)
 NOLOGGING
 PARALLEL;

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
 PARALLEL;

CREATE TABLE App_Parameters (
   AppName VARCHAR2(30) NOT NULL
  ,Parameter VARCHAR2(30) NOT NULL
  ,Value VARCHAR2(30)
  ,CONSTRAINT app_parameters_pk PRIMARY KEY (App_Name, Parameter_Name)
);

CREATE TABLE App_ApiKey (
   Key VARCHAR2(64) UNIQUE
  ,Username VARCHAR2(30) NOT NULL
  ,Description VARCHAR2(128)
)
 NOLOGGING;

--
-- DML

INSERT INTO Departments
WITH tabrows AS (
 SELECT 10, 'Administration', 100 FROM DUAL UNION ALL
 SELECT 20, 'Marketing', 200 FROM DUAL UNION ALL
 SELECT 30, 'Compliance', 300 FROM DUAL UNION ALL
 SELECT 40, 'Channel', 400 FROM DUAL UNION ALL
 SELECT 99, 'Dummy', null FROM DUAL
)
SELECT * FROM tabrows;

INSERT ALL
 INTO Employees VALUES(100, 'David', 'OConnell', 4800, '2010/01/01', null, 10)
 INTO Employees VALUES(101, 'Susan', 'Grant', 6500, '2010/12/31', 100, 10)
 INTO Employees VALUES(200, 'Jennifer', 'Whalen', 4400, '2010/01/01', null, 20)
 INTO Employees VALUES(201, 'Bruce', 'Hartstein', 6000, '2010/12/31', 200, 20)
 INTO Employees VALUES(202, 'Pat', 'Fay', 6000, '2010/12/31', 200, 20)
SELECT * FROM DUAL;

INSERT ALL 
 INTO App_Parameters VALUES('SpringMVC', 'dptDbAccess', 'DAO')
 INTO App_Parameters VALUES('SpringMVC', 'empDbAccess', 'Mybatis')
 INTO App_Parameters VALUES('SpringMVC', 'dptCurrentDb', 'Primary')
 INTO App_Parameters VALUES('SpringMVC', 'empCurrentDb', 'Secondary')
 INTO App_Parameters VALUES('SpringREST', 'dptDbAccess', 'DAO')
 INTO App_Parameters VALUES('SpringREST', 'empDbAccess', 'Mybatis')
 INTO App_Parameters VALUES('SpringREST', 'dptCurrentDb', 'Primary')
 INTO App_Parameters VALUES('SpringREST', 'empCurrentDb', 'Secondary')
SELECT * FROM DUAL;

SELECT * from (
	SELECT appname, parameter, value 
	FROM app_parameters
	WHERE appname = #{appName}
) PIVOT (
	LISTAGG(value)
	FOR PARAMETER IN (
		'dptDbAccess' AS dptDbAccess,
		'empDbAccess' AS empDbAccess,
		'dptCurrentDb' AS dptCurrentDb,
		'empCurrentDb' AS empCurrentDb
	)
);

INSERT ALL
 INTO App_ApiKey 
  VALUES('XvcItqrOmhPb2gxalIiGWhe7kOQ5cT6E5ZpUpYLM8RU=', 'DEMO', 'Key for user DEMO')
 INTO App_ApiKey 
  VALUES('nXW29eDKVKZnwT2Hu5LXVHp+m0Xd32py9OdRp0qSZIw=', 'TEST', 'Key for user TEST')
SELECT * FROM DUAL;
 