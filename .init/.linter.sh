#!/bin/bash
cd /home/kavia/workspace/code-generation/android-tv-hello-world-42026-42035/frontend_android_tv
./gradlew lint
LINT_EXIT_CODE=$?
if [ $LINT_EXIT_CODE -ne 0 ]; then
   exit 1
fi

