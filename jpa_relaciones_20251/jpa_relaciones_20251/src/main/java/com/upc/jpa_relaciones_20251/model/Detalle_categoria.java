package com.upc.jpa_relaciones_20251.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Detalle_categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String detalle;
    private Date fecha_inicio;
    @OneToOne
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;
}
