<!DOCTYPE html>
<html>
    <head>
        <title>Chat</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="css/normalize.css">
        <link rel="stylesheet" href="css/start.css">

    </head>
    
    
    <body>
        <div style="text-align: center;"><h1>LiveChat</h1></div>
        <img src="img/logo.png" style="width:300px;height:180px;display:block;margin-left: auto;margin-right: auto" />
        
    <div id="message">   
       <br>       
        <?php      
        spl_autoload_register(function($class_name){
        include "class/class_".$class_name.".inc.php";
        });       
        new Login();
        ?>    
      <br>
    </div>
        

        

 
    </body>
</html>

