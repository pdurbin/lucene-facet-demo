# lucene-facet-3.5.0 example.simple

    JAVA_HOME=/usr/java/latest $HOME/netbeans-7.3/java/maven/bin/mvn package >/dev/null

    java -cp $HOME/NetBeansProjects/simple/target/simple-1.0-SNAPSHOT.jar:$HOME/.m2/repository/org/apache/lucene/lucene-core/3.5.0/lucene-core-3.5.0.jar:$HOME/.m2/repository/org/apache/lucene/lucene-facet/3.5.0/lucene-facet-3.5.0.jar org.apache.lucene.facet.example.simple.SimpleMain

## Sample output

    murphy:lucene-facet-demo pdurbin$ ./go.sh 
    Running runSimple()...
    index the sample documents...
    Indexed 3 documents with overall 15 facets.
    search the sample documents...
    Query: text:social
    Interating through facet results...
    CategoryPath label: author
    label = author/Wiggum, Clancy
    last = Wiggum, Clancy
    value = 1.0
    ---
    label = author/Flanders, Ned
    last = Flanders, Ned
    value = 1.0
    ---
    label = author/Simpson, Homer
    last = Simpson, Homer
    value = 1.0
    ---
    CategoryPath label: productionDate
    label = productionDate/2012-03-09
    last = 2012-03-09
    value = 1.0
    ---
    label = productionDate/2013-03-10
    last = 2013-03-10
    value = 1.0
    ---
    label = productionDate/2013-03-11
    last = 2013-03-11
    value = 1.0
    ---
    CategoryPath label: keyword
    label = keyword/pastries
    last = pastries
    value = 1.0
    ---
    label = keyword/scripture
    last = scripture
    value = 1.0
    ---
    label = keyword/butter
    last = butter
    value = 1.0
    ---
    CategoryPath label: topicClassification
    label = topicClassification/Cooking
    last = Cooking
    value = 2.0
    ---
    label = topicClassification/Religion
    last = Religion
    value = 1.0
    ---
    CategoryPath label: dvName
    label = dvName/Clancy Wiggum
    last = Clancy Wiggum
    value = 1.0
    ---
    label = dvName/Ned Flanders
    last = Ned Flanders
    value = 1.0
    ---
    label = dvName/Homer Simpson
    last = Homer Simpson
    value = 1.0
    ---
    ================================
    Running runDrillDown()...
    index the sample documents...
    Indexed 3 documents with overall 15 facets.
    search the sample documents...
    Query: text:social
    Query: +text:social +$facets:author?Flanders, Ned
    Iterating though runDrillDown facet results...
    Facet result...
    Request: author nRes=10 nLbl=10
    Num valid Descendants (up to specified depth): 1
            Facet Result Node with 1 sub result nodes.
            Name: author
            Value: 1.0
            Residue: 0.0

            Subresult #0
                    Facet Result Node with 0 sub result nodes.
                    Name: author/Flanders, Ned
                    Value: 1.0
                    Residue: 0.0

    CategoryPath label: author
    label = author/Flanders, Ned
    last = Flanders, Ned
    value = 1.0
    ---
    DONE
    murphy:lucene-facet-demo pdurbin$ 
