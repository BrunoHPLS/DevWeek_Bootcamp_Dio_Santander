package com.project.bootcamp.repository;
/**
 * Esta Interface é responsável por interagir diretamente com o banco de dados,
 * por se tratar de um repositório
 *
 */

import com.project.bootcamp.model.Stock;
import com.project.bootcamp.model.dto.StockDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface StockRepository extends JpaRepository<Stock,Long> {
    //informa que esta classe é um repositório e o id

    Optional<Stock> findByNameAndDate(String name, LocalDate date);
    //O SpringData vai dar um Select criando um Where com Name e Date
    //Essa forma o spring data entende pois já tem um método parecido

    @Query("SELECT stock "+
        "FROM Stock stock "+
        "WHERE stock.name = :name AND stock.date = :date AND stock.id <> :id")
    Optional<Stock> findByStockUpdate(String name, LocalDate date , Long id);
    //Esta forma o spring data não conhece e por isso deve-se criar uma query
    //Vai selecionar no banco de dados um objeto com o mesmo nome,data e id diferente.

    @Query("SELECT stock "+
            "FROM Stock stock "+
            "WHERE stock.date = :date ")
    Optional<List<Stock>> findByToday(LocalDate date);
}
