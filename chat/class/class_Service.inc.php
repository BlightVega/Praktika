<?php

// Hier kein SQL
// Nur zur DB Verbindung
// 

class Service{
    const HOST = "localhost"; //127.0.0.1
    const USER = "root";
    const PASS = "cimdata";
    const DB_NAME = "db_livechat";
    static $myPDO; //IMmer die selbe DB Verbindung nutzen
    
    
    private function connectDB(){
        try{

        SELF::$myPDO = new PDO("mysql:host=".SELF::HOST.";dbname=".SELF::DB_NAME.";charset=utf8", SELF::USER, SELF::PASS);
    }
    catch (Exception $e) {
            exit("Ah! Ah! Ah! Du hast das Zauberwort nicht gesagt.");//Keinen weiteren Programmcode ausführen
        }
    }
    
    
    public static function getAll($sql){ //Gibt SQL SELECT * zurück aus class_model
      SELF::connectDB();
      $result = SELF::$myPDO->query($sql);//DB Abfrage
      return $result->fetchALL();//Rücklieferung 
        
    }
    
    public static function setIntoDB($sql){ // Diese Methode schreibt in die DB
        SELF::connectDB();
       return SELF::$myPDO->exec($sql); // Liefert true oder false bzw. betroffene zeilen zurück
    }
    
    public function getLastInsertID(){
        return SELF::$myPDO->LastInsertID(); //Rückgabe der letzten autoincrement id
    }
    
    public static function getOne($sql){ // Rückgabe von class_Model
        SELF::connectDB();
        $result = SELF::$myPDO->query($sql);
        return $result->fetchColumn(); // Gibt ein Numerischen Wert zurück char/string/boolean (kein Array also)
    }
    


}

