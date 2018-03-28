# Description
This project is a personal objective from my work and it’s part of a bigger application. The UI for this application can be found at “ https://github.com/vuscad/evolve-ui ”. This module was called “Training paths” and it should make it possible to create, edit and delete career paths, which will be assign to people later in other modules.

# Requirements
The requirements for the assignment were:
- spring 5
- spring boot 2
- web-flux architecture
- kotlin
- extra: mongoDB

# Architecture
As the architecture of the solution, I chose a Layered one, but I tried to keep the naming specific for web-flux type:
- the controller layer is implemented as a Routing Function, where all the routes for the application are specified
- the business layer is implemented by the handlers
- the repository layer is implemented by the ReactiveMongoRepositories.
As embedded server I use the Reactor.

# Deployment
For deployment, I chose Docker(how could I not..), and my opinion is that for the moment it can be deployed as it is, without clustering or load-ballancers. I think best about it like a module ready to be plugged to a bigger application.

# Attributes
Now about some quality attributes:
- performance and scalability: I think this is more or less self explanatory. By using a web-flux architecture and keeping everything reactive(event the embedded server is Reactor), in theory everything should be asynchronous and non-blocking, which will mean that the system will be very performant and scalable, without even a cluster.
- availability: Usually when I work on a server which will have lots of request and users, I go with Kubernetes and go for 3 node cluster, with load balancing in front of them - for the server container, and just 2 nodes for the DB node. But I think this application server is so small that it doesn’t need clustering, one node will do just fine, but I’ll consider to deploy the UI also on docker, and using an ngNix node in front of it.
- testability: from my experience, testing a server application is easy and can be done in multiple ways. I prefer the pyramid rule: many jUnits, some integration, few smoke-tests. This server is no exception, all the endpoints can be easily tested (manually with postman or automatically - eq Unirest). Spring 5 provides a WebTestClient that can send request to your server and then assert different things about the response.

# Run:
# ./gradlew clean build
# docker-compose up --build -d
