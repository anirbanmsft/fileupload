# Getting Started

### Steps to run this application in docker container

* `mvn clean package`
* `docker build -t <conainer-registry>/<user>/<image-name>:<version>`
* `docker push <conainer-registry>/<user>/<image-name>:<version>`
* `docker run -it -e  azure.accountName=<storage-account-name> -e azure.containerName=<storage-account-container-name> -e azure.accountKey=<storage-account-access-key> -e azure.blobEndpoint=<storage-account-container-endpoint> -p 8080:8080 <conainer-registry>/<user>/<image-name>:<version> --name <name-of-docker-container-instance>`