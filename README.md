# spring-boot-mybatis-docker

https://qiita.com/rubytomato@github/items/8eee9e3fa86c89dd305c  
https://spring.pleiades.io/guides/gs/handling-form-submission/  


psql -h localhost -p 5432 -U postgres -c "copy item to stdout" > item.csv  
psql -h localhost -p 5432 -U postgres -c "truncate item;"  
psql -h localhost -p 5432 -U postgres -c "copy item (id,name) from stdin" < item.csv  


mvn spring-boot:build-image  
docker login  
docker images  
docker tag batch:0.0.1-SNAPSHOT namickey/batch:latest  
docker images  
docker push namickey/batch:latest  

