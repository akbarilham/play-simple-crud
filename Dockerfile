ARG OPENJDK_TAG=11.0.13
FROM openjdk:${OPENJDK_TAG}

ARG SBT_VERSION=1.9.6

# prevent this error: java.lang.IllegalStateException: cannot run sbt from root directory without -Dsbt.rootdir=true; see sbt/sbt#1458
WORKDIR /app

# Install sbt
RUN \
  mkdir /working/ && \
  cd /working/ && \
  curl -L -o sbt-$SBT_VERSION.deb https://repo.scala-sbt.org/scalasbt/debian/sbt-$SBT_VERSION.deb && \
  dpkg -i sbt-$SBT_VERSION.deb && \
  rm sbt-$SBT_VERSION.deb && \
  apt-get update && \
  apt-get install sbt && \
  cd && \
  rm -r /working/ && \
  sbt sbtVersion