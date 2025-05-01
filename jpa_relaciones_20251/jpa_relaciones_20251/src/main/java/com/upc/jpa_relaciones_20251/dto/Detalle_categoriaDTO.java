package com.upc.jpa_relaciones_20251.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Detalle_categoriaDTO {
    private Long id;
    private String detalle;
    private Date fecha_inicio;
    private Long categoriaId;
}
