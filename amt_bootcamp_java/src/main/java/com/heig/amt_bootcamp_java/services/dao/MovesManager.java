package com.heig.amt_bootcamp_java.services.dao;

import com.heig.amt_bootcamp_java.model.Move;
import java.sql.Connection;
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
}
