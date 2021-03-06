package com.heig.amt_bootcamp_java.services.dao;

import com.heig.amt_bootcamp_java.model.Type;
import java.util.List;
import javax.ejb.Local;

/**
 * This defines all actions possible in database for types
 *
 * @author Mathieu Monteverde, Sathiya Kirushnapillai
 */
@Local
public interface TypesManagerLocal {
   
   /**
    * Return true if the type exists
    * @param name to find
    * @return true if the type exists
    */
   public boolean exists(String name);
   
   /**
    * Returns a list of all types
    * @return a list of all types
    */
   public List<Type> findAll();
   
   /**
    * Find type by name
    * @param name Name to find
    * @return Return the found type. Returns null if not found.
    */
   public Type findByName(String name);
   
   /**
    * Delete moves by pokemon no
    * @param no to search and delete
    */
   public void deleteByNo(int no);
}
