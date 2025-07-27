package com.gabriel.tela.controller;

import com.gabriel.tela.DAO.EstudanteDAO;
import com.gabriel.tela.entity.Estudante;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.w3c.dom.events.MouseEvent;

public class EstudanteController implements Initializable {
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        prepararLista();
        ocutarDelete();
        ocutarEditar();
    }


    @FXML
    private Button inputButtonDeletar;

    @FXML
    private Button inputButtonEditar;

    @FXML
    private Button inputButtonSalvar;

    @FXML
    private RadioButton inputRadioButtonFeminino;

    @FXML
    private RadioButton inputRadioButtonMasculino;

    @FXML
    private RadioButton inputRadioButtonNaoBinario;

    @FXML
    private TextField inputTextFielIdade;

    @FXML
    private TextField inputTextFieldNome;

    @FXML
    private TableColumn<?, ?> outputTableColumnID;

    @FXML
    private TableColumn<?, ?> outputTableColumnIdade;

    @FXML
    private TableColumn<?, ?> outputTableColumnNome;

    @FXML
    private TableColumn<?, ?> outputTableColumnSexo;

    @FXML
    private TableView<Estudante> outputTableViewAluno;

    private void ocutarDelete() {
        inputButtonDeletar.setVisible(false);
    }

    private void ocutarEditar() {
        inputButtonEditar.setVisible(false);
    }

    private void prepararLista() {
        EstudanteDAO estudanteDAO = new EstudanteDAO();
        ObservableList<Estudante> observableList;

        outputTableColumnID.setCellValueFactory(new PropertyValueFactory<>("id"));
        outputTableColumnNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        outputTableColumnIdade.setCellValueFactory(new PropertyValueFactory<>("idade"));
        outputTableColumnSexo.setCellValueFactory(new PropertyValueFactory<>("sexo"));

        List<Estudante> todosOsEstudantes = estudanteDAO.buscarTodos();


        observableList = FXCollections.observableList(todosOsEstudantes);
        outputTableViewAluno.setItems(observableList);

    }

    Estudante novoEstudanteEditar = new Estudante();
    @FXML
    public void campos () {

        novoEstudanteEditar = (Estudante) outputTableViewAluno.getSelectionModel().getSelectedItem();
        if (novoEstudanteEditar != null) {
            inputTextFieldNome.setText(novoEstudanteEditar.getNome());
            inputTextFielIdade.setText(String.valueOf(novoEstudanteEditar.getIdade()));
            if (novoEstudanteEditar.getSexo().equals("M")) inputRadioButtonMasculino.setSelected(true);
            else if (novoEstudanteEditar.getSexo().equals("F")) inputRadioButtonFeminino.setSelected(true);
            else if (novoEstudanteEditar.getSexo().equals("N")) inputRadioButtonNaoBinario.setSelected(true);
            inputButtonEditar.setVisible(true);
            inputButtonDeletar.setVisible(true);
            inputButtonSalvar.setVisible(false);
        }
    }

    @FXML
    void delete() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            EstudanteDAO estudanteDAO = new EstudanteDAO();
            estudanteDAO.apagarPorId(novoEstudanteEditar.getId());
            prepararLista();
            inputButtonDeletar.setVisible(false);
            inputButtonEditar.setVisible(false);
            inputButtonSalvar.setVisible(true);
        }
    }

    @FXML
    void editar() {
        if (this.validar()) {
            EstudanteDAO estudanteDAO = new EstudanteDAO();

            String nome = inputTextFieldNome.getText();
            int idade = Integer.valueOf(inputTextFielIdade.getText());
            String sexo = "N";
            Alert alert = new Alert(Alert.AlertType.INFORMATION);

            if (inputRadioButtonMasculino.isSelected()) sexo = "M";
            else if (inputRadioButtonFeminino.isSelected()) sexo = "F";
            else if (inputRadioButtonNaoBinario.isSelected()) sexo = "N";


            alert.setTitle("EDITAR");
            alert.setHeaderText("editado com sucesso");
            alert.show();

            novoEstudanteEditar.setNome(nome);
            novoEstudanteEditar.setIdade(idade);
            novoEstudanteEditar.setSexo(sexo);


            inputTextFieldNome.setText("");
            inputTextFielIdade.setText("");
            inputRadioButtonFeminino.setSelected(false);
            inputRadioButtonMasculino.setSelected(true);
            inputRadioButtonNaoBinario.setSelected(false);

            estudanteDAO.editar(novoEstudanteEditar,novoEstudanteEditar.getId());
            prepararLista();
            inputButtonSalvar.setVisible(true);
            inputButtonEditar.setVisible(false);
            inputButtonDeletar.setVisible(false);

        }
    }


    @FXML
    public void salvar() {
        if (this.validar()) {
            EstudanteDAO estudanteDAO = new EstudanteDAO();
            Estudante novoEstudante = new Estudante();
            String nome = inputTextFieldNome.getText();
            int idade = Integer.valueOf(inputTextFielIdade.getText());
            String sexo = "N";
            Alert alert = new Alert(Alert.AlertType.INFORMATION);

            if (inputRadioButtonMasculino.isSelected()) sexo = "M";
            else if (inputRadioButtonFeminino.isSelected()) sexo = "F";
            else if (inputRadioButtonNaoBinario.isSelected()) sexo = "N";


            alert.setTitle("Salvar");
            alert.setHeaderText("Salvo com sucesso");
            alert.show();

            novoEstudante.setNome(nome);
            novoEstudante.setIdade(idade);
            novoEstudante.setSexo(sexo);


            inputTextFieldNome.setText("");
            inputTextFielIdade.setText("");
            inputRadioButtonFeminino.setSelected(false);
            inputRadioButtonMasculino.setSelected(true);
            inputRadioButtonNaoBinario.setSelected(false);

            estudanteDAO.inserir(novoEstudante);
            prepararLista();

        }
    }

    public boolean validar() {
        StringBuffer nome = new StringBuffer();
        nome.append("Esse campo e obrigatorio -> ");
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("ERRO");
        alert.setHeaderText("erro nos campos ");

        if (inputTextFieldNome.getText().equals("")) {

            alert.setContentText(nome.toString() + "nome");
            alert.show();
            return false;
        }
        if (inputTextFielIdade.getText().equals("")) {
            alert.setContentText(nome.toString() + "idade");
            alert.show();
            return false;
        } else {
            return true;
        }


    }

}
