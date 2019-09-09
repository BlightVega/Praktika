<?php

    ##################
    # Aufgabe des Autoloader
    # die gewünschte Klasse bereitstellen
    ##################

    spl_autoload_register(function($class_name){
        include "class_".$class_name.".inc.php";
    });

    new Controller();
