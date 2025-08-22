package View;

import Controller.TreinadorController;
import Model.Treinador;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ListaTreinadorPanel extends JInternalFrame {
    private TreinadorController controller;
    private JTable tabelaTreinador;
    private DefaultTableModel tableModel;
    private JButton btnAtualizar, btnRemover, btnBuscar, btnEditar;
    private JTextField txtBuscaNome;

    public ListaTreinadorPanel(TreinadorController controller) {
        super("Lista de Treinadores", true, true, true, true);
        this.controller = controller;

        setSize(900, 500);
        setLayout(new BorderLayout());

        String[] colunas = {"ID", "Nome", "Cidade"};
        tableModel = new DefaultTableModel(colunas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tabelaTreinador = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(tabelaTreinador);
        add(scrollPane, BorderLayout.CENTER);

        JPanel panelAcoes = new JPanel(new FlowLayout(FlowLayout.LEFT));
        txtBuscaNome = new JTextField(20);
        btnBuscar = new JButton("Buscar por Nome");
        btnAtualizar = new JButton("Atualizar Tabela");
        btnRemover = new JButton("Remover Selecionado");
        btnEditar = new JButton("Editar Selecionado");

        panelAcoes.add(new JLabel("Nome:"));
        panelAcoes.add(txtBuscaNome);
        panelAcoes.add(btnBuscar);
        panelAcoes.add(btnAtualizar);
        panelAcoes.add(btnRemover);
        panelAcoes.add(btnEditar);
        add(panelAcoes, BorderLayout.NORTH);

        btnAtualizar.addActionListener(e -> carregarTreinadorNaTabela());
        btnRemover.addActionListener(e -> removerTreinadorSelecionado());
        btnBuscar.addActionListener(e -> buscarTreinadorPorNome());
        btnEditar.addActionListener(e -> editarTreinadorSelecionado());
    }

    private void carregarTreinadorNaTabela() {
        tableModel.setRowCount(0);
        List<Treinador> Treinadores = controller.listarTodosTreinador();
        for (Treinador Treinador : Treinadores) {
            tableModel.addRow(new Object[]{
                    Treinador.getId_treinador(),
                    Treinador.getNome(),
                    Treinador.getcidade()
            });
        }
    }

    private void removerTreinadorSelecionado() {
        int selectedRow = tabelaTreinador.getSelectedRow();
        if (selectedRow >= 0) {
            int IdTreinador = (int) tableModel.getValueAt(selectedRow, 0);

            int confirm = JOptionPane.showConfirmDialog(this,
                    "Tem certeza que deseja remover o Treiandor ID: " + IdTreinador + "?",
                    "Confirmar Remoção", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                try {
                    controller.removerTreinador(IdTreinador);
                    JOptionPane.showMessageDialog(this, "Treinador removido com sucesso!");
                    carregarTreinadorNaTabela();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Erro ao remover Treinador: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Selecione um Treinador para remover.", "Aviso", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void buscarTreinadorPorNome() {
        String nomeTreinadorBusca = txtBuscaNome.getText().trim();
        tableModel.setRowCount(0);

        Treinador Treinadors = controller.buscarTreinadorPorNome(nomeTreinadorBusca);

        if (Treinadors.getNome().isEmpty() && !nomeTreinadorBusca.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nenhum Treinador encontrado com o nome: '" + nomeTreinadorBusca + "'", "Busca", JOptionPane.INFORMATION_MESSAGE);
        }

        tableModel.addRow(new Object[]{
                Treinadors.getId_treinador(),
                Treinadors.getNome(),
                Treinadors.getcidade()

        });
    }

    private void editarTreinadorSelecionado() {
        int selectedRow = tabelaTreinador.getSelectedRow();
        if (selectedRow >= 0) {
            int IdTreinador = (int) tableModel.getValueAt(selectedRow, 0);

            TreinadorForm treinadorForm = new TreinadorForm(controller, IdTreinador);
            this.getDesktopPane().add(treinadorForm);
            treinadorForm.setVisible(true);
            treinadorForm.toFront();

            treinadorForm.addInternalFrameListener(new javax.swing.event.InternalFrameAdapter() {
                @Override
                public void internalFrameClosed(javax.swing.event.InternalFrameEvent e) {
                    carregarTreinadorNaTabela();
                }
            });

        } else {
            JOptionPane.showMessageDialog(this, "Selecione um Pokémon para editar.", "Aviso", JOptionPane.WARNING_MESSAGE);
        }
    }
}