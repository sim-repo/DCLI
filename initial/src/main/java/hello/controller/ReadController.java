package hello.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import hello.model.connectors.JdbConnector;
import hello.model.getter.DbGetter;
import hello.security.model.ProtoLogin;
import hello.redis.MyRedisson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Controller
public class ReadController {


    @Autowired
    private MyRedisson myRedisson;

    public static ResponseEntity<String> getBadResponse(){
        return new ResponseEntity<String>("request does not contain a parameter named 'method' ", HttpStatus.BAD_REQUEST);
    }


    @RequestMapping(value = "conf/connector", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> jsonPub(@RequestBody JdbConnector conn) {
        HttpHeaders headers = new HttpHeaders();
        try {
            System.out.println(conn);
            myRedisson.pubConnectors(conn);
            return new ResponseEntity<String>("", headers, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<String>(e.getCause().toString(), headers, HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "conf/getter", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> jsonPub(@RequestBody DbGetter getter) {
        HttpHeaders headers = new HttpHeaders();
        try {
            System.out.println(getter);
            myRedisson.pubGetter(getter);
            return new ResponseEntity<String>("", headers, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<String>(e.getCause().toString(), headers, HttpStatus.BAD_REQUEST);
        }
    }


    // DEMO: MSSQL SERVER:...

    @RequestMapping(value = "demo/mssql/connector", method = RequestMethod.GET,  produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody JdbConnector demoConnector(HttpServletRequest request){
        JdbConnector conn = JdbConnector.createSqlServer();
        return conn;
    }


    @RequestMapping(value = "demo/mssql/getter", method = RequestMethod.GET,  produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody DbGetter demoGetter(HttpServletRequest request){
        DbGetter getter = DbGetter.create();
        return getter;
    }

    // DEMO: MYSQL:...

    @RequestMapping(value = "demo/mysql/connector", method = RequestMethod.GET,  produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody JdbConnector demoConnector2(HttpServletRequest request){
        JdbConnector conn = JdbConnector.createMySql();
        return conn;
    }


    @RequestMapping(value = "demo/mysql/getter", method = RequestMethod.GET,  produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody DbGetter demoGetter2(HttpServletRequest request){
        DbGetter getter = DbGetter.create();
        return getter;
    }

    @RequestMapping(value = "demo/hashmap", method = RequestMethod.GET,  produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody HashMap<Integer, String> demoHashMap(HttpServletRequest request){
        HashMap<Integer,String> map = new HashMap<>();
        map.put(1,"one");
        map.put(2,"two");
        map.put(3,"three");
        return map;
    }

    @RequestMapping(value = "demo/removeToken", method = RequestMethod.GET,  produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ArrayList<Integer> removeToken(HttpServletRequest request){
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        return list;
    }

    @RequestMapping(value = "demo/addLogin", method = RequestMethod.GET,  produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ProtoLogin addLogin(HttpServletRequest request){
        ProtoLogin login = new ProtoLogin();
        login.id = 111;
        login.name = "alex";
        login.expireInDays = 5;
        login.password = "123ABC";
        return login;
    }

    @RequestMapping(value = "demo/addToken", method = RequestMethod.GET,  produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody HashMap<Integer, String> addToken(HttpServletRequest request){
        HashMap<Integer,String> map = new HashMap<>();
        map.put(1,"one");
        map.put(2,"two");
        map.put(3,"three");
        return map;
    }


    @RequestMapping(value = "demo/authentication_mode", method = RequestMethod.GET,  produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ArrayList<String> authentication_mode(HttpServletRequest request){
        ArrayList<String> list = new ArrayList<>();
        list.add("JWT");
        list.add("BASIC");
        list.add("NONE");
        return list;
    }

    @RequestMapping(value = "demo/authorization_mode", method = RequestMethod.GET,  produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ArrayList<String> authorization_mode(HttpServletRequest request){
        ArrayList<String> list = new ArrayList<>();
        list.add("true");
        list.add("false");
        return list;
    }


    @RequestMapping(value = "demo/expire_in_days", method = RequestMethod.POST,  produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody String expire_in_days(@RequestBody Integer expireInDays){
        return "12";
    }


    @RequestMapping(value = "demo/addLoginRoles", method = RequestMethod.GET,  produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody HashMap<Integer, String> addLoginRoles(HttpServletRequest request){
        HashMap<Integer,String> map = new HashMap<>();
        map.put(1,"any role 1");
        map.put(2,"any role 2");
        map.put(3,"any role 3");
        return map;
    }

    @RequestMapping(value = "demo/removeLoginRoles", method = RequestMethod.GET,  produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody HashMap<Integer, String> removeLoginRoles(HttpServletRequest request){
        HashMap<Integer,String> map = new HashMap<>();
        map.put(1,"any role 1");
        map.put(2,"any role 2");
        map.put(3,"any role 3");
        return map;
    }


    @RequestMapping(value = "demo/addGetterRoles", method = RequestMethod.GET,  produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody HashMap<Integer, String> addGetterRoles(HttpServletRequest request){
        HashMap<Integer,String> map = new HashMap<>();
        map.put(1,"any role 1");
        map.put(2,"any role 2");
        map.put(3,"any role 3");
        return map;
    }

    @RequestMapping(value = "demo/removeGetterRoles", method = RequestMethod.GET,  produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody HashMap<Integer, String> removeGetterRoles(HttpServletRequest request){
        HashMap<Integer,String> map = new HashMap<>();
        map.put(1,"any role 1");
        map.put(2,"any role 2");
        map.put(3,"any role 3");
        return map;
    }



    //#################
    //Login functions:
    //#################
    @RequestMapping(value = "conf/addLogin", method = RequestMethod.POST,  produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody String addLogin(@RequestBody ProtoLogin login){
        myRedisson.pubProtoLogin(login);
        return "ok";
    }

    @RequestMapping(value = "conf/removeLogin", method = RequestMethod.POST,  produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody String addLogin(@RequestBody Integer loginId){
        myRedisson.pubRemoveLoginById(loginId);
        return "ok";
    }

    //#################
    //Token functions:
    //#################

    @RequestMapping(value = "conf/addToken", method = RequestMethod.POST,  produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody String addToken(@RequestBody String body){

        ObjectMapper mapper = new ObjectMapper();
        HashMap<Integer, String> map = new HashMap<>();

        try {
            map = mapper.readValue(body, new TypeReference<Map<Integer, String>>() {}); //tokenByLoginId
            myRedisson.pub_addToken(map);
            System.out.println(map);
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return "ok";
    }


    @RequestMapping(value = "conf/removeToken", method = RequestMethod.POST,  produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody String removeToken(@RequestBody String body){
        try {
            Integer loginId = Integer.parseInt(body);
            ArrayList<Integer>arr = new ArrayList();
            arr.add(loginId);
            myRedisson.pub_removeToken(arr);
            return "ok";
        }catch (Exception e) {

        }

        String[] ids = body.split(",");
        int[] array = Arrays.asList(body).stream().mapToInt(Integer::parseInt).toArray();
        ArrayList<Integer> arr = (ArrayList) Arrays.asList(array);
        myRedisson.pub_removeToken(arr);

        return "bad";
    }

    //#################
    //Authentication mode:
    //#################

    @RequestMapping(value = "conf/authentication_mode", method = RequestMethod.POST,  produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody String authentication_mode(@RequestBody String body){
        myRedisson.pub_setAuthenticationMode(body);
        return "ok";
    }

    //#################
    //Authorization mode:
    //#################
    @RequestMapping(value = "conf/authorization_mode", method = RequestMethod.POST,  produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody String authorization_mode(@RequestBody String body){
        boolean b = Boolean.parseBoolean(body);
        myRedisson.pub_setAuthorizationMode(b);
        return "ok";
    }

    //#################
    //Expire in days:
    //#################
    @RequestMapping(value = "conf/expire_in_days", method = RequestMethod.POST,  produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody String authorization_mode(@RequestBody Integer expireInDays){
        myRedisson.pub_setDefaultExpire(expireInDays);
        return "ok";
    }


    //#################
    //Roles functions:
    //#################
    @RequestMapping(value = "conf/addLoginRoles", method = RequestMethod.POST,  produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody String addLoginRoles(@RequestBody String body){

        ObjectMapper mapper = new ObjectMapper();
        HashMap<String, String> map = new HashMap<>();

        try {
            map = mapper.readValue(body, new TypeReference<Map<Integer, String>>() {}); //tokenByLoginId
            myRedisson.pub_addLoginRoles(map);
            System.out.println(map);
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return "ok";
    }

    @RequestMapping(value = "conf/removeLoginRoles", method = RequestMethod.POST,  produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody String removeLoginRoles(@RequestBody String body){

        ObjectMapper mapper = new ObjectMapper();
        HashMap<String, String> map = new HashMap<>();

        try {
            map = mapper.readValue(body, new TypeReference<Map<Integer, String>>() {}); //tokenByLoginId
            myRedisson.pub_removeLoginRoles(map);
            System.out.println(map);
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return "ok";
    }

    @RequestMapping(value = "conf/addGetterRoles", method = RequestMethod.POST,  produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody String addGetterRoles(@RequestBody String body){

        ObjectMapper mapper = new ObjectMapper();
        HashMap<String, String> map = new HashMap<>();

        try {
            map = mapper.readValue(body, new TypeReference<Map<Integer, String>>() {}); //tokenByLoginId
            myRedisson.pub_addGetterRoles(map);
            System.out.println(map);
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return "ok";
    }

    @RequestMapping(value = "conf/removeGetterRoles", method = RequestMethod.POST,  produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody String removeGetterRoles(@RequestBody String body){

        ObjectMapper mapper = new ObjectMapper();
        HashMap<String, String> map = new HashMap<>();

        try {
            map = mapper.readValue(body, new TypeReference<Map<Integer, String>>() {}); //tokenByLoginId
            myRedisson.pub_removeGetterRoles(map);
            System.out.println(map);
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return "ok";
    }
}
