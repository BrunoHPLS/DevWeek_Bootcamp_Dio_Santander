package com.project.bootcamp.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

/**
 * Dto = Data Transfer Object
 * É uma classe unica e exclusivamente utilizada para interagir com o controlador
 * Os dados colocados nesta classe terão interação direta ele
 * O dto vai sempre atender ao que o Front quer
 */
public class StockDto {
//O que é preciso receber na requisição para poder fazer algo? é isso que o Dto determina

    private Long id;//id da ação no banco de dados

    @NotNull//Valores que não podem ser nulos, que o requisito tem que apresentar
    private String name;//nome da ação/cotação

    @NotNull
    @DecimalMin(value = "0.00") //Não pode ser um decimal menor que 0
    @Digits(integer = 6, fraction = 2) //Tamanho do valor, aceita no máximo "999999,99"
    private Double price;

    @NotNull
    @Digits(integer = 3, fraction = 2) //Max "999.99"
    private Double variation;//Preço da ação/cotação e variação da ação/cotação

    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate date;//Data da ação
    public StockDto(){

    }
    public StockDto(Long i,String n,Double p,Double v,LocalDate d){
        this.setId(i);
        this.setName(n);
        this.setDate(d);
        this.setPrice(p);
        this.setVariation(v);
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getVariation() {
        return variation;
    }

    public void setVariation(Double variation) {
        this.variation = variation;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
