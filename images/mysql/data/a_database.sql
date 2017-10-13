DROP DATABASE IF EXISTS Bootcamp;
CREATE DATABASE Bootcamp;
USE Bootcamp;

-- TYPE
CREATE TABLE Type (
  Name varchar(25) NOT NULL,
  
  PRIMARY KEY(Name)
);


-- MOVE
CREATE TABLE Move (
  ID    int             NOT NULL AUTO_INCREMENT,
  Name  varchar(25)    NOT NULL,
  
  PRIMARY KEY(ID)
);


-- POKEMON
CREATE TABLE Pokemon (
  No    int              NOT NULL,
  Name  varchar(25)     NOT NULL,
  
  PRIMARY KEY(No)
);


-- POKEMON_TYPE
CREATE TABLE Pokemon_Type (
  PokemonNo     int,
  TypeName      varchar(25),
  
  FOREIGN KEY (PokemonNo) REFERENCES Pokemon(No) ON DELETE CASCADE,
  FOREIGN KEY (TypeName) REFERENCES Type(Name) ON DELETE CASCADE
);


-- POKEMON_MOVE
CREATE TABLE Pokemon_Move (
  PokemonNo   int,
  MoveID      int,
  
  FOREIGN KEY (PokemonNo) REFERENCES Pokemon(No) ON DELETE CASCADE,
  FOREIGN KEY (MoveID) REFERENCES Move(ID) ON DELETE CASCADE
);

