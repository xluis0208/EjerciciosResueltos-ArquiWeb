package com.upc.jpa_relaciones_20251.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClienteProductoResumenDTO {
    private String cliente;
    private String producto;
}
