Application is responsible for keeping the tracked hashtags on the Redis cache.

docker run -d --rm --name redis --net twitter -p 6379:6379 redis:4.0.6-alpine

docker run -d --rm --name hashtag-tracker --net twitter -p 9090:9090 olgafedorova/tracked_hashtag