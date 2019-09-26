package hello.security.model;


import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;


public class Login implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;

    private String name;
    private Date expire;
    private String encryptedPassword;
    private String salt;
    private String token;
    private String oldToken;
    private HashSet<String> roles;


    @Override
    public String toString() {
        return "Login{" +
                "id=" + id +
                ", login='" + name + '\'' +
                ", expire=" + expire +
                ", psw='" + encryptedPassword + '\'' +
                ", salt='" + salt + '\'' +
                '}';
    }
}
