<?php

class Login{
 
    private $req;
    
    public function __construct(){     
        session_start();  
        $this->req = $_REQUEST;
        
 
        switch(true){//             reg = Ansicht | register = registrierung
            case isset($this->req['reg']):$this->toDisplayRegister();
                                              break;
            case isset($this->req['register']):$this->setRegister(); 
                                              break;                                          
            case isset($this->req['logout']):$this->setLogout();   
                                              break;
            case isset($this->req['login']):$this->checkPassword(); 
                                              break;
            default:
                    if(isset($_SESSION['id_user']))$this->toDisplayMessage();
                         else $this->toDisplayLogin();                                             
        }//end switch   
    }

        
    private function setLogout(){   
         
        // Vor dem Logout und dem redirect zur start.php die users.txt öffnen, den auszuloggenden user löschen, users.txt speichern.
        
        $listUsers = file("users.txt", FILE_IGNORE_NEW_LINES); // User Liste. Alle User die GERADE Online sind. Siehe die index.php und das ajax
        $log =    "Session beendet: ".' - '.date("F j, Y, g:i a").PHP_EOL.
                   "User Name: ".$_SESSION['name'].PHP_EOL.
                   "User ID: ".($_SESSION['id_user']).PHP_EOL.         
                   "-------------------------".PHP_EOL;
   
          
        foreach($listUsers as $index => $user){
                if ($user == "<img src='img/online-icon.png' style='width:18px;height:18px;' />".$_SESSION['name']."<br>"){              
                    unset($listUsers[$index]); // Löschung der $_SESSION['name'] über die variable index die zuvor über die user angesprochen wurde
                    }
                }

        $txt = implode("\r\n", $listUsers);
        file_put_contents("logs/users.txt", $txt );
        file_put_contents('logs/log_'.date("j.n.Y").'.txt', $log, FILE_APPEND);
        
         session_destroy();
         header("Location: start.php");
         exit;
     }


    private function getUserName(){ // Den Namen der aktuellen SESSION user ID anzeigen und irgendwo nutzen
        $name = MODEL::getUserName($_SESSION['id_user']); 
        $_SESSION['name'] = $name;
        return $name;
    }

   
    private function checkPassword(){
    $name = $this->req['name']; // Name bei Anmeldung
    $hash = hash('sha512',$this->req['pass']);    
    $id = Model::getUserPassID($name, $hash); // Entweder kommt eine ID Zurück oder ein false
    
    
        if($id){ // Userzugang richtig
            $_SESSION['id_user'] = $id;
            $_SESSION['time'] = time(); // Siehe Controller
            //Textdatei speichern für session name

            if(file_exists("logs/users.txt")){
                file_put_contents('logs/users.txt',"<img src='img/online-icon.png' style='width:18px;height:18px;' />".$this->getUserName()."<br>\r\n",FILE_APPEND); // WEnn existiert dann informationen dranhängen
            }else{
                file_put_contents('logs/users.txt',"<img src='img/online-icon.png' style='width:18px;height:18px;' />".$this->getUserName()."<br>\r\n"); // Ansonsten neu erstellen
            }


            header("Location: index.php");
            exit;
                }else{ // Userzugang falsch
                    echo "<div style='text-align: center; color: red;'><strong><br>Ah! Ah! Ah! Du hast das Zauberwort nicht gesagt.</strong><br><p></div>";
                    $this->toDisplayLogin();
                }
    }    

    
    private function setRegister(){
        $name = $this->req['name']; //Name aus Formular

        /* Registrierungsprozess mit Überprüfung auf Leerstellen, min/max Zeichenlänge gleiches Passwort und ob User bereits Existiert */

        if ($this->req['name'] == ""){
            echo "<div style='text-align: center; color: red;'><strong><br>Leerzeichen im Namen sind nicht erlaubt.</strong><br><p></div>";
            $this->toDisplayRegister();
            return false;
                }elseif

                 ($this->req['name'] == strlen($this->req['name']) < 3 ){
                     echo "<div style='text-align: center; color: red;'><strong><br>Dein Name muss aus mindestens 3 Buchstaben bestehen.</strong><br><p></div>";
                    $this->toDisplayRegister();
                    return false;
                        }elseif
                        ($this->req['name'] == strlen($this->req['name']) > 10 ){
                             echo "<div style='text-align: center; color: red;'><strong><br>Dein Name darf aus maximal 10 Buchstaben bestehen.</strong><br><p></div>";
                            $this->toDisplayRegister();
                            return false;
                                }elseif
                                ($this->req['pass'] != $this->req['passw']){
                                    echo "<div style='text-align: center; color: red;'><strong><br>Passwortwiederholung stimmt nicht.</strong><br><p></div>";
                                    $this->toDisplayRegister();
                                    return false; // Abbruch, keine weitere Ausgabe
                                    }else
                                    $hash = hash('sha512', $this->req['pass']);
                                    $id = MODEL::setUserIntoDB($name,$hash); // Bei Erfolg Userid

                                    if($id){
                                        $_SESSION['id_user'] = $id; // User registriert
                                        header("Location: index.php"); //Sprung zur Hauptseite mit aktivierter Session
                                        exit;
                                    }
        else{ // Username existiert bereits
            echo "<div style='text-align: center; color: red;'><strong><br>Username vergeben.</strong><br><p></div>";
            $this->toDisplayRegister();
        }
    }


    private function toDisplayMessage(){ // Messagefenster

        echo "Willkommen "."<b>".$this->getUserName()."</b>"."<br>";


        echo '<div id="input" minlength="2" maxlength="210" contenteditable="true" data-text="Nachricht eingeben..."></div>       


                <!-- Senden Button und Smileys -->
                <button id="send">Senden</button><br>



                <ul id="emoticons">
                <li><img onclick="showImg(this.src)" alt="error" src="img/laught.png" style="width:18px;height:18px;"/></li> 
                <li><img onclick="showImg(this.src)" alt="error" src="img/smile.png" style="width:18px;height:18px;" /></li>  
                <li><img onclick="showImg(this.src)" alt="error" src="img/smileAngry.png" style="width:18px;height:18px;" /></li> 
                <li><img onclick="showImg(this.src)" alt="error" src="img/smileGhost.png" style="width:18px;height:18px;" /></li> 
                <li><img onclick="showImg(this.src)" alt="error" src="img/smileLove.png" style="width:18px;height:18px;" /></li> 
                <li><img onclick="showImg(this.src)" alt="error" src="img/smileSweat.png" style="width:18px;height:18px;" /></li> 
                <li><img onclick="showImg(this.src)" alt="error" src="img/smileTongue.png" style="width:18px;height:18px;" /></li> 
                <li><img onclick="showImg(this.src)" alt="error" src="img/smileUpsideDown.png" style="width:18px;height:18px;" /></li>
                </ul>';
    }   
    
    
    private function toDisplayLogin(){ // Loginfenster
            echo  '<div style="text-align: center;">
                   <button>Anmelden</button> / <a href="?reg=true"><button>Registrieren</button></a><br><br>
                   <form id="login">
                   <input type="text" placeholder="Name" name="name"><br>
                   <input type="text" placeholder="Passwort" name="pass"><br><br>
                   <input type="submit" value="login" name="login">
                   </form>
                   </div>';
            echo '<br><div style="text-align: center;">';
            echo '<a href="impressum.html">Impressum</a>&nbsp;&nbsp;'; 
            echo '<a href="kontakt.html">Kontakt</a>'; 
            echo '</div>';
        
    }
    
    
    private function toDisplayRegister(){ // Registerfenster
             echo '<div style="text-align: center;">
                  <a href="start.php"><button>Anmelden</button></a> / <button>Registrieren</button><br><br>
                  <form>
                  <input type="text" placeholder="Name" name="name"><br>
                  <input type="text" placeholder="Passwort" name="pass"><br>
                  <input type="text" placeholder="Passwortwiederholung" name="passw"><br><br>
                  <input type="submit" value="registration" name="register"> 
                  </form>
                  </div>';
    }
}


