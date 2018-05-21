package fr.wildcodeschool.githubtracker.dao;

import fr.wildcodeschool.githubtracker.model.Githuber;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import java.util.List;

@ApplicationScoped
@InMemory
public class MemoryGithuberDAO implements GithuberDAO {

    @Inject
    private GithubUtils ghu;

    private  HashMap<String, Githuber> mapGithubers = new HashMap<>();

    @Override
    public List<Githuber> getGithubers() {
        List<Githuber> githubers = new ArrayList<>(mapGithubers.values());
        return githubers;
    }

    @Override
    public Githuber getGithuber(String login) {
        List<Githuber> githubers = getGithubers();
        for (Githuber g : githubers){
            if (login.equals(g.getLogin())){
                return g;
            }
        }
        return null;
    }

    @Override
    public void saveGithuber(Githuber githuber) {
        if (githuber != null){
            mapGithubers.put(githuber.getLogin(), githuber);
        }
    }

    @Override
    public boolean deleteGithuber(long id) {
        return false;
    }

    @PostConstruct
    private void postConstruct() {
        try{
            mapGithubers.put("christellsahli", ghu.parseGithuber("christellsahli"));
            mapGithubers.put("Gregbee", ghu.parseGithuber("Gregbee"));
            mapGithubers.put("jbourbo", ghu.parseGithuber("jbourbo"));
            mapGithubers.put("Lucilediague", ghu.parseGithuber("Lucilediague"));
            mapGithubers.put("arnogc33", ghu.parseGithuber("arnogc33"));
        }
        catch (IOException e){
            throw new RuntimeException(e);
        }

    }

}
