package com.heig.amt_bootcamp_java.services.dao;

import com.heig.amt_bootcamp_java.model.Move;
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
 * This class manages moves. (CRUD and more)
 * 
 * @author Mathieu Monteverde, Sathiya Kirushnapillai
 */
@Stateless
public class MovesManager implements MovesManagerLocal {
   
   @Resource(lookup = "jdbc/bootcamp")
   private DataSource dataSource;
   
   @Override
   public boolean exists(int id) {
      return findById(id) != null;
   }
   
   @Override
   public boolean exists(String name) {
      return findByName(name) != null;
   }
   
   @Override
   public List<Move> findAll() {
      List<Move> result = new ArrayList<>();
      
      try (
         Connection connection = dataSource.getConnection()
      ) 
      {
         // Get all moves without join
         ResultSet moveRows = 
            connection
               .prepareStatement("CALL findAllMoves()")
               .executeQuery();
                  
         // For each move
         while(moveRows.next()) {
            
            // Create move
            int id = moveRows.getInt("ID");
            String name = moveRows.getString("Name");
            result.add(new Move(id, name));
         }
         
      } catch (SQLException ex) {
         Logger.getLogger(
            MovesManager.class.getName()).log(Level.SEVERE, null, ex
         );
      }
      
      return result;
   }
   
   @Override
   public Move findByName(String name) {
      
      Move move = null;
      
      try (
         Connection connection = dataSource.getConnection()
      ) 
      {
         // Get the type
         PreparedStatement preparedStatement = 
            connection.prepareStatement("CALL findMoveByName(?)");
         preparedStatement.setString(1, name);
         ResultSet rows = preparedStatement.executeQuery();
         
         if(rows.next()) {
            move = new Move(rows.getInt("ID"), rows.getString("Name"));
         }
         
      } catch (SQLException ex) {
         Logger.getLogger(
            PokemonsManager.class.getName()).log(Level.SEVERE, null, ex
         );
      }

      return move;
   }
   
   @Override
   public Move findById(int id) {
      
      Move move = null;
      
      try (
         Connection connection = dataSource.getConnection()
      ) 
      {
         // Get the type
         PreparedStatement preparedStatement = 
            connection.prepareStatement("CALL findMoveById(?)");
         preparedStatement.setInt(1, id);
         ResultSet rows = preparedStatement.executeQuery();
         
         if(rows.next()) {
            move = new Move(rows.getInt("ID"), rows.getString("Name"));
         }
         
      } catch (SQLException ex) {
         Logger.getLogger(
            PokemonsManager.class.getName()).log(Level.SEVERE, null, ex
         );
      }

      return move;
   }
   
   @Override
   public void deleteByNo(int no) {
      try (
         Connection connection = dataSource.getConnection()
      ) 
      {
         PreparedStatement preparedStatement = 
            connection.prepareStatement("CALL deleteTypesOfPokemon(?)");
         preparedStatement.setInt(1, no);
         preparedStatement.executeQuery();
      } catch (SQLException ex) {
         Logger.getLogger(
            PokemonsManager.class.getName()).log(Level.SEVERE, null, ex
         );
      }
   }
}
