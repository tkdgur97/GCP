create database library2;
use library2;

create table members(
id varchar(10) not null primary key,
pw varchar(10) not null,
name varchar(10),
tel varchar(20),
address varchar(50),
bookrenturret varchar(1000),
bookrentcumlative varchar(1000),
booklate varchar(1000),
rrn varchar(30)
);

create table book(
number varchar(300),
title varchar(300),
author varchar(300),
publisher varchar(300),
year varchar(300),
bill varchar(300),
rent varchar(10)
);