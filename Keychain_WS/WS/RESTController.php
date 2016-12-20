<?php

require_once("Handlers/RegistrationRESTHandler.php");
require_once("Handlers/LoginRESTHandler.php");
require_once("Handlers/AllKeysHandler.php");

$view = "";
if (filter_has_var(INPUT_POST, 'service')) {
    $service = filter_input(INPUT_POST, 'service');

    /*
      controls the RESTful services
      URL mapping
     */
    switch ($service) {

        case "registration":
            $registration = new RegistrationRESTHandler();
            $registration->work();
            break;
        
        case "login":
            $login = new LoginRESTHandler();
            $login->work();
            break;

        case "allLocks":
            $lock = new AllKeysHandler();
            $lock->work();
            break;

        case "" :
            //404 - not found;
            break;
    }
} else {
    $statusCode = 200;
    $rest = new RESTClass();
    $rawData = $rest->getResponseMessage(20);
    $rest->response($statusCode, $rawData);
}
?>