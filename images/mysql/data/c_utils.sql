USE Bootcamp;

-- Find all pokemons
DELIMITER //
CREATE PROCEDURE findAllPokemons ()
BEGIN
  SELECT * FROM Pokemon;
END //

-- Find all types
DELIMITER //
CREATE PROCEDURE findAllTypes ()
BEGIN
  SELECT * FROM Type;
END //

-- Find all moves
DELIMITER //
CREATE PROCEDURE findAllMoves ()
BEGIN
  SELECT * FROM Move;
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

