package com.example.demo.service;


import com.example.demo.dtos.GenericResponseDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;


import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;


@Service
public class TestService implements ITestService {


    private static final Logger logger = LoggerFactory.getLogger(TestService.class);

    private ObjectMapper mapper = new ObjectMapper();

    @Value("${test.path}")
    private String path;


    @Override
    public GenericResponseDTO guardarArchivo(Map map) throws Exception {
        try {

            logger.info(mapper.writeValueAsString(map));
            System.out.println(path);

            String nombreArchivo = map.get("filename").toString();
            String encoding = "UTF-8";
            try {
                PrintWriter writer = new PrintWriter(
                        path.concat(nombreArchivo.contains(".txt") ? nombreArchivo : nombreArchivo.concat(".txt")),
                        encoding);
                writer.println(map.get("text").toString());
                writer.close();
            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }


            return GenericResponseDTO.builder().message("Se creo el fichero " + nombreArchivo).objectResponse(map).statusCode(HttpStatus.OK.value()).build();

        } catch (Exception e) {
            logger.error(e.getMessage());
            return GenericResponseDTO.builder().message("Error al crear el fichero ").objectResponse(null).statusCode(HttpStatus.BAD_REQUEST.value()).build();
        }
    }

    @Override
    public GenericResponseDTO listarArchivos() throws Exception {
        try {

            File dir = new File(path);
            String[] list = dir.list();

            return GenericResponseDTO.builder().message("Lista de fichero ").objectResponse(list).statusCode(HttpStatus.OK.value()).build();

        } catch (Exception e) {
            logger.error(e.getMessage());
            return GenericResponseDTO.builder().message("Error al listar los fichero ").objectResponse(null).statusCode(HttpStatus.BAD_REQUEST.value()).build();
        }
    }

}
