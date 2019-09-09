<?php

$name = $_POST['Name'];

$absender = $_POST['Email'];

$betreff = $_POST['Betreff'];

$msg = $_POST['Nachricht'];

//EOPOL ansetzen

$mailSenden = $name."<br>".$absender."<br>".$msg."<br>".$betreff."-------------------------";
   
echo var_dump($betreff);
echo "<br>";
echo var_dump($msg);
echo "<br>";

//$arr = array(1 => '$betreff', 2 => '$msg');
//$mailSenden = "{$arr[1]}{$arr[2]}";
echo var_dump($mailSenden);
echo "<br>";
 
 
//Weiterleitung, hier konnte jetzt per echo auch Ausgaben stehen
if($mailSenden){
  echo "test vor file<br>";
  file_put_contents('../Kontaktanfragen/contact'.date("j.n.Y").'.txt', $mailSenden, FILE_APPEND); 
  echo "test"; 
} else{
  file_put_contents('../Kontaktanfragen/contact'.date("j.n.Y").'.txt'.date("j.n.Y"), $mailSenden, FILE_APPEND); 
  
}