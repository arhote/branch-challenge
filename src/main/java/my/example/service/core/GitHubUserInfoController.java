package my.example.service.core;

import org.eclipse.egit.github.core.Repository;
import org.eclipse.egit.github.core.User;
import org.eclipse.egit.github.core.service.RepositoryService;
import org.eclipse.egit.github.core.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.regex.Pattern;

@RestController
public class GitHubUserInfoController {

   UserService userService = new UserService();

    @GetMapping("/")
    public GitHubUserInfo gitHubUserInfo(@RequestParam(value = "user", defaultValue = "octocat") String user){
        //Get GitHub info

        String user_name;
        String display_name;
        String avatar;
        String geo_location;
        String email;
        String url;
        String created_at;
        GitHubRepo[] repos;

        //Validate username
        if(!validUser(user)){
            throw new InvalidUsernameException();
        }
        else {
            user_name = user;
        }

        //Get user info from https://api.github.com/users/<user_name>
        try{
            User ghUser = userService.getUser(user_name);
            display_name = ghUser.getName();
            avatar = ghUser.getAvatarUrl();
            geo_location = ghUser.getLocation();
            email = ghUser.getEmail();
            url = ghUser.getHtmlUrl();
            created_at = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(ghUser.getCreatedAt());

        }catch (IOException e){
            throw new InvalidUsernameException();
        }

        //Get user's repo info from https://api.github.com/users/<user_name>/repos
        RepositoryService service = new RepositoryService();

        ArrayList<GitHubRepo> repositories = new ArrayList<>();
        try {
            for (Repository repo : service.getRepositories(user_name)){
                repositories.add(new GitHubRepo(repo.getName(), repo.getSvnUrl()));
            }
            repos = repositories.toArray(new GitHubRepo[0]);
        }catch(IOException e){
            throw new InvalidUsernameException();
        }

        return new GitHubUserInfo(user_name, display_name, avatar, geo_location, email, url, created_at, repos);

    }

    static boolean validUser(String user){
        boolean valid = true;
        final String VALID_CHARACTERS = "^[a-z0-9_-]{0,38}$";

        Pattern pattern;
        pattern = Pattern.compile(VALID_CHARACTERS);

        if (!pattern.matcher(user).matches()) valid = false;
        if (user.startsWith("-")) valid = false;
        if (user.endsWith("-")) valid = false;
        if (user.contains("--")) valid = false;

        return valid;
    }
}
