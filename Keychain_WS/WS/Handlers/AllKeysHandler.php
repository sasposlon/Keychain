<?php

require_once("./RESTClass.php");
require_once('./../Database/Db_class.php');

class AllKeysHandler extends RESTClass {

    function work() {
        $db = new Db();
        $statusCode = 200;
        if (filter_has_var(INPUT_POST, 'mail')) {

            $mail = filter_input(INPUT_POST, 'mail');

            $db->query("SELECT u.mail, k.description, k.name, k.hash FROM NFCkeys as k, user_keys as uk, user as u WHERE u.mail = :mail and u.mail = uk.mail and uk.id_key = k.id_key");
            $db->bind(':mail', $mail);
            $result = $db->resultset();
            if (!empty($result)) {
                $rawData = $this->getResponseMessage(10, $result);
            }
            else {
                $rawData = $this->getResponseMessage(24);
            }
        } else {
            $rawData = $this->getResponseMessage(23);
        }
        $db->disconnect();
        $this->response($statusCode, $rawData);
    }

}

?>