package com.upc.authjwt20251.controller;

import com.upc.authjwt20251.dto.CategoriaTransferidaDTO;
import com.upc.authjwt20251.dto.ListadoDTO;
import com.upc.authjwt20251.dto.ProductoDTO;
import com.upc.authjwt20251.service.ProductoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/producto")
public class ProductoController {
    final ProductoService productoService;

    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }
    @GetMapping("/listar")
    public ResponseEntity<List<ProductoDTO>> listar() {
        return ResponseEntity.ok(productoService.listar());
    }
    @GetMapping("/listar/listado")
    public ResponseEntity<List<ListadoDTO>> listado() {
        return ResponseEntity.ok(productoService.listado());
    }
    @PostMapping("/agregar")
    public ResponseEntity<ProductoDTO> agregar(@RequestBody ProductoDTO productoDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(productoService.agregar(productoDTO));
    }
    @PutMapping("/transferir/{id}")
    public ResponseEntity<ProductoDTO> transferir(@PathVariable Long id, @RequestBody CategoriaTransferidaDTO categoriaId) {
        return ResponseEntity.status(HttpStatus.OK).body(productoService.transferir(id, categoriaId));
    }
}
