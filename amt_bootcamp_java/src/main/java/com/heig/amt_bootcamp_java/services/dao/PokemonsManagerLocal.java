package com.heig.amt_bootcamp_java.services.dao;

import com.heig.amt_bootcamp_java.model.Pokemon;
import java.util.List;
import javax.ejb.Local;

@Local
public interface PokemonsManagerLocal {
   public List<Pokemon> findAllPokemons();
}
