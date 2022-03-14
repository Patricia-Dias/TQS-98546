## Create Maven Project
```
mvn archetype:generate -DgroupId=lab1_1.tqsStack 
-DartifactId=tqs-stack -DarchetypeArtifactId=maven-archetype-quickstart 
-DarchetypeVersion=1.4 -DinteractiveMode=false
```

```
<dependency>
        <groupId>org.junit.jupiter</groupId>
        <artifactId>junit-jupiter</artifactId>
        <version>5.5.2</version>
        <scope>test</scope>
    </dependency>
```
* run maven project " $mvn install $mvn exec:java -Dexec:mainClass='lab1_1.tqsStack.App' "
## Perguntar ao stor como correr testes no terminal
correr testes = "$mvn test"