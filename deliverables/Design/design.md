# 1. Design Specification for Cat Math

* Cat Math

* Version 0.1

* Friday, 18 October 2024

* Ben Hawk and Jessica Venema

* Approvers: 


---

## 2. Introduction

* Overview:  Brief summary of the project. What is the project about?

* Purpose: Why is this project necessary? What problem does it solve?

* Scope: Define the boundaries—what is included and what is not.

* Audience: Children in Kindergarten, 1st, and 2nd grade.


---

## 3. Goals and Objectives

* Primary Goal: What is the main purpose of the project?

* Objectives: Break the goal into smaller, actionable objectives.

* Example: “Implement user login,” “Ensure password encryption,” etc.


Success Criteria: How will you measure success?
Example: “All unit tests pass with 95% coverage.”


---

## 4. System Overview

* Architecture: Describe the high-level architecture (client-server, microservices, etc.). Use diagrams if possible.

* Core Components: What major components are involved (e.g., frontend, backend, database)?

* Technologies: List the programming languages, frameworks, libraries, and tools used. Example: “React for frontend, Node.js for backend, PostgreSQL for the database.”


---

## 5. Functional Requirements

### Requirement 1: The UI shall be engaging and intuitive.
- Engaging User Interface:
   - The user interface shall have large and colorful buttons that will clearly state their purpose, such as the "Game Modes" button, or the home button that will have a home icon.
   - The User's current level and progress shall always be displayed at the top of each screen.
- Intuitive:
  - The Main Menu shall have an option for Game Modes, Avatars, and to quit.
  - The Main Menu shall always be easily accessed by a button in each mode.
  - The Game Modes screen shall have the option for Math Drill, Math Problems, and Calculator Modes.
  - The Avatars screen shall display the current XP and the selected Avatar.
  - The User can shall the phone's backspace button to go back to the last screen. 
  
* UI Charts
  - ![UI Chart](./UI2.png/)
  - ![UI Chart](./UI1.png/)

### Requirement 2: The user shall level up and have a customizable avatar.
- User:
  - The user shall have an array of their owned avatars.
  - The user shall have an avatar variable for their equipped avatar.
  - Users shall unlock new avatars by purchasing them with their total XP, which will not decrease their level or progress to the next.
- Avatar:
  - Avatars shall be animal themed.
  - There shall be only one avatar can be equipped at a time. 
  
* XP and Avatar Charts
  - ![Avatar Chart](./XPandAvatars.png/)

### Requirement 3: The user shall be able to solve math problems using the Calculator Mode. 
- Calculation:
  - The calculator shall have functions for addition, subtraction, mutliplication, and division.
  - The calculator shall display the entire equation on the screen.
  
* Calculator Mode Charts
  - ![Calculator Mode Chart](./CatMath-CalcMode-SwimLane.pdf/)
  - ![Calculator Mode Chart](./CatMath-CalcMode-DataFlow.pdf/)
  
### Requirement 4: The user shall be able to practice in the Math Problem Mode.
- Problem Generation:
  - The Math Problem mode shall random math problems that are appropriate for K-2 grades.
  - The math problem shall be checked using the calculator functionality to ensure it is valid.
- Answer:
  - Answering incorrectly shall display the correct answer, and with a slight increase in XP.
  - Answering correctly shall result in more XP given as well as a cat fact.
  
* Math Problem Mode Charts
  - ![Math Problem Mode Chart](./MathProblems1.png/)
  - ![Math Problem Mode Chart](./MathProblems2.png/)

### Requirement 5: The user shall be able to challenge themself in the Math Drill Mode.
- Timer:
  - The Math drill mode shall employ similar problem generation, but with the addition of a timer.
  - The timer shall start at 60 seconds and complete at 0 seconds.
  - Running out of time shall end the user's run. 
- Answer:
  - Entering the incorrect answer shall fail the current run, slightly increase xp, and give the correct answer.
  - Entering correctly shall display the user's equipped avatar and a cat fact, as well as increase xp.
  
* Math Drill Mode Charts
  - ![Math Drill Mode Chart](./MathDrillMode.png/)


---

## 6. Technical Design

* Diagrams: Include flowcharts, class diagrams, sequence diagrams, or ERDs if needed.

* Data Structures: List key data structures or schemas. Example: “The User object will contain ID, name, email, and password fields.”

* Algorithms: Describe critical algorithms if any are needed.


---

## 7. Project Plan

* Timeline and Milestones: Provide key deadlines or a project schedule. Example: “Authentication feature by October 25th.”

* Dependencies: List third-party libraries, tools, or systems the project relies on.


---

## 8. Testing and Validation

* Testing Strategy: Unit testing, integration testing, end-to-end testing, etc.

* Acceptance Criteria: Define what constitutes project completion.

* Tools for Testing: Mention any testing frameworks (e.g., Jest, JUnit).


