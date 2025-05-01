package com.upc.jpa_relaciones_20251.controller;

import com.upc.jpa_relaciones_20251.dto.ProductoDTO;
import com.upc.jpa_relaciones_20251.service.ProductoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/productos")
public class ProductoController {
    final ProductoService productoService;

    Logger log = Logger.getLogger(ProductoController.class.getName());

    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }
    @GetMapping
    public ResponseEntity<List<ProductoDTO>> listar() {
        return ResponseEntity.ok(productoService.listar());
    }
    @GetMapping("/{id}")
    public ResponseEntity<ProductoDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(productoService.buscarPorId(id));
    }
    @PostMapping
    public ResponseEntity<ProductoDTO> guardar(@RequestBody ProductoDTO productoDTO) {
        log.info("Guardando producto: " + productoDTO.toString());
        return ResponseEntity.status(HttpStatus.CREATED).body(productoService.guardar(productoDTO));
    }
}

