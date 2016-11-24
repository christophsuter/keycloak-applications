#!/bin/bash
set -e
DIR="$(cd `dirname "${BASH_SOURCE[0]}"` && pwd)"
#DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"
#DIR="$(dirname "$(readlink -f "$0")")"
"$DIR"/bin/start-daemon.sh ${project.build.finalName}.jar server $@