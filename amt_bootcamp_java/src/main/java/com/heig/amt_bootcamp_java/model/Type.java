package com.heig.amt_bootcamp_java.model;

import java.util.Objects;

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
   
   @Override
   public boolean equals(Object o) {

      if (o == this) return true;
      if (!(o instanceof Type)) {
         return false;
      }
      Type type = (Type) o;

      return Objects.equals(name, type.name);
   }

   @Override
   public int hashCode() {
      int hash = 7;
      hash = 29 * hash + Objects.hashCode(this.name);
      return hash;
   }

}
