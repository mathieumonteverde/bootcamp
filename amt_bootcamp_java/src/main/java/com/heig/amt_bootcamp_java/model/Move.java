package com.heig.amt_bootcamp_java.model;

import java.util.Objects;

/**
 * The Move class is the model to store Move information. It provides the 
 * Move.
 * 
 * @author Mathieu Monteverde, Sathiya Kirushnapillai
 */
public class Move {
   
   private int id;
   private String name;
   
   public Move(int id, String name) {
      this.id = id;
      this.name = name;
   }
   
   public int getId() {
      return id;
   }
   
   public String getName() {
      return name;
   }
   
   @Override
   public boolean equals(Object o) {

      if (o == this) return true;
      if (!(o instanceof Move)) {
         return false;
      }
      Move move = (Move) o;

      return id == move.id && 
              Objects.equals(name, move.name);
   }

   @Override
   public int hashCode() {
      int hash = 7;
      hash = 67 * hash + this.id;
      hash = 67 * hash + Objects.hashCode(this.name);
      return hash;
   }
}
