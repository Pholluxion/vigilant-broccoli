# Payments Processor

This is a simple processor that receives messages from RabbitMQ and processes them and save  the result in a database.

## How to build Docker image

```bash
docker build -f src/main/docker/Dockerfile.jvm -t pholluxion/payment-processor .
```

## How to run Docker container

```bash
docker run -i --rm -p 8083:8083 pholluxion/payment-processor
```

## How to push Docker image to Docker Hub

```bash
docker push pholluxion/payment-processor
```