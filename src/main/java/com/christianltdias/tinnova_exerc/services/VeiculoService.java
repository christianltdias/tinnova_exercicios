package com.christianltdias.tinnova_exerc.services;


import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.christianltdias.tinnova_exerc.domain.Veiculo;
import com.christianltdias.tinnova_exerc.repository.VeiculoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

/*
	Classe resposável pela camada de serviçoes. Aqui é a realizada a comunicação com o bando de dados
*/

@Service
public class VeiculoService {

	// Injetando uma repositório para operações
	@Autowired
	private VeiculoRepository veiculoRepository;

	// Alguns nomes aceitos para validar as marcas
	private List<String> validNames = Arrays.asList("Honda","Fiat","Ford","Volkswagen","Audi","Renault","Toyota","Hyundai","Suzuki","Bmw");

	// Retornar um veiculo pelo id ou null se não achar
	public Veiculo find(Integer id) {

		return veiculoRepository.findById(id).get();

	}


	// insere um novo veiculo. Verifica se o nome é possível e seta as datas para o momento da criação
	public Veiculo insert(Veiculo obj) {
		obj.setId(null);
		if (obj.validateName(validNames)) {
			obj.setCreated(new Date(System.currentTimeMillis()));
			obj.setUpdated(obj.getCreated());
			obj = veiculoRepository.save(obj);
			return obj;
		}
		return null;
	}

	// Atualiza um veiculo inteiro. Também é atualizada a data de update
	public Veiculo update(Veiculo newObj) {
		newObj.setUpdated(new Date(System.currentTimeMillis()));
		return veiculoRepository.save(newObj);
	}

	// Atualiza um veiculos. somente os campos informados. Também é atualuzado o updateDate
	public Veiculo patch(Veiculo newObj, Map<String, Object> values) {

		values.forEach(
                (change, value) -> {
                    switch (change){
                        case "veiculo": newObj.setVeiculo((String) value); break;
                        case "marca": newObj.setMarca((String) value); break;
                        case "ano": newObj.setAno((Integer) value); break;
                        case "descricao": newObj.setDescricao((String) value); break;
                        case "vendido": newObj.setVendido((Boolean) value); break;
                    }
                }
        );
				
		newObj.setUpdated(new Date(System.currentTimeMillis()));
		return veiculoRepository.save(newObj);
	}

	// Tenta deletar um veiculo pelo id
	public void delete(Integer id) {
		find(id);
		try {
			veiculoRepository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			e.printStackTrace();
		}
	}

	// Retorna todos os veiculos cadastrados
	public List<Veiculo> findAll() {
		return veiculoRepository.findAll();
	}

	// Busca os veiculos não vendidos
	public List<Veiculo> findByVendido() {
		List<Veiculo> obj = veiculoRepository.findByVendidoFalse();
		return obj;

	}

	// Busca os veiculos que coincidem com a marca informada
	public List<Veiculo> findByMarca(String marca) {
		List<Veiculo> obj = veiculoRepository.findByMarcaContaining(marca);
		return obj;

	}

	// Busca os veiculos fabricados na mesma decada
	public List<Veiculo> findByDecada(Integer iniDate, Integer endDate) {
		List<Veiculo> obj = veiculoRepository.findByAnoBetween(iniDate, endDate);
		return obj;

	}

	// Busca os veiculos cadastrados no periodo de uma semana 
	public List<Veiculo> findVeiculosUltimaSemana() {
		Date data = new Date(System.currentTimeMillis());
		data.setTime(data.getTime() - 7 * 24 * 3600 * 1000);
		List<Veiculo> obj = veiculoRepository.findByCreatedAfter(data);
		return obj;

	}

}