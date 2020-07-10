package com.christianltdias.tinnova_exerc.repository;

import java.util.Date;
import java.util.List;

import com.christianltdias.tinnova_exerc.domain.Veiculo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public interface VeiculoRepository extends JpaRepository<Veiculo, Integer> {

    @Transactional(readOnly = true)
    public List<Veiculo> findByVendidoFalse();
    
    @Transactional(readOnly = true)
    public List<Veiculo> findByMarcaContaining(String marca);

    @Transactional(readOnly = true)
    public List<Veiculo> findByAnoBetween(Integer iniDate, Integer endDate);

    @Transactional(readOnly = true)
    public List<Veiculo> findByCreatedAfter(Date data);

    
}