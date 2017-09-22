
FROM jboss/wildfly:10.1.0.Final

MAINTAINER Sathiya Kirushnapillai <sathiya.kirushnapillai@heig-vd.ch> Mathieu Monteverde <mathieu.monteverde@heig-vd.ch>

RUN /opt/jboss/wildfly/bin/add-user.sh admin admin --silent

CMD ["/opt/jboss/wildfly/bin/standalone.sh", "-b", "0.0.0.0", "-bmanagement", "0.0.0.0"]
