<?php

/*
 * #########################################
 * #                                       #
 * #         Upload Steuern                #   
 * #         Rechte Verwaltung             #
 * #                                       #
 * #########################################
 * 
 */

 
                    /* Variablen */

$message =  "Datei ist zu groß!";
$message2 = "Fehler in MIME Endung";
$message3 = "Fehler während des Start des Uploads!";

$message0 = "Alles erfolgreich überprüft und hochgeladen!";

$dateiExist = $_FILES['Datei'];              
$dateiError = $_FILES['Datei']['error'];     
$dateiName = $_FILES['Datei']['name'];       
$dateiSize = $_FILES['Datei']['size'];       
$dateiTmp = $_FILES['Datei']['tmp_name'];    


$fInfo = new finfo(FILEINFO_MIME_TYPE); // Auslesen aus Datei
$mimeType = array('jpg' => 'image/jpeg', 'JPG' => 'image/jpeg'); // Array wird erweitert um erlaubte Dateiendungen, dient erstmal nur dem Test



                    /* Fehler Vorbereitung der UPLOAD_ERR_ Codes*/

  class UploadException extends Exception{
      
      
    public function __construct($code) { 
        
        $message4 = $this->ExceptionCode($code);
        parent::__construct($message4, $code); 
        echo("<script>alert('$message4')</script>");
        echo("<script>window.location = '../index.php';</script>");
        exit;
    } 
    
       private function ExceptionCode($code){
            
       switch ($code) { 
            case UPLOAD_ERR_PARTIAL: 
                $message = "The uploaded file was only partially uploaded"; 
                break; 
            case UPLOAD_ERR_NO_FILE: 
                $message = "No file was uploaded"; 
                break; 
            case UPLOAD_ERR_NO_TMP_DIR: 
                $message = "Missing a temporary folder"; 
                break; 
            case UPLOAD_ERR_CANT_WRITE: 
                $message = "Failed to write file to disk"; 
                break; 
            default: 
                $message = "Unknown upload error"; 
                break; 
        } 
        return $message; 
    }
}





                    /* Haupt Programm */

if ($_FILES['Datei']['error'] === UPLOAD_ERR_OK)
{
          
     if (isset($dateiExist) and !$dateiError and ($dateiName != '.htaccess')) // htaccess anlegen für Beschränkungen und/oder Zugriffsrechte.
     {
        if ($dateiSize > 500000) // Überprüfung der Dateigröße, wenn zu groß Fehlermeldung 
        {
            echo "<script type='text/javascript'>alert('$message');</script>"; 
            echo("<script>window.location = '../index.php';</script>");
            exit;
        } 
            if (!array_search($fInfo->file($dateiTmp),$mimeType))             
            {
                 echo("<script>alert('$message2')</script>");
                 echo("<script>window.location = '../index.php';</script>");
                 exit;
           
            } 
                if (move_uploaded_file($dateiTmp,"../upload/".$dateiName)) //tmp Dateinamen in upload Ordner verschieben und Dateinamen setzen
                {
                    echo("<script>alert('$message0')</script>");
                    echo("<script>window.location = '../index.php';</script>");
                    exit; 
                }
                    else
                    {
                        echo("<script>alert('$message3')</script>");
                    }
     }
}
else
{

    throw new UploadException($_FILES['Datei']['error']); 

}   
  
