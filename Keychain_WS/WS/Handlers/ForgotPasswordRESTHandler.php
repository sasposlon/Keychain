<?php

require_once("./RESTClass.php");
require_once('./../Database/Db_class.php');

class ForgotPasswordRESTHandler extends RESTClass {
    private $result = array('mail' => '',
        'password' => '',
        'pin' => '');

    function work() {
        $db = new Db();
        $statusCode = 200;
        if (filter_has_var(INPUT_POST, 'mail')) {
            $mail = filter_input(INPUT_POST, 'mail');

            if (!filter_var($mail, FILTER_VALIDATE_EMAIL)) {
                $rawData = $this->getResponseMessage(21, $this->result);
            } else {
                $db->query("Select * from user where mail = :mail");
                $db->bind('mail', $mail);
                $row = $db->single();

                if (empty($row)) {
                    $rawData = $this->getResponseMessage(24, $this->result);
                    
                } else {
                    $adress = $row['mail'];
                    $subject = "Password";
                    $message = "Your password is: ". $row['password'];
                    mail($adress, $subject, $message);
                    $rawData = $this->getResponseMessage(10, $this->result);
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