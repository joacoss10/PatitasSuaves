package com.example.patitas.Service;

import com.example.patitas.Dtos.GaleriaPerroResponseDto;
import com.example.patitas.Exeptions.ApiException;
import com.example.patitas.Model.GaleriaPerro;
import com.example.patitas.Repository.GaleriaPerroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GaleriaPerroService {

    @Autowired
    private GaleriaPerroRepository repository;

    public GaleriaPerro guardarFotoPerro(String nombrePerro, String servicioRealizado, String comentarios, MultipartFile archivoFoto) {
        // Validamos si el archivo viene vacío usando tu excepción personalizada
        if (archivoFoto == null || archivoFoto.isEmpty()) {
            throw new ApiException("Por favor, selecciona una imagen válida.", HttpStatus.BAD_REQUEST);
        }

        try {
            GaleriaPerro perro = new GaleriaPerro();
            perro.setNombrePerro(nombrePerro);
            perro.setServicioRealizado(servicioRealizado);
            perro.setComentarios(comentarios);

            // Convertimos la imagen a bytes para almacenarla en la BD
            perro.setFoto(archivoFoto.getBytes());

            return repository.save(perro);

        } catch (IOException e) {
            // Si ocurre un error de lectura/escritura del archivo, lanzamos un 500
            throw new ApiException("Error interno al procesar la imagen: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    public List<GaleriaPerroResponseDto> obtenerGaleria() {
        List<GaleriaPerro> perros = repository.findAll();

        return perros.stream().map(perro -> new GaleriaPerroResponseDto(
                perro.getId(),
                perro.getNombrePerro(),
                perro.getServicioRealizado(),
                perro.getComentarios(),
                // Construimos la URL dinámica apuntando al endpoint que crearemos abajo
                "https://patitassuaves-backend.onrender.com/auth/vistaGaleria/galeria/foto/" + perro.getId()
        )).collect(Collectors.toList());
    }

    // 2. Obtener solo los bytes de la foto por su ID
    public byte[] obtenerFotoBytes(Long id) {
        GaleriaPerro perro = repository.findById(id)
                .orElseThrow(() -> new ApiException("La foto del perro no existe.", HttpStatus.NOT_FOUND));

        return perro.getFoto();
    }
}