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
/*Ա��������Ϣ��*/
CREATE TABLE EmployeeInformation
(
  E_Number INT IDENTITY(1,1),            --Ա�����
  E_Name VARCHAR(30) NOT NULL,                       --����
  E_Sex VARCHAR(2) NOT NULL,                         --�Ա�
  E_BornDate VARCHAR(30) NOT NULL,                   --��������
  E_Marriage VARCHAR(4) NOT NULL,                    --����״̬
  E_PoliticsVisage VARCHAR(20) NOT NULL,             --������ò
  E_SchoolAge VARCHAR(20) NOT NULL,                  --ѧ��
  E_EnterDate VARCHAR(30) NOT NULL,                  --���빫˾ʱ��
  E_InDueFormDate VARCHAR(30) NOT NULL,              --ת��ʱ��
  E_Department VARCHAR(20) NOT NULL,                 --����
  E_Headship VARCHAR(20) NOT NULL,                   --ְ��
  E_Estate VARCHAR(10) NOT NULL,                     --״̬
  E_Remark VARCHAR(500),                             --��ע
)
go
INSERT EmployeeInformation VALUES('����','Ů','1980��9��29��','δ��','��Ա','����','2004��9��10��','2004��12��10��','��ѧ��','����','��ְ','�߼���ʦ')
INSERT EmployeeInformation VALUES('aaa','��','1980��6��24��','δ��','��Ա','����','2004��9��10��','2004��12��10��','�������','����','��ְ','�߼�����ʦ')


SELECT * FROM EmployeeInformation
--DELETE FROM EmployeeInformation where E_Name = '����'

go
/*��ѵ��Ϣ��*/
CREATE TABLE TrainInformation
(
   T_Number VARCHAR(20) NOT NULL,                    --��ѵ���
   T_Content VARCHAR(100) NOT NULL,                  --��ѵ����
   T_Name VARCHAR(20) NOT NULL,                     --��ѵԱ������
   T_Date INT Not NULL,                              --��ѵ����
   T_Money INT                                       --��ѵ����
)
go
INSERT TrainInformation VALUES('2007_001','ְҵ����','����',30,7000)
INSERT TrainInformation VALUES('2007_002','ְҵ����','Ҧ��',30,7000)
INSERT TrainInformation VALUES('2007_003','ְҵ����','�γ�',30,7000)
INSERT TrainInformation VALUES('2007_004','ְҵ����','��С��',30,7000)
INSERT TrainInformation VALUES('2007_005','ְҵ����','������',30,7000)
INSERT TrainInformation VALUES('2007_006','ְҵ����','��С��',30,7000)
INSERT TrainInformation VALUES('2007_007','ְҵ����','���',30,7000)
INSERT TrainInformation VALUES('2007_008','ְҵ����','��һ��',30,7000)
INSERT TrainInformation VALUES('2007_009','ְҵ����','����',30,7000)

--SELECT * FROM TrainInformation


go
/*������Ϣ��*/
CREATE TABLE EncouragementPunishInformation
(
   EP_Number INT IDENTITY(1,1),
   EP_Name VARCHAR(30) NOT NULL,
   EP_Date VARCHAR(30) NOT NULL,
   EP_Address VARCHAR(50) NOT NULL,
   EP_Causation VARCHAR(200) NOT NULL,               --����ԭ��
   EP_Remark VARCHAR(500),                           --��ע
)
go
INSERT EncouragementPunishInformation VALUES('����','2006��5��3��','��ѧ�ݶ�¥','�ݽ�����һ�Ƚ�','afa')
INSERT EncouragementPunishInformation VALUES('��һ��','2004��5��3��','��ѧ�ݶ�¥','�ݽ�����һ�Ƚ�','aaaa')


--SELECT * FROM EncouragementPunishInformation 

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
