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
/*员工基本信息表*/
CREATE TABLE EmployeeInformation
(
  E_Number INT IDENTITY(1,1),            --员工编号
  E_Name VARCHAR(30) NOT NULL,                       --姓名
  E_Sex VARCHAR(2) NOT NULL,                         --性别
  E_BornDate VARCHAR(30) NOT NULL,                   --出生日期
  E_Marriage VARCHAR(4) NOT NULL,                    --婚姻状态
  E_PoliticsVisage VARCHAR(20) NOT NULL,             --政治面貌
  E_SchoolAge VARCHAR(20) NOT NULL,                  --学历
  E_EnterDate VARCHAR(30) NOT NULL,                  --进入公司时间
  E_InDueFormDate VARCHAR(30) NOT NULL,              --转正时间
  E_Department VARCHAR(20) NOT NULL,                 --部门
  E_Headship VARCHAR(20) NOT NULL,                   --职务
  E_Estate VARCHAR(10) NOT NULL,                     --状态
  E_Remark VARCHAR(500),                             --备注
)
go
INSERT EmployeeInformation VALUES('张三','女','1980年9月29日','未婚','党员','本科','2004年9月10日','2004年12月10日','教学部','主管','在职','高级讲师')
INSERT EmployeeInformation VALUES('aaa','男','1980年6月24日','未婚','党员','本科','2004年9月10日','2004年12月10日','软件开发','主管','在职','高级工程师')


SELECT * FROM EmployeeInformation
--DELETE FROM EmployeeInformation where E_Name = '张三'

go
/*培训信息表*/
CREATE TABLE TrainInformation
(
   T_Number VARCHAR(20) NOT NULL,                    --培训编号
   T_Content VARCHAR(100) NOT NULL,                  --培训内容
   T_Name VARCHAR(20) NOT NULL,                     --培训员工姓名
   T_Date INT Not NULL,                              --培训天数
   T_Money INT                                       --培训费用
)
go
INSERT TrainInformation VALUES('2007_001','职业素质','张三',30,7000)
INSERT TrainInformation VALUES('2007_002','职业素质','姚明',30,7000)
INSERT TrainInformation VALUES('2007_003','职业素质','宋成',30,7000)
INSERT TrainInformation VALUES('2007_004','职业素质','甘小发',30,7000)
INSERT TrainInformation VALUES('2007_005','职业素质','陈天桥',30,7000)
INSERT TrainInformation VALUES('2007_006','职业素质','刘小成',30,7000)
INSERT TrainInformation VALUES('2007_007','职业素质','彭煌',30,7000)
INSERT TrainInformation VALUES('2007_008','职业素质','刘一发',30,7000)
INSERT TrainInformation VALUES('2007_009','职业素质','胡来',30,7000)

--SELECT * FROM TrainInformation


go
/*奖罚信息表*/
CREATE TABLE EncouragementPunishInformation
(
   EP_Number INT IDENTITY(1,1),
   EP_Name VARCHAR(30) NOT NULL,
   EP_Date VARCHAR(30) NOT NULL,
   EP_Address VARCHAR(50) NOT NULL,
   EP_Causation VARCHAR(200) NOT NULL,               --奖罚原因
   EP_Remark VARCHAR(500),                           --备注
)
go
INSERT EncouragementPunishInformation VALUES('张三','2006年5月3日','教学馆二楼','演讲比赛一等奖','afa')
INSERT EncouragementPunishInformation VALUES('刘一发','2004年5月3日','教学馆二楼','演讲比赛一等奖','aaaa')


--SELECT * FROM EncouragementPunishInformation 

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
