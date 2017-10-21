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
public class PokemonEditServlet extends HttpServlet {
   
   @EJB
   private MovesManagerLocal movesManager;
   
   @EJB
   private TypesManagerLocal typesManager;
   
   @EJB
   private PokemonsManagerLocal pokemonsManager;

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

      // Get the pokemon parameter
      int pokemonNo = 0;
      try{
         pokemonNo = Integer.parseInt(request.getParameter("pokemon"));
      } 
      catch(NumberFormatException e) {
         response.sendError(HttpServletResponse.SC_NOT_FOUND);
      }
         
      // Get the pokemon
      Pokemon pokemon = pokemonsManager.findByNo(pokemonNo);
      if(pokemon == null) {
         response.sendError(HttpServletResponse.SC_NOT_FOUND);
      }


      // Set attribute for the form
      request.setAttribute("pokemon", pokemon);
      request.setAttribute("types", typesManager.findAll());
      request.setAttribute("moves", movesManager.findAll());


      // Set types
      List<String> typeValues = new ArrayList<>();
      for(Type t : pokemon.getTypes()) {
         typeValues.add(t.getName());
      }

      for(int i = typeValues.size(); i < Pokemon.MAX_TYPES; i++) {
         typeValues.add("Type " + i);
      }
      request.setAttribute("typesValues", typeValues);


      // Set moves
      List<String> movesValues = new ArrayList<>();
      for(Move m : pokemon.getMoves()) {
         movesValues.add("" + m.getId());
      }

      for(int i = movesValues.size(); i < Pokemon.MAX_MOVES; i++) {
         movesValues.add("Move " + i);
      }
      request.setAttribute("movesValues", movesValues);

      request.getRequestDispatcher("/WEB-INF/views/pokemonEdit.jsp").forward(request, response);
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
      for(int i = 1; i <= Pokemon.MAX_TYPES; i++) {
         selectedTypes.add(request.getParameter("pokemonType" + i));
      }

      List<String> selectedMoves = new ArrayList<>();
      for(int i = 1; i <= Pokemon.MAX_MOVES; i++) {
         selectedMoves.add(request.getParameter("pokemonMove" + i));
      }
      
      
      // POKEMON NO VALIDATION
      Pokemon pokemon = null;
      try {
         int no = Integer.parseInt(selectedPokemonNo);
         
         // Check for negative number
         if(0 > no) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
         }
         
         pokemon = pokemonsManager.findByNo(no);
         if(pokemon == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
         }
      }
      catch(NumberFormatException e) {
         response.sendError(HttpServletResponse.SC_NOT_FOUND);
         return;
      }

      
      // POKEMON NAME VALIDATION
      String nameError = new String();

      // Check for empty name
      if(selectedPokemonName == null || selectedPokemonName.isEmpty()) {
         nameError = "Name can not be empty";
      }
      // Check if the name is already used
      else if(!pokemon.getName().equals(selectedPokemonName) && pokemonsManager.exists(selectedPokemonName)) {
         nameError = "Name exists already";
      }
      else {
         pokemon.setName(selectedPokemonName);
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
      
      if(types.size() <= 0 || types.size() > Pokemon.MAX_TYPES) {
         typesError = "Must fill in one type at least and max " +  + Pokemon.MAX_TYPES;
      }
      else {
         pokemon.setTypes(types);
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
      
      if(moves.size() <= 0 || moves.size() > Pokemon.MAX_MOVES) {
         movesError = "Must fill in one move at least and max " + Pokemon.MAX_MOVES;
      }
      else {
         pokemon.setMoves(moves);
      }
      
      if(nameError.isEmpty() && 
         typesError.isEmpty() && 
         movesError.isEmpty()) 
      {
         pokemonsManager.update(
            pokemon
         );
         
         // Redirection
         // TODO : Redirect with a message
         response.sendRedirect(request.getContextPath() + "/pokemons");
         return;
      }
      
      response.setContentType("text/html;charset=UTF-8");

      request.setAttribute("pokemon", pokemon);
      request.setAttribute("types", typesManager.findAll());
      request.setAttribute("moves", movesManager.findAll());
      request.setAttribute("typesValues", selectedTypes);
      request.setAttribute("movesValues", selectedMoves);
      
      // Set errors
      request.setAttribute("nameError", nameError);
      request.setAttribute("typesError", typesError);
      request.setAttribute("movesError", movesError);
      

      request.getRequestDispatcher("/WEB-INF/views/pokemonEdit.jsp").forward(request, response);
   }
}
