package hello.model.getter;


import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import hello.enums.FormatEnum;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;


@JsonAutoDetect
@JsonDeserialize(as = DbGetter.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class DbGetter extends AGetter {

    @Override
    public String getClazz() {
        return this.getClass().getName();
    }



    public static DbGetter create() {
        DbGetter getter = new DbGetter();
        getter.description = "get_test";
        getter.endpointId = "jdb";
        getter.method = "getTest";
        getter.executedFunctionName = "EXEC [dbo].[get_test]";
        ArrayList<String> webp = new ArrayList<String>();
        webp.add("wparam1");
        webp.add("wparam2");
        HashMap<String,List<String>> map = new HashMap<>();
        map.put("getTest", webp);
        getter.webParamsByMethod = map;


        HashMap<String,String> map2 = new HashMap<>();
        map2.put("wparam1", "@param1");
        map2.put("wparam2", "@param2");
        getter.funcParamByWebParam = map2;

        getter.setResultType(FormatEnum.flatJSON);

        return getter;
    }
}