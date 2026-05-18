package com.example.patitas.Controller;

import com.example.patitas.Dtos.GaleriaPerroResponseDto;
import com.example.patitas.Exeptions.ApiException;
import com.example.patitas.Service.GaleriaPerroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/auth/vistaGaleria")
public class VistaGaleriaController {
    @Autowired
    private GaleriaPerroService galeriaPerroService;

    @GetMapping("/obtenerGaleria")
    public List<GaleriaPerroResponseDto> obtenerGaleria() {
        return galeriaPerroService.obtenerGaleria();
    }
    @GetMapping("/galeria/foto/{id}")
    public ResponseEntity<byte[]> obtenerFotoPerro(@PathVariable Long id) {
        try {
            byte[] imagenBytes = galeriaPerroService.obtenerFotoBytes(id);

            // Retornamos los bytes configurando las cabeceras para que el navegador entienda que es una imagen
            return ResponseEntity.ok()
                    .contentType(MediaType.IMAGE_JPEG) // Puedes cambiarlo a IMAGE_PNG o usar detección dinámica si lo prefieres
                    .body(imagenBytes);

        } catch (ApiException e) {
            // Reutilizamos tu manejador para devolver el 404 si el ID no existe
            return ResponseEntity.status(e.getStatus()).body(null);
        }
    }
}