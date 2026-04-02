package cl.duoc.benjamincubillos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.duoc.benjamincubillos.model.Solicitud;
import cl.duoc.benjamincubillos.service.SolicitudService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("api/v1/solicitudes")
public class SolicitudController {

    @Autowired
    private SolicitudService solicitudService;

    @GetMapping
    public ResponseEntity<List<Solicitud>> listarSolicitudes() {
        List<Solicitud> lista = solicitudService.getSolicitudes();
        return ResponseEntity.ok(lista);
    }

    @PostMapping
    public ResponseEntity<String> agregarSolicitud(@Valid @RequestBody Solicitud sol) {
        String resultado = solicitudService.saveSolicitud(sol);

        return switch (resultado) {
            case "OK" -> new ResponseEntity<>("Solicitud ingresada con éxito", HttpStatus.CREATED);
            case "ID_DUPLICADO" -> ResponseEntity.badRequest().body("Error: El ID " + sol.getId() + " ya está en uso.");
            default -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        };
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> actualizarPorId(@PathVariable Long id, @Valid @RequestBody Solicitud sol) {
        sol.setId(id);
        String resultado = solicitudService.updateSolicitudId(sol);

        return switch (resultado) {
            case "OK" -> ResponseEntity.ok("Solicitud actualizada con éxito.");
            case "NOT_FOUND" -> ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontro la solicitud con ID " + id);
            default -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        };
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarSolicitudId(@PathVariable Long id) {
        solicitudService.deleteSolicitudId(id);
        return ResponseEntity.noContent().build();
    }
    
    @GetMapping("/{id}/id")
    public ResponseEntity<Solicitud> buscarSolicitudId(@PathVariable Long id) {
        return solicitudService.getSolicitudId(id)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{rut}/rut")
    public ResponseEntity<List<Solicitud>> buscarSolicitudesRut(@PathVariable String rut) {
        List<Solicitud> lista = solicitudService.getSolicitudesRut(rut);
        return lista.isEmpty() 
            ? ResponseEntity.notFound().build() 
            : ResponseEntity.ok(lista);
    }

    @GetMapping("/{estado}")
    public ResponseEntity<List<Solicitud>> filtrarPorEstado(@PathVariable String estado) {
        List<Solicitud> lista = solicitudService.getSolicitudesEstado(estado);
        return lista.isEmpty()
            ? ResponseEntity.notFound().build()
            : ResponseEntity.ok(lista);
    }

    @GetMapping("/prioridad")
    public ResponseEntity<List<Solicitud>> listarPorPrioridad() {
        List<Solicitud> lista = solicitudService.getSolicitudesPrioridad();
        return lista.isEmpty() 
            ? ResponseEntity.noContent().build() 
            : ResponseEntity.ok(lista);
    }

}
