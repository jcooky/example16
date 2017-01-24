package kr.ac.konkuk.example16.basic;

import kr.ac.konkuk.example16.UB;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntModelSpec;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.ResourceFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author JCooky
 * @since 2017-01-23
 */
public class BasicExample2 {
  private static Logger logger = LoggerFactory.getLogger(BasicExample2.class);

  public static void main(String[] args) throws IOException {
//    new Generator().start(1, 0, (int)(Math.random() * 200000), true, "http://swat.cse.lehigh.edu/onto/univ-bench.owl");

    OntModel model = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM);
    try (FileReader reader = new FileReader("University0_0.owl")) {
      model.read(reader, "");
    }

    try (FileReader reader = new FileReader("University0_1.owl")) {
      model.read(reader, "");
    }

    try (FileOutputStream fos = new FileOutputStream("University0.owl")) {
      model.write(fos, "RDF/XML");
    }
  }


}
