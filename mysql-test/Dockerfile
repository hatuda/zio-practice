FROM mysql:8-debian

ENV MYSQL_ROOT_PASSWORD=root

RUN apt update \
    && apt install -y wget unzip \
    && wget http://downloads.mysql.com/docs/sakila-db.zip \
    && unzip sakila-db.zip \
    && mv sakila-db/sakila-schema.sql /docker-entrypoint-initdb.d/01_sakila-schema.sql \
    && mv sakila-db/sakila-data.sql /docker-entrypoint-initdb.d/02_sakila-data.sql \
    && echo "CREATE USER sakila@'%' IDENTIFIED BY 'sakila';" > /docker-entrypoint-initdb.d/03_create_test_user.sql  \
    && echo "GRANT ALL PRIVILEGES ON *.* TO sakila@'%';" >> /docker-entrypoint-initdb.d/03_create_test_user.sql  \
    && echo "FLUSH PRIVILEGES;" >> /docker-entrypoint-initdb.d/03_create_test_user.sql