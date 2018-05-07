package fr.wildcodeschool.githubtracker.service;

import fr.wildcodeschool.githubtracker.dao.GithubUtils;
import fr.wildcodeschool.githubtracker.dao.GithuberDAO;
import fr.wildcodeschool.githubtracker.dao.InMemory;
import fr.wildcodeschool.githubtracker.dao.MemoryGithuberDAO;
import fr.wildcodeschool.githubtracker.model.Githuber;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.io.IOException;
import java.util.List;

@Dependent
public class GithubersService {

    @Inject
    @InMemory
    private GithuberDAO dao;

    @Inject
    private GithubUtils ghu;

    public List<Githuber> getAllGithubers(){
        return dao.getGithubers();
    }

    public Githuber getGithuber(final String login){

        List<Githuber> githubers = getAllGithubers();
        for (Githuber g : githubers){
            if (login.equals(g.getLogin())){
                return g;
            }
        }

        return null;

    }

    public void track(String login){
        try{
            dao.saveGithuber(ghu.parseGithuber(login));
        }
        catch (IOException e){
           throw new RuntimeException(e);
        }

    }


}
