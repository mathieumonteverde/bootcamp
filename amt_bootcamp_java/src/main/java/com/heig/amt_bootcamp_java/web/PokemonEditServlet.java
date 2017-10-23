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
 * This servlet serves the edit page
 * 
 * @author Mathieu Monteverde, Sathiya Kirushnapillai
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

      // Get the pokemon no parameter
      int pokemonNo = 0;
      try {
         pokemonNo = Integer.parseInt(request.getParameter("pokemon"));
      } catch (NumberFormatException e) {
         response.sendError(HttpServletResponse.SC_NOT_FOUND);
      }

      // Get the pokemon
      Pokemon pokemon = pokemonsManager.findByNo(pokemonNo);
      if (pokemon == null) {
         response.sendError(HttpServletResponse.SC_NOT_FOUND);
      }

      // Set attribute for the form
      request.setAttribute("pokemon", pokemon);
      request.setAttribute("types", typesManager.findAll());
      request.setAttribute("moves", movesManager.findAll());

      // Set types
      List<String> typeValues = new ArrayList<>();
      for (Type t : pokemon.getTypes()) {
         typeValues.add(t.getName());
      }

      for (int i = typeValues.size(); i < Pokemon.MAX_TYPES; i++) {
         typeValues.add("Type " + i);
      }
      request.setAttribute("typesValues", typeValues);

      // Set moves
      List<String> movesValues = new ArrayList<>();
      for (Move m : pokemon.getMoves()) {
         movesValues.add("" + m.getId());
      }

      for (int i = movesValues.size(); i < Pokemon.MAX_MOVES; i++) {
         movesValues.add("Move " + i);
      }
      request.setAttribute("movesValues", movesValues);

      request.getRequestDispatcher("/WEB-INF/views/pokemonEdit.jsp").forward(request, response);
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

      // Get the pokemon
      Pokemon pokemon;
      try {
         String selectedPokemonNo = request.getParameter("pokemonNo");
         int no = Integer.parseInt(selectedPokemonNo);

         // Check for negative number
         if (0 > no) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
         }

         pokemon = pokemonsManager.findByNo(no);
         if (pokemon == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
         }
      } catch (NumberFormatException e) {
         response.sendError(HttpServletResponse.SC_NOT_FOUND);
         return;
      }

      // Set Name
      pokemon.setName(request.getParameter("pokemonName"));

      // Set types
      ArrayList<Type> types = new ArrayList<>();
      for (int i = 1; i <= Pokemon.MAX_TYPES; i++) {
         String t = request.getParameter("pokemonType" + i);
         Type type = typesManager.findByName(t);
         if (type != null) {
            types.add(type);
         }
      }
      pokemon.setTypes(types);

      // Set moves
      ArrayList<Move> moves = new ArrayList<>();

      for (int i = 1; i <= Pokemon.MAX_MOVES; i++) {
         try {
            String m = request.getParameter("pokemonMove" + i);
            Move move = movesManager.findById(Integer.parseInt(m));
            if (move != null) {
               moves.add(move);
            }
         } catch (NumberFormatException e) {
         }
      }
      pokemon.setMoves(moves);

      ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
      Validator validator = factory.getValidator();

      Set<ConstraintViolation<Pokemon>> violations = validator.validate(pokemon);

      String constraintError = null;
      if (violations.isEmpty()) {
         try {
            pokemonsManager.update(pokemon);

            // Redirection
            String message = "Pokemon was successfully edited";
            request.setAttribute("message", message);
            request.getRequestDispatcher("/success.jsp").forward(request, response);
            return;
         } catch (IntegrityConstraintViolation e) {
            constraintError = e.getValue();
         }
      }

      response.setContentType("text/html;charset=UTF-8");

      request.setAttribute("pokemon", pokemon);
      request.setAttribute("types", typesManager.findAll());
      request.setAttribute("moves", movesManager.findAll());

      List<String> selectedTypes = new ArrayList<>();
      for (Type type : pokemon.getTypes()) {
         selectedTypes.add(type.getName());
      }
      for (int i = pokemon.getTypes().size(); i < Pokemon.MAX_TYPES; i++) {
         selectedTypes.add("Type " + i);
      }
      request.setAttribute("typesValues", selectedTypes);

      
      List<String> selectedMoves = new ArrayList<>();
      for (Move move : pokemon.getMoves()) {
         selectedMoves.add("" + move.getId());
      }
      for (int i = pokemon.getMoves().size(); i < Pokemon.MAX_MOVES; i++) {
         selectedMoves.add("Move " + i);
      }
      request.setAttribute("movesValues", selectedMoves);

      // Set errors
      for (ConstraintViolation<Pokemon> violation : violations) {
         request.setAttribute(
                 violation.getPropertyPath() + "Error",
                 violation.getMessage()
         );
      }

      request.setAttribute("constraintError", constraintError);

      request.getRequestDispatcher("/WEB-INF/views/pokemonEdit.jsp").forward(request, response);
   }
}
