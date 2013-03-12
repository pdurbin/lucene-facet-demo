package org.apache.lucene.facet.example.simple;

import java.util.Iterator;
import java.util.List;

import org.apache.lucene.index.IndexReader;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.RAMDirectory;

import org.apache.lucene.facet.example.ExampleResult;
import org.apache.lucene.facet.example.ExampleUtils;
import org.apache.lucene.facet.search.results.FacetResult;
import org.apache.lucene.facet.search.results.FacetResultNode;
import org.apache.lucene.facet.taxonomy.CategoryPath;
import org.apache.lucene.facet.taxonomy.TaxonomyReader;
import org.apache.lucene.facet.taxonomy.directory.DirectoryTaxonomyReader;

/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements. See the NOTICE file distributed with this
 * work for additional information regarding copyright ownership. The ASF
 * licenses this file to You under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
/**
 * Driver for the simple sample.
 *
 * @lucene.experimental
 */
public class SimpleMain {

    /**
     * Driver for the simple sample.
     *
     * @throws Exception on error (no detailed exception handling here for
     * sample simplicity
     */
    public static void main(String[] args) throws Exception {

        String searchString = "social";
//        String searchString = "softball";

        ExampleUtils.log("Running runSimple()...");
        List<FacetResult> exampleResultSimpleList = new SimpleMain().runSimple(searchString).getFacetResults();
        int numSimpleResults = exampleResultSimpleList.size();
        ExampleUtils.log("Iterating through " + numSimpleResults + " facets/categories...");
        for (int i = 0; i < exampleResultSimpleList.size(); i++) {
            FacetResult facetResult = exampleResultSimpleList.get(i);
            ExampleUtils.log("- category " + i + ": " + facetResult.getFacetResultNode().getLabel());
            for (FacetResultNode n : facetResult.getFacetResultNode().getSubResults()) {
                CategoryPath label = n.getLabel();
                String last = n.getLabel().lastComponent().toString();
                double hits = n.getValue();
                ExampleUtils.log("  - expect " + hits + " hits from a faceted search for \"" + label + "\"");
            }
        }

        ExampleUtils.log("================================");

        CategoryPath categoryPathOfInterest = new CategoryPath("topicClassification", "Cooking");
//        CategoryPath categoryPathOfInterest = new CategoryPath("productionDate", "2013-03-09");
//        CategoryPath categoryPathOfInterest = new CategoryPath("author", "Simpson, Homer");
//        CategoryPath categoryPathOfInterest = new CategoryPath("author", "Flanders, Ned");
        ExampleUtils.log("Running runDrillDown() with faceted search for \"" + categoryPathOfInterest.toString() + "\"");
        List<FacetResult> exampleResultDrillDownList = new SimpleMain().runDrillDown(searchString, categoryPathOfInterest).getFacetResults();
        int numDrillDownResults = exampleResultDrillDownList.size();
        ExampleUtils.log("Iterating though " + numDrillDownResults + " facets/categories for facet query \"" + categoryPathOfInterest.toString() + "\"");
        for (int i = 0; i < exampleResultDrillDownList.size(); i++) {
            FacetResult facetResult = exampleResultDrillDownList.get(i);
            ExampleUtils.log("- category " + i + ": " + facetResult.getFacetResultNode().getLabel());
            for (FacetResultNode n : facetResult.getFacetResultNode().getSubResults()) {
                CategoryPath label = n.getLabel();
                String last = n.getLabel().lastComponent().toString();
                double hits = n.getValue();
                ExampleUtils.log("  - hits for " + label + ": " + hits);
            }
        }
        ExampleUtils.log("DONE");
    }

    public ExampleResult runSimple(String searchString) throws Exception {
        // create Directories for the search index and for the taxonomy index
        Directory indexDir = new RAMDirectory();
        Directory taxoDir = new RAMDirectory();

        // index the sample documents
        ExampleUtils.log("index the sample documents...");
        SimpleIndexer.index(indexDir, taxoDir);

        // open readers
        TaxonomyReader taxo = new DirectoryTaxonomyReader(taxoDir);
        IndexReader indexReader = IndexReader.open(indexDir, true);

        ExampleUtils.log("search the sample documents...");
        List<FacetResult> facetRes = SimpleSearcher.searchWithFacets(searchString, indexReader, taxo);

        // close readers
        taxo.close();
        indexReader.close();

        ExampleResult res = new ExampleResult();
        res.setFacetResults(facetRes);
        return res;
    }

    public ExampleResult runDrillDown(String searchString, CategoryPath categoryPathOfInterest) throws Exception {
        // create Directories for the search index and for the taxonomy index
        Directory indexDir = new RAMDirectory();
        Directory taxoDir = new RAMDirectory();

        // index the sample documents
        ExampleUtils.log("index the sample documents...");
        SimpleIndexer.index(indexDir, taxoDir);

        // open readers
        TaxonomyReader taxo = new DirectoryTaxonomyReader(taxoDir);
        IndexReader indexReader = IndexReader.open(indexDir, true);

        ExampleUtils.log("search the sample documents...");
        List<FacetResult> facetRes = SimpleSearcher.searchWithDrillDown(searchString, indexReader, taxo, categoryPathOfInterest);

        // close readers
        taxo.close();
        indexReader.close();

        ExampleResult res = new ExampleResult();
        res.setFacetResults(facetRes);
        return res;
    }
}
