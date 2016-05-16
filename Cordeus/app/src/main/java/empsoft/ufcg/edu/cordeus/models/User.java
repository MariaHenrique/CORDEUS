package empsoft.ufcg.edu.cordeus.models;

import java.util.List;

public class User {

    private String username;
    private String password;
    private List<Cordel> cordels;

    public  User(String username, String password) throws Exception {
        if(username == null || username.equals("")){
            throw new Exception("Invalid username.");
        }
        if(password == null || password.equals("")){
            throw new Exception("Invalid password.");
        }
        this.username = username;
        this.password = password;
    }


    public String getUserame() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setUsername(String username) throws Exception {
        if(username == null || username.equals("")){
            throw new Exception("Invalid username.");
        }
        this.username = username;
    }

    public void setPassword(String password) throws Exception {
        if(password == null || password.equals("")){
            throw new Exception("Invalid password.");
        }
        this.password = password;
    }

    public List<Cordel> getCordels(){
        return cordels;
    }

    public void addForm(Cordel cordel)throws Exception {
        if(cordel == null){
            throw new Exception("Form is null.");
        }
        cordels.add(cordel);
    }
}
