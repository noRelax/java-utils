if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[tb_kehu_info]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
drop table [dbo].[tb_kehu_info]
GO

if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[tb_kucun_info]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
drop table [dbo].[tb_kucun_info]
GO

if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[tb_productType_info]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
drop table [dbo].[tb_productType_info]
GO

if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[tb_product_info]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
drop table [dbo].[tb_product_info]
GO

if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[tb_ruku_info]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
drop table [dbo].[tb_ruku_info]
GO

if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[tb_sell_info]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
drop table [dbo].[tb_sell_info]
GO

if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[tb_supply_info]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
drop table [dbo].[tb_supply_info]
GO

if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[tb_tuihuo_info]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
drop table [dbo].[tb_tuihuo_info]
GO

if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[tb_tuiku_info]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
drop table [dbo].[tb_tuiku_info]
GO

if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[tb_user_info]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
drop table [dbo].[tb_user_info]
GO

if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[tb_yewuyuan_info]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
drop table [dbo].[tb_yewuyuan_info]
GO

CREATE TABLE [dbo].[tb_kehu_info] (
	[kehuID] [int] IDENTITY (1, 1) NOT NULL ,
	[kehuName] [varchar] (50) COLLATE Chinese_PRC_CI_AS NOT NULL ,
	[kehuAddress] [varchar] (50) COLLATE Chinese_PRC_CI_AS NOT NULL ,
	[kehuPhone] [varchar] (30) COLLATE Chinese_PRC_CI_AS NOT NULL ,
	[kehuZip] [varchar] (30) COLLATE Chinese_PRC_CI_AS NULL ,
	[kehuConn] [varchar] (30) COLLATE Chinese_PRC_CI_AS NOT NULL ,
	[kehuConnPhone] [varchar] (30) COLLATE Chinese_PRC_CI_AS NOT NULL ,
	[kehuEmail] [varchar] (50) COLLATE Chinese_PRC_CI_AS NULL 
) ON [PRIMARY]
GO

CREATE TABLE [dbo].[tb_kucun_info] (
	[proID] [int] NOT NULL ,
	[kucunAcount] [int] NOT NULL 
) ON [PRIMARY]
GO

CREATE TABLE [dbo].[tb_productType_info] (
	[proTypeID] [int] IDENTITY (1, 1) NOT NULL ,
	[proTypeName] [varchar] (50) COLLATE Chinese_PRC_CI_AS NOT NULL ,
	[proTypeDanwei] [varchar] (30) COLLATE Chinese_PRC_CI_AS NOT NULL ,
	[proTypeCreateTime] [datetime] NOT NULL ,
	[ywyID] [int] NOT NULL ,
	[proTypeRemark] [varchar] (250) COLLATE Chinese_PRC_CI_AS NULL 
) ON [PRIMARY]
GO

CREATE TABLE [dbo].[tb_product_info] (
	[proID] [int] IDENTITY (1, 1) NOT NULL ,
	[proTypeID] [int] NOT NULL ,
	[proName] [varchar] (30) COLLATE Chinese_PRC_CI_AS NOT NULL ,
	[proPrice] [numeric](18, 0) NOT NULL ,
	[proCreateTime] [datetime] NOT NULL ,
	[ywyID] [int] NOT NULL ,
	[spID] [int] NOT NULL ,
	[proRemark] [varchar] (250) COLLATE Chinese_PRC_CI_AS NULL 
) ON [PRIMARY]
GO

CREATE TABLE [dbo].[tb_ruku_info] (
	[rukuID] [int] IDENTITY (1, 1) NOT NULL ,
	[rukuDateTime] [datetime] NOT NULL ,
	[rukuAcount] [int] NOT NULL ,
	[proID] [int] NOT NULL ,
	[ywyID] [int] NOT NULL ,
	[rukuRemark] [varchar] (250) COLLATE Chinese_PRC_CI_AS NOT NULL 
) ON [PRIMARY]
GO

CREATE TABLE [dbo].[tb_sell_info] (
	[sellID] [int] IDENTITY (1, 1) NOT NULL ,
	[sellDateTime] [datetime] NOT NULL ,
	[proID] [int] NOT NULL ,
	[sellAcount] [int] NOT NULL ,
	[proSellPrice] [numeric](18, 0) NOT NULL ,
	[ywyID] [int] NOT NULL ,
	[sellRemark] [varchar] (250) COLLATE Chinese_PRC_CI_AS NULL ,
	[kehuID] [int] NOT NULL 
) ON [PRIMARY]
GO

CREATE TABLE [dbo].[tb_supply_info] (
	[spID] [int] IDENTITY (1, 1) NOT NULL ,
	[spName] [varchar] (50) COLLATE Chinese_PRC_CI_AS NOT NULL ,
	[spAddress] [varchar] (50) COLLATE Chinese_PRC_CI_AS NOT NULL ,
	[spPhone] [varchar] (30) COLLATE Chinese_PRC_CI_AS NOT NULL ,
	[spZip] [varchar] (30) COLLATE Chinese_PRC_CI_AS NOT NULL ,
	[spConn] [varchar] (30) COLLATE Chinese_PRC_CI_AS NOT NULL ,
	[spConnPhone] [varchar] (30) COLLATE Chinese_PRC_CI_AS NOT NULL ,
	[spEmail] [varchar] (50) COLLATE Chinese_PRC_CI_AS NULL 
) ON [PRIMARY]
GO

CREATE TABLE [dbo].[tb_tuihuo_info] (
	[thID] [int] IDENTITY (1, 1) NOT NULL ,
	[sellID] [int] NOT NULL ,
	[thTime] [datetime] NOT NULL ,
	[thRemark] [varchar] (250) COLLATE Chinese_PRC_CI_AS NOT NULL ,
	[ywyID] [int] NOT NULL ,
	[thAcount] [int] NOT NULL 
) ON [PRIMARY]
GO

CREATE TABLE [dbo].[tb_tuiku_info] (
	[tkID] [int] IDENTITY (1, 1) NOT NULL ,
	[rukuID] [int] NOT NULL ,
	[tkTime] [datetime] NOT NULL ,
	[tkAcount] [int] NOT NULL ,
	[tkRemark] [varchar] (50) COLLATE Chinese_PRC_CI_AS NOT NULL ,
	[ywyID] [int] NOT NULL 
) ON [PRIMARY]
GO

CREATE TABLE [dbo].[tb_user_info] (
	[userID] [int] IDENTITY (1, 1) NOT NULL ,
	[userName] [varchar] (30) COLLATE Chinese_PRC_CI_AS NOT NULL ,
	[userPassWord] [varchar] (30) COLLATE Chinese_PRC_CI_AS NOT NULL ,
	[userPower] [int] NOT NULL 
) ON [PRIMARY]
GO

CREATE TABLE [dbo].[tb_yewuyuan_info] (
	[ywyID] [int] IDENTITY (1, 1) NOT NULL ,
	[ywyName] [varchar] (30) COLLATE Chinese_PRC_CI_AS NOT NULL ,
	[ywyAddress] [varchar] (50) COLLATE Chinese_PRC_CI_AS NOT NULL ,
	[ywyPhone] [varchar] (30) COLLATE Chinese_PRC_CI_AS NOT NULL 
) ON [PRIMARY]
GO

