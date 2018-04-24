package com.algaworks.vinho.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.algaworks.vinho.model.Vinho;

public interface Vinhos extends JpaRepository<Vinho, Long> {

}
