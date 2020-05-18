FROM ubuntu
LABEL "matainer"="Chongwen"
RUN mkdir /usr/local/jdk
WORKDIR /usr/local/jdk
ADD ./jdk-11.0.7_linux-x64_bin.tar.gz /usr/local/jdk

ENV JAVA_HOME /usr/local/jdk/jdk-11.0.7
ENV JRE_HOME /usr/local/jdk/jdk-11.0.7/jre
ENV PATH $JAVA_HOME/bin:$PATH