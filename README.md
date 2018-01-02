# microservice-template Usage:

git clone https://git.autodesk.com/fengka/microservice-template.git

cd microservice-template

docker build -t mst-v1 .

docker run -p 3000:3000 mst-v1

chrome:  http://xxxx:3000
