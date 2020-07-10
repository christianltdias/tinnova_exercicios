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

@Service
public class VeiculoService {

	@Autowired
	private VeiculoRepository veiculoRepository;

	private List<String> validNames = Arrays.asList("Honda","Fiat","Ford","Volkswagen","Audi","Renault","Toyota","Hyundai","Suzuki","Bmw");

	public Veiculo find(Integer id) {

		return veiculoRepository.findById(id).get();

	}

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

	public Veiculo update(Veiculo newObj) {
		newObj.setUpdated(new Date(System.currentTimeMillis()));
		return veiculoRepository.save(newObj);
	}

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

	public void delete(Integer id) {
		find(id);
		try {
			veiculoRepository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			e.printStackTrace();
		}
	}

	public List<Veiculo> findAll() {
		return veiculoRepository.findAll();
	}

	public List<Veiculo> findByVendido() {
		List<Veiculo> obj = veiculoRepository.findByVendidoFalse();
		return obj;

	}

	public List<Veiculo> findByMarca(String marca) {
		List<Veiculo> obj = veiculoRepository.findByMarcaContaining(marca);
		return obj;

	}

	public List<Veiculo> findByDecada(Integer iniDate, Integer endDate) {
		List<Veiculo> obj = veiculoRepository.findByAnoBetween(iniDate, endDate);
		return obj;

	}

	public List<Veiculo> findVeiculosUltimaSemana() {
		Date data = new Date(System.currentTimeMillis());
		data.setTime(data.getTime() - 7 * 24 * 3600 * 1000);
		List<Veiculo> obj = veiculoRepository.findByCreatedAfter(data);
		return obj;

	}

}