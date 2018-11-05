Application is responsible for keeping the tracked hashtags on the Redis cache.

**Commands:**

docker run -d --rm --name redis --net twitter -p 6379:6379 redis:4.0.6-alpine

docker run -d --rm --name rabbitmq --net twitter -p 5672:5672 -p 15672:15672 rabbitmq:3.7.0-management-alpine

docker run -d --rm --name hashtag-tracker --net twitter -p 9090:9090 olgafedorova/tracked_hashtag

docker run -d --rm --name api-stub --net twitter -p 8081:8081 olgafedorova/tweet_api_stub

docker run -d --rm --name gathering --net twitter -p 8080:8080 olgafedorova/tweet_gathering

docker run -d --rm --name dispatcher --net twitter -p 9099:9099 olgafedorova/tracked_dispatcher

curl -H "Content-Type: application/json" -X POST -d'{"hashTag":"bitcoin","queue":"bitcoin"}' http://localhost:9090/api/tracked-hash-tag

mvn clean install docker:build

**Description project:**

1. The first operation, tracked-hashtag-service, will persist the hashtag in the Redis database.

2. After that, tracked-hashtag-service will send the newly tracked hashtag to a queue in the RabbitMQ Broker.

3. tweet-gathering is listening to the queue to track Tweets and trigger the event and starts by listening to the tweet-api-stub stream.

4. tweet-gathering starts to get Tweets from the tweet-api-stub stream.

5. tweet-gathering publishes Tweets to a queue in the RabbitMQ broker.

6. tweet-dispatcher consumes the message.

7. tweet-dispatcher sends the message to the Client using SSE.
