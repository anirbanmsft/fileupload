# Getting Started

This application takes .MP4 files as input, converts it to .AVI formats and uploads them in the Azure Storage container.

## Prerequisites

### To build and package this application, you need the following prerequisites:

* [Java 11](https://adoptopenjdk.net/).
* [Maven 3.7+](https://maven.apache.org/install.html).

### To run this application, you need the following prerequisites:

* An active [Azure account](https://azure.microsoft.com/en-us/free/).
* An [Azure Storage Account](https://docs.microsoft.com/en-us/azure/storage/common/storage-account-create?tabs=azure-portal) and [container](https://docs.microsoft.com/en-us/azure/storage/blobs/blob-containers-portal#create-a-container).
* [Docker Desktop](https://docs.docker.com/desktop/install/windows-install/) (for Windows) or [docker engine](https://www.linux.com/topic/desktop/how-install-and-use-docker-linux/) (for Linux) running in your system.

## Steps to run this application in docker container

* `mvn clean package`
* `docker build -t <conainer-registry>/<user>/<image-name>:<version>`
* `docker push <conainer-registry>/<user>/<image-name>:<version>`
* `docker run -it -e  azure.accountName=<storage-account-name> -e azure.containerName=<storage-account-container-name> -e azure.accountKey=<storage-account-access-key> -e azure.blobEndpoint=<storage-account-container-endpoint> -p 8080:8080 <conainer-registry>/<user>/<image-name>:<version> --name <name-of-docker-container-instance>`


## Download the docker image from public Azure Container Registry

* `az login`
* `az acr login --name <acrName>`
* `docker pull hackathonlocal.azurecr.io/anirban/upload-video-to-azure-storage:latest`

reference: [Individual login with Azure AD](https://docs.microsoft.com/en-us/azure/container-registry/container-registry-authentication?tabs=azure-cli)

## REST API
The REST API to upload avi file to Azure Storage Blob.

### Request
`POST /blob/writeBlobFile`

Response
HTTP/1.1 200
Content-Type: "application/json"
Date: "Mon, 05 Sep 2022 13:05:22 GMT"

    {
        "accountName": "<accountName>",
        "accountKey": "<accountKey>",
        "containerName": "<containerName>",
        "blobEndpoint": "<blobEndpoint>",
        "blobURL": "<blobURL>"
    }
