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
INSERT INTO Move (Name) values ('Pound');
INSERT INTO Move (Name) values ('Karate Chop*');
INSERT INTO Move (Name) values ('Double Slap');
INSERT INTO Move (Name) values ('Comet Punch');
INSERT INTO Move (Name) values ('Pay Day');
INSERT INTO Move (Name) values ('Fire Punch');
INSERT INTO Move (Name) values ('Ice Punch');
INSERT INTO Move (Name) values ('Thunder Punch');
INSERT INTO Move (Name) values ('Vice Grip');
INSERT INTO Move (Name) values ('Guillotine');
INSERT INTO Move (Name) values ('Razor Wind');
INSERT INTO Move (Name) values ('Swords Dance');
INSERT INTO Move (Name) values ('Cut');
INSERT INTO Move (Name) values ('Gust');
INSERT INTO Move (Name) values ('Wing Attack');
INSERT INTO Move (Name) values ('Whirlwind');
INSERT INTO Move (Name) values ('Fly');
INSERT INTO Move (Name) values ('Bind');
INSERT INTO Move (Name) values ('Slam');
INSERT INTO Move (Name) values ('Vine Whip');
INSERT INTO Move (Name) values ('Stomp');
INSERT INTO Move (Name) values ('Double Kick');
INSERT INTO Move (Name) values ('Mega Kick');
INSERT INTO Move (Name) values ('Jump Kick');
INSERT INTO Move (Name) values ('Rolling Kick');
INSERT INTO Move (Name) values ('Sand Attack');
INSERT INTO Move (Name) values ('Headbutt');
INSERT INTO Move (Name) values ('Horn Attack');
INSERT INTO Move (Name) values ('Fury Attack');
INSERT INTO Move (Name) values ('Sand Attack');
INSERT INTO Move (Name) values ('Aurora Beam');
INSERT INTO Move (Name) values ('Horn Drill');
INSERT INTO Move (Name) values ('Sand Attack');
INSERT INTO Move (Name) values ('Body Slam');
INSERT INTO Move (Name) values ('Wrap');
INSERT INTO Move (Name) values ('Take Down');
INSERT INTO Move (Name) values ('Thrash');
INSERT INTO Move (Name) values ('Double-Edge');
INSERT INTO Move (Name) values ('Poison Sting');
INSERT INTO Move (Name) values ('Twineedle');
INSERT INTO Move (Name) values ('Pin Missile');
INSERT INTO Move (Name) values ('Leer');
INSERT INTO Move (Name) values ('Bite');
INSERT INTO Move (Name) values ('Roar');
INSERT INTO Move (Name) values ('Sing');
INSERT INTO Move (Name) values ('Supersonic');
INSERT INTO Move (Name) values ('Sonic Boom');
INSERT INTO Move (Name) values ('Disable');
INSERT INTO Move (Name) values ('Acid');
INSERT INTO Move (Name) values ('Ember');
INSERT INTO Move (Name) values ('Flamethrower');
INSERT INTO Move (Name) values ('Mist');
INSERT INTO Move (Name) values ('Water Gun');
INSERT INTO Move (Name) values ('Hydro Pump');
INSERT INTO Move (Name) values ('Surf');
INSERT INTO Move (Name) values ('Ice Beam');
INSERT INTO Move (Name) values ('Blizzard');
INSERT INTO Move (Name) values ('Psybeam');
INSERT INTO Move (Name) values ('Bubble Beam');


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

