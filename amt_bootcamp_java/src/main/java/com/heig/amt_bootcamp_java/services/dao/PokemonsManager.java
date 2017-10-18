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

/**
 * This class manages pokemons. (CRUD and more)
 * 
 * @author Mathieu Monteverde, Sathiya Kirushnapillai
 */
@Stateless
public class PokemonsManager implements PokemonsManagerLocal {
   
   @Resource(lookup = "jdbc/bootcamp")
   private DataSource dataSource;
   
   
   @Override
   public void add(List<Pokemon> pokemons) {

      try (
         Connection connection = dataSource.getConnection()
      ) 
      {
         connection.setAutoCommit(false);
         
         // For each pokemon
         for(Pokemon pokemon : pokemons) {
            
            PreparedStatement addPokemon = 
               connection.prepareStatement("CALL addPokemon(?, ?)");
            addPokemon.setInt(1, pokemon.getNo());
            addPokemon.setString(2, pokemon.getName());
            addPokemon.executeQuery();
            
            // For each move
            for(Move m : pokemon.getMoves()) {
               PreparedStatement p = 
                  connection.prepareStatement("CALL addMoveToPokemon(?, ?)");
               p.setInt(1, pokemon.getNo());
               p.setInt(2, m.getId());
               p.executeQuery();
            }
            
            // For each type
            for(Type t : pokemon.getTypes()) {
               PreparedStatement p = 
                  connection.prepareStatement("CALL addTypeToPokemon(?, ?)");
               p.setInt(1, pokemon.getNo());
               p.setString(2, t.getName());
               p.executeQuery();
            }
            
            connection.commit();
         }
         
         
      } catch (SQLException ex) {
         Logger.getLogger(
            PokemonsManager.class.getName()).log(Level.SEVERE, null, ex
         );
      }
   }
   
   @Override
   public List<Pokemon> findAll() {
      
      List<Pokemon> result = new ArrayList<>();
      
      try (
         Connection connection = dataSource.getConnection()
      ) 
      {
         // Get all pokemons without join
         ResultSet pokemonRows = 
            connection
               .prepareStatement("CALL findAllPokemons()")
               .executeQuery();
                  
         // For each pokemon
         while(pokemonRows.next()) {
            
            // Get pokemon no
            int no = pokemonRows.getInt("No");

            // Get all moves of the pokemon
            PreparedStatement moveStatement = 
               connection.prepareStatement("CALL findMovesByPokemon(?)");
            moveStatement.setInt(1, no);
            ResultSet moveRows = moveStatement.executeQuery();

            ArrayList<Move> moves = new ArrayList<>();
            while(moveRows.next()) {
               int id = moveRows.getInt("ID");
               String name = moveRows.getString("name");

               moves.add(new Move(id, name));
            }
            
            // Get all types of the pokemon
            PreparedStatement typeStatement = 
               connection.prepareStatement("CALL findTypesByPokemon(?)");
            typeStatement.setInt(1, no);
            ResultSet typeRows = typeStatement.executeQuery();
            
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
   
   @Override
   public void deleteAll() {
      try (
         Connection connection = dataSource.getConnection()
      ) 
      {
         PreparedStatement preparedStatement = 
            connection.prepareStatement("CALL deleteAllPokemon()");
         preparedStatement.executeUpdate();
      } catch (SQLException ex) {
         Logger.getLogger(
            PokemonsManager.class.getName()).log(Level.SEVERE, null, ex
         );
      }
   }
   
   @Override
   public void deleteByNo(int no) {
      try (
         Connection connection = dataSource.getConnection()
      ) 
      {
         PreparedStatement preparedStatement = 
            connection.prepareStatement("CALL deletePokemon(?)");
         preparedStatement.setInt(1, no);
         preparedStatement.executeUpdate();
      } catch (SQLException ex) {
         Logger.getLogger(
            PokemonsManager.class.getName()).log(Level.SEVERE, null, ex
         );
      }
   }
}
