import Controller.PokemonController;
import Controller.TreinadorController;
import Model.Pokemon;
import Services.JsonReader;
import View.ListaPokemonsPanel;
import View.ListaTreinadorPanel;
import View.PokemonForm;
import View.TreinadorForm;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class MainApp extends JFrame {

    private JDesktopPane desktopPane;
    private PokemonController pokemonController;
    private TreinadorController treinadorController;

    public MainApp() {
        super("Sistema de Gerenciamento do Poke Center");
        this.pokemonController = new PokemonController();
        this.treinadorController = new TreinadorController();

        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        desktopPane = new JDesktopPane();
        setContentPane(desktopPane);

        createMenuBar();
    }

    private void createMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        // Menu Pokémons
        JMenu menuPokemons = new JMenu("Pokémons");
        JMenu menuTreinador = new JMenu("Treinador");
//        JMenu menuJson = new JMenu("JSON");
        JMenuItem itemCadastrarPokemon = new JMenuItem("Cadastrar Pokémon");
        JMenuItem itemCadastrarTreinador = new JMenuItem("Cadastrar Treinador");
        JMenuItem itemListarPokemons = new JMenuItem("Listar Pokémons");
        JMenuItem itemListarTreinadores = new JMenuItem("Listar Treinador");
//        JMenuItem itemJsonList = new JMenuItem("JSON");

        itemCadastrarPokemon.addActionListener(e -> openPokemonForm(null));
        itemCadastrarTreinador.addActionListener(e -> openTreinadorForm(null));
        itemListarPokemons.addActionListener(e -> openListaPokemonsPanel());
        itemListarTreinadores.addActionListener(e -> openListaTreinadorPanel());
//        itemJsonList.addActionListener(e -> openJsonForm());


        menuPokemons.add(itemCadastrarPokemon);
        menuPokemons.add(itemListarPokemons);
        menuTreinador.add(itemCadastrarTreinador);
        menuTreinador.add(itemListarTreinadores);
//        menuBar.add(itemJsonList);

        menuBar.add(menuPokemons);
        menuBar.add(menuTreinador);
//        menuBar.add(menuJson);

        // Menu Sair
        JMenu menuSair = new JMenu("Sair");
        JMenuItem itemSair = new JMenuItem("Sair do Sistema");
        itemSair.addActionListener(e -> System.exit(0));

        menuSair.add(itemSair);
        menuBar.add(menuSair);

        setJMenuBar(menuBar);


    }

//    private void openJsonForm() {
//        List<Pokemon> pokemonsParaInserir =
//                System.out.println("Lidos " + pokemonsParaInserir.size() + pokemonController.inserirLista(pokemonsParaInserir);
//            System.out.println("Carga de Pokémons concluída com sucesso!");
//        } catch IOException e; {
//           System.err.println("Erro ao carregar o arquivo JSON: " + e.getMessage());
//            e.printStackTrace();
//    }

    private void openPokemonForm(Integer idPokemon) {
        PokemonForm pokemonForm = new PokemonForm(pokemonController, idPokemon);
        desktopPane.add(pokemonForm);
        pokemonForm.setVisible(true);
        pokemonForm.toFront();


    }

    private void openTreinadorForm(Integer Treinadorid) {
        TreinadorForm treinadorForm = new TreinadorForm(treinadorController, Treinadorid);
        desktopPane.add(treinadorForm);
        treinadorForm.setVisible(true);
        treinadorForm.toFront();

    }

    private void openListaPokemonsPanel() {
        ListaPokemonsPanel listaPokemons = new ListaPokemonsPanel(pokemonController);
        desktopPane.add(listaPokemons);
        listaPokemons.setVisible(true);
        listaPokemons.toFront();
    }

    private void openListaTreinadorPanel() {
        ListaTreinadorPanel listaTreinadores = new ListaTreinadorPanel(treinadorController);
        desktopPane.add(listaTreinadores);
        listaTreinadores.setVisible(true);
        listaTreinadores.toFront();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new MainApp().setVisible(true);
        });
    }
}
