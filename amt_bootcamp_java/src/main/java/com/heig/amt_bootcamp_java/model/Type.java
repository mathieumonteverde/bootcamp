package com.heig.amt_bootcamp_java.model;

/**
 * The Type class is the model to store Type information. It provides the 
 * Type. Type enum to describe a Pokemon type(s).
 * 
 * @author Mathieu Monteverde, Sathiya Kirushnapillai
 */
public class Type {
   private String name;
   
   public Type(String name) {
      this.name = name;
   }
   
   public String getName() {
      return name;
   }
}
