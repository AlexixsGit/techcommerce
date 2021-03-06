#!/bin/bash
docker run --restart=unless-stopped \
        -v /Users/edwardalexisortizagudelo/Documents/alexis/cedesistemas/var/data/elasticsearch:/usr/share/elasticsearch/data \
        --hostname elasticsearch.cedesistemas.local \
        --net cedesistemas_network \
        --name elasticsearch.cedesistemas.local \
        --memory=2048m \
        -e "TZ=America/Bogota" \
        -e "discovery.type=single-node" \
        -e "xpack.security.enabled=false" \
        -e "bootstrap.memory_lock=true" \
        --ulimit memlock=-1:-1 \
        --user 1000:50 \
        -p 9200:9200 \
        -p 9300:9300 \
        -d elasticsearch:6.8.0
