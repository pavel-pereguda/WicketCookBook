#!/bin/bash
mvn archetype:create -DarchetypeGroupId=org.apache.wicket -DarchetypeArtifactId=wicket-archetype-quickstart -DarchetypeVersion=1.4.9 -DgroupId=cookbook -DartifactId=recipe$1
cd recipe$1
mvn eclipse:eclipse -DdownloadSources=true
