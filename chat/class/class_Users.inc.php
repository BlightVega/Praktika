<?php

class users{
    
    private $id;
    private $name;
    private $loginStatus;
    private $lastLogin;
    private $dbConn;
    
    
    function setId($id) {$this->id = $id;}
    function getId(){ return $this->id;}
    function setName($name) {$this->name = $name;}
    function getName(){return $this->name;}
    function setLoginStatus($loginStatus){$this->loginStatus = $loginStatus;}
    function getLoginStatus(){ return $this->loginStatus;}
    function setLastLogin($lastLogin){$this->lastLogin = $lastLogin;}
    function getLastLogin(){return $this->lastLogin;}
          
    public function __construct(){
        require_once("class_Service.inc.php");
        $db = new DbConnect();
        $this->dbConn = $db->connect();
    }
    
    public function save(){
        
    }
    
}
