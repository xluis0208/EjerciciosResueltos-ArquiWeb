package com.upc.jpa_relaciones_20251.controller;

import com.upc.jpa_relaciones_20251.dto.CategoriaDTO;
import com.upc.jpa_relaciones_20251.service.CategoriaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {
    final CategoriaService categoriaService;

    Logger log = Logger.getLogger(CategoriaController.class.getName());

    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }
    @GetMapping
    public ResponseEntity<List<CategoriaDTO>> listar() {
        return ResponseEntity.ok(categoriaService.listar());
    }
    @GetMapping("/{id}")
    public ResponseEntity<CategoriaDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(categoriaService.buscarPorId(id));
    }
    @PostMapping
    public ResponseEntity<CategoriaDTO> guardar(@RequestBody CategoriaDTO categoriaDTO) {
        log.info("Guardando categoria: " + categoriaDTO.toString());
        return ResponseEntity.status(HttpStatus.CREATED).body(categoriaService.guardar(categoriaDTO));
    }
}
