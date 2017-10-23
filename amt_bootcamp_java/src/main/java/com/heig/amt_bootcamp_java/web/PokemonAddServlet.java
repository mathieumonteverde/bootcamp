/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.heig.amt_bootcamp_java.web;

import com.heig.amt_bootcamp_java.exceptions.IntegrityConstraintViolation;
import com.heig.amt_bootcamp_java.model.Move;
import com.heig.amt_bootcamp_java.model.Pokemon;
import com.heig.amt_bootcamp_java.model.Type;
import com.heig.amt_bootcamp_java.services.dao.MovesManagerLocal;
import com.heig.amt_bootcamp_java.services.dao.PokemonsManagerLocal;
import com.heig.amt_bootcamp_java.services.dao.TypesManagerLocal;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

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
      for(int i = 1; i <= Pokemon.MAX_TYPES; i++) {
         typeValues.add("Type " + i);
      }
      request.setAttribute("typesValues", typeValues);
      
      List<String> movesValues = new ArrayList<>();
      for(int i = 1; i <= Pokemon.MAX_MOVES; i++) {
         movesValues.add("Move " + i);
      }
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

      Pokemon p = new Pokemon();

      // Set no
      try {
         p.setNo(Integer.parseInt(request.getParameter("pokemonNo")));
      }
      catch(NumberFormatException e) {
         p.setNo(-1);
      }

      // Set Name
      p.setName(request.getParameter("pokemonName"));

      // Set types
      ArrayList<Type> types = new ArrayList<>();
      for(int i = 1; i <= Pokemon.MAX_TYPES; i++) {
         String t = request.getParameter("pokemonType" + i);
         Type type = typesManager.findByName(t);
         if(type != null) {
            types.add(type);
         }
      }
      p.setTypes(types);
  
      // Set moves
      ArrayList<Move> moves = new ArrayList<>();
      
      for(int i = 1; i <= Pokemon.MAX_MOVES; i++) {
         try {
            String m = request.getParameter("pokemonMove" + i);
            Move move = movesManager.findById(Integer.parseInt(m));
            if(move != null) {
               moves.add(move);
            }
         }
         catch(NumberFormatException e) {}
      }
      p.setMoves(moves);
      

      ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
      Validator validator = factory.getValidator();
      
      Set<ConstraintViolation<Pokemon>> violations = validator.validate(p);
      
      String constraintError = null;
      // If no validation errors
      if(violations.size() == 0) 
      {
         try {
            pokemonsManager.add(p);
            
            // Redirection
            // TODO : Redirect with a message
            response.sendRedirect(request.getContextPath() + "/pokemons");
            return;
            
         } catch (IntegrityConstraintViolation e) {
            constraintError = e.getValue();
         }
      }
      

      response.setContentType("text/html;charset=UTF-8");
      
      request.setAttribute("pokemonNo", p.getNo() == -1 ? "" : p.getNo());
      request.setAttribute("pokemonName", p.getName());
      request.setAttribute("types", typesManager.findAll());
      request.setAttribute("moves", movesManager.findAll());
      
      List<String> selectedTypes = new ArrayList<>();
      for(Type type : p.getTypes()) {
         selectedTypes.add(type.getName());
      }
      for(int i = p.getTypes().size(); i < Pokemon.MAX_TYPES; i++) {
         selectedTypes.add("Type " + i);
      }
      request.setAttribute("typesValues", selectedTypes);
      
      List<String> selectedMoves = new ArrayList<>();
      for(Move move : p.getMoves()) {
         selectedMoves.add("" + move.getId());
      }
      for(int i = p.getMoves().size(); i < Pokemon.MAX_MOVES; i++) {
         selectedMoves.add("Move " + i);
      }
      request.setAttribute("movesValues", selectedMoves);
      
      // Set errors
      for (ConstraintViolation<Pokemon> violation : violations) {
         request.setAttribute(
            violation.getPropertyPath() + "Error" , 
            violation.getMessage()
         );
      }
      request.setAttribute("constraintError", constraintError);
      
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
