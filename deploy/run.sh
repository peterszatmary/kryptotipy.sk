#!/usr/bin/env bash

# usage run.sh
ps aux | grep kryptotipy-web
LATEST_JAR_WITH_VERSION=$(ls -tr | grep jar | grep kryptotipy-web | tail -1)
echo 'Version found='$LATEST_JAR_WITH_VERSION
ps aux | grep kryptotipy-web
PID=$(ps aux | sort -r | grep jar | grep ${LATEST_JAR_WITH_VERSION} | sed -n '2p' | awk -F ' ' '{print $2}')
echo 'PID found='$PID
kill -9 $PID
echo 'After kill'
ps aux | grep kryptotipy-web
CMD='java -jar -Dspring.config.location=app-prod.properties '${LATEST_JAR_WITH_VERSION}
echo 'Running command='$CMD
$CMD

