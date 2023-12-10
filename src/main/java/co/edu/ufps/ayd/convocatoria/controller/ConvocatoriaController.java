package co.edu.ufps.ayd.convocatoria.controller;

import java.util.List;

import co.edu.ufps.ayd.convocatoria.model.dto.PropuestaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import co.edu.ufps.ayd.convocatoria.model.entity.ConvocatoriaEntity;
import co.edu.ufps.ayd.convocatoria.service.implementations.ConvocatoriaService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/convocatoria")
@Validated
@CrossOrigin(origins = "*")
public class ConvocatoriaController {
    
    @Autowired
    ConvocatoriaService convocatoriaService;

    @PostMapping("/abrir")
    @PreAuthorize("hasAuthority('ROL_ADMIN')")
    public ResponseEntity<String> abrirConvocatoria(@Valid @RequestBody ConvocatoriaEntity convocatoriaEntity) {
        try {
            convocatoriaService.abrirConvocatoria(convocatoriaEntity);
            return ResponseEntity.ok().body("La convocatoria se abrio correctamente");  
        } catch (Exception e) {
            throw e;
        }

    }

    @GetMapping("/cerrar")
    @PreAuthorize("hasAuthority('ROL_ADMIN')")
    public ResponseEntity<String> cerrarConvocatoria(){
        try {
            convocatoriaService.cerrarConvocatoria();
            return ResponseEntity.ok().body("La convocatoria se cerro correctamente");  
        } catch (Exception e) {
            throw e;
        }
    }

    @GetMapping("/abierta")
    @PreAuthorize("hasAuthority('ROL_ADMIN')")
    public ConvocatoriaEntity buscarConvocatoriaAbierta(){
        try {
            return convocatoriaService.buscarConvocatoriaHabilitada();
        } catch (Exception e) {
            throw e;
        }
    }

    @PutMapping("/modificar")
    @PreAuthorize("hasAuthority('ROL_ADMIN')")
    public ResponseEntity<String> modificarConvocatoria(@Valid @RequestBody ConvocatoriaEntity convocatoriaModificada){
        try {
            convocatoriaService.modificarConvocatoria(convocatoriaModificada);
            return ResponseEntity.ok().body("Las fechas de la convocatoria se modificaron correctamente");
        } catch (Exception e) {
            throw e;
        }
    }

    @GetMapping("/listarPasadas")
    @PreAuthorize("hasAuthority('ROL_ADMIN')")
    public List<ConvocatoriaEntity> listarConvocatoriasPasadas(){
        return convocatoriaService.listarConvocatoriasPasadas();
    }
}
