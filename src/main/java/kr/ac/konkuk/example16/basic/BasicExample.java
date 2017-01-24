package kr.ac.konkuk.example16.basic;

import edu.lehigh.swat.bench.uba.Generator;
import kr.ac.konkuk.example16.UB;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.filefilter.RegexFileFilter;
import org.apache.jena.ontology.Individual;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntModelSpec;
import org.apache.jena.rdf.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.regex.Pattern;

/**
 * @author JCooky
 * @since 2017-01-23
 */
public class BasicExample {
  private static Logger logger = LoggerFactory.getLogger(BasicExample.class);

  public static void main(String[] args) throws IOException {
    new Generator().start(1, 0, (int)(Math.random() * 200000), true,
        "http://swat.cse.lehigh.edu/onto/univ-bench.owl");

    OntModel model = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM);

    File file = new File("University0_0.owl");
    try (FileReader reader = new FileReader(file)) {
      model.read(reader, "");
    }

    for (Individual individual : model.listIndividuals(UB.FullProfessor).toList()) {
      String uri = individual.getURI();
      String name = individual.getPropertyValue(UB.name).asLiteral().getString();

      logger.trace("{}'s name: {}", uri, name);
    }
  }

}



