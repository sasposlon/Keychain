<?php

/*
  A simple RESTful webservices base class
 */

class RESTClass {

    private $httpVersion = "HTTP/1.1";

    public function setHttpHeaders($contentType, $statusCode) {

        $statusMessage = $this->getHttpStatusMessage($statusCode);

        header($this->httpVersion . " " . $statusCode . " " . $statusMessage);
        header("Content-Type:" . $contentType);
    }

    public function getHttpStatusMessage($statusCode) {
        $httpStatus = array(
            100 => 'Continue',
            101 => 'Switching Protocols',
            200 => 'OK',
            201 => 'Created',
            202 => 'Accepted',
            203 => 'Non-Authoritative Information',
            204 => 'No Content',
            205 => 'Reset Content',
            206 => 'Partial Content',
            300 => 'Multiple Choices',
            301 => 'Moved Permanently',
            302 => 'Found',
            303 => 'See Other',
            304 => 'Not Modified',
            305 => 'Use Proxy',
            306 => '(Unused)',
            307 => 'Temporary Redirect',
            400 => 'Bad Request',
            401 => 'Unauthorized',
            402 => 'Payment Required',
            403 => 'Forbidden',
            404 => 'Not Found',
            405 => 'Method Not Allowed',
            406 => 'Not Acceptable',
            407 => 'Proxy Authentication Required',
            408 => 'Request Timeout',
            409 => 'Conflict',
            410 => 'Gone',
            411 => 'Length Required',
            412 => 'Precondition Failed',
            413 => 'Request Entity Too Large',
            414 => 'Request-URI Too Long',
            415 => 'Unsupported Media Type',
            416 => 'Requested Range Not Satisfiable',
            417 => 'Expectation Failed',
            500 => 'Internal Server Error',
            501 => 'Not Implemented',
            502 => 'Bad Gateway',
            503 => 'Service Unavailable',
            504 => 'Gateway Timeout',
            505 => 'HTTP Version Not Supported');
        return ($httpStatus[$statusCode]) ? $httpStatus[$statusCode] : $statusCode[500];
    }

    public function getResponseMessage($code, $result = null) {
        $data = array(
            10 => array('code' => 10,
                'type' => 'Success',
                'message' => 'Querry executed!',
                'items' => $result),
            11 => array('code' => 11,
                'type' => 'Success',
                'message' => 'Mail and password OK!',
                'items' => $result),
            20 => array('code' => 20,
                'type' => 'Error',
                'message' => 'Service variable missing',
                'items' => $result),
            21 => array('code' => 21,
                'type' => 'Error',
                'message' => 'Wrong email format!',
                'items' => $result),
            22 => array('code' => 22,
                'type' => 'Error',
                'message' => 'Wrong e-mail or password!',
                'items' => $result),
            23 => array('code' => 23,
                'type' => 'Error',
                'message' => 'Variables missing!',
                'items' => $result),
            24 => array('code' => 24,
                'type' => 'Error',
                'message' => 'E-mail does not exist!',
                'items' => $result),
            25 => array('code' => 25,
                'type' => 'Error',
                'message' => 'E-mail already exists!',
                'items' => $result)
        );
        return $data[$code];
    }

    public function response($statusCode, $rawData) {
        $requestContentType = filter_input(INPUT_SERVER, 'HTTP_ACCEPT');
        $this->setHttpHeaders($requestContentType, $statusCode);

        if (strpos($requestContentType, 'application/json') !== false) {
            $response = $this->encodeJson($rawData);
            echo $response;
        } else if (strpos($requestContentType, 'text/html') !== false) {
            $response = $this->encodeHtml($rawData);
            echo $response;
        } else if (strpos($requestContentType, 'application/xml') !== false) {
            $response = $this->encodeXml($rawData);
            echo $response;
        }
    }

    public function encodeJson($responseData) {
        $jsonResponse = json_encode($responseData);
        return $jsonResponse;
    }

    public function encodeXml($responseData) {
        $xml = new SimpleXMLElement('<?xml version="1.0"?><keychain></keychain>');
        foreach ($responseData as $key => $value) {
            $xml->addChild($key, $value);
        }
        return $xml->asXML();
    }

    public function encodeHtml($responseData) {

        $htmlResponse = "<table border='1'>";
        foreach ($responseData as $key => $value) {
            $htmlResponse .= "<tr><td>" . $key . "</td><td>" . $value . "</td></tr>";
        }
        $htmlResponse .= "</table>";
        return $htmlResponse;
    }

}

?>