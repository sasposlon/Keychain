<?php

require_once("Handlers/RegistrationRESTHandler.php");
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

        case "" :
            //404 - not found;
            break;
    }
} else {
    $statusCode = 200;
    $rest = new RESTClass();
    $rawData = array('code' => 20,
        'type' => 'Error',
        'message' => 'Service variable missing',
        'items' => '');
    $rest->response($statusCode, $rawData);
 }
?>