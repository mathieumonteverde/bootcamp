
FROM oracle/glassfish:4.1.2

MAINTAINER Sathiya Kirushnapillai <sathiya.kirushnapillai@heig-vd.ch> Mathieu Monteverde <mathieu.monteverde@heig-vd.ch>

ADD drivers/ /glassfish4/glassfish/lib

RUN echo "AS_ADMIN_PASSWORD=glassfish" > /tmp/glassfishpwd && \
asadmin --user admin --passwordfile=/tmp/glassfishpwd create-domain domainAMT && \
asadmin start-domain domainAMT  && \
asadmin --user admin --passwordfile=/tmp/glassfishpwd enable-secure-admin && \
asadmin --interactive=false --user admin --passwordfile=/tmp/glassfishpwd create-jdbc-connection-pool --restype javax.sql.DataSource --datasourceclassname com.mysql.jdbc.jdbc2.optional.MysqlDataSource --property user=root:password=adminpw:DatabaseName=Bootcamp:ServerName=db:port=3306 pool_bootcamp && \
asadmin --interactive=false --user admin --passwordfile=/tmp/glassfishpwd create-jdbc-resource --connectionpoolid pool_bootcamp jdbc/bootcamp

RUN ./glassfish4/bin/asadmin stop-domain domainAMT

ADD app/amt_bootcamp_java-1.0-SNAPSHOT.war /glassfish4/glassfish/domains/domainAMT/autodeploy/

CMD ["asadmin", "start-domain", "-v", "domainAMT"]
