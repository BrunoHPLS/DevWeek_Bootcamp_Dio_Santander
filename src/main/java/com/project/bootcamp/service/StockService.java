package com.project.bootcamp.service;

import com.project.bootcamp.exceptions.BusinessException;
import com.project.bootcamp.exceptions.NotFoundException;
import com.project.bootcamp.mapper.StockMapper;
import com.project.bootcamp.model.Stock;
import com.project.bootcamp.model.dto.StockDto;
import com.project.bootcamp.repository.StockRepository;
import com.project.bootcamp.util.MessageUtils;
import net.bytebuddy.implementation.bind.MethodDelegationBinder;
import org.omg.CosNaming.NamingContextPackage.NotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * Camada intermediária que vai entender a parte negocial da camada Stock
 * É recomendado ter um Service para cada entidade
 * Regras de negócio são colocadas aqui
 * Esta Camada Gerencia as ações se devem ou não mexer no repository. É a camada business
 *
 */
@Service
public class StockService {

    @Autowired
    private StockRepository repository;

    @Autowired
    private StockMapper mapper;

   @Transactional //Incumbe ao método save a possibilidade de realizar uma transação com o banco de dados
    public StockDto save(StockDto dto) {
        //Chama o repository e coloca esse dado na base de dados

        //Verifica se já existe na base de dados um objeto cadastrado naquele dia
        Optional<Stock> optionalStock = repository.findByNameAndDate(dto.getName(),dto.getDate());
        if(optionalStock.isPresent()){
            //Está entrando aqui
            throw new BusinessException();
        }
        Stock stock = mapper.toEntity(dto);
        repository.save(stock);//salvou o dto preenchido
        return mapper.toDto(stock);
    }

    @Transactional
    public StockDto delete(Long id) {
       //Verifica no método find by id se existe esse id no banco de dados e se tiver, deleta e retorna o dto.
        StockDto dto = this.findById(id);
        repository.deleteById(dto.getId());
        return  dto;
    }

    @Transactional
    public StockDto update(StockDto dto) {
        Optional<Stock> optionalStock = repository.findByStockUpdate(dto.getName(),dto.getDate(),dto.getId());
        if(optionalStock.isPresent()){
            //Está entrando aqui
            throw new BusinessException();
        }
        Stock stock = mapper.toEntity(dto);
        repository.save(stock);//salvou o dto preenchido
        return mapper.toDto(stock);
    }
    @Transactional
    public List<StockDto> findAll() {
    List<Stock> list = repository.findAll();
    return mapper.toDto(list);
    }

    @Transactional
    public StockDto findById(Long id) {

       return repository.findById(id).map(mapper::toDto).orElseThrow(NotFoundException::new);
    }

    @Transactional
    public List<StockDto> findByToday() {

       return repository.findByToday(LocalDate.now()).map(mapper::toDto).orElseThrow(NotFoundException::new);
    }
}
