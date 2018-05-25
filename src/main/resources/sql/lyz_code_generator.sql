drop database if exists lyz_code_generator;
create database lyz_code_generator;
use lyz_code_generator;

create table clc_bonuses (empid BigInt null ,userid BigInt null ,reason varchar(256) null ,bonus_balance varchar(256) null ,description text null ,bonus_name varchar(256) null ,active bool null ,id BigInt not null auto_increment,primary key (id));
create table clc_clock_records (empid BigInt null ,userid BigInt null ,time_stamp varchar(256) null ,description text null ,clock_record_name varchar(256) null ,active bool null ,id BigInt not null auto_increment,primary key (id));
create table clc_employee_types (description text null ,employee_type_name varchar(256) null ,active bool null ,id BigInt not null auto_increment,primary key (id));
create table clc_fines (empid BigInt null ,userid BigInt null ,reason varchar(256) null ,fine_balance varchar(256) null ,description text null ,fine_name varchar(256) null ,active bool null ,id BigInt not null auto_increment,primary key (id));
create table clc_leaves (empid BigInt null ,userid BigInt null ,day varchar(256) null ,leave_type_id BigInt null ,description text null ,leave_name varchar(256) null ,active bool null ,id BigInt not null auto_increment,primary key (id));
create table clc_leave_lefts (empid BigInt null ,userid BigInt null ,annual_leave_left Integer null ,sick_leave_left Integer null ,private_leave_left Integer null ,other_leave_left Integer null ,year Integer null ,description text null ,leave_left_name varchar(256) null ,active bool null ,id BigInt not null auto_increment,primary key (id));
create table clc_leave_limits (employee_type_id BigInt null ,annual_leave_limit Integer null ,sick_leave_limit Integer null ,private_leave_limit Integer null ,other_leave_limit Integer null ,description text null ,leave_limit_name varchar(256) null ,active bool null ,id BigInt not null auto_increment,primary key (id));
create table clc_leave_types (unit_fine varchar(256) null ,description text null ,leave_type_name varchar(256) null ,active bool null ,id BigInt not null auto_increment,primary key (id));
create table clc_privileges (url varchar(256) null ,isadmin bool null ,can_delete bool null ,privilege_name varchar(256) null ,active bool null ,id BigInt not null auto_increment,primary key (id));
create table clc_users (empid BigInt null ,firstname varchar(256) null ,lastname varchar(256) null ,password varchar(256) null ,gender varchar(256) null ,isadmin bool null ,salt varchar(256) null ,loginfailure Integer null ,namec varchar(256) null ,address varchar(256) null ,address1 varchar(256) null ,phone varchar(256) null ,mobile varchar(256) null ,confirmpassword varchar(256) null ,username varchar(256) null ,active bool null ,id BigInt not null auto_increment,primary key (id));

