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

@RestController
@RequestMapping(value = "/veiculos") // endpoint
public class VeiculoResource {

	@Autowired
	private VeiculoService service;

	// Endpoint id
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable Integer id) {
		Veiculo obj = service.find(id);
		return ResponseEntity.ok().body(obj);
	}

	// Retornar todos os valores
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Veiculo>> findAll() {

		List<Veiculo> list = service.findAll();

		return ResponseEntity.ok().body(list);
	}

	// Inserir novo veiculo
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody Veiculo obj) {
		
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	// Atualizar um valor
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody Veiculo obj, @PathVariable Integer id) {
		obj.setId(id);
		obj = service.update(obj);
		return ResponseEntity.noContent().build();
	}

	// Delete
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

	// Patch
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

		List<Veiculo> list = new ArrayList();

		if(param.equals("vendidos")){

			list = service.findByVendido();
		} else if(param.equals("fabricante")){
			
			list = service.findByMarca(marca);
		}else if(param.equals("decada")){
			String anoStr = String.valueOf(ano);
			if(anoStr.charAt(anoStr.length()-1) == '0'){
				list=service.findByDecada(ano, ano + 9);
			}
		}else if(param.equals("novos")){
			
			list = service.findVeiculosUltimaSemana();
		}

		return ResponseEntity.ok().body(list.size());
	}
}