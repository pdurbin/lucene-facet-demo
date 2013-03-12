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
        ExampleUtils.log("Running runSimple()...");
        List<FacetResult> exampleResultSimpleList = new SimpleMain().runSimple().getFacetResults();
        ExampleUtils.log("Interating through facet results...");
        for (Iterator<FacetResult> it = exampleResultSimpleList.iterator(); it.hasNext();) {
            FacetResult facetResult = it.next();
            //            ExampleUtils.log("Dumping facet result from runSimple..." + facetResult.toString());
            ExampleUtils.log("CategoryPath label: " + facetResult.getFacetResultNode().getLabel());
            for (FacetResultNode n : facetResult.getFacetResultNode().getSubResults()) {
                CategoryPath label = n.getLabel();
                String last = n.getLabel().lastComponent().toString();
                double value = n.getValue();
                ExampleUtils.log("label = " + label);
                ExampleUtils.log("last = " + last);
                ExampleUtils.log("value = " + value);
                ExampleUtils.log("---");
            }
        }

        ExampleUtils.log("================================");

        CategoryPath categoryPathOfInterest = new CategoryPath("topicClassification", "Cooking");
//        CategoryPath categoryPathOfInterest = new CategoryPath("author", "Flanders, Ned");
        ExampleUtils.log("Running runDrillDown() with category path " + categoryPathOfInterest.toString());
        List<FacetResult> exampleResultDrillDownList = new SimpleMain().runDrillDown(categoryPathOfInterest).getFacetResults();
        int numResults = exampleResultDrillDownList.size();
        ExampleUtils.log("Iterating though " + numResults + " facet results for facet query \"" + categoryPathOfInterest.toString() + "\"");
        for (int i = 0; i < exampleResultDrillDownList.size(); i++) {
            FacetResult facetResult = exampleResultDrillDownList.get(i);
            ExampleUtils.log("Facet result " + i + " label: " + facetResult.getFacetResultNode().getLabel());
            for (FacetResultNode n : facetResult.getFacetResultNode().getSubResults()) {
                CategoryPath label = n.getLabel();
                String last = n.getLabel().lastComponent().toString();
                double hits = n.getValue();
                ExampleUtils.log("  Hits for " + label + ": " + hits);
            }
        }
        ExampleUtils.log("DONE");
    }

    public ExampleResult runSimple() throws Exception {
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
        List<FacetResult> facetRes = SimpleSearcher.searchWithFacets(indexReader, taxo);

        // close readers
        taxo.close();
        indexReader.close();

        ExampleResult res = new ExampleResult();
        res.setFacetResults(facetRes);
        return res;
    }

    public ExampleResult runDrillDown(CategoryPath categoryPathOfInterest) throws Exception {
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
        List<FacetResult> facetRes = SimpleSearcher.searchWithDrillDown(indexReader, taxo, categoryPathOfInterest);

        // close readers
        taxo.close();
        indexReader.close();

        ExampleResult res = new ExampleResult();
        res.setFacetResults(facetRes);
        return res;
    }
}
