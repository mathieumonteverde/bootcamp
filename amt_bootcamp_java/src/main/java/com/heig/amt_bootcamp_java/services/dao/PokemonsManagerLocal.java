package com.heig.amt_bootcamp_java.services.dao;

import com.heig.amt_bootcamp_java.model.Pokemon;
import java.util.List;
import javax.ejb.Local;

@Local
public interface PokemonsManagerLocal {
   
   /**
    * Add new pokemons
    * @param pokemons The pokemons to add
    */
   public void add(List<Pokemon> pokemons);
   
   /**
    * Count the number of pokemons
    * @return The number of pokemons
    */
   public int count();
   
   /**
    * Returns a list of all pokemons
    * @return a list of all pokemons
    */
   public List<Pokemon> findAll(int limit, int offset);
   
   /**
    * Delete pokemons
    */
   public void deleteAll();
   
   /**
    * Delete a pokemon by his no
    * @param no The no that identifies the pokemon
    */
   public void deleteByNo(int no);
}
