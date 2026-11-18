package com.tecsup.controller;
import org.springframework.web.bind.annotation.*;
import java.util.Map;
@RestController
@RequestMapping("/api/pruebas")
public class DemoraController {
 @GetMapping("/demora")
 public Map<String,String> demora() throws InterruptedException { Thread.sleep(10000); return Map.of("mensaje","Respuesta de pedido después de 10 segundos"); }
}
