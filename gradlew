#!/usr/bin/env sh

APP_HOME="$(cd "$(dirname "$0")" && pwd)"
CLASSPATH=$APP_HOME/gradle/wrapper/gradle-wrapper.jar

if [ -n "$JAVA_HOME" ] ; then
    if [ -x "$JAVA_HOME/bin/java" ] ; then
        JAVACMD="$JAVA_HOME/bin/java"
    else
        JAVACMD="$JAVA_HOME/jre/bin/java"
    fi
    if [ ! -x "$JAVACMD" ] ; then
        echo "ERROR: JAVA_HOME is set to an invalid directory: $JAVA_HOME"
        exit 1
    fi
else
    JAVACMD="java"
    which java >/dev/null 2>&1 || {
        echo "ERROR: JAVA_HOME is not set and no 'java' command could be found in your PATH."
        exit 1
    }
fi

DEFAULT_JVM_OPTS='"-Xmx64m" "-Xms64m"'
GRADLE_OPTS=${GRADLE_OPTS:-}
GRADLE_APP_NAME="Gradle"

eval "set -- $DEFAULT_JVM_OPTS $JAVA_OPTS $GRADLE_OPTS"

exec "$JAVACMD" -classpath "$CLASSPATH" org.gradle.wrapper.GradleWrapperMain "$@"
