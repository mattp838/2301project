-- script to create "Video Rental Store" database

-- todo items for kristan:
-- add employee name attribute to employee entity in er diagram
-- remove employeeID attribute from invoice entity
-- what is the sales entity for? aren't all the sales recorded in the invoice entity?

-- questions: some names you used are reserved words i think, like 'transaction', category -Peter


-- clear tables
DROP TABLE STOREINFO CASCADE CONSTRAINTS;
DROP TABLE CUSTOMER CASCADE CONSTRAINTS;
DROP TABLE EMPLOYEE CASCADE CONSTRAINTS;
DROP TABLE SALES CASCADE CONSTRAINTS;
DROP TABLE VIDEOSALES CASCADE CONSTRAINTS;
DROP TABLE VIDEORENTALS CASCADE CONSTRAINTS;

DROP TABLE PROMOS CASCADE CONSTRAINTS;
DROP TABLE TRANSACTION CASCADE CONSTRAINTS;
DROP TABLE VIDEO CASCADE CONSTRAINTS;
DROP TABLE INVOICE CASCADE CONSTRAINTS;


-- create the tables
CREATE TABLE storeInfo
{
	name VARCHAR2(30),
	address VARCHAR2(30),
	taxNumber NUMBER(20),
	phone NUMBER(10),
	CONSTRAINT storeInfo_name_pk PRIMARY KEY (name)
}

CREATE TABLE customer
(
	accountID NUMBER(9),
	fName VARCHAR2(30),
	lName VARCHAR2(30),
	address VARCHAR2(50),
	dlsin VARCHAR2(30),
	phone VARCHAR2(10),
	altPhone VARCHAR2(10),
	CONSTRAINT customer_accountID_pk PRIMARY KEY (accountID)
);

CREATE TABLE employee
(
	empID NUMBER(6),
	position VARCHAR2(30),
	name VARCHAR2(30),
	CONSTRAINT employee_empID_pk PRIMARY KEY (empID)
);







CREATE TABLE promos
(
	promoID NUMBER(6),
	tagname VARCHAR2(30),
	description VARCHAR2(30),
	deduction NUMBER(6),
	CONSTRAINT promos_promoID_pk PRIMARY KEY (promoID)
);

CREATE TABLE invoice
(
	invoiceID NUMBER(6),
	dateTime DATE,
	
	CONSTRAINT invoice_invoiceID_pk PRIMARY KEY (invoiceID)
);

CREATE TABLE videoInfo
(
	infoID NUMBER(11),
	title VARCHAR2(30),
	genre VARCHAR2(30),
	synopsis VARCHAR2(300),
	medium VARCHAR2(30),
	category VARCHAR2(30),
	actors VARCHAR2(30),
	language VARCHAR2(30),
	director VARCHAR2(30),
	rating VARCHAR2(10),
	-- length of the movie in minutes
	length NUMBER(11),
	videoReleaseDate DATE,
	-- is this needed? theatricalRelaseDate DATE,
	CONSTRAINT videoInfo_infoID_pk PRIMARY KEY (infoID)
);

CREATE TABLE physicalVideo
{
	SKU VARCHAR2(30),
	format VARCHAR2(30),
	condition VARCHAR2(30),
	-- rentOrSale VARCHAR2(30),
	CONSTRAINT physicalVideo_SKU_pk PRIMARY KEY (SKU)
}

CREATE TABLE videoSales
(
	saleID NUMBER(6),
	CONSTRAINT videoSales_saleID_pk PRIMARY KEY (saleID)
);

CREATE TABLE videoRental
(
	rentalID NUMBER(6),
	checkOutTime DATE,
	-- maybe we should have a boolean to determine if a video is in or out or just use the two dates to figure it out?
	-- if checkOutTime > checkInTime, then the video is rented out
	checkInTime DATE,
	
	CONSTRAINT videoRentals_rentalID_pk PRIMARY KEY (rentalID)
);

CREATE TABLE invoice
(
	invoiceID NUMBER(6),
	employeeID NUMBER(6),
	dateTime DATE
	CONSTRAINT invoice_invoiceID_pk PRIMARY KEY (invoiceID)
);


-- inserting sample data
INSERT INTO customer VALUES
(
	000000001,
	Bruce,
	Wayne,
	123 Main Street,
	999888777,
	6045551111,
	6045552222
);


COMMIT;
