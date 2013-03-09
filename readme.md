# lucene-facet-3.5.0 example.simple

    [pdurbin@tabby simple]$ JAVA_HOME=/usr/java/latest $HOME/netbeans-7.3/java/maven/bin/mvn package >/dev/null
    [pdurbin@tabby simple]$ 
    [pdurbin@tabby simple]$ java -cp $HOME/NetBeansProjects/simple/target/simple-1.0-SNAPSHOT.jar:$HOME/.m2/repository/org/apache/lucene/lucene-core/3.5.0/lucene-core-3.5.0.jar:$HOME/.m2/repository/org/apache/lucene/lucene-facet/3.5.0/lucene-facet-3.5.0.jar org.apache.lucene.facet.example.simple.SimpleMain
    index the sample documents...
    Indexed 2 documents with overall 4 facets.
    search the sample documents...
    Query: text:white
    Res 0: Request: root/a nRes=10 nLbl=10
    Num valid Descendants (up to specified depth): 3
            Facet Result Node with 3 sub result nodes.
            Name: root/a
            Value: 2.0
            Residue: 0.0

            Subresult #0
                    Facet Result Node with 0 sub result nodes.
                    Name: root/a/f1
                    Value: 2.0
                    Residue: 0.0

            Subresult #1
                    Facet Result Node with 0 sub result nodes.
                    Name: root/a/f3
                    Value: 1.0
                    Residue: 0.0

            Subresult #2
                    Facet Result Node with 0 sub result nodes.
                    Name: root/a/f2
                    Value: 1.0
                    Residue: 0.0

    index the sample documents...
    Indexed 2 documents with overall 4 facets.
    search the sample documents...
    Query: text:white
    Res 0: Request: root/a nRes=10 nLbl=10
    Num valid Descendants (up to specified depth): 3
            Facet Result Node with 3 sub result nodes.
            Name: root/a
            Value: 2.0
            Residue: 0.0

            Subresult #0
                    Facet Result Node with 0 sub result nodes.
                    Name: root/a/f1
                    Value: 2.0
                    Residue: 0.0

            Subresult #1
                    Facet Result Node with 0 sub result nodes.
                    Name: root/a/f3
                    Value: 1.0
                    Residue: 0.0

            Subresult #2
                    Facet Result Node with 0 sub result nodes.
                    Name: root/a/f2
                    Value: 1.0
                    Residue: 0.0

    Query: +text:white +$facets:rootaf3
    Res 0: Request: root/a nRes=10 nLbl=10
    Num valid Descendants (up to specified depth): 2
            Facet Result Node with 2 sub result nodes.
            Name: root/a
            Value: 1.0
            Residue: 0.0

            Subresult #0
                    Facet Result Node with 0 sub result nodes.
                    Name: root/a/f3
                    Value: 1.0
                    Residue: 0.0

            Subresult #1
                    Facet Result Node with 0 sub result nodes.
                    Name: root/a/f1
                    Value: 1.0
                    Residue: 0.0

    DONE
    [pdurbin@tabby simple]$ 
