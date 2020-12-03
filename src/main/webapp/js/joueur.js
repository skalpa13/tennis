

    $(document).ready(function () {
    	 var monExprRegLetter = new RegExp("([^A-Za-z])"); 
    	 $('.erreurfrontend').css('display', 'none');
    	 $('.erreurfrontend').css('color', 'red');
    	 $('.erreur').css('color', 'red');
    	 $(function()
    	 {
    	    $("button").click(checkInputValues);
    	 });
    	 function checkInputValues() {
    	 	  $("input").each(checkInput);
    	 }
    	
    	 function checkInput() {
    	      if ( monExprRegLetter.test($(this).val()) == true )
    	      {
    	    	  $('.erreurfrontend').css('display', 'inline');
    	          $(this).css("background-color", "red");
    	          event.preventDefault();  // empêche envoi des données du formulaire
    	      }	  
    	 }
    	
    	     $("input").focus(function () {

    	    	 $(this).css("background-color", "transparent");
    	    	 $('.erreur').css('display', 'none');
            	 $('.erreurfrontend').css('display', 'none');
            });
      
    });





