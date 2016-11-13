<?php

include '../Database/Db_class.php';
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$db = new Db();
if (filter_has_var(INPUT_POST, 'mail') && filter_has_var(INPUT_POST, 'password')) {
    $mail = filter_input(INPUT_POST, 'mail');
    $password = filter_input(INPUT_POST, 'password');

    if (!filter_var($mail, FILTER_VALIDATE_EMAIL)) {
        echo "ERROR21";
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
            echo "OK10";
        } else {
            $db->disconnect();
            echo "ERROR20";
        }
    }
}