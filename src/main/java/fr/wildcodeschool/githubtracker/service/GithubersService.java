package fr.wildcodeschool.githubtracker.service;

import fr.wildcodeschool.githubtracker.dao.*;
import fr.wildcodeschool.githubtracker.model.Githuber;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.io.IOException;
import java.util.List;

@Dependent
public class GithubersService {

    @Inject
    @Jpa
    private GithuberDAO dao;

    @Inject
    private GithubUtils ghu;

    public List<Githuber> getAllGithubers(){
        return dao.getGithubers();
    }

    public Githuber getGithuber(final String login){
        return dao.getGithuber(login);
    }

    public void track(String login){
        try{
            dao.saveGithuber(ghu.parseGithuber(login));
        }
        catch (IOException e){
            throw new RuntimeException(e);
        }

    }

    public void untrack(long id){
        dao.deleteGithuber(id);
    }


}
