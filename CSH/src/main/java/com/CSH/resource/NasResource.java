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

import com.CSH.BO.NasBO;
import com.CSH.DTO.NasDTO;
import com.CSH.beans.Nas;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/nas")
@CrossOrigin(origins = "http://localhost:5173")

public class NasResource {
	private NasBO nasBO = new NasBO();
	
	@PostMapping
	public ResponseEntity<Nas> create(@RequestBody @Valid NasDTO nasDTO) throws Exception {	
		long idEquipe = Long.parseLong(nasDTO.getIdEquipe());
		
		Nas nas = new Nas();
		nas.setData(nasDTO.getData());
		nas.setValor(nasDTO.getValor());;
		nas.setIdPaciente(nasDTO.getIdPaciente());
		nas.setIdEquipe(idEquipe);
		nas.setComplexidade(nasDTO.getComplexidade());
		nasBO.cadastrar(nas);	   
	    return ResponseEntity.status(HttpStatus.CREATED).body(nas);
	}
	
	@GetMapping
	public List<Nas> getObjects() throws Exception {
		return nasBO.select();

	}
	
	@GetMapping("{id}")
    public ResponseEntity<Nas> getNasPorId(@PathVariable long id) throws Exception{
        return ResponseEntity.of(nasBO.NasPorId(id));
    }
	
	@DeleteMapping("{id}")
    public ResponseEntity<Object> delete(@PathVariable long id) throws Exception{
        var optional = nasBO.NasPorId(id);

        if(optional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}

        nasBO.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
	
	@PutMapping("{id}")
    public ResponseEntity<Object> atualizar(@PathVariable long id, @RequestBody @Valid Nas nas) throws Exception{
        var optional = nasBO.NasPorId(id);

        if(optional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}

        nasBO.atualizar(nas);
        return ResponseEntity.ok(nas);
    }
	
	
}
