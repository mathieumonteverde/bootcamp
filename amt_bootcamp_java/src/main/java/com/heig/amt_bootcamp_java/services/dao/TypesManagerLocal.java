package com.heig.amt_bootcamp_java.services.dao;

import com.heig.amt_bootcamp_java.model.Type;
import java.util.List;
import javax.ejb.Local;

@Local
public interface TypesManagerLocal {
   /**
    * Returns a list of all types
    * @return a list of all types
    */
   public List<Type> findAll();
}
