package Controller;


import Model.Treinador;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import util.HibernateUtil;

import java.util.List;

public class TreinadorController {
    public void cadastrartreinador(Treinador treinador) throws Exception {
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

    public void atualizartreinador(Treinador treinador) throws Exception {
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

    public List<Treinador> listarTodostreinadors() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            //HQL (Hibernate Query Language) - similar ao SQL, mas usa o nome da CLASSE
            Query<Treinador> query = session.createQuery("FROM treinador", Treinador.class);
            return query.getResultList();

        }
    }

    //
    public Treinador buscartreinadorPorId(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Treinador.class, id);

        }
    }


    public void removertreinador(int id) throws Exception {
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

    public long contarTreinadorsPorTipo(String tipo) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Long> query = session.createQuery("SELECT COUNT(*) FROM treinador WHERE tipo1 = :tipo", Long.class);
            query.setParameter("tipo", tipo);
            return query.getSingleResult();

        }
    }


    public Treinador buscarTreinadorPorNome(String nome) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Treinador.class, nome);
        }
    }
}
