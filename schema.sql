create table Users (
   userID      serial not null,
   firstname   varchar(50),
   lastname    varchar(50),
   username    varchar(50) not null unique,
   password    varchar(50) not null,
   email       varchar(100) not null,
   address     varchar(100),
   yearOfBirth int,
   creditCard  varchar(19) not null,
   confirmed   boolean default false,
   primary key (userID)
);

create table Auction (
   auctionID         serial not null,
   itemName          varchar(50),
   title             varchar(50),
   category          text,
   picture           text,
   description       text,
   postage           text,
   reservePrice      float,
   biddingStartPrice float,
   biddingIncrement  float,
   endTime           int,
   primary key (auctionID)
);