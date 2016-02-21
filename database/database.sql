-- this statements are only compatible with mysql
-- to use the statements with sqlite you have to delete the AUTO_INCREMENT instructions

CREATE TABLE state (
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    name TEXT
);

CREATE TABLE city (
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    name TEXT,
    state_id INTEGER NOT NULL,
    FOREIGN KEY (state_id) REFERENCES state(id)
);

CREATE TABLE task (
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    name TEXT,
    description TEXT,
    duration INTEGER,
    started INTEGER,
    finished INTEGER,
    city_id INTEGER,
    state_id INTEGER,
    task_id_predecessor INTEGER,
    FOREIGN KEY (city_id) REFERENCES city(id),
    FOREIGN KEY (state_id) REFERENCES state(id),
    FOREIGN KEY (task_id_predecessor) REFERENCES task(id)
);

CREATE TABLE task_date (
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    task_date TEXT,
    task_id INTEGER,
    FOREIGN KEY (task_id) REFERENCES task(id)
);

CREATE TABLE goal (
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    name TEXT,
    task_id INTEGER NOT NULL,
    FOREIGN KEY (task_id) REFERENCES task(id)
);

-- insert states of germany
INSERT INTO state (name) VALUES ('Baden-W&uuml;rttemberg');
INSERT INTO state (name) VALUES ('Bayern');
INSERT INTO state (name) VALUES ('Berlin');
INSERT INTO state (name) VALUES ('Brandenburg');
INSERT INTO state (name) VALUES ('Bremen');
INSERT INTO state (name) VALUES ('Hamburg');
INSERT INTO state (name) VALUES ('Hessen');
INSERT INTO state (name) VALUES ('Mecklenburg-Vorpommern');
INSERT INTO state (name) VALUES ('Niedersachsen');
INSERT INTO state (name) VALUES ('Nordrhein-Westfalen');
INSERT INTO state (name) VALUES ('Rheinland-Pfalz');
INSERT INTO state (name) VALUES ('Saarland');
INSERT INTO state (name) VALUES ('Sachsen');
INSERT INTO state (name) VALUES ('Sachsen-Anhalt');
INSERT INTO state (name) VALUES ('Schleswig-Holstein');
INSERT INTO state (name) VALUES ('Th&uuml;ringen');

-- insert some test cities
INSERT INTO city (name, state_id) VALUES ('Osnabr&uuml;ck', (SELECT id FROM state WHERE name = 'Niedersachsen'));
INSERT INTO city (name, state_id) VALUES ('M&uuml;nster', (SELECT id FROM state WHERE name = 'Nordrhein-Westfalen'));
INSERT INTO city (name, state_id) VALUES ('Bremen', (SELECT id FROM state WHERE name = 'Bremen'));
INSERT INTO city (name, state_id) VALUES ('Berlin', (SELECT id FROM state WHERE name = 'Berlin'));
INSERT INTO city (name, state_id) VALUES ('Hamburg', (SELECT id FROM state WHERE name = 'Hamburg'));
INSERT INTO city (name, state_id) VALUES ('Hannover', (SELECT id FROM state WHERE name = 'Niedersachsen'));
INSERT INTO city (name, state_id) VALUES ('M&uuml;nchen', (SELECT id FROM state WHERE name = 'Bayern'));

-- insert some test tasks
INSERT INTO task (name, description, duration, city_id, state_id, task_id_predecessor)
    VALUES (
        'Arrival to Germany',
        'Welcome to Germany! This App is intended to help you as a refugee to organise your steps towards achieving your goals: Get a residence permit and apply for asylum to be allowed to stay in Germany, participate in a language course, manage to get residence permits for your partner and children to reunite your family, prepare to apply for a job or to study...<br><b>In order to select the correct future steps, this App needs some information from you: Please use the settings menu and enter your nationality and age.</b>',
        NULL,
        NULL,
        NULL,
        NULL);


INSERT INTO task (name, description, duration, city_id, state_id, task_id_predecessor)
    VALUES (
        'Preliminary Registration',
        'Get in contact with the authorities, e.g. the police. They will send you to the nearest reception center, where you receive a document entitled &quot;Bescheinigung &uuml;ber die Meldung als Asylsuchender - B&Uuml;MA&quot; (Confirmation of having reported as an asylum seeker).',
        NULL,
        NULL,
        NULL,
        1);

INSERT INTO task (name, description, duration, city_id, state_id, task_id_predecessor)
    VALUES (
        'Assignment to a federal state',
        'After your preliminary registration, you will live at the initial reception center until you are assigned a federal state within Germany (you cannot choose it): This is where you will live in the future (at least until your asylum application will be processed). Your are sent to an accommodation centre or emergency shelter in this state, which will take care of your daily needs, accommodation and health care.<br><b>In order to select the correct future steps, please use the settings menu an enter your assigned federal state, as well as your city, if it is available from the drop-down menu (this will ensure that you receive localised information if available).</b>',
        50,
        NULL,
        NULL,
        2);

INSERT INTO task (name, description, duration, city_id, state_id, task_id_predecessor)
    VALUES (
        'Asylum Application',
        'Once you arrived in your assigned federal state, you need to go to the &quot;federal office for migration and refugees&quot; (Bundesamt f&uuml;r Migration und Fl&uuml;chtlinge - BAMF) and submit your asylum application in person. You will need to bring your personal documents (passport, birth certificate, etc.) and will receive a document called &quot;Aufenthaltsgestattung zur Durchf&uuml;hrung des Asylverfahrens&quot; which identifies you as an asylum seeker. This means that you are now a legal resident of Germany until your asylum application is processed.<br>According to a scheme called &quot;Dublin Regulation&quot;, your asylum application will be processed and a decision will be made whether you may stay in Germany or another country within Europe. It can also happen that your asylum application is not granted, in which case you have the right to take legal action.',
        120,
        NULL,
        NULL,
        3);

INSERT INTO task (name, description, duration, city_id, state_id, task_id_predecessor)
    VALUES (
        'Asylum granted (GOAL)',
        'Your asylum application is granted and you are assigned a European country of residence. If this is Germany, you are now the owner of an official document, the &quot;residence permit&quot;. Regulations differ in other European countries.',
        NULL,
        NULL,
        NULL,
        4);

INSERT INTO task (name, description, duration, city_id, state_id, task_id_predecessor)
    VALUES (
        'Application at Embassy in your Home Country',
        'You already have a residence title. If you also have sufficient living space and a secured means of subsistence, it is possible for your family members to join you.<br>Your spouse/children need to apply in the German Embassy or Consulate in your home country for a residence title for the purpose of family reunification for Germany. Since this process can take some time, please familiarise yourself with the documents needed for the application in good time and make an early application.<br><a href="http://www.bamf.de/EN/Migration/Arbeiten/Familiennachzug/familiennachzug-node.html">Federal Office for Migration and Refugees: Family reunification</a>',
        100,
        NULL,
        NULL,
        5);

INSERT INTO task (name, description, duration, city_id, state_id, task_id_predecessor)
    VALUES (
        'Registration of your Family in Germany',
        'After having received the residence title for the purpose of family reunification in Germany, your family can travel to Germany. Once arrived in Germany, register your family members with the residence registration office and with the competent immigrant authority (Ausl&auml;nderbeh&ouml;rde). In order to do so, you need to collect the passports, birth certificates and wedding certificates, salary or tax certificates as well as evidence of tenancy and possibly other documents, depending on your specific family circumstances.<br><a href="http://www.bamf.de/EN/Migration/Arbeiten/Familiennachzug/familiennachzug-node.html">Federal Office for Migration and Refugees: Family reunification</a>',
        NULL,
        NULL,
        NULL,
        6);

INSERT INTO task (name, description, duration, city_id, state_id, task_id_predecessor)
    VALUES (
        'Family reunification (GOAL)',
        'Now you and your family have residence permits which allow you to stay in Germany.',
        NULL,
        NULL,
        NULL,
        7);

INSERT INTO task (name, description, duration, city_id, state_id, task_id_predecessor)
    VALUES (
        'Apply for Jobs (GOAL)',
        'You are allowed to apply for a job three months after your application for a residence permit, or right away after your received it.  However, your chances are not very good at first, because you will only get a job if no German or European Union citizen is in the running for the same position (this is called &quot;Vorrangpr&uuml;fung&quot;).<br>Once you are in Germany for 15 months, this test is no more needed. But, just like before, you have to get the approval of the municipal immigration office before accepting a job offer. After four years, there are no more restrictions.<br>The local office of the &quot;Federal Employment Agency&quot; (Bundesagentur f&uuml;r Arbeit) will help you to identify suitable job offers.',
        NULL,
        NULL,
        NULL,
        5);

INSERT INTO task (name, description, duration, city_id, state_id, task_id_predecessor)
    VALUES (
        'Start your language course (GOAL)',
        'CITY SPECIFIC (Fallback entries on federal state/national level)(Unconfirmed example:) Language courses at Osnabr&uuml;ck University of Applied Sciences start regularly every first Monday of a month.',
        NULL,
        NULL,
        NULL,
        3);

INSERT INTO task_date (task_date, task_id) VALUES ('2016-03-07', 10);
INSERT INTO task_date (task_date, task_id) VALUES ('2016-04-04', 10);
INSERT INTO task_date (task_date, task_id) VALUES ('2016-05-02', 10);
INSERT INTO task_date (task_date, task_id) VALUES ('2016-06-06', 10);
INSERT INTO task_date (task_date, task_id) VALUES ('2016-07-04', 10);
INSERT INTO task_date (task_date, task_id) VALUES ('2016-08-08', 10);
INSERT INTO task_date (task_date, task_id) VALUES ('2016-09-05', 10);
INSERT INTO task_date (task_date, task_id) VALUES ('2016-10-03', 10);
INSERT INTO task_date (task_date, task_id) VALUES ('2016-11-07', 10);
INSERT INTO task_date (task_date, task_id) VALUES ('2016-12-05', 10);

INSERT INTO task (name, description, duration, city_id, state_id, task_id_predecessor)
    VALUES (
        'C1 language test',
        'Finish your language course at C1 level. (For most degrees in German, a refugee has to be able to speak German at the C1 level, according to the widely-recognized CEF language scale.)',
        NULL,
        NULL,
        NULL,
        10);

INSERT INTO task (name, description, duration, city_id, state_id, task_id_predecessor)
    VALUES (
        'Start/Resume your education or studies (GOAL)',
        'In most German states, refugees have a right to study. The conditions of study do differ from state to state though.  In addition, the applicant needs to have an approved school leaving certificate. Some universities make an exception for refugees however, or they just let them take part in lectures as guests. It is important for refugees to inform themselves of the types of offers that are available for asylum seekers wanting to study in their region.<br>
In general, semesters at German universities start twice per year. When received the confirmation of eligibility for your studies, information should be contained about where and when to enroll at university and start your studies.',
        NULL,
        NULL,
        NULL,
        11);

INSERT INTO task_date (task_date, task_id) VALUES ('2016-04-01', 12);
INSERT INTO task_date (task_date, task_id) VALUES ('2016-10-01', 12);

INSERT INTO goal (name, task_id) VALUES ('Residence Permit', 5);
INSERT INTO goal (name, task_id) VALUES ('Language Course', 10);
INSERT INTO goal (name, task_id) VALUES ('Education/Studies', 12);
INSERT INTO goal (name, task_id) VALUES ('Job', 9);
INSERT INTO goal (name, task_id) VALUES ('Family Reunification', 8);