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
  SELECT Move.ID, Move.Name FROM Pokemon
  INNER JOIN Pokemon_Move ON Pokemon_Move.PokemonNo = Pokemon.No
  INNER JOIN Move ON Pokemon_Move.MoveID = Move.ID
  WHERE Pokemon.No = pokemonNo;
END //

-- Find all types of a pokemon
DELIMITER //
CREATE PROCEDURE findTypesByPokemon (IN pokemonNo INT)
BEGIN
  SELECT Type.Name FROM Pokemon
  INNER JOIN Pokemon_Type ON Pokemon_Type.PokemonNo = Pokemon.No
  INNER JOIN Type ON Pokemon_Type.TypeName = Type.Name
  WHERE Pokemon.No = pokemonNo;
END //


-- Add pokemon
DELIMITER //
CREATE PROCEDURE addPokemon (IN pokemonNo INT)
BEGIN

END //


-- Delete a pokemon
DELIMITER //
CREATE PROCEDURE deletePokemon (IN pokemonNo INT)
BEGIN
  DELETE FROM Pokemon
  WHERE No = pokemonNo;
END //
