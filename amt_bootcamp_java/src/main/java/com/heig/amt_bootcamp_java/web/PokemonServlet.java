/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.heig.amt_bootcamp_java.web;

import com.heig.amt_bootcamp_java.services.dao.PokemonsManagerLocal;
import java.io.IOException;
import java.util.LinkedList;
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
      int pokemonsPerPage = POKEMONS_PER_PAGE;
      try {
         page = Integer.parseInt(request.getParameter("page"));
      } catch(NumberFormatException e) {
         page = 1; // TODO: 404 or error ?
      }
      
      try {
         pokemonsPerPage = Integer.parseInt(request.getParameter("pokemonsPerPage"));
      } catch(NumberFormatException e) {
         pokemonsPerPage = POKEMONS_PER_PAGE; // TODO: 404 or error ?
      }
      
      int maxNbPokemon = pokemonsManager.count();
      int maxNbPage = (int) Math.ceil(maxNbPokemon / (double)pokemonsPerPage);
      
      // Page limitation
      if(page < 1 || page > maxNbPage) {
         // TODO : Display an error message or 404 page
      }
      
      response.setContentType("text/html;charset=UTF-8");
      request.setAttribute("pokemons", pokemonsManager.findAll(pokemonsPerPage, (page-1) * pokemonsPerPage));
      
      // Pagination variables
      request.setAttribute("page", page);
      request.setAttribute("pokemonsPerPage", pokemonsPerPage);
      request.setAttribute("maxNbPage", maxNbPage);
      
      List pagesToDisplay = new LinkedList();
      // If possible, add at most the three first pages
      int pageCount = 1;
      while (pageCount <= 3 && pageCount <= maxNbPage) {
         pagesToDisplay.add(pageCount++);
      }
      
      // Then add the current page
      if (pageCount < page) {
         pagesToDisplay.add(page);
         pageCount = page;
      }
      
      // Then if possible, add the last three pages
      if (pageCount < maxNbPage) {
         pageCount = maxNbPage - pageCount < 3 ? maxNbPage - pageCount : maxNbPage - 2;
         while (pageCount <= maxNbPage) {
            pagesToDisplay.add(pageCount++);
         }
      }
      request.setAttribute("pagesToDisplay", pagesToDisplay);
      
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
