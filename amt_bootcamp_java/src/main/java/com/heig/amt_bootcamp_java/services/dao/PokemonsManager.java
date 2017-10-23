package com.heig.amt_bootcamp_java.services.dao;

import com.heig.amt_bootcamp_java.exceptions.IntegrityConstraintViolation;
import com.heig.amt_bootcamp_java.model.Move;
import com.heig.amt_bootcamp_java.model.Pokemon;
import com.heig.amt_bootcamp_java.model.Type;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
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
   public void add(Pokemon pokemon) throws IntegrityConstraintViolation {
      add(Arrays.asList(pokemon));
   }
   
   @Override
   public void add(List<Pokemon> pokemons) throws IntegrityConstraintViolation {

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

      } 
      catch (SQLException ex) {
         if(ex.getSQLState().equals("23000")) {
            throw new IntegrityConstraintViolation(ex.getMessage());
         }
         else {
            Logger.getLogger(
               PokemonsManager.class.getName()).log(Level.SEVERE, null, ex
            );
         }
      }
   }
   
   @Override
   public int count() {
      try (
         Connection connection = dataSource.getConnection()
      ) 
      {
         // Get the number of pokemon
         ResultSet rows = 
            connection
               .prepareStatement("CALL countPokemons()")
               .executeQuery();
         
         if(rows.next()) {
            return rows.getInt(1);
         }
         
      } catch (SQLException ex) {
         Logger.getLogger(
            PokemonsManager.class.getName()).log(Level.SEVERE, null, ex
         );
      }
      
      return 0;
   }
   
   @Override
   public boolean exists(int no) {
      return findByNo(no) != null;
   }
   
   @Override
   public boolean exists(String name) {
      return findByName(name) != null;
   }
   
   @Override
   public List<Pokemon> findAll(int limit, int offset) {
      
      List<Pokemon> result = new ArrayList<>();
      
      try (
         Connection connection = dataSource.getConnection()
      ) 
      {
         // Get all pokemons without join
         PreparedStatement preparedStatement = 
            connection.prepareStatement("CALL findAllPokemons(?, ?)");
         preparedStatement.setInt(1, limit);
         preparedStatement.setInt(2, offset);
         ResultSet pokemonRows = preparedStatement.executeQuery();
                  
         // For each pokemon
         while(pokemonRows.next()) {
            result.add(getPokemon(connection, pokemonRows));
         }
         
      } catch (SQLException ex) {
         Logger.getLogger(
            PokemonsManager.class.getName()).log(Level.SEVERE, null, ex
         );
      }
      
      return result;
   }
   
   @Override
   public Pokemon findByName(String name) {
      
      Pokemon pokemon = null;
      
      try (
         Connection connection = dataSource.getConnection()
      ) 
      {
         // Get the pokemon
         PreparedStatement preparedStatement = 
            connection.prepareStatement("CALL findPokemonByName(?)");
         preparedStatement.setString(1, name);
         ResultSet rows = preparedStatement.executeQuery();
         
         if(rows.next()) {
            pokemon = getPokemon(connection, rows);
         }
         
      } catch (SQLException ex) {
         Logger.getLogger(
            PokemonsManager.class.getName()).log(Level.SEVERE, null, ex
         );
      }

      return pokemon;
   }
   
   @Override
   public Pokemon findByNo(int no) {
      
      Pokemon pokemon = null;
      
      try (
         Connection connection = dataSource.getConnection()
      ) 
      {
         // Get the pokemon
         PreparedStatement preparedStatement = 
            connection.prepareStatement("CALL findPokemonByNo(?)");
         preparedStatement.setInt(1, no);
         ResultSet rows = preparedStatement.executeQuery();
         
         if(rows.next()) {
            pokemon = getPokemon(connection, rows);
         }
      } catch (SQLException ex) {
         Logger.getLogger(
            PokemonsManager.class.getName()).log(Level.SEVERE, null, ex
         );
      }

      return pokemon;
   }
   
   @Override
   public void generatePokemons(int nbPokemon, int maxTypesPerPoke, int maxMovesPerPoke) {
      try (
         Connection connection = dataSource.getConnection()
      ) 
      {
         // Get the pokemon
         PreparedStatement preparedStatement = 
            connection.prepareStatement("CALL pokemonGenerator(?, ?, ?)");
         preparedStatement.setInt(1, nbPokemon);
         preparedStatement.setInt(2, maxTypesPerPoke);
         preparedStatement.setInt(3, maxMovesPerPoke);
         preparedStatement.executeQuery();
      } catch (SQLException ex) {
         Logger.getLogger(
            PokemonsManager.class.getName()).log(Level.SEVERE, null, ex
         );
      }
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
   
   @Override
   public void update(Pokemon pokemon) throws IntegrityConstraintViolation {
      try (
         Connection connection = dataSource.getConnection()
      ) 
      {
         connection.setAutoCommit(false);
         
         {
            PreparedStatement preparedStatement = 
               connection.prepareStatement("CALL updatePokemon(?, ?)");
            preparedStatement.setInt(1, pokemon.getNo());
            preparedStatement.setString(2, pokemon.getName());
            preparedStatement.executeUpdate();
         }
         
         {
            PreparedStatement preparedStatement = 
               connection.prepareStatement("CALL deleteTypesOfPokemon(?)");
            preparedStatement.setInt(1, pokemon.getNo());
            preparedStatement.executeUpdate();
         }
         
         {
            PreparedStatement preparedStatement = 
               connection.prepareStatement("CALL deleteMovesOfPokemon(?)");
            preparedStatement.setInt(1, pokemon.getNo());
            preparedStatement.executeUpdate();
         }
         
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
         
      } catch (SQLException ex) {
         
         if(ex.getSQLState().equals("23000")) {
            throw new IntegrityConstraintViolation(ex.getMessage());
         }
         else {
            Logger.getLogger(
               PokemonsManager.class.getName()).log(Level.SEVERE, null, ex
            );
         }
      }
   }
   
   /**
    * Get a pokemon (Method for refactor)
    * 
    * @param connection Database connection
    * @param pokemon Pokemon to get
    * @return The pokemon
    * @throws SQLException 
    */
   private Pokemon getPokemon(Connection connection, ResultSet pokemon) 
      throws SQLException 
   {
      // Get pokemon no
      int no = pokemon.getInt("No");

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
      
      String name = pokemon.getString("Name");
      
      Pokemon p = new Pokemon();
      p.setNo(no);
      p.setName(name);
      p.setMoves(moves);
      p.setTypes(types);
      
      return p;
   }
   
   @Override
   public List<Pokemon> search(String name, int limit, int offset) {
      List<Pokemon> result = new ArrayList<>();
      
      try (
         Connection connection = dataSource.getConnection()
      ) 
      {
         // Get all pokemons without join
         PreparedStatement preparedStatement = 
            connection.prepareStatement("CALL searchPokemon(?, ?, ?)");
         preparedStatement.setString(1, name);
         preparedStatement.setInt(2, limit);
         preparedStatement.setInt(3, offset);
         ResultSet pokemonRows = preparedStatement.executeQuery();
                  
         // For each pokemon
         while(pokemonRows.next()) {
            result.add(getPokemon(connection, pokemonRows));
         }
         
      } catch (SQLException ex) {
         Logger.getLogger(
            PokemonsManager.class.getName()).log(Level.SEVERE, null, ex
         );
      }
      
      return result;
   }
}
