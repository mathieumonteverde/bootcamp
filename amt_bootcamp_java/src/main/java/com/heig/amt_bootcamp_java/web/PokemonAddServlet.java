/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.heig.amt_bootcamp_java.web;

import com.heig.amt_bootcamp_java.model.Move;
import com.heig.amt_bootcamp_java.model.Pokemon;
import com.heig.amt_bootcamp_java.model.Type;
import com.heig.amt_bootcamp_java.services.dao.MovesManagerLocal;
import com.heig.amt_bootcamp_java.services.dao.PokemonsManagerLocal;
import com.heig.amt_bootcamp_java.services.dao.TypesManagerLocal;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author mathieu
 */
public class PokemonAddServlet extends HttpServlet {
   
   @EJB
   private PokemonsManagerLocal pokemonsManager;
   
   @EJB
   private MovesManagerLocal movesManager;
   
   @EJB
   private TypesManagerLocal typesManager;

   /**
    * Handles the HTTP <code>GET</code> method.
    *
    * @param request servlet request
    * @param response servlet response
    * @throws ServletException if a servlet-specific error occurs
    * @throws IOException if an I/O error occurs
    */
   @Override
   protected void doGet(HttpServletRequest request, HttpServletResponse response)
           throws ServletException, IOException {

      response.setContentType("text/html;charset=UTF-8");
      request.setAttribute("pokemonNo", "");
      request.setAttribute("pokemonName", "");
      request.setAttribute("types", typesManager.findAll());
      request.setAttribute("moves", movesManager.findAll());
      
      List<String> typeValues = new ArrayList<>();
      typeValues.add("Type 1");
      typeValues.add("Type 2");
      typeValues.add("Type 3");
      request.setAttribute("typesValues", typeValues);
      
      List<String> movesValues = new ArrayList<>();
      movesValues.add("Move 1");
      movesValues.add("Move 2");
      movesValues.add("Move 3");
      movesValues.add("Move 4");
      request.setAttribute("movesValues", movesValues);
      
      request.getRequestDispatcher("/WEB-INF/views/pokemonAdd.jsp").forward(request, response);
   }

   /**
    * Handles the HTTP <code>POST</code> method.
    *
    * @param request servlet request
    * @param response servlet response
    * @throws ServletException if a servlet-specific error occurs
    * @throws IOException if an I/O error occurs
    */
   @Override
   protected void doPost(HttpServletRequest request, HttpServletResponse response)
           throws ServletException, IOException {
      
      // Get the POST submitted data
      String selectedPokemonNo = request.getParameter("pokemonNo");
      String selectedPokemonName = request.getParameter("pokemonName");
      
      List<String> selectedTypes = new ArrayList<>();
      selectedTypes.add(request.getParameter("pokemonType1"));
      selectedTypes.add(request.getParameter("pokemonType2"));
      selectedTypes.add(request.getParameter("pokemonType3"));
      
      
      List<String> selectedMoves = new ArrayList<>();
      selectedMoves.add(request.getParameter("pokemonMove1"));
      selectedMoves.add(request.getParameter("pokemonMove2"));
      selectedMoves.add(request.getParameter("pokemonMove3"));
      selectedMoves.add(request.getParameter("pokemonMove4"));
      

      // POKEMON NO VALIDATION
      String noError = new String();
      try {
         int no = Integer.parseInt(selectedPokemonNo);
         
         // Check for negative number
         if(0 > no) {
            noError = "No must be positive";
         }
         // Check if the number is already used
         else if(pokemonsManager.exists(no)) {
            noError = "No exists already";
         }
      }
      catch(NumberFormatException e) {
         noError = "You must enter a number";
         selectedPokemonNo = "";
      }
      
      
      // POKEMON NAME VALIDATION
      String nameError = new String();
      
      // Check for empty name
      if(selectedPokemonName == null || selectedPokemonName.isEmpty()) {
         nameError = "Name can not be empty";
      }
      // Check if the name is already used
      else if(pokemonsManager.exists(selectedPokemonName)) {
         nameError = "Name exists already";
      }
      
      
      // POKEMON TYPES VALIDATION
      ArrayList<Type> types = new ArrayList<>();
      String typesError = new String();
      for(String t : selectedTypes) {
         Type type = typesManager.findByName(t);
         if(type != null) {
            types.add(type);
         }
      }
      
      if(types.size() <= 0 || types.size() > 3) {
         typesError = "Must fill in one type at least and max 3";
      }
      
      
      // POKEMON MOVES VALIDATION
     ArrayList<Move> moves = new ArrayList<>();
      String movesError = new String();
      for(String m : selectedMoves) {
         try {
            Move move = movesManager.findById(Integer.parseInt(m));
            if(move != null) {
               moves.add(move);
            }
         }
         catch(NumberFormatException e) {}
      }
      
      if(moves.size() <= 0 || moves.size() > 4) {
         movesError = "Must fill in one move at least and max 4";
      }
      
      if(noError.isEmpty() && 
         nameError.isEmpty() && 
         typesError.isEmpty() && 
         movesError.isEmpty()) 
      {
         pokemonsManager.add(
            new Pokemon(
                    Integer.parseInt(selectedPokemonNo),
                    selectedPokemonName, 
                    moves,
                    types
            )
         );
         
         // Redirection
         // TODO : Redirect with a message
         response.sendRedirect(request.getContextPath() + "/pokemons");
         return;
      }
      

      response.setContentType("text/html;charset=UTF-8");
      
      request.setAttribute("pokemonNo", selectedPokemonNo);
      request.setAttribute("pokemonName", selectedPokemonName);
      request.setAttribute("types", typesManager.findAll());
      request.setAttribute("moves", movesManager.findAll());
      request.setAttribute("typesValues", selectedTypes);
      request.setAttribute("movesValues", selectedMoves);
      
      // Set errors
      request.setAttribute("noError", noError);
      request.setAttribute("nameError", nameError);
      request.setAttribute("typesError", typesError);
      request.setAttribute("movesError", movesError);
      
      request.getRequestDispatcher("/WEB-INF/views/pokemonAdd.jsp").forward(request, response);
   }

   /**
    * Returns a short description of the servlet.
    *
    * @return a String containing servlet description
    */
   @Override
   public String getServletInfo() {
      return "Short description";
   }

}
