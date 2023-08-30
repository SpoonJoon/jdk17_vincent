FROM debian:latest

# Install required boot image, build tools and dependencies
RUN apt-get update && apt-get install -y \
  build-essential \
  git \
  openjdk-17-jdk \
  libfreetype6-dev \
  libcups2-dev \
  libx11-dev libxext-dev libxrender-dev libxrandr-dev libxtst-dev libxt-dev \
  libasound2-dev \
  libffi-dev \
  libfontconfig1-dev\
  zip\
  autoconf \
  file

# Clone the OpenJDK 17
RUN git clone https://github.com/SpoonJoon/jdk17u.git /opt/openjdk

WORKDIR /opt/openjdk
