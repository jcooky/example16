package kr.ac.konkuk.example16.reasoning;

import kr.ac.konkuk.example16.EX;
import kr.ac.konkuk.example16.UB;
import org.apache.commons.io.filefilter.RegexFileFilter;
import org.apache.jena.ontology.Individual;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntModelSpec;
import org.apache.jena.rdf.model.*;
import org.apache.jena.reasoner.Reasoner;
import org.apache.jena.reasoner.rulesys.GenericRuleReasoner;
import org.apache.jena.reasoner.rulesys.GenericRuleReasonerFactory;
import org.apache.jena.reasoner.rulesys.Rule;
import org.apache.jena.sparql.vocabulary.FOAF;
import org.apache.jena.util.FileManager;
import org.apache.jena.util.PrintUtil;
import org.apache.jena.vocabulary.RDF;
import org.apache.jena.vocabulary.ReasonerVocabulary;

import java.io.File;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.util.List;
import java.util.regex.Pattern;

/**
 * @author JCooky
 * @since 2017-01-24
 */
public class ReasoningExample {
  public static void main(String[] args) throws Exception {
// Register a namespace for use in the demo
    String demoURI = "http://jena.hpl.hp.com/demo#";
    PrintUtil.registerPrefix("demo", demoURI);

// Create an (RDF) specification of a hybrid reasoner which
// loads its data from an external file.
    Model m = ModelFactory.createDefaultModel();
    Resource configuration =  m.createResource();
    configuration.addProperty(ReasonerVocabulary.PROPruleMode, "hybrid");
    configuration.addProperty(ReasonerVocabulary.PROPruleSet,  "data/demo.rules");


// Create an instance of such a reasoner
    Reasoner reasoner = GenericRuleReasonerFactory.theInstance().create(configuration);


// Load test data
    Model data = FileManager.get().loadModel("file:data/demoData.rdf");
    InfModel infmodel = ModelFactory.createInfModel(reasoner, data);


// Query for all things related to "a" by "p"
    Property p = data.getProperty(demoURI, "p");
    Resource a = data.getResource(demoURI + "a");
    StmtIterator i = infmodel.listStatements(a, p, (RDFNode)null);
    while (i.hasNext()) {
      System.out.println(" - " + PrintUtil.print(i.nextStatement()));
    }
  }
}

