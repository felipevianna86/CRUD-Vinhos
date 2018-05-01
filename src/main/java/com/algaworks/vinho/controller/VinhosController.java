package com.algaworks.vinho.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.algaworks.vinho.model.TipoVinho;
import com.algaworks.vinho.model.Vinho;
import com.algaworks.vinho.repository.Vinhos;

@Controller
@RequestMapping("/vinhos")
public class VinhosController {
	
	@Autowired
	private Vinhos vinhos; 
	
	@DeleteMapping("/{id}")
	public String remover(@PathVariable Long id, RedirectAttributes attributes) {
		
		vinhos.deleteById(id);
		
		attributes.addFlashAttribute("mensagem", "Vinho removido com sucesso!");
		return "redirect:/vinhos";
		
	}
	
	@GetMapping
	public ModelAndView listar() {
		ModelAndView modelAndView = new ModelAndView("vinhos/lista-vinhos");
		modelAndView.addObject("vinhos", vinhos.findAll());
		return modelAndView;
	}
	
	@GetMapping("/{id}")
	public ModelAndView editar(@PathVariable Long id){
		
		return novo(vinhos.findById(id).get());
	}
	
	@GetMapping("/novo")
	public ModelAndView novo(Vinho vinho) {
		ModelAndView model = new ModelAndView("vinhos/cadastro-vinho");
		model.addObject(vinho);
		model.addObject("tipos", TipoVinho.values());
		return model;

	}
	
	@PostMapping("/novo")
	public ModelAndView salvar(@Valid Vinho vinho, BindingResult result, RedirectAttributes attributes) {
		if(result.hasErrors())
			return novo(vinho);
		
		vinhos.save(vinho);
		
		attributes.addFlashAttribute("mensagem", "Vinho salvo com sucesso.");
		
		return new ModelAndView("redirect:/vinhos/novo");
	}
}
