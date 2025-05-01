package com.upc.jpa_relaciones_20251.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClienteProductoDTO {
    private Long clienteId;
    private Long productoId;
}
