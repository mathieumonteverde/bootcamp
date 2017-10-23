package com.heig.amt_bootcamp_java.services.dao;

import com.heig.amt_bootcamp_java.model.Move;
import java.util.List;
import javax.ejb.Local;

/**
 * This defines all actions possible in database for moves
 *
 * @author Mathieu Monteverde, Sathiya Kirushnapillai
 */
@Local
public interface MovesManagerLocal {
   
   /**
    * Return true if the move exists
    * @param id to find
    * @return true if the move exists
    */
   public boolean exists(int id);
   
   /**
    * Return true if the move exists
    * @param name to find
    * @return true if the move exists
    */
   public boolean exists(String name);
   
   /**
    * Returns a list of all moves
    * @return a list of all moves
    */
   public List<Move> findAll();
   
   /**
    * Find move by his name
    * @param name Name to search
    * @return Returns the found type. Returns null if not found.
    */
   public Move findByName(String name);
   
   /**
    * Find type by his id
    * @param id No to search
    * @return Returns the found type. Returns null if not found.
    */
   public Move findById(int id);
   
   /**
    * Delete types by pokemon no
    * @param no to search and delete
    */
   public void deleteByNo(int no);
}
