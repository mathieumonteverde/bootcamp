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
 * This servlet serves the home page
 * 
 * @author Mathieu Monteverde, Sathiya Kirushnapillai
 */
public class PokemonServlet extends HttpServlet {

   public static final int POKEMONS_PER_PAGE =10;

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

      int page;
      int pokemonsPerPage;
      
      // Check if the page is a number
      try {
         page = Integer.parseInt(request.getParameter("page"));
      } catch (NumberFormatException e) {
         page = 1;
      }

      // Check if the number of pokemon per page is a number
      try {
         pokemonsPerPage = Integer.parseInt(request.getParameter("pokemonsPerPage"));
      } catch (NumberFormatException e) {
         pokemonsPerPage = POKEMONS_PER_PAGE;
      }
      
      // Check for negative value
      if (pokemonsPerPage < 1) {
         response.sendError(HttpServletResponse.SC_NOT_FOUND);
      }
      
      // Calcul the number of page
      int totalNbPokemon = pokemonsManager.count();
      int maxNbPage = (int) Math.ceil(totalNbPokemon / (double) pokemonsPerPage);

      // Check page limitation
      if (page < 1) {
         response.sendError(HttpServletResponse.SC_NOT_FOUND);
      }
      
      if (page > maxNbPage) {
         page = maxNbPage;
      }

      response.setContentType("text/html;charset=UTF-8");
      request.setAttribute("pokemons", pokemonsManager.findAll(pokemonsPerPage, (page - 1) * pokemonsPerPage));

      // Pagination variables
      request.setAttribute("page", page);
      request.setAttribute("pokemonsPerPage", pokemonsPerPage);
      request.setAttribute("maxNbPage", maxNbPage);

      // Pagination creation
      List beforePagination = new LinkedList();
      List pagePagination = new LinkedList();
      List afterPagination = new LinkedList();

      // If there is a small number of pages, create a full pagination
      if (maxNbPage <= 6) {
         for (int i = 1; i <= maxNbPage; ++i) {
            pagePagination.add(i);
         }
      } else {
         // Else parse it in three
         // If possible, add at most the three first pages
         int pageCount = 1;
         if (pageCount < page - 2) {
            beforePagination.add(pageCount);
         }

         pageCount = page - 2 < 1 ? 1 : page - 2;
         while (pageCount <= page + 2 && pageCount <= maxNbPage) {
            pagePagination.add(pageCount++);
         }

         pageCount = (maxNbPage > page + 3) ? maxNbPage : page + 3;
         if (pageCount <= maxNbPage) {
            afterPagination.add(pageCount);
         }
      }

      request.setAttribute("beforePagination", beforePagination);
      request.setAttribute("pagePagination", pagePagination);
      request.setAttribute("afterPagination", afterPagination);

      request.getRequestDispatcher("/WEB-INF/views/pokemons.jsp").forward(request, response);
   }
}
