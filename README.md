Application is responsible for keeping the tracked hashtags on the Redis cache.

docker run -d --rm --name redis --net twitter -p 6379:6379 redis:4.0.6-alpine

docker run -d --rm --name hashtag-tracker --net twitter -p 9090:9090 olgafedorova/tracked_hashtag

docker run -d --rm --name rabbitmq --net twitter -p 5672:5672 -p 15672:15672 rabbitmq:3.7.0-management-alpine

curl -H "Content-Type: application/json" -X POST -d'{"hashTag":"bitcoin","queue":"bitcoin"}' http://localhost:9090/api/tracked-hash-tag