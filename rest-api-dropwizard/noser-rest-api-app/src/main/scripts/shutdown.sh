#!/bin/bash
set -e
DIR="$(cd `dirname "${BASH_SOURCE[0]}"` && pwd)"
#DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"
#DIR="$(dirname "$(readlink -f "$0")")"
"$DIR"/bin/stop-daemon.sh