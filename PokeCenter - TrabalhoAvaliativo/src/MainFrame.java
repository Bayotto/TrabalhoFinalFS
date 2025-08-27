import Controller.PokemonController;
import Controller.TreinadorController;
import Model.Pokemon;
import Model.Treinador;
import View.Pokemon.ListaPokemonsPanel;
import View.Pokemon.PokemonForm;
import View.Treinador.ListaTreinadorPanel;
import View.Treinador.TreinadorForm;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class MainFrame extends JFrame {

    private JDesktopPane desktopPane;
    private PokemonController pokemonController;
    private TreinadorController treinadorController;

    public MainFrame() {
        super(" PokéCenter ");
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
        JMenuItem itemCadastrarPokemon = new JMenuItem("Cadastrar Pokémon");
        JMenuItem itemListarPokemons = new JMenuItem("Listar Pokémons");
        JMenuItem itemInserirListaPokemons = new JMenuItem("Inserir Pokémons");

        // Menu Treinador
        JMenu menuTreinadores = new JMenu("Treinadores");
        JMenuItem itemCadastrarTreinador = new JMenuItem("Cadastrar Treinador");
        JMenuItem itemListarTreinadores = new JMenuItem("Listar Treinadores");
        JMenuItem itemInserirListaTreinadores = new JMenuItem("Inserir Treinadores");


        itemCadastrarPokemon.addActionListener(e -> openPokemonForm(null));
        itemListarPokemons.addActionListener(e -> openListaPokemonsPanel());
        itemInserirListaPokemons.addActionListener(e -> {
            try {
                openInsereListaPokemonsPanel();
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });


        itemCadastrarTreinador.addActionListener(e -> openTreinadorForm(null));
        itemListarTreinadores.addActionListener(e -> openListaTreinadoresPanel());
        itemInserirListaTreinadores.addActionListener(e -> {
            try {
                openInsereListaTreinadoresPanel();
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });

        menuPokemons.add(itemCadastrarPokemon);
        menuPokemons.add(itemListarPokemons);
        menuPokemons.add(itemInserirListaPokemons);


        menuTreinadores.add(itemCadastrarTreinador);
        menuTreinadores.add(itemListarTreinadores);
        menuTreinadores.add(itemInserirListaTreinadores);

        menuBar.add(menuTreinadores);
        menuBar.add(menuPokemons);

        // Menu Sair
        JMenu menuSair = new JMenu("Sair");
        JMenuItem itemSair = new JMenuItem("Sair do Sistema");
        itemSair.addActionListener(e -> System.exit(0));

        menuSair.add(itemSair);
        menuBar.add(menuSair);

        setJMenuBar(menuBar);
    }

    private void openPokemonForm(Integer idPokemon) {
            PokemonForm pokemonForm = new PokemonForm(pokemonController, idPokemon);
            desktopPane.add(pokemonForm);
            pokemonForm.setVisible(true);
            pokemonForm.toFront();
    }
    private void openTreinadorForm(Integer idTreinador) {
        TreinadorForm treinadorForm = new TreinadorForm(treinadorController, idTreinador);
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

    private void openListaTreinadoresPanel() {
        ListaTreinadorPanel listaTreinadores = new ListaTreinadorPanel(treinadorController);
        desktopPane.add(listaTreinadores);
        listaTreinadores.setVisible(true);
        listaTreinadores.toFront();
    }

    private void openInsereListaPokemonsPanel() throws Exception {
        List<Pokemon> listaPokemon = new ArrayList<>();

        // KANTO

        listaPokemon.add(new Pokemon("Bulbasaur", "Grama", "Venenoso", 1, 100, 1)); // fk_treinador = 1
        listaPokemon.add(new Pokemon("Ivysaur", "Grama", "Venenoso", 2, 120, 2)); // fk_treinador = 1
        listaPokemon.add(new Pokemon("Venusaur", "Grama", "Venenoso", 3, 150, 3)); // fk_treinador = 2
        listaPokemon.add(new Pokemon("Charmander", "Fogo", null, 4, 90, 4)); // fk_treinador = 2
        listaPokemon.add(new Pokemon("Charmeleon", "Fogo", null, 5, 110, 5)); // fk_treinador = 3
        listaPokemon.add(new Pokemon("Charizard", "Fogo", "Voador", 6, 160, 6)); // fk_treinador = 3
        listaPokemon.add(new Pokemon("Squirtle", "Água", null, 7, 95, 7)); // fk_treinador = 4
        listaPokemon.add(new Pokemon("Wartortle", "Água", null, 8, 115, 8)); // fk_treinador = 4
        listaPokemon.add(new Pokemon("Blastoise", "Água", null, 9, 155, 9)); // fk_treinador = 5
        listaPokemon.add(new Pokemon("Caterpie", "Inseto", null, 10, 45, 10)); // fk_treinador = 5
        listaPokemon.add(new Pokemon("Metapod", "Inseto", null, 11, 60, 11)); // fk_treinador = 1
        listaPokemon.add(new Pokemon("Butterfree", "Inseto", "Voador", 12, 80, 12)); // fk_treinador = 2
        listaPokemon.add(new Pokemon("Weedle", "Inseto", "Venenoso", 13, 50, 13)); // fk_treinador = 3
        listaPokemon.add(new Pokemon("Kakuna", "Inseto", "Venenoso", 14, 65, 13)); // fk_treinador = 4
        listaPokemon.add(new Pokemon("Beedrill", "Inseto", "Venenoso", 15, 85, 14)); // fk_treinador = 5
        listaPokemon.add(new Pokemon("Pidgey", "Normal", "Voador", 16, 60, 15)); // fk_treinador = 1
        listaPokemon.add(new Pokemon("Pidgeotto", "Normal", "Voador", 17, 80, 16)); // fk_treinador = 2
        listaPokemon.add(new Pokemon("Pidgeot", "Normal", "Voador", 18, 120, 17)); // fk_treinador = 3
        listaPokemon.add(new Pokemon("Rattata", "Normal", null, 19, 45, 18)); // fk_treinador = 4
        listaPokemon.add(new Pokemon("Raticate", "Normal", null, 20, 85, 19)); // fk_treinador = 5
        listaPokemon.add(new Pokemon("Spearow", "Normal", "Voador", 21, 55, 19)); // fk_treinador = 1
        listaPokemon.add(new Pokemon("Fearow", "Normal", "Voador", 22, 105, 20)); // fk_treinador = 2
        listaPokemon.add(new Pokemon("Ekans", "Venenoso", null, 23, 70, 21)); // fk_treinador = 3
        listaPokemon.add(new Pokemon("Arbok", "Venenoso", null, 24, 110, 22)); // fk_treinador = 4
        listaPokemon.add(new Pokemon("Pikachu", "Elétrico", null, 25, 100, 23)); // fk_treinador = 5
        listaPokemon.add(new Pokemon("Raichu", "Elétrico", null, 26, 130, 24)); // fk_treinador = 1
        listaPokemon.add(new Pokemon("Sandshrew", "Terrestre", null, 27, 75, 25)); // fk_treinador = 2
        listaPokemon.add(new Pokemon("Sandslash", "Terrestre", null, 28, 115, 26)); // fk_treinador = 3
        listaPokemon.add(new Pokemon("Nidoran♀", "Venenoso", null, 29, 55, 27)); // fk_treinador = 4
        listaPokemon.add(new Pokemon("Nidorina", "Venenoso", null, 30, 85, 28)); // fk_treinador = 5
        listaPokemon.add(new Pokemon("Nidoqueen", "Venenoso", "Terrestre", 31, 140, 29)); // fk_treinador = 1
        listaPokemon.add(new Pokemon("Nidoran♂", "Venenoso", null, 32, 55, 30)); // fk_treinador = 2
        listaPokemon.add(new Pokemon("Nidorino", "Venenoso", null, 33, 85, 31)); // fk_treinador = 3
        listaPokemon.add(new Pokemon("Nidoking", "Venenoso", "Terrestre", 34, 140, 32)); // fk_treinador = 4
        listaPokemon.add(new Pokemon("Clefairy", "Fada", null, 35, 95, 33)); // fk_treinador = 5
        listaPokemon.add(new Pokemon("Clefable", "Fada", null, 36, 130, 34)); // fk_treinador = 1
        listaPokemon.add(new Pokemon("Vulpix", "Fogo", null, 37, 75, 35)); // fk_treinador = 2
        listaPokemon.add(new Pokemon("Ninetales", "Fogo", null, 38, 115, 36)); // fk_treinador = 3
        listaPokemon.add(new Pokemon("Jigglypuff", "Normal", "Fada", 39, 115, 37)); // fk_treinador = 4
        listaPokemon.add(new Pokemon("Wigglytuff", "Normal", "Fada", 40, 145, 38)); // fk_treinador = 5
        listaPokemon.add(new Pokemon("Zubat", "Venenoso", "Voador", 41, 60, 39)); // fk_treinador = 1
        listaPokemon.add(new Pokemon("Golbat", "Venenoso", "Voador", 42, 100, 40)); // fk_treinador = 2
        listaPokemon.add(new Pokemon("Oddish", "Grama", "Venenoso", 43, 70, 41)); // fk_treinador = 3
        listaPokemon.add(new Pokemon("Gloom", "Grama", "Venenoso", 44, 95, 42)); // fk_treinador = 4
        listaPokemon.add(new Pokemon("Vileplume", "Grama", "Venenoso", 45, 125, 43)); // fk_treinador = 5
        listaPokemon.add(new Pokemon("Paras", "Inseto", "Grama", 46, 60, 44)); // fk_treinador = 1
        listaPokemon.add(new Pokemon("Parasect", "Inseto", "Grama", 47, 100, 45)); // fk_treinador = 2
        listaPokemon.add(new Pokemon("Venonat", "Inseto", "Venenoso", 48, 75, 46)); // fk_treinador = 3
        listaPokemon.add(new Pokemon("Venomoth", "Inseto", "Venenoso", 49, 105, 47)); // fk_treinador = 4
        listaPokemon.add(new Pokemon("Diglett", "Terrestre", null, 50, 40, 48)); // fk_treinador = 5
        listaPokemon.add(new Pokemon("Dugtrio", "Terrestre", null, 51, 80, 49)); // fk_treinador = 1
        listaPokemon.add(new Pokemon("Meowth", "Normal", null, 52, 60, 50)); // fk_treinador = 2
        listaPokemon.add(new Pokemon("Persian", "Normal", null, 53, 100, 51)); // fk_treinador = 3
        listaPokemon.add(new Pokemon("Psyduck", "Água", null, 54, 70, 52)); // fk_treinador = 4
        listaPokemon.add(new Pokemon("Golduck", "Água", null, 55, 115, 53)); // fk_treinador = 5
        listaPokemon.add(new Pokemon("Mankey", "Lutador", null, 56, 70, 54)); // fk_treinador = 1
        listaPokemon.add(new Pokemon("Primeape", "Lutador", null, 57, 105, 55)); // fk_treinador = 2
        listaPokemon.add(new Pokemon("Growlithe", "Fogo", null, 58, 80, 56)); // fk_treinador = 3
        listaPokemon.add(new Pokemon("Arcanine", "Fogo", null, 59, 140, 57)); // fk_treinador = 4
        listaPokemon.add(new Pokemon("Poliwag", "Água", null, 60, 65, 58)); // fk_treinador = 5
        listaPokemon.add(new Pokemon("Poliwhirl", "Água", null, 61, 90, 59)); // fk_treinador = 1
        listaPokemon.add(new Pokemon("Poliwrath", "Água", "Lutador", 62, 130, 60)); // fk_treinador = 2
        listaPokemon.add(new Pokemon("Abra", "Psíquico", null, 63, 40, 61)); // fk_treinador = 3
        listaPokemon.add(new Pokemon("Kadabra", "Psíquico", null, 64, 70, 62)); // fk_treinador = 4
        listaPokemon.add(new Pokemon("Alakazam", "Psíquico", null, 65, 110, 63)); // fk_treinador = 5
        listaPokemon.add(new Pokemon("Machop", "Lutador", null, 66, 80, 64)); // fk_treinador = 1
        listaPokemon.add(new Pokemon("Machoke", "Lutador", null, 67, 110, 65)); // fk_treinador = 2
        listaPokemon.add(new Pokemon("Machamp", "Lutador", null, 68, 140, 66)); // fk_treinador = 3
        listaPokemon.add(new Pokemon("Bellsprout", "Grama", "Venenoso", 69, 65, 67)); // fk_treinador = 4
        listaPokemon.add(new Pokemon("Weepinbell", "Grama", "Venenoso", 70, 90, 68)); // fk_treinador = 5
        listaPokemon.add(new Pokemon("Victreebel", "Grama", "Venenoso", 71, 120, 69)); // fk_treinador = 1
        listaPokemon.add(new Pokemon("Tentacool", "Água", "Venenoso", 72, 70, 70)); // fk_treinador = 2
        listaPokemon.add(new Pokemon("Tentacruel", "Água", "Venenoso", 73, 120, 71)); // fk_treinador = 3
        listaPokemon.add(new Pokemon("Geodude", "Pedra", "Terrestre", 74, 80, 72)); // fk_treinador = 4
        listaPokemon.add(new Pokemon("Graveler", "Pedra", "Terrestre", 75, 100, 73)); // fk_treinador = 5
        listaPokemon.add(new Pokemon("Golem", "Pedra", "Terrestre", 76, 130, 74)); // fk_treinador = 1
        listaPokemon.add(new Pokemon("Ponyta", "Fogo", null, 77, 85, 75)); // fk_treinador = 2
        listaPokemon.add(new Pokemon("Rapidash", "Fogo", null, 78, 120, 76)); // fk_treinador = 3
        listaPokemon.add(new Pokemon("Slowpoke", "Água", "Psíquico", 79, 100, 77)); // fk_treinador = 4
        listaPokemon.add(new Pokemon("Slowbro", "Água", "Psíquico", 80, 130, 78)); // fk_treinador = 5
        listaPokemon.add(new Pokemon("Magnemite", "Elétrico", "Aço", 81, 60, 79)); // fk_treinador = 1
        listaPokemon.add(new Pokemon("Magneton", "Elétrico", "Aço", 82, 100, 80)); // fk_treinador = 2
        listaPokemon.add(new Pokemon("Farfetch’d", "Normal", "Voador", 83, 75, 81)); // fk_treinador = 3
        listaPokemon.add(new Pokemon("Doduo", "Normal", "Voador", 84, 70, 82)); // fk_treinador = 4
        listaPokemon.add(new Pokemon("Dodrio", "Normal", "Voador", 85, 110, 83)); // fk_treinador = 5
        listaPokemon.add(new Pokemon("Seel", "Água", null, 86, 85, 84)); // fk_treinador = 1
        listaPokemon.add(new Pokemon("Dewgong", "Água", "Gelo", 87, 120, 85)); // fk_treinador = 2
        listaPokemon.add(new Pokemon("Grimer", "Venenoso", null, 88, 80, 86)); // fk_treinador = 3
        listaPokemon.add(new Pokemon("Muk", "Venenoso", null, 89, 120, 87)); // fk_treinador = 4
        listaPokemon.add(new Pokemon("Shellder", "Água", null, 90, 60, 88)); // fk_treinador = 5
        listaPokemon.add(new Pokemon("Cloyster", "Água", "Gelo", 91, 130, 89)); // fk_treinador = 1
        listaPokemon.add(new Pokemon("Gastly", "Fantasma", "Venenoso", 92, 60, 90)); // fk_treinador = 2
        listaPokemon.add(new Pokemon("Haunter", "Fantasma", "Venenoso", 93, 80, 91)); // fk_treinador = 3
        listaPokemon.add(new Pokemon("Gengar", "Fantasma", "Venenoso", 94, 120, 92)); // fk_treinador = 4
        listaPokemon.add(new Pokemon("Onix", "Pedra", "Terrestre", 95, 100, 93)); // fk_treinador = 5
        listaPokemon.add(new Pokemon("Drowzee", "Psíquico", null, 96, 75, 94)); // fk_treinador = 1
        listaPokemon.add(new Pokemon("Hypno", "Psíquico", null, 97, 110, 95)); // fk_treinador = 2
        listaPokemon.add(new Pokemon("Krabby", "Água", null, 98, 60, 96)); // fk_treinador = 3
        listaPokemon.add(new Pokemon("Kingler", "Água", null, 99, 110, 97)); // fk_treinador = 4
        listaPokemon.add(new Pokemon("Voltorb", "Elétrico", null, 100, 60, 98)); // fk_treinador = 5
        listaPokemon.add(new Pokemon("Electrode", "Elétrico", null, 11, 90, 99)); // fk_treinador = 1
        listaPokemon.add(new Pokemon("Exeggcute", "Grama", "Psíquico", 12, 70, 100)); // fk_treinador = 2
        listaPokemon.add(new Pokemon("Exeggutor", "Grama", "Psíquico", 13, 120, 101)); // fk_treinador = 3
        listaPokemon.add(new Pokemon("Cubone", "Terrestre", null, 14, 65, 102)); // fk_treinador = 4
        listaPokemon.add(new Pokemon("Marowak", "Terrestre", null, 15, 95, 103)); // fk_treinador = 5
        listaPokemon.add(new Pokemon("Hitmonlee", "Lutador", null, 16, 110, 104)); // fk_treinador = 1
        listaPokemon.add(new Pokemon("Hitmonchan", "Lutador", null, 17, 110, 105)); // fk_treinador = 2
        listaPokemon.add(new Pokemon("Lickitung", "Normal", null, 18, 90, 106)); // fk_treinador = 3
        listaPokemon.add(new Pokemon("Koffing", "Venenoso", null, 19, 70, 107)); // fk_treinador = 4
        listaPokemon.add(new Pokemon("Weezing", "Venenoso", null, 10, 120, 108)); // fk_treinador = 5
        listaPokemon.add(new Pokemon("Rhyhorn", "Terrestre", "Pedra", 11, 95, 109)); // fk_treinador = 1
        listaPokemon.add(new Pokemon("Rhydon", "Terrestre", "Pedra", 12, 130, 110)); // fk_treinador = 2
        listaPokemon.add(new Pokemon("Chansey", "Normal", null, 13, 250, 111)); // fk_treinador = 3
        listaPokemon.add(new Pokemon("Tangela", "Grama", null, 14, 90, 112)); // fk_treinador = 4
        listaPokemon.add(new Pokemon("Kangaskhan", "Normal", null, 15, 120, 113)); // fk_treinador = 5
        listaPokemon.add(new Pokemon("Horsea", "Água", null, 16, 65, 114)); // fk_treinador = 1
        listaPokemon.add(new Pokemon("Seadra", "Água", null, 17, 95, 115)); // fk_treinador = 2
        listaPokemon.add(new Pokemon("Goldeen", "Água", null, 18, 70, 116)); // fk_treinador = 3
        listaPokemon.add(new Pokemon("Seaking", "Água", null, 19, 100, 117)); // fk_treinador = 4
        listaPokemon.add(new Pokemon("Staryu", "Água", null, 20, 70, 118)); // fk_treinador = 5
        listaPokemon.add(new Pokemon("Starmie", "Água", "Psíquico", 21, 110, 119)); // fk_treinador = 1
        listaPokemon.add(new Pokemon("Mr. Mime", "Psíquico", "Fada", 22, 90, 120)); // fk_treinador = 2
        listaPokemon.add(new Pokemon("Scyther", "Inseto", "Voador", 23, 110, 121)); // fk_treinador = 3
        listaPokemon.add(new Pokemon("Jynx", "Gelo", "Psíquico", 24, 95, 122)); // fk_treinador = 4
        listaPokemon.add(new Pokemon("Electabuzz", "Elétrico", null, 25, 110, 123)); // fk_treinador = 5
        listaPokemon.add(new Pokemon("Magmar", "Fogo", null, 26, 110, 124)); // fk_treinador = 1
        listaPokemon.add(new Pokemon("Pinsir", "Inseto", null, 27, 100, 125)); // fk_treinador = 2
        listaPokemon.add(new Pokemon("Tauros", "Normal", null, 28, 100, 126)); // fk_treinador = 3
        listaPokemon.add(new Pokemon("Magikarp", "Água", null, 29, 30, 127)); // fk_treinador = 4
        listaPokemon.add(new Pokemon("Gyarados", "Água", "Voador", 30, 150, 128)); // fk_treinador = 5
        listaPokemon.add(new Pokemon("Lapras", "Água", "Gelo", 31, 130, 129)); // fk_treinador = 1
        listaPokemon.add(new Pokemon("Ditto", "Normal", null, 32, 60, 130)); // fk_treinador = 2
        listaPokemon.add(new Pokemon("Eevee", "Normal", null, 33, 85, 131)); // fk_treinador = 3
        listaPokemon.add(new Pokemon("Vaporeon", "Água", null, 34, 130, 132)); // fk_treinador = 4
        listaPokemon.add(new Pokemon("Jolteon", "Elétrico", null, 35, 110, 133)); // fk_treinador = 5
        listaPokemon.add(new Pokemon("Flareon", "Fogo", null, 36, 110, 134)); // fk_treinador = 1
        listaPokemon.add(new Pokemon("Porygon", "Normal", null, 37, 85, 135)); // fk_treinador = 2
        listaPokemon.add(new Pokemon("Omanyte", "Pedra", "Água", 38, 70, 136)); // fk_treinador = 3
        listaPokemon.add(new Pokemon("Omastar", "Pedra", "Água", 39, 110, 137)); // fk_treinador = 4
        listaPokemon.add(new Pokemon("Kabuto", "Pedra", "Água", 40, 70, 138)); // fk_treinador = 5
        listaPokemon.add(new Pokemon("Kabutops", "Pedra", "Água", 41, 110, 139)); // fk_treinador = 1
        listaPokemon.add(new Pokemon("Aerodactyl", "Pedra", "Voador", 42, 130, 140)); // fk_treinador = 2
        listaPokemon.add(new Pokemon("Snorlax", "Normal", null, 43, 160, 141)); // fk_treinador = 3
        listaPokemon.add(new Pokemon("Articuno", "Gelo", "Voador", 44, 140, 142)); // fk_treinador = 4
        listaPokemon.add(new Pokemon("Zapdos", "Elétrico", "Voador", 45, 140, 143)); // fk_treinador = 5
        listaPokemon.add(new Pokemon("Moltres", "Fogo", "Voador", 46, 140, 144)); // fk_treinador = 1
        listaPokemon.add(new Pokemon("Dratini", "Dragão", null, 47, 90, 145)); // fk_treinador = 2
        listaPokemon.add(new Pokemon("Dragonair", "Dragão", null, 48, 125, 146)); // fk_treinador = 3
        listaPokemon.add(new Pokemon("Dragonite", "Dragão", "Voador", 49, 160, 147)); // fk_treinador = 4
        listaPokemon.add(new Pokemon("Mewtwo", "Psíquico", null, 50, 200, 148)); // fk_treinador = 5
        listaPokemon.add(new Pokemon("Mew", "Psíquico", null, 51, 200, 149)); // fk_treinador = 1
        pokemonController.inserirListaPokemons(listaPokemon);
    }

    private void openInsereListaTreinadoresPanel() throws Exception {
        List<Treinador> listaTreinador = new ArrayList<>();

        listaTreinador.add(new Treinador("Ash","kanto"));

        treinadorController.inserirListaTreinadores(listaTreinador);

    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new MainFrame().setVisible(true);
        });
    }
}