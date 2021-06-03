package com.project.bootcamp.mapper;

import com.project.bootcamp.model.Stock;
import com.project.bootcamp.model.dto.StockDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Na camada Mapper serão feitos mapeamentos e transformações de classes e entidades para poder transitar
 * estes dados do banco para o backend
 *
 */
@Component
public class StockMapper {
    public Stock toEntity(StockDto dto) {
        //Receber o dto preenchido da requisição, transforma pra entity e retorna ela
        Stock stock = new Stock();
        stock.setId(dto.getId());
        stock.setName(dto.getName());
        stock.setDate(dto.getDate());
        stock.setPrice(dto.getPrice());
        stock.setVariation(dto.getVariation());
        return stock;
    }

    public StockDto toDto(Stock stock) {
        StockDto dto = new StockDto();
        dto.setId(stock.getId());
        dto.setName(stock.getName());
        dto.setDate(stock.getDate());
        dto.setPrice(stock.getPrice());
        dto.setVariation(stock.getVariation());
        return dto;
    }
    public List<StockDto> toDto(List<Stock> listStock){
        return listStock.stream().map(this::toDto).collect(Collectors.toList());
        //Percorre a Lista e faz um .map item a item e transforma cada entidade em um Dto criando uma lista
    }
}
