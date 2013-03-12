# lucene-facet-3.5.0 example.simple

    JAVA_HOME=/usr/java/latest $HOME/netbeans-7.3/java/maven/bin/mvn package >/dev/null

    java -cp $HOME/NetBeansProjects/simple/target/simple-1.0-SNAPSHOT.jar:$HOME/.m2/repository/org/apache/lucene/lucene-core/3.5.0/lucene-core-3.5.0.jar:$HOME/.m2/repository/org/apache/lucene/lucene-facet/3.5.0/lucene-facet-3.5.0.jar org.apache.lucene.facet.example.simple.SimpleMain

## Sample output

    murphy:lucene-facet-demo pdurbin$ ./go.sh 
    Running runSimple()...
    index the sample documents...
    Indexed 5 documents with overall 25 facets.
    search the sample documents...
    Query: text:social
    Found 4 documents with query "text:social"
    - title 0: Social Science: Better than Butter
    - title 1: Social Dynamics in Football
    - title 2: God and Social Science
    - title 3: Donuts and Social Science
    Iterating through 5 facets/categories...
    - category 0: author
      - expect 2.0 hits from a faceted search for "author/Simpson, Homer"
      - expect 1.0 hits from a faceted search for "author/Wiggum, Clancy"
      - expect 1.0 hits from a faceted search for "author/Flanders, Ned"
    - category 1: productionDate
      - expect 2.0 hits from a faceted search for "productionDate/2013-03-10"
      - expect 2.0 hits from a faceted search for "productionDate/2013-03-09"
    - category 2: keyword
      - expect 1.0 hits from a faceted search for "keyword/pastries"
      - expect 1.0 hits from a faceted search for "keyword/scripture"
      - expect 1.0 hits from a faceted search for "keyword/sports"
      - expect 1.0 hits from a faceted search for "keyword/butter"
    - category 3: topicClassification
      - expect 2.0 hits from a faceted search for "topicClassification/Cooking"
      - expect 1.0 hits from a faceted search for "topicClassification/Religion"
      - expect 1.0 hits from a faceted search for "topicClassification/TV"
    - category 4: dvName
      - expect 2.0 hits from a faceted search for "dvName/Homer Simpson Dataverse"
      - expect 1.0 hits from a faceted search for "dvName/Clancy Wiggum Dataverse"
      - expect 1.0 hits from a faceted search for "dvName/Ned Flanders Dataverse"
    ================================
    Running runDrillDown() with faceted search for "topicClassification/Cooking"
    index the sample documents...
    Indexed 5 documents with overall 25 facets.
    search the sample documents...
    Query: text:social
    Found 4 documents with query "text:social"
    - title 0: Social Science: Better than Butter
    - title 1: Social Dynamics in Football
    - title 2: God and Social Science
    - title 3: Donuts and Social Science
    categoryOfInterest = topicClassification/Cooking
    Query: +text:social +$facets:topicClassification?Cooking
    Found 2 documents with query "+text:social +$facets:topicClassification?Cooking"
    - title 0: Social Science: Better than Butter
    - title 1: Donuts and Social Science
    Iterating though 5 facets/categories for facet query "topicClassification/Cooking"
    - category 0: author
      - hits for author/Wiggum, Clancy: 1.0
      - hits for author/Simpson, Homer: 1.0
    - category 1: productionDate
      - hits for productionDate/2013-03-10: 1.0
      - hits for productionDate/2013-03-09: 1.0
    - category 2: keyword
      - hits for keyword/pastries: 1.0
      - hits for keyword/butter: 1.0
    - category 3: topicClassification
      - hits for topicClassification/Cooking: 2.0
    - category 4: dvName
      - hits for dvName/Clancy Wiggum Dataverse: 1.0
      - hits for dvName/Homer Simpson Dataverse: 1.0
    DONE
    murphy:lucene-facet-demo pdurbin$ 
