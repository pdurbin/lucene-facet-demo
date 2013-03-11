# lucene-facet-3.5.0 example.simple

    JAVA_HOME=/usr/java/latest $HOME/netbeans-7.3/java/maven/bin/mvn package >/dev/null

    java -cp $HOME/NetBeansProjects/simple/target/simple-1.0-SNAPSHOT.jar:$HOME/.m2/repository/org/apache/lucene/lucene-core/3.5.0/lucene-core-3.5.0.jar:$HOME/.m2/repository/org/apache/lucene/lucene-facet/3.5.0/lucene-facet-3.5.0.jar org.apache.lucene.facet.example.simple.SimpleMain
