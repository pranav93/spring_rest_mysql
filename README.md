# spring rest (works with in memory DB Or mysql)

## How to run this project?
You need gradle installed on the system. After cloning the repository, run './gradlew bootRun', and you are good to go, as it uses in memory db. If you want to run it with mysql, change the `application.properties` in src/main/resources.

## Available apis?
1) To add new shops
```
curl -X POST \
  http://localhost:8080/shops/add \
  -H 'content-type: application/json' \
  -d '{
	"name": "shopName",
	"addressName": "shopAddressName"
}
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
./gradlew test --rerun-tasks
```
