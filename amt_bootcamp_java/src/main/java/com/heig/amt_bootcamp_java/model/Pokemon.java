package com.heig.amt_bootcamp_java.model;

/**
 * @author Mathieu Monteverde, Sathiya Kirushnapillai
 * @file Pokemon.java
 * @date 20.09.2017
 * 
 * The Pokemon class is the model to store Pokemon information. It provides the 
 * Pokemon.Type enum to describe a Pokemon type(s).
 */
public class Pokemon {
   
   public enum Type {
      NORMAL,
      FIRE,
      WATER, 
      ELECTRIC,
      GRASS, 
      ICE, 
      FIGHTING,
      POISON,
      GROUND,
      FLYING,
      PSYCHIC,
      BUG,
      ROCK,
      GHOST,
      DRAGON,
      DARK,
      STEEL, 
      FAIRY
   }

   private String name;
   private String[] moves;
   private Type[] types;

   public Pokemon(String name, String[] moves, Type[] types) {
      this.name = name;
      this.moves = moves;
      this.types = types;
   }

   public String getName() {
      return name;
   }

   public String[] getMoves() {
      return moves;
   }

   public Type[] getTypes() {
      return types;
   }

   public void setName(String name) {
      this.name = name;
   }

   public void setMoves(String[] moves) {
      this.moves = moves;
   }

   public void setTypes(Type[] types) {
      this.types = types;
   }
}
