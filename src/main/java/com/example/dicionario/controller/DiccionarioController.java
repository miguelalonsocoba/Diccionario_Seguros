package com.example.dicionario.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.dicionario.config.SwaggerConfig;
import com.example.dicionario.constants.Constants;
import com.example.dicionario.dto.TerminoDTO;
import com.example.dicionario.entity.response.ResponseBulkLoad;
import com.example.dicionario.exceptions.ProxyException;
import com.example.dicionario.service.IDiccionarioService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;
import lombok.extern.slf4j.Slf4j;

/**
 * Class DiccionarioController.
 */
@Slf4j
@RestController
@RequestMapping(path = "/diccionario")
@Api(value = "diccionario", tags = {SwaggerConfig.TAG_1})
@ApiOperation(value = "Consultar, Insertar, Actualizar y Eliminar Terminos del Diccionario", notes = "")
@SwaggerDefinition(tags = {
		@Tag(name = "Example", description = "Example")
})
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
	@GetMapping(path = "/getTerminoById", produces = "application/json")
	public String getTerminoID(@RequestParam(name = "idTermino") final Integer idTermino) {
		log.info(String.format("getTerminoID() >>>>>> idTermino: %s", idTermino));
		try {
			if (idTermino == null) {
				throw new NullPointerException("The param idTermino es null: ");
			}
			TerminoDTO termino = serviceDiccionario.getTerminoId(idTermino);
			log.info(String.format("getTerminoID() <<<<<< termino: %s", termino.toString()));
			return oMapper.writeValueAsString(termino);
		} catch (ProxyException e) {
			log.error(String.format("ProxyException: %s", e.getMessage()));
			throw new ProxyException(String.format(Constants.ERROR_SERVICIO_DICCIONARIO_SEGUROS, e.getMessage()));
		} catch (JsonProcessingException e) {
			log.error(String.format("JsonProcessingException: %s", e.getMessage()));
			throw new ProxyException(String.format(Constants.ERROR_SERVICIO_DICCIONARIO_SEGUROS, e.getMessage()));
		}
	}

	/**
	 * Agregar un Termino.
	 * 
	 * @param request of String
	 * @return Object String
	 * @throws Exception
	 */
	@ApiOperation(code = Constants.STATUSOK, value = "Agrega un Termino.", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiResponses(value = {
			@ApiResponse(code = Constants.STATUSOK, message = "Consulta exitosa", response = TerminoDTO.class),
			@ApiResponse(code = 400, message = "Conflicto interno en el proceso."),
			@ApiResponse(code = 503, message = "Servicio no Disponible."),
			@ApiResponse(code = 500, message = "Conflictos con el servidor.") })
	@PostMapping(path = "/addTermino", consumes = "application/json", produces = "application/json")
	public String addTermino(@Validated @RequestBody final TerminoDTO request) throws Exception {
		log.info(String.format("addTermino() >>>>>>> request: %s", request));
		try {
			TerminoDTO response = serviceDiccionario.addTermino(request);
			log.info(String.format("addTermino() <<<<<<<< response: %s", response.toString()));
			return oMapper.writeValueAsString(response);
		} catch (JsonProcessingException e) {
			log.error(String.format("JsonProccesingException: %s", e.getMessage()));
			throw new ProxyException(String.format(Constants.ERROR_SERVICIO_DICCIONARIO_SEGUROS, e.getMessage()));
		} catch (Exception e) {
			log.error("Fallo al insertar Termino: " + e.getMessage());
			throw new Exception(String.format("Fallo al insertar Termino: %s", e.getMessage()));
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
			if (id == null) {
				throw new NullPointerException("El parametro id es null");
			}
			String response = serviceDiccionario.deleteById(id);
			log.info(String.format("deletTermino() <<<<< response: %s", "response"));
			return "Termino con id: " + id + " eliminado correctamente: " + response;
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
	public List<TerminoDTO> getAll() throws Exception {
		log.info("getAll() >>>>> ");
		List<TerminoDTO> response;
		try {
			response = serviceDiccionario.listAll();
		} catch (Exception e) {
			log.info("Error: " + e.getMessage());
			throw new Exception(e.getMessage());
		}
		log.info("getAll() <<<<<");
		return response;
	}

	/**
	 * Obtiene un termino a partir de su nombre.
	 * 
	 * return List TerminoDTO
	 */
	@ApiOperation(value = "Obtiene termino(s)", notes = "Obtendra los terminos que coincidan con el valor que se pasa como parametro.")
	@ApiResponses(value = {
			@ApiResponse(code = Constants.STATUSOK, message = "Consulta exitosa.", response = TerminoDTO.class, responseContainer = "List"),
			@ApiResponse(code = 400, message = "Conflicto interno en el proceso"),
			@ApiResponse(code = 503, message = "Servicio no Disponible."),
			@ApiResponse(code = 500, message = "Conflictos con el servidor.") })
	@GetMapping(path = "getByTermino", produces = "application/json")
	public List<TerminoDTO> getTerminoByName(@RequestParam(name = "termino") final String termino) {
		log.info("getTerminoByName() >>>> requestPAram: %s", termino);
		if (termino.isEmpty()) {
			log.info("Error: El parametro termino tienen un valor nulo.");
			throw new NullPointerException("EL parametro termino tienen un valor nulo");
		}
		List<TerminoDTO> response = serviceDiccionario.getTerminoByNombre(termino);
		log.info("getTErminoNyName() <<<< response: %s", response.toString());
		return response;
	}

	/**
	 * Actualiza un termino.
	 * 
	 * @param termino the termino
	 * @return object String
	 * @throws Exception the exception
	 */
	@ApiOperation(value = "Actualiza Termino", notes = "Actualiza la informacion de un Termino si este previamente existe.")
	@ApiResponses({
			@ApiResponse(code = Constants.STATUSOK, message = "Actualizacion exitosa", response = TerminoDTO.class),
			@ApiResponse(code = 400, message = "Conflicto interno en el proceso."),
			@ApiResponse(code = 500, message = "Conflicto con el servidor."),
			@ApiResponse(code = 503, message = "Servicio no disponible.") })
	@PutMapping(path = "updateTermino", produces = "application/json", consumes = "application/json")
	public String updateTermino(@RequestBody final TerminoDTO termino) throws Exception {
		log.info("updateTermino() >>>>> request: %s", termino.toString());
		TerminoDTO response = serviceDiccionario.updateTermino(termino);
		return oMapper.writeValueAsString(response);
	}

	/**
	 * Elimina todas las coincidencias a partir del nombre proporcionado.
	 * 
	 * @param termino the termino
	 * @return object String
	 * @throws Exception the Exception
	 */
	@ApiOperation(value = "Delete Termino(s)", notes = "Elimina todas las coincidencias a partir del nombre proporcionado.")
	@ApiResponses({ @ApiResponse(code = Constants.STATUSOK, message = "Eliminacion exitosa", response = String.class),
			@ApiResponse(code = 400, message = "Conflicto interno en el proceso."),
			@ApiResponse(code = 500, message = "Conflicto con el Servidor."),
			@ApiResponse(code = 503, message = "Servicio no Disponible.") })
	@DeleteMapping(path = "deleteByName")
	public String deleteByName(@RequestParam(name = "termino", required = true) final String termino) throws Exception {
		log.info("deleteByName() >>>>> requestParam: " + termino);
		Long response = serviceDiccionario.deleteByName(termino);
		log.info("deleteByName() <<<<< response: " + termino);
		return Constants.MESSAGE_ELEMENTS_DELETE + response;
	}

	/**
	 * Realiza la carga masiva de datos.
	 * 
	 * @param terminos the terminos
	 * @param rollBack the rollBack
	 * @return object ResponseBulkLoad
	 * @throws Exception the exception
	 */
	@ApiOperation(value = "BulkLoad", notes = "Realiza una carga masiva de datos. Si el parametre rollBack tiene valor de true este realizara un rollback de los datos insertados en la BD, esto siempre y cuando no se pueda insetar un elementeo de la lista por alguna razon.")
	@ApiResponses({
			@ApiResponse(code = Constants.STATUSOK, message = "Carga exitosa.", response = ResponseBulkLoad.class),
			@ApiResponse(code = 400, message = "Conflicto interno en el Proceso."),
			@ApiResponse(code = 500, message = "Conflicto con el Servidor."),
			@ApiResponse(code = 503, message = "Servicio no Disponible.") })
	@PostMapping(path = "bulkLoad", produces = "application/json", consumes = "application/json")
	public ResponseBulkLoad bulkLoad(@RequestBody final List<TerminoDTO> terminos,
			@RequestParam(name = "rollBack") final String rollBack) throws Exception {
		log.info("bulkLoad() >>>>> request: " + terminos.toString() + " - " + rollBack);
		return serviceDiccionario.bulkLoad(terminos, rollBack);
	}

}
