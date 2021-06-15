use FLex
--++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++--
/*
drop table [User]
drop table Student
drop table GraduateStudent
drop table UndergraduateStudent
drop table Faculty
drop table VisitingFaculty
drop table PermanentFaculty
drop table Course
drop table CourseResult
drop table CourseAssistant
drop table QuizMarksList
drop table Attendances
drop table InstructorsTeachingTheCourse
drop table StudentsTakingTheCourse
drop table FeeChallan
drop table Semester
drop table CourseList
drop table ListOfCoursesTeachedByTeacher
drop table NotificationsForStudents
drop Procedure StudentLogin
drop procedure getStudentInfo
drop Procedure FacultyLogin
drop procedure getFacultyInfo
drop procedure addAttendance
drop procedure add_course
drop procedure drop_course
*/

--++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++--

Create table [User]
(
	Email varchar(50),
	ContactNo varchar(50),
	[Address] varchar(100),
	Name varchar(50),
	Blood_group varchar(3),
	Gender varchar(50),
	Nationality varchar(50),
	Campus varchar(50),
	[Password] varchar(50) not null,

	Primary key(Email)
)


--++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++--

Create table Student	--Child class of User class
(
	Email varchar(50),	--Child class of User so have to import Email from user
	RollNumber varchar(50),
	CGPA float,
	--yearjoined

	Primary key(RollNumber),
	Foreign key(Email) References [User](Email)
)



--++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++--


Create table GraduateStudent	--Child class of Student class
(
	RollNumber varchar(50),	--Importing RollNo which is key of Primary class
	ResearchThesis varchar(50),

	Foreign key(RollNumber) References Student(RollNumber)
)


--++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++--


Create table UndergraduateStudent	--Child class of Student class
(
	RollNumber varchar(50),	--Importing RollNo which is key of Primary class
	FYP varchar(50),

	Foreign key(RollNumber) References Student(RollNumber)
)

--++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++--


--drop table Faculty
Create table Faculty	--Child class of User class
(
	Email varchar(50),	--Child class of User so have to import Email from user
	FacID int,

	Primary key(FacID),
	Foreign key(Email) References [User](Email)
)

Select FacID From Faculty Where Email='profx@xmen.com'
go

--++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++--

Create Table PermanentFaculty
(
	salaryPerSemester int,	
	facID int,

	primary key (facID),
	foreign key (facID) references Faculty(FacID),

)
--++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++--

Create table VisitingFaculty
(
	salaryPerLecture int,
	facID int,

	primary key (facID),
	foreign key (facID) references Faculty(FacID),
)
--++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++--
--Total Courses in Uni
create table CourseList
(
	Code varchar(50),
	Name varchar(50),
	CreditHours int,
	Type varchar(50)

	Primary key(Code),
)
--Select * from Course
--select * from CourseList

insert into CourseList 
values ('CS201','Data Structures',3,'Core')
go

insert into CourseList 
values ('CS203','Database Systems',3,'Core'),('EE213','Assembly',3,'Core')
go

insert into CourseList
values ('CL205','Operating Systems Lab',1,'Core'),('CS205','Operating System',3,'Core'),('CS211','Discrete Structures',3,'Core '),('MG108','Fundamentals of Accounting',3,'Elective'),('MG211','Total Quality Management',3,'Elective'),('CS302','Design and Ananlysis of algorithm',3,'Core'),('MT203','Differential Equations',3,'Core'),('MG414','Entrepreneurship',3,'Elective'),('SS104','French Language',3,'Elective'),('SS118','Phsycology',3,'Elective'),('SS127','Sociology',3,'Elective'),('SS206','Organizational Behaviour',3,'Elective'),('CS301','Theory of Automata',3,'Core'),('CS323','Computer Networks',3,'Core'),('CL323','Computer Networks Lab',1,'Core')
go

--drop table Course
Create table Course
(
	Code varchar(50),
	Name varchar(50),
	CreditHours int

	Primary key(Code),
)


--++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++--

Create Table CourseResult
(
	RollNumber varchar(50),
	Code varchar(50),

	GPA float,
	midMarks float,
	finalMarks float,

	primary key(RollNumber,Code),
	Foreign key (Code) References Course(Code),
)

--++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++--

Create Table CourseAssistant
(
	Code varchar(50), --CourseCode
	facID int,
	TARollNum varchar(50),

	primary key(facID,Code),
	foreign key (facID) references Faculty(FacID),
	foreign key (TARollNum) references Student(RollNumber),
)
--++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++--

Create Table QuizMarksList
(
	
	RollNumber varchar(50),
	Code varchar(50),

	marksQ1 float,
	marksQ2 float,
	marksQ3 float,

	primary Key(RollNumber,Code),
	Foreign key (Code) references Course(Code),

)

--++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++--




Create Table Attendances
(
	Code varchar(50),
	RollNumber varchar(50),
	LectureDate Date, --YYYY-MM-DD
	attendanceStatus char(2),

	primary key(Code,LectureDate,RollNumber),
	Foreign key(Code) references Course(Code),
)


--++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++--



Create table InstructorsTeachingTheCourse	--To save a list of intructors teaching the course
(
	Code varchar(50),	
	Instructor int,

	Foreign key(Code) References Course(Code),
	Foreign key(Instructor) References Faculty(FacID)
)


--++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++--


Create table StudentsTakingTheCourse	--To save the list of students taking the course
(
	Code varchar(50),
	Student varchar(50),
	
	Foreign key(Code) References CourseList(Code),
	Foreign key(Student) References Student(RollNumber)
)



--++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++--




--++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++--

Create table Semester
(
	ID varchar(50),
	[Year] int,
	CourseOffered varchar(50),
	--due date
	Primary key(ID,CourseOffered),
	foreign key (CourseOffered) references Course(Code),
)


go
--++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++--

--++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++--


Create Table ListOfCoursesTeachedByTeacher
(
	Code varchar(50),
	FacID int,

	

	primary key (Code,FacID),
	Foreign Key (FacID) references Faculty(FacID),
)

--++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++--
go
create Table FeeChallan(
RollNumber varchar(50) primary key,
FeeStatus varchar(50),
DueDate date 
)
insert into FeeChallan values ('L171111','','')
insert into FeeChallan values ('L171234','','')
insert into FeeChallan values ('L174321','','')
go

Select * from FeeChallan
update FeeChallan
set FeeStatus = 'Pending'
update FeeChallan
set DueDate = '2020-2-15'
--++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++--
go
Create Table NotificationsForStudents(
	
	notifID int identity(1,1) not null,
	RollNumber varchar(50),
	Notification varchar(50),

	primary key(notifID)
)
go
Select*From NotificationsForStudents
go
Delete From NotificationsForStudents where RollNumber='L174321'
--Insert into NotificationsForStudents values('L174321','Wish I could love you')
--++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++--

Select * From [User]
go
Select * from Student
go
Select * From GraduateStudent
go
Select*from UndergraduateStudent
go
Select * from Faculty
go
Select* From PermanentFaculty
go
Select * From VisitingFaculty
go
Select * from Course
go
Select * From CourseResult
go
Select * From CourseAssistant
go
Select* From QuizMarksList
go
Select*From Attendances
go
Select * from InstructorsTeachingTheCourse
go
Select * from StudentsTakingTheCourse
go
Select * from Semester
go
Select* From CourseList
go
Select * From ListOfCoursesTeachedByTeacher
go

Select * from InstructorsTeachingTheCourse
where Instructor=2
go
--++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++--
go
----------------------Procedures-------------------------------------------
Create Procedure StudentLogin
(
	@Email varchar(50), 
	@Password varchar(16),
	@returnCheck int OUT
)
As
Begin
	If Exists
	(
		Select * 
		From [User]
		Where [User].Email = @Email and [User].[Password] = @Password
	)
	Begin
		If Exists (Select * From Student Where Student.Email = @Email)
		Begin
			Set @returnCheck = 1
		End
		Else
		Begin
			Set @returnCheck = 0
		End
	End
	
	Else
	Begin
		Set @returnCheck = 0
	End
End


go
Declare @checkValue int
Exec StudentLogin 'withapencil@continental.com', 'Killerwithapencil', @checkValue output
Select @checkValue
go


Select * from Student
Select * from [User]

go
-----------------------------------------------------------------------------------------------------------------------------
Create procedure getStudentInfo 
(
	@Email varchar(50)
)
As
Begin
	Select [User].*,Student.RollNumber,Student.CGPA
	From [User]
	Left join Student ON ([User].Email = Student.Email)
	Where [User].Email = @Email
End


go

Execute getStudentInfo 'darkknight@gotham.com'

go
-----------------------------------------------------------------------------------------------------------------------------
Create Procedure FacultyLogin
(
	@Email varchar(50), 
	@Password varchar(16),
	@returnCheck int OUT
)
As
Begin
	If Exists
	(
		Select * 
		From [User]
		Where [User].Email = @Email and [User].[Password] = @Password
	)
	Begin
		If Exists (Select * From Faculty Where Faculty.Email = @Email)
		Begin
			Set @returnCheck = 1
		End
		Else
		Begin
			Set @returnCheck = 0
		End
	End
	
	Else
	Begin
		Set @returnCheck = 0
	End
End


go

Declare @checkValue int
Exec FacultyLogin 'claws@xmen.com', 'angriboi', @checkValue output
Select @checkValue
go

Select * From [User]

go

-----------------------------------------------------------------------------------------------------------------------------

Create procedure getFacultyInfo 
(
	@Email varchar(50)
)
As
Begin
	Select [User].*,Faculty.FacID
	From [User]
	Inner join Faculty ON ([User].Email = Faculty.Email)
	Where [User].Email = @Email
End


go
Execute getFacultyInfo 'claws@xmen.com'



--++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++--

--select distinct* from StudentsTakingTheCourse
--where StudentsTakingTheCourse.Student = 'L171111'

--select StudentsTakingTheCourse.Student,StudentsTakingTheCourse.Code,Course.Name,course.CreditHours,sum(Course.CreditHours)
--from StudentsTakingTheCourse 
--join Course on (StudentsTakingTheCourse.Code = Course.Code)
--where StudentsTakingTheCourse.Student = 'L171111'
--order by StudentsTakingTheCourse.Student



-- query to calculate fee for student
--select * from Student
--Select StudentsTakingTheCourse.Student,sum(Course.CreditHours)*17000 as TotalFee 
--from StudentsTakingTheCourse
--join Course on (StudentsTakingTheCourse.Code =Course.Code)
--where StudentsTakingTheCourse.Student ='L171111'
--group by StudentsTakingTheCourse.Student

--Hassaan's Use Case--
go
Create procedure addAttendance 
	@code varchar(20),
	@rollno varchar(20),
	@date Date,
	@status varchar(2), 
	@result int output
as
begin
	if exists(select * 
			  from Attendances
			  where LectureDate=@date and RollNumber=@rollno and Code=@code)
	begin
		set @result=0
	end

	else
	begin
		insert into Attendances
		values(@code,@rollno,@date,@status)
		set @result=1

	end

end
go

--Omer's Use cases--
Create procedure add_course @code varchar(20), @roll varchar(20), @returnCheck int output 
as
begin
	set @returnCheck=0
	if exists(select code from CourseList where CourseList.Code = @code)
	begin			
		if exists (select * from StudentsTakingTheCourse where @roll=Student and Code = @code)
		begin
			set @returnCheck=2
		end
		else
		begin
			declare @crsName varchar(50)
			Select @crsName = Course.Name
			From Course
			Where Course.Code = @code

			declare @crdtHours int
			Select @crdtHours = CreditHours
			From CourseList
			Where CourseList.Code = @code
			
			insert into StudentsTakingTheCourse values(@code,@roll)
			insert into Course values (@code, @crsName, @crdtHours)

			set @returnCheck=1
		end
	end
	
end

go
Create proc Drop_course @roll varchar(50), @code varchar(20),@returnCheck int out
as
begin
	set @returnCheck=0
	if exists(select code from CourseList where Code = @code)
	begin			
		if exists (select * from StudentsTakingTheCourse where @roll=Student and @code=Code)
		begin
			set @returnCheck=1
			delete from StudentsTakingTheCourse where StudentsTakingTheCourse.Code=@code and StudentsTakingTheCourse.Student=@roll
		end
		else 
		begin
			set @returnCheck=2
		end		
	end	
end

