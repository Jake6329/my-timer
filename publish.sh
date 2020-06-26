#!/usr/bin/env bash

function show_usage {
    echo "./publish.sh [-d | -p]"
    echo "-d: for developement"
    echo "-p: for production (default)"
    exit 1
}

function check_multiple_mode {
    if [ -n "$MODE" ]; then
        echo "Multiple mode arguments are specified. Please specify only one."
        show_usage
    fi
}

POSITIONAL=()
while [[ $# -gt 0 ]]; do
    key="$1"
    case $key in
        -h|--help)
            show_usage
            ;;
        -p|--prod)
            check_multiple_mode
            MODE="prod"
            shift
            ;;
        -d|--dev)
            check_multiple_mode
            MODE="dev"
            shift
            ;;
        *) # unknown option
            POSITIONAL+=("$1") # save it in an array for later
            shift
            ;;
    esac
done
set -- "${POSITIONAL[@]}" # restore positional parameters

if [ -z "$MODE" ]; then
    MODE="prod"
fi

SUBMODULE_DIR="Jake6329.github.io"
SRC_JS_DIR="target/scala-2.12"
DEST_JS_DIR="js"

if [[ "$MODE" == "prod" ]]; then
    sbt fullOptJS
    cp "$SRC_JS_DIR/the-timer-opt.js" "$SUBMODULE_DIR/$DEST_JS_DIR/the-timer.js"
    cp "$SRC_JS_DIR/the-timer-opt.js.map" "$SUBMODULE_DIR/$DEST_JS_DIR/the-timer-opt.js.map"
else
    sbt fastOptJS
    cp "$SRC_JS_DIR/the-timer-fastopt.js" "$SUBMODULE_DIR/$DEST_JS_DIR/the-timer.js"
    cp "$SRC_JS_DIR/the-timer-fastopt.js.map" "$SUBMODULE_DIR/$DEST_JS_DIR/the-timer-fastopt.js.map"
fi

cp "$SRC_JS_DIR/the-timer-jsdeps.js" "$SUBMODULE_DIR/$DEST_JS_DIR/the-timer-jsdeps.js"
cp timer.html $SUBMODULE_DIR
