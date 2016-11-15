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
                $rawData = array('code' => 21,
                    'type' => 'Error',
                    'message' => 'Wrong email format!',
                    'items' => '');
            } else {
                $db->query("Select * from user where mail = :mail");
                $db->bind('mail', $mail);
                $row = $db->single();

                if (empty($row)) {
                    $db->query("Insert into user (mail, password) values (:mail, :password)");
                    $db->bind(':mail', $mail);
                    $db->bind(':password', $password);
                    $db->execute();
                    $statusCode = 200;
                    $rawData = array('code' => 10,
                        'type' => 'Success',
                        'message' => 'Querry executed!',
                        'items' => '');
                } else {
                    $db->query("Select * from user where mail = :mail and password = :password");
                    $db->bind('mail', $mail);
                    $db->bind('password', $password);
                    $row = $db->single();
                    if (!empty($row)) {
                        $rawData = array('code' => 11,
                            'type' => 'Success',
                            'message' => 'Mail and password OK!',
                            'items' => '');
                    } else {
                        $rawData = array('code' => 22,
                            'type' => 'Error',
                            'message' => 'Wrong password!',
                            'items' => '');
                    }
                }
            }
        } else {
            $rawData = array('code' => 23,
                'type' => 'Error',
                'message' => 'Variables missing!',
                'items' => '');
        }
        $db->disconnect();
        $this->response($statusCode, $rawData);
    }

}

?>