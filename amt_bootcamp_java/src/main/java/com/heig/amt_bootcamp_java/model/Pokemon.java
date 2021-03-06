package com.heig.amt_bootcamp_java.model;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * The Pokemon class is the model to store Pokemon information. It provides the 
 * Pokemon.
 * 
 * @author Mathieu Monteverde, Sathiya Kirushnapillai
 */
public class Pokemon {
   
   public static final int MAX_TYPES = 3;
   public static final int MAX_MOVES = 4;
   
   //You must enter a positive number
   @DecimalMin("0")
   @NotNull(message = "No cannot be null")
   private int no;
   
   @NotNull(message = "Name cannot be null")
   @Size(min = 3, max = 80, message = "Name must be between 3 and 80 characters")
   private String name;
   
   @Size(min=1, max=Pokemon.MAX_MOVES, message="Must fill in one move at least and max " + Pokemon.MAX_MOVES)
   private List<Move> moves;
   
   @Size(min=1, max=Pokemon.MAX_TYPES, message="Must fill in one type at least and max " + Pokemon.MAX_TYPES)
   private List<Type> types;
   
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
      
      removeDouble(this.moves);
   }

   public void setTypes(List<Type> types) {
      this.types = types;
      
      removeDouble(this.types);
   }
   
   public <T> void removeDouble(List<T> list) {
      Set setItems = new LinkedHashSet(list);
      list.clear();
      list.addAll(setItems);
   }
}
