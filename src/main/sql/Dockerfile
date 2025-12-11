FROM postgres:alpine


ENV POSTGRES_USER=postgres
ENV POSTGRES_PASSWORD='Thread.currentThread()'
ENV POSTGRES_DB=project1_db

COPY  library.sql /docker-entrypoint-initdb.d/
EXPOSE 5432