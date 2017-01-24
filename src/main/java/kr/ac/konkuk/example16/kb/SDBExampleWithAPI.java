package kr.ac.konkuk.example16.kb;

import kr.ac.konkuk.example16.UB;
import org.apache.jena.ontology.Individual;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntModelSpec;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.sdb.SDBFactory;
import org.apache.jena.sdb.Store;
import org.apache.jena.sdb.StoreDesc;
import org.apache.jena.sdb.sql.JDBC;
import org.apache.jena.sdb.sql.MySQLEngineType;
import org.apache.jena.sdb.sql.SDBConnection;
import org.apache.jena.sdb.store.DatabaseType;
import org.apache.jena.sdb.store.LayoutType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileReader;

/**
 * @author JCooky
 * @since 2017-01-24
 */
public class SDBExampleWithAPI {
  private static Logger logger = LoggerFactory.getLogger(SDBExampleWithAPI.class);

  public static void main(String[] args) throws Exception {
    StoreDesc storeDesc = new StoreDesc(LayoutType.LayoutSimple, DatabaseType.MySQL);
    storeDesc.engineType = MySQLEngineType.InnoDB;

    JDBC.loadDriverMySQL();
    String jdbcURL = "jdbc:mysql://localhost/jena?useUnicode=true&characterEncoding=utf8&useSSL=false";
    SDBConnection conn = new SDBConnection(jdbcURL, "root", "") ;
    Store store = SDBFactory.connectStore(conn, storeDesc) ;
//    store.getTableFormatter().create();
//    store.getTableFormatter().truncate();


    OntModel model = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM, SDBFactory.connectDefaultModel(store));
    for (Individual individual : model.listIndividuals(UB.FullProfessor).toList()) {
      String uri = individual.getURI();
      String name = individual.getPropertyValue(UB.name).asLiteral().getString();

      logger.trace("{}'s name: {}", uri, name);
    }

    store.close();
  }
}
