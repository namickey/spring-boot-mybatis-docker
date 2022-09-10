# web-css

https://qiita.com/rubytomato@github/items/8eee9e3fa86c89dd305c  
https://spring.pleiades.io/guides/gs/handling-form-submission/  


mvnw.cmd spring-boot:build-image  
or
mvn spring-boot:build-image  

docker images  
docker tag web-css:0.0.1-SNAPSHOT namickey/spring-boot-web-css:latest  
docker images  

docker login  
docker push namickey/spring-boot-web-css:latest  

docker run -it -p 8080:8080 namickey/spring-boot-web-css:latest

http://localhost:8080

