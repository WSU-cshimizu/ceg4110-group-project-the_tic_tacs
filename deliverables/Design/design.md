# Design Specification

## Requirement 1: The UI shall be engaging and intuitive.
- Engaging User Interface
   - The user interface shall have large and colorful buttons that will clearly state their purpose, such as the "Game Modes" button, or the home button that will have a home icon.
   - The User's current level and progress will always be displayed at the bottom of each screen.
- Intuitive
  - The Main Menu will have an option for Game Modes, Avatars, and to quit.
  - The Main Menu will always be easily accessed by a button in each mode.
  - The Game Modes screen will have the option for Math Drill, Math Problems, and Calculator Mode
  - The Avatars screen will display the current XP and the selected Avatar.
  - The User can use the phone's backspace button to go back to the last screen. 

## Requirement 2: The user shall level up and have a customizable avatar.
- User
  - The user will have a dedicated class to track XP, Levels, and owned avatars.
  - The user will have an integer variable for their current level.
  - The user wil have an integer variable for their total accrued XP.
  - The user will have an integer variable for their current XP count.
  - The user will have an array of their owned avatars.
  - The user shall have an avatar variable for their equipped avatar.
  - Users shall unlock new avatars by purchasing them with their total XP, which will not decrease their level or progress to the next. 
- Avatar
  - Avatars will be separated into their own class.
  - There will be an integer variable for the avatar's XP cost.
  - There will be a boolean variable for the avatar's owned status.
  - There will be a boolean variable for the avatar's equipped status.
  - Only one avatar can be equipped at a time. 

## Requirement 3: The User shall be able to solve math problems using the Calculator. 
  - 

## Requirement 4: The user shall be able to practice in the Math Problem Mode.

## Requirement 5: The user shall be able to challenge themself in the Math Drill Mode.



