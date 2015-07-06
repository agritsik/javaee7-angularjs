FROM httpd:2.4
RUN apt-get update && apt-get install -y curl && apt-get install -y vim

COPY app/ /usr/local/apache2/htdocs/