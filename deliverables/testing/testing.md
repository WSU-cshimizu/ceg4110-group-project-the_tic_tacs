
### Testing Plan:

As we are building our project in java, Unit Testing is the best automated and robust testing method that can be used. 
Unit tests validate the correctness of  code by checking if individual components work as intended. 
This helps catch bugs early and improves the reliability of your application. Some of the elements that I will be testing are:
⦁	Unit testing for individual components
⦁	Method-level validation
⦁	Error handling verification
⦁	Mathematical Logic Validation
⦁	XP Point Calculation Tests
⦁	Question Generation Accuracy
⦁	User Interface Component Tests
⦁	Error Handling
⦁	Calculator Functionality Test
⦁	Catfact Generation Test

### Testing Framework:

The framework I choose for unit testing is JUnit4, which is a well-established testing framework for Java and Kotlin-based projects and comes built-in in android studio.

### Testing Plan Justification:

Unit tetsing  is already part of the team's development plan, making it easy to implement without disrupting workflows. It integrates well with Android's build system ,
 allowing tests to run automatically and ensuring early detection of bugs. Unit testing helps to test each small feature of the app . 
 So, we can keep testing our code along with the development of the project. It will be easy for us to debug each feature compare to the whole app.
  We can write Independent, repeatable test cases.
 
### Justification for Framework:

JUnit4 is standard Java testing framework and already widely used in Android development, and it's well-suited for testing Kotlin code. 
Since we are already using JUnit4 for unit testing, it's easy to integrate with the Jetpack Compose testing tools for UI testing, without introducing complex 
new frameworks or dependencies.JUnit4 combined with Compose UI Testing will adequately test both the logic (XP validation, avatar selection) 
and the UI (element visibility, interaction). This combination ensures that both the backend logic and frontend UI are thoroughly tested.
 I also had used Junit in my undergrad level once for one of my java project. So, I choose Junit for the unit testing of this project.

 
Note: All of mine testing codes are located in: deliverables\Cat Math\app\src\test\java\com\example\catmath
