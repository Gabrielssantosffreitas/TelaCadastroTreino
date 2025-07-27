package com.gabriel.tela.repository;

import com.gabriel.tela.entity.Estudante;

import java.util.List;

public interface EstudanteRepositorio {
    public Estudante porId(long id);
    public List <Estudante>  buscarTodos();
    public void inserir(Estudante estudante);
    public  void apagarPorId(long id);
    void editar(Estudante estudante, long id);
}
