package com.tecsup.controller;
import org.springframework.web.bind.annotation.*;
import java.util.Map;
@RestController
@RequestMapping("/api/pruebas")
public class DemoraController {
 @GetMapping("/demora")
 public Map<String,String> demora() throws InterruptedException { Thread.sleep(5000); return Map.of("mensaje","Respuesta de cliente después de 5 segundos"); }
}
