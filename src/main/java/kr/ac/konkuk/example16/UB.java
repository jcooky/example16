package kr.ac.konkuk.example16;

import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.ResourceFactory;

public interface UB {
  String URL = "http://swat.cse.lehigh.edu/onto/univ-bench.owl";

  Resource FullProfessor = ResourceFactory.createResource(URL + "#FullProfessor");

  Property name = ResourceFactory.createProperty(URL + "#name");
}