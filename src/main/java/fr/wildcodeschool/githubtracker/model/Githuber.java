package fr.wildcodeschool.githubtracker.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;


@Entity
@Table(name="githuber")
public class Githuber {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="github_id")
    private Long githubId;

    @Column(name="name")
    private String name;

    @Column(name="url")
    private String url;

    @Column(name="email")
    private String email;

    @Column(name="login")
    private String login;

    @Column(name="bio")
    private String bio;

    @Column(name="location")
    private String location;

    @Column(name="avatar_url")
    private String avatarUrl;


    public Githuber() {
    }

    public Githuber(long id, String name, String email, String login, String avatarUrl) {

        this.githubId = id;
        this.name = name;
        this.email = email;
        this.login = login;
        this.avatarUrl = avatarUrl;
    }

    @JsonCreator
    public Githuber(@JsonProperty("id") long id,
                    @JsonProperty("name") String name,
                    @JsonProperty("url") String url,
                    @JsonProperty("email") String email,
                    @JsonProperty("login") String login,
                    @JsonProperty("bio") String bio,
                    @JsonProperty("location") String location,
                    @JsonProperty("avatar_url") String avatarUrl) {

        this.githubId = id;
        this.name = name;
        this.url = url;
        this.email = email;
        this.login = login;
        this.bio = bio;
        this.location = location;
        this.avatarUrl = avatarUrl;

    }

    public long getId() { return id; }

    public void setId(long id) {this.id = id;}

    public long getGithubId() {
        return githubId;
    }

    public void setGithubId(long githubId) {
        this.githubId = githubId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
