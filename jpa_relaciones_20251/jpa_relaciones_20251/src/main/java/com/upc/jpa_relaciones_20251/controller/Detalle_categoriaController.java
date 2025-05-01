package com.upc.jpa_relaciones_20251.controller;

import com.upc.jpa_relaciones_20251.dto.Detalle_categoriaDTO;
import com.upc.jpa_relaciones_20251.service.Detalle_categoriaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/detalle_categorias")
public class Detalle_categoriaController {
    final Detalle_categoriaService detalle_categoriaService;

    public Detalle_categoriaController(Detalle_categoriaService detalleCategoriaService) {
        detalle_categoriaService = detalleCategoriaService;
    }
    @GetMapping
    public ResponseEntity<List<Detalle_categoriaDTO>> listar() {
        return ResponseEntity.ok(detalle_categoriaService.listar());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Detalle_categoriaDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(detalle_categoriaService.buscarPorId(id));
    }
    @PostMapping
    public ResponseEntity<Detalle_categoriaDTO> guardar(@RequestBody Detalle_categoriaDTO detalle_categoriaDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(detalle_categoriaService.guardar(detalle_categoriaDTO));
    }
}
