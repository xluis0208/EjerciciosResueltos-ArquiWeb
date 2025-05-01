package pe.edu.upc.examenfinal.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upc.examenfinal.entities.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
}
