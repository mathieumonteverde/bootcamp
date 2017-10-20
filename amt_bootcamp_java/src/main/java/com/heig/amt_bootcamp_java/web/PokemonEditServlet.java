/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.heig.amt_bootcamp_java.web;

import com.heig.amt_bootcamp_java.model.Pokemon;
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
      

      try{
         // If the pokemon parameter is not null (GET method) we show the edit form
         int pokemonNo = Integer.parseInt(request.getParameter("pokemon"));
         
         // Get the pokemon
         Pokemon pokemon = pokemonsManager.findByNo(pokemonNo);
         if(pokemon == null) {
            errorForward(request, response);
         }
         
         request.setAttribute("pokemon", pokemon);
         
         // TODO : ADD no and name input
         // TODO : Match scroll with the get pokemon
         
         request.setAttribute("types", typesManager.findAll());
         request.setAttribute("moves", movesManager.findAll());

         List<String> typeValues = new ArrayList<>();
         typeValues.add("Type 1");
         typeValues.add("Type 2");
         typeValues.add("Type 3");
         request.setAttribute("typesValues", typeValues);

         List<String> movesValues = new ArrayList<>();
         movesValues.add("Move 1");
         movesValues.add("Move 2");
         movesValues.add("Move 3");
         movesValues.add("Move 4");
         request.setAttribute("movesValues", movesValues);

         request.getRequestDispatcher("/WEB-INF/views/pokemonEdit.jsp").forward(request, response);
      } 
      catch(NumberFormatException e) {
         // If the pokemon is not specified, we show the pokemon selection screen
         errorForward(request, response);
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

   private void errorForward(
      HttpServletRequest request, 
      HttpServletResponse response) 
      throws ServletException, IOException 
   {
      request.setAttribute("pokemons", pokemonsManager.findAll(pokemonsManager.count(), 0));

      request.setAttribute("title", "Select the Pokemon to edit");
      request.setAttribute("actionUrl", "/pokemons/edit");
      request.setAttribute("submitText", "Edit...");

      request.getRequestDispatcher("/WEB-INF/views/pokemonActionSelection.jsp").forward(request, response);
   }
}
