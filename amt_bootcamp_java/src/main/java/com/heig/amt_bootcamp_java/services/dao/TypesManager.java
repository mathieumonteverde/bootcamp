package com.heig.amt_bootcamp_java.services.dao;

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
 * This class manages types. (CRUD and more)
 * 
 * @author Mathieu Monteverde, Sathiya Kirushnapillai
 */
@Stateless
public class TypesManager implements TypesManagerLocal {
   
   @Resource(lookup = "jdbc/bootcamp")
   private DataSource dataSource;
   
   @Override
   public boolean exists(String name) {
      return findByName(name) != null;
   }
   
   @Override
   public List<Type> findAll() {

      List<Type> result = new ArrayList<>();
      
      try (
         Connection connection = dataSource.getConnection()
      ) 
      {
         // Get all type without join
         ResultSet typeRows = 
            connection
               .prepareStatement("CALL findAllTypes()")
               .executeQuery();
                  
         // For each type
         while(typeRows.next()) {
            
            // Create type
            String name = typeRows.getString("Name");
            result.add(new Type(name));
         }
         
      } catch (SQLException ex) {
         Logger.getLogger(
            TypesManager.class.getName()).log(Level.SEVERE, null, ex
         );
      }
      
      return result;
   }
   
   @Override
   public Type findByName(String name) {
      
      Type type = null;
      
      try (
         Connection connection = dataSource.getConnection()
      ) 
      {
         // Get the type
         PreparedStatement preparedStatement = 
            connection.prepareStatement("CALL findTypeByName(?)");
         preparedStatement.setString(1, name);
         ResultSet rows = preparedStatement.executeQuery();
         
         if(rows.next()) {
            type = new Type(rows.getString("Name"));
         }
         
      } catch (SQLException ex) {
         Logger.getLogger(
            TypesManager.class.getName()).log(Level.SEVERE, null, ex
         );
      }

      return type;
   }
   
   @Override
   public void deleteByNo(int no) {
      try (
         Connection connection = dataSource.getConnection()
      ) 
      {
         PreparedStatement preparedStatement = 
            connection.prepareStatement("CALL deleteMovesOfPokemon(?)");
         preparedStatement.setInt(1, no);
         preparedStatement.executeQuery();
      } catch (SQLException ex) {
         Logger.getLogger(
            TypesManager.class.getName()).log(Level.SEVERE, null, ex
         );
      }
   }
}
