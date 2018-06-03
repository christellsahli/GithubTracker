package fr.wildcodeschool.githubtracker.dao;

import fr.wildcodeschool.githubtracker.model.Githuber;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
@Jpa
public class JPAGithuberDAO implements GithuberDAO {

    @Inject
    private GithubUtils ghu;

    @PersistenceContext(unitName = "bdd_githubertracker_PU")
    private EntityManager em;

    public JPAGithuberDAO() {
    }

    @Override
    public List<Githuber> getGithubers() {

        return em.createQuery("SELECT g from Githuber g")
                .getResultList();

    }

    @Override
    public Githuber getGithuber(String login) {
        try{
            Query query = em.createQuery("SELECT g from Githuber g where g.login = :login");
            query.setParameter("login", login );
            List results =  query.getResultList();
            if (results.isEmpty()){
                return null;
            }
            else{
                return (Githuber) results.get(0);
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return null;


    }

    @Override
    @Transactional
    public void saveGithuber(Githuber githuber) {
        if (githuber != null && getGithuber(githuber.getLogin()) == null){
            try{
                em.persist(githuber);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    @Override
    @Transactional
    public boolean deleteGithuber(long id){
        Githuber githuber = em.find(Githuber.class,id);

        if (githuber != null){
            try{
                em.remove(githuber);
                return true;
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return false;

    }

}
