package com.heig.amt_bootcamp_java.model;

/**
 * @author Mathieu Monteverde, Sathiya Kirushnapillai
 * @file Pokemon.java
 * @date 13.10.2017
 * 
 * The Move class is the model to store Move information. It provides the 
 * Move.
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
