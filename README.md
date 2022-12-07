# BigMetric

## Introduction
BigMetric is a Proof of Concept to test a scalable network of temperature sensors located worldwide.
Theoretically, if implemented correctly, it should be able to handle up to 1 million devices,
that send their °C temperature every ~10 seconds.
![BigMetric schema](/../images/bigmetric-schema.png?raw=true "BigMetric schema")

## Deployment
In my PoC the infrastructure deployment is rough, everything runs in Docker containers on a local
machine and therefore cannot be expanded too much.

### Scalability
#### On Premise
If you opt for a good on-premise deployment, there are many opportunities for scalability using
Kubernetes when the sensor network grows.
Since each Spring microservice is independent of each other, with Kubernetes simply add more
of them, and they will take the input data from the Apache Kafka cluster.
Then, simply add more Cassandra and Kafka nodes to their clusters, as the performance increase
is nearly linear when you add more nodes (horizontal scaling).

#### Cloud
We can migrate this PoC to use cloud providers services and have an easy way to automatically scale
based on load.
The possible solution is to use a managed Kubernetes service such as GKE to manage the deployment of Java
Spring microservices, or even a serverless solution, if it's cheaper, in that way it will be executed only
when there are data to process.
Also, instead of a Cassandra cluster we can opt for something similar, such as Google BigTable
or Amazon DynamoDB, and Google Cloud Pub/Sub can replace Apache Kafka.
