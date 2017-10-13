package com.heig.amt_bootcamp_java.services.dao;

import com.heig.amt_bootcamp_java.model.Type;
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
 * This class manages types. (CRUD and more)
 * 
 * @author Mathieu Monteverde, Sathiya Kirushnapillai
 */
@Stateless
public class TypesManager implements TypesManagerLocal {
   
   @Resource(lookup = "jdbc/bootcamp")
   private DataSource dataSource;
   
   @Override
   public List<Type> findAll() {

      List<Type> result = new ArrayList<>();
      
      try (
         Connection connection = dataSource.getConnection()
      ) 
      {
         // Get all pokemons without join
         ResultSet typeRows = 
            connection
               .prepareStatement("CALL findAllPokemons()")
               .executeQuery();
                  
         // For each pokemon
         while(typeRows.next()) {
            
            // Create pokemon
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
}
