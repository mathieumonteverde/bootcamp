package com.heig.amt_bootcamp_java.web;

import com.heig.amt_bootcamp_java.model.Pokemon;
import com.heig.amt_bootcamp_java.services.dao.PokemonsManagerLocal;
import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This servlet serves the configuration page
 *
 * @author Mathieu Monteverde, Sathiya Kirushnapillai
 */
public class ConfigurationServlet extends HttpServlet {
   
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
      
      String errorMessage = new String();
      
      try {
         // Gets the number of pokemon to generate
         nbGene = Integer.parseInt(request.getParameter("number"));

         if(nbGene <= 0 || nbGene > 1234567) {
            errorMessage = "Number of pokemons must be between 1 and 1234567";
         }
      } catch(NumberFormatException e) {
         errorMessage = "Number of pokemons must be between 1 and 1234567";
      }
      
      // Redirect if number is incorrect
      if(!errorMessage.isEmpty()) {
         response.setContentType("text/html;charset=UTF-8");
         request.setAttribute("errorMessage", errorMessage);
         request.getRequestDispatcher("/WEB-INF/views/configuration.jsp").forward(request, response);
         return;
      }
      
      pokemonsManager.deleteAll();
      pokemonsManager.generatePokemons(nbGene, Pokemon.MAX_TYPES, Pokemon.MAX_MOVES);
      
      // Redirection
      response.sendRedirect(request.getContextPath() + "/pokemons");
   }
}
