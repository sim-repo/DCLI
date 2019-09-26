package hello.model.getter;

import hello.enums.FormatEnum;
import hello.helper.StatementParser;

import java.util.HashSet;
import java.util.List;
import java.util.Map;

public abstract class AGetter implements IGetter{

    public Integer id;
    public String clazz;
    public String method = "";
    public String endpointId = "";
    public Map<String, List<String>> webParamsByMethod;
    public String executedFunctionName = "";
    public Map<String, String> funcParamByWebParam;
    public String hibernateParamsMap = "";
    public String resultType = "";
    public String description = "";
    public Boolean isAllAccess = false;
    public HashSet<String> roles;


    @Override
    public String getExecutedFunctionName() {
        return executedFunctionName;
    }

    @Override
    public FormatEnum getResultType() {
        return FormatEnum.fromValue(resultType);
    }

    public void setResultType(FormatEnum resultType) {
        this.resultType = resultType.toValue();
    }

    public Map<String, String> getFuncParamByWebParam() {
        return funcParamByWebParam;
    }

    @Override
    public String getFuncParamByWebParam(String webParam) {
        return funcParamByWebParam.get(webParam);
    }

    public void setHibernateParamsMap(String hibernateParamsMap) {
        this.hibernateParamsMap = hibernateParamsMap;
        this.webParamsByMethod = StatementParser.parseWebParamsByMethod(method, hibernateParamsMap);
        this.funcParamByWebParam = StatementParser.parseFunctionParamByWebParam(hibernateParamsMap);
    }

    public void setRole(String role) {
        this.roles.add(role);
    }

    @Override
    public Boolean getAllAccess() {
        return isAllAccess;
    }

    @Override
    public HashSet<String> getRoles() {
        return roles;
    }

    @Override
    public String toString() {
        return "AGetter{" +
                "method='" + method + '\'' +
                ", endpointId='" + endpointId + '\'' +
                ", webParamsByMethod=" + webParamsByMethod +
                ", executedFunctionName='" + executedFunctionName + '\'' +
                ", funcParamByWebParam=" + funcParamByWebParam +
                ", hibernateParamsMap='" + hibernateParamsMap + '\'' +
                ", resultType='" + resultType + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
