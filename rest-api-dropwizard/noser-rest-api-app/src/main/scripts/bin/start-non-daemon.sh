#!/bin/bash
set -e
# get dirs
DAEMON_DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"
APP_DIR="$( dirname "$DAEMON_DIR" )"
# get java
JAVA="$( "$DAEMON_DIR"/find-java.sh )"
# run
"$JAVA" $JVM_ARGS -jar "$APP_DIR"/"$@"