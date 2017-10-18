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
import java.util.concurrent.ThreadLocalRandom;
import javax.servlet.RequestDispatcher;


public class ConfigurationServlet extends HttpServlet {
   
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
      request.getRequestDispatcher("/WEB-INF/views/configuration.jsp").forward(request, response);
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
   protected void doPost(
      HttpServletRequest request, 
      HttpServletResponse response
   )
      throws ServletException, IOException 
   {
      int nbGene = 0;
      
      try {
         // Gets the number of pokemon to generate
         nbGene = Integer.parseInt(request.getParameter("number"));
         
         // TODO : Check the range of the number
         
      } catch(NumberFormatException e) {
         // TODO : Display an error message
      }
      
      
      // Gets all the moves possible
      List<Move> allMoves = movesManager.findAll();

      // Gets all the types possible
      List<Type> allTypes = typesManager.findAll();

      
      ArrayList<Pokemon> pokemons = new ArrayList<>();
      for(int i = 0; i < nbGene; i++) {
         
         // Set the types (min 1, max 2)
         ArrayList<Type> types = new ArrayList<>();
         int nbType = ThreadLocalRandom.current().nextInt(1, 3);
         for(int t = 0; t < nbType; t++) {
            int x = ThreadLocalRandom.current().nextInt(0, allTypes.size());
            Type type = allTypes.get(x);
            
            if(types.contains(type)) {
               t--;
            }
            else {
               types.add(type);
            }
         }
         
         // Set the moves
         ArrayList<Move> moves = new ArrayList<>();
         int nbMove = ThreadLocalRandom.current().nextInt(1, 5);
         for(int m = 0; m < nbMove; m++) {
            int x = ThreadLocalRandom.current().nextInt(0, allMoves.size());
            Move move = allMoves.get(x);
            
            if(moves.contains(move)) {
               m--;
            }
            else {
               moves.add(move);
            }
         }
         
         // Generate a random name
         int nbCars = ThreadLocalRandom.current().nextInt(3, 13);
         String name = randomName(nbCars);

         
         pokemons.add(new Pokemon(i, name, moves, types));
      }
      
      // TODO : Transaction ...
      pokemonsManager.deleteAll();
      pokemonsManager.add(pokemons);
      
      // Redirection
      // TODO : Redirect with a message
      RequestDispatcher dispatcher = 
         request.getRequestDispatcher("/pokemons");
      dispatcher.forward(request, response);
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
   
   /**
    * Generate a random alpha name 
    * TODO : Move this in a class
    * 
    * @param length The length of the name
    * @return the name
    */
   private String randomName(int length) {
      if(length <= 0) {
         // TODO : Throw an exception...
         length = 1;
      }
      
      String alpha = "abcdefghijklmnopqrstuvwxyz";
      StringBuilder name = new StringBuilder();
      
      for(int i = 0; i < length; i++) {
         int index = ThreadLocalRandom.current().nextInt(0, alpha.length());
         name.append(alpha.charAt(index));
      }
      
      return name.toString();
   }
}
