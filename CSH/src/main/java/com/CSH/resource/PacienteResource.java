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

import com.CSH.BO.PacienteBO;
import com.CSH.DTO.PacienteComplexidadeDTO;
import com.CSH.DTO.PacienteDTO;
import com.CSH.beans.Paciente;

import jakarta.validation.Valid;
@RestController
@RequestMapping(value = "/paciente")
@CrossOrigin(origins = "http://localhost:5173")
public class PacienteResource {
	private PacienteBO pacienteBO = new PacienteBO();
	
	@PostMapping
	public ResponseEntity<Paciente> create(@RequestBody @Valid PacienteDTO pacienteDTO) throws Exception {	  
		int idade = Integer.parseInt(pacienteDTO.getIdade());
		
		Paciente paciente = new Paciente();
		paciente.setCpf(pacienteDTO.getCpf());
		paciente.setNome(pacienteDTO.getNome());
		paciente.setDtNascimento(pacienteDTO.getDtNascimento());
		paciente.setIdade(idade);
		paciente.setTelefone(pacienteDTO.getTelefone());
		pacienteBO.cadastrar(paciente);	   
	    return ResponseEntity.status(HttpStatus.CREATED).body(paciente);
	}

	@GetMapping
	public List<Paciente> getObjects() throws Exception {
		return pacienteBO.select();

	}

	@GetMapping("{id}")
    public ResponseEntity<Paciente> getPacientePorId(@PathVariable long id) throws Exception{
        return ResponseEntity.of(pacienteBO.PacientePorId(id));
    }
	
	@GetMapping("/complexidade/{data}-{vIdEquipe}")
    public ResponseEntity<List<PacienteComplexidadeDTO>> getNomeComplexidadePacientePorData(@PathVariable String data, @PathVariable String vIdEquipe) throws Exception{		
		long idEquipe = Long.parseLong(vIdEquipe);		
        return ResponseEntity.ok(pacienteBO.selectNomeComplexidadePaciente(data, idEquipe));
    }
	
	@DeleteMapping("{id}")
    public ResponseEntity<Object> delete(@PathVariable long id) throws Exception{
        var optional = pacienteBO.PacientePorId(id);

        if(optional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}

        pacienteBO.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
	
	@PutMapping("{id}")
    public ResponseEntity<Object> atualizar(@PathVariable long id, @RequestBody @Valid Paciente paciente) throws Exception{
        var optional = pacienteBO.PacientePorId(id);

        if(optional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}

        pacienteBO.atualizar(paciente);
        return ResponseEntity.ok(paciente);
    }
	
	@GetMapping("/cpf/{cpf}")
    public ResponseEntity<String> getIdPacientePorCpf(@PathVariable String cpf) throws Exception{
        return ResponseEntity.ok(String.valueOf(pacienteBO.IdPacientePorCpf(cpf)));
    }

}
