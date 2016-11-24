#!/bin/bash
set -e
DAEMON_DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"
APP_DIR="$( dirname "$DAEMON_DIR" )"
JAVA="$( "$DAEMON_DIR"/find-java.sh )"

# startup
"$JAVA" $JVM_ARGS -jar "$APP_DIR"/"${project.build.finalName}.jar" server $@
