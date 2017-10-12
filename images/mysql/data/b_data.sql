USE Bootcamp;

-- Insert all pokemon types
INSERT INTO Type (Name) values ('Normal');
INSERT INTO Type (Name) values ('Fire');
INSERT INTO Type (Name) values ('Fighting');
INSERT INTO Type (Name) values ('Water');
INSERT INTO Type (Name) values ('Flying');
INSERT INTO Type (Name) values ('Grass');
INSERT INTO Type (Name) values ('Poison');
INSERT INTO Type (Name) values ('Electric');
INSERT INTO Type (Name) values ('Ground');
INSERT INTO Type (Name) values ('Psychic');
INSERT INTO Type (Name) values ('Rock');
INSERT INTO Type (Name) values ('Ice');
INSERT INTO Type (Name) values ('Bug');
INSERT INTO Type (Name) values ('Dragon');
INSERT INTO Type (Name) values ('Ghost');
INSERT INTO Type (Name) values ('Dark');
INSERT INTO Type (Name) values ('Steel');
INSERT INTO Type (Name) values ('Fairy');
INSERT INTO Type (Name) values ('???');





-- Insert pokemon moves
INSERT INTO Move (Name) values ('Tackle');
INSERT INTO Move (Name) values ('Growl');
INSERT INTO Move (Name) values ('Scratch');
INSERT INTO Move (Name) values ('Tail Whip');


-- Insert pokemons
INSERT INTO Pokemon (No, Name) values (1, 'Bulbasaur');
INSERT INTO Pokemon (No, Name) values (4, 'Charmander');
INSERT INTO Pokemon (No, Name) values (7, 'Squirtle');



-- Insert Bulbasaur types and moves
INSERT INTO Pokemon_Type (PokemonNo, TypeName) values (
  (SELECT No FROM Pokemon WHERE Name = 'Bulbasaur'), 
  'Grass'
);

INSERT INTO Pokemon_Type (PokemonNo, TypeName) values (
  (SELECT No FROM Pokemon WHERE Name = 'Bulbasaur'), 
  'Poison'
);

INSERT INTO Pokemon_Move (PokemonNo, MoveID) values (
  (SELECT No FROM Pokemon WHERE Name = 'Bulbasaur'), 
  (SELECT ID FROM Move WHERE Name = 'Tackle')
);

INSERT INTO Pokemon_Move (PokemonNo, MoveID) values (
  (SELECT No FROM Pokemon WHERE Name = 'Bulbasaur'), 
  (SELECT ID FROM Move WHERE Name = 'Growl')
);



-- Insert Charmander types and moves
INSERT INTO Pokemon_Type (PokemonNo, TypeName) values (
  (SELECT No FROM Pokemon WHERE Name = 'Charmander'), 
  'Fire'
);

INSERT INTO Pokemon_Move (PokemonNo, MoveID) values (
  (SELECT No FROM Pokemon WHERE Name = 'Charmander'), 
  (SELECT ID FROM Move WHERE Name = 'Growl')
);

INSERT INTO Pokemon_Move (PokemonNo, MoveID) values (
  (SELECT No FROM Pokemon WHERE Name = 'Charmander'), 
  (SELECT ID FROM Move WHERE Name = 'Scratch')
);


-- Insert Squirtle types and moves
INSERT INTO Pokemon_Type (PokemonNo, TypeName) values (
  (SELECT No FROM Pokemon WHERE Name = 'Squirtle'), 
  'Water'
);

INSERT INTO Pokemon_Move (PokemonNo, MoveID) values (
  (SELECT No FROM Pokemon WHERE Name = 'Squirtle'), 
  (SELECT ID FROM Move WHERE Name = 'Tackle')
);

INSERT INTO Pokemon_Move (PokemonNo, MoveID) values (
  (SELECT No FROM Pokemon WHERE Name = 'Squirtle'), 
  (SELECT ID FROM Move WHERE Name = 'Tail Whip')
);

