FROM mongo:4.2.3
ADD ./setup.js /docker-entrypoint-initdb.d/
EXPOSE 27017
ENV MONGO_INITDB_ROOT_USERNAME='root'
ENV MONGO_INITDB_ROOT_PASSWORD='hackerman'
