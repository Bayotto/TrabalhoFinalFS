package View;

import Controller.PokemonController;
import Controller.TreinadorController;
import Model.Pokemon;
import Model.Treinador;

import javax.swing.*;
import java.awt.*;

public class TreinadorForm extends JInternalFrame {
    private TreinadorController controller;
    private JTextField txtId, txtNome, txtCidade;
    private JButton btnSalvar, btnBuscar;
    private Integer pokemonIdParaEdicao;

    public TreinadorForm(TreinadorController controller, Integer treinadorId) {
        super("Cadastro de Pokémon", true, true, true, true);
        this.controller = controller;
        this.pokemonIdParaEdicao = treinadorId;

        setSize(600, 400);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 45, 5, 45);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        int row = 0;

        // Campo ID
        gbc.gridx = 0;
        gbc.gridy = row;
        add(new JLabel("ID:"), gbc);
        gbc.gridx = 1;
        gbc.gridy = row;
        txtId = new JTextField(10);
        txtId.setEditable(false);
        add(txtId, gbc);
        gbc.gridx = 2;
        gbc.gridy = row;
        row++;

        // Nome
        gbc.gridx = 0;
        gbc.gridy = row;
        add(new JLabel("Nome:"), gbc);
        gbc.gridx = 1;
        gbc.gridy = row;
        gbc.gridwidth = 2;
        txtNome = new JTextField(25);
        add(txtNome, gbc);
        row++;

        // Tipo Primário
        gbc.gridx = 0;
        gbc.gridy = row;
        add(new JLabel("Cidade:"), gbc);
        gbc.gridx = 1;
        gbc.gridy = row;
        gbc.gridwidth = 2;
        txtCidade = new JTextField(25);
        add(txtCidade, gbc);
        row++;


        // Botão Salvar
        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.gridwidth = 3;
        btnSalvar = new JButton("Salvar");
        btnSalvar.addActionListener(e -> salvarPokemon());
        add(btnSalvar, gbc);

        if (pokemonIdParaEdicao != null) {
            carregarPokemonParaEdicao(pokemonIdParaEdicao);
            txtId.setText(String.valueOf(pokemonIdParaEdicao));
            btnBuscar.setEnabled(false);
        }
    }

    private void buscarPokemon() {
        String idStr = JOptionPane.showInputDialog(this, "Digite o ID do Pokémon para buscar:");
        if (idStr != null && !idStr.trim().isEmpty()) {
            try {
                int id = Integer.parseInt(idStr);
                carregarPokemonParaEdicao(id);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "ID inválido. Por favor, digite um número.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void carregarPokemonParaEdicao(int id) {
        try {
            Treinador treinador = controller.buscartreinadorPorId(id);
            if (treinador != null) {
                txtId.setText(String.valueOf(treinador.getId_treinador()));
                txtNome.setText(treinador.getNome());

                pokemonIdParaEdicao = treinador.getId_treinador();
            } else {
                JOptionPane.showMessageDialog(this, "Pokémon com ID " + id + " não encontrado.", "Erro", JOptionPane.ERROR_MESSAGE);
                limparCampos();
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao buscar Pokémon: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void salvarPokemon() {
        try {
            String nome = txtNome.getText().trim();
            String cidade = txtCidade.getText().trim();


            if (pokemonIdParaEdicao == null) {
                controller.cadastrartreinador(new Treinador(nome, cidade));
                JOptionPane.showMessageDialog(this, "Pokémon cadastrado com sucesso!");
            }
            this.dispose();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Nível ou HP Máximo inválido. Por favor, insira valores numéricos válidos.", "Erro de Validação", JOptionPane.ERROR_MESSAGE);
        } catch (IllegalArgumentException e) { // Captura exceções do Model (validações de Nível/HP)
            JOptionPane.showMessageDialog(this, "Erro de validação (Model): " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) { // Captura exceções do Controller
            JOptionPane.showMessageDialog(this, "Erro ao salvar Pokémon: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void limparCampos() {
        txtId.setText("");
        txtNome.setText("");
        txtCidade.setText("");
        pokemonIdParaEdicao = null;
        btnBuscar.setEnabled(true);
    }
}
