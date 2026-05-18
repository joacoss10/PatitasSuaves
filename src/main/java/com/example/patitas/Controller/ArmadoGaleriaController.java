package com.example.patitas.Controller;

import com.example.patitas.Exeptions.ApiException;
import com.example.patitas.Service.GaleriaPerroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/Admin/galeria")
public class ArmadoGaleriaController {
    @Autowired
    private GaleriaPerroService galeriaPerroService;
    @PostMapping(value = "/subirGaleria", consumes = "multipart/form-data")
    public ResponseEntity<String> subirImagenPerro(
            @RequestParam("nombrePerro") String nombrePerro,
            @RequestParam("servicioRealizado") String servicioRealizado,
            @RequestParam(value = "comentarios", required = false) String comentarios,
            @RequestParam("foto") MultipartFile archivoFoto) {
        try {
            // Delegamos toda la lógica y validaciones al servicio [cite: 21, 23]
            galeriaPerroService.guardarFotoPerro(nombrePerro, servicioRealizado, comentarios, archivoFoto);

            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("¡Foto de " + nombrePerro + " subida a la galería con éxito!");

        } catch (ApiException e) {
            // Captura tu excepción personalizada y mapea el HttpStatus correspondiente
            return ResponseEntity.status(e.getStatus())
                    .body(e.getMessage());
        }
    }
}
