package hello.helper;

import hello.model.getter.IGetter;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;


import java.io.IOException;

@Service("busMsgJsonDeserializer")
@SuppressWarnings("unchecked")
public class BusMsgJsonDeserializer extends JsonDeserializer<IGetter> {

    public static final String NAME = "clazz";

    @Override
    public IGetter deserialize(JsonParser jp, DeserializationContext context)throws IOException, JsonProcessingException {


        System.out.println("SSSSSSSSSSSSSSSSSSSSSSSSSS");
        ObjectMapper mapper = (ObjectMapper) jp.getCodec();
        ObjectNode root = mapper.readTree(jp);
        if (root.has(NAME)) {
            JsonNode clazzNode = root.get(NAME);
            IGetter msg = null;
            try {
                msg = (IGetter)mapper.readValue(root.toString(), getClass(clazzNode.asText(),IGetter.class));
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            return msg;
        }
        throw context.mappingException("FRONT-service: failed to de-serialize message.");
    }

    public static <T> Class<T> getClass(final String className, Class<T> ifaceClass)  throws ClassNotFoundException {
        final Class<T> clazz = (Class<T>) Class.forName(className).asSubclass(ifaceClass);
        return clazz;
    }

}