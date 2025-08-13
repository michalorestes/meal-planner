# Technical Approach & Architecture Document


# Problem Statement

Meal planning is crucial for achieving a healthy lifestyle as it helps individuals meet their nutritional requirements. Unfortunately, due to the slow and tedious process of calculating macronutrients for each meal, many fall short of achieving their health goals. I am building a meal planning application that will provide a simple and intuitive system for planning daily meals complete with a nutritional breakdown.

# Goals And Constraints

Develop an MVP system for managing user meal plans, recipes and recipe ingredients. The MVP should be designed as a monolithic application running on a single server ensuring the running costs are low. The initial design should be modular to accommodate for future scalability and expansion into microservices architecture.

## Non-functional goals
-  Security
    - Ensure least privileged IAM roles are used
    - Private networking
    - Secure credentials with AWS Secrets Manager
- Application should be developed as a monolith
- Application should be designed with modular components to allow for scalability and potential migration to microservices in the future
- Minimise infrastructure costs
- Assumptions
    - Small user base
    - Nutritional data is user defined
    - No integration with external APIs for the MVP

# TechStack
- **Java** Spring Boot: For backend logic
- **AWS**: Infrastructure and monitoring including ECS, database, CloudWatch
- **GitHub Actions**: CI/CD pipeline
- **Terraform**: Infrastructure as code
