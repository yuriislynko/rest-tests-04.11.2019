**Run All tests:**  
`mvn clean verify`

**View Serenity HTML report:**  
`mvn serenity:aggregate`
http://localhost:63342/rest-tests-04.11.2019/target/site/serenity/index.html

mvn clean verify -Dtest.name=OrderTest