FROM amazoncorretto:11
RUN yum install -y procps-ng
ADD HelloWorld.java .
ADD thread-dumper.sh .
RUN javac HelloWorld.java
EXPOSE 8080/tcp
CMD java -Xms128M -Xmx512M -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=/dumps HelloWorld