/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.heig.amt_bootcamp_java.web;

import com.heig.amt_bootcamp_java.services.dao.PokemonsManagerLocal;
import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author mathieu
 */
public class PokemonDeleteServlet extends HttpServlet {
   
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
      try {
         // Gets the no of the pokemon to delete
         int no = Integer.parseInt(request.getParameter("pokemon"));
         
         // Delete the pokemon
         pokemonsManager.deleteByNo(no);
         
         // Redirection
         // TODO : Redirect with a message
         RequestDispatcher dispatcher = 
            request.getRequestDispatcher("/pokemons");
         dispatcher.forward(request, response);
         
      } catch(NumberFormatException e) {
         // Display an error message
         request.setAttribute("title", "Select Pokemon to delete");
         request.setAttribute("actionUrl", "/pokemons/delete");
         request.setAttribute("submitText", "Delete...");
         request
            .getRequestDispatcher("/WEB-INF/views/pokemonActionSelection.jsp")
            .forward(request, response);
      }
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
