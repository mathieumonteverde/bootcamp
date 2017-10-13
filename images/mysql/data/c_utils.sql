USE Bootcamp;

-- Find all pokemons
DELIMITER //
CREATE PROCEDURE findAllPokemons ()
BEGIN
  SELECT * FROM Pokemon;
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
