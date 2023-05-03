-- ? means that we parameterize this field and wait for users' input
-- Show available (vacant) healing 
SELECT H.HealingInformation_ID AS ID, H.Date, H.Place, CAST(H.Fee AS INT) AS Fee, S.FullName, S.Sex
FROM [Booking].[HealingInformation] H
     INNER JOIN [Account].[Specialist] S ON S.Specialist_ID=H.SpecialistID
WHERE H.HealingInformation_ID NOT IN(SELECT DISTINCT B.HealingInformationID FROM [Booking].[Booking] B)AND H.Date>=CAST(GETDATE() AS DATE)
ORDER BY H.Date
-- Insert patient booking information 
INSERT INTO [Booking].[Booking] ([PatientID], [HealingInformationID])
VALUES (?,?)
-- Authenticate user login
DECLARE @UserName AS VARCHAR(50) = ?
DECLARE @Pwd AS VARCHAR(30) = ?
SELECT CASE WHEN A.User_ID=P.UserID AND P.UserID IS NOT NULL THEN 'Patient'
       WHEN A.User_ID=S.UserID AND S.UserID IS NOT NULL THEN 'Specialist' ELSE NULL END AS Role, CASE WHEN A.User_ID=P.UserID AND P.UserID IS NOT NULL THEN P.Patient_ID
                                                                                                 WHEN A.User_ID=S.UserID AND S.UserID IS NOT NULL THEN S.Specialist_ID ELSE NULL END AS ID
FROM [Account].[Account] A
     FULL JOIN [Account].[Patient] P ON A.User_ID=P.UserID
     FULL JOIN [Account].[Specialist] S ON A.User_ID=S.UserID
WHERE A.User_name=@UserName AND A.Password=HASHBYTES('SHA1', CONCAT(@Pwd, (SELECT A.Salt FROM [Account].[Account] A WHERE A.User_name=@UserName)))
-- Show user name
DECLARE @UserID AS INT = ?
DECLARE @Role AS VARCHAR(30) = ?
SELECT FullName
FROM(SELECT CASE WHEN @UserID=P.Patient_ID AND @Role='Patient' THEN P.FullName
            WHEN @UserID=S.Specialist_ID AND @Role='Specialist' THEN S.FullName END AS FullName
     FROM [Account].[Account] A
          FULL JOIN [Account].[Patient] P ON A.User_ID=P.UserID
          FULL JOIN [Account].[Specialist] S ON A.User_ID=S.UserID) AS SUBQUERY
WHERE FullName IS NOT NULL
-- Update user password with authentication old password
DECLARE @UserName AS VARCHAR(50) = ?
DECLARE @oldPwd AS VARCHAR(30) = ?
DECLARE @newPwd AS VARCHAR(30) = ?
DECLARE @Salt AS VARCHAR(5)
SET @Salt=CONCAT(CHAR(FLOOR(RAND()* 10)+48), CHAR(FLOOR(RAND()* 26)+65), CHAR(FLOOR(RAND()* 26)+97), CHAR(FLOOR(RAND()* 15)+33), CHAR(FLOOR(RAND()* 10)+48))
UPDATE [Account].[Account]
SET Password=HASHBYTES('SHA1', CONCAT(@newPwd, @Salt)), Salt=@Salt
WHERE User_name=@UserName AND Password=HASHBYTES('SHA1', CONCAT(@oldPwd, (SELECT A.Salt FROM [Account].[Account] A WHERE A.User_name=@UserName)))
-- Show symptom of a disease
SELECT S.Name AS symptom, So.Name AS solution, So.Platform, So.Description
FROM [Disease].[Disease] D
     INNER JOIN [Disease].[DiseaseSymptom] DS ON D.Disease_ID=DS.DiseaseID
     INNER JOIN [Disease].[Symptom] S ON DS.SymptomID=S.Symptom_ID
     INNER JOIN [Solution].[CureOneByOne] C ON S.Symptom_ID=C.SymptomID
     INNER JOIN [Solution].[Solution] So ON C.SolutionID=So.Solution_ID
WHERE D.Name= ?
-- Show patient recent booking
SELECT H.HealingInformation_ID AS ID, CONCAT(DAY(H.Date), ' / ', MONTH(H.Date)) AS DATE, H.Place, S.FullName, S.Phone
FROM [Account].[Specialist] S
     INNER JOIN [Booking].[HealingInformation] H ON S.Specialist_ID=H.SpecialistID
     INNER JOIN [Booking].[Booking] B ON H.HealingInformation_ID=B.HealingInformationID
     INNER JOIN [Account].[Patient] P ON B.PatientID=P.Patient_ID
WHERE P.Patient_ID = ? AND H.Date>=CAST(GETDATE() AS DATE)
ORDER BY H.Date
-- Remove patient healing
DELETE FROM [Booking].[Booking]
WHERE PatientID = ? AND HealingInformationID = ?
-- Register patient user
DECLARE @Username AS VARCHAR(50) = ?
DECLARE @Pwd AS VARCHAR(30) = ?
DECLARE @Salt AS VARCHAR(5)
SET @Salt=CONCAT(CHAR(FLOOR(RAND()* 10)+48), CHAR(FLOOR(RAND()* 26)+65), CHAR(FLOOR(RAND()* 26)+97), CHAR(FLOOR(RAND()* 15)+33), CHAR(FLOOR(RAND()* 10)+48))
INSERT INTO [Account].[Account]([User_name], [Password], [Salt])
VALUES(@Username, HASHBYTES('SHA1', CONCAT(@Pwd, @Salt)), @Salt)
INSERT INTO [Account].[Patient]([UserID], [FullName], [DoB], [Sex], [Email])
VALUES((SELECT User_ID FROM [Account].[Account] A WHERE A.User_name = ?), ?, ?, ?, ?)
-- Post specialist healing session
INSERT INTO [Booking].[HealingInformation] ([SpecialistID], [Place], [Date], [Fee], [Description], [Extra_Information])
VALUES (?,?,?,?,?,?) 
-- Show specialist recent booking
SELECT H.HealingInformation_ID AS ID, CONCAT(DAY(H.Date), ' / ', MONTH(H.Date)) AS DATE, H.Place, H.Fee, P.FullName, P.Sex, P.Email, CASE WHEN H.HealingInformation_ID=B.HealingInformationID THEN 'RESERVED' ELSE 'VACANT' END AS State
FROM [Account].[Specialist] S
     FULL JOIN [Booking].[HealingInformation] H ON S.Specialist_ID=H.SpecialistID
     FULL JOIN [Booking].[Booking] B ON H.HealingInformation_ID=B.HealingInformationID
     FULL JOIN [Account].[Patient] P ON B.PatientID=P.Patient_ID
WHERE S.[Specialist_ID] =? AND H.Date>=CAST(GETDATE() AS DATE)
ORDER BY H.Date
-- Remove vacant specialist session
DELETE FROM [Booking].[HealingInformation]
WHERE SpecialistID = ? AND HealingInformation_ID = ?
-- Register specialist user
DECLARE @Username AS VARCHAR(50) = ?
DECLARE @Pwd AS VARCHAR(30) = ?
DECLARE @Salt AS VARCHAR(5)
SET @Salt=CONCAT(CHAR(FLOOR(RAND()* 10)+48), CHAR(FLOOR(RAND()* 26)+65), CHAR(FLOOR(RAND()* 26)+97), CHAR(FLOOR(RAND()* 15)+33), CHAR(FLOOR(RAND()* 10)+48))
INSERT INTO [Account].[Account]([User_name], [Password], [Salt])
VALUES(@Username, HASHBYTES('SHA1', CONCAT(@Pwd, @Salt)), @Salt)
INSERT INTO [Account].[Specialist]([UserID], [FullName], [DoB], [Sex], [Email], [Phone], [IdentifyNumber], [GraduationUniversity])
VALUES((SELECT User_ID FROM [Account].[Account] A WHERE A.User_name=?), ?, ?, ?, ?, ?, ?, ?)
-- Show available test combo
SELECT Test_ID AS ID FROM [Test].[Test]
-- Show available question combo
SELECT Question_ID AS questionID FROM [Test].[Question] WHERE TestID = ?
-- Show question content for specific question ID
SELECT Title FROM [Test].[Question] WHERE TestID = ? AND Question_ID = ?
-- Show answer set content for specific question ID
SELECT A.Title
FROM [Test].[Question] Q
     INNER JOIN [Test].[AnswerSet] ASET ON Q.AnswerSetID=ASET.AnswerSet_ID
     INNER JOIN [Test].[AnswerSetContent] ASCon ON ASET.AnswerSet_ID=ASCon.AnswerSetID
     INNER JOIN [Test].[Answer] A ON ASCon.AnswerID=A.Answer_ID
WHERE Q.Question_ID= ?
-- Show total score for specific test combo
SELECT Total FROM [Test].[Test] WHERE Test_ID= ?
-- Show weights per answer for specific question ID 
SELECT A.Weight
FROM [Test].[Question] Q
     INNER JOIN [Test].[AnswerSet] ASET ON Q.AnswerSetID=ASET.AnswerSet_ID
     INNER JOIN [Test].[AnswerSetContent] ASCon ON ASCon.AnswerSetID=ASET.AnswerSet_ID
     INNER JOIN [Test].[Answer] A ON ASCon.AnswerID=A.Answer_ID
WHERE Q.Question_ID= ?
-- Show solution set 
DECLARE @TestID AS INT = ?
DECLARE @Weight AS INT = ?
SELECT R.Result_ID AS ID, S.Name, S.Benefit, S.Platform, S.Description
FROM [Test].[Result] R
     INNER JOIN [Solution].[Recommendation] Re ON R.Result_ID=Re.ResultID
     INNER JOIN [Solution].[Solution] S ON Re.SolutionID=S.Solution_ID
WHERE R.TestID=@TestID AND R.Weight=@Weight AND R.Result_ID IN(SELECT R.Result_ID
                                                               FROM [Test].[Result] R
                                                               WHERE TestID=@TestID AND Weight=@Weight)
-- Submit patient test result
INSERT INTO [Account].[PatientRecord]
VALUES (?, ?)
-- Select severe patient in the database
SELECT TOP 1 Pa.FullName, Pa.Sex, Pa.DoB, COUNT(Description) NumberOfSevereDisease
FROM [Account].[Patient] Pa
     INNER JOIN [Account].[PatientRecord] Pr ON Pa.Patient_ID=Pr.PatientID
     INNER JOIN [Test].[Result] R ON PR.ResultID=R.Result_ID
WHERE R.Description LIKE 'Severe'
GROUP BY Pa.FullName, Pa.Sex, Pa.DoB
ORDER BY NumberOfSevereDisease DESC
-- Select patient who booked the most reservations
SELECT TOP 1 Pa.FullName, Pa.Sex, Pa.DoB, COUNT(B.HealingInformationID) NumberOfReservation
FROM [Account].[Patient] Pa
     INNER JOIN [Booking].[Booking] B ON Pa.Patient_ID=B.PatientID
GROUP BY Pa.FullName, Pa.Sex, Pa.DoB
ORDER BY NumberOfReservation DESC
-- Select specialist who posted the most reservations
SELECT TOP 1 S.Specialist_ID, S.FullName, COUNT(H.HealingInformation_ID) NumberOfReservation
FROM [Account].[Specialist] S
     INNER JOIN [Booking].[HealingInformation] H ON S.Specialist_ID=H.SpecialistID
GROUP BY S.Specialist_ID, S.FullName
ORDER BY NumberOfReservation DESC
-- Select specialist who have the most booked reservations
SELECT TOP 1 S.Specialist_ID, S.FullName, COUNT(B.HealingInformationID) NumberOfBookedReservation
FROM [Account].[Specialist] S
     INNER JOIN [Booking].[HealingInformation] H ON S.Specialist_ID=H.SpecialistID
     INNER JOIN [Booking].[Booking] B ON H.HealingInformation_ID=B.HealingInformationID
GROUP BY S.Specialist_ID, S.FullName
ORDER BY NumberOfBookedReservation DESC
-- Select patient who has not made any reservation
SELECT P.FullName
FROM [Account].[Patient] P
WHERE P.Patient_ID NOT IN(SELECT B.PatientID FROM [Booking].[Booking] B)
-- Select specialist who has not posted any reservation
SELECT S.FullName
FROM [Account].[Specialist] S
WHERE S.Specialist_ID NOT IN(SELECT H.HealingInformation_ID FROM [Booking].[HealingInformation] H)
-- Select the disease most patients have
SELECT TOP 1 D.Name, D.Description, COUNT(PR.ResultID) NumberOfDoneTest
FROM [Disease].[Disease] D
     INNER JOIN [Test].[Test] T ON D.Disease_ID=T.Test_ID
     INNER JOIN [Test].[Result] R ON T.Test_ID=R.TestID
     INNER JOIN [Account].[PatientRecord] PR ON R.Result_ID=PR.ResultID
GROUP BY D.Name, D.Description
ORDER BY NumberOfDoneTest DESC