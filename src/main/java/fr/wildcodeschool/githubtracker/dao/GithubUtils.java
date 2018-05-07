package fr.wildcodeschool.githubtracker.dao;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.wildcodeschool.githubtracker.model.Githuber;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.io.IOException;
import java.net.URL;

@ApplicationScoped
public class GithubUtils {

    private final static String githubUrl = "https://api.github.com/users/";

    @Inject
    private ObjectMapper om;

    public Githuber parseGithuber(String login) throws IOException {

        URL url = new URL(githubUrl + login);
        try {
            return om.readValue(url, Githuber.class);
        }
        catch (Exception e){
            return null;
        }

    }
}

