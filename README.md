<div id="top" align="center">

<img src="image/mindfulness.png" alt="Banner">

</div>
<!-- PROJECT LOGO -->
<div align="center">
<h1 align="center">mindfulNESS</h1>
  <h3 align="center">
    Mental healthcare services. For PDM course at International University - VNU
    <br />
    <br />
    <a href="https://github.com/itzmealvin/PDM_mindfulNESS_PC/issues">Report Bug</a>
    ·
    <a href="https://github.com/itzmealvin/PDM_mindfulNESS_PC/issues">Request Feature</a>
  </h3>

[![Contributors][contributors-shield]][contributors-url]
[![Forks][forks-shield]][forks-url]
[![Issues][issues-shield]][issues-url]

</div>

<!-- About -->

# ABOUT

## 1. The team behind it

| No. |       Full Name       | Student's ID |              Email               |                      Github account                      |                      Roles                       | Contribution |
|:---:|:---------------------:|:------------:|:--------------------------------:|:--------------------------------------------------------:|:------------------------------------------------:|:------------:|
|  1  |  Nguyen Hoang Anh Tu  | ITDSIU20090  | ITDSIU20090@student.hcmiu.edu.vn |        [nghganhtu](https://github.com/nghganhtu)         | **TEAM LEADER** Database Developer, Data Analyst |     20%      |
|  2  |   Nguyen Quang Dieu   | ITDSIU20031  | ITDSIU20031@student.hcmiu.edu.vn |       [itzmealvin](https://github.com/itzmealvin)        |        Interface Developer, Data Analyst         |     20%      |
|  3  | Nguyen Luan Cong Bang | ITITIU20163  | ITITIU20163@student.hcmiu.edu.vn |     [congbangitiu](https://github.com/congbangitiu)      |               Interface Developer                |     20%      |
|  4  |     Pham Minh Vu      | ITITIU20354  | ITITIU20354@student.hcmiu.edu.vn |          [PMinhVu](https://github.com/PMinhVu)           |               Interface Developer                |     20%      |
|  5  |   Huynh Tran Khanh    | ITCSIU21011  | ITCSIU21011@student.hcmiu.edu.vn | [huynhtrankhanh](https://gist.github.com/huynhtrankhanh) |               Interface Developer                |     20%      |

## 2. The project we are working on

The mental healthcare service system **mindfulNESS** involves professional resources to promote mental healthcare and
treat
mental illnesses. It includes many services such as disease search, disease testing, and appointment booking with a
consultant.

The project provided a valuable opportunity to apply **database management principles** to a real-world problem in the
field of mental health. It showcased the practical application of database management principles in addressing
challenges in
mental health.

## 3. Goal

To be short, the project aims achieve these goals:

- []() Design database to meet Normalization BC Normal Form requirements.

- []() Connect front-end interface to back-end database using Java connection driver.

- []() Develop login, account creation, and booking functions with complex queries.

- []() Implement security measures to prevent SQL injection and ensure user data privacy.

<!-- REASON -->

# REASON

## 1. Motivation

The global pandemic has profoundly impacted mental health, resulting in increased recognition of the importance of
psychological therapy. However, accessing traditional therapy can still be challenging for many people due to factors
such as long wait-lists, high costs, and the enduring stigma associated with mental health

## 2. Idea

To address these barriers and meet the growing demand for mental health services, online therapy platforms have
emerged as a popular and accessible option. These platforms offer a wide range of mental health services. One of the
critical advantages of online therapy is its flexibility, allowing individuals to schedule sessions at their
convenience and receive therapy from the comfort of their homes. They also offer more affordable pricing than
traditional in-person therapy, making therapy accessible to a broader audience.

Among the well-known online therapy platforms are BetterHelp, Talkspace, and Amwell. BetterHelp provides various
therapy options, including live sessions, messaging, and chat support. Talkspace specializes in unlimited messaging
therapy with licensed therapists. On the other hand, Amwell offers mental health services as part of its broader
telehealth platform, including access to medical doctors and specialists.

## 3. Roadmap

- [x] 2 distinct roles: patient and consultant for users to choose from
- [x] The system allows patients to search for diseases and take health tests with real scores and solutions
- [x] Patients can make an appointment with a specialist if their illness is severe
- [ ] More to come...

Please see the [open issues](https://github.com/itzmealvin/PDM_mindfulNESS_PC) for a full list of proposed features (
and known issues).

<!-- METHODOLOGY -->

# METHODOLOGY

## 1. Feature Analysis

- **Login page**: Require users to enter the correct credentials to log in for people who have registered for the
  system, sign up for first time users of the system, or explorer mode for system administrators only with a special
  password
- **Sign up mode**: If the user presses the "SIGN UP" button, the system will ask the users to select their role and
  enter
  the correct and complete information before getting in the system
- **Patient Dashboard**: When the user logs in with the patient's account, this page will appear to show all the
  operations related to them, including their recent healing sessions.
- **Self-diagnosis test**: When the user clicks the "SELF-DIAGNOSIS TEST" button in the patient dashboard, the system
  will be redirected to the test page for the user to take the test. The user will choose the test set and choose the
  question
  to answer, after answering all the questions, the score and solution will appear corresponding to their mental health
  level
- **Booking heal**: When the user clicks the "PLACE A HEALING" button in the patient dashboard, the system will be
  redirected to the booking page to let the user book an appointment with the specialist. The user will enter the ID of
  the book list and press the "PLACE A HEALING" button to confirm the healing available
- **Specialist mode**: When the expert enters all the required information and presses the "POST BOOKING INFORMATION"
  button, the information will be displayed on the right listed booking table. If the user wants to delete the meeting,
  press
  the "DELIST HEALING" button.

## 2. Database Design

Please take a look at the diagrams to familiar yourself with our database design

### a. ERD in SQL Server Management Studio format

<div align ="center">
<img src="image/ERD1.png" alt="ERD1">
</div>

### b. ERD in Crowd's Foot format

<div align ="center">
<img src="image/ERD2.png" alt="ERD2">
</div>

### c. Normalization form

| Normal Form Level | Description                                                                        | 
|:------------------|:-----------------------------------------------------------------------------------|
| 1  NF             | The database does not have any multivalued tuples                                  | 
| 2  NF             | All the non-key attributes depend on the primary key                               | 
| 3  NF             | There are no transitive dependencies between non-key attributes                    | 
| BC NF             | Every non-trivial functional dependency in the database depends on a candidate key |

## 2. Database Realization

The database creation was done by using a free hosting storage: **[FreeASPHosting](http://www.freeasphosting.net/)** and
remotely access via SQL Server Management Studio & IntelliJ IDEA Console

At the time of the writing, the database was created using **SQL Server 2016**. You can recreate the database with all
the information using the files provided in SQL Folder

| Table               | Schema   |  Data Source   | 
|:--------------------|:---------|:--------------:|
| Account             | Account  | Self-generated |
| Specialist          | Account  | Self-generated |
| Patient             | Account  | Self-generated |
| Healing Information | Booking  | Self-generated |
| Result              | Solution |    Internet    |
| Solution            | Solution |    Internet    |
| Disease             | Disease  |    Internet    |
| Symptom             | Disease  |    Internet    |
| Test                | Test     |    Internet    |
| Question            | Test     |    Internet    |
| Answer Set          | Test     |    Internet    |
| Answer              | Test     |    Internet    |

## 3. Query usage

Most queries used have the simple keywords, but each of fields that required user input are changed to question marks to
shows it is parameterized (faster query, prevent SQL injection)

| Purpose                         | Query type/Return | Parameterized by user input? | 
|:--------------------------------|:------------------|:----------------------------:|
| Login authentication            | Query/ userID     |             YES              |
| Registration                    | Insert into       |             YES              |
| Display username                | Query/ userName   |              NO              |
| Change password                 | Update            |             YES              |
| Disease search                  | Query/ symptoms   |             YES              |
| Post healing session            | Insert into       |             YES              |
| Display available sessions      | Query/ healingID  |              NO              |
| Delete/Cancel healing sessions  | Remove            |             YES              |
| Book healing session            | Insert into       |             YES              |
| Show test, question, answer set | Query/ testID     |              NO              |
| Show solution                   | Query/ solutionID |              NO              |
| Record patient result           | Insert into       |              NO              |

## 4. Java GUI Design

We can design the frames for Graphical User Interface directly on IntelliJ IDEA GUI Designer and from then, we can
drag-and-drop the elements to the JFrame created.

To initialize the frames, we will use the constructor provided with the use of the
Singleton Design Pattern.

| Element type               | Description                                                                                              |
|:---------------------------|:---------------------------------------------------------------------------------------------------------|
| HSpacer, VSpacer           | to add spacing between the elements                                                                      |
| JPanel                     | to add a frame for other elements, it must be extended from our frame classes                            |
| JButton                    | add a button with ActionListeners to perform logical code when clicking (discuss more in the later part) |
| JScrollPane                | add a frame that can be scrollable, especially for long content                                          |
| JLabel                     | to add title and instruction on the interface                                                            |
| JTextField, JPasswordField | to receive user input on the interface;the latter is used for the password field to protect privacy      |
| JTextArea                  | to display the message after the query has been done                                                     |
| JComboBox                  | to make a drop-down list for the user to choose from                                                     |
| JTable                     | to display the query result in tabular form                                                              |
| JOptionPane                | to display the dialog for the user to interact with                                                      |

## 5. Java Classes & Connection Design

We have the following table to show the
dependence of each class on others and provide a quicker view of the methods
implementations

| Class                       | Description                                                                                                                             |
|:----------------------------|:----------------------------------------------------------------------------------------------------------------------------------------|
| ConnectSQL.java             | manages all the databases connection and return result from the query                                                                   |
| Main.java                   | manages the main() function; we use this class to start the application process                                                         |
| frmBooking.java             | manages the BOOKING frame and logic for buttons inside it for reservation booking functions                                             |
| frmExplorer.java            | manages the EXPLORER frame and logic for buttons inside it for admin query functions                                                    |
| frmIndex.java               | manages the INDEX frame and logic for buttons inside it for signup/login functions. This returns values to use in other methods as well |
| frmPatientDashboard.java    | manages the PATIENT DASHBOARD frame and logic for buttons inside it for patient users.                                                  |
| frmPatientSign.java         | manages the PATIENT SIGNUP frame and logic for buttons inside it for patient registration.                                              |
| frmRoles.java               | manages the ROLES frame and logic for buttons inside it for the role chooser function.                                                  |
| frmSignDone.java            | manages the SIGNUP DONE frame and logic for buttons inside it to take the user back to the index frame.                                 |
| frmSpecialistDashboard.java | manages the SPECIALIST DASHBOARD frame and logic for buttons inside it for specialist users.                                            |
| frmSpecialistSign.java      | manages the SPECIALIST SIGNUP frame and logic for buttons inside it for specialist registration.                                        |
| frmTest.java                | manages the TEST frame and logic for buttons inside it for patients to take tests in the system.                                        |

Then the connection string would be like this:

```java
import java.sql.*;

import net.proteanit.sql.DbUtils;

static final String connectionUrl=
        "jdbc:sqlserver://sql.bsite.net\\MSSQL2016;databaseName=congbang0711_;user=con gbang0711_;password=***;encrypt=true;trustServerCertificate=true;";
```

And also we implement some buttons. As many buttons need to be implemented, we will only show some common
patterns for this button. Note that all buttons have the logic to validate user input (blank,
not long enough, duplicate, etc..) and follow the SwingWorker design pattern:

| Button name                                                                | Action description                                                                       |
|:---------------------------------------------------------------------------|:-----------------------------------------------------------------------------------------|
| Log in, sign up buttons                                                    | use user input (account, password) for authentication and perform the subsequent actions |
| Run the query, Search buttons                                              | to run the query and return the result to the interface                                  |
| Place a booking, confirm registering, and Post booking information buttons | to put the user input into the database                                                  |
| Patient, Specialist                                                        | Place healing Mode, Explorer Mode, and Self-test                                         |
| Mode buttons                                                               | to set them according to the visible frame and dispose of the current frame              |
| Cancel healing, and delist healing buttons                                 | for users to cancel any reservations they made                                           |
| Answer A, B, C, and D buttons                                              | to show answer content and accompany the user when doing tests                           |
| Clear all button                                                           | to clear all the fields after confirming dialog                                          |
| Reset password button                                                      | to the reset password function                                                           |
| Refresh button                                                             | to refresh the results if the system fails to do so                                      |
| Log out button                                                             | to log out of the application and end that program session                               |
| Go back button                                                             | to go back to the previous frame                                                         |

<!-- INSTALLATION -->

# INSTALLATION

### Required software

* Java Development Kit (i.e. OpenJDK) [CLICK TO DOWNLOAD](https://openjdk.org/)
* Any Java IDE (i.e. JetBrains IntelliJ IDEA) [CLICK TO DOWNLOAD](https://www.jetbrains.com/idea/download/)

### Steps

1. Clone the repo
   ```sh
   git clone https://github.com/itzmealvin/PDM_mindfulNESS_PC.git
   ```
2. Open in a Java IDE
3. Choose **mindfulNESS.java** and click RUN to start the program. Please note that this is a version for educational
   purpose only, so **DO NOT ENTER YOUR REAL CREDENTIALS ON OUR DATABASE**

<!-- DEMO RESULT -->

# DEMO - RESULT

Sample of the program screenshots that demo the project's current build:

- [x] The login portal when users' run the program

<div align="center">
<img src="image/loginPage.jpg" alt="">
</div>

- [x] The menu for role choosing

<div align ="center">
<img src="image/roleChoice.jpg" alt="Role choice">
</div>

- [x] The searching function in action

<div align ="center">
<img src="image/patientDashboardSearch.jpg" alt="Searching in Patient Dashboard">
</div>

- [x] The cancel booked healing session function

<div align ="center">
<img src="image/patientDashboardCancelHealing.jpg" alt="Cancel Healing in Patient Dashboard">
</div>

- [x] The self-diagnosis test portal

<div align ="center">
<img src="image/patientDashboardTest.jpg" alt="Take test in Patient Dashboard">
</div>

- [x] Testing in progress

<div align ="center">
<img src="image/testDemo.jpg" alt="Test demo">
</div>

- [x] The place a healing session function

<div align ="center">
<img src="image/patientDashboardPlaceHealing.jpg" alt="Place Healing in Patient Dashboard">
</div>

- [x] List of available healing session

<div align ="center">
<img src="image/bookHealing.jpg" alt="Book list">
</div>

- [x] The specialist dashboard with related features

<div align ="center">
<img src="image/specialistMode.jpg" alt="Specialist mode">
</div>

**(and more screenshots hidden for you to explore yourself in our application...)**

<!-- CONTRIBUTING -->

# CONTRIBUTING

Contributions are what make the open source community such an amazing place to learn, inspire, and create. Any
contributions you make are **greatly appreciated**.

If you have a suggestion that would make this better, please fork the repo and create a pull request. You can also
simply open an issue with the tag "enhancement".
Don't forget to give the project a star! Thanks again!

1. Fork the Project
2. Create your Feature Branch (`git checkout -b feature/AmazingFeature`)
3. Commit your Changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the Branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

<!-- CONTACT-->

# CONTACT

Nguyen Hoang Anh Tu by **[Email HERE](mailto:ITDSIU20090@student.hcmiu.edu.vn)**

Project Link: [https://github.com/itzmealvin/PDM_mindfulNESS_PC](https://github.com/itzmealvin/PDM_mindfulNESS_PC)

<!-- ACKNOWLEDGMENTS -->

# ACKNOWLEDGEMENTS

We want to express our sincerest thanks to our lecturer and the people who have helped us to achieve this project's
goals:

- []()    Assoc. Prof. Nguyen Thi Thuy Loan
- []()    MSc. Nguyen Quang Phu
- []()    The README.md template from **[othneildrew](https://github.com/othneildrew/Best-README-Template)**

<!-- MARKDOWN LINKS & IMAGES -->

[contributors-shield]: https://img.shields.io/github/contributors/itzmealvin/PDM_mindfulNESS_PC.svg?style=for-the-badge

[contributors-url]: https://github.com/itzmealvin/PDM_mindfulNESS_PC/graphs/contributors

[forks-shield]: https://img.shields.io/github/forks/itzmealvin/PDM_mindfulNESS_PC.svg?style=for-the-badge

[forks-url]: https://github.com/itzmealvin/PDM_mindfulNESS_PC/network/members

[issues-shield]: https://img.shields.io/github/issues/itzmealvin/PDM_mindfulNESS_PC.svg?style=for-the-badge

[issues-url]: https://github.com/itzmealvin/PDM_mindfulNESS_PC/issues


