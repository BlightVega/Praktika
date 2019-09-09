<?php

class View{
    
    private $out;

   public function setLayout($data){
       
       foreach($data as $sp){
           echo "<div>";
           echo "<div>".$sp['name'];         
           echo "<span>".$sp['ts']."</span>"."</div>";
           echo $sp['text']."<br>";
           echo "</div>";
           
           
       }
        
    }
    
    public function toDisplay(){
        echo $this->out;
    }
}