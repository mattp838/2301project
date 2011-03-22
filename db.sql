-- script to create "Video Rental Store" database
-- version 1

-- questions: some names you used are reserved words i think, like 'transaction', 'rowid' -Peter

-- clear tables
DROP TABLE CUSTOMER CASCADE CONSTRAINTS;
DROP TABLE EMPLOYEE CASCADE CONSTRAINTS;
DROP TABLE SALES CASCADE CONSTRAINTS;
DROP TABLE VIDEOSALES CASCADE CONSTRAINTS;
DROP TABLE VIDEORENTALS CASCADE CONSTRAINTS;
DROP TABLE CURRENTLYRENTING CASCADE CONSTRAINTS;
DROP TABLE PROMOS CASCADE CONSTRAINTS;
DROP TABLE TRANSACTION CASCADE CONSTRAINTS;
DROP TABLE VIDEO CASCADE CONSTRAINTS;
DROP TABLE INVOICE CASCADE CONSTRAINTS;


-- create the tables
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
	CONSTRAINT employee_empID_pk PRIMARY KEY (empID)
);


CREATE TABLE sales
(
	rowID NUMBER(6),
	title VARCHAR2(30),
	videoID NUMBER(6),
	dateTime DATE,
	CONSTRAINT sales_rowID_pk PRIMARY KEY (rowID)
);


CREATE TABLE videoSales
(
	saleID NUMBER(6),
	videoID NUMBER(6),
	price NUMBER(6),
	CONSTRAINT videoSales_saleID_pk PRIMARY KEY (saleID)
);

CREATE TABLE videoRentals
(
	rentalID NUMBER(6),
	price NUMBER(6),
	status VARCHAR2(10),
	videoID NUMBER(6),
	CONSTRAINT videoRentals_rentalID_pk PRIMARY KEY (rentalID)
);


CREATE TABLE currentlyRenting
(
	accountID NUMBER(6),
	videoID NUMBER(6),
	dateTime DATE,
	returned VARCHAR(10),
);

CREATE TABLE promos
(
	promoID NUMBER(6),
	description VARCHAR2(30),
	deduction NUMBER(6),
	tagname VARCHAR2(30),
	CONSTRAINT promos_promoID_pk PRIMARY KEY (promoID)
);

CREATE TABLE transaction
(
	rowID NUMBER(6),
	promoID NUMBER(6),
	invoiceID NUMBER(6),
	saleID NUMBER(6),
	rentalID NUMBER(6),
	quality VARCHAR2(30),
	price NUMBER(6)
);

CREATE TABLE video
(
	sku NUMBER(11),
	title VARCHAR2(30),
	genre VARCHAR2(30),
	synopsis VARCHAR2(300),
	medium VARCHAR2(30),
	category VARCHAR2(30),
	actors VARCHAR2(30),
	language VARCHAR2(30),
	director VARCHAR2(30),
	CONSTRAINT video_sku_pk PRIMARY KEY (sku)
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
