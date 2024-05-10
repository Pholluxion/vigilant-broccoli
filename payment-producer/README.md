# Payments Producer

This is a simple producer that sends messages to RabbitMQ and retrieves the response from the processing service.


## How to build Docker image
```bash
.\mvnw clean package
```

```bash
docker build -f src/main/docker/Dockerfile.jvm -t pholluxion/payment-producer . 
```

## How to run Docker container

```bash
docker run -i --rm -p 8081:8081 pholluxion/payment-producer
```

## How to push Docker image to Docker Hub

```bash
docker push pholluxion/payment-producer
```
