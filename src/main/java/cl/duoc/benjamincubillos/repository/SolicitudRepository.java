package cl.duoc.benjamincubillos.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import cl.duoc.benjamincubillos.model.Solicitud;

@Repository
public class SolicitudRepository {

    private final Map<Long, Solicitud> mapaSolicitud = new HashMap<>();

    public List<Solicitud> listarSolicitudes() {
        return new ArrayList<>(mapaSolicitud.values());
    }

    public Solicitud agregarSolicitud(Solicitud sol) {
        if (mapaSolicitud.containsKey(sol.getId())) {
            System.out.println("Error: El ID " + sol.getId() + " ya está registrado.");
            return null;
        }

        mapaSolicitud.put(sol.getId(), sol);
        return sol;
    }

    public Solicitud actuaSolicitud(Solicitud sol) {
        mapaSolicitud.put(sol.getId(), sol);
        return sol;
    }

    public void eliminarSolicitudId(Long id) {
        mapaSolicitud.remove(id);
    }

    public Optional<Solicitud> buscarPorId(Long id) {
        return Optional.ofNullable(mapaSolicitud.get(id));
    }
}
