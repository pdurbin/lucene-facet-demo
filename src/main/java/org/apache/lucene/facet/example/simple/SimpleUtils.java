package org.apache.lucene.facet.example.simple;

import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.WhitespaceAnalyzer;

import org.apache.lucene.facet.example.ExampleUtils;
import org.apache.lucene.facet.taxonomy.CategoryPath;

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
 * Some definitions for the Simple Sample.
 *
 * @lucene.experimental
 */
public class SimpleUtils {

    /**
     * Documents text field.
     */
    public static final String TEXT = "text";
    /**
     * Documents title field.
     */
    public static final String TITLE = "title";
    /**
     * sample documents text (for the text field).
     */
    public static String[] docTexts = {
        "Is social science is good enough to eat?",
        "Is football a social science?",
        "Pray for better results in social science.",
        "Ice cream social or donuts?",};
    /**
     * sample documents titles (for the title field).
     */
    public static String[] docTitles = {
        "Social Science: Better than Butter",
        "Social Dynamics in Football",
        "God and Social Science",
        "Donuts and Social Science",};
    /**
     * Categories: categories[D][N] == category-path no. N for document no. D.
     */
    public static CategoryPath[][] categories = {
        {
            new CategoryPath("author", "Simpson, Homer"),
            new CategoryPath("productionDate", "2013-03-09"),
            /**
             * todo support more than one keyword
             */
            new CategoryPath("keyword", "butter"),
            new CategoryPath("topicClassification", "Cooking"),
            new CategoryPath("dvName", "Homer Simpson Dataverse"),},
        {
            new CategoryPath("author", "Simpson, Homer"),
            new CategoryPath("productionDate", "2013-03-09"),
            new CategoryPath("keyword", "football"),
            new CategoryPath("topicClassification", "TV"),
            new CategoryPath("dvName", "Homer Simpson Dataverse"),},
        {
            new CategoryPath("author", "Flanders, Ned"),
            new CategoryPath("productionDate", "2013-03-10"),
            new CategoryPath("keyword", "scripture"),
            new CategoryPath("topicClassification", "Religion"),
            new CategoryPath("dvName", "Ned Flanders Dataverse"),},
        {
            new CategoryPath("author", "Wiggum, Clancy"),
            new CategoryPath("productionDate", "2013-03-09"),
            new CategoryPath("keyword", "pastries"),
            new CategoryPath("topicClassification", "Cooking"),
            new CategoryPath("dvName", "Clancy Wiggum Dataverse"),},};
    /**
     * Analyzer used in the simple sample.
     */
    public static final Analyzer analyzer = new WhitespaceAnalyzer(ExampleUtils.EXAMPLE_VER);

    /**
     * Utility method: List of category paths out of an array of them...
     *
     * @param categoryPaths input array of category paths.
     */
    public static List<CategoryPath> categoryPathArrayToList(CategoryPath... categoryPaths) {
        ArrayList<CategoryPath> res = new ArrayList<CategoryPath>();
        for (CategoryPath categoryPath : categoryPaths) {
            res.add(categoryPath);
        }
        return res;
    }
}
