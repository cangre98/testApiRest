package com.example.demo.controller;

import com.example.demo.dtos.GenericResponseDTO;
import com.example.demo.service.ITestService;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.server.ResponseStatusException;


import java.util.Map;

@RestController
@RequestMapping("/v1")
@CrossOrigin
public class TestController implements ITestController {

    private static final Logger logger = LoggerFactory.getLogger(TestController.class);

    @Autowired
    ITestService iTestService;


    @Override
    @PostMapping(path = "/guardarArchivo", produces = "application/json", consumes = "application/json")
    @ApiOperation(value = "guardar en un archivo con el nombre del contenido de \"filename\" con ruta base definida en un archivo de propiedades", notes = "notas")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La creacion fue exitosa", response = GenericResponseDTO.class)
    })
    public ResponseEntity<GenericResponseDTO> guardarArchivo(@RequestBody @ApiParam(type = "Map", value = "{\n" +
            "    \"text\": \"Contenido del archivo \",\n" +
            "    \"filename\": \"Nombre del archivo23\"\n" +
            "}", required = true) Map map) throws Exception {
        try {
            System.out.println("Thread.currentThread().getStackTrace()[1].getMethodName() " + Thread.currentThread().getStackTrace()[1].getMethodName());

            GenericResponseDTO genericResponseDTO = iTestService.guardarArchivo(map);

            return new ResponseEntity(
                    genericResponseDTO, HttpStatus.valueOf(genericResponseDTO.getStatusCode())
            );

        } catch (Exception e) {
            logger.error(Thread.currentThread().getStackTrace()[1].getMethodName(), e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    "Ocurrio un error inesperado");
        }
    }

    @Override
    @GetMapping(path = "/listarArchivos", produces = "application/json", consumes = "application/json")
    @ApiOperation(value = "listar los nombres de los archivos que se encuentran en el directorio ruta base definida en un archivo de propiedades", notes = "notas")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La consulta fue exitosa", response = GenericResponseDTO.class)
    })
    public ResponseEntity<GenericResponseDTO> listarArchivos() throws Exception {
        try {
            System.out.println("Thread.currentThread().getStackTrace()[1].getMethodName() " + Thread.currentThread().getStackTrace()[1].getMethodName());

            GenericResponseDTO genericResponseDTO = iTestService.listarArchivos();

            return new ResponseEntity(
                    genericResponseDTO, HttpStatus.valueOf(genericResponseDTO.getStatusCode())
            );

        } catch (Exception e) {
            logger.error(Thread.currentThread().getStackTrace()[1].getMethodName(), e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    "Ocurrio un error inesperado");
        }
    }


}
