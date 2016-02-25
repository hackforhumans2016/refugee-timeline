
/* Create the database tables */
CREATE TABLE Task (
	id INTEGER PRIMARY KEY NOT NULL,
	parentTaskRef INTEGER,
	FOREIGN KEY (parentTaskRef) REFERENCES Task(id)
);

CREATE TABLE Step (
	id INTEGER PRIMARY KEY NOT NULL,
	taskRef INTEGER NOT NULL,
	isFinal INTEGER NOT NULL,
	conditions TEXT,
	FOREIGN KEY (taskRef) REFERENCES Task(id)
);

CREATE TABLE TaskLocalization (
	taskRef INTEGER NOT NULL,
	langCode TEXT NOT NULL,
	title TEXT NOT NULL,
	description TEXT NOT NULL,
	PRIMARY KEY (taskRef, langCode),
	FOREIGN KEY (taskRef) REFERENCES Task(id)
);

CREATE TABLE GoalLocalization (
	taskRef INTEGER NOT NULL,
	langCode TEXT NOT NULL,
	title TEXT NOT NULL,
	PRIMARY KEY (taskRef, langCode),
	FOREIGN KEY (taskRef) REFERENCES Task(id)	
);

CREATE TABLE StepLocalization (
	stepRef INTEGER NOT NULL,
	langCode TEXT NOT NULL,
	title TEXT NOT NULL,
	PRIMARY KEY (stepRef, langCode),
	FOREIGN KEY (stepRef) REFERENCES Step(id)
);

CREATE TABLE Date (
	id INTEGER PRIMARY KEY NOT NULL,
	stepRef INTEGER NOT NULL,
	date DATE NOT NULL,
	FOREIGN KEY (stepRef) REFERENCES Step(id)
);

CREATE TABLE DateLocalization (
	dateRef INTEGER NOT NULL,
	langCode TEXT NOT NULL,
	title TEXT NOT NULL,
	PRIMARY KEY (dateRef, langCode),
	FOREIGN KEY (dateRef) REFERENCES Date(id)
);

CREATE TABLE Condition (
	id INTEGER PRIMARY KEY NOT NULL,
	type TEXT NOT NULL,
	operation TEXT NOT NULL,
	name TEXT NOT NULL,
	value TEXT
);


/* Fill the tables with example data */

INSERT INTO Task VALUES
(1, NULL),
(2, NULL),
(3, NULL),
(4, NULL),
(5, NULL),
(6, NULL),
(7, NULL),
(8, NULL),
(9, NULL),
(10, NULL),
(11, NULL),
(12, NULL);

INSERT INTO GoalLocalization VALUES
(5, "en", "Residence permission"),
(10, "en", "Language course"),
(12, "en", "Education/Studies"),
(9, "en", "Job"),
(8, "en", "Family reunification");

INSERT INTO TaskLocalization VALUES 
(1, "en", "Arrival to Germany", "Welcome to Germany! This App is intended to help you as a refugee to organise your steps towards achieving your goals: Get a residence permit and apply for asylum to be allowed to stay in Germany, participate in a language course, manage to get residence permits for your partner and children to reunite your family, prepare to apply for a job or to study...<br><b>In order to select the correct future steps, this App needs some information from you: Please use the settings menu and enter your nationality and age.</b>"),
(2, "en", "Preliminary Registration", "Get in contact with the authorities, e.g. the police. They will send you to the nearest reception center, where you receive a document entitled &quot;Bescheinigung &uuml;ber die Meldung als Asylsuchender - B&Uuml;MA&quot; (Confirmation of having reported as an asylum seeker)."),
(3, "en", "Assignment to a federal state", "After your preliminary registration, you will live at the initial reception center until you are assigned a federal state within Germany (you cannot choose it): This is where you will live in the future (at least until your asylum application will be processed). Your are sent to an accommodation centre or emergency shelter in this state, which will take care of your daily needs, accommodation and health care.<br><b>In order to select the correct future steps, please use the settings menu an enter your assigned federal state, as well as your city, if it is available from the drop-down menu (this will ensure that you receive localised information if available).</b>"),
(4, "en", "Asylum Application", "Once you arrived in your assigned federal state, you need to go to the &quot;federal office for migration and refugees&quot; (Bundesamt f&uuml;r Migration und Fl&uuml;chtlinge - BAMF) and submit your asylum application in person. You will need to bring your personal documents (passport, birth certificate, etc.) and will receive a document called &quot;Aufenthaltsgestattung zur Durchf&uuml;hrung des Asylverfahrens&quot; which identifies you as an asylum seeker. This means that you are now a legal resident of Germany until your asylum application is processed.<br>According to a scheme called &quot;Dublin Regulation&quot;, your asylum application will be processed and a decision will be made whether you may stay in Germany or another country within Europe. It can also happen that your asylum application is not granted, in which case you have the right to take legal action."),
(5, "en", "Asylum granted", "Your asylum application is granted and you are assigned a European country of residence. If this is Germany, you are now the owner of an official document, the &quot;residence permit&quot;. Regulations differ in other European countries."),
(6, "en", "Application at Embassy in your Home Country", "You already have a residence title. If you also have sufficient living space and a secured means of subsistence, it is possible for your family members to join you.<br>Your spouse/children need to apply in the German Embassy or Consulate in your home country for a residence title for the purpose of family reunification for Germany. Since this process can take some time, please familiarise yourself with the documents needed for the application in good time and make an early application.<br><a href=""http://www.bamf.de/EN/Migration/Arbeiten/Familiennachzug/familiennachzug-node.html"">Federal Office for Migration and Refugees: Family reunification</a>"),
(7, "en", "Registration of your Family in Germany", "After having received the residence title for the purpose of family reunification in Germany, your family can travel to Germany. Once arrived in Germany, register your family members with the residence registration office and with the competent immigrant authority (Ausl&auml;nderbeh&ouml;rde). In order to do so, you need to collect the passports, birth certificates and wedding certificates, salary or tax certificates as well as evidence of tenancy and possibly other documents, depending on your specific family circumstances.<br><a href=""http://www.bamf.de/EN/Migration/Arbeiten/Familiennachzug/familiennachzug-node.html"">Federal Office for Migration and Refugees: Family reunification</a>"),
(8, "en", "Family reunification", "Now you and your family have residence permits which allow you to stay in Germany."),
(9, "en", "Apply for Jobs", "You are allowed to apply for a job three months after your application for a residence permit, or right away after your received it.  However, your chances are not very good at first, because you will only get a job if no German or European Union citizen is in the running for the same position (this is called &quot;Vorrangpr&uuml;fung&quot;).<br>Once you are in Germany for 15 months, this test is no more needed. But, just like before, you have to get the approval of the municipal immigration office before accepting a job offer. After four years, there are no more restrictions.<br>The local office of the &quot;Federal Employment Agency&quot; (Bundesagentur f&uuml;r Arbeit) will help you to identify suitable job offers."),
(10, "en", "Start your language course", "CITY SPECIFIC (Fallback entries on federal state/national level)(Unconfirmed example:) Language courses at Osnabr&uuml;ck University of Applied Sciences start regularly every first Monday of a month."),
(11, "en", "C1 language test", "Finish your language course at C1 level. (For most degrees in German, a refugee has to be able to speak German at the C1 level, according to the widely-recognized CEF language scale.)"),
(12, "en", "Start/Resume your education or studies", "In most German states, refugees have a right to study. The conditions of study do differ from state to state though.  In addition, the applicant needs to have an approved school leaving certificate. Some universities make an exception for refugees however, or they just let them take part in lectures as guests. It is important for refugees to inform themselves of the types of offers that are available for asylum seekers wanting to study in their region.<br>In general, semesters at German universities start twice per year. When received the confirmation of eligibility for your studies, information should be contained about where and when to enroll at university and start your studies.");

INSERT INTO Condition VALUES
(1, "Task", "isDone", 1, NULL),
(2, "Task", "isDone", 2, NULL),
(3, "Task", "isDone", 3, NULL),
(4, "Task", "isDone", 4, NULL),
(5, "Task", "isDone", 5, NULL),
(6, "Task", "isDone", 6, NULL),
(7, "Task", "isDone", 7, NULL),
(8, "Task", "isDone", 10, NULL),
(9, "Task", "isDone", 11, NULL),
(10, "Task", "durationDone", 2, 50),
(11, "Task", "durationDone", 3, 120),
(12, "Task", "durationDone", 5, 100);

INSERT INTO Step VALUES
(1, 1, 1, NULL),
(2, 2, 1, "1"),
(3, 3, 1, "2"),
(4, 4, 1, "3 & 10"),
(5, 5, 1, "4 & 11"),
(6, 6, 1, "5"),
(7, 7, 1, "6 & 12"),
(8, 8, 1, "7"),
(9, 9, 1, "5"),
(10, 10, 1, "3"),
(11, 11, 1, "8"),
(12, 12, 1, "9");

INSERT INTO DATE VALUES
(1, 10, 2016-03-07),
(2, 10, 2016-04-04),
(3, 10, 2016-05-02),
(4, 10, 2016-06-06),
(5, 10, 2016-07-04),
(6, 10, 2016-08-08),
(7, 10, 2016-09-05),
(8, 10, 2016-10-03),
(9, 10, 2016-11-07),
(10, 10, 2016-12-05),
(11, 12, 2016-04-01),
(12, 12, 2016-10-01);
