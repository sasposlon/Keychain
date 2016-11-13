<?php

include("./RESTClass.php");
include './../Database/Db_class.php';

class RegistrationRESTHandler extends RESTClass {

    function work() {
        $db = new Db();
        if (filter_has_var(INPUT_POST, 'mail') && filter_has_var(INPUT_POST, 'password')) {
            $mail = filter_input(INPUT_POST, 'mail');
            $password = filter_input(INPUT_POST, 'password');

            if (!filter_var($mail, FILTER_VALIDATE_EMAIL)) {
                $statusCode = 406;
                $rawData = array('ERROR' => 'Wrong email format!');
            } else {
                $db->query("Select * from user where mail = :mail");
                $db->bind('mail', $mail);
                $row = $db->single();

                if (empty($row)) {
                    $db->query("Insert into user (mail, password) values (:mail, :password)");
                    $db->bind(':mail', $mail);
                    $db->bind(':password', $password);
                    $execute = $db->execute();
                    $db->disconnect();
                    $statusCode = 200;
                    $rawData = array('OK' => 'Querry executed!');

                } else {
                    $db->disconnect();
                    $statusCode = 302;
                    $rawData = array('ERROR' => 'User already exists!');
                }
            }
        } else {
            $statusCode = 400;
            $rawData = array('ERROR' => 'Bad request!');
        }
        $requestContentType = $_SERVER['HTTP_ACCEPT'];
        $this->setHttpHeaders($requestContentType, $statusCode);

        if (strpos($requestContentType, 'application/json') !== false) {
            $response = $this->encodeJson($rawData);
            echo $response;
        }
    }

    public function encodeJson($responseData) {
        $jsonResponse = json_encode($responseData);
        return $jsonResponse;
    }

}

?>