package com.heig.amt_bootcamp_java.services.dao;

import com.heig.amt_bootcamp_java.model.Move;
import com.heig.amt_bootcamp_java.model.Pokemon;
import com.heig.amt_bootcamp_java.model.Type;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.sql.DataSource;

@Stateless
public class PokemonsManager implements PokemonsManagerLocal {
   
   @Resource(lookup = "jdbc/bootcamp")
   private DataSource dataSource;
   
   @Override
   public List<Pokemon> findAllPokemons() {
      
      List<Pokemon> result = new ArrayList<>();
      
      try (
         Connection connection = dataSource.getConnection()
      ) 
      {
         // Prepare and execute a query
         ResultSet pokemonRows = 
            connection
               .prepareStatement("CALL findAllPokemons()")
               .executeQuery();
                  
         // For each pokemon
         while(pokemonRows.next()) {
            
            // Get pokemon no
            int no = pokemonRows.getInt("No");

            // Get all moves
            PreparedStatement moveStatement = connection.prepareStatement("CALL findMovesByPokemon(?)");
            moveStatement.setInt(1, no);
            ResultSet moveRows = moveStatement.executeQuery();

            ArrayList<Move> moves = new ArrayList<>();
            while(moveRows.next()) {
               int id = moveRows.getInt("ID");
               String name = moveRows.getString("name");

               moves.add(new Move(id, name));
            }
            
            // Get all types
            PreparedStatement typeStatement = connection.prepareStatement("CALL findTypesByPokemon(?)");
            typeStatement.setInt(1, no);
            ResultSet typeRows = moveStatement.executeQuery();
            
            ArrayList<Type> types = new ArrayList<>();
            while(typeRows.next()) {
               String name = typeRows.getString("name");
               
               types.add(new Type(name));
            }
            
            
            // Create pokemon
            String name = pokemonRows.getString("Name");
            
            result.add(new Pokemon(no, name, moves, types));
         }
         
         
      } catch (SQLException ex) {
         Logger.getLogger(
            PokemonsManager.class.getName()).log(Level.SEVERE, null, ex
         );
      }
      
      return result;
   }
   
}
