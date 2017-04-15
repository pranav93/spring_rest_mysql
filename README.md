# spring_rest_mysql (Or in memory DB)

## How to run this project?
You need gradle installed on the system. After cloning the repository, run './gradlew bootRun', and you are good to go.

## Available apis?
1) To add new shops
```
curl -X GET \
  'http://localhost:8080/shops/add?name=shopName&addressName=shopAddressName'
```
2) To get all shops
```
curl -X GET \
  http://localhost:8080/shops/all
```
3) The closest shop
```
curl -X GET \
  'http://localhost:8080/shops/closest?selectedLat=40&selectedLong=-105'
```
## Documentation?
go to the javaDoc repo and run
```
python -m SimpleHTTPServer
```
Open `localhost:8000` in the browser

## What about unit testing?
```
./gradlew test
```
Being a noob in Spring boot :(, Still struggling with setting up the project tests, getting `unable to find out the reason behin org.springframework.beans.factory.NoSuchBeanDefinitionException`, tests will be updated as soon as I get the solution.

P.S. The Google maps api token is added in repo for convenience. In case the api token does not work (most probably I might have disabled it), generate the api token and add it in application properties.
