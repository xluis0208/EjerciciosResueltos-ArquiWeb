package backend.project.controllers;

import backend.project.entities.LnSoftware;
import backend.project.services.LnSoftwareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/lara/softwares")
@CrossOrigin("*")
public class LnSoftwareController {

    @Autowired
    private LnSoftwareService lnSoftwareService;

    @GetMapping("/list")
    public ResponseEntity<List<LnSoftware>> listarTodosLosSoftwares() {
        List<LnSoftware> lnSoftwares = lnSoftwareService.listAllLnSoftwares();
        if (lnSoftwares.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(lnSoftwares, HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<LnSoftware> registrarSoftware(@RequestBody LnSoftware lnSoftware) {
        LnSoftware nuevoSoftware = lnSoftwareService.insertLnSoftware(lnSoftware);
        return new ResponseEntity<>(nuevoSoftware, HttpStatus.CREATED);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarSoftware(@PathVariable("id") Long id) {
        lnSoftwareService.deleteLnSoftware(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @GetMapping("/{id}")
    public ResponseEntity<LnSoftware> obtenerSoftwarePorId(@PathVariable("id") Long id) {
        LnSoftware lnSoftware = lnSoftwareService.findByLnId(id);
        if (lnSoftware != null) {
            return new ResponseEntity<>(lnSoftware, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/category/{category}")
    public ResponseEntity<List<LnSoftware>> listarSoftwaresPorCategoria(@PathVariable("category") String category) {
        List<LnSoftware> lnSoftwares = lnSoftwareService.findByLnCategory(category);
        if (lnSoftwares.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(lnSoftwares, HttpStatus.OK);
    }
    @GetMapping("/year/{year}")
    public ResponseEntity<List<LnSoftware>> listarSoftwaresPorAnio(@PathVariable("year") int year) {
        List<LnSoftware> lnSoftwares = lnSoftwareService.findByLnYear(year);
        if (lnSoftwares.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(lnSoftwares, HttpStatus.OK);
    }
}