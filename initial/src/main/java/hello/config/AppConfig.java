package hello.config;

import hello.model.getter.DbGetter;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;


@Service("appConfig")
@Scope("singleton")
public class AppConfig {

    public JdbcTemplate getJdbcTemplate(String endpointId) {
        // TODO: redis
        return null;
    }

    public DbGetter getDbGetter(String method){
        // TODO: redis
        // return this.dbUniGetHashMap.get(method);
        return null;
    }

    
}
