package com.heig.amt_bootcamp_java.model;

/**
 * @author Mathieu Monteverde, Sathiya Kirushnapillai
 * @file Pokemon.java
 * @date 20.09.2017
 * 
 * The Type class is the model to store Type information. It provides the 
 * Type. Type enum to describe a Pokemon type(s).
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
