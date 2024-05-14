#!/bin/bash

# Generate random string
tag=$(uuidgen | tr -d '-' | cut -c 1-6)

echo "Random six-character string: $tag"

./mvnw versions:set -DnewVersion="$tag"
./mvnw jib:dockerBuild -Djib.to.tags=$tag,latest

taggedImage=aykanferhat/presentation-advert-api:$tag
latestImage=aykanferhat/presentation-advert-api:latest

docker tag presentation-advert-api:$tag $taggedImage
docker tag presentation-advert-api:$tag $latestImage
docker push $taggedImage
docker push $latestImage

kubectl set image deployment/advert-api advert-api=$taggedImage

kubectl describe deployments.apps advert-api

## https://hub.docker.com/repository/docker/aykanferhat/presentation-advert-api