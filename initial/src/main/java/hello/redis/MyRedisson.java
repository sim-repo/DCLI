package hello.redis;

import hello.model.connectors.JdbConnector;
import hello.model.getter.IGetter;
import hello.security.model.ProtoLogin;
import org.redisson.api.RTopic;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;

@Component("myRedisson")
public class MyRedisson {

    RedissonClient client = null;

    public MyRedisson() {
        Config config = new Config();
        config.useSingleServer()
                .setAddress("redis://127.0.0.1:6379");
        client = Redisson.create(config);
    }

    public void pubConnectors(JdbConnector connector){
        RTopic topic = client.getTopic("jdb.connector");
        topic.publish(connector);
    }

    public void pubGetter(IGetter getter){
        RTopic topic = client.getTopic("getter");
        topic.publish(getter);
    }


    //#################
    //Login functions:
    //#################

    public void pubProtoLogin(ProtoLogin login){
        RTopic topic = client.getTopic("admin.add.protoLogin");
        topic.publish(login);
    }

    public void pubRemoveLoginById(Integer loginId){
        RTopic topic = client.getTopic("admin.remove.login");
        topic.publish(loginId);
    }


    //#################
    //Token functions:
    //#################

    // add or replace
    public void pub_addToken(HashMap<Integer, String> map) {
        RTopic topic = client.getTopic("admin.add.token");
        topic.publish(map);
    }

    // clear
    public void pub_removeToken(ArrayList<Integer> list ) {
        RTopic topic = client.getTopic("admin.clear.token");
        topic.publish(list);
    }


    //#################
    //Authentication mode:
    //#################

    public void pub_setAuthenticationMode(String mode ) {
        RTopic topic = client.getTopic("admin.change.authentication_mode");
        topic.publish(mode);
    }


    //#################
    //Authorization mode:
    //#################
    public void pub_setAuthorizationMode(Boolean mode ) {
        RTopic topic = client.getTopic("admin.change.authorization_mode");
        topic.publish(mode);
    }

    //#################
    //Expire in days:
    //#################
    public void pub_setDefaultExpire(int expireInDays ) {
        RTopic topic = client.getTopic("admin.change.default_expire_in_days");
        topic.publish(expireInDays);
    }


    //#################
    //Roles functions:
    //#################
    // add or replace
    public void pub_addLoginRoles(HashMap<String, String> roleByLoginId) {
        RTopic topic = client.getTopic("admin.add.login.roles");
        topic.publish(roleByLoginId);
    }

    public void pub_removeLoginRoles(HashMap<String, String> roleByLoginId) {
        RTopic topic = client.getTopic("admin.REMOVE.login.roles");
        topic.publish(roleByLoginId);
    }

    public void pub_addGetterRoles(HashMap<String, String> roleByLoginId) {
        RTopic topic = client.getTopic("admin.add.getter.roles");
        topic.publish(roleByLoginId);
    }

    public void pub_removeGetterRoles(HashMap<String, String> roleByLoginId) {
        RTopic topic = client.getTopic("admin.REMOVE.getter.roles");
        topic.publish(roleByLoginId);
    }
}
