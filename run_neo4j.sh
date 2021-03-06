#!/bin/bash
docker run --restart=unless-stopped \
        -v /Users/edwardalexisortizagudelo/Documents/alexis/cedesistemas/var/data/neo4j:/data \
        -v /Users/edwardalexisortizagudelo/Documents/alexis/cedesistemas/var/data/neo4j/logs:/var/lib/neo4j/logs \
        -v /Users/edwardalexisortizagudelo/Documents/alexis/cedesistemas/var/data/neo4j/data:/var/lib/neo4j/data \
        -v /Users/edwardalexisortizagudelo/Documents/alexis/cedesistemas/var/data/neo4j/conf:/var/lib/neo4j/conf \
        -v /Users/edwardalexisortizagudelo/Documents/alexis/cedesistemas/var/data/neo4j/plugins:/var/lib/neo4j/plugins \
        --hostname neo4j.eladio.local \
        --name neo4j.eladio.local \
        --memory=1024m \
        -e "TZ=America/Bogota" \
        -e "NEO4J_AUTH=none" \
        -p 7474:7474 \
        -p 7687:7687 \
        -d neo4j:4.0.3
