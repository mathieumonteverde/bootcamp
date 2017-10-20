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
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author mathieu
 */
public class PokemonDeleteServlet extends HttpServlet {
   
   private static final String COOKIE_DELETE = "deleteConfirmation";
   
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
         
         // Check if the pokemon exists
         if (pokemonsManager.exists(no)) {
            
            // Get cookies and check if the delete cookie exists
            Cookie[] cookies = request.getCookies();
            for (Cookie c : cookies) {
               
               // If the user asked not to be asked again, delete and leave
               if (c.getName().equals(COOKIE_DELETE)) {
                  // Delete the pokemon
                  pokemonsManager.deleteByNo(no);

                  // Redirect to pokemons
                  response.sendRedirect(request.getContextPath() + "/pokemons");
                  
                  return;
               }
            }
            
            // Else, Show the confirmation form
            request.setAttribute("pokemon", pokemonsManager.findByNo(no));
            request.getRequestDispatcher("/WEB-INF/views/deleteConfirmation.jsp")
                    .forward(request, response);
            
         } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
         }
         
      } catch(NumberFormatException e) {
         response.sendError(HttpServletResponse.SC_NOT_FOUND);
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
      try {
         // Get pokemon number
         int no = Integer.parseInt(request.getParameter("pokemon"));
         
         // Check if the pokemon exists
         if (pokemonsManager.exists(no)) {
            
            // Delete the pokemon
            pokemonsManager.deleteByNo(no);
            
            // Check if the user asked to not be asked again
            String notAskAgain = request.getParameter("notAskAgain");
            if (notAskAgain != null && notAskAgain.equals("on")) {
               // Set a cookie
               response.addCookie(new Cookie(COOKIE_DELETE, "true"));
            }
            
            // Redirect to pokemons
            response.sendRedirect(request.getContextPath() + "/pokemons");
         } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
         }
         
      } catch(NumberFormatException e) {
         response.sendError(HttpServletResponse.SC_NOT_FOUND);
      }
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
