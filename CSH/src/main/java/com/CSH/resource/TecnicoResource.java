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
import com.CSH.BO.TecnicoBO;
import com.CSH.beans.Tecnico;
import jakarta.validation.Valid;
@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping(value = "/tecnico")
public class TecnicoResource {

private TecnicoBO tecnicoBO = new TecnicoBO();
	
	@PostMapping
	public ResponseEntity<Tecnico> create(@RequestBody @Valid Tecnico tecnico) throws Exception {	    
		tecnicoBO.cadastrar(tecnico);	   
	    return ResponseEntity.status(HttpStatus.CREATED).body(tecnico);
	}
	
	@GetMapping
	public List<Tecnico> getObjects() throws Exception{		
		return tecnicoBO.select();
		
	}
	
	@GetMapping("{coren}")
    public ResponseEntity<Tecnico> getTecnicoPorCoren(@PathVariable int coren) throws Exception{
        return ResponseEntity.of(tecnicoBO.TecnicoPorCoren(coren));
    }
	
	@DeleteMapping("{coren}")
    public ResponseEntity<Object> delete(@PathVariable int coren) throws Exception{
        var optional = tecnicoBO.TecnicoPorCoren(coren);

        if(optional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}

        tecnicoBO.delete(coren);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
	
	@PutMapping("{coren}")
    public ResponseEntity<Object> atualizar(@PathVariable int coren, @RequestBody @Valid Tecnico tecnico) throws Exception{
        var optional = tecnicoBO.TecnicoPorCoren(coren);

        if(optional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}

        tecnicoBO.atualizar(tecnico);
        return ResponseEntity.ok(tecnico);
    }
	
	@GetMapping("/nome/{vIdEquipe}")
    public ResponseEntity<List<String>> getTecnicosPorIdEquipe(@PathVariable long vIdEquipe) throws Exception{		
        return ResponseEntity.ok(tecnicoBO.selectNomeTecnicoPorIdEquipe(vIdEquipe));
    }
	
}
