package hello.pubsub;

import hello.model.connectors.JdbConnector;
import hello.model.getter.IGetter;
import hello.security.model.ProtoLogin;
import org.redisson.api.*;
import org.redisson.Redisson;
import org.redisson.config.Config;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Component("pubSub")
public class PubSub {

    RedissonClient client;

    public PubSub() {
        Config config = new Config();
        config.useSingleServer()
                .setAddress("redis://127.0.0.1:6379");
        client = Redisson.create(config);
    }

    public void pubConnectors(JdbConnector connector){
        RTopic topic = client.getTopic("jdb.connector");
        topic.publish(connector);
        persist_Connectors(connector);
    }

    private void persist_Connectors(JdbConnector connector) {
        RMap<Integer, JdbConnector> map = client.getMap("jdbcConnectors");
        map.fastPut(connector.id, connector);
    }

    public void pubGetter(IGetter getter){
        RTopic topic = client.getTopic("getter");
        topic.publish(getter);
        persist_Getter(getter);
    }

    private void persist_Getter(IGetter getter) {
        RMap<Integer, IGetter> map = client.getMap("jdbcGetter");
        map.fastPut(getter.getId(), getter);
    }


    //#################
    //Login functions:
    //#################

    public void pubProtoLogin(ProtoLogin login){
        RTopic topic = client.getTopic("admin.add.protoLogin");
        topic.publish(login);
        persist_addLogin(login);
    }

    public void pubRemoveLoginById(Integer loginId){
        RTopic topic = client.getTopic("admin.remove.login");
        topic.publish(loginId);
    }

    private void persist_addLogin(ProtoLogin login) {
        RMap<Integer, ProtoLogin> map = client.getMap("protoLoginById");
        map.fastPut(login.id, login);
    }

    //#################
    //Token functions:
    //#################

    // add or replace
    public void pub_addToken(HashMap<Integer, String> tokenByLoginId) {
        RTopic topic = client.getTopic("admin.add.token");
        topic.publish(tokenByLoginId);
        persist_addToken(tokenByLoginId);
    }

    // clear
    public void pub_removeToken(ArrayList<Integer> loginIds ) {
        RTopic topic = client.getTopic("admin.clear.token");
        topic.publish(loginIds);
        persist_removeToken(loginIds);
    }

    private void persist_addToken(HashMap<Integer, String> tokenByLoginId) {
        RMap<Integer, String> map = client.getMap("tokenByLoginId");
        for(Map.Entry<Integer,String> element : tokenByLoginId.entrySet()){
            map.fastPut(element.getKey(), element.getValue());
        }
    }

    private void persist_removeToken(ArrayList<Integer> loginIds) {
        RMap<Integer, String> map = client.getMap("tokenByLoginId");
        loginIds.forEach(id -> {
            map.fastRemove(id);
            System.out.println("removed: "+id);
        });
    }

    //#################
    //Authentication mode:
    //#################

    public void pub_setAuthenticationMode(String mode ) {
        RTopic topic = client.getTopic("admin.change.authentication_mode");
        topic.publish(mode);
        persist_setAuthenticationMode(mode);
    }

    private void persist_setAuthenticationMode(String mode) {
        RMap<Integer, String> map = client.getMap("authenticationMode");
        map.fastPut(0,mode);
    }


    //#################
    //Authorization mode:
    //#################
    public void pub_setAuthorizationMode(Boolean mode ) {
        RTopic topic = client.getTopic("admin.change.authorization_mode");
        topic.publish(mode);
        persist_setAuthorizationMode(mode);
    }

    private void persist_setAuthorizationMode(Boolean mode) {
        RMap<Integer, Boolean> map = client.getMap("authorizationMode");
        map.fastPut(0,mode);
    }

    //#################
    //Expire in days:
    //#################
    public void pub_setDefaultExpire(int expireInDays ) {
        RTopic topic = client.getTopic("admin.change.default_expire_in_days");
        topic.publish(expireInDays);
        persist_setDefaultExpire(expireInDays);
    }

    private void persist_setDefaultExpire(int expireInDays) {
        RMap<Integer, Integer> map = client.getMap("expireInDays");
        map.fastPut(0,expireInDays);
    }

    //#################
    //Roles functions:
    //#################
    // add or replace
    public void pub_addLoginRoles(HashMap<Integer, String> roleByLoginId) {
        RTopic topic = client.getTopic("admin.add.login.roles");
        topic.publish(roleByLoginId);
        persist_addLoginRoles(roleByLoginId);
    }

    private void persist_addLoginRoles(HashMap<Integer, String> roleByLoginId) {
        RMap<Integer, String> map = client.getMap("addLoginRoles");
        for(Map.Entry<Integer,String> element : roleByLoginId.entrySet()){
            map.fastPut(element.getKey(), element.getValue());
        }
    }

    public void pub_removeLoginRoles(HashMap<Integer, String> roleByLoginId) {
        RTopic topic = client.getTopic("admin.REMOVE.login.roles");
        topic.publish(roleByLoginId);
        persist_removeLoginRoles(roleByLoginId);
    }

    private void persist_removeLoginRoles(HashMap<Integer, String> roleByLoginId) {
        RMap<Integer, String> map = client.getMap("removeLoginRoles");
        for(Map.Entry<Integer,String> element : roleByLoginId.entrySet()){
            map.remove(element.getKey());
        }
    }

    public void pub_addGetterRoles(HashMap<Integer, String> roleByLoginId) {
        RTopic topic = client.getTopic("admin.add.getter.roles");
        topic.publish(roleByLoginId);
        persist_addGetterRoles(roleByLoginId);
    }

    private void persist_addGetterRoles(HashMap<Integer, String> roleByLoginId) {
        RMap<Integer, String> map = client.getMap("addGetterRoles");
        for(Map.Entry<Integer,String> element : roleByLoginId.entrySet()){
            map.fastPut(element.getKey(), element.getValue());
        }
    }

    public void pub_removeGetterRoles(HashMap<Integer, String> roleByLoginId) {
        RTopic topic = client.getTopic("admin.REMOVE.getter.roles");
        topic.publish(roleByLoginId);
        persist_removeGetterRoles(roleByLoginId);
    }

    private void persist_removeGetterRoles(HashMap<Integer, String> roleByLoginId) {
        RMap<Integer, String> map = client.getMap("removeGetterRoles");
        for(Map.Entry<Integer,String> element : roleByLoginId.entrySet()){
            map.remove(element.getKey());
        }
    }
}
