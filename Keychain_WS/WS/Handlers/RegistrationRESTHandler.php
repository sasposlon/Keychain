<?php

require_once("./RESTClass.php");
require_once('./../Database/Db_class.php');

class RegistrationRESTHandler extends RESTClass {
    private $result = array('mail' => '',
        'password' => '',
        'pin' => '');

    function work() {
        $db = new Db();
        $statusCode = 200;
        if (filter_has_var(INPUT_POST, 'mail') && filter_has_var(INPUT_POST, 'password')) {
            $mail = filter_input(INPUT_POST, 'mail');
            $password = filter_input(INPUT_POST, 'password');

            if (!filter_var($mail, FILTER_VALIDATE_EMAIL)) {
                $rawData = $this->getResponseMessage(21, $this->result);
            } else {
                $db->query("Select * from user where mail = :mail");
                $db->bind('mail', $mail);
                $row = $db->single();

                if (empty($row)) {
                    $db->query("Insert into user (mail, password) values (:mail, :password)");
                    $db->bind(':mail', $mail);
                    $db->bind(':password', $password);
                    $db->execute();
                    
                    $db->query("Select * from user where mail = :mail");
                    $db->bind(':mail', $mail);
                    $data = $db->single();
                    $rawData = $this->getResponseMessage(10, $data);
                } else {
                    $rawData = $this->getResponseMessage(25, $this->result);
                }
            }
        } else {
            $rawData = $this->getResponseMessage(23, $this->result);
        }
        $db->disconnect();
        $this->response($statusCode, $rawData);
    }

}

?>