FROM jenkins/jenkins:lts

USER root

#RUN /usr/local/bin/install-plugins.sh workflow-aggregator job-dsl git mailer pipeline-model-definition aws-credentials s3 pipeline-aws

COPY ./plugins.txt /usr/share/jenkins/plugins.txt
RUN cat /usr/share/jenkins/plugins.txt | xargs /usr/local/bin/install-plugins.sh

COPY ./.credentials /usr/share/jenkins/ref/init.groovy.d/
COPY ./configs /usr/share/jenkins/ref/init.groovy.d/
COPY ./init.groovy.d /usr/share/jenkins/ref/init.groovy.d/

ENV JAVA_OPTS -Djenkins.install.runSetupWizard=false

RUN apt-get update && apt-get install -y zip unzip

# install docker
RUN curl -sSL get.docker.com | sh
RUN usermod -aG docker jenkins

# install docker
# COPY install-docker.sh /install-docker.sh
# RUN /install-docker.sh
