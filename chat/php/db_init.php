<?php
/*
 * #########################################
 * #                                       #
 * #         Datenbank für Chat            #   
 * #             anlegen                   #
 * #                                       #
 * #########################################
 * vorhanden Datenbank/Tabellen
 * Werden nicht überschriben
 */

//Konstante const(nur Klassen)
define("HOST", "localhost"); //127.0.0.1
define("USER", "root");
define("PASS","cimdata");
define("DB_NAME", "db_livechat");

echo '<h1> DB anlegen</h1>';

try 
{
    $myPDO = new PDO("mysql:host=".HOST , USER, PASS );
} 
catch (PDOException $e) //unabhänige Klasse auf dem Server
{
    exit("Error: ".$e->getMessage());// Feheler ausgabe
}

//print_r(get_class_methods($myPDO));

function getSQLError()// anwendbar auf letzten SQL-Anweisung
{
    global $myPDO;
   $arr = $myPDO ->errorInfo(); // liefert SQL Fehler!
   echo $arr[2]; // Fehler als Text
}

//Datenbank anlegen
$myPDO->exec("CREATE DATABASE IF NOT EXISTS ".DB_NAME);

// DB wird bereitgestellt
$myPDO->exec("USE ".DB_NAME);//exec = ausführen

//Datenbanken anzeigen
$result = $myPDO->query("SHOW DATABASES ");

// auflistung der DBs als array ->
//print_r($result->fetchAll()); 

//-> als foreach
//foreach($result as $db)
//{
//   echo implode(",", $db)."<br>";
//}

//Zeichensatz festlegen, 
$myPDO->exec("SET NAMES utf8; SET CHARACTER SET UTF8 ");

$sql[] = "CREATE TABLE IF NOT EXISTS tb_user
        (
            id INT(11) AUTO_INCREMENT PRIMARY KEY,  
            name VARCHAR(20) NOT NULL UNIQUE,
            status VARCHAR(20)
        )";

//$sql[] = "CREATE TABLE IF NOT EXISTS tb_files" // HIER WEITERMACHEN UM DATEIEN AUF DIE DB HOCHLADEN
//        GET DIR/cwd/scandir


$sql[] = "CREATE TABLE IF NOT EXISTS tb_messages
        (
            id INT(11) AUTO_INCREMENT PRIMARY KEY,
            id_user INT(11) NOT NULL,
            text VARCHAR(160) NOT NULL, 
            ts TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
            FOREIGN KEY(id_user) REFERENCES tb_user(id)
        )";//timestamp als unix in sek


              /*$sql[]= "INSERT INTO tb_user(name, status)
               VALUES('Susi','Kunde')";

            $sql[]= "INSERT INTO tb_messages(id_user, text)
             VALUES('1','Hallo hier ist Susi.')";//die user_id muss mit der id in der tb_user übereinstimmen bei ersten mal auf 1 setzten später wird das automatisch mit PHP zugeordnet

            */
//$sql[] = "ALTER TABLE tb_user
//          ADD pass VARCHAR(128) NOT NULL";




 $sql[] = "SELECT ts, id, id_user, text FROM tb_messages           
           INTO OUTFILE 'C:/xampp/htdocs/phpbeispiele/tag_6/log/logfile.txt'  FIELDS TERMINATED BY '||' 
           LINES TERMINATED BY '\r\n'";
           


foreach ($sql as $query) 
{
    $myPDO->exec($query);
    getSQLError();
}
//tabellen in der DB anzeigen
$result = $myPDO->query("SHOW TABLES");

/*foreach ($result->fetchAll() as $res) 
{
    echo $res[0]."<br>";   
}
*/
?>
