package hello.security.model;


import java.io.Serializable;

public class ProtoLogin implements Serializable {
    public Integer id;
    public String name;
    public Integer expireInDays;
    public String password;

}
