$(document).ready(function() {
   
   /*
    * Change the number of pokemon page when the user selects another number
    * of pokemon per page on pokedex page.
    */
   $('.pokemonsPerPageSelect').on('change', function() {
      let url = $(this).val();
      if (url) {
         window.location = url;
      }
   });
   
   $('.nav-trigger').click(function() {
      $('header').toggleClass('open');
   });
});

