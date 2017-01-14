<?php

require_once("./RESTClass.php");
require_once('./../Database/Db_class.php');

class AddKeyRESTHandler extends RESTClass {

    private $result = array('mail' => '',
        'password' => '',
        'pin' => '');

    function work() {
        $db = new Db();
        $statusCode = 200;
        if (filter_has_var(INPUT_POST, 'mail') && filter_has_var(INPUT_POST, 'name') && filter_has_var(INPUT_POST, 'description') && filter_has_var(INPUT_POST, 'address') && filter_has_var(INPUT_POST, 'longitude') && filter_has_var(INPUT_POST, 'latitude') && filter_has_var(INPUT_POST, 'code')) {
            $mail = filter_input(INPUT_POST, 'mail');
            $name = filter_input(INPUT_POST, 'name');
            $description = filter_input(INPUT_POST, 'description');
            $address = filter_input(INPUT_POST, 'address');
            $longitude = filter_input(INPUT_POST, 'longitude');
            $latitude = filter_input(INPUT_POST, 'latitude');
            $code = filter_input(INPUT_POST, 'code');

            $db->query("select * from NFCkeys where name = :name and mail = :mail");
            $db->bind(':name', $name);
            $db->bind(':mail', $mail);
            $row = $db->single();
            if (empty($row)) {

                $db->query("INSERT into NFCkeys (name, description, address, longitude, latitude, code, mail) values (:name, :description, :address, :longitude, :latitude, :code, :mail)");
                $db->bind(':name', $name);
                $db->bind(':description', $description);
                $db->bind(':address', $address);
                $db->bind(':longitude', $longitude);
                $db->bind(':latitude', $latitude);
                $db->bind(':code', $code);
                $db->bind(':mail', $mail);
                $db->execute();

                $rawData = $this->getResponseMessage(10, $this->result);
            }
            else {
                $rawData = $this->getHttpStatusMessage(26, $this->result);
            }
        } else {
            $rawData = $this->getResponseMessage(23);
        }
        $db->disconnect();
        $this->response($statusCode, $rawData);
    }

}

?>