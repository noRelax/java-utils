CREATE DATABASE EmployeeInformationMS
--DROP DATABASE EmployeeInformationMS
go
USE EmployeeInformationMS


go
/*�û���Ϣ��*/
CREATE TABLE UserInformation
(
   User_ID INT IDENTITY(1,1),   
   User_Name VARCHAR(20) NOT NULL,
   Password VARCHAR(20) NOT NULL,
   Popedom VARCHAR(20) DEFAULT 'B', --Ȩ��
)   
go
INSERT UserInformation VALUES('����Ա','123','A')
INSERT UserInformation (User_Name,Password) VALUES('admin','123456')


--TRUNCATE TABLE UserInformation
--SELECT * FROM UserInformation


go
/*������Ϣ��*/
CREATE TABLE DepartmentInformation
(
   D_Number INT IDENTITY(1,1),
   D_Name VARCHAR(20) NOT NULL,
   D_Count VARCHAR(20) NOT NULL,
)
go
INSERT DepartmentInformation VALUES('�г���','50')
INSERT DepartmentInformation VALUES('��ѧ��','65')
INSERT DepartmentInformation VALUES('�ƻᲿ','26')
INSERT DepartmentInformation VALUES('��ѵ��','52')
INSERT DepartmentInformation VALUES('���ڲ�','69')
INSERT DepartmentInformation VALUES('��ҵ��','66')
INSERT DepartmentInformation VALUES('�߻���','50')
INSERT DepartmentInformation VALUES('���������','67')
INSERT DepartmentInformation VALUES('������','26')


go
/*н����Ϣ��*/
CREATE TABLE WageInformation
(
   W_Number INT IDENTITY(1,1),
   W_Name VARCHAR(30) NOT NULL,                      --Ա������
   W_BasicWage INT NOT NULL,                         --��������
   W_Boon INT NOT NULL,                              --����
   W_Bonus INT NOT NULL,                             --����
   W_CountMethod VARCHAR(50) NOT NULL,               --���㷽��
   W_FactWage INT NOT NULL,                          --ʵ������
)

--SELECT * FROM WageInformation
INSERT WageInformation VALUES('����','3000','300','200','�������� + ���� + ����',3500)
INSERT WageInformation VALUES('����','3000','300','200','�������� + ���� + ����',3500)
INSERT WageInformation VALUES('Andy','3000','300','200','�������� + ���� + ����',3500)
INSERT WageInformation VALUES('��һ��','3000','300','200','�������� + ���� + ����',3500)
INSERT WageInformation VALUES('Ҧ��','3000','300','200','�������� + ���� + ����',3500)
INSERT WageInformation VALUES('����','3000','300','200','�������� + ���� + ����',3500)
INSERT WageInformation VALUES('����','3000','300','200','�������� + ���� + ����',3500)
INSERT WageInformation VALUES('�γ�','3000','300','200','�������� + ���� + ����',3500)
