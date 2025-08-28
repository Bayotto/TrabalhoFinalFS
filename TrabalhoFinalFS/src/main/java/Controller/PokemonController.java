package Controller;

import Model.Pokemon;
import Model.Treinador;
import org.hibernate.Transaction;
import org.hibernate.Session;
import org.hibernate.query.Query;
import util.HibernateUtil;

import java.sql.SQLException;
import java.util.List;

public class PokemonController {
    public void cadastrarPokemon(Pokemon pokemon) throws Exception {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            //Validações de négocio(nome,tipo,etc.)
            if (pokemon.getNome() == null || pokemon.getNome().trim().isEmpty()) {
                throw new Exception("Errou");
            }

            if (pokemon.getNome() == null || pokemon.getNome().trim().isEmpty()) {
                throw new Exception("Nome do Pokémon é obrigatório.");
            }
            if (pokemon.getTipoPrimario() == null || pokemon.getTipoPrimario().trim().isEmpty()) {
                throw new Exception("Tipo primário é obrigatório.");
            }
            if (pokemon.getTipoPrimario().equalsIgnoreCase(pokemon.getTipoSecundario())) {
                throw new Exception("Tipo secundário não pode ser igual ao primário.");
            }
            if (pokemon.getNivel() < 0 || pokemon.getNivel() > 100) {
                throw new Exception("Nível inválido.");
            }
            if (pokemon.getHpMaximo() <= 0 || pokemon.getHpMaximo() > 300) {
                throw new Exception("HP inválido.");

            }
            session.persist(pokemon);//Salva o objeto no banco
            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException("Erro ao cadastrar Pokemon", e);
        }
    }

    public void atualizarPokemon(Pokemon pokemon) throws Exception {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.merge(pokemon);
            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException("Erro ao atualizar Pokemon", e);
        }
    }

    public List<Pokemon> listarTodosPokemons() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            //HQL (Hibernate Query Language) - similar ao SQL, mas usa o nome da CLASSE
            Query<Pokemon> query = session.createQuery("FROM Pokemon", Pokemon.class);
            return query.getResultList();

        }
    }

    public Pokemon buscarPokemonPorId(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Pokemon.class, id);

        }
    }

    public void removerPokemon(int id) throws Exception {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Pokemon pokemon = session.get(Pokemon.class, id);
            if (pokemon != null) {
                session.remove(pokemon); // Remove o objeto
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();

            }
            throw new RuntimeException("Erro ao remover Pokemon",e);
        }
    }

    public long contarPokemonsPorTipo(String tipo){
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            Query<Long> query = session.createQuery("SELECT COUNT(*) FROM Pokemon WHERE tipo1 = :tipo", Long.class );
            query.setParameter("tipo",tipo);
            return query.getSingleResult();

        }
    }

    public List<Pokemon> buscarPokemonPorNome(String nome) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Pokemon> pokemon = session.createQuery("FROM Pokemon WHERE nome = :nome", Pokemon.class);
            pokemon.setParameter("nome",nome);
            return pokemon.getResultList();
        }
    }

    public List<Pokemon> buscarTreinadorPorNome(int FK_Treinador) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Pokemon> query = session.createQuery("FROM Pokemon WHERE FK_Treinador = :FK_Treinador", Pokemon.class);
            query.setParameter("FK_Treinador", FK_Treinador);
            List<Pokemon> pokes = query.getResultList();
            System.out.println(pokes.getFirst().getNome());
            return query.getResultList();
        }
    }
//    public void curarTodosPokemons(List<Pokemon> pokemons) {
//        Transaction transaction = null;
//        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
//            transaction = session.beginTransaction();
//            for (Pokemon poke : pokemons) {
//                poke.setHp_Atual(poke.getHpMaximo());
//                session.merge(poke);
//            }
//            session.flush();
//            transaction.commit();
//        } catch (Exception e) {
//            System.out.println("Houve um erro ao alterar o HP" + e.getMessage());
//        }
//    }


    public int ajustarNiveisPorTipo(String tipo, int novoNivel) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            String hql = "UPDATE Pokemon SET nivel = :novoNivel WHERE tipoPrimario = :tipo";
            int updatedEntities = session.createQuery(hql)
                    .setParameter("novoNivel", novoNivel)
                    .setParameter("tipo", tipo)
                    .executeUpdate();
            transaction.commit();
            return updatedEntities;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException("Erro ao ajustar niveis por tipo", e);
        }


    }
}

