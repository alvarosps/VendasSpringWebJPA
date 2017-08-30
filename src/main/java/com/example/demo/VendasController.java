package com.example.demo;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@Controller
public class VendasController{
	private ProdutoRepositorioV2 pr;
	private VendaRepositorioV2 vr;
	
	@Autowired
	public VendasController(ProdutoRepositorioV2 pr, VendaRepositorioV2 vr) {
		this.pr = pr;
		this.vr = vr;
	}
	
	@GetMapping("/listaprod")
	public String consultarProdutos(Model model) {
		model.addAttribute("pr", pr.findAll());
		return "hello";
	}
	
	@PostMapping("/cadprod")
	public String cadProduto(@ModelAttribute @Valid Produto produto, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return "produto";
		}
		else {
			pr.save(produto);
			return "redirect:/listaprod";
		}
	}
	
	@GetMapping("/apagar")
	public String cadProduto(@RequestParam(value = "id") Integer id) {
		pr.delete(id);
		return "redirect:/listaprod";
	}
	
	@GetMapping("/produto")
	public String saudacao(Model model, @RequestParam(value = "id", required = false) Integer id) {
		if(id == null) {
			model.addAttribute("produto", new Produto());
		}
		else {
			Produto produto = pr.findOne(id);
			model.addAttribute("produto", produto);
		}
		return "produto";
	}
	
	//adicionar novas vendas
	@PostMapping("/vendas")
	public ResponseEntity<Void> adicionarVenda(@RequestBody Venda venda, UriComponentsBuilder uc){
		if(venda.getId() != 0 && vr.exists(venda.getId())) {
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}
		
		Venda novaVenda = vr.save(venda);
		HttpHeaders cabecalho = new HttpHeaders();
		cabecalho.setLocation(uc.path("/vendas/{id}").buildAndExpand(novaVenda.getId()).toUri());
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}
	
	//adicionando produtos dentro de uma venda
	@PostMapping("/vendas/{codVenda}/{codProduto}/{quant}")
	public ResponseEntity<Venda> adicionarProdutoVenda(@PathVariable("codVenda") int codVenda, @PathVariable("codProduto") int codProduto, @PathVariable("quant") int quantidade){
		Venda v = vr.findOne(codVenda);
		if(v != null) {
			Produto p = pr.findOne(codProduto);
			if(p != null) {
				v.vender(p, quantidade);
				vr.save(v);
				return new ResponseEntity<Venda>(v, HttpStatus.OK);
			}
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
}
