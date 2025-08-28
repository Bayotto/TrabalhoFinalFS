package Controller;


import Model.Pokemon;
import Model.Treinador;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import util.HibernateUtil;

import java.util.List;

public class TreinadorController {

    public void cadastrarTreinador(Treinador treinador) throws Exception {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            //Validações de négocio(nome,cidade)


            if (treinador.getNome() == null || treinador.getNome().trim().isEmpty()) {
                throw new Exception("Nome do Treinador é obrigatório.");
            }
            if (treinador.getcidade() == null || treinador.getcidade().trim().isEmpty()) {
                throw new Exception("Nome da Cidade é obrigatório");
            }

            session.persist(treinador);//Salva o objeto no banco
            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException("Erro ao cadastrar treinador", e);
        }
    }

    public List<Treinador> listarTodosTreinador() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // HQL (hibernate query languege - similar ao sql, mas usa o nome da classe
            Query<Treinador> query = session.createQuery("FROM Treinador", Treinador.class);
            return query.getResultList();
        }
    }

    public void atualizarTreinador(Treinador treinador) throws Exception {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.merge(treinador);
            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException("Erro ao atualizar treinador", e);
        }
    }

    public Treinador buscarTreinadorPorId(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Treinador.class, id);

        }
    }

    public void removerTreinador(int id) throws Exception {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Treinador treinador = session.get(Treinador.class, id);
            if (treinador != null) {
                session.remove(treinador); // Remove o objeto
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();

            }
            throw new RuntimeException("Erro ao remover treinador", e);
        }
    }

    public long contarTreinadorPorTipo(String tipo) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Long> query = session.createQuery("SELECT COUNT(*) FROM treinador WHERE tipo1 = :tipo", Long.class);
            query.setParameter("tipo", tipo);
            return query.getSingleResult();

        }
    }

    public Treinador buscarTreinadorPorNome(String nome) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Treinador> query = session.createQuery("FROM Treinador WHERE nome = :nome", Treinador.class);
            query.setParameter("nome", nome);
            return query.getSingleResult();
        }
    }
}
