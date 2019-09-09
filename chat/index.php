<!DOCTYPE html>
<html>

    <head>
        <title>Chat</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="css/normalize.css">
        <link rel="stylesheet" href="css/main.css">
    </head>
  

    <body>
        <h1>LiveChat V1.1</h1>        
        <div id="message"> </div>
        
<?php      
        spl_autoload_register(function($class_name){
        include "class/class_".$class_name.".inc.php";       
        });
        new Login();       
?>

              
<div id="uploadArea">
     <ul>
        <?php

            $ordner = "upload";
            $allesLesen = scandir($ordner); // scandir holt den Inhalt vom Verzeichnis /upload/

            foreach ($allesLesen as $datei){
                $dateiInfo = pathinfo($ordner."/".$datei); // Die Möglichkeit Informationen über Datei zu erfragen
                $size = ceil(filesize($ordner."/".$datei)/1024); // Dateigröße in kb Ausgabe
    
                    if($datei != "." && $datei != ".."){ // Wenn Datei Information aus "." oder ".." besteht wird es ausgeblendet
        ?>
         
        <!-- ['dirname'] und ['basename'] gibt mir den Pfadname und den vorherigen raus. -->
        <!-- Zeile 53 erstellt den anklickbaren link für das File -->
        <!-- Zeile 54 gibt den Namen + Dateiendung + Filegröße umgerechnet in kb (siehe Zeile 44) für das File -->
     <li>
         <img src="<?php echo $dateiInfo['dirname']."/".$dateiInfo['basename'];?>" width="20" alt="Vorschau" />
         <a href="<?php echo $dateiInfo['dirname']."/".$dateiInfo['basename'];?>">              
         <?php echo $dateiInfo['filename']; ?> </a> (<?php echo $dateiInfo['extension']; ?> | <?php echo $size ; ?>kb)  
     </li>
         
        <?php
        };
        };
        ?>          
     </ul>
</div>

        
<div id="onlineUsers"><br>  
            
    <?php
            
        $log  = "Session gestartet: ".' - '.date("F j, Y, g:i a").PHP_EOL.
                "User Name: ".$_SESSION['name'].PHP_EOL.
                "User ID: ".($_SESSION['id_user']).PHP_EOL.         
                "-------------------------".PHP_EOL;

                file_put_contents('logs/log_'.date("j.n.Y").'.txt', $log, FILE_APPEND);     
    ?>       
</div>

                               
<div id="DateiUpload">

        <form action="class/class_File.inc.php" method="post" enctype="multipart/form-data">
            <p>Wählen Sie eine Datei aus:<br>     
            <input type="file" name="Datei" />
            <br>
            <input type="submit" name="Submit" value="Senden">
        </form>   

</div>
 


<div id="options"> 
        <a href="" onclick="javascript:show('uploadArea'); return false"> 
        <button>Hochgeladene Dateien anzeigen / ausblenden</button></a> 
        <p>
        <a href="" onclick="javascript:show('DateiUpload'); return false"> 
        <button>Dateien hochladen anzeigen / ausblenden</button></a> 
        <p>      
        <a href="index.php?logout=true"><button>Logout</button></a>
</div>

<!-- BEGINN ALLER SCRIPTE -->        
<!-- notwendige Referenz damit die Jquery scripte funktionieren */  -->      
<script type="text/javascript" src="//ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
<script>
    
 
    
    
         function show(id) 
         { 
            if(document.getElementById) 
            { 
                var showHide = document.getElementById(id); 
                showHide.style.display = (showHide.style.display === 'block'?'none':'block'); 
            } 
         } 

         
      



        /* Für die max Zeichenlänge im div (messagefenster) */

        $(document).ready(function() 
        {
                $('#input').keyup(function()
                {

                  var limit = parseInt($(this).attr('maxlength'));           
                  var text = $(this).html();
                  var chars = text.length;

                    if(chars >= limit)
                    {             
                        var new_text = text.substr(0,limit);
                        $(this).html(new_text);           
                    }
                });
        });


        /* Für die Audioausgabe beim betätigen von "Senden" */

         $(function()
         {
             $('#send').click(function()
             {
                 var snd='<audio autoplay=true> <source src="sound/sent.wav"></audio>';
                 $('body').append(snd);
             });
         });




         /* Für den Übertrag des Smileys in das input Feld" */
           
        function showImg(icon)
        {
          document.getElementById("input").innerHTML = document.getElementById("input").innerHTML+'<img style="width:18px;height:18px;" src="'+icon+'">';     
        };



         /* Beginn der Ajax Scripte */   
            
            var m = document.getElementById("message");
            var s = document.getElementById("send"); //Selektor
            var inp = document.getElementById("input");
            var uWindow = document.getElementById("onlineUsers");
            
            /* Ajax für die Online User */  
            function myReadUserNames()
            {
                var http = new XMLHttpRequest();
                // lade Datei auf Server synchron / true = asynchrone
                http.open('get','logs/users.txt', true);
                http.onreadystatechange = function()
                {
                    if(http.readyState === 4 && http.status === 200)
                    {
                        uWindow.innerHTML = http.responseText; // Hier mit createElement Ansetzen für Traffic Reduzierung.          
                        
                    }
                        if(http.status === 404)
                        {
                            alert("File not found");
                        }
                };
                http.send(); //Ajax aufbau
                
            }
            setInterval("myReadUserNames()", 2000);
            
            
            
            
            
            /* Ajax für den Chat und der Nachrichten */  
            function myRead()
            {
                var http = new XMLHttpRequest();
                // lade Datei auf Server synchron / true = asynchrone
                http.open('get','class/autoloader.php', true);
                http.onreadystatechange = function()
                {
                    if(http.readyState === 4 && http.status === 200)
                    {
                        m.innerHTML = http.responseText; // Hier mit createElement Ansetzen für Traffic Reduzierung.          
                        m.scrollTop = 10000;
                    }
                        if(http.status === 404)
                        {
                            alert("File not found");
                        }
                };
                http.send(); //Ajax aufbau    
            }

               

            /* Ajax für die übertragung des geschriebenen Texts (input) in den Chat (message) */  
            function myWrite(message)
            { // An Server senden
                var httpsend = new XMLHttpRequest();
                httpsend.open('get','class/autoloader.php?input='+message , true);
                httpsend.send();
            }
            
            /* Hauptprogram Anweisungen */
            myRead();         
            setInterval("myRead()", 2000); //Alle 2sec. aktualisieren
            
            
            /* Events */
            s.onclick = function()
            {
                myWrite(inp.innerHTML); // innerHTML wenn ich ein div zur Texteingabe nutze. TEXT von INPUT auf CHAT
                inp.innerHTML = ""; //löschen der Eingabe nach betätigung des "send" button
            };
 
    </script>


    </body>
</html>
