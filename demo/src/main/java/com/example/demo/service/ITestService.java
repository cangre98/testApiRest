package com.example.demo.service;


import com.example.demo.dtos.GenericResponseDTO;

import java.util.Map;

public interface ITestService {

    GenericResponseDTO guardarArchivo(Map estadoDTO) throws Exception;

    GenericResponseDTO listarArchivos() throws Exception;



}
