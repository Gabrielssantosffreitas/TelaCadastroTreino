package com.gabriel.tela.DAO;

import com.gabriel.tela.entity.Estudante;
import com.gabriel.tela.repository.EstudanteRepositorio;
import com.gabriel.tela.util.Conexao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class EstudanteDAO implements EstudanteRepositorio {
    @Override
    public Estudante porId(long id) {
        Estudante estudanteNovo = new Estudante();
        try {
            String sql  ="select * from estudante where id=?";
            PreparedStatement preparedStatement = Conexao.connection().prepareStatement(sql);
            preparedStatement.setLong(1,id);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()){

                estudanteNovo.setNome(rs.getString("nome"));
                estudanteNovo.setIdade(rs.getInt("idade"));
                estudanteNovo.setSexo(rs.getString("sexo"));
                estudanteNovo.setId(rs.getLong("id"));
            }
        }catch (Exception e){
            System.out.println("ERRO "+ e.getMessage());
        }
        return estudanteNovo;
    }

    @Override
    public List<Estudante> buscarTodos() {
        List<Estudante>  estudantes = new ArrayList<>();
        try{

            ResultSet rs = null;
            String sql = "SELECT * FROM estudante";
            PreparedStatement preparedStatement = Conexao.connection().prepareStatement(sql);
            rs = preparedStatement.executeQuery();

            while(rs.next()){
                Estudante estudante = new Estudante();
                estudante.setId(rs.getLong("id"));
                estudante.setNome(rs.getString("nome"));
                estudante.setSexo(rs.getString("sexo"));
                estudante.setIdade(rs.getInt("idade"));
                estudantes.addLast(estudante);
            }


        }catch (Exception e){
            e.printStackTrace();
            System.out.println("ERRo : " + e.getMessage());
        }
        return estudantes;
    }

    @Override
    public void inserir(Estudante estudante) {
        try {
            String sql = "INSERT INTO estudante(nome,sexo,idade) VALUES(?,?,?)";
            PreparedStatement preparedStatement = Conexao.connection().prepareStatement(sql);
            preparedStatement.setString(1, estudante.getNome());
            preparedStatement.setString(2, estudante.getSexo());
            preparedStatement.setInt(3, estudante.getIdade());
            preparedStatement.executeUpdate();
        }catch (Exception e ){
            e.printStackTrace();
            System.out.println("ERRO "+e.getMessage());
        }
    }

    @Override
    public void apagarPorId(long id) {
        try {


            String sql = "DELETE from estudante where id=?";
            PreparedStatement preparedStatement = Conexao.connection().prepareStatement(sql);
            preparedStatement.setLong(1,id);
            preparedStatement.execute();



        }catch (Exception e){
            System.out.println("ERRO: " + e.getMessage());
        }

    }

    @Override
    public void editar(Estudante estudante, long id) {
        try {
            String sql = "UPDATE estudante SET nome=?, sexo=?,idade=? WHERE id=?";
            PreparedStatement preparedStatement = Conexao.connection().prepareStatement(sql);
            preparedStatement.setString(1, estudante.getNome());
            preparedStatement.setString(2, estudante.getSexo());
            preparedStatement.setInt(3, estudante.getIdade());
            preparedStatement.setLong(4,id);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
