package pe.edu.upc.examenfinal.serviceimplements;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import pe.edu.upc.examenfinal.dtos.CategoriaDTO;
import pe.edu.upc.examenfinal.entities.Categoria;
import pe.edu.upc.examenfinal.repositories.CategoriaRepository;

import java.util.ArrayList;
import java.util.List;
@Service
public class CategoriaService {
    final CategoriaRepository categoriaRepository;

    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }
    public List<CategoriaDTO> listar() {
        List<Categoria> categorias = categoriaRepository.findAll();
        List<CategoriaDTO> categoriaDTOS = new ArrayList<>();
        ModelMapper mapper = new ModelMapper();
        for (Categoria categoria : categorias) {
            CategoriaDTO categoriaDTO = mapper.map(categoria, CategoriaDTO.class);
            categoriaDTOS.add(categoriaDTO);
        }
        return categoriaDTOS;
    }
    public Categoria agregar(CategoriaDTO categoriaDTO) {
        ModelMapper mapper = new ModelMapper();
        Categoria categoria = mapper.map(categoriaDTO, Categoria.class);
        return categoriaRepository.save(categoria);
    }
}
