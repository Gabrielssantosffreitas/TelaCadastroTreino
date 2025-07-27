package com.gabriel.tela;

import com.gabriel.tela.DAO.EstudanteDAO;
import com.gabriel.tela.entity.Estudante;

import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void main(String[] args) {


        List<Estudante> estudantes = null;
        EstudanteDAO estudanteDAO = new EstudanteDAO();
        estudantes =  estudanteDAO.buscarTodos();

        for (int i = 0; i < estudantes.size(); i++) {
            System.out.println(estudantes.get(i).getNome());
        }

        Estudante novoEstudante = new Estudante();
        novoEstudante.setNome("eduarda");
        novoEstudante.setSexo("M");
        novoEstudante.setIdade(19);

        estudanteDAO.editar(novoEstudante,4);

        System.out.println(estudanteDAO.porId(2).getNome());

        estudanteDAO.apagarPorId(5);
    }
}
