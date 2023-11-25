package com.CSH.resource;

import java.util.List;

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

import com.CSH.BO.EnfermeiroBO;
import com.CSH.beans.Enfermeiro;

import jakarta.validation.Valid;
@RestController
@RequestMapping(value = "/enfermeiro")
@CrossOrigin(origins = "http://localhost:5173")
public class EnfermeiroResource {
	private EnfermeiroBO enfermeiroBO = new EnfermeiroBO();
	
	@PostMapping
	public ResponseEntity<Enfermeiro> create(@RequestBody @Valid Enfermeiro enfermeiro) throws Exception {	    
		enfermeiroBO.cadastrar(enfermeiro);	   
	    return ResponseEntity.status(HttpStatus.CREATED).body(enfermeiro);
	}
	
	@GetMapping
	public List<Enfermeiro> getObjects() throws Exception{		
		return enfermeiroBO.select();
		
	}
	
	@GetMapping("{coren}")
    public ResponseEntity<Enfermeiro> getEnfermeiroPorCoren(@PathVariable int coren) throws Exception{
        return ResponseEntity.of(enfermeiroBO.EnfermeiroPorCoren(coren));
    }
	
	@DeleteMapping("{coren}")
    public ResponseEntity<Object> delete(@PathVariable int coren) throws Exception{
        var optional = enfermeiroBO.EnfermeiroPorCoren(coren);

        if(optional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}

        enfermeiroBO.deleteEnfermeiro(coren);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
	
	@PutMapping("{coren}")
    public ResponseEntity<Object> atualizar(@PathVariable int coren, @RequestBody @Valid Enfermeiro enfermeiro) throws Exception{
        var optional = enfermeiroBO.EnfermeiroPorCoren(coren);

        if(optional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}

        enfermeiroBO.atualizar(enfermeiro);
        return ResponseEntity.ok(enfermeiro);
    }
	
	@GetMapping("/login/{senhaEnfermeiro}")
    public ResponseEntity<String> validaLoginEnfermeiro(@PathVariable String senhaEnfermeiro) throws Exception{
        return ResponseEntity.ok(enfermeiroBO.validaLoginEnfemeiro(senhaEnfermeiro));
    }
	
}
