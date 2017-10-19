package com.heig.amt_bootcamp_java.services.dao;

import com.heig.amt_bootcamp_java.model.Move;
import java.util.List;
import javax.ejb.Local;

@Local
public interface MovesManagerLocal {
   /**
    * Returns a list of all moves
    * @return a list of all moves
    */
   public List<Move> findAll();
}
