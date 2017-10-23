package com.heig.amt_bootcamp_java.model;

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
}
