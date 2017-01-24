package kr.ac.konkuk.example16.sparql;

import edu.lehigh.swat.bench.uba.Generator;
import org.apache.commons.io.filefilter.RegexFileFilter;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntModelSpec;
import org.apache.jena.query.*;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.sparql.lang.ParserSPARQL10;
import org.apache.jena.sparql.lang.sparql_10.SPARQLParser10;

import java.io.*;
import java.util.regex.Pattern;

/**
 * @author JCooky
 * @since 2017-01-24
 */
public class SparqlExample {
  private static String QUERY =
      "" +
          "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
          "PREFIX ub: <http://swat.cse.lehigh.edu/onto/univ-bench.owl#>\n" +
          "SELECT ?X\n" +
          "WHERE\n" +
          "{?X rdf:type ub:Student .\n" +
          "  ?X ub:takesCourse <http://www.Department0.University0.edu/GraduateCourse0>}";

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
