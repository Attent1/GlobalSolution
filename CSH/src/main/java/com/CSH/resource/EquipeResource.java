package com.CSH.resource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.CSH.BO.EquipeBO;
import com.CSH.DTO.EquipeDTO;
import com.CSH.beans.Equipe;

import jakarta.validation.Valid;
@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping(value = "/equipe")
public class EquipeResource {

	private EquipeBO equipeBO = new EquipeBO();

	@PostMapping
	public ResponseEntity<Equipe> create(@RequestBody @Valid EquipeDTO equipeDTO) throws Exception {	
		 int corenEnf = Integer.parseInt(equipeDTO.getCorenEnf());
		    List<Integer> corenTecnicos = equipeDTO.getCorenTecnicos().stream()
		            .map(tecnicoDTO -> Integer.parseInt(tecnicoDTO.getCoren()))
		            .collect(Collectors.toList());

		    Equipe equipe = new Equipe();
		    equipe.setNome(equipeDTO.getNome());
		    equipe.setCorenEnf(corenEnf);
		    equipe.setCorenTecnicos((ArrayList<Integer>) corenTecnicos);
		    equipe.setSenhaEquipe(equipeDTO.getSenhaEquipe());
		    
		equipeBO.cadastrar(equipe);	   
	    return ResponseEntity.status(HttpStatus.CREATED).body(equipe);
	}

	@GetMapping
	public List<Equipe> getObjects() throws Exception {
		return equipeBO.select();

	}

	@GetMapping("{id}")
    public ResponseEntity<Equipe> getEquipePorId(@PathVariable long id) throws Exception{
        return ResponseEntity.of(equipeBO.EquipePorId(id));
    }
	
	@DeleteMapping("{id}")
    public ResponseEntity<Object> delete(@PathVariable long id) throws Exception{
        var optional = equipeBO.EquipePorId(id);

        if(optional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}

        equipeBO.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
	
	@PutMapping("{id}")
    public ResponseEntity<Object> atualizar(@PathVariable long id, @RequestBody @Valid Equipe equipe) throws Exception{
        var optional = equipeBO.EquipePorId(id);

        if(optional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}

        equipeBO.atualizar(equipe);
        return ResponseEntity.ok(equipe);
    }
	
	@GetMapping("/id/{nome}")
    public ResponseEntity<String> getIdEquipePorNome(@PathVariable String nome) throws Exception{
        return ResponseEntity.ok(String.valueOf(equipeBO.selectEquipePorNome(nome)));
    }
	
	@GetMapping("/login/{nomeEquipe}")
    public ResponseEntity<List<String>> validaLoginEquipe(@PathVariable String nomeEquipe) throws Exception{
        return ResponseEntity.ok(equipeBO.validaLogin(nomeEquipe));
    }
	
}
