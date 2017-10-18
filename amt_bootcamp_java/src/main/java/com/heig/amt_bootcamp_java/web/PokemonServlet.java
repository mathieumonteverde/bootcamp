/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.heig.amt_bootcamp_java.web;

import com.heig.amt_bootcamp_java.services.dao.PokemonsManagerLocal;
import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author mathieu
 */
public class PokemonServlet extends HttpServlet {
   
   public static final int POKEMONS_PER_PAGE = 5;

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
           throws ServletException, IOException 
   {

      // Get page number
      int page = 1;
      try {
         page = Integer.parseInt(request.getParameter("page"));
      } catch(NumberFormatException e) {
         page = 1; // TODO: 404 or error ?
      }
      
      int maxNbPokemon = pokemonsManager.count();
      int maxNbPage = (int) Math.ceil(maxNbPokemon / POKEMONS_PER_PAGE);
      
      // Page limitation
      if(page < 1 || page > maxNbPage) {
         // TODO : Display an error message or 404 page
      }
      
      response.setContentType("text/html;charset=UTF-8");
      request.setAttribute("pokemons", pokemonsManager.findAll(POKEMONS_PER_PAGE, (page-1) * POKEMONS_PER_PAGE));
      request.getRequestDispatcher("/WEB-INF/views/pokemons.jsp").forward(request, response);
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
      // Do Something on POST
   }

}
