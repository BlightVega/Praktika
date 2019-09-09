
<?php



class Controller{
    
    private $page;
    private $req; //Soll die Request aufnehmen zum auswerten
    
    public function __construct(){  
        session_start();
        

        
//        file_put_contents("session.txt",$_SESSION);
             
          

        $this->req = $_REQUEST; // 
        
        // Hauptpgrogramm
        switch(true){
            case isset($this->req['input']) : $this->writeMessage();
           
        }

        
        $this->page = new View(); // 1. Seite bereitstellen
        $this->getAllMessage(); // 2. Alle Messages holen
        $this->page->toDisplay(); // 3. Seite anzeigen
        
    }
    
    private function getAllMessage(){
        //Holt Daten von MYSQL Server
        $data = MODEL::getAllMessage(); 
        $this->page->setLayout($data);  
    }
    
    private function writeMessage(){
                               //Inhalt vom Input Feld || User ID                 
          MODEL::setMessageIntoDB($this->req['input'],$_SESSION['id_user'] ); }
        

        }
         

          
    
    
                                

                
    
