setTimeout(function(){
   const errorMessage = document.getElementById("login-error");
   if(errorMessage){
       errorMessage.style.transition = "opacity 0.5s ease";
       errorMessage.style.opacity = "0";

                   setTimeout(function () {
                       errorMessage.remove();
                   }, 500);
   }

},3000)