<?php

include("./RESTClass.php");
include './../Database/Db_class.php';

class RegistrationRESTHandler extends RESTClass {

    function work() {
        $db = new Db();
        $statusCode = 200;
        if (filter_has_var(INPUT_POST, 'mail') && filter_has_var(INPUT_POST, 'password')) {
            $mail = filter_input(INPUT_POST, 'mail');
            $password = filter_input(INPUT_POST, 'password');

            if (!filter_var($mail, FILTER_VALIDATE_EMAIL)) {
                $rawData = array('Code' => 21,
                    'Type' => 'Error',
                    'Message' => 'Wrong email format!');
            } else {
                $db->query("Select * from user where mail = :mail");
                $db->bind('mail', $mail);
                $row = $db->single();

                if (empty($row)) {
                    $db->query("Insert into user (mail, password) values (:mail, :password)");
                    $db->bind(':mail', $mail);
                    $db->bind(':password', $password);
                    $db->execute();
                    $db->disconnect();
                    $statusCode = 200;
                    $rawData = array('Code' => 10,
                        'Type' => 'Success',
                        'Message' => 'Querry executed!');
                } else {
                    $db->disconnect();
                    $rawData = array('Code' => 22,
                        'Type' => 'Error',
                        'Message' => 'User already exists!');
                }
            }
        } else {
            $rawData = array('Code' => 23,
                'Type' => 'Error',
                'Message' => 'Variables missing!');
        }
        $this->response($statusCode, $rawData);
    }

}

?>