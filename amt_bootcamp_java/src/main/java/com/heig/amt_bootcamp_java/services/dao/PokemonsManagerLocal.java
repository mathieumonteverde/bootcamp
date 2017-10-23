package com.heig.amt_bootcamp_java.services.dao;

import com.heig.amt_bootcamp_java.exceptions.IntegrityConstraintViolation;
import com.heig.amt_bootcamp_java.model.Pokemon;
import java.util.List;
import javax.ejb.Local;

@Local
public interface PokemonsManagerLocal {
   
   /**
    * Add new pokemons
    * @param pokemon The pokemon to add
    */
   public void add(Pokemon pokemon) throws IntegrityConstraintViolation ;
   
   /**
    * Add new pokemons
    * @param pokemons The pokemons to add
    */
   public void add(List<Pokemon> pokemons) throws IntegrityConstraintViolation ;
   
   /**
    * Count the number of pokemons
    * @return The number of pokemons
    */
   public int count();
   
   /**
    * Return true if the pokemon exists
    * @param no to find
    * @return true if the pokemon exists
    */
   public boolean exists(int no);
   
   /**
    * Return true if the pokemon exists
    * @param name to find
    * @return true if the pokemon exists
    */
   public boolean exists(String name);
   
   /**
    * Returns a list of all pokemons
    * @return a list of all pokemons
    */
   public List<Pokemon> findAll(int limit, int offset);
   
   /**
    * Find pokemon by his name
    * @param name Name to search
    * @return Returns the found pokemon. Returns null if not found.
    */
   public Pokemon findByName(String name);
   
   /**
    * Find pokemon by his name
    * @param no No to search
    * @return Returns the found pokemon. Returns null if not found.
    */
   public Pokemon findByNo(int no);
   
   /**
    * Generate random pokemon
    * 
    * @param nbPokemon Number of pokemon to generate
    * @param maxTypesPerPoke Max types per pokemon
    * @param maxMovesPerPoke Max moves per pokemon
    */
   public void generatePokemons(int nbPokemon, int maxTypesPerPoke, int maxMovesPerPoke);
    
   /**
    * Delete pokemons
    */
   public void deleteAll();
   
   /**
    * Delete a pokemon by his no
    * @param no The no that identifies the pokemon
    */
   public void deleteByNo(int no);
   
   /**
    * Update a pokemon
    * @param p Pokemon to update
    */
   public void update(Pokemon p);
}
