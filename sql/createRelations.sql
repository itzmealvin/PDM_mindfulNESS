CREATE DATABASE NESS
Go
USE NESS
Go
--- Create Schema to classify various relations ---
Go
CREATE SCHEMA Account AUTHORIZATION dbo
Go
CREATE SCHEMA Booking AUTHORIZATION dbo
Go
CREATE SCHEMA Disease AUTHORIZATION dbo
Go
CREATE SCHEMA Test AUTHORIZATION dbo
Go
CREATE SCHEMA Solution AUTHORIZATION dbo
Go

--- Create Account-related Relations ---
CREATE TABLE [Account].[Account] (
    [User_ID] [bigint] IDENTITY(1, 1) PRIMARY KEY,
    [User_name] [varchar](50) NOT NULL,
    [Password] [varchar](20) NOT NULL,
    [Hash] [varchar](100) NOT NULL,
    [Salt] [varchar](100) NOT NULL)
GO

CREATE TABLE [Account].[Patient] (
    [Patient_ID] [int] IDENTITY(1, 1) PRIMARY KEY,
    [UserID] [bigint] NOT NULL,
    [FullName] [varchar](50) NOT NULL,
    [DoB] [date] NOT NULL,
    [Sex] [varchar](10) NOT NULL,
    [Email] [varchar](50) NOT NULL,
    FOREIGN KEY (UserID) REFERENCES [Account].[Account] (User_ID))
GO

CREATE TABLE [Account].[Specialist] (
    [Specialist_ID] [int] IDENTITY(1, 1) PRIMARY KEY,
    [UserID] [bigint] NOT NULL,
    [FullName] [varchar](50) NOT NULL,
    [DoB] [date] NOT NULL,
    [Sex] [varchar](10) NOT NULL,
    [Email] [varchar](50) NOT NULL,
    [Phone] [varchar](15) NOT NULL,
    [IndentityNumber] [varchar](50) NOT NULL,
    [GraduationUniversity] [varchar](50) NOT NULL,
    FOREIGN KEY (UserID) REFERENCES [Account].[Account] (User_ID))
GO

--- Create Booking-related Relations ---
CREATE TABLE [Booking].[HealingInformation] (
    [HealingInformation_ID] [int] IDENTITY(1, 1) PRIMARY KEY,
    [SpecialistID] [int] NOT NULL,
    [Place] [varchar](50) NOT NULL,
    [Date] [date] NOT NULL,
    [Fee] [money] NOT NULL,
    [Description] [varchar](200) NOT NULL,
    [Extra_Information] [varchar](100),
    FOREIGN KEY (SpecialistID) REFERENCES [Account].[Specialist] (Specialist_ID))
GO

CREATE TABLE [Booking].[Booking] (
    [Book_ID] [int] IDENTITY(1, 1) PRIMARY KEY,
    [PatientID] [int] NOT NULL,
    [HealingInformationID] [int] NOT NULL,
    FOREIGN KEY (PatientID) REFERENCES [Account].[Patient] (Patient_ID),
    FOREIGN KEY (HealingInformationID) REFERENCES [Booking].[HealingInformation] (HealingInformation_ID))
GO

--- Create Disease-related Relations ---
CREATE TABLE [Disease].[Disease] (
    [Disease_ID] [int] PRIMARY KEY,
    [Name] [varchar](50) NOT NULL,
    [Description] [varchar](200) NOT NULL, )
GO

CREATE TABLE [Disease].[Symptom] (
    [Symptom_ID] [int] PRIMARY KEY,
    [Name] [varchar](50) NOT NULL,
    [Description] [varchar](100) NOT NULL, )
GO

CREATE TABLE [Disease].[DiseaseSymptom] (
    [ID] [int] PRIMARY KEY,
    [DiseaseID] [int] NOT NULL,
    [SymptomID] [int] NOT NULL,
    FOREIGN KEY (DiseaseID) REFERENCES [Disease].[Disease] (Disease_ID),
    FOREIGN KEY (SymptomID) REFERENCES [Disease].[Symptom] (Symptom_ID))
GO

--- Create Test-related Relations ---
CREATE TABLE [Test].[Test] (
    [Test_ID] [int] PRIMARY KEY,
    [Total] [int],
    [No_Question] [int],
    [DiseaseID] [int] NOT NULL,
    FOREIGN KEY (DiseaseID) REFERENCES [Disease].[Disease] (Disease_ID))
GO

CREATE TABLE [Test].[Result] (
    [Result_ID] [int] PRIMARY KEY,
    [TestID] [int] NOT NULL,
    [Weight] [int] NOT NULL,
    [Description] [varchar](200),
    FOREIGN KEY (TestID) REFERENCES [Test].[Test] (Test_ID), )
GO

CREATE TABLE [Account].[PatientRecord] (
    [ID] [int] PRIMARY KEY,
    [PatientID] [int] NOT NULL,
    [ResultID] [int] NOT NULL,
    FOREIGN KEY (PatientID) REFERENCES [Account].[Patient] (Patient_ID),
    FOREIGN KEY (ResultID) REFERENCES [Test].[Result] (Result_ID))
GO

CREATE TABLE [Test].[AnswerSet] (
    [AnswerSet_ID] [int] PRIMARY KEY,
    [No_Answer] [int] NOT NULL, )
GO

CREATE TABLE [Test].[Answer] (
    [Answer_ID] [int] PRIMARY KEY,
    [Title] [varchar](100) NOT NULL,
    [Weight] [int] NOT NULL, )
GO

CREATE TABLE [Test].[AnswerSetContent] (
    [ID] [int] PRIMARY KEY,
    [AnswerSetID] [int] NOT NULL,
    [AnswerID] [int] NOT NULL,
    FOREIGN KEY (AnswerID) REFERENCES [Test].[Answer] (Answer_ID),
    FOREIGN KEY (AnswerSetID) REFERENCES [Test].[AnswerSet] (AnswerSet_ID))
GO

CREATE TABLE [Test].[Question] (
    [Question_ID] [int] PRIMARY KEY,
    [TestID] [int] NOT NULL,
    [Title] [varchar](100) NOT NULL,
    [AnswerSetID] [int] NOT NULL,
    FOREIGN KEY (TestID) REFERENCES [Test].[Test] (Test_ID),
    FOREIGN KEY (AnswerSetID) REFERENCES [Test].[AnswerSet] (AnswerSet_ID))
GO

--- Create Solution-related Relations ---
CREATE TABLE [Solution].[Solution] (
    [Solution_ID] [int] PRIMARY KEY,
    [Name] [varchar](50) NOT NULL,
    [Type] [varchar](50) NOT NULL,
    [Benefit] [varchar](50) NOT NULL,
    [Platform] [varchar](30) NOT NULL,
    [Description] [varchar](400), )
GO

CREATE TABLE [Solution].[Recommendation] (
    [ID] [int] PRIMARY KEY,
    [SolutionID] [int] NOT NULL,
    [ResultID] [int] NOT NULL,
    FOREIGN KEY (SolutionID) REFERENCES [Solution].[Solution] (Solution_ID),
    FOREIGN KEY (ResultID) REFERENCES [Test].[Result] (Result_ID))
GO

CREATE TABLE [Solution].[CureOneByOne] (
    [ID] [int] PRIMARY KEY,
    [SolutionID] [int],
    [SymptomID] [int] NOT NULL,
    FOREIGN KEY (SolutionID) REFERENCES [Solution].[Solution] (Solution_ID),
    FOREIGN KEY (SymptomID) REFERENCES [Disease].[Symptom] (Symptom_ID))
GO