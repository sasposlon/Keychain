<?php


define ('hostname', 'localhost');
define ('user', 'WebDiP2014x054');
define ('password', 'admin_mDKA');
define ('database', 'WebDiP2014x054');

$connect = mysqli_connect(hostname, user, password,database);    
    
    
   
	
	if($_SERVER["REQUEST_METHOD"]=="POST"){
		insertKey();

	}

	function insertKey(){
		global $connect;
		
		$name = $_POST["name"];
		$description = $_POST["description"];
		$hash = $_POST["nfc"];
		
		$query = "Insert into NFCkeys(description, hash, name) values('$description', '$hash', '$name');)";
		
		mysqli_query($connect, $query) or die (mysqli_error($connect));
		mysqli_close($connect);

	}

		
		
	 
	

?>