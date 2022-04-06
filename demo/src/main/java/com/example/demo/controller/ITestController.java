package com.example.demo.controller;


import com.example.demo.dtos.GenericResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;


import java.util.Map;

public interface ITestController {

    ResponseEntity<GenericResponseDTO> guardarArchivo(@RequestBody Map map) throws Exception;

    ResponseEntity<GenericResponseDTO> listarArchivos() throws Exception;

    }
