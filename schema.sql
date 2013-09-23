create table Users (
   userID      serial not null,
   firstname   varchar(50),
   lastname    varchar(50),
   username    varchar(50) not null unique,
   password    varchar(50) not null,
   address     varchar(100),
   yearOfBirth int,
   creditCard  varchar(19),
   confirmed   boolean default false,
   primary key (userID)
)