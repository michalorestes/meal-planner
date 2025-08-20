# Contents

- [Contents](#contents)
- [Problem Statement](#problem-statement)
- [Requirements](#requirements)
	- [Functional Requirements](#functional-requirements)
	- [Non-functional goals](#non-functional-goals)
- [Tech Stack](#tech-stack)
- [Architecture](#architecture)
	- [Code Structure](#code-structure)
	- [Infrastructure Diagram](#infrastructure-diagram)
	- [Infrastructure](#infrastructure)
		- [Primary Components](#primary-components)
		- [Security](#security)
			- [Security Groups](#security-groups)
			- [IAM Roles](#iam-roles)
			- [Database Connection](#database-connection)
			- [Credentials Storage](#credentials-storage)
	- [Session And State Management](#session-and-state-management)
	- [Fault Tolerance](#fault-tolerance)
	- [High Availability](#high-availability)
- [Monitoring And Logging](#monitoring-and-logging)
	- [Logging](#logging)
	- [Monitoring](#monitoring)
- [CI/CD](#cicd)
- [Infrastructure As Code (Deferred)](#infrastructure-as-code-deferred)
- [Environments](#environments)
- [Future Considerations](#future-considerations)
- [Appendix](#appendix)
	- [User Stories](#user-stories)

# Problem Statement

Achieving a healthy lifestyle is a common goal but many individuals struggle to maintain healthy nutritional habits consistently. Common barrier to achieving a healthy lifestyle is the slow, tedious and time consuming process of calculating macro nutrients for each meal. This is a critical step in achieving a healthy life style but it's also very discouraging many individuals. 

To address this issue, I am building a meal planning application that will simplify and automate the process of planning meals and calculating macro nutrients. This application will provide tools to plan users' daily meal plans and help them to stay on track with their nutritional goals.
# Requirements

## Functional Requirements

Develop an MVP system for managing user meal plans, recipes and recipe ingredients. The MVP should be focused on providing only the most critical functionality and allow for further expansion. 

- Managing ingredients (add, remove, edit)
- Managing recipes (add, remove, edit)
- Managing daily meal plans (add, remove, edit)
- Nutritional breakdown for every ingredient, recipe and meal plan
- Allow user to register and login to their accounts 
## Non-functional goals

- Security
	- Ensure least privileged permissions for all services and user are used
	- Private networking
	- Credentials, passwords, secret keys should be stored securely 
- Use modular components to allow for scalability and potential migration to microservices in the future
- Minimum infrastructure costs
- Nutritional data should be defined by the user
- Prioritize rapid development, testing and fast time to market 
- Use infrastructure as code for repeatable deployments
# Tech Stack

- **Java** **Spring Boot**: Java with Spring Boot is chosen for the back-end application as its auto-configuration, easy integration with JWT and embedded server enable a rapid development. Additionally, Spring’s layered architecture support clean separation of concerns and Java's mature ecosystem and strong concurrency support will allow the application to scale and grow
- **AWS**: AWS is chosen because it supports the requirements by providing tools to easily build the infrastructure whilst maintain low costs for an application with low traffic
- **GitHub Actions**: Chosen over Jenkins as it does not require any additional infrastructure and it is well integrated within the GitHub ecosystem allowing for fast and easy configuration 
- **Terraform**: Terraform is chosen over AWS CloudFormation as it is not tied to a specific vendor and its modular structure allows for re-using components 

# Architecture

The Meal Planner application is a new MVP designed for low traffic and data volume. The priority at this stage is rapid development, low cost, and a modular structure that can evolve into a more scalable system in the future.

A monolithic architecture with a single code repository has been chosen to meet these goals. This approach enables:

- Fast development and testing cycles with a single code base
- Lower infrastructure costs
- Simple deployment of one application
- Easy on-boarding of new team members (in a team scenario)

Monolithic architectures do have limitations. As the code base grows, maintenance, testing, and deployments can become slower due to tight coupling between components. Scaling may also become more challenging when relying on a single database.

A microservices architecture could address these limitations by:

- Decoupling components into separate services for better adherence to the single responsibility principle
- Enabling independent deployments and dedicated databases per service
- Supporting larger teams and improved scalability
- Optimizing resource usage and cost efficiency

However, the project’s current scale does not justify the added complexity and cost of microservices. The plan is to start with a monolith and progressively decouple components as the application grows.

## Code Structure 

The code will be structured with a domain driven approach where each feature will be isolated in its own package and divided in to data, domain, application and web layers for decoupling components of each feature. While this is not a fully adapted Domain Driven Development model, this structure will support decoupling the code base, clarity, scalability and enable refactoring individual features in to separate micro-services in the future. Compared to other approaches, such as MVC, this approach offers:
- **Feature isolation**: Each domain (e.g. recipes, ingredients, meal plans) is self-contained, improving maintainability and reducing coupling
- **Modularity**: Easier to refactor into microservices or REST APIs as the application grows
- **Testability**: Domain-centric boundaries make it easier to write focused unit and integration tests

Recommended project structure: 

```
src/
├── features/
│   └── [feature-name]/
│       ├── data/
|		│   ├── mapper/
│       │   ├── model/
│       │   ├── repository/
|		│   ├── exception/
│       ├── domain/
│       │   ├── entity/
│       │   └── repository/
│       │   └── services/
│       ├── application/
│       │   └── service/
|		│   ├── mapper/
|		|   |── validator/
|		|   |── dto/
│       └── web/
│           └── controller/
├── shared/
│   ├── utils/
│   └── exceptions/
├── resources/
│   └── templates/
│       └── [feature-name]/
├── config/
└── README.md
```

This structure aligns with the project’s requirement of modular code base open to refactoring to micro-services. 

## Infrastructure Diagram

![Architecture Diagram Image](./images/Phase%201%20Architecture%20Diagram.jpg)

## Infrastructure 

### Primary Components

**Meal Planner application** - the application will run in a Docker container to ensure a consistent development and production environment. Staging environment will be omitted to minimize costs. In production environment, the container will be deployed to AWS ECS service managed with Fargate. ECS services work well for long running containerized services as it allows for scaling the applications easily if needed. 

**Database** - Meal Planner application will utilize MySQL database through AWS RDS. MySQL database will work very well for this project as it mainly requires fixed structured data. Considering the scope of the project, more scalable solutions such as MariaDB are not currently required. NoSQL databases, such as DynamoDB or MongoDB, are also less suitable for the structured and interconnected data of this application and horizontal scaling of the database is not a expected requirement within the near to medium future. 

### Security 

The application will be running inside a Virtual Private Network (VPC). Inside the VPC, the database and Meal Planner application will be isolated within a private sub-network. The ALB will run in a a public sub-network to allow connections from outside the VPC.

#### Security Groups 

- ALB: allows inbound 443, redirects 80→443
- ECS: allows inbound only from ALB
- RDS: allows inbound only from ECS

#### IAM Roles

ECS Task Execution, Application, and CI/CD Deployment roles provide least-privilege access for container execution, secret retrieval, RDS connections, and deployment automation

All policies follow a least-privilege model and live as reusable modules in a centralized Terraform infrastructure repository. Further details will be refined in Phase 2.  

#### Database Connection 

The database will be located within a private sub-network which will not be accessible to any traffic outside of the VPC. AWS Systems Manager Session Manager will be used to allows developers to access the RDS database. 

#### Credentials Storage

- Credentials, keys and secrets required by the application will be stored in AWS Secrets Manager  
- User passwords should be encrypted in the database 
- Sensitive information must be transmitted via the network with HTTPS protocol

## Session And State Management 

The MVP runs a single instance, so no state management is required; Redis or ElasticCache could be introduced if scaling is needed in the future

## Fault Tolerance 

High fault tolerance is not a priority at MVP stage. Currently, failure of ECS or RDS will stop the application. Future improvements could include multiple application instances, multi-AZ RDS replication, or a microservices architecture to limit the impact of individual failures.

## High Availability 

The current infrastructure will be running within a single availability zone to minimize costs. Multi AZ infrastructure should be considered in the future if user base grows. 

# Monitoring And Logging 

## Logging 

Application logs will be stored in Cloud Watch as it is cheaper and easier to integrate with AWS services than alternatives such as Kibana or Data Dog.

## Monitoring 

All alerts should be delivered via Slack and e-mail. 

**Application Performance Monitors**
- Error Rate (HTTP 5xx)
- CPU and Memory Utilization
- Health-check

**Database Monitors**
- Database Connections
- Free Storage Space

# CI/CD

The project uses GitHub Actions to automate continuous integration and deployment. Each push triggers a pipeline that runs unit and integration tests to ensure the code still functions as expected. On successful builds, the application will be packaged into a Docker image and deployed to AWS ECS.

# Infrastructure As Code (Deferred)

- Terraform configuration will be managed via a separate centralized repository
- The local development environment will be built using Docker Compose. 

# Environments

- **Production** - runs on AWS as outlined in this document
- **Development** - Local environment via Docker Compose is sufficient; staging and cloud dev environments are omitted to reduce costs

# Future Considerations 

The current design is functioning considering the scope of this project and allows for rapid development. However, it will become difficult to maintain as the application grows, it does not support scaling or fault tolerance. Each future development phase should aim to decouple application components in to smaller services and progress towards a micro-service architecture: 

| Development Phase | Recommended architecture changes                                                                                                                                                                                                                                    |
| ----------------- | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| Phase 1 (Current) | - Monolith application                                                                                                                                                                                                                                              |
| Phase 2           | - Decouple front-end from back-end<br>- Refactor the existing monolith application to function as REST API for the front-end application<br>- Consider if scaling the application and increasing availability via multi AZ infrastructure is required at this stage |
| Phase 3           | - Handle JWT token generation via a separate auth repository/project                                                                                                                                                                                                |
| Phase 4           | - Migrate ingredients search functionality to a separate service using Elastic Search to provide fast search for the users                                                                                                                                          |
| Further phases    | - Consider micro front-end architecture for the front end application to facilitate better scaling the project across multiple teams                                                                                                                                |

# Appendix 

## User Stories 

[User stories are available in GitHub Issues](https://github.com/michalorestes/meal-planner/issues?q=state%3Aopen%20label%3A%22User%20Story%22)

