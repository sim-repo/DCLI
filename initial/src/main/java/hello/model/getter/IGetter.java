package hello.model.getter;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import hello.enums.FormatEnum;

import java.io.Serializable;
import java.util.HashSet;


@JsonPropertyOrder({"clazz"})
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public interface IGetter extends Serializable {
    Integer getId();
    @JsonGetter("clazz")
    String getClazz();
    String getFuncParamByWebParam(String webParam);
    String getExecutedFunctionName();
    FormatEnum getResultType();
    Boolean getAllAccess();
    HashSet<String> getRoles();
}
