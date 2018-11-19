# spring-boot-it-study
Study in how to @Autowire test specific util components.
See question on [StackOverflow](https://stackoverflow.com/questions/53346814/spring-boot-how-to-define-components-in-src-test-so-they-can-autowire-into-tes)

The original problem:
- `ContactControllerIT` - this is the code I want to work, but doesn't because I can't figure out how to @Autowire src/test/java @Components
- `ContactControllerIT2` - This example works, but is too busy doing all the test data setup that I want to move to a separate @Component where it can be reused. 

Determined this was not an issue of getting src/test/java to be scanned, but rather an understanding with Spring Boot's default scanning process.
The data builder components were added to a different package than then one annotated with @SpringBootApplication.  By default Spring will do component scanning of that package and anything nested
in that package only.  There are two ways then to fix my issue:

1. Move the data building utils into/under the same package as @SpringBootApplication i.e. `com.ttt.example.api`
2. Add `@ComponentScan` to the Application class to include the package the data builder utils are in.

  ```Java
  @ComponentScan("com.ttt.example")  // <-- Move up to root package to ensure all my components are scanned
  @SpringBootApplication
  @SuppressWarnings("checkstyle:hideutilityclassconstructor" /* Spring Boot requires default constructor on Application */)
  public class Application
  ```

