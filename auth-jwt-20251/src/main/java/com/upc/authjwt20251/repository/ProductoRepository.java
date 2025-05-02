package com.upc.authjwt20251.repository;

import com.upc.authjwt20251.model.Producto;
import jakarta.persistence.Tuple;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
    @Query(value = "select p.nombre, c.descripcion from categoria c LEFT JOIN Producto p on p.categoria_id = c.id", nativeQuery = true)
    List<Tuple> listado();

    List<Producto> findByN(Long id);
}
