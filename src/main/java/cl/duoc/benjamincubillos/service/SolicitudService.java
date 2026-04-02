package cl.duoc.benjamincubillos.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.duoc.benjamincubillos.model.Solicitud;
import cl.duoc.benjamincubillos.repository.SolicitudRepository;

@Service
public class SolicitudService {

    @Autowired
    private SolicitudRepository solicitudRepository;

    public List<Solicitud> getSolicitudes() {
        return solicitudRepository.listarSolicitudes();
    }

    public String saveSolicitud(Solicitud sol) {
        if (solicitudRepository.buscarPorId(sol.getId()).isPresent()) {
            return "ID_DUPLICADO";
        }

        solicitudRepository.agregarSolicitud(sol);
        return "OK";
    }

    public String updateSolicitudId(Solicitud sol) {
        if (solicitudRepository.buscarPorId(sol.getId()).isPresent()) {
            solicitudRepository.actuaSolicitud(sol);
            return "OK";
        } else {
            return "NOT_FOUND";
        }
    }

    public String deleteSolicitudId(Long id) {
        solicitudRepository.eliminarSolicitudId(id);
        return "Producto eliminado.";
    }

    public Optional<Solicitud> getSolicitudId(Long id) {
        return solicitudRepository.buscarPorId(id);
    }

    public List<Solicitud> getSolicitudesRut(String rut) {
        return solicitudRepository.buscarPorRut(rut);
    }

    public List<Solicitud> getSolicitudesEstado(String estado) {
        return solicitudRepository.filtrarPorEstado(estado);
    }

    public List<Solicitud> getSolicitudesPrioridad() {
        return solicitudRepository.filtrarPorPrioridad();
    }
}
