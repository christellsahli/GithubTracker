package fr.wildcodeschool.githubtracker.dao;

import fr.wildcodeschool.githubtracker.model.Githuber;

import javax.enterprise.context.Dependent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@Dependent
public class DumbGithuberDAO implements GithuberDAO{
    private final static List<Githuber> githuberList = new ArrayList<>(5);

   static {
        githuberList.add(new Githuber(1L, "christellsahli", "christellsahli@gmail.com", "christellsahli", "https://avatars3.githubusercontent.com/u/38050036?s=88&v=4"));
        githuberList.add(new Githuber(2L, "Gregbee", "backtopa@gmail.com", "Gregbee", "https://avatars1.githubusercontent.com/u/14792727?s=88&v=4"));
        githuberList.add(new Githuber(3L, "Julien Bourbonnais", "jbourbo@gmail.com", "jbourbo", "https://avatars0.githubusercontent.com/u/5736082?s=88&v=4"));
        githuberList.add(new Githuber(4L, "Lucilediague", "diaguelucile@gmail.com", "Lucilediague", "https://avatars3.githubusercontent.com/u/24568268?s=88&v=4"));
        githuberList.add(new Githuber(5L, "arnogc33", "guillou.arnaud@gmail.com", "arnogc33", "https://avatars2.githubusercontent.com/u/35994942?s=88&v=4"));
    }

    private List<Githuber> githubers = Collections.unmodifiableList(githuberList);

    @Override
    public List<Githuber> getGithubers() {
        return githubers;
    }

    @Override
    public Githuber getGithuber(String login) {
        return null;
    }

    @Override
    public boolean deleteGithuber(long id) {
        return false;
    }

    @Override
    public void saveGithuber(Githuber githuber) throws UnsupportedOperationException {

    }


}


