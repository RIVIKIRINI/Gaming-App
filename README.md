# Gaming App
This Android game, titled "GameJ", is a space adventure game where players control a rocket that moves between three lanes to avoid oncoming planets. The game is built with a custom GameView that handles the game's rendering and user interactions.

## Key Features:

### 1. Gameplay Mechanics:
* Rocket Movement: Players can move the rocket left or right by tapping on the respective side of the screen.
* Obstacle Planets: Planets appear randomly and move down the screen. The player must avoid colliding with these planets.
* Speed and Scoring: The game speed increases as the player's score goes up. The player's score increments each time an obstacle planet is successfully avoided.
* Game Over Condition: The game ends if the rocket collides with a planet, triggering the closeGame method which displays the final score.

### 2. High Score Management:
The game tracks and saves the highest score achieved using SharedPreferences.
The high score is displayed on the main screen and updated if a new high score is achieved.

### 3. User Interface:
Main Activity: Contains a start button to begin the game, and text views to display the current score and high score.
Game View: Handles the drawing of the game elements (space background, rocket, obstacle planets) and manages the game loop.

### 4. Graphics:
Uses BitmapFactory to decode resources for the space background and planets.
The rocket and obstacle planets are drawn on the canvas using drawable resources.

## Code Structure:
Manifest File: Defines the application and main activity, ensuring the app can be launched from the home screen.
Main Activity: Manages the start of the game and displays the score and high score.
GameView Class: Extends View to handle game rendering, user interactions, and game logic.
SharedPreferences: Used for saving and retrieving the high score.
Interface GameTask: Defines the closeGame method for handling game-over logic.
Unit Tests: Includes basic tests for ensuring correct application context and simple logic.
How to Play:
Tap the start button to begin the game.
Control the rocket by tapping on the left or right side of the screen to switch lanes.
Avoid colliding with other planets that appear on the screen.
The game ends upon collision, displaying the score and updating the high score if beaten.
