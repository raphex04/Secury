package com.MapRelacioTurma.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.MapRelacioTurma.entities.Aluno;
import com.MapRelacioTurma.service.AlunoService;

@RestController
@RequestMapping("/aluno")
public class AlunoController {
	private final AlunoService alunoService;
	@Autowired
	public AlunoController(AlunoService alunoService) {
		this.alunoService = alunoService;
	}

	@GetMapping("/{id}")
	public ResponseEntity<Aluno> getAlunoById(@PathVariable Long id) {
		Aluno aluno = alunoService.getAlunoById(id);
		if (aluno != null) {
			return ResponseEntity.ok(aluno);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("/")
	public ResponseEntity<List<Aluno>> getAllAluno() {
		List<Aluno> aluno = alunoService.getAllAluno();
		return ResponseEntity.ok(aluno);
	}
	@GetMapping("/cidade/{cidade}")
	public ResponseEntity<List<Aluno>> getAlunosPorCidade(@PathVariable String cidade){
		List<Aluno> alunos = alunoService.getAlunosPorCidade(cidade);
		return ResponseEntity.ok(alunos);
	}
	@GetMapping("/nome/{nome}/renda/{renda}")
	public ResponseEntity<List<Aluno>> getAlunosPorCidadeRenda(@PathVariable String cidade, double renda){
		List<Aluno> alunos = alunoService.getAlunosPorCidadeERenda(cidade, renda);
		return ResponseEntity.ok(alunos);
	}
	@GetMapping("/renda/{renda}")
	public ResponseEntity<List<Aluno>> getAlunosPorRenda(@PathVariable double renda){
		List<Aluno> alunos = alunoService.getAlunosPorRenda(renda);
		return ResponseEntity.ok(alunos);
	}
	@GetMapping("/ra/{ra}")
	public ResponseEntity<List<Aluno>> getAlunoPorRa(@PathVariable String ra){
		List<Aluno> alunos = alunoService.getAlunosPorRa(ra);
		return ResponseEntity.ok(alunos);
	}
	//@Query
	@GetMapping("/nome/{nome}")
	public List<Aluno> findAlunosPorNome(@PathVariable String nome){
		return alunoService.findByNome(nome);
	}
	//@Query
	@GetMapping("/nome-completo/{nomeCompleto}")
	public List<Aluno> findAlunosPorNomeCompletoLike(@PathVariable String nomeCompleto){
		return alunoService.findByNomeCompletoLike(nomeCompleto);
	}
	//@query
	@GetMapping("/turma/{turmaId}")
	public List<Aluno> findAlunosPorTurma(@PathVariable Long turmaId){
		return alunoService.findByTurmaId(turmaId);
	}
	
	/*@GetMapping("/ra/{ra}")
	public ResponseEntity<List<Aluno>> getAlunosPorRa(@PathVariable String ra){
		List<Aluno> alunos = alunoService.getAlunosPorRa(ra);
		return ResponseEntity.ok(alunos);
	}*/
	
	
	@PostMapping("/")
	public ResponseEntity<Aluno> criarAluno(@RequestBody Aluno aluno) {
		Aluno criarAluno = alunoService.salvarAluno(aluno);
		return ResponseEntity.status(HttpStatus.CREATED).body(criarAluno);
	}


	@PutMapping("/{id}")
	public ResponseEntity<Aluno> updateAluno(@PathVariable Long id,@RequestBody Aluno aluno) {
		Aluno updatedAluno = alunoService.updateAluno(id, aluno);
		if (updatedAluno != null) {
			return ResponseEntity.ok(updatedAluno);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteAluno(@PathVariable Long id) {
		boolean deleted = alunoService.deleteAluno(id);
		if (deleted) {
			return ResponseEntity.ok().body("O aluno foi excluído com sucesso.");
		} else {
			return ResponseEntity.notFound().build();
		}
	}


}


