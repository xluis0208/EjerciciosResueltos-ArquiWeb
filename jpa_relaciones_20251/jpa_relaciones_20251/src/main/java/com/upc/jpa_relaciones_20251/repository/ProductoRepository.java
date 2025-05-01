package com.upc.jpa_relaciones_20251.repository;

import com.upc.jpa_relaciones_20251.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
}
