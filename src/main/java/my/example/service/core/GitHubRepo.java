package my.example.service.core;

public class GitHubRepo {
    private String name;
    private String url;

    public GitHubRepo(String name, String url){
        this.name = name;
        this.url = url;
    }

    public String getName(){
        return name;
    }

    public String getUrl() {
        return url;
    }
}
