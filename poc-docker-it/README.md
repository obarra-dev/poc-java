
mvn clean install -Dit.port=5000

mvn clean install -Plocal-profile -Dlocal-profile


docker build -t python-rest .

docker run -p 5000:5000 python-rest

docker run python-rest 