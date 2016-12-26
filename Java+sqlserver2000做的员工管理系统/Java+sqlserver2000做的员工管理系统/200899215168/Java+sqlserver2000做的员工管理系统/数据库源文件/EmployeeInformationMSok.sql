CREATE DATABASE EmployeeInformationMS
--DROP DATABASE EmployeeInformationMS
go
USE EmployeeInformationMS


go
/*用户信息表*/
CREATE TABLE UserInformation
(
   User_ID INT IDENTITY(1,1),   
   User_Name VARCHAR(20) NOT NULL,
   Password VARCHAR(20) NOT NULL,
   Popedom VARCHAR(20) DEFAULT 'B', --权限
)   
go
INSERT UserInformation VALUES('管理员','123','A')
INSERT UserInformation (User_Name,Password) VALUES('admin','123456')


--TRUNCATE TABLE UserInformation
--SELECT * FROM UserInformation


go
/*部门信息表*/
CREATE TABLE DepartmentInformation
(
   D_Number INT IDENTITY(1,1),
   D_Name VARCHAR(20) NOT NULL,
   D_Count VARCHAR(20) NOT NULL,
)
go
INSERT DepartmentInformation VALUES('市场部','50')
INSERT DepartmentInformation VALUES('教学部','65')
INSERT DepartmentInformation VALUES('财会部','26')
INSERT DepartmentInformation VALUES('培训部','52')
INSERT DepartmentInformation VALUES('后勤部','69')
INSERT DepartmentInformation VALUES('就业部','66')
INSERT DepartmentInformation VALUES('策划部','50')
INSERT DepartmentInformation VALUES('软件开发部','67')
INSERT DepartmentInformation VALUES('招生部','26')


go
/*薪资信息表*/
CREATE TABLE WageInformation
(
   W_Number INT IDENTITY(1,1),
   W_Name VARCHAR(30) NOT NULL,                      --员工姓名
   W_BasicWage INT NOT NULL,                         --基本工资
   W_Boon INT NOT NULL,                              --福利
   W_Bonus INT NOT NULL,                             --奖金
   W_CountMethod VARCHAR(50) NOT NULL,               --计算方法
   W_FactWage INT NOT NULL,                          --实发工资
)

--SELECT * FROM WageInformation
INSERT WageInformation VALUES('张三','3000','300','200','基本工资 + 福利 + 奖金',3500)
INSERT WageInformation VALUES('麻五','3000','300','200','基本工资 + 福利 + 奖金',3500)
INSERT WageInformation VALUES('Andy','3000','300','200','基本工资 + 福利 + 奖金',3500)
INSERT WageInformation VALUES('陈一邦','3000','300','200','基本工资 + 福利 + 奖金',3500)
INSERT WageInformation VALUES('姚明','3000','300','200','基本工资 + 福利 + 奖金',3500)
INSERT WageInformation VALUES('胡来','3000','300','200','基本工资 + 福利 + 奖金',3500)
INSERT WageInformation VALUES('秋若','3000','300','200','基本工资 + 福利 + 奖金',3500)
INSERT WageInformation VALUES('宋成','3000','300','200','基本工资 + 福利 + 奖金',3500)
