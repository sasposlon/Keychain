<?php

require_once("Handlers/RegistrationRESTHandler.php");
require_once("Handlers/LoginRESTHandler.php");
require_once("Handlers/AllKeysRESTHandler.php");
require_once("Handlers/ForgotPasswordRESTHandler.php");
require_once("Handlers/AddKeyRESTHandler.php");

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
            $lock = new AllKeysRESTHandler();
            $lock->work();
            break;
        
        case "forgotPassword":
            $forgotPass = new ForgotPasswordRESTHandler();
            $forgotPass->work();
            break;
        
        case "addKey":
            $addKey = new AddKeyRESTHandler();
            $addKey->work();
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