package my.example.service.core;

public class GitHubUserInfo {
    private String user_name;
    private String display_name;
    private String avatar;
    private String geo_location;
    private String email;
    private String url;
    private String created_at;
    private GitHubRepo[] repos;

    public GitHubUserInfo(String user_name, String display_name, String avatar, String geo_location, String email,
                          String url, String created_at, GitHubRepo[] repos){

        this.user_name = user_name;
        this.display_name = display_name;
        this.avatar = avatar;
        this.geo_location = geo_location;
        this.email = email;
        this.url = url;
        this.created_at = created_at;
        this.repos = new GitHubRepo[repos.length];
        System.arraycopy(repos, 0, this.repos, 0, repos.length);

    }

    public String getUser_name() {
        return user_name;
    }

    public String getDisplay_name() {
        return display_name;
    }

    public String getAvatar() {
        return avatar;
    }

    public String getGeo_location() {
        return geo_location;
    }

    public String getEmail() {
        return email;
    }

    public String getUrl() {
        return url;
    }

    public String getCreated_at() {
        return created_at;
    }

    public GitHubRepo[] getRepos() {
        return repos;
    }
}
