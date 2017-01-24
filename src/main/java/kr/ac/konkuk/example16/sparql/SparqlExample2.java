package kr.ac.konkuk.example16.sparql;

import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntModelSpec;
import org.apache.jena.query.*;
import org.apache.jena.rdf.model.ModelFactory;

import java.io.FileReader;

/**
 * @author JCooky
 * @since 2017-01-24
 */
public class SparqlExample2 {
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
    OntModel model = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM_RULE_INF);

    try (FileReader reader = new FileReader("University0_0.owl")) {
      model.read(reader, "");
    }

    Query query = QueryFactory.create(QUERY, Syntax.syntaxSPARQL_10);

    try (QueryExecution qexec = QueryExecutionFactory.create(query, model)) {
      ResultSet results = qexec.execSelect();
//      while (results.hasNext()) {
//        QuerySolution qsln = results.nextSolution();
//
//      }
      ResultSetFormatter.out(results, query);
    }

  }
}


