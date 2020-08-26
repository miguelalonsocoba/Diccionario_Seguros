package com.example.dicionario.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.dicionario.constants.Constants;
import com.example.dicionario.dto.TerminoDTO;
import com.example.dicionario.exceptions.ProxyException;
import com.example.dicionario.service.IDiccionarioService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;

/**
 * Class DiccionarioController.
 */
@Slf4j
@RestController
@RequestMapping(path = "/diccionario")
@Api(value = "diccionario", description = "Operaciones CRUD de Diccionario")
@ApiOperation(value = "Consultar, Insertar, Actualizar y Eliminar Terminos del Diccionario", notes = "")
public class DiccionarioController {

	/** The service. */
	@Autowired
	@Qualifier(value = "diccionarioService")
	private IDiccionarioService serviceDiccionario;

	/** The oMapper. */
	@Autowired
	private ObjectMapper oMapper;

	/**
	 * Buscar un termino por ID.
	 * 
	 * @param idTermino of Integer
	 * @return Object String
	 */
	@ApiOperation(code = Constants.STATUSOK, value = "Busca un termino en base a su ID.", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiResponses(value = {
			@ApiResponse(code = Constants.STATUSOK, message = "Consulta exitosa", response = TerminoDTO.class),
			@ApiResponse(code = 400, message = "Conflicto interno en el proceso"),
			@ApiResponse(code = 503, message = "Servicio no Disponible"),
			@ApiResponse(code = 500, message = "Conflictos en el servidor") })
	@GetMapping(path = "/getTermino", produces = "application/json")
	public String getTerminoID(@RequestParam(name = "idTermino") final Integer idTermino) {
		log.info(String.format("getTerminoID() >>>>>> idTermino: %s", idTermino));
		try {
			TerminoDTO termino = serviceDiccionario.getTerminoId(idTermino);
			log.info(String.format("getTerminoID() <<<<<< termino: %s", termino.toString()));
			return oMapper.writeValueAsString(termino);
		} catch (JsonProcessingException e) {
			log.error(String.format("JsonProccesingException: %s", e.getMessage()));
			throw new ProxyException(String.format(Constants.ERROR_SERVICIO_DICCIONARIO_SEGUROS, e.getMessage()));
		}
	}

	/**
	 * Agregar un Termino.
	 * 
	 * @param request of String
	 * @return Object String
	 */
	@ApiOperation(code = Constants.STATUSOK, value = "Agrega un Termino.", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiResponses(value = {
			@ApiResponse(code = Constants.STATUSOK, message = "Consulta exitosa", response = TerminoDTO.class),
			@ApiResponse(code = 400, message = "Conflicto interno en el proceso."),
			@ApiResponse(code = 503, message = "Servicio no Disponible."),
			@ApiResponse(code = 500, message = "Conflictos con el servidor.") })
	@PostMapping(path = "/addTermino", consumes = "application/json", produces = "application/json")
	public String addTermino(@Validated @RequestBody final TerminoDTO request) {
		log.info(String.format("addTermino() >>>>>>> request: %s", request));
		try {
			TerminoDTO response = serviceDiccionario.addTermino(request);
			log.info(String.format("addTermino() <<<<<<<< response: %s", response.toString()));
			return oMapper.writeValueAsString(response);
		} catch (JsonProcessingException e) {
			log.error(String.format("JsonProccesingException: %s", e.getMessage()));
			throw new ProxyException(String.format(Constants.ERROR_SERVICIO_DICCIONARIO_SEGUROS, e.getMessage()));
		}
	}

	/**
	 * Elimina un termino a partir de su ID.
	 * 
	 * @param request of Integer
	 * @return String response
	 * @throws Exception
	 */
	@ApiOperation(code = Constants.STATUSOK, value = "Elimina un Termino.")
	@ApiResponses(value = {
			@ApiResponse(code = Constants.STATUSOK, message = "Consulta exitosa.", response = String.class),
			@ApiResponse(code = 400, message = "Conflicto interno en el proceso."),
			@ApiResponse(code = 503, message = "Servicio no Disponible."),
			@ApiResponse(code = 500, message = "Conflictos con el servidor.") })
	@DeleteMapping(path = "/deletTermino", produces = "application/json")
	public String deletTermino(@RequestParam(name = "id") final Integer id) throws Exception {
		log.info(String.format("deletTermino() >>>>> request: %s", id));
		try {
			serviceDiccionario.deleteById(id);
			log.info(String.format("deletTermino() <<<<< response: %s", "response"));
			return "Termino con id: " + id + " eliminado correctamente.";
		} catch (Exception e) {
			log.info(String.format("Exception: %s", e.getMessage()));
			throw new Exception(String.format("Controller: %s", e.getMessage()));
		}
	}

	/**
	 * Obtiene todos los Terminos.
	 * 
	 * @return List Object String
	 */
	@ApiOperation(value = "Obtiene la lista de los Terminos.", notes = "Retorn una lista de Terminos")
	@ApiResponses(value = {
			@ApiResponse(code = Constants.STATUSOK, message = "Consulta exitosa.", response = TerminoDTO.class, responseContainer = "List"),
			@ApiResponse(code = 400, message = "Conflicto interno en el proceso."),
			@ApiResponse(code = 503, message = "Servicio no Disponible."),
			@ApiResponse(code = 500, message = "Conflictos con el servidor.") })
	@GetMapping(path = "/getAll", produces = "application/json")
	public List<TerminoDTO> getAll() {
		log.info("getAll() >>>>> ");
		List<TerminoDTO> response = serviceDiccionario.listAll();
		log.info("getAll() <<<<<");
		return response;
	}

}
