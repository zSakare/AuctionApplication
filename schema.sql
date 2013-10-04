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
   isAdmin     boolean,
   banned      boolean default false,
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
   endTime           bigint,
   primary key (auctionID)
);

create table Bids (
   bidID       serial not null,
   auction     integer references Auctions(auctionID),
   bidTime     bigint,
   bidPrice    float not null,
   primary key (bidID)
);

create table User_has_Bids (
   bidder   integer references Users(userID),
   bid      integer references Bids(bidID),
   primary key (bidder, bid)
);