package com.heig.amt_bootcamp_java.model;

import java.util.List;

/**
 * @author Mathieu Monteverde, Sathiya Kirushnapillai
 * @file Pokemon.java
 * @date 20.09.2017
 * 
 * The Pokemon class is the model to store Pokemon information. It provides the 
 * Pokemon.Type enum to describe a Pokemon type(s).
 */
public class Pokemon {
   
   public static final int MAX_TYPES = 3;
   public static final int MAX_MOVES = 4;
   
   private int no;
   private String name;
   private List<Move> moves;
   private List<Type> types;

   public Pokemon(int no, String name, List<Move> moves, List<Type> types) {
      this.no = no;
      this.name = name;
      this.moves = moves;
      this.types = types;
   }
   
   public int getNo() {
      return no;
   }

   public String getName() {
      return name;
   }

   public List<Move> getMoves() {
      return moves;
   }

   public List<Type> getTypes() {
      return types;
   }
   
   public void setNo(int no) {
      this.no = no;
   }

   public void setName(String name) {
      this.name = name;
   }

   public void setMoves(List<Move> moves) {
      this.moves = moves;
   }

   public void setTypes(List<Type> types) {
      this.types = types;
   }
}
