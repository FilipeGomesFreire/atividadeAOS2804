package com.funny.autismo_app.controller;

import java.util.HashMap;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;
import java.util.HashMap;

@RestController
public class StatusController {

    @GetMapping("/")
    public String status() {
        return "Backend está rodando!";
    }
    @GetMapping("/testjson")
    public Map<String, Object> testeJson() {
        Map<String, Object> map = new HashMap<>();
        map.put("ok", true);
        map.put("mensagem", "O Spring está convertendo JSON");
        return map;
}

}
