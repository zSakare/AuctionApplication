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

create table Auctions (
   auctionID         serial not null,
   creator           integer references Users(userID),
   itemName          varchar(50) not null,
   title             varchar(50) not null,
   category          text,
   picture           text,
   description       text,
   postage           text not null,
   reservePrice      float not null,
   biddingStartPrice float not null,
   biddingIncrement  float not null,
   endTime           int,
   primary key (auctionID)
);


