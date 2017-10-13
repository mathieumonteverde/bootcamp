/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.heig.amt_bootcamp_java.web;

import com.heig.amt_bootcamp_java.model.Move;
import com.heig.amt_bootcamp_java.model.Pokemon;
import com.heig.amt_bootcamp_java.model.Type;
import java.io.IOException;
import java.util.Arrays;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author mathieu
 */
public class PokemonEditServlet extends HttpServlet {

   // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
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
      String pokemonName = request.getParameter("pokemon");
      
      if (pokemonName != null) {
         // If the pokemon parameter is not null (GET method) we show the edit form
         
         // TODO Fetch check input and fetch corresponding pokemon model
         
         Pokemon pokemon = new Pokemon(
            1, 
            pokemonName,
            Arrays.asList(new Move(1, "Cut")),
            Arrays.asList(new Type("Water"))
         );
         request.setAttribute("pokemon", pokemon);

         request.getRequestDispatcher("/WEB-INF/views/pokemonEdit.jsp").forward(request, response);
      } else {
         // If the pokemon is not specified, we show the pokemon selection screen
         
         request.setAttribute("title", "Select Pokemon to edit");
         request.setAttribute("actionUrl", "/pokemons/edit");
         request.setAttribute("submitText", "Edit...");
         request.getRequestDispatcher("/WEB-INF/views/pokemonActionSelection.jsp").forward(request, response);
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

      request.getRequestDispatcher("WEB-INF/views/pokemonEdit.jsp").forward(request, response);
   }

}
