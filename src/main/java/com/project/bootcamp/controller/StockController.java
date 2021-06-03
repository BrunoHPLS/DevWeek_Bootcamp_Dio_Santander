package com.project.bootcamp.controller;

import com.project.bootcamp.model.dto.StockDto;
import com.project.bootcamp.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.print.attribute.standard.Media;
import javax.validation.Valid;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
//Stock é um termo utilizado para ações/cotações, por isso esse nome, A Camada controller vai servir para
//Guardar os ending points da api para realizar a comunicação com os requests.

/**
 * Esta classe contará com alguns ending points, por ser a classe controladora
 * Ending points irão realizar as ações de Request, ou seja, GET,PUT,POST,DELETE & ETC.
 *
 * @author BRUNO
 */

@CrossOrigin
@RestController
@RequestMapping(value = "/stock")//Quem quiser acessar este controlador, terá de usar um /stock
//o stock vai salvar uma ação na base de dados.
public class StockController {
    @Autowired //O spring vai gerenciar as instancias desta classe e controlar quando vai instanciar ou não esta camada
    private StockService service;

    /**
     * Este método é um Post ,com o nome de save(), ele recebe como parâmetros um JSON que contém todos os valores
     * requisitados pelo objeto StockDto, salva no banco de dados os valores e retorna um status 2XX como outra
     * informação do objeto StockDto também em JSON.
     *
     * @param dto
     * @return
     */
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE )
    public ResponseEntity<StockDto> save(@Valid @RequestBody StockDto dto){
        return ResponseEntity.ok(service.save(dto));
    }

    /**
     * Este é um método Put, com o nome de update(), ele altera aluma informação no banco de dados recebendo um
     * JSON com todos os valores do objeto StockDto, altera no BD e retorna outro JSON com status 2XX.
     *
     * @param dto
     * @return
     */
    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE )
    public ResponseEntity<StockDto> update(@RequestBody StockDto dto){
        return ResponseEntity.ok(service.update(dto));
    }

    /**
     * Este é um método de Get, ele retornará todos os dados de todas as cotações em uma lista, os dados estarã em
     * JSON. Como não precisa de parametros e de endereço, ele é diretamente acessado pelo stock
     *
     * @return
     */
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<StockDto>>findAll(){
        return ResponseEntity.ok(service.findAll());
    }

    /**
     * A partir do ID gerado na URL, este método irá retornar a ação/cotação com o ID correspondente
     *
     * @param id
     * @return
     */
    @GetMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StockDto>findById(@PathVariable Long id){
        return ResponseEntity.ok(service.findById(id));
    }

    /**
     * Deleta o dado do banco de dados a partir do id
     *
     * @param id
     * @return
     */
    @DeleteMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StockDto> delete(@PathVariable Long id){
        return ResponseEntity.ok(service.delete(id));
    }

    /**
     * Retorna uma lista de entidades presentes no dia atual e seus dados
     *
     * @return
     */
    @GetMapping(value = "/today",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<StockDto>> findByToday(){
        return ResponseEntity.ok(service.findByToday());
    }
}
