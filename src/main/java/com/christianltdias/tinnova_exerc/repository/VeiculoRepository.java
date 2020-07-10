package com.christianltdias.tinnova_exerc.repository;

import java.util.Date;
import java.util.List;

import com.christianltdias.tinnova_exerc.domain.Veiculo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

/*
    Inteface responsável pela camada de repositório (JPA) 
*/
public interface VeiculoRepository extends JpaRepository<Veiculo, Integer> {

    // Métodos todos buscam uma lista de Veiculos utilizando o padrão de nomes do Spring Framework

    // Irá retornar a lista de Veiculos ainda não vendidos (Boolean = false)
    @Transactional(readOnly = true)
    public List<Veiculo> findByVendidoFalse();
    
    // Irá retornar a lista de Veículos com o mesmo nome da marca informada
    @Transactional(readOnly = true)
    public List<Veiculo> findByMarcaContaining(String marca);

    // Irá retornar a lista de veiculos com a data (fabricação) entre dois valores inteiros
    @Transactional(readOnly = true)
    public List<Veiculo> findByAnoBetween(Integer iniDate, Integer endDate);

    // Irá retornar a lista de veiculos a partir da data informado (usado para pegar veículos novos <= 1 semana de cadastrado)
    @Transactional(readOnly = true)
    public List<Veiculo> findByCreatedAfter(Date data);

    
}