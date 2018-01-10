# from https://github.com/big-data-europe/docker-kafka

FROM bde2020/kafka:2.11-0.10.2.0

MAINTAINER Karl-Heinz Sylla <karl-heinz.sylla@iais.fraunhofer.de>

RUN chmod +x /wait-for-step.sh

COPY healthcheck /
COPY kafka-startup.sh /
COPY execute-step.sh /
COPY finish-step.sh /

EXPOSE 9092

CMD [ "./kafka-startup.sh" ]
