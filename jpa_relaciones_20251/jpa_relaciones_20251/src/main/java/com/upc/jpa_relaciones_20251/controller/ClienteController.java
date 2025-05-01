package com.upc.jpa_relaciones_20251.controller;

import com.upc.jpa_relaciones_20251.dto.ClienteDTO;
import com.upc.jpa_relaciones_20251.dto.ClienteProductoDTO;
import com.upc.jpa_relaciones_20251.dto.ClienteProductoResumenDTO;
import com.upc.jpa_relaciones_20251.service.ClienteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
    final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }
    @GetMapping
    public ResponseEntity<List<ClienteDTO>> listar() {
        return ResponseEntity.ok(clienteService.listar());
    }
    @GetMapping("/{id}")
    public ResponseEntity<ClienteDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(clienteService.buscarPorId(id));
    }
    @PostMapping
    public ResponseEntity<ClienteDTO> guardar(@RequestBody ClienteDTO clienteDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteService.guardar(clienteDTO));
    }
    @PostMapping("/{id}/producto/{id_producto}")
    public ResponseEntity<ClienteProductoDTO> agregarProductoACliente(@PathVariable Long id, @PathVariable Long id_producto) {
        ClienteProductoDTO clienteProductoDTO = new ClienteProductoDTO(id, id_producto);
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteService.agregarProductoACliente(clienteProductoDTO));
    }
    @GetMapping("/resumen")
    public ResponseEntity<List<ClienteProductoResumenDTO>> resumen() {
        return ResponseEntity.ok(clienteService.resumen());
    }
    @GetMapping("/habilitados/{hab}")
    public ResponseEntity<List<ClienteDTO>> habilitados (@PathVariable Boolean hab) {
        return ResponseEntity.ok(clienteService.habilitados(hab));
    }
}
