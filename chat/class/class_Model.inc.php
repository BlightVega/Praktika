<?php

//Hier ausschließlich alle SQL Anfragen
// Siehe MVCS Prinzip/Aufbau

class Model{
    
    public static function getAllMessage(){
        
        $sql = "SELECT m.ts,m.text,u.name
                FROM tb_messages m
                JOIN tb_user u on u.id = m.id_user";
                     

       return SERVICE::getAll($sql);
    }
    
   public static function setMessageIntoDB($message, $id_user){
       $sql = "INSERT INTO tb_messages (id_user, text)
               VALUES ({$id_user},'{$message}')";
       SERVICE::setIntoDB($sql);        
       
   }
   
   public static function setUserIntoDB($name, $hash){
       $sql = "INSERT INTO tb_user(name,status, pass)
               VALUES ('{$name}','Kunde','{$hash}')";
       SERVICE::setIntoDB($sql); // Ausführen
       return SERVICE::getLastInsertID(); // ID für Session
   }
   
   public static function getUserPassID($name, $hash){
       $sql = "SELECT id FROM tb_user WHERE name = '{$name}' AND pass = '{$hash}'";
       return SERVICE::getOne($sql);
   }
   
   public static function getUserName($id){
       $sql = "SELECT name FROM tb_user WHERE id = ({$id})";
       
       return SERVICE::getOne($sql);
   }
   

    
}