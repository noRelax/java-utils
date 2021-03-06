if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[art]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
drop table [dbo].[art]
GO

if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[art2]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
drop table [dbo].[art2]
GO

if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[science]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
drop table [dbo].[science]
GO

if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[science2]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
drop table [dbo].[science2]
GO

CREATE TABLE [dbo].[art] (
	[cardID] [bigint] NOT NULL ,
	[studentName] [varchar] (50) COLLATE Chinese_PRC_CI_AS NULL ,
	[schoolBefore] [varchar] (50) COLLATE Chinese_PRC_CI_AS NULL 
) ON [PRIMARY]
GO

CREATE TABLE [dbo].[art2] (
	[subjectID] [bigint] IDENTITY (1, 1) NOT NULL ,
	[cardID] [bigint] NOT NULL ,
	[subject] [varchar] (50) COLLATE Chinese_PRC_CI_AS NULL ,
	[achievement] [int] NULL ,
	[subjectNameID] [bigint] NULL 
) ON [PRIMARY]
GO

CREATE TABLE [dbo].[science] (
	[cardID] [bigint] NOT NULL ,
	[studentName] [varchar] (50) COLLATE Chinese_PRC_CI_AS NULL ,
	[schoolBefore] [varchar] (50) COLLATE Chinese_PRC_CI_AS NULL 
) ON [PRIMARY]
GO

CREATE TABLE [dbo].[science2] (
	[subjectID] [bigint] IDENTITY (1, 1) NOT NULL ,
	[cardID] [bigint] NOT NULL ,
	[subject] [varchar] (50) COLLATE Chinese_PRC_CI_AS NULL ,
	[achievement] [int] NULL ,
	[subjectNameID] [bigint] NULL 
) ON [PRIMARY]
GO

