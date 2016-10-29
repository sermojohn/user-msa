package gr.iserm.java.hsqldb;

import org.hsqldb.util.DatabaseManagerSwing;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class MemDbViewer {

    @PostConstruct
    public void init() {
        DatabaseManagerSwing.main(new String[] { "--url", "jdbc:hsqldb:mem:testdb", "--user", "sa", "--password", "" });

        //hsqldb
//        DatabaseManagerSwing.main(new String[] { "--url", "jdbc:hsqldb:mem:testdb", "--user", "sa", "--password", "" });

        //derby
        //DatabaseManagerSwing.main(new String[] { "--url", "jdbc:derby:memory:testdb", "--user", "", "--password", "" });

        //h2
        //DatabaseManagerSwing.main(new String[] { "--url", "jdbc:h2:mem:testdb", "--user", "sa", "--password", "" });
    }

}
