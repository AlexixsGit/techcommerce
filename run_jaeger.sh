#!/bin/bash
docker run --restart=unless-stopped \
        --hostname jaeger.cedesistemas.local \
        --net cedesistemas_network \
        --name jaeger.cedesistemas.local \
        --memory=256m \
        -e "TZ=America/Bogota" \
        -e "SPAN_STORAGE_TYPE=elasticsearch" \
        -e "ES_SERVER_URLS=http://elasticsearch.cedesistemas.local:9200" \
        -e "COLLECTOR_ZIPKIN_HTTP_PORT=9411" \
        -p 5775:5775/udp \
        -p 6831:6831/udp \
        -p 6832:6832/udp \
        -p 5778:5778 \
        -p 16686:16686 \
        -p 14268:14268 \
        -p 9411:9411 \
        -d jaegertracing/all-in-one:latest
