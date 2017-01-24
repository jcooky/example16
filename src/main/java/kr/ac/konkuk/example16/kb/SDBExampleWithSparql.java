package kr.ac.konkuk.example16.kb;

import org.apache.jena.query.*;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.sdb.SDBFactory;
import org.apache.jena.sdb.Store;
import org.apache.jena.sdb.StoreDesc;
import org.apache.jena.sdb.sql.JDBC;
import org.apache.jena.sdb.sql.MySQLEngineType;
import org.apache.jena.sdb.sql.SDBConnection;
import org.apache.jena.sdb.store.DatabaseType;
import org.apache.jena.sdb.store.DatasetStore;
import org.apache.jena.sdb.store.LayoutType;

import java.io.FileReader;

/**
 * @author JCooky
 * @since 2017-01-24
 */
public class SDBExampleWithSparql {
//  private static String QUERY =
//      "" +
//          "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
//          "PREFIX ub: <http://swat.cse.lehigh.edu/onto/univ-bench.owl#>\n" +
//          "SELECT ?X\n" +
//          "WHERE\n" +
//          "{?X rdf:type ub:Student .\n" +
//          "  ?X ub:takesCourse <http://www.Department0.University0.edu/GraduateCourse0>}";

  private static String QUERY =
      "" +
          "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
          "PREFIX ub: <http://swat.cse.lehigh.edu/onto/univ-bench.owl#>\n" +
          "SELECT ?X ?Y ?Z\n" +
          "WHERE\n" +
          "{?X rdf:type ub:GraduateStudent .\n" +
          "  ?Y rdf:type ub:University .\n" +
          "  ?Z rdf:type ub:Department .\n" +
          "  ?X ub:memberOf ?Z .\n" +
          "  ?Z ub:subOrganizationOf ?Y .\n" +
          "  ?X ub:undergraduateDegreeFrom ?Y}";

  public static void main(String[] args) throws Exception {
    StoreDesc storeDesc = new StoreDesc(LayoutType.LayoutSimple, DatabaseType.MySQL);
    storeDesc.engineType = MySQLEngineType.InnoDB;

    JDBC.loadDriverMySQL();
    String jdbcURL = "jdbc:mysql://localhost/jena?useUnicode=true&characterEncoding=utf8&useSSL=false";
    SDBConnection conn = new SDBConnection(jdbcURL, "root", "") ;
    Store store = SDBFactory.connectStore(conn, storeDesc) ;
//    store.getTableFormatter().create();
//    store.getTableFormatter().truncate();

    Dataset ds = DatasetStore.create(store);
    Query query = QueryFactory.create(QUERY, Syntax.syntaxSPARQL_10);

    try (QueryExecution qexec = QueryExecutionFactory.create(query, ds)) {
      ResultSet results = qexec.execSelect();
//      while (results.hasNext()) {
//        QuerySolution qsln = results.nextSolution();
//
//      }
      ResultSetFormatter.out(results, query);
    }

    store.close();
  }
}
