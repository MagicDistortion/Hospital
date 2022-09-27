SET DB_CLOSE_DELAY 10;


CREATE TABLE  "roles" (
  "id_roles" int unsigned NOT NULL AUTO_INCREMENT,
  "name" varchar(45) NOT NULL,
  PRIMARY KEY ("id_roles"),
  UNIQUE KEY  ("id_roles"),
  UNIQUE KEY ("name")
) ;

--
-- Dumping data for table "roles"
--


INSERT INTO "roles" VALUES (2,'doctor'),(3,'nurse'),(4,'patient'),(1,'sys_admin');

CREATE TABLE "users" (
  "id_users" int NOT NULL AUTO_INCREMENT,
  "create_time" datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  "surname" varchar(45) NOT NULL,
  "name" varchar(100) NOT NULL,
  "login" varchar(100) NOT NULL,
  "password" varchar(100) NOT NULL,
  "tel" varchar(50) NOT NULL,
  "date_of_birth" date NOT NULL,
  "id_roles" int unsigned DEFAULT NULL,
  PRIMARY KEY ("id_users"),
  UNIQUE KEY  ("id_users"),
  UNIQUE KEY  ("tel"),
  UNIQUE KEY  ("login"),
  CONSTRAINT "fk_users_roles" FOREIGN KEY ("id_roles") REFERENCES "roles" ("id_roles")
) ;

--
-- Dumping data for table "users"
--

INSERT INTO "users" VALUES (1,'2022-08-14 18:55:42','Сіс','Адмін','Admin','1750806','380661119922','2013-08-06',1),(28,'2022-07-25 16:37:40','Гарин','Петро','Petro01','1750806','380668934412','1966-12-11',2),(29,'2022-07-25 16:37:40','Мед','Сестра','Nurse','1750806','380508934412','1993-07-12',3),(30,'2022-07-25 16:37:40','Казаков','Андрій','kazakov','1750806','380661114412','1999-12-11',2),(31,'2022-07-25 16:37:40','Гаєва','Ганна','Haeva','1750806','380931234412','1993-07-12',3),(32,'2022-07-25 16:37:40','Мартинов','Сергій','martynov','1750806','380932894412','1993-07-12',3),(33,'2022-07-25 16:37:40','Сірко','Олександр','Sirko','1750806','380938754422','1993-07-12',4),(34,'2022-07-25 16:37:40','Шипаєв','Іван','ivan','1750806','380932252039','1993-07-12',1),(35,'2022-07-25 16:37:40','Храбров','Роман','roman','1750806','380502252039','1993-07-12',2),(79,'2022-07-31 18:37:14','Громов','Олег','Gromov','1750806','380662252039','1993-07-12',1),(82,'2022-07-31 18:53:14','Томіна','Ольга','tomina','1750806','380662251234','1991-03-10',4),(87,'2022-07-31 19:12:17','Чушкіна','Олеся','olesia','1750806','380669920454','2000-03-19',4),(96,'2022-07-31 19:54:23','Баранець','Олександр','Baranets','1750806','380662257426','1992-03-19',2),(97,'2022-07-31 20:12:14','Кузнєцов','Віктор','kuznecov777','1750806','380953468823','1993-07-12',3),(101,'2022-08-02 09:28:38','Спірідонова','Ольга','Spiridonova','1750806','380992228818','1990-03-10',2),(102,'2022-08-02 12:43:28','Заїка','Андрій','Zaika','1750806','380659894412','2010-03-10',4),(123,'2022-08-07 11:09:33','Бандера','Степан','Bandera','1750806','380662228877','1999-03-19',4),(124,'2022-08-08 14:40:29','Семенова','Вікторія','Semenova','1750806','380502279810','1988-10-10',2),(125,'2022-08-08 14:41:05','Сафронов','Олександр','safronov','1750806','380502279899','1972-10-10',4),(126,'2022-08-11 10:08:13','Агаркова','Марина','garkova','1750806','380662292299','1990-03-10',4),(128,'2022-08-14 14:58:11','Пацієнт','Нульовий','Patient','1750806','380668882299','1996-06-10',4),(129,'2022-08-21 12:05:11','Доктор','Перший','Doctor','1750806','380661112267','1995-07-11',2),(130,'2022-08-21 09:50:29','Барков','Сергій','barkov','1750806','380662299988','1994-10-02',4),(132,'2022-08-28 10:17:40','Баталов','Антон','batalov','1750806','380662229178','1987-03-10',1),(133,'2022-08-25 13:42:16','Соколов','Андрій','sokolov','1750806','380662298855','1994-02-10',2),(135,'2022-09-01 14:43:15','Баранець','Ганна','HaevaH','1750806','380661118822','1993-07-12',2);



-- Table structure for table "appointment"
--
CREATE TABLE "category" (
  "id" int NOT NULL AUTO_INCREMENT,
  "name" varchar(45) NOT NULL,
  PRIMARY KEY ("id"),
  UNIQUE KEY  ("id"),
  UNIQUE KEY  ("name")
) ;


--
-- Dumping data for table "category"
--

INSERT INTO "category" VALUES (8,'Лор'),(1,'Непризначено'),(7,'Окуліст'),(5,'Педіатр'),(2,'Психолог'),(3,'Стоматолог'),(9,'Терапевт'),(6,'Травматолог'),(4,'Хірург');


--
-- Table structure for table "doctors"
--

CREATE TABLE "doctors" (
  "id" int NOT NULL,
  "number_of_patients" int unsigned NOT NULL DEFAULT '0',
  "category_id" int NOT NULL DEFAULT '1',
  PRIMARY KEY ("id"),
  UNIQUE KEY  ("id"),
  KEY "fk_doctors_category_idx" ("category_id"),
  CONSTRAINT "doctors_ibfk_1" FOREIGN KEY ("id") REFERENCES "users" ("id_users") ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT "fk_doctors_category" FOREIGN KEY ("category_id") REFERENCES "category" ("id") ON DELETE CASCADE ON UPDATE CASCADE
) ;

--
-- Dumping data for table "doctors"
--

INSERT INTO "doctors" VALUES (28,5,2),(30,3,3),(35,3,6),(96,5,5),(101,12,3),(124,16,6),(129,1,9),(133,0,5),(135,9,1);


--
-- Table structure for table "hospital_card"
--



--
-- Table structure for table "nurse"
--

CREATE TABLE "nurse" (
  "id" int NOT NULL,
  PRIMARY KEY ("id"),
  UNIQUE KEY  ("id"),
  CONSTRAINT "nurse_ibfk_1" FOREIGN KEY ("id") REFERENCES "users" ("id_users") ON DELETE CASCADE ON UPDATE CASCADE
);

--
-- Dumping data for table "nurse"
--

INSERT INTO "nurse" VALUES (29),(31),(32),(33),(97),(102);

--
-- Table structure for table "patients"
--

CREATE TABLE "patients" (
  "id" int NOT NULL,
  PRIMARY KEY ("id"),
  UNIQUE KEY  ("id"),
  CONSTRAINT "patients_ibfk_1" FOREIGN KEY ("id") REFERENCES "users" ("id_users") ON DELETE CASCADE ON UPDATE CASCADE
) ;

--
-- Dumping data for table "patients"
--

INSERT INTO "patients" VALUES (33),(82),(87),(102),(123),(125),(126),(128),(130);

CREATE TABLE "hospital_card" (
  "id_card" int NOT NULL,
  "diagnosis" varchar(1024) DEFAULT NULL,
  "current_doctor_id" int DEFAULT NULL,
  "status" varchar(45) NOT NULL DEFAULT 'newbee',
  PRIMARY KEY ("id_card"),
  UNIQUE KEY  ("id_card"),
  KEY "fk_hospital_card_doctors1_idx" ("current_doctor_id"),
  CONSTRAINT "fk_hospital_card_doctors1" FOREIGN KEY ("current_doctor_id") REFERENCES "doctors" ("id") ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT "fk_hospital_card_patient" FOREIGN KEY ("id_card") REFERENCES "patients" ("id") ON DELETE CASCADE ON UPDATE CASCADE
) ;

--
-- Dumping data for table "hospital_card"
--

INSERT INTO "hospital_card" VALUES (33,'Гострий апендицит',124,'is being treated'),(82,'лікувати чи не лікувати',NULL,'newbee'),(87,'Діарея',101,'newbee'),(102,'Нефроптоз',35,'newbee'),(123,'Панкреатит',30,'newbee'),(125,'Свинка',124,'newbee'),(126,'Сепсис',28,'newbee'),(128,'Цистит',124,'newbee'),(130,NULL,28,'newbee');


CREATE TABLE "appointment" (
  "id" int NOT NULL AUTO_INCREMENT,
  "name" varchar(45) NOT NULL,
  PRIMARY KEY ("id"),
  UNIQUE KEY  ("name"),
  UNIQUE KEY  ("id")
) ENGINE=InnoDB AUTO_INCREMENT=56 DEFAULT CHARSET=utf8mb3;

--
-- Dumping data for table "appointment"
--

INSERT INTO "appointment" VALUES (6,'Евтаназія'),(5,'Массаж'),(3,'Огляд'),(1,'Операція'),(7,'Пункція'),(2,'УЗІ'),(10,'Укол');


--
-- Table structure for table "appointment_detail"
--


CREATE TABLE "appointment_detail" (
  "id" int NOT NULL AUTO_INCREMENT,
  "text" varchar(1024) NOT NULL,
  "nurse_id" int DEFAULT NULL,
  "appointment_id" int NOT NULL,
  "doctors_id" int DEFAULT NULL,
  "hospital_card_id" int NOT NULL,
  "date" date NOT NULL,
  "status" varchar(45) NOT NULL DEFAULT 'waiting',
  PRIMARY KEY ("id"),
  KEY "nurse_id_idx" ("nurse_id"),
  KEY "fk_appoinment_detail_appoinment1_idx" ("appointment_id"),
  KEY "fk_appoinment_detail_doctors1_idx" ("doctors_id"),
  KEY "fk_hospital_card_id_idx" ("hospital_card_id"),
  CONSTRAINT "appointment_detail_ibfk_1" FOREIGN KEY ("hospital_card_id") REFERENCES "hospital_card" ("id_card") ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT "fk_appoinment_detail_appointment" FOREIGN KEY ("appointment_id") REFERENCES "appointment" ("id") ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT "fk_appoinment_detail_doctors1" FOREIGN KEY ("doctors_id") REFERENCES "doctors" ("id") ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT "nurse_id" FOREIGN KEY ("nurse_id") REFERENCES "nurse" ("id") ON DELETE SET NULL ON UPDATE CASCADE
);


--
-- Dumping data for table "appointment_detail"
--

INSERT INTO "appointment_detail" VALUES (1,'внутрішніх органів',29,2,28,33,'2022-08-23','done'),(2,'вирізати апендицит',31,1,30,33,'2023-09-25','waiting'),(4,'робити дуже обережно',97,7,101,125,'2022-08-25','waiting'),(7,'огляд верхньої частини тіла',29,3,28,82,'2022-09-01','done'),(8,'згода не потребується',29,6,28,82,'2022-08-22','waiting'),(9,'від скаліозу',31,5,28,82,'2022-08-21','waiting'),(10,'Саму велику голку',97,10,28,126,'2022-08-27','waiting'),(11,'обережно',29,7,28,82,'2022-09-30','done'),(12,'вирізати апендецит',32,1,129,87,'2022-09-06','in process'),(13,'нирки',33,2,129,33,'2022-09-07','waiting'),(16,'в попу',29,10,30,123,'2022-09-08','in process'),(17,'шлунку',31,3,30,123,'2022-09-14','in process'),(20,'жопи',29,5,30,123,'2022-09-09','done');


--
-- Table structure for table "category"
--




--
-- Table structure for table "roles"
--




--
-- Table structure for table "sys_admin"
--


CREATE TABLE "sys_admin" (
  "id" int NOT NULL,
  PRIMARY KEY ("id"),
  UNIQUE KEY  ("id"),
  CONSTRAINT "sys_admin_ibfk_1" FOREIGN KEY ("id") REFERENCES "users" ("id_users") ON DELETE CASCADE ON UPDATE CASCADE
) ;

--
-- Dumping data for table "sys_admin"
--

INSERT INTO "sys_admin" VALUES (1),(34),(79),(132);

--
-- Table structure for table "users"
--


