<?php

require_once("./RESTClass.php");
require_once('./../Database/Db_class.php');

class LoginRESTHandler extends RESTClass {

    function work() {
        $db = new Db();
        $statusCode = 200;
        if (filter_has_var(INPUT_POST, 'mail') && filter_has_var(INPUT_POST, 'password')) {
            $mail = filter_input(INPUT_POST, 'mail');
            $password = filter_input(INPUT_POST, 'password');

            if (!filter_var($mail, FILTER_VALIDATE_EMAIL)) {
                $rawData = $this->getResponseMessage(21);
            } else {
                $db->query("Select * from user where mail = :mail and password = :password");
                $db->bind('mail', $mail);
                $db->bind('password', $password);
                $row = $db->single();

                if (!empty($row)) {
                    $rawData = $this->getResponseMessage(10);
                } else {
                    $rawData = $this->getResponseMessage(22);
                }
            }
        } else {
            $rawData = $this->getResponseMessage(23);
        }
        $db->disconnect();
        $this->response($statusCode, $rawData);
    }

}

?>