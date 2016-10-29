package gr.iserm.java.hsqldb;

import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.io.IOException;
import java.util.Properties;

public class HsqlDbServerWrapper {

    private static HsqlDbServer server;

    public static void init() throws IOException {
        Properties properties = PropertiesLoaderUtils.loadAllProperties("hsqldb.properties");
        server = new HsqlDbServer(properties);
        server.start();
    }

    public static void stop() {
        if(server!=null){
            server.stop();
        }
    }
}
