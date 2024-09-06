
# Project Overview Proposal

## Project Name: Cat Math

### Elevator Pitch:

* My team and I have kids who are in grade school. They are doing math and math drills that focus on the basics. I was looking for an app for him to use as practice, and I did not find anything that he was interested in. His teacher says that there is a market for apps that support classroom activities. I think we should make an Android app called Cat Math. The app will support basic math as drills as well as function as a calculator. The kids love games, so I think a level system with XP points would be a great learning motivator. I would like to add animal science facts to support gradeschool science learning. The kids seem to love cats and they liked this idea. My team and I have worked out the basic tech stack and architecture. Can I send you a more formal proposal?
	
### Complexity and Components:

* Andriod OS based math drill and calculator app.
* UI with artwork: The user interface with be relatively simple with bright colors, easy for children to navigate.
* Experience Points (XP): A number which will increase the users level and serve as currency for the user to purchase cat or animal avatars.
* Reward correct answers with animal facts (especially cat facts): A window which provides extra educational content upon a correct answer.
* Math question mode: A component which will ask a basic math question. It will reward XP and a cat or animal fact upon a correct answer.
* Math drill mode: Timed basic math questions. It will reward XP and a cat or animal fact upon a correct answer.
* Calculator mode: The app will also be able to function as a basic calculator in increase user activity.

### Predicted Architecture:

* The Android app will function as a stand alone piece of software on Android devices. No backend will be necessary.
* We have never used the tech stack listed below or build an app like this. Based on zero experience, our predicted architecture will focus on availability, reliability, and security. To this end we will attempt to use SOLID software architecture principles. 
	* Single Responsibility: One objective for each service component.
	* Open-Closed Principle: Each service component must be independent and upgradeable.
	* Liskov Substitution Principle: Each service component should be able to communicate with every other service component and substitute when necessary.
	* Interface Segregation Principle: Service components must not have redundancies.
	* Dependence Inversion Principle: Higher level service components must not rely on lower level service components and changes to high level components must not affedt low level components.


### Tech Stack and Frameworks:

* We will use Android Studio as our IDE and testing software. 
* React Native will be the "glue" in our tech stack.
* Java Script and Java will be the primary coding languages.
* These components will be subject to change as the project iterates through the lifecycle.

### Methodology and Lifecycle:

* Scrum administered via OpenProject or Taiga. 
* Jessica Venema will be the Scrum master.
* Sprints will be a weekly event.
* We will have standups at least twice a week.


