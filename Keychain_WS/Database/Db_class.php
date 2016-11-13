<?php

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Database connection class
 *
 * @author Saša Poslončec
 */
class Db {
    private $connection;
    private $error;
    private $statement;
    
    //Connect to db
    public function connect(){
        if(!isset($this->connection)){
            try{
                $config = parse_ini_file('config.ini');
                $this->connection = new PDO("mysql:host=$config[host];dbname=$config[dbname];"
                        . "charset=utf8", $config['username'], $config['password'], 
                        array(PDO::ATTR_EMULATE_PREPARES=>false, PDO::ATTR_DEFAULT_FETCH_MODE=>PDO::FETCH_ASSOC,
                             PDO::ATTR_ERRMODE=> PDO::ERRMODE_EXCEPTION));
            }            
            catch (PDOException $e){
                $this->error = $e->getMessage();
                $this->connection = null;      
            }
        }
        return $this->connection;
    }
    
    //querry preparation
    public function query($query){
        try{
            $this->connection = $this->connect();
            $this->statement = $this->connection->prepare($query);            
        }        
        catch (PDOException $e){
            $this->error = $e->getMessage();
            $this->connection = null;
        }       
    }
    
    //parametrization
    public function bind($param, $value, $type = null){
        if (is_null($type)) {
            switch (true) {
                case is_int($value):
                    $type = PDO::PARAM_INT;
                    break;
                case is_bool($value):
                    $type = PDO::PARAM_BOOL;
                    break;
                case is_null($value):
                    $type = PDO::PARAM_NULL;
                    break;
                default:
                    $type = PDO::PARAM_STR;
            }
        }
        $this->statement->bindValue($param, $value, $type);
    }
    
    //insert, update, delete
    public function execute(){
        return $this->statement->execute();
    }
    
    //multi row select
    public function resultset(){
        $this->execute();
        return $this->statement->fetchAll();
    }
    
    //single row select
    public function single(){
        $this->execute();
        return $this->statement->fetch();
    }
    
    //close connection
    public function disconnect(){
        $this->connection = null;
    }
}
