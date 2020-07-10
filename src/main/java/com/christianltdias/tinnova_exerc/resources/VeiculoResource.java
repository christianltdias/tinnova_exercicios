package com.christianltdias.tinnova_exerc.resources;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import com.christianltdias.tinnova_exerc.domain.Veiculo;
import com.christianltdias.tinnova_exerc.services.VeiculoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

/*
	Classe responsável pela camada de resources. Aqui são inseridos os Endpoint e Métodos RESTFul
*/

@RestController
@RequestMapping(value = "/veiculos") // Endpoint
public class VeiculoResource {

	@Autowired
	private VeiculoService service; // Injetando um modelo de service para operaç~ies

	// Retornar um determinado veículos pelo id informado no endpoint (Método GET)
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable Integer id) {
		Veiculo obj = service.find(id); // Busca o objeto e retorna
		return ResponseEntity.ok().body(obj);
	}

	// Retornar todo os veiculos cadastrados (Método GET)
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Veiculo>> findAll() {

		List<Veiculo> list = service.findAll();

		return ResponseEntity.ok().body(list);
	}

	// Insere um novo veículo pelo formato JSON (Método POST)
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody Veiculo obj) {
		
		obj = service.insert(obj);
		// Retorna a URI do novo veiculo adicionado
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	// Atualiza um veiculo inteiro informado pelo id (Método PUT)
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody Veiculo obj, @PathVariable Integer id) {
		obj.setId(id);
		obj = service.update(obj);
		return ResponseEntity.noContent().build();
	}

	// Delete um veiculo pelo id informado no endpoint (Método DELETE)
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

	// Atualiza determinado campo informado no formato JSON, através do id informado (Método PATCH)
	@RequestMapping(value = "/{id}", method = RequestMethod.PATCH)
	public ResponseEntity<Void> delete(@PathVariable Integer id,@RequestBody Map<String, Object> values) {
		Veiculo obj = service.find(id);
		obj = service.patch(obj, values);
		return ResponseEntity.noContent().build();
	}
	

	// Função para pesquisa personalizada
	// Valores de q: vendidos,fabricante,decada, novos
	// Respectivamente retorna o número de carros já vendidos, por fabricante, da decada informada e os cadastradas desde ultima semana
	@RequestMapping(value = "/find", method = RequestMethod.GET)
	public ResponseEntity<Integer> findPage(@RequestParam(value = "q", defaultValue = "vendidos") String param,
	@RequestParam(value = "marca", defaultValue = "Fiat") String marca,
	@RequestParam(value = "ano", defaultValue = "2000") Integer ano ) {

		// Caso nenhum campo seja informado, os valores default são usados
		// O parametro q contém o tipo de requisição desejada
		// Caso informado "fabricante" também é possível informar a marca para pesquisa
		// Caso informado o "ano" também é possível informad o ano para pesquisa

		List<Veiculo> list = new ArrayList();

		if(param.equals("vendidos")){
			// Retorna a lista de veiculos não vendidos
			list = service.findByVendido();

		} else if(param.equals("fabricante")){
			// Retorna a lista de veiculos com mesma marca
			list = service.findByMarca(marca);

		}else if(param.equals("decada")){
			// Retorna a lista de carros da mesma decáda 

			String anoStr = String.valueOf(ano);
			if(anoStr.charAt(anoStr.length()-1) == '0'){
				list=service.findByDecada(ano, ano + 9);
			}

		}else if(param.equals("novos")){
			// Retorna a lista de carros cadastrados na ultima semana
			list = service.findVeiculosUltimaSemana();
		}

		// Irá retornar o número de objetos de uma lista
		return ResponseEntity.ok().body(list.size());

		// Também poderiamos retornar a lista desta forma:
		// return ResponseEntity.ok().body(list.size());
		
	}
}