/*    ==Scripting Parameters==

    Source Server Version : SQL Server 2016 (13.0.1742)
    Source Database Engine Edition : Microsoft SQL Server Standard Edition
    Source Database Engine Type : Standalone SQL Server

    Target Server Version : SQL Server v160
    Target Database Engine Edition : Unknown
    Target Database Engine Type : Standalone SQL Server
*/

/****** Object:  Database [congbang0711_]    Script Date: 5/2/2023 10:57:53 PM ******/
CREATE DATABASE [congbang0711_] CONTAINMENT=NONE
ON PRIMARY(NAME=N'mindfulNESS', FILENAME=N'D:\sql-freeasphost-user-dbs\congbang0711_.mdf', SIZE=4352KB, MAXSIZE=UNLIMITED, FILEGROWTH=1024KB)
LOG ON(NAME=N'mindfulNESS_log', FILENAME=N'D:\sql-freeasphost-user-dbs\congbang0711_.ldf', SIZE=3840KB, MAXSIZE=2048GB, FILEGROWTH=10%)
COLLATE SQL_Latin1_General_CP1_CI_AS
GO
IF(1=FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))BEGIN
    EXEC [congbang0711_].[dbo].[sp_fulltext_database] @action='enable'
END
GO
ALTER DATABASE [congbang0711_] SET ANSI_NULL_DEFAULT OFF
GO
ALTER DATABASE [congbang0711_] SET ANSI_NULLS OFF
GO
ALTER DATABASE [congbang0711_] SET ANSI_PADDING OFF
GO
ALTER DATABASE [congbang0711_] SET ANSI_WARNINGS OFF
GO
ALTER DATABASE [congbang0711_] SET ARITHABORT OFF
GO
ALTER DATABASE [congbang0711_] SET AUTO_CLOSE ON
GO
ALTER DATABASE [congbang0711_] SET AUTO_SHRINK OFF
GO
ALTER DATABASE [congbang0711_] SET AUTO_UPDATE_STATISTICS ON
GO
ALTER DATABASE [congbang0711_] SET CURSOR_CLOSE_ON_COMMIT OFF
GO
ALTER DATABASE [congbang0711_] SET CURSOR_DEFAULT GLOBAL
GO
ALTER DATABASE [congbang0711_] SET CONCAT_NULL_YIELDS_NULL OFF
GO
ALTER DATABASE [congbang0711_] SET NUMERIC_ROUNDABORT OFF
GO
ALTER DATABASE [congbang0711_] SET QUOTED_IDENTIFIER OFF
GO
ALTER DATABASE [congbang0711_] SET RECURSIVE_TRIGGERS OFF
GO
ALTER DATABASE [congbang0711_] SET DISABLE_BROKER
GO
ALTER DATABASE [congbang0711_] SET AUTO_UPDATE_STATISTICS_ASYNC OFF
GO
ALTER DATABASE [congbang0711_] SET DATE_CORRELATION_OPTIMIZATION OFF
GO
ALTER DATABASE [congbang0711_] SET TRUSTWORTHY OFF
GO
ALTER DATABASE [congbang0711_] SET ALLOW_SNAPSHOT_ISOLATION OFF
GO
ALTER DATABASE [congbang0711_] SET PARAMETERIZATION SIMPLE
GO
ALTER DATABASE [congbang0711_] SET READ_COMMITTED_SNAPSHOT OFF
GO
ALTER DATABASE [congbang0711_] SET HONOR_BROKER_PRIORITY OFF
GO
ALTER DATABASE [congbang0711_] SET RECOVERY SIMPLE
GO
ALTER DATABASE [congbang0711_] SET MULTI_USER
GO
ALTER DATABASE [congbang0711_] SET PAGE_VERIFY CHECKSUM
GO
ALTER DATABASE [congbang0711_] SET DB_CHAINING OFF
GO
ALTER DATABASE [congbang0711_] SET FILESTREAM(NON_TRANSACTED_ACCESS=OFF)
GO
ALTER DATABASE [congbang0711_] SET TARGET_RECOVERY_TIME=0 SECONDS
GO
ALTER DATABASE [congbang0711_] SET DELAYED_DURABILITY=DISABLED
GO
ALTER DATABASE [congbang0711_] SET QUERY_STORE=OFF
GO
USE [congbang0711_]
GO
ALTER DATABASE SCOPED CONFIGURATION SET LEGACY_CARDINALITY_ESTIMATION=OFF;
GO
ALTER DATABASE SCOPED CONFIGURATION SET MAXDOP=0;
GO
ALTER DATABASE SCOPED CONFIGURATION SET PARAMETER_SNIFFING=ON;
GO
ALTER DATABASE SCOPED CONFIGURATION SET QUERY_OPTIMIZER_HOTFIXES=OFF;
GO
GRANT VIEW ANY COLUMN ENCRYPTION KEY DEFINITION TO [public] AS [dbo]
GO
GRANT VIEW ANY COLUMN MASTER KEY DEFINITION TO [public] AS [dbo]
GO
ALTER DATABASE [congbang0711_] SET READ_WRITE
GO
USE [congbang0711_]
GO
/****** Object:  Schema [Account]    Script Date: 5/2/2023 11:00:23 PM ******/
CREATE SCHEMA [Account]
GO
/****** Object:  Schema [Booking]    Script Date: 5/2/2023 11:00:23 PM ******/
CREATE SCHEMA [Booking]
GO
/****** Object:  Schema [Disease]    Script Date: 5/2/2023 11:00:23 PM ******/
CREATE SCHEMA [Disease]
GO
/****** Object:  Schema [Solution]    Script Date: 5/2/2023 11:00:23 PM ******/
CREATE SCHEMA [Solution]
GO
/****** Object:  Schema [Test]    Script Date: 5/2/2023 11:00:23 PM ******/
CREATE SCHEMA [Test]
GO
/****** Object:  Table [Account].[Account]    Script Date: 5/2/2023 11:00:23 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [Account].[Account] ([User_ID] [bigint] IDENTITY(1, 1) NOT NULL,
[User_name] [varchar](50) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL,
[Salt] [varchar](100) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL,
[Password] [varbinary](20) NOT NULL,
PRIMARY KEY CLUSTERED([User_ID] ASC)WITH(PAD_INDEX=OFF, STATISTICS_NORECOMPUTE=OFF, IGNORE_DUP_KEY=OFF, ALLOW_ROW_LOCKS= ON, ALLOW_PAGE_LOCKS=ON)ON [PRIMARY])ON [PRIMARY]
GO
/****** Object:  Table [Account].[Patient]    Script Date: 5/2/2023 11:00:24 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [Account].[Patient] ([Patient_ID] [int] IDENTITY(1, 1) NOT NULL,
[UserID] [bigint] NOT NULL,
[FullName] [varchar](50) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL,
[DoB] [date] NOT NULL,
[Sex] [varchar](10) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL,
[Email] [varchar](50) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL,
PRIMARY KEY CLUSTERED([Patient_ID] ASC)WITH(PAD_INDEX=OFF, STATISTICS_NORECOMPUTE=OFF, IGNORE_DUP_KEY=OFF, ALLOW_ROW_LOCKS= ON, ALLOW_PAGE_LOCKS=ON)ON [PRIMARY])ON [PRIMARY]
GO
/****** Object:  Table [Account].[PatientRecord]    Script Date: 5/2/2023 11:00:24 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [Account].[PatientRecord] ([ID] [int] IDENTITY(1, 1) NOT NULL,
[PatientID] [int] NOT NULL,
[ResultID] [int] NOT NULL,
PRIMARY KEY CLUSTERED([ID] ASC)WITH(PAD_INDEX=OFF, STATISTICS_NORECOMPUTE=OFF, IGNORE_DUP_KEY=OFF, ALLOW_ROW_LOCKS= ON, ALLOW_PAGE_LOCKS=ON)ON [PRIMARY])ON [PRIMARY]
GO
/****** Object:  Table [Account].[Specialist]    Script Date: 5/2/2023 11:00:24 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [Account].[Specialist] ([Specialist_ID] [int] IDENTITY(1, 1) NOT NULL,
[UserID] [bigint] NOT NULL,
[FullName] [varchar](50) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL,
[DoB] [date] NOT NULL,
[Sex] [varchar](10) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL,
[Email] [varchar](50) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL,
[Phone] [varchar](15) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL,
[GraduationUniversity] [varchar](50) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL,
[IdentifyNumber] [varchar](30) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
PRIMARY KEY CLUSTERED([Specialist_ID] ASC)WITH(PAD_INDEX=OFF, STATISTICS_NORECOMPUTE=OFF, IGNORE_DUP_KEY=OFF, ALLOW_ROW_LOCKS= ON, ALLOW_PAGE_LOCKS=ON)ON [PRIMARY])ON [PRIMARY]
GO
/****** Object:  Table [Booking].[Booking]    Script Date: 5/2/2023 11:00:24 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [Booking].[Booking] ([Book_ID] [int] IDENTITY(1, 1) NOT NULL,
[PatientID] [int] NOT NULL,
[HealingInformationID] [int] NOT NULL,
PRIMARY KEY CLUSTERED([Book_ID] ASC)WITH(PAD_INDEX=OFF, STATISTICS_NORECOMPUTE=OFF, IGNORE_DUP_KEY=OFF, ALLOW_ROW_LOCKS= ON, ALLOW_PAGE_LOCKS=ON)ON [PRIMARY])ON [PRIMARY]
GO
/****** Object:  Table [Booking].[HealingInformation]    Script Date: 5/2/2023 11:00:24 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [Booking].[HealingInformation] ([HealingInformation_ID] [int] IDENTITY(1, 1) NOT NULL,
[SpecialistID] [int] NOT NULL,
[Place] [varchar](50) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL,
[Date] [date] NOT NULL,
[Fee] [money] NOT NULL,
[Description] [varchar](200) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL,
[Extra_Information] [varchar](100) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
PRIMARY KEY CLUSTERED([HealingInformation_ID] ASC)WITH(PAD_INDEX=OFF, STATISTICS_NORECOMPUTE=OFF, IGNORE_DUP_KEY=OFF, ALLOW_ROW_LOCKS= ON, ALLOW_PAGE_LOCKS=ON)ON [PRIMARY])ON [PRIMARY]
GO
/****** Object:  Table [Disease].[Disease]    Script Date: 5/2/2023 11:00:24 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [Disease].[Disease] ([Disease_ID] [int] NOT NULL,
[Name] [varchar](50) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL,
[Description] [varchar](200) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL,
PRIMARY KEY CLUSTERED([Disease_ID] ASC)WITH(PAD_INDEX=OFF, STATISTICS_NORECOMPUTE=OFF, IGNORE_DUP_KEY=OFF, ALLOW_ROW_LOCKS= ON, ALLOW_PAGE_LOCKS=ON)ON [PRIMARY])ON [PRIMARY]
GO
/****** Object:  Table [Disease].[DiseaseSymptom]    Script Date: 5/2/2023 11:00:24 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [Disease].[DiseaseSymptom] ([ID] [int] NOT NULL,
[DiseaseID] [int] NOT NULL,
[SymptomID] [int] NOT NULL,
PRIMARY KEY CLUSTERED([ID] ASC)WITH(PAD_INDEX=OFF, STATISTICS_NORECOMPUTE=OFF, IGNORE_DUP_KEY=OFF, ALLOW_ROW_LOCKS= ON, ALLOW_PAGE_LOCKS=ON)ON [PRIMARY])ON [PRIMARY]
GO
/****** Object:  Table [Disease].[Symptom]    Script Date: 5/2/2023 11:00:24 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [Disease].[Symptom] ([Symptom_ID] [int] NOT NULL,
[Name] [varchar](50) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL,
[Description] [varchar](100) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL,
PRIMARY KEY CLUSTERED([Symptom_ID] ASC)WITH(PAD_INDEX=OFF, STATISTICS_NORECOMPUTE=OFF, IGNORE_DUP_KEY=OFF, ALLOW_ROW_LOCKS= ON, ALLOW_PAGE_LOCKS=ON)ON [PRIMARY])ON [PRIMARY]
GO
/****** Object:  Table [Solution].[CureOneByOne]    Script Date: 5/2/2023 11:00:24 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [Solution].[CureOneByOne] ([ID] [int] NOT NULL,
[SolutionID] [int] NULL,
[SymptomID] [int] NOT NULL,
PRIMARY KEY CLUSTERED([ID] ASC)WITH(PAD_INDEX=OFF, STATISTICS_NORECOMPUTE=OFF, IGNORE_DUP_KEY=OFF, ALLOW_ROW_LOCKS= ON, ALLOW_PAGE_LOCKS=ON)ON [PRIMARY])ON [PRIMARY]
GO
/****** Object:  Table [Solution].[Recommendation]    Script Date: 5/2/2023 11:00:24 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [Solution].[Recommendation] ([ID] [int] NOT NULL,
[SolutionID] [int] NOT NULL,
[ResultID] [int] NOT NULL,
PRIMARY KEY CLUSTERED([ID] ASC)WITH(PAD_INDEX=OFF, STATISTICS_NORECOMPUTE=OFF, IGNORE_DUP_KEY=OFF, ALLOW_ROW_LOCKS= ON, ALLOW_PAGE_LOCKS=ON)ON [PRIMARY])ON [PRIMARY]
GO
/****** Object:  Table [Solution].[Solution]    Script Date: 5/2/2023 11:00:24 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [Solution].[Solution] ([Solution_ID] [int] NOT NULL,
[Name] [varchar](50) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL,
[Type] [varchar](50) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL,
[Benefit] [varchar](50) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL,
[Platform] [varchar](30) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL,
[Description] [varchar](400) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
PRIMARY KEY CLUSTERED([Solution_ID] ASC)WITH(PAD_INDEX=OFF, STATISTICS_NORECOMPUTE=OFF, IGNORE_DUP_KEY=OFF, ALLOW_ROW_LOCKS= ON, ALLOW_PAGE_LOCKS=ON)ON [PRIMARY])ON [PRIMARY]
GO
/****** Object:  Table [Test].[Answer]    Script Date: 5/2/2023 11:00:24 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [Test].[Answer] ([Answer_ID] [int] NOT NULL,
[Title] [varchar](100) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL,
[Weight] [int] NOT NULL,
PRIMARY KEY CLUSTERED([Answer_ID] ASC)WITH(PAD_INDEX=OFF, STATISTICS_NORECOMPUTE=OFF, IGNORE_DUP_KEY=OFF, ALLOW_ROW_LOCKS= ON, ALLOW_PAGE_LOCKS=ON)ON [PRIMARY])ON [PRIMARY]
GO
/****** Object:  Table [Test].[AnswerSet]    Script Date: 5/2/2023 11:00:24 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [Test].[AnswerSet] ([AnswerSet_ID] [int] NOT NULL,
[No_Answer] [int] NOT NULL,
PRIMARY KEY CLUSTERED([AnswerSet_ID] ASC)WITH(PAD_INDEX=OFF, STATISTICS_NORECOMPUTE=OFF, IGNORE_DUP_KEY=OFF, ALLOW_ROW_LOCKS= ON, ALLOW_PAGE_LOCKS=ON)ON [PRIMARY])ON [PRIMARY]
GO
/****** Object:  Table [Test].[AnswerSetContent]    Script Date: 5/2/2023 11:00:24 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [Test].[AnswerSetContent] ([ID] [int] NOT NULL,
[AnswerSetID] [int] NOT NULL,
[AnswerID] [int] NOT NULL,
PRIMARY KEY CLUSTERED([ID] ASC)WITH(PAD_INDEX=OFF, STATISTICS_NORECOMPUTE=OFF, IGNORE_DUP_KEY=OFF, ALLOW_ROW_LOCKS= ON, ALLOW_PAGE_LOCKS=ON)ON [PRIMARY])ON [PRIMARY]
GO
/****** Object:  Table [Test].[Question]    Script Date: 5/2/2023 11:00:24 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [Test].[Question] ([Question_ID] [int] NOT NULL,
[TestID] [int] NOT NULL,
[Title] [varchar](500) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL,
[AnswerSetID] [int] NOT NULL,
PRIMARY KEY CLUSTERED([Question_ID] ASC)WITH(PAD_INDEX=OFF, STATISTICS_NORECOMPUTE=OFF, IGNORE_DUP_KEY=OFF, ALLOW_ROW_LOCKS= ON, ALLOW_PAGE_LOCKS=ON)ON [PRIMARY])ON [PRIMARY]
GO
/****** Object:  Table [Test].[Result]    Script Date: 5/2/2023 11:00:24 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [Test].[Result] ([Result_ID] [int] NOT NULL,
[TestID] [int] NOT NULL,
[Weight] [int] NOT NULL,
[Description] [varchar](200) COLLATE SQL_Latin1_General_CP1_CI_AS NULL,
PRIMARY KEY CLUSTERED([Result_ID] ASC)WITH(PAD_INDEX=OFF, STATISTICS_NORECOMPUTE=OFF, IGNORE_DUP_KEY=OFF, ALLOW_ROW_LOCKS= ON, ALLOW_PAGE_LOCKS=ON)ON [PRIMARY])ON [PRIMARY]
GO
/****** Object:  Table [Test].[Test]    Script Date: 5/2/2023 11:00:24 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [Test].[Test] ([Test_ID] [int] NOT NULL,
[Total] [int] NULL,
[No_Question] [int] NULL,
[DiseaseID] [int] NOT NULL,
PRIMARY KEY CLUSTERED([Test_ID] ASC)WITH(PAD_INDEX=OFF, STATISTICS_NORECOMPUTE=OFF, IGNORE_DUP_KEY=OFF, ALLOW_ROW_LOCKS= ON, ALLOW_PAGE_LOCKS=ON)ON [PRIMARY])ON [PRIMARY]
GO
SET IDENTITY_INSERT [Account].[Account] ON
INSERT [Account].[Account]([User_ID], [User_name], [Salt], [Password])
VALUES(1, N'tucute123', N'9Pn(2', 0xDD4689A6BF12DFA64DDD713199BA53B51F65AD53)
INSERT [Account].[Account]([User_ID], [User_name], [Salt], [Password])
VALUES(2, N'lamthanhluan2907', N'6Eb(2', 0x698BF12AA5B33A21903D296B5784158A6DFB1378)
INSERT [Account].[Account]([User_ID], [User_name], [Salt], [Password])
VALUES(3, N'vanbui.work', N'0Lw%2', 0x01D1D332798FD5D6EE495294886ABD5886418F1A)
INSERT [Account].[Account]([User_ID], [User_name], [Salt], [Password])
VALUES(4, N'guangyaoaoao', N'0Rk$8', 0xAC9B7169C63453090BDAA6E857338528B77BC43F)
INSERT [Account].[Account]([User_ID], [User_name], [Salt], [Password])
VALUES(5, N'congbang', N'5Sj%3', 0xC17EDC261652EC7CD357BC6850B8962B9EE9216A)
INSERT [Account].[Account]([User_ID], [User_name], [Salt], [Password])
VALUES(6, N'phamvu', N'5Ne+2', 0x4D7B70B7A888954D99964B9505B69704A26D021B)
INSERT [Account].[Account]([User_ID], [User_name], [Salt], [Password])
VALUES(7, N'huynhtrankhanh', N'2Pt$2', 0x1EB4AAB4E6FDDD20ED2EEFF5B446310DED9F157F)
INSERT [Account].[Account]([User_ID], [User_name], [Salt], [Password])
VALUES(19, N'dtrnhminh', N'7Qc*5', 0x75DD830911053C8B57E92AF92AD1C095975558EE)
INSERT [Account].[Account]([User_ID], [User_name], [Salt], [Password])
VALUES(22, N'bichphuong', N'5Et+1', 0xD4F5C6E6DC7E2CFDA5D5005A978985F9F62F6E9D)
INSERT [Account].[Account]([User_ID], [User_name], [Salt], [Password])
VALUES(23, N'hokieumy', N'7Ei+6', 0xFF92C13996563C874EE38C9F7FB87B99ABDD751B)
INSERT [Account].[Account]([User_ID], [User_name], [Salt], [Password])
VALUES(28, N'trducbao', N'0Jg''6', 0xFB1117B964E0887B5218A70C0ADBE8A451717836)
INSERT [Account].[Account]([User_ID], [User_name], [Salt], [Password])
VALUES(41, N'xitrum123', N'7Fs"4', 0xF07874D3ABAC2710DF8F64E0F1EDF5651D39568C)
SET IDENTITY_INSERT [Account].[Account] OFF
GO
SET IDENTITY_INSERT [Account].[Patient] ON
INSERT [Account].[Patient]([Patient_ID], [UserID], [FullName], [DoB], [Sex], [Email])
VALUES(1, 1, N'Nguyen Hoang Anh Tu', CAST(N'2002-06-13' AS Date), N'Male', N'nghganhtu@gmail.com')
INSERT [Account].[Patient]([Patient_ID], [UserID], [FullName], [DoB], [Sex], [Email])
VALUES(2, 3, N'Bui Thi Cam Van', CAST(N'2002-12-08' AS Date), N'Female', N'buicamvan.work@gmail.com')
INSERT [Account].[Patient]([Patient_ID], [UserID], [FullName], [DoB], [Sex], [Email])
VALUES(3, 4, N'Nguyen Quang Dieu', CAST(N'2002-05-29' AS Date), N'Male', N'guangyao@gmail.com')
INSERT [Account].[Patient]([Patient_ID], [UserID], [FullName], [DoB], [Sex], [Email])
VALUES(4, 5, N'Nguyen Luan Cong Bang', CAST(N'2002-11-07' AS Date), N'Male', N'congbang.1107@gmail.com')
INSERT [Account].[Patient]([Patient_ID], [UserID], [FullName], [DoB], [Sex], [Email])
VALUES(5, 7, N'Huynh Tran Khanh', CAST(N'2003-01-13' AS Date), N'Male', N'htk2003@gmail.com')
INSERT [Account].[Patient]([Patient_ID], [UserID], [FullName], [DoB], [Sex], [Email])
VALUES(17, 19, N'Duong Tran Nhat Minh', CAST(N'2002-10-29' AS Date), N'Male', N'dtrnhminh@gmail.com')
INSERT [Account].[Patient]([Patient_ID], [UserID], [FullName], [DoB], [Sex], [Email])
VALUES(18, 22, N'Nguyen Bich Phuong', CAST(N'1998-12-12' AS Date), N'Female', N'bphuong@gmail.com')
INSERT [Account].[Patient]([Patient_ID], [UserID], [FullName], [DoB], [Sex], [Email])
VALUES(19, 23, N'Ho Kieu My', CAST(N'2002-10-10' AS Date), N'Female', N'hkmy@gmail.com')
INSERT [Account].[Patient]([Patient_ID], [UserID], [FullName], [DoB], [Sex], [Email])
VALUES(22, 41, N'Xi Trum', CAST(N'2002-12-12' AS Date), N'Female', N'xitrum123@gmail.com')
SET IDENTITY_INSERT [Account].[Patient] OFF
GO
SET IDENTITY_INSERT [Account].[PatientRecord] ON
INSERT [Account].[PatientRecord]([ID], [PatientID], [ResultID])
VALUES(1, 1, 2)
INSERT [Account].[PatientRecord]([ID], [PatientID], [ResultID])
VALUES(2, 5, 19)
INSERT [Account].[PatientRecord]([ID], [PatientID], [ResultID])
VALUES(3, 1, 4)
INSERT [Account].[PatientRecord]([ID], [PatientID], [ResultID])
VALUES(4, 3, 15)
INSERT [Account].[PatientRecord]([ID], [PatientID], [ResultID])
VALUES(5, 4, 1)
INSERT [Account].[PatientRecord]([ID], [PatientID], [ResultID])
VALUES(6, 1, 20)
INSERT [Account].[PatientRecord]([ID], [PatientID], [ResultID])
VALUES(7, 1, 17)
INSERT [Account].[PatientRecord]([ID], [PatientID], [ResultID])
VALUES(8, 5, 2)
INSERT [Account].[PatientRecord]([ID], [PatientID], [ResultID])
VALUES(9, 5, 3)
INSERT [Account].[PatientRecord]([ID], [PatientID], [ResultID])
VALUES(10, 3, 16)
INSERT [Account].[PatientRecord]([ID], [PatientID], [ResultID])
VALUES(11, 4, 8)
INSERT [Account].[PatientRecord]([ID], [PatientID], [ResultID])
VALUES(12, 1, 1)
INSERT [Account].[PatientRecord]([ID], [PatientID], [ResultID])
VALUES(13, 1, 1)
INSERT [Account].[PatientRecord]([ID], [PatientID], [ResultID])
VALUES(14, 1, 1)
SET IDENTITY_INSERT [Account].[PatientRecord] OFF
GO
SET IDENTITY_INSERT [Account].[Specialist] ON
INSERT [Account].[Specialist]([Specialist_ID], [UserID], [FullName], [DoB], [Sex], [Email], [Phone], [GraduationUniversity], [IdentifyNumber])
VALUES(1, 2, N'Lam Thanh Luan', CAST(N'1990-06-13' AS Date), N'Male', N'lamthanhluan@gmail.com', N'0913517039', N'Sai Gon University', N'403697021687539')
INSERT [Account].[Specialist]([Specialist_ID], [UserID], [FullName], [DoB], [Sex], [Email], [Phone], [GraduationUniversity], [IdentifyNumber])
VALUES(2, 6, N'Pham Minh Vu', CAST(N'1985-06-13' AS Date), N'Male', N'laimaybay@gmail.com', N'0947513068', N'International University', N'5103697140687439')
INSERT [Account].[Specialist]([Specialist_ID], [UserID], [FullName], [DoB], [Sex], [Email], [Phone], [GraduationUniversity], [IdentifyNumber])
VALUES(5, 28, N'Trinh Duc Bao', CAST(N'2002-09-06' AS Date), N'Male', N'trducbo@gmail.com', N'0944738291', N'International University', N'032428934334')
SET IDENTITY_INSERT [Account].[Specialist] OFF
GO
SET IDENTITY_INSERT [Booking].[Booking] ON
INSERT [Booking].[Booking]([Book_ID], [PatientID], [HealingInformationID])
VALUES(1, 5, 1)
INSERT [Booking].[Booking]([Book_ID], [PatientID], [HealingInformationID])
VALUES(3, 2, 2)
INSERT [Booking].[Booking]([Book_ID], [PatientID], [HealingInformationID])
VALUES(5, 1, 4)
INSERT [Booking].[Booking]([Book_ID], [PatientID], [HealingInformationID])
VALUES(6, 1, 8)
SET IDENTITY_INSERT [Booking].[Booking] OFF
GO
SET IDENTITY_INSERT [Booking].[HealingInformation] ON
INSERT [Booking].[HealingInformation]([HealingInformation_ID], [SpecialistID], [Place], [Date], [Fee], [Description], [Extra_Information])
VALUES(1, 1, N'Quan 3', CAST(N'2023-05-01' AS Date), 300000.0000, N'I will help you to get out of your mental problem within 1 hour', N'Please come in the morning')
INSERT [Booking].[HealingInformation]([HealingInformation_ID], [SpecialistID], [Place], [Date], [Fee], [Description], [Extra_Information])
VALUES(2, 2, N'Quan 1', CAST(N'2023-05-01' AS Date), 400000.0000, N'If you need help, come to me', NULL)
INSERT [Booking].[HealingInformation]([HealingInformation_ID], [SpecialistID], [Place], [Date], [Fee], [Description], [Extra_Information])
VALUES(4, 2, N'Quan 1', CAST(N'2023-05-05' AS Date), 400000.0000, N'If you need help, come to me', N'I am free in the afternoon')
INSERT [Booking].[HealingInformation]([HealingInformation_ID], [SpecialistID], [Place], [Date], [Fee], [Description], [Extra_Information])
VALUES(5, 2, N'Quan 1', CAST(N'2023-05-06' AS Date), 400000.0000, N'If you need help, come to me', NULL)
INSERT [Booking].[HealingInformation]([HealingInformation_ID], [SpecialistID], [Place], [Date], [Fee], [Description], [Extra_Information])
VALUES(6, 2, N'Quan 1', CAST(N'2023-05-13' AS Date), 400000.0000, N'If you need help, come to me', NULL)
INSERT [Booking].[HealingInformation]([HealingInformation_ID], [SpecialistID], [Place], [Date], [Fee], [Description], [Extra_Information])
VALUES(7, 1, N'Quan 3', CAST(N'2023-05-13' AS Date), 300000.0000, N'I will help you to get out of your mental problem within 1 hour', NULL)
INSERT [Booking].[HealingInformation]([HealingInformation_ID], [SpecialistID], [Place], [Date], [Fee], [Description], [Extra_Information])
VALUES(8, 2, N'Quan 1', CAST(N'2023-05-16' AS Date), 400000.0000, N'If you need help, come to me', N'I am free in the afternoon')
INSERT [Booking].[HealingInformation]([HealingInformation_ID], [SpecialistID], [Place], [Date], [Fee], [Description], [Extra_Information])
VALUES(9, 1, N'Quan 3', CAST(N'2023-05-18' AS Date), 300000.0000, N'I will help you to get out of your mental problem within 1 hour', N'The meeting will be hold online')
INSERT [Booking].[HealingInformation]([HealingInformation_ID], [SpecialistID], [Place], [Date], [Fee], [Description], [Extra_Information])
VALUES(10, 2, N'Quan 1', CAST(N'2023-05-20' AS Date), 400000.0000, N'If you need help, come to me', NULL)
INSERT [Booking].[HealingInformation]([HealingInformation_ID], [SpecialistID], [Place], [Date], [Fee], [Description], [Extra_Information])
VALUES(16, 1, N'Quan 1', CAST(N'2023-05-03' AS Date), 300000.0000, N'Come with me', N'')
INSERT [Booking].[HealingInformation]([HealingInformation_ID], [SpecialistID], [Place], [Date], [Fee], [Description], [Extra_Information])
VALUES(18, 1, N'Quan 2', CAST(N'2023-05-12' AS Date), 300000.0000, N'Cam on ban!', N'')
SET IDENTITY_INSERT [Booking].[HealingInformation] OFF
GO
INSERT [Disease].[Disease]([Disease_ID], [Name], [Description])
VALUES(1, N'Depression', N'Depression is a mood disorder that causes a persistent feeling of sadness and loss of interest.')
INSERT [Disease].[Disease]([Disease_ID], [Name], [Description])
VALUES(2, N'Stress', N'Stress is a consequence of experiencing pressure or tension.')
INSERT [Disease].[Disease]([Disease_ID], [Name], [Description])
VALUES(3, N'Anxiety', N'Anxiety is an emotion caused by a perceived or experienced threat, which often leads to an avoidance or evasion thereof.')
INSERT [Disease].[Disease]([Disease_ID], [Name], [Description])
VALUES(4, N'Borderline', N'Borderline is a personality disorder. People with borderline have fast-changing feelings toward friendship, love, and so on.')
INSERT [Disease].[Disease]([Disease_ID], [Name], [Description])
VALUES(5, N'Migraine', N'Migraine is an illness, where regular intense attacks of headache occur. In the case of migraine, this headache often only takes place at one side of the head, and causes a pounding type of pain')
INSERT [Disease].[Disease]([Disease_ID], [Name], [Description])
VALUES(6, N'Alzheimer', N'Alzheimer disease is a brain disorder that slowly destroys memory and thinking skills and, eventually, the ability to carry out the simplest tasks.')
INSERT [Disease].[Disease]([Disease_ID], [Name], [Description])
VALUES(7, N'Burnout', N'Burnout is a mental disorder, in which you are totally burned out. You just cannot go on and are often very emotional.')
INSERT [Disease].[Disease]([Disease_ID], [Name], [Description])
VALUES(8, N'Schizophrenia', N'Schizophrenia is a mental disorder characterized by continuous or relapsing episodes of psychosis')
INSERT [Disease].[Disease]([Disease_ID], [Name], [Description])
VALUES(9, N'Post-Traumatic Stress Disorder', N'Posttraumatic Stress Disorder (PTSD) is a psychiatric disorder that can occur in people who have experienced or witnessed a traumatic event, series of events or set of circumstances.')
INSERT [Disease].[Disease]([Disease_ID], [Name], [Description])
VALUES(10, N'Bipolar Disorder', N'Bipolar disorder is a brain disorders that causes changes in a person’s mood, energy, and ability to function.')
GO
INSERT [Disease].[DiseaseSymptom]([ID], [DiseaseID], [SymptomID])
VALUES(1, 1, 15)
INSERT [Disease].[DiseaseSymptom]([ID], [DiseaseID], [SymptomID])
VALUES(2, 1, 30)
INSERT [Disease].[DiseaseSymptom]([ID], [DiseaseID], [SymptomID])
VALUES(3, 1, 19)
INSERT [Disease].[DiseaseSymptom]([ID], [DiseaseID], [SymptomID])
VALUES(4, 1, 31)
INSERT [Disease].[DiseaseSymptom]([ID], [DiseaseID], [SymptomID])
VALUES(5, 1, 2)
INSERT [Disease].[DiseaseSymptom]([ID], [DiseaseID], [SymptomID])
VALUES(6, 1, 26)
INSERT [Disease].[DiseaseSymptom]([ID], [DiseaseID], [SymptomID])
VALUES(7, 1, 28)
INSERT [Disease].[DiseaseSymptom]([ID], [DiseaseID], [SymptomID])
VALUES(8, 1, 29)
INSERT [Disease].[DiseaseSymptom]([ID], [DiseaseID], [SymptomID])
VALUES(9, 1, 32)
INSERT [Disease].[DiseaseSymptom]([ID], [DiseaseID], [SymptomID])
VALUES(10, 1, 7)
INSERT [Disease].[DiseaseSymptom]([ID], [DiseaseID], [SymptomID])
VALUES(11, 1, 9)
INSERT [Disease].[DiseaseSymptom]([ID], [DiseaseID], [SymptomID])
VALUES(12, 1, 14)
INSERT [Disease].[DiseaseSymptom]([ID], [DiseaseID], [SymptomID])
VALUES(13, 1, 23)
INSERT [Disease].[DiseaseSymptom]([ID], [DiseaseID], [SymptomID])
VALUES(14, 1, 27)
INSERT [Disease].[DiseaseSymptom]([ID], [DiseaseID], [SymptomID])
VALUES(15, 2, 1)
INSERT [Disease].[DiseaseSymptom]([ID], [DiseaseID], [SymptomID])
VALUES(16, 2, 2)
INSERT [Disease].[DiseaseSymptom]([ID], [DiseaseID], [SymptomID])
VALUES(17, 2, 26)
INSERT [Disease].[DiseaseSymptom]([ID], [DiseaseID], [SymptomID])
VALUES(18, 2, 17)
INSERT [Disease].[DiseaseSymptom]([ID], [DiseaseID], [SymptomID])
VALUES(19, 2, 33)
INSERT [Disease].[DiseaseSymptom]([ID], [DiseaseID], [SymptomID])
VALUES(20, 2, 6)
INSERT [Disease].[DiseaseSymptom]([ID], [DiseaseID], [SymptomID])
VALUES(21, 2, 15)
INSERT [Disease].[DiseaseSymptom]([ID], [DiseaseID], [SymptomID])
VALUES(22, 2, 30)
INSERT [Disease].[DiseaseSymptom]([ID], [DiseaseID], [SymptomID])
VALUES(23, 2, 16)
INSERT [Disease].[DiseaseSymptom]([ID], [DiseaseID], [SymptomID])
VALUES(24, 2, 25)
INSERT [Disease].[DiseaseSymptom]([ID], [DiseaseID], [SymptomID])
VALUES(25, 2, 9)
INSERT [Disease].[DiseaseSymptom]([ID], [DiseaseID], [SymptomID])
VALUES(26, 2, 11)
INSERT [Disease].[DiseaseSymptom]([ID], [DiseaseID], [SymptomID])
VALUES(27, 2, 13)
INSERT [Disease].[DiseaseSymptom]([ID], [DiseaseID], [SymptomID])
VALUES(28, 2, 14)
INSERT [Disease].[DiseaseSymptom]([ID], [DiseaseID], [SymptomID])
VALUES(29, 3, 11)
INSERT [Disease].[DiseaseSymptom]([ID], [DiseaseID], [SymptomID])
VALUES(30, 3, 2)
INSERT [Disease].[DiseaseSymptom]([ID], [DiseaseID], [SymptomID])
VALUES(31, 3, 9)
INSERT [Disease].[DiseaseSymptom]([ID], [DiseaseID], [SymptomID])
VALUES(32, 3, 26)
INSERT [Disease].[DiseaseSymptom]([ID], [DiseaseID], [SymptomID])
VALUES(33, 3, 17)
INSERT [Disease].[DiseaseSymptom]([ID], [DiseaseID], [SymptomID])
VALUES(34, 3, 34)
INSERT [Disease].[DiseaseSymptom]([ID], [DiseaseID], [SymptomID])
VALUES(35, 3, 18)
INSERT [Disease].[DiseaseSymptom]([ID], [DiseaseID], [SymptomID])
VALUES(36, 4, 21)
INSERT [Disease].[DiseaseSymptom]([ID], [DiseaseID], [SymptomID])
VALUES(37, 4, 23)
INSERT [Disease].[DiseaseSymptom]([ID], [DiseaseID], [SymptomID])
VALUES(38, 4, 6)
INSERT [Disease].[DiseaseSymptom]([ID], [DiseaseID], [SymptomID])
VALUES(39, 4, 27)
INSERT [Disease].[DiseaseSymptom]([ID], [DiseaseID], [SymptomID])
VALUES(40, 4, 22)
INSERT [Disease].[DiseaseSymptom]([ID], [DiseaseID], [SymptomID])
VALUES(41, 4, 25)
INSERT [Disease].[DiseaseSymptom]([ID], [DiseaseID], [SymptomID])
VALUES(42, 4, 35)
INSERT [Disease].[DiseaseSymptom]([ID], [DiseaseID], [SymptomID])
VALUES(43, 5, 35)
INSERT [Disease].[DiseaseSymptom]([ID], [DiseaseID], [SymptomID])
VALUES(44, 5, 6)
INSERT [Disease].[DiseaseSymptom]([ID], [DiseaseID], [SymptomID])
VALUES(45, 5, 26)
INSERT [Disease].[DiseaseSymptom]([ID], [DiseaseID], [SymptomID])
VALUES(46, 5, 12)
INSERT [Disease].[DiseaseSymptom]([ID], [DiseaseID], [SymptomID])
VALUES(47, 5, 36)
INSERT [Disease].[DiseaseSymptom]([ID], [DiseaseID], [SymptomID])
VALUES(48, 5, 37)
INSERT [Disease].[DiseaseSymptom]([ID], [DiseaseID], [SymptomID])
VALUES(49, 5, 24)
INSERT [Disease].[DiseaseSymptom]([ID], [DiseaseID], [SymptomID])
VALUES(50, 5, 13)
INSERT [Disease].[DiseaseSymptom]([ID], [DiseaseID], [SymptomID])
VALUES(51, 6, 13)
INSERT [Disease].[DiseaseSymptom]([ID], [DiseaseID], [SymptomID])
VALUES(52, 6, 14)
INSERT [Disease].[DiseaseSymptom]([ID], [DiseaseID], [SymptomID])
VALUES(53, 6, 21)
INSERT [Disease].[DiseaseSymptom]([ID], [DiseaseID], [SymptomID])
VALUES(54, 6, 37)
INSERT [Disease].[DiseaseSymptom]([ID], [DiseaseID], [SymptomID])
VALUES(55, 7, 2)
INSERT [Disease].[DiseaseSymptom]([ID], [DiseaseID], [SymptomID])
VALUES(56, 7, 33)
INSERT [Disease].[DiseaseSymptom]([ID], [DiseaseID], [SymptomID])
VALUES(57, 7, 20)
INSERT [Disease].[DiseaseSymptom]([ID], [DiseaseID], [SymptomID])
VALUES(58, 7, 17)
INSERT [Disease].[DiseaseSymptom]([ID], [DiseaseID], [SymptomID])
VALUES(59, 7, 26)
INSERT [Disease].[DiseaseSymptom]([ID], [DiseaseID], [SymptomID])
VALUES(60, 8, 8)
INSERT [Disease].[DiseaseSymptom]([ID], [DiseaseID], [SymptomID])
VALUES(61, 8, 3)
INSERT [Disease].[DiseaseSymptom]([ID], [DiseaseID], [SymptomID])
VALUES(62, 8, 37)
INSERT [Disease].[DiseaseSymptom]([ID], [DiseaseID], [SymptomID])
VALUES(63, 8, 4)
INSERT [Disease].[DiseaseSymptom]([ID], [DiseaseID], [SymptomID])
VALUES(64, 8, 25)
INSERT [Disease].[DiseaseSymptom]([ID], [DiseaseID], [SymptomID])
VALUES(65, 8, 9)
INSERT [Disease].[DiseaseSymptom]([ID], [DiseaseID], [SymptomID])
VALUES(66, 8, 13)
INSERT [Disease].[DiseaseSymptom]([ID], [DiseaseID], [SymptomID])
VALUES(67, 8, 27)
INSERT [Disease].[DiseaseSymptom]([ID], [DiseaseID], [SymptomID])
VALUES(68, 8, 6)
INSERT [Disease].[DiseaseSymptom]([ID], [DiseaseID], [SymptomID])
VALUES(69, 9, 2)
INSERT [Disease].[DiseaseSymptom]([ID], [DiseaseID], [SymptomID])
VALUES(70, 9, 9)
INSERT [Disease].[DiseaseSymptom]([ID], [DiseaseID], [SymptomID])
VALUES(71, 9, 15)
INSERT [Disease].[DiseaseSymptom]([ID], [DiseaseID], [SymptomID])
VALUES(72, 9, 10)
INSERT [Disease].[DiseaseSymptom]([ID], [DiseaseID], [SymptomID])
VALUES(73, 9, 5)
INSERT [Disease].[DiseaseSymptom]([ID], [DiseaseID], [SymptomID])
VALUES(74, 9, 13)
INSERT [Disease].[DiseaseSymptom]([ID], [DiseaseID], [SymptomID])
VALUES(75, 9, 30)
INSERT [Disease].[DiseaseSymptom]([ID], [DiseaseID], [SymptomID])
VALUES(76, 9, 6)
INSERT [Disease].[DiseaseSymptom]([ID], [DiseaseID], [SymptomID])
VALUES(77, 9, 23)
INSERT [Disease].[DiseaseSymptom]([ID], [DiseaseID], [SymptomID])
VALUES(78, 10, 2)
INSERT [Disease].[DiseaseSymptom]([ID], [DiseaseID], [SymptomID])
VALUES(79, 10, 27)
INSERT [Disease].[DiseaseSymptom]([ID], [DiseaseID], [SymptomID])
VALUES(80, 10, 9)
INSERT [Disease].[DiseaseSymptom]([ID], [DiseaseID], [SymptomID])
VALUES(81, 10, 35)
INSERT [Disease].[DiseaseSymptom]([ID], [DiseaseID], [SymptomID])
VALUES(82, 10, 28)
INSERT [Disease].[DiseaseSymptom]([ID], [DiseaseID], [SymptomID])
VALUES(83, 10, 20)
INSERT [Disease].[DiseaseSymptom]([ID], [DiseaseID], [SymptomID])
VALUES(84, 10, 7)
INSERT [Disease].[DiseaseSymptom]([ID], [DiseaseID], [SymptomID])
VALUES(85, 10, 26)
INSERT [Disease].[DiseaseSymptom]([ID], [DiseaseID], [SymptomID])
VALUES(86, 10, 32)
INSERT [Disease].[DiseaseSymptom]([ID], [DiseaseID], [SymptomID])
VALUES(87, 10, 30)
GO
INSERT [Disease].[Symptom]([Symptom_ID], [Name], [Description])
VALUES(1, N'Eating disorder', N'Physical')
INSERT [Disease].[Symptom]([Symptom_ID], [Name], [Description])
VALUES(2, N'Sleeping disorder', N'Physical')
INSERT [Disease].[Symptom]([Symptom_ID], [Name], [Description])
VALUES(3, N'Having fewer feelings', N'Mental')
INSERT [Disease].[Symptom]([Symptom_ID], [Name], [Description])
VALUES(4, N'Decrease libido', N'Physical')
INSERT [Disease].[Symptom]([Symptom_ID], [Name], [Description])
VALUES(5, N'Tension', N'Mental')
INSERT [Disease].[Symptom]([Symptom_ID], [Name], [Description])
VALUES(6, N'Irritability', N'Mental')
INSERT [Disease].[Symptom]([Symptom_ID], [Name], [Description])
VALUES(7, N'Guilty thoughts', N'Mental')
INSERT [Disease].[Symptom]([Symptom_ID], [Name], [Description])
VALUES(8, N'Delusion', N'Mental')
INSERT [Disease].[Symptom]([Symptom_ID], [Name], [Description])
VALUES(9, N'Attention losing', N'Mental')
INSERT [Disease].[Symptom]([Symptom_ID], [Name], [Description])
VALUES(10, N'Doubting the meaning of life', N'Mental')
INSERT [Disease].[Symptom]([Symptom_ID], [Name], [Description])
VALUES(11, N'Worrying', N'Mental')
INSERT [Disease].[Symptom]([Symptom_ID], [Name], [Description])
VALUES(12, N'Headaches', N'Physical')
INSERT [Disease].[Symptom]([Symptom_ID], [Name], [Description])
VALUES(13, N'Memory issue', N'Mental')
INSERT [Disease].[Symptom]([Symptom_ID], [Name], [Description])
VALUES(14, N'Indecisiveness', N'Mental')
INSERT [Disease].[Symptom]([Symptom_ID], [Name], [Description])
VALUES(15, N'Melancholy mood', N'Mental')
INSERT [Disease].[Symptom]([Symptom_ID], [Name], [Description])
VALUES(16, N'Self-deprecation', N'Mental')
INSERT [Disease].[Symptom]([Symptom_ID], [Name], [Description])
VALUES(17, N'Heart palpitations', N'Physical')
INSERT [Disease].[Symptom]([Symptom_ID], [Name], [Description])
VALUES(18, N'Experiencing gastrointestinal (GI) problems', N'Physical')
INSERT [Disease].[Symptom]([Symptom_ID], [Name], [Description])
VALUES(19, N'Weight loss', N'Physical')
INSERT [Disease].[Symptom]([Symptom_ID], [Name], [Description])
VALUES(20, N'Decrease appetite', N'Physical')
INSERT [Disease].[Symptom]([Symptom_ID], [Name], [Description])
VALUES(21, N'Quick mood shifts', N'Mental')
INSERT [Disease].[Symptom]([Symptom_ID], [Name], [Description])
VALUES(22, N'Identity disorder', N'Mental')
INSERT [Disease].[Symptom]([Symptom_ID], [Name], [Description])
VALUES(23, N'Self-harm thoughts', N'Mental')
INSERT [Disease].[Symptom]([Symptom_ID], [Name], [Description])
VALUES(24, N'Feeling nauseous', N'Physical')
INSERT [Disease].[Symptom]([Symptom_ID], [Name], [Description])
VALUES(25, N'Social isolation', N'Mental')
INSERT [Disease].[Symptom]([Symptom_ID], [Name], [Description])
VALUES(26, N'Fatigue', N'Physical')
INSERT [Disease].[Symptom]([Symptom_ID], [Name], [Description])
VALUES(27, N'Thoughts of death/suicide', N'Mental')
INSERT [Disease].[Symptom]([Symptom_ID], [Name], [Description])
VALUES(28, N'Slow movement and speechs', N'Physical')
INSERT [Disease].[Symptom]([Symptom_ID], [Name], [Description])
VALUES(29, N'Increase purposeless physical activity', N'Physical')
INSERT [Disease].[Symptom]([Symptom_ID], [Name], [Description])
VALUES(30, N'Loss of interest', N'Mental')
INSERT [Disease].[Symptom]([Symptom_ID], [Name], [Description])
VALUES(31, N'Weight Gain', N'Physical')
INSERT [Disease].[Symptom]([Symptom_ID], [Name], [Description])
VALUES(32, N'Feeling worthless', N'Mental')
INSERT [Disease].[Symptom]([Symptom_ID], [Name], [Description])
VALUES(33, N'Pain and illness', N'Physical')
INSERT [Disease].[Symptom]([Symptom_ID], [Name], [Description])
VALUES(34, N'Breathing rapidly', N'Physical')
INSERT [Disease].[Symptom]([Symptom_ID], [Name], [Description])
VALUES(35, N'Feeling restless', N'Mental')
INSERT [Disease].[Symptom]([Symptom_ID], [Name], [Description])
VALUES(36, N'Muscle ache', N'Physical')
INSERT [Disease].[Symptom]([Symptom_ID], [Name], [Description])
VALUES(37, N'Language disorder', N'Mental')
GO
INSERT [Solution].[CureOneByOne]([ID], [SolutionID], [SymptomID])
VALUES(1, 58, 1)
INSERT [Solution].[CureOneByOne]([ID], [SolutionID], [SymptomID])
VALUES(2, 1, 2)
INSERT [Solution].[CureOneByOne]([ID], [SolutionID], [SymptomID])
VALUES(3, 3, 3)
INSERT [Solution].[CureOneByOne]([ID], [SolutionID], [SymptomID])
VALUES(4, 59, 4)
INSERT [Solution].[CureOneByOne]([ID], [SolutionID], [SymptomID])
VALUES(5, 56, 5)
INSERT [Solution].[CureOneByOne]([ID], [SolutionID], [SymptomID])
VALUES(6, 53, 6)
INSERT [Solution].[CureOneByOne]([ID], [SolutionID], [SymptomID])
VALUES(7, 54, 7)
INSERT [Solution].[CureOneByOne]([ID], [SolutionID], [SymptomID])
VALUES(8, 29, 8)
INSERT [Solution].[CureOneByOne]([ID], [SolutionID], [SymptomID])
VALUES(9, 4, 9)
INSERT [Solution].[CureOneByOne]([ID], [SolutionID], [SymptomID])
VALUES(10, 47, 10)
INSERT [Solution].[CureOneByOne]([ID], [SolutionID], [SymptomID])
VALUES(11, 10, 11)
INSERT [Solution].[CureOneByOne]([ID], [SolutionID], [SymptomID])
VALUES(12, 50, 12)
INSERT [Solution].[CureOneByOne]([ID], [SolutionID], [SymptomID])
VALUES(13, NULL, 13)
INSERT [Solution].[CureOneByOne]([ID], [SolutionID], [SymptomID])
VALUES(14, NULL, 14)
INSERT [Solution].[CureOneByOne]([ID], [SolutionID], [SymptomID])
VALUES(15, 26, 15)
INSERT [Solution].[CureOneByOne]([ID], [SolutionID], [SymptomID])
VALUES(16, 37, 16)
INSERT [Solution].[CureOneByOne]([ID], [SolutionID], [SymptomID])
VALUES(17, 30, 17)
INSERT [Solution].[CureOneByOne]([ID], [SolutionID], [SymptomID])
VALUES(18, NULL, 18)
INSERT [Solution].[CureOneByOne]([ID], [SolutionID], [SymptomID])
VALUES(19, NULL, 19)
INSERT [Solution].[CureOneByOne]([ID], [SolutionID], [SymptomID])
VALUES(20, NULL, 20)
INSERT [Solution].[CureOneByOne]([ID], [SolutionID], [SymptomID])
VALUES(21, NULL, 21)
INSERT [Solution].[CureOneByOne]([ID], [SolutionID], [SymptomID])
VALUES(22, NULL, 22)
INSERT [Solution].[CureOneByOne]([ID], [SolutionID], [SymptomID])
VALUES(23, 2, 23)
INSERT [Solution].[CureOneByOne]([ID], [SolutionID], [SymptomID])
VALUES(24, NULL, 24)
INSERT [Solution].[CureOneByOne]([ID], [SolutionID], [SymptomID])
VALUES(25, 17, 25)
INSERT [Solution].[CureOneByOne]([ID], [SolutionID], [SymptomID])
VALUES(26, NULL, 26)
INSERT [Solution].[CureOneByOne]([ID], [SolutionID], [SymptomID])
VALUES(27, 15, 27)
INSERT [Solution].[CureOneByOne]([ID], [SolutionID], [SymptomID])
VALUES(28, NULL, 28)
INSERT [Solution].[CureOneByOne]([ID], [SolutionID], [SymptomID])
VALUES(29, NULL, 29)
INSERT [Solution].[CureOneByOne]([ID], [SolutionID], [SymptomID])
VALUES(30, 17, 30)
INSERT [Solution].[CureOneByOne]([ID], [SolutionID], [SymptomID])
VALUES(31, NULL, 31)
INSERT [Solution].[CureOneByOne]([ID], [SolutionID], [SymptomID])
VALUES(32, 42, 32)
INSERT [Solution].[CureOneByOne]([ID], [SolutionID], [SymptomID])
VALUES(33, NULL, 33)
INSERT [Solution].[CureOneByOne]([ID], [SolutionID], [SymptomID])
VALUES(34, 55, 34)
INSERT [Solution].[CureOneByOne]([ID], [SolutionID], [SymptomID])
VALUES(35, 12, 35)
INSERT [Solution].[CureOneByOne]([ID], [SolutionID], [SymptomID])
VALUES(36, NULL, 36)
INSERT [Solution].[CureOneByOne]([ID], [SolutionID], [SymptomID])
VALUES(37, NULL, 37)
INSERT [Solution].[CureOneByOne]([ID], [SolutionID], [SymptomID])
VALUES(38, NULL, 1)
GO
INSERT [Solution].[Recommendation]([ID], [SolutionID], [ResultID])
VALUES(1, 30, 1)
INSERT [Solution].[Recommendation]([ID], [SolutionID], [ResultID])
VALUES(2, 18, 1)
INSERT [Solution].[Recommendation]([ID], [SolutionID], [ResultID])
VALUES(3, 13, 1)
INSERT [Solution].[Recommendation]([ID], [SolutionID], [ResultID])
VALUES(4, 53, 2)
INSERT [Solution].[Recommendation]([ID], [SolutionID], [ResultID])
VALUES(5, 11, 2)
INSERT [Solution].[Recommendation]([ID], [SolutionID], [ResultID])
VALUES(6, 49, 2)
INSERT [Solution].[Recommendation]([ID], [SolutionID], [ResultID])
VALUES(7, 42, 2)
INSERT [Solution].[Recommendation]([ID], [SolutionID], [ResultID])
VALUES(8, 1, 2)
INSERT [Solution].[Recommendation]([ID], [SolutionID], [ResultID])
VALUES(9, 11, 2)
INSERT [Solution].[Recommendation]([ID], [SolutionID], [ResultID])
VALUES(10, 47, 3)
INSERT [Solution].[Recommendation]([ID], [SolutionID], [ResultID])
VALUES(11, 43, 3)
INSERT [Solution].[Recommendation]([ID], [SolutionID], [ResultID])
VALUES(12, 26, 3)
INSERT [Solution].[Recommendation]([ID], [SolutionID], [ResultID])
VALUES(13, 38, 4)
INSERT [Solution].[Recommendation]([ID], [SolutionID], [ResultID])
VALUES(14, 8, 4)
INSERT [Solution].[Recommendation]([ID], [SolutionID], [ResultID])
VALUES(15, 6, 4)
INSERT [Solution].[Recommendation]([ID], [SolutionID], [ResultID])
VALUES(16, 7, 4)
INSERT [Solution].[Recommendation]([ID], [SolutionID], [ResultID])
VALUES(17, 30, 4)
INSERT [Solution].[Recommendation]([ID], [SolutionID], [ResultID])
VALUES(18, 27, 4)
INSERT [Solution].[Recommendation]([ID], [SolutionID], [ResultID])
VALUES(19, 21, 5)
INSERT [Solution].[Recommendation]([ID], [SolutionID], [ResultID])
VALUES(20, 23, 5)
INSERT [Solution].[Recommendation]([ID], [SolutionID], [ResultID])
VALUES(21, 10, 5)
INSERT [Solution].[Recommendation]([ID], [SolutionID], [ResultID])
VALUES(22, 34, 6)
INSERT [Solution].[Recommendation]([ID], [SolutionID], [ResultID])
VALUES(23, 13, 6)
INSERT [Solution].[Recommendation]([ID], [SolutionID], [ResultID])
VALUES(24, 52, 6)
INSERT [Solution].[Recommendation]([ID], [SolutionID], [ResultID])
VALUES(25, 1, 6)
INSERT [Solution].[Recommendation]([ID], [SolutionID], [ResultID])
VALUES(26, 45, 6)
INSERT [Solution].[Recommendation]([ID], [SolutionID], [ResultID])
VALUES(27, 22, 6)
INSERT [Solution].[Recommendation]([ID], [SolutionID], [ResultID])
VALUES(28, 51, 7)
INSERT [Solution].[Recommendation]([ID], [SolutionID], [ResultID])
VALUES(29, 43, 7)
INSERT [Solution].[Recommendation]([ID], [SolutionID], [ResultID])
VALUES(30, 3, 7)
INSERT [Solution].[Recommendation]([ID], [SolutionID], [ResultID])
VALUES(31, 14, 8)
INSERT [Solution].[Recommendation]([ID], [SolutionID], [ResultID])
VALUES(32, 25, 8)
INSERT [Solution].[Recommendation]([ID], [SolutionID], [ResultID])
VALUES(33, 9, 8)
INSERT [Solution].[Recommendation]([ID], [SolutionID], [ResultID])
VALUES(34, 39, 8)
INSERT [Solution].[Recommendation]([ID], [SolutionID], [ResultID])
VALUES(35, 47, 8)
INSERT [Solution].[Recommendation]([ID], [SolutionID], [ResultID])
VALUES(36, 25, 15)
INSERT [Solution].[Recommendation]([ID], [SolutionID], [ResultID])
VALUES(37, 38, 15)
INSERT [Solution].[Recommendation]([ID], [SolutionID], [ResultID])
VALUES(38, 16, 16)
INSERT [Solution].[Recommendation]([ID], [SolutionID], [ResultID])
VALUES(39, 32, 16)
INSERT [Solution].[Recommendation]([ID], [SolutionID], [ResultID])
VALUES(40, 45, 16)
INSERT [Solution].[Recommendation]([ID], [SolutionID], [ResultID])
VALUES(41, 15, 16)
INSERT [Solution].[Recommendation]([ID], [SolutionID], [ResultID])
VALUES(42, 42, 17)
INSERT [Solution].[Recommendation]([ID], [SolutionID], [ResultID])
VALUES(43, 58, 17)
INSERT [Solution].[Recommendation]([ID], [SolutionID], [ResultID])
VALUES(44, 16, 17)
INSERT [Solution].[Recommendation]([ID], [SolutionID], [ResultID])
VALUES(45, 56, 18)
INSERT [Solution].[Recommendation]([ID], [SolutionID], [ResultID])
VALUES(46, 32, 18)
INSERT [Solution].[Recommendation]([ID], [SolutionID], [ResultID])
VALUES(47, 10, 18)
INSERT [Solution].[Recommendation]([ID], [SolutionID], [ResultID])
VALUES(48, 48, 18)
INSERT [Solution].[Recommendation]([ID], [SolutionID], [ResultID])
VALUES(49, 24, 18)
INSERT [Solution].[Recommendation]([ID], [SolutionID], [ResultID])
VALUES(50, 19, 18)
INSERT [Solution].[Recommendation]([ID], [SolutionID], [ResultID])
VALUES(51, 18, 19)
INSERT [Solution].[Recommendation]([ID], [SolutionID], [ResultID])
VALUES(52, 11, 19)
INSERT [Solution].[Recommendation]([ID], [SolutionID], [ResultID])
VALUES(53, 21, 19)
INSERT [Solution].[Recommendation]([ID], [SolutionID], [ResultID])
VALUES(54, 55, 20)
INSERT [Solution].[Recommendation]([ID], [SolutionID], [ResultID])
VALUES(55, 25, 20)
INSERT [Solution].[Recommendation]([ID], [SolutionID], [ResultID])
VALUES(56, 16, 20)
INSERT [Solution].[Recommendation]([ID], [SolutionID], [ResultID])
VALUES(57, 54, 20)
INSERT [Solution].[Recommendation]([ID], [SolutionID], [ResultID])
VALUES(58, 20, 20)
GO
INSERT [Solution].[Solution]([Solution_ID], [Name], [Type], [Benefit], [Platform], [Description])
VALUES(1, N'Sheep in Dream', N'Game', N'Sleep', N'Mobile App', N'Sheep in Dream is a game about getting a boy to sleep by making sheep jump over a log. The sheep are in the top left corner of the screen on a dream hill and the boy is in his bed at the bottom. ')
INSERT [Solution].[Solution]([Solution_ID], [Name], [Type], [Benefit], [Platform], [Description])
VALUES(2, N'Love is in small things', N'Game', N'Relationship', N',Mobile App', N'Love is…in small things is one of the romantic games made by Lunosoft. The game showcases the lively artwork of a South Korean artist who goes by the name Puuung and is a romantic title')
INSERT [Solution].[Solution]([Solution_ID], [Name], [Type], [Benefit], [Platform], [Description])
VALUES(3, N'My Oasis: Relaxing, Satisfying', N'Game', N'Communicate-Calm', N'Mobile App', N'My Oasis: Anxiety Relief Game is a relaxing game that helps in calming your mind, relieve your stress. This anti anxiety game helps in relaxation and calm sleeping.')
INSERT [Solution].[Solution]([Solution_ID], [Name], [Type], [Benefit], [Platform], [Description])
VALUES(4, N'Focus Plant: Pomodoro timer', N'App', N'Focus', N'Mobile App', N'Focus is a gamified focus study timer app to help people better manage their time and focus on the important things in their life. Some times self-discipline is just not enough to keep us away from the phones. This is when gamification comes in, the in-game achievements encourage users to stick with the app, better manage their time and get things done.')
INSERT [Solution].[Solution]([Solution_ID], [Name], [Type], [Benefit], [Platform], [Description])
VALUES(5, N'Wokamon - Walking app game', N'Game', N'Exercise', N'Mobile App', N'Wokamons are running out of resources and they need your help! Every step you take is turned into energy. Use it to feed, grow and collect Wokamons. The more you walk, the more Wokamons you can collect and the further you get to explore the magical Woka-worlds like candy desert, icy realm, mystical forest and more! Lend a helping hand and soon you will find, the more you help, the fitter you get!')
INSERT [Solution].[Solution]([Solution_ID], [Name], [Type], [Benefit], [Platform], [Description])
VALUES(6, N'Cats & Soup', N'Game', N'Relax', N'Mobile App', N'Looking for a cute cat game? Here is a peaceful animal forest where cats boil their delicious soup! An idle relaxing cat game perfect for cat moms and dads')
INSERT [Solution].[Solution]([Solution_ID], [Name], [Type], [Benefit], [Platform], [Description])
VALUES(7, N'Magic Fluids Lite', N'Game', N'Relax-Calm', N'Mobile App', N'Touch and simulate this magical fluid substance to get calm, trippy and creative! Gorgeous visual effects give a out of this world feel. Now with more presets and music!')
INSERT [Solution].[Solution]([Solution_ID], [Name], [Type], [Benefit], [Platform], [Description])
VALUES(8, N'Antistress - Relaxing games', N'Game', N'Relax-Calm', N'Mobile App', N'When you need relaxation, diversion or just a moment of distraction enjoy this collection of toys: hear the sound of a bamboo chime, play with wooden boxes, swipe gently your finger in the water, tap buttons, draw with chalks and so on!')
INSERT [Solution].[Solution]([Solution_ID], [Name], [Type], [Benefit], [Platform], [Description])
VALUES(9, N'Heaven Travel', N'Game', N'Relax', N'Mobile App', N'Lofn, a little girl who wakes up on an ethereal grassland, sets out on an adventure in order to save the Memory Tree. While she collects different memory pieces, she also discovers the surprising truth of her life. Other than unexpected plot twists, the story is presented in a unique way which we hope will stimulate the reader to look at life from a different perspective.')
INSERT [Solution].[Solution]([Solution_ID], [Name], [Type], [Benefit], [Platform], [Description])
VALUES(10, N'Worrydolls', N'Game', N'Relieve anxiety', N'Mobile App', N'Worrydolls gives you a small doll who is ready to listen to what is worrying you. Tell your worry to the doll, then track it over time. You can use Worrydolls like a journal to help you overcome anxiety and stress.')
INSERT [Solution].[Solution]([Solution_ID], [Name], [Type], [Benefit], [Platform], [Description])
VALUES(11, N'Happy Color', N'Game', N'Focus', N'Mobile App', N'Discover Happy Color®: a calming paint by numbers game. Relax by painting digital art, with exclusive nature, fashion, Disney, Marvel art templates and much more - whether you’re looking for a fun, creative moment or for anxiety relief, Happy Color® is the coloring app you need.')
INSERT [Solution].[Solution]([Solution_ID], [Name], [Type], [Benefit], [Platform], [Description])
VALUES(12, N'Virtual bubble wrap', N'Game', N'Relax', N'Online', N'https://bubblespop.netlify.app')
INSERT [Solution].[Solution]([Solution_ID], [Name], [Type], [Benefit], [Platform], [Description])
VALUES(13, N'Mandala Maker', N'Game', N'Creative', N'Online', N'https://mandalamaker.online')
INSERT [Solution].[Solution]([Solution_ID], [Name], [Type], [Benefit], [Platform], [Description])
VALUES(14, N'Michael O''Mara Books', N'Game', N'Relax', N'Online', N'https://www.mombooks.com/mom/online-activities')
INSERT [Solution].[Solution]([Solution_ID], [Name], [Type], [Benefit], [Platform], [Description])
VALUES(15, N'Sym', N'Game', N'Survive', N'Online', N'"Sym is a puzzle-platformer that explores social anxiety disorder. Play as Josh, a teenage boy trying to reconcile a maze of two contrasting worlds that coexist within the blank spaces of each other - his perception of reality, and the world he created to avoid his fears. https://store.steampowered.com/app/342100/Sym/"')
INSERT [Solution].[Solution]([Solution_ID], [Name], [Type], [Benefit], [Platform], [Description])
VALUES(16, N'@longtrust', N'Video', N'Discovery', N'Tiktok', N'Khám phá phim theo t?ng th? lo?i trên n?n t?ng Netflix.')
INSERT [Solution].[Solution]([Solution_ID], [Name], [Type], [Benefit], [Platform], [Description])
VALUES(17, N'@gains2gains', N'Video', N'Motivation', N'Tiktok', N'Cung c?p các l?i khuyên h?u ích v?c d?y ý chí b?n thân.')
INSERT [Solution].[Solution]([Solution_ID], [Name], [Type], [Benefit], [Platform], [Description])
VALUES(18, N'@mindful_tom', N'Video', N'Motivation', N'Tiktok', N'Clip tu v?n và cung c?p các tips t? th?c t?nh và ch?a lành các can b?nh tâm lý.')
INSERT [Solution].[Solution]([Solution_ID], [Name], [Type], [Benefit], [Platform], [Description])
VALUES(19, N'@bethanyylouisaa', N'Video', N'Healing', N'Tiktok', N'Các clip v? phong c?nh d?p, tho m?ng cùng nh?c nh?. Các clip t?m 15 giây.')
INSERT [Solution].[Solution]([Solution_ID], [Name], [Type], [Benefit], [Platform], [Description])
VALUES(20, N'@waterandlight', N'Video', N'Healing', N'Tiktok', N'Các clip v? sóng bi?n d?p. Các clip t?m 10 giây.')
INSERT [Solution].[Solution]([Solution_ID], [Name], [Type], [Benefit], [Platform], [Description])
VALUES(21, N'@chocoandpancake', N'Video', N'Healing', N'Tiktok', N'Các câu chuy?n v? c?p tình nhân là choco và pancake. Các clip ng?n thu?ng m?y ch?c giây. (Tình c?m, th?u hi?u, d? thuong)')
INSERT [Solution].[Solution]([Solution_ID], [Name], [Type], [Benefit], [Platform], [Description])
VALUES(22, N'@dinosaurcouchcomics', N'Video', N'Healing', N'Tiktok', N'Có 2 lo?i clip khác nhau. M?t là nh?ng câu chuy?n c?a các chú kh?ng long v?i nhau. Hai là nh?ng câu chuy?n c?a chú chó và c?u ch?. Các video clip tuong d?i ng?n t?m m?y ch?c giây. (nhân van, d? thuong, ng? ng?n, th?u hi?u)')
INSERT [Solution].[Solution]([Solution_ID], [Name], [Type], [Benefit], [Platform], [Description])
VALUES(23, N'@lovely.words', N'Video', N'Healing', N',Tiktok', N'About lovely words in a lot of books')
INSERT [Solution].[Solution]([Solution_ID], [Name], [Type], [Benefit], [Platform], [Description])
VALUES(24, N'@the_simulator', N'Video', N'Discovery', N'Tiktok', N'Math')
INSERT [Solution].[Solution]([Solution_ID], [Name], [Type], [Benefit], [Platform], [Description])
VALUES(25, N'@planet_scape', N'Video', N'Discovery', N'Tiktok', N'Landscapes')
INSERT [Solution].[Solution]([Solution_ID], [Name], [Type], [Benefit], [Platform], [Description])
VALUES(26, N'Sky: Children of the Light', N'Game', N'Relationship', N'Mobile App', N'Experience the best of humanity in a place where you can meaningfully connect with others. In Sky, you can soar above the clouds, play instruments, or just relax and enjoy the beauty around you. There is no pressure. Just allow the experience to open up at your own pace. All are welcome, especially you!')
INSERT [Solution].[Solution]([Solution_ID], [Name], [Type], [Benefit], [Platform], [Description])
VALUES(27, N'How to meditate', N'Meditation', N'Soft Exercise', N'Website', N'https://www.mindful.org/how-to-meditate/')
INSERT [Solution].[Solution]([Solution_ID], [Name], [Type], [Benefit], [Platform], [Description])
VALUES(28, N'Top 10 guided meditation', N'Meditation', N'Soft Exercise', N'Website', N'https://www.mindful.org/the-top-10-guided-meditations-of-2022/')
INSERT [Solution].[Solution]([Solution_ID], [Name], [Type], [Benefit], [Platform], [Description])
VALUES(29, N'List of meditation', N'Meditation', N'Soft Exercise', N'Youtube', N'https://www.youtube.com/watch?v=O-6f5wQXSu8&list=PLQiGxGHwiuD1kdxsWKFuhE0rITIXe-7yC')
INSERT [Solution].[Solution]([Solution_ID], [Name], [Type], [Benefit], [Platform], [Description])
VALUES(30, N'List of meditation 2', N'Meditation', N'Soft Exercise', N'Youtube', N'https://www.youtube.com/watch?v=HRuqe26F8SU&list=PLQiGxGHwiuD1OIV-vWbhZqy6iSkazC_Jd')
INSERT [Solution].[Solution]([Solution_ID], [Name], [Type], [Benefit], [Platform], [Description])
VALUES(31, N'Yoga''s tips', N'Yoga', N'Soft Exercise', N'Website', N'https://www.yogabasics.com/practice/yoga-for-beginners/yoga-tips-for-beginners/')
INSERT [Solution].[Solution]([Solution_ID], [Name], [Type], [Benefit], [Platform], [Description])
VALUES(32, N'SarahBethYoga', N'Yoga', N'Soft Exercise', N'Youtube', N'https://www.youtube.com/@sarahbethyoga/videos')
INSERT [Solution].[Solution]([Solution_ID], [Name], [Type], [Benefit], [Platform], [Description])
VALUES(33, N'YogaWithAdriene', N'Yoga', N'Soft Exercise', N'Youtube', N'https://www.youtube.com/@yogawithadriene/playlists')
INSERT [Solution].[Solution]([Solution_ID], [Name], [Type], [Benefit], [Platform], [Description])
VALUES(34, N'Yograja', N'Yoga', N'Soft Exercise', N'Youtube', N'https://www.youtube.com/@Yograja/videos')
INSERT [Solution].[Solution]([Solution_ID], [Name], [Type], [Benefit], [Platform], [Description])
VALUES(35, N'JessicaRichburg', N'Yoga', N'Soft Exercise', N'Youtube', N'https://www.youtube.com/@JessicaRichburg/videos')
INSERT [Solution].[Solution]([Solution_ID], [Name], [Type], [Benefit], [Platform], [Description])
VALUES(36, N'The Skin Deep', N'Video', N'Understanding', N'Youtube', N'https://www.youtube.com/@TheSkinDeep')
INSERT [Solution].[Solution]([Solution_ID], [Name], [Type], [Benefit], [Platform], [Description])
VALUES(37, N'@happinessproject', N'Picture', N'Motivation', N'Tiktok', N'Các câu chuy?n k? b?ng các ?nh t?m 7, 8 ?nh v? các thông di?p d?ng viên, th?u hi?u, thông c?m, tips d? ch?ng l?i s? tr?m c?m.')
INSERT [Solution].[Solution]([Solution_ID], [Name], [Type], [Benefit], [Platform], [Description])
VALUES(38, N'Rise: Energy & Sleep Tracker', N'App', N'Sleep', N'Mobile App', NULL)
INSERT [Solution].[Solution]([Solution_ID], [Name], [Type], [Benefit], [Platform], [Description])
VALUES(39, N'Loóna: calm, relax and sleep', N'App', N'Sleep', N'Mobile App', NULL)
INSERT [Solution].[Solution]([Solution_ID], [Name], [Type], [Benefit], [Platform], [Description])
VALUES(40, N'Calm', N'App', N'Exercise', N'Mobile App', NULL)
INSERT [Solution].[Solution]([Solution_ID], [Name], [Type], [Benefit], [Platform], [Description])
VALUES(41, N'Fabulous: Daily Habit Tracker', N'App', N'Motivation', N'Mobile App', NULL)
INSERT [Solution].[Solution]([Solution_ID], [Name], [Type], [Benefit], [Platform], [Description])
VALUES(42, N'Tangerine: Self-care & Goals', N'App', N'Motivation', N'Mobile App', NULL)
INSERT [Solution].[Solution]([Solution_ID], [Name], [Type], [Benefit], [Platform], [Description])
VALUES(43, N'Headspace: Mindful Meditation', N'App', N'Soft Exercise', N'Mobile App', NULL)
INSERT [Solution].[Solution]([Solution_ID], [Name], [Type], [Benefit], [Platform], [Description])
VALUES(44, N'Balance: Meditation & Sleep', N'App', N'Soft Exercise', N'Mobile App', NULL)
INSERT [Solution].[Solution]([Solution_ID], [Name], [Type], [Benefit], [Platform], [Description])
VALUES(45, N'BetterSleep: Relax and Sleep', N'App', N'Sleep', N'Mobile App', NULL)
INSERT [Solution].[Solution]([Solution_ID], [Name], [Type], [Benefit], [Platform], [Description])
VALUES(46, N'Endel: Focus, Sleep, Relax', N'App', N'Focus', N'Mobile App', NULL)
INSERT [Solution].[Solution]([Solution_ID], [Name], [Type], [Benefit], [Platform], [Description])
VALUES(47, N'Sanvello: Anxiety & Depression', N'App', N'Coaching', N'Mobile App', NULL)
INSERT [Solution].[Solution]([Solution_ID], [Name], [Type], [Benefit], [Platform], [Description])
VALUES(48, N'Moodi', N'Natural sound', N'Calm-relax', N'Website', N'https://www.moodil.com')
INSERT [Solution].[Solution]([Solution_ID], [Name], [Type], [Benefit], [Platform], [Description])
VALUES(49, N'A Soft Murmur', N'Natural sound', N'Focus', N'Website', N'https://asoftmurmur.com')
INSERT [Solution].[Solution]([Solution_ID], [Name], [Type], [Benefit], [Platform], [Description])
VALUES(50, N'My Noise', N'Sound', N'Relax-calm-focus', N'Website', N'https://mynoise.net')
INSERT [Solution].[Solution]([Solution_ID], [Name], [Type], [Benefit], [Platform], [Description])
VALUES(51, N'Calm Sound', N'Natural sound', N'Sleep-relax', N'Website', N'https://www.calmsound.com')
INSERT [Solution].[Solution]([Solution_ID], [Name], [Type], [Benefit], [Platform], [Description])
VALUES(52, N'WildAmbience', N'Natural sound', N'Relax', N'Website', N'https://wildambience.com')
INSERT [Solution].[Solution]([Solution_ID], [Name], [Type], [Benefit], [Platform], [Description])
VALUES(53, N'Exhaler', N'Breathe', N'Calm', N'Website', N'https://xhalr.com')
INSERT [Solution].[Solution]([Solution_ID], [Name], [Type], [Benefit], [Platform], [Description])
VALUES(54, N'Pixel Thoughts', N'Interact', N'Relieve Stress', N'Website', N'http://www.pixelthoughts.co/')
INSERT [Solution].[Solution]([Solution_ID], [Name], [Type], [Benefit], [Platform], [Description])
VALUES(55, N'Connected Breath', N'Breathe', N'Calm', N'Website', N'http://www.connectedbreath.co')
INSERT [Solution].[Solution]([Solution_ID], [Name], [Type], [Benefit], [Platform], [Description])
VALUES(56, N'Do Nothing', N'Nothing', N'Calm', N'Website', N'http://www.donothingfor2minutes.com')
INSERT [Solution].[Solution]([Solution_ID], [Name], [Type], [Benefit], [Platform], [Description])
VALUES(57, N'Allolo', N'Natural sound', N'Relax', N'Website', N'http://www.allolo.ru/?id=14')
INSERT [Solution].[Solution]([Solution_ID], [Name], [Type], [Benefit], [Platform], [Description])
VALUES(58, N'RR: Eating Disorder Management', N'App', N'Avoid Eating Disorder', N'Mobile App', N'Recovery Record is the smart companion for managing your journey to recovery from eating disorders including anorexia nervosa, bulimia nervosa, binge eating disorder and ARFID. This app is also intended for people with general eating, weight and shape concerns.')
INSERT [Solution].[Solution]([Solution_ID], [Name], [Type], [Benefit], [Platform], [Description])
VALUES(59, N'Rosy - Women''s Sexual Health', N'App', N'Libido Issues', N'Mobile App', N'The Sexual Wellness Solutions We All Deserve. Developed by doctors and psychologists, Rosy is the first-of-its-kind platform for the 43% of us who experience sexual concerns. Our app meets women right where they are so they can take their sexual health into your own hands.')
GO
INSERT [Test].[Answer]([Answer_ID], [Title], [Weight])
VALUES(1, N'Not at all', 0)
INSERT [Test].[Answer]([Answer_ID], [Title], [Weight])
VALUES(2, N'Several days', 1)
INSERT [Test].[Answer]([Answer_ID], [Title], [Weight])
VALUES(3, N'More than half the days', 2)
INSERT [Test].[Answer]([Answer_ID], [Title], [Weight])
VALUES(4, N'Nearly every day', 3)
INSERT [Test].[Answer]([Answer_ID], [Title], [Weight])
VALUES(5, N'Never', 0)
INSERT [Test].[Answer]([Answer_ID], [Title], [Weight])
VALUES(6, N'Almost never', 1)
INSERT [Test].[Answer]([Answer_ID], [Title], [Weight])
VALUES(7, N'Sometimes', 2)
INSERT [Test].[Answer]([Answer_ID], [Title], [Weight])
VALUES(8, N'Fairly Often', 3)
INSERT [Test].[Answer]([Answer_ID], [Title], [Weight])
VALUES(9, N'Very Often', 4)
INSERT [Test].[Answer]([Answer_ID], [Title], [Weight])
VALUES(10, N'Always', 4)
INSERT [Test].[Answer]([Answer_ID], [Title], [Weight])
VALUES(11, N'True', 1)
INSERT [Test].[Answer]([Answer_ID], [Title], [Weight])
VALUES(12, N'FALSE', 0)
INSERT [Test].[Answer]([Answer_ID], [Title], [Weight])
VALUES(13, N'Yes', 1)
INSERT [Test].[Answer]([Answer_ID], [Title], [Weight])VALUES(14, N'No', 0)
GO
INSERT [Test].[AnswerSet]([AnswerSet_ID], [No_Answer])VALUES(1, 4)
INSERT [Test].[AnswerSet]([AnswerSet_ID], [No_Answer])VALUES(2, 5)
INSERT [Test].[AnswerSet]([AnswerSet_ID], [No_Answer])VALUES(3, 2)
INSERT [Test].[AnswerSet]([AnswerSet_ID], [No_Answer])VALUES(4, 3)
INSERT [Test].[AnswerSet]([AnswerSet_ID], [No_Answer])VALUES(5, 2)
GO
INSERT [Test].[AnswerSetContent]([ID], [AnswerSetID], [AnswerID])
VALUES(1, 1, 1)
INSERT [Test].[AnswerSetContent]([ID], [AnswerSetID], [AnswerID])
VALUES(2, 1, 2)
INSERT [Test].[AnswerSetContent]([ID], [AnswerSetID], [AnswerID])
VALUES(3, 1, 3)
INSERT [Test].[AnswerSetContent]([ID], [AnswerSetID], [AnswerID])
VALUES(4, 1, 4)
INSERT [Test].[AnswerSetContent]([ID], [AnswerSetID], [AnswerID])
VALUES(5, 2, 5)
INSERT [Test].[AnswerSetContent]([ID], [AnswerSetID], [AnswerID])
VALUES(6, 2, 6)
INSERT [Test].[AnswerSetContent]([ID], [AnswerSetID], [AnswerID])
VALUES(7, 2, 7)
INSERT [Test].[AnswerSetContent]([ID], [AnswerSetID], [AnswerID])
VALUES(8, 2, 8)
INSERT [Test].[AnswerSetContent]([ID], [AnswerSetID], [AnswerID])
VALUES(9, 2, 9)
INSERT [Test].[AnswerSetContent]([ID], [AnswerSetID], [AnswerID])
VALUES(10, 3, 11)
INSERT [Test].[AnswerSetContent]([ID], [AnswerSetID], [AnswerID])
VALUES(11, 3, 12)
INSERT [Test].[AnswerSetContent]([ID], [AnswerSetID], [AnswerID])
VALUES(12, 4, 5)
INSERT [Test].[AnswerSetContent]([ID], [AnswerSetID], [AnswerID])
VALUES(13, 4, 7)
INSERT [Test].[AnswerSetContent]([ID], [AnswerSetID], [AnswerID])
VALUES(14, 4, 10)
INSERT [Test].[AnswerSetContent]([ID], [AnswerSetID], [AnswerID])
VALUES(15, 5, 13)
INSERT [Test].[AnswerSetContent]([ID], [AnswerSetID], [AnswerID])
VALUES(16, 5, 14)
GO
INSERT [Test].[Question]([Question_ID], [TestID], [Title], [AnswerSetID])
VALUES(1, 1, N'Little interest or pleasure in doing things?', 1)
INSERT [Test].[Question]([Question_ID], [TestID], [Title], [AnswerSetID])
VALUES(2, 1, N'Feeling down, depressed, or hopeless?', 1)
INSERT [Test].[Question]([Question_ID], [TestID], [Title], [AnswerSetID])
VALUES(3, 1, N'Trouble falling or staying asleep, or sleeping too much?', 1)
INSERT [Test].[Question]([Question_ID], [TestID], [Title], [AnswerSetID])
VALUES(4, 1, N'Feeling tired or having little energy?', 1)
INSERT [Test].[Question]([Question_ID], [TestID], [Title], [AnswerSetID])
VALUES(5, 1, N'Poor appetite or overeating?', 1)
INSERT [Test].[Question]([Question_ID], [TestID], [Title], [AnswerSetID])
VALUES(6, 1, N'Feeling bad about yourself - or that you are a failure or have let yourself or your family down?', 1)
INSERT [Test].[Question]([Question_ID], [TestID], [Title], [AnswerSetID])
VALUES(7, 1, N'Trouble concentrating on things, such as reading the newspaper or watching television?', 1)
INSERT [Test].[Question]([Question_ID], [TestID], [Title], [AnswerSetID])
VALUES(8, 1, N'Moving or speaking so slowly that other people could have noticed? Or the opposite - being so fidgety or restless that you have been moving around a lot more than usual?', 1)
INSERT [Test].[Question]([Question_ID], [TestID], [Title], [AnswerSetID])
VALUES(9, 1, N'Thoughts that you would be better off dead, or of hurting yourself in some way?', 1)
INSERT [Test].[Question]([Question_ID], [TestID], [Title], [AnswerSetID])
VALUES(10, 2, N'Been upset because of something that happened unexpectedly?', 2)
INSERT [Test].[Question]([Question_ID], [TestID], [Title], [AnswerSetID])
VALUES(11, 2, N'Felt that you were unable to control important things in your life?', 2)
INSERT [Test].[Question]([Question_ID], [TestID], [Title], [AnswerSetID])
VALUES(12, 2, N'Felt nervous and stressed?', 2)
INSERT [Test].[Question]([Question_ID], [TestID], [Title], [AnswerSetID])
VALUES(13, 2, N'Not felt confident about your ability to handle your personal problems?', 2)
INSERT [Test].[Question]([Question_ID], [TestID], [Title], [AnswerSetID])
VALUES(14, 2, N'Not felt that things were going your way?', 2)
INSERT [Test].[Question]([Question_ID], [TestID], [Title], [AnswerSetID])
VALUES(15, 2, N'Found that you could NOT cope with all the things you had to do', 2)
INSERT [Test].[Question]([Question_ID], [TestID], [Title], [AnswerSetID])
VALUES(16, 2, N'Not been able to control irritations in your life?', 2)
INSERT [Test].[Question]([Question_ID], [TestID], [Title], [AnswerSetID])
VALUES(17, 2, N'Not felt that you were on top of things?', 2)
INSERT [Test].[Question]([Question_ID], [TestID], [Title], [AnswerSetID])
VALUES(18, 2, N'Been angered because of things that happened that were out of your control?', 2)
INSERT [Test].[Question]([Question_ID], [TestID], [Title], [AnswerSetID])
VALUES(19, 2, N'Felt difficulties were piling up so high that you could not overcome them?', 2)
INSERT [Test].[Question]([Question_ID], [TestID], [Title], [AnswerSetID])
VALUES(20, 3, N'I find it very hard to unwind, relax or sit still', 2)
INSERT [Test].[Question]([Question_ID], [TestID], [Title], [AnswerSetID])
VALUES(21, 3, N'I have had stomach problems, such as feeling sick or stomach cramps', 2)
INSERT [Test].[Question]([Question_ID], [TestID], [Title], [AnswerSetID])
VALUES(22, 3, N'I have been irritable and easily become annoyed', 2)
INSERT [Test].[Question]([Question_ID], [TestID], [Title], [AnswerSetID])
VALUES(23, 3, N'I have experienced shortness of breath', 2)
INSERT [Test].[Question]([Question_ID], [TestID], [Title], [AnswerSetID])
VALUES(24, 3, N'I have felt dizzy and unsteady at times', 2)
INSERT [Test].[Question]([Question_ID], [TestID], [Title], [AnswerSetID])
VALUES(25, 3, N'I have had difficulties with sleep (including waking early, finding it hard to go to sleep)', 2)
INSERT [Test].[Question]([Question_ID], [TestID], [Title], [AnswerSetID])
VALUES(26, 3, N'I have felt panicked and overwhelmed by things in my life', 2)
INSERT [Test].[Question]([Question_ID], [TestID], [Title], [AnswerSetID])
VALUES(27, 3, N'I have felt nervous and on edge', 2)
INSERT [Test].[Question]([Question_ID], [TestID], [Title], [AnswerSetID])
VALUES(28, 3, N'I have had trembling hands', 2)
INSERT [Test].[Question]([Question_ID], [TestID], [Title], [AnswerSetID])
VALUES(29, 3, N'I seem to be constantly worrying about things', 2)
INSERT [Test].[Question]([Question_ID], [TestID], [Title], [AnswerSetID])
VALUES(30, 4, N'My relationships are very intense, unstable, and alternate between the extremes of over idealizing and undervaluing people who are important to me.', 3)
INSERT [Test].[Question]([Question_ID], [TestID], [Title], [AnswerSetID])
VALUES(31, 4, N'My emotions change very quickly, and I experience intense episodes of sadness, irritability, and anxiety or panic attacks.', 4)
INSERT [Test].[Question]([Question_ID], [TestID], [Title], [AnswerSetID])
VALUES(32, 4, N'My level of anger is often inappropriate, intense, and difficult to control.', 4)
INSERT [Test].[Question]([Question_ID], [TestID], [Title], [AnswerSetID])
VALUES(33, 4, N'Now, or in the past, when upset, I have engaged in recurrent suicidal behaviors, gestures, threats, or self-injurious behavior such as cutting, burning, or hitting myself.', 4)
INSERT [Test].[Question]([Question_ID], [TestID], [Title], [AnswerSetID])
VALUES(34, 4, N'I have a significant and persistently unstable image or sense of myself, or of who I am or what I truly believe in.', 4)
INSERT [Test].[Question]([Question_ID], [TestID], [Title], [AnswerSetID])
VALUES(35, 4, N'I have very suspicious ideas, and am even paranoid (falsely believe that others are plotting to cause me harm); or I experience episodes under stress when I feel that I, other people, or the situation is somewhat unreal.', 4)
INSERT [Test].[Question]([Question_ID], [TestID], [Title], [AnswerSetID])
VALUES(36, 4, N'I engage in two or more self-damaging acts such as excessive spending, unsafe and inappropriate sexual conduct, substance abuse, reckless driving, and binge eating.', 4)
INSERT [Test].[Question]([Question_ID], [TestID], [Title], [AnswerSetID])
VALUES(37, 4, N'I engage in frantic efforts to avoid real or imagined abandonment by people who are close to me.', 4)
INSERT [Test].[Question]([Question_ID], [TestID], [Title], [AnswerSetID])
VALUES(38, 4, N'I suffer from feelings of emptiness and boredom.', 4)
INSERT [Test].[Question]([Question_ID], [TestID], [Title], [AnswerSetID])
VALUES(39, 8, N'Do you ever hear or see things that others cannot?', 2)
INSERT [Test].[Question]([Question_ID], [TestID], [Title], [AnswerSetID])
VALUES(40, 8, N'Do you struggle to trust that what you are thinking is real?', 2)
INSERT [Test].[Question]([Question_ID], [TestID], [Title], [AnswerSetID])
VALUES(41, 8, N'Do you get the sense that others are controlling your thoughts and emotions?', 2)
INSERT [Test].[Question]([Question_ID], [TestID], [Title], [AnswerSetID])
VALUES(42, 8, N'Do you struggle to keep up with daily living tasks such as showering, changing clothes, paying bills, cleaning, cooking, etc.?', 2)
INSERT [Test].[Question]([Question_ID], [TestID], [Title], [AnswerSetID])
VALUES(43, 8, N'Do you feel that you have powers that other people cannot understand or appreciate?', 2)
INSERT [Test].[Question]([Question_ID], [TestID], [Title], [AnswerSetID])
VALUES(44, 8, N'Do you find it difficult to organize or keep track of your thinking?', 2)
INSERT [Test].[Question]([Question_ID], [TestID], [Title], [AnswerSetID])
VALUES(45, 8, N'Do other people say that it is difficult for you to stay on subject or for them to understand you?', 2)
INSERT [Test].[Question]([Question_ID], [TestID], [Title], [AnswerSetID])
VALUES(46, 8, N'Are you struggling with maintaining social relationships, employment,  and/or academic demands?', 2)
INSERT [Test].[Question]([Question_ID], [TestID], [Title], [AnswerSetID])
VALUES(47, 8, N'Do you feel that you are being tracked, followed, or watched at home or outside?', 2)
INSERT [Test].[Question]([Question_ID], [TestID], [Title], [AnswerSetID])
VALUES(48, 8, N'Do other people have a difficult time guessing your emotions by your facial expressions?', 2)
INSERT [Test].[Question]([Question_ID], [TestID], [Title], [AnswerSetID])
VALUES(49, 9, N'Have you experienced, witnessed, or heard from a person close to you (family or friend) about a threatening event? Examples include actual or threatened death, serious injury, or sexual violence.', 5)
INSERT [Test].[Question]([Question_ID], [TestID], [Title], [AnswerSetID])
VALUES(50, 9, N'Since this event occurred, do you experience recurrent involuntary memories or dreams that make you suffer?', 5)
INSERT [Test].[Question]([Question_ID], [TestID], [Title], [AnswerSetID])
VALUES(51, 9, N'Do you become distressed by sights, sounds, smells or feelings that remind you of the traumatic event?', 5)
INSERT [Test].[Question]([Question_ID], [TestID], [Title], [AnswerSetID])
VALUES(52, 9, N'Do you try not to think about the traumatic event?', 5)
INSERT [Test].[Question]([Question_ID], [TestID], [Title], [AnswerSetID])
VALUES(53, 9, N'Do you try not to see people, go to places or see objects that are associated with the traumatic event?', 5)
INSERT [Test].[Question]([Question_ID], [TestID], [Title], [AnswerSetID])
VALUES(54, 9, N'Do you have trouble remembering an important aspect of the traumatic event?', 5)
INSERT [Test].[Question]([Question_ID], [TestID], [Title], [AnswerSetID])
VALUES(55, 9, N'Because of the event, do you now believe that no one can be trusted?', 5)
INSERT [Test].[Question]([Question_ID], [TestID], [Title], [AnswerSetID])
VALUES(56, 9, N'Do you blame yourself for the event happening?', 5)
INSERT [Test].[Question]([Question_ID], [TestID], [Title], [AnswerSetID])
VALUES(57, 9, N'Do you feel fear, guilt or shame about the traumatic event?', 5)
INSERT [Test].[Question]([Question_ID], [TestID], [Title], [AnswerSetID])
VALUES(58, 9, N'Are you uninterested in activities you enjoyed before the traumatic event?', 5)
INSERT [Test].[Question]([Question_ID], [TestID], [Title], [AnswerSetID])
VALUES(59, 9, N'Do you feel disconnected from people?', 5)
INSERT [Test].[Question]([Question_ID], [TestID], [Title], [AnswerSetID])
VALUES(60, 9, N'Is it very difficult for you to feel happiness, satisfaction or love?', 5)
INSERT [Test].[Question]([Question_ID], [TestID], [Title], [AnswerSetID])
VALUES(61, 9, N'Are you feeling angrier than before the traumatic event occurred?', 5)
INSERT [Test].[Question]([Question_ID], [TestID], [Title], [AnswerSetID])
VALUES(62, 9, N'Are you more self-destructive?', 5)
INSERT [Test].[Question]([Question_ID], [TestID], [Title], [AnswerSetID])
VALUES(63, 9, N'Are you always on high alert?', 5)
INSERT [Test].[Question]([Question_ID], [TestID], [Title], [AnswerSetID])
VALUES(64, 9, N'Have you developed problems with sleep?', 5)
INSERT [Test].[Question]([Question_ID], [TestID], [Title], [AnswerSetID])
VALUES(65, 9, N'Could any of the new behaviors or thoughts you are experiencing be attributed to substance use or a medical condition (ie. a head injury)?', 5)
INSERT [Test].[Question]([Question_ID], [TestID], [Title], [AnswerSetID])
VALUES(66, 9, N'Have these issues caused significant difficulties in your relationships, work or school?', 5)
INSERT [Test].[Question]([Question_ID], [TestID], [Title], [AnswerSetID])
VALUES(67, 10, N'At times I am MUCH more talkative or speak MUCH faster than usual.', 2)
INSERT [Test].[Question]([Question_ID], [TestID], [Title], [AnswerSetID])
VALUES(68, 10, N'There have been times when I was MUCH more active or did MANY more things than usual.', 2)
INSERT [Test].[Question]([Question_ID], [TestID], [Title], [AnswerSetID])
VALUES(69, 10, N'I get into moods where I feel VERY speeded up or irritable.', 2)
INSERT [Test].[Question]([Question_ID], [TestID], [Title], [AnswerSetID])
VALUES(70, 10, N'There have been times when I have felt both high (elated) and low (depressed) AT THE SAME TIME.', 2)
INSERT [Test].[Question]([Question_ID], [TestID], [Title], [AnswerSetID])
VALUES(71, 10, N'At times I have been MUCH more interested in sex than usual.', 2)
INSERT [Test].[Question]([Question_ID], [TestID], [Title], [AnswerSetID])
VALUES(72, 10, N'My self-confidence ranges from GREAT self-doubt to EQUALLY GREAT overconfidence.', 2)
INSERT [Test].[Question]([Question_ID], [TestID], [Title], [AnswerSetID])
VALUES(73, 10, N'There have been GREAT variations in the quantity or quality of my work.', 2)
INSERT [Test].[Question]([Question_ID], [TestID], [Title], [AnswerSetID])
VALUES(74, 10, N'FOR NO OBVIOUS REASON I sometimes have been VERY angry or hostile.', 2)
INSERT [Test].[Question]([Question_ID], [TestID], [Title], [AnswerSetID])
VALUES(75, 10, N'Sometimes I am mentally dull and at other times I think VERY creatively.', 2)
INSERT [Test].[Question]([Question_ID], [TestID], [Title], [AnswerSetID])
VALUES(76, 10, N'At times I am GREATLY interested in being with people and at other times I just want to be left alone with my thoughts.', 2)
INSERT [Test].[Question]([Question_ID], [TestID], [Title], [AnswerSetID])
VALUES(77, 10, N'At some times I have GREAT optimism and at other times EQUALLY GREAT pessimism.', 2)
INSERT [Test].[Question]([Question_ID], [TestID], [Title], [AnswerSetID])
VALUES(78, 10, N'Some of the time I show MUCH tearfulness and crying and at other times I laugh and joke EXCESSIVELY.', 2)
GO
INSERT [Test].[Result]([Result_ID], [TestID], [Weight], [Description])
VALUES(1, 1, 80, N'Little Severe')
INSERT [Test].[Result]([Result_ID], [TestID], [Weight], [Description])
VALUES(2, 1, 100, N'Severe')
INSERT [Test].[Result]([Result_ID], [TestID], [Weight], [Description])
VALUES(3, 2, 80, N'Little Severe')
INSERT [Test].[Result]([Result_ID], [TestID], [Weight], [Description])
VALUES(4, 2, 100, N'Severe')
INSERT [Test].[Result]([Result_ID], [TestID], [Weight], [Description])
VALUES(5, 3, 80, N'Little Severe')
INSERT [Test].[Result]([Result_ID], [TestID], [Weight], [Description])
VALUES(6, 3, 100, N'Severe')
INSERT [Test].[Result]([Result_ID], [TestID], [Weight], [Description])
VALUES(7, 4, 80, N'Little Severe')
INSERT [Test].[Result]([Result_ID], [TestID], [Weight], [Description])
VALUES(8, 4, 100, N'Severe')
INSERT [Test].[Result]([Result_ID], [TestID], [Weight], [Description])
VALUES(9, 5, 80, N'Little Severe')
INSERT [Test].[Result]([Result_ID], [TestID], [Weight], [Description])
VALUES(10, 5, 100, N'Severe')
INSERT [Test].[Result]([Result_ID], [TestID], [Weight], [Description])
VALUES(11, 6, 80, N'Little Severe')
INSERT [Test].[Result]([Result_ID], [TestID], [Weight], [Description])
VALUES(12, 6, 100, N'Severe')
INSERT [Test].[Result]([Result_ID], [TestID], [Weight], [Description])
VALUES(13, 7, 80, N'Little Severe')
INSERT [Test].[Result]([Result_ID], [TestID], [Weight], [Description])
VALUES(14, 7, 100, N'Severe')
INSERT [Test].[Result]([Result_ID], [TestID], [Weight], [Description])
VALUES(15, 8, 80, N'Little Severe')
INSERT [Test].[Result]([Result_ID], [TestID], [Weight], [Description])
VALUES(16, 8, 100, N'Severe')
INSERT [Test].[Result]([Result_ID], [TestID], [Weight], [Description])
VALUES(17, 9, 80, N'Little Severe')
INSERT [Test].[Result]([Result_ID], [TestID], [Weight], [Description])
VALUES(18, 9, 100, N'Severe')
INSERT [Test].[Result]([Result_ID], [TestID], [Weight], [Description])
VALUES(19, 10, 80, N'Little Severe')
INSERT [Test].[Result]([Result_ID], [TestID], [Weight], [Description])
VALUES(20, 10, 100, N'Severe')
GO
INSERT [Test].[Test]([Test_ID], [Total], [No_Question], [DiseaseID])
VALUES(1, 27, 9, 1)
INSERT [Test].[Test]([Test_ID], [Total], [No_Question], [DiseaseID])
VALUES(2, 40, 10, 2)
INSERT [Test].[Test]([Test_ID], [Total], [No_Question], [DiseaseID])
VALUES(3, 40, 10, 3)
INSERT [Test].[Test]([Test_ID], [Total], [No_Question], [DiseaseID])
VALUES(4, 17, 9, 4)
INSERT [Test].[Test]([Test_ID], [Total], [No_Question], [DiseaseID])
VALUES(5, NULL, NULL, 5)
INSERT [Test].[Test]([Test_ID], [Total], [No_Question], [DiseaseID])
VALUES(6, NULL, NULL, 6)
INSERT [Test].[Test]([Test_ID], [Total], [No_Question], [DiseaseID])
VALUES(7, NULL, NULL, 7)
INSERT [Test].[Test]([Test_ID], [Total], [No_Question], [DiseaseID])
VALUES(8, 40, 10, 8)
INSERT [Test].[Test]([Test_ID], [Total], [No_Question], [DiseaseID])
VALUES(9, 18, 18, 9)
INSERT [Test].[Test]([Test_ID], [Total], [No_Question], [DiseaseID])
VALUES(10, 48, 12, 10)
GO
/****** Object:  Index [UQ__Patient__1788CCAD1357D177]    Script Date: 5/2/2023 11:00:53 PM ******/
ALTER TABLE [Account].[Patient]
ADD UNIQUE NONCLUSTERED([UserID] ASC)WITH(PAD_INDEX=OFF, STATISTICS_NORECOMPUTE=OFF, SORT_IN_TEMPDB=OFF, IGNORE_DUP_KEY=OFF, ONLINE=OFF, ALLOW_ROW_LOCKS=ON, ALLOW_PAGE_LOCKS=ON)ON [PRIMARY]
GO
/****** Object:  Index [UQ__Speciali__1788CCAD3EBB574D]    Script Date: 5/2/2023 11:00:53 PM ******/
ALTER TABLE [Account].[Specialist]
ADD UNIQUE NONCLUSTERED([UserID] ASC)WITH(PAD_INDEX=OFF, STATISTICS_NORECOMPUTE=OFF, SORT_IN_TEMPDB=OFF, IGNORE_DUP_KEY=OFF, ONLINE=OFF, ALLOW_ROW_LOCKS=ON, ALLOW_PAGE_LOCKS=ON)ON [PRIMARY]
GO
/****** Object:  Index [UQ__Booking__58090F7D106065C7]    Script Date: 5/2/2023 11:00:53 PM ******/
ALTER TABLE [Booking].[Booking]
ADD UNIQUE NONCLUSTERED([HealingInformationID] ASC)WITH(PAD_INDEX=OFF, STATISTICS_NORECOMPUTE=OFF, SORT_IN_TEMPDB=OFF, IGNORE_DUP_KEY=OFF, ONLINE=OFF, ALLOW_ROW_LOCKS=ON, ALLOW_PAGE_LOCKS=ON)ON [PRIMARY]
GO
/****** Object:  Index [UQ__Booking__58090F7D3A530A9D]    Script Date: 5/2/2023 11:00:53 PM ******/
ALTER TABLE [Booking].[Booking]
ADD UNIQUE NONCLUSTERED([HealingInformationID] ASC)WITH(PAD_INDEX=OFF, STATISTICS_NORECOMPUTE=OFF, SORT_IN_TEMPDB=OFF, IGNORE_DUP_KEY=OFF, ONLINE=OFF, ALLOW_ROW_LOCKS=ON, ALLOW_PAGE_LOCKS=ON)ON [PRIMARY]
GO
ALTER TABLE [Account].[Patient] WITH CHECK
ADD FOREIGN KEY([UserID])REFERENCES [Account].[Account]([User_ID])
GO
ALTER TABLE [Account].[PatientRecord] WITH CHECK
ADD FOREIGN KEY([PatientID])REFERENCES [Account].[Patient]([Patient_ID])
GO
ALTER TABLE [Account].[PatientRecord] WITH CHECK
ADD FOREIGN KEY([ResultID])REFERENCES [Test].[Result]([Result_ID])
GO
ALTER TABLE [Account].[Specialist] WITH CHECK
ADD FOREIGN KEY([UserID])REFERENCES [Account].[Account]([User_ID])
GO
ALTER TABLE [Booking].[Booking] WITH CHECK
ADD FOREIGN KEY([HealingInformationID])REFERENCES [Booking].[HealingInformation]([HealingInformation_ID])
GO
ALTER TABLE [Booking].[Booking] WITH CHECK
ADD FOREIGN KEY([PatientID])REFERENCES [Account].[Patient]([Patient_ID])
GO
ALTER TABLE [Booking].[HealingInformation] WITH CHECK
ADD FOREIGN KEY([SpecialistID])REFERENCES [Account].[Specialist]([Specialist_ID])
GO
ALTER TABLE [Booking].[HealingInformation] WITH CHECK
ADD FOREIGN KEY([SpecialistID])REFERENCES [Account].[Specialist]([Specialist_ID])
GO
ALTER TABLE [Disease].[DiseaseSymptom] WITH CHECK
ADD FOREIGN KEY([DiseaseID])REFERENCES [Disease].[Disease]([Disease_ID])
GO
ALTER TABLE [Disease].[DiseaseSymptom] WITH CHECK
ADD FOREIGN KEY([SymptomID])REFERENCES [Disease].[Symptom]([Symptom_ID])
GO
ALTER TABLE [Solution].[CureOneByOne] WITH CHECK
ADD FOREIGN KEY([SolutionID])REFERENCES [Solution].[Solution]([Solution_ID])
GO
ALTER TABLE [Solution].[CureOneByOne] WITH CHECK
ADD FOREIGN KEY([SymptomID])REFERENCES [Disease].[Symptom]([Symptom_ID])
GO
ALTER TABLE [Solution].[Recommendation] WITH CHECK
ADD FOREIGN KEY([ResultID])REFERENCES [Test].[Result]([Result_ID])
GO
ALTER TABLE [Solution].[Recommendation] WITH CHECK
ADD FOREIGN KEY([SolutionID])REFERENCES [Solution].[Solution]([Solution_ID])
GO
ALTER TABLE [Test].[AnswerSetContent] WITH CHECK
ADD FOREIGN KEY([AnswerID])REFERENCES [Test].[Answer]([Answer_ID])
GO
ALTER TABLE [Test].[AnswerSetContent] WITH CHECK
ADD FOREIGN KEY([AnswerSetID])REFERENCES [Test].[AnswerSet]([AnswerSet_ID])
GO
ALTER TABLE [Test].[Question] WITH CHECK
ADD FOREIGN KEY([AnswerSetID])REFERENCES [Test].[AnswerSet]([AnswerSet_ID])
GO
ALTER TABLE [Test].[Question] WITH CHECK
ADD FOREIGN KEY([TestID])REFERENCES [Test].[Test]([Test_ID])
GO
ALTER TABLE [Test].[Result] WITH CHECK
ADD FOREIGN KEY([TestID])REFERENCES [Test].[Test]([Test_ID])
GO
ALTER TABLE [Test].[Test] WITH CHECK
ADD FOREIGN KEY([DiseaseID])REFERENCES [Disease].[Disease]([Disease_ID])
GO
ALTER TABLE [Booking].[HealingInformation] WITH CHECK
ADD CHECK(([Date]>CONVERT([date], getdate(), 0)))
GO