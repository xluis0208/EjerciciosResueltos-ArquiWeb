package backend.project.controllers;

import backend.project.dtos.LnCompanySummaryDTO;
import backend.project.entities.LnCompany;
import backend.project.services.LnCompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/lara/companies")
@CrossOrigin("*")
public class LnCompanyController {

    @Autowired
    private LnCompanyService lnCompanyService;

    @GetMapping("/list")
    public ResponseEntity<List<LnCompany>> listarTodasLasEmpresas() {
        List<LnCompany> lnCompanies = lnCompanyService.listAllLnCompanies();
        if (lnCompanies.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(lnCompanies, HttpStatus.OK);
    }
    @GetMapping("/summary")
    public ResponseEntity<List<LnCompanySummaryDTO>> listarResumenEmpresas() {
        List<LnCompanySummaryDTO> companySummaries = lnCompanyService.getLnCompaniesSummary();
        if (companySummaries.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(companySummaries, HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<LnCompany> registrarEmpresa(@RequestBody LnCompany lnCompany) {
        LnCompany nuevaEmpresa = lnCompanyService.insertLnCompany(lnCompany);
        return new ResponseEntity<>(nuevaEmpresa, HttpStatus.CREATED);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarEmpresa(@PathVariable("id") Long id) {
        lnCompanyService.deleteLnCompany(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @GetMapping("/{id}")
    public ResponseEntity<LnCompany> obtenerEmpresaPorId(@PathVariable("id") Long id) {
        LnCompany lnCompany = lnCompanyService.findByLnId(id);
        if (lnCompany != null) {
            return new ResponseEntity<>(lnCompany, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}