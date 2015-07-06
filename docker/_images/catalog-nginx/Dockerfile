FROM nginx
RUN apt-get update && apt-get install -y curl && apt-get install -y vim

COPY app/ /usr/share/nginx/html
#COPY default.conf /etc/nginx/conf.d/default.conf

#RUN sed -i 's/127.0.0.1/'"$CATALOG_GF_PORT_8080_TCP_ADDR"'/g' /etc/nginx/conf.d/default.conf

