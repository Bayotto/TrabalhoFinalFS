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
        super("Lista de Pokémons", true, true, true, true);
        this.controller = controller;

        setSize(900, 500);
        setLayout(new BorderLayout());

        String[] colunas = {"ID", "Nome", "Tipo Primário", "Tipo Secundário", "Nível", "HP Máximo"};
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

        btnAtualizar.addActionListener(e -> carregarTreinadorsNaTabela());
        btnRemover.addActionListener(e -> removerTreinadorSelecionado());
        btnBuscar.addActionListener(e -> buscarTreinadorsPorNome());
        btnEditar.addActionListener(e -> editarTreinadorSelecionado());


    }


    private void carregarTreinadorsNaTabela() {
        tableModel.setRowCount(0);
        List<Treinador> Treinadors = controller.listarTodostreinadors();
        for (Treinador Treinador : Treinadors) {
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
            int idTreinador = (int) tableModel.getValueAt(selectedRow, 0);

            int confirm = JOptionPane.showConfirmDialog(this,
                    "Tem certeza que deseja remover o Pokémon ID: " + idTreinador + "?",
                    "Confirmar Remoção", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                try {
                    controller.removertreinador(idTreinador);
                    JOptionPane.showMessageDialog(this, "Pokémon removido com sucesso!");
                    carregarTreinadorsNaTabela();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Erro ao remover Pokémon: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Selecione um Pokémon para remover.", "Aviso", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void buscarTreinadorsPorNome() {
        String nomeBusca = txtBuscaNome.getText().trim();
        tableModel.setRowCount(0);

        Treinador Treinadors = controller.buscarTreinadorPorNome(nomeBusca);

        if (Treinadors.getNome().isEmpty() && !nomeBusca.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nenhum Pokémon encontrado com o nome: '" + nomeBusca + "'", "Busca", JOptionPane.INFORMATION_MESSAGE);
        }

        tableModel.addRow(new Object[]{
                Treinadors.getId_treinador(),
                Treinadors.getNome(),

        });
    }


    private void editarTreinadorSelecionado() {
        int selectedRow = tabelaTreinador.getSelectedRow();
        if (selectedRow >= 0) {
            int idTreinador = (int) tableModel.getValueAt(selectedRow, 0);

            TreinadorForm TreinadorForm = new TreinadorForm(controller, idTreinador);
            this.getDesktopPane().add(TreinadorForm);
            TreinadorForm.setVisible(true);
            TreinadorForm.toFront();

            TreinadorForm.addInternalFrameListener(new javax.swing.event.InternalFrameAdapter() {
                @Override
                public void internalFrameClosed(javax.swing.event.InternalFrameEvent e) {
                    carregarTreinadorsNaTabela();
                }
            });

        } else {
            JOptionPane.showMessageDialog(this, "Selecione um Pokémon para editar.", "Aviso", JOptionPane.WARNING_MESSAGE);
        }
    }
}