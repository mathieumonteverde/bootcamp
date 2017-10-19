USE Bootcamp;

-- Count pokemon
DELIMITER //
CREATE PROCEDURE countPokemons ()
BEGIN
  SELECT Count(No) FROM Pokemon;
END //

-- Find all pokemons
DELIMITER //
CREATE PROCEDURE findAllPokemons (IN l INT, IN o INT)
BEGIN
  SELECT * FROM Pokemon 
  LIMIT l OFFSET o;
END //

-- Find pokemon by name
DELIMITER //
CREATE PROCEDURE findPokemonByName (IN n varchar(80))
BEGIN
  SELECT * FROM Pokemon 
  WHERE Name = n;
END //

-- Find pokemon by no
DELIMITER //
CREATE PROCEDURE findPokemonByNo (IN n INT)
BEGIN
  SELECT * FROM Pokemon 
  WHERE No = n;
END //

-- Find all types
DELIMITER //
CREATE PROCEDURE findAllTypes ()
BEGIN
  SELECT * FROM Type;
END //

-- Find type by name
DELIMITER //
CREATE PROCEDURE findTypeByName (IN n varchar(80))
BEGIN
  SELECT * FROM Type 
  WHERE Name = n;
END //

-- Find all moves
DELIMITER //
CREATE PROCEDURE findAllMoves ()
BEGIN
  SELECT * FROM Move;
END //

-- Find move by name
DELIMITER //
CREATE PROCEDURE findMoveByName (IN n varchar(80))
BEGIN
  SELECT * FROM Move 
  WHERE Name = n;
END //

-- Find move by id
DELIMITER //
CREATE PROCEDURE findMoveById (IN n INT)
BEGIN
  SELECT * FROM Move 
  WHERE ID = n;
END //

-- Find all moves of a pokemon
DELIMITER //
CREATE PROCEDURE findMovesByPokemon (IN pokemonNo INT)
BEGIN
  SELECT Move.ID, Move.Name FROM Move
  INNER JOIN Pokemon_Move ON Pokemon_Move.MoveID = Move.ID
  WHERE Pokemon_Move.PokemonNo = pokemonNo;
END //

-- Find all types of a pokemon
DELIMITER //
CREATE PROCEDURE findTypesByPokemon (IN pokemonNo INT)
BEGIN
  SELECT Type.Name FROM Type
  INNER JOIN Pokemon_Type ON Pokemon_Type.TypeName = Type.Name
  WHERE Pokemon_Type.PokemonNo = pokemonNo;
END //

-- Add pokemon
DELIMITER //
CREATE PROCEDURE addPokemon (IN no INT, IN name varchar(80))
BEGIN
  INSERT INTO Pokemon
  VALUES (no, name); 
END //

-- Add move to a pokemon
DELIMITER //
CREATE PROCEDURE addMoveToPokemon (IN pokemonNo INT, IN moveId INT)
BEGIN
  INSERT INTO Pokemon_Move
  VALUES (pokemonNo, moveId); 
END //

-- Add type to a pokemon
DELIMITER //
CREATE PROCEDURE addTypeToPokemon (IN pokemonNo INT, IN typeName varchar(80))
BEGIN
  INSERT INTO Pokemon_Type
  VALUES (pokemonNo, typeName); 
END //

-- Delete a pokemon
DELIMITER //
CREATE PROCEDURE deletePokemon (IN pokemonNo INT)
BEGIN
  DELETE FROM Pokemon
  WHERE No = pokemonNo;
END //

-- Delete all pokemon
DELIMITER //
CREATE PROCEDURE deleteAllPokemon ()
BEGIN
  DELETE FROM Pokemon;
END //









