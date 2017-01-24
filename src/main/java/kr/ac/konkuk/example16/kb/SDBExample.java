package kr.ac.konkuk.example16.kb;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.sdb.SDBFactory;
import org.apache.jena.sdb.Store;
import org.apache.jena.sdb.StoreDesc;
import org.apache.jena.sdb.sql.JDBC;
import org.apache.jena.sdb.sql.MySQLEngineType;
import org.apache.jena.sdb.sql.SDBConnection;
import org.apache.jena.sdb.store.DatabaseType;
import org.apache.jena.sdb.store.LayoutType;

import java.io.FileReader;

/**
 * @author JCooky
 * @since 2017-01-24
 */
public class SDBExample {

  public static void main(String[] args) throws Exception {
    StoreDesc storeDesc = new StoreDesc(LayoutType.LayoutSimple, DatabaseType.MySQL);
    storeDesc.engineType = MySQLEngineType.InnoDB;

    JDBC.loadDriverMySQL();
    String jdbcURL = "jdbc:mysql://localhost/jena?useUnicode=true&characterEncoding=utf8&useSSL=false";
    SDBConnection conn = new SDBConnection(jdbcURL, "root", "") ;
    Store store = SDBFactory.connectStore(conn, storeDesc) ;
    store.getTableFormatter().create();
//    store.getTableFormatter().truncate();

    Model model = SDBFactory.connectDefaultModel(store);
    try (FileReader reader = new FileReader("University0_0.owl")) {
      model.read(reader, "");
    }

    store.close();
  }
}
