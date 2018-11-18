# spring-boot-it-study
Study in how to @Autowire test specific util components

To exercise this example run JUnit on:
- `ContactControllerIT` - this is the code I want to work, but doesn't because I can't figure out how to @Autowire src/test/java @Components
- `ContactControllerIT2` - This example works, but is too busy doing all the test data setup that I want to move to a separate @Component where it can be reused. 