package com.ntnn.cucumber.controller;

import org.json.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v2/common")
public class MainController {
    @GetMapping(value = "/hello", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> helloWorld() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("errorCode", "200");
        jsonObject.put("message", "Hello");
        return ResponseEntity.ok(jsonObject.toString());
    }
}
