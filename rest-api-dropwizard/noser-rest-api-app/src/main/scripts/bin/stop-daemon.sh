#!/bin/bash
set -e
# script dir
DAEMON_DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"
APP_DIR="$( dirname "$DAEMON_DIR" )"
# pid file path
PID_FILE="$APP_DIR"/.pid
# get java
JAVA="$( "$DAEMON_DIR"/find-java.sh )"
# check pid file
if [ ! -f "$PID_FILE" ] ; then
    echo "Pid file doesn't exist, path: $PID_FILE"
    exit 1
fi
# shutdown
PID="$( cat "$PID_FILE" )"
rm "$PID_FILE"
kill "$PID"
echo "TERM signal sent, pid: $PID"