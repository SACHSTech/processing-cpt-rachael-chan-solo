import processing.core.PApplet;
import processing.core.PImage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Sketch1 extends PApplet {

    PImage imgBackground;
    PImage[] images = new PImage[4];  // Array to hold the four images
    Float[] imageX = new Float[4];    // Array to hold the X positions of the images
    Float[] imageY = new Float[4];    // Array to hold the Y positions of the images
    int[] imageColors = new int[4];   // Array to hold the colors of the images
    float speed = 5;                  // Speed at which the images fall

    ArrayList<Integer> order = new ArrayList<>();  // ArrayList to hold the correct order of the images
    ArrayList<Integer> caught = new ArrayList<>(); // ArrayList to hold the images caught by the catcher
    int lives = 3;                                 // Number of lives
    int catcherColor = color(255, 0, 0);  // Color of the catcher

    float catcherX;  // X position of the catcher
    float catcherSpeed = 10;  // Speed at which the catcher moves


    public void settings() {
      // size call
      size(800, 600);
    }

    public void setup() {
      imgBackground = loadImage("Kitchen.png");
      imgBackground.resize(800, 600);

       // Set the initial position of the catcher
      catcherX = width / 2;

      // Set the initial color of the catcher
      catcherColor = color(255, 0, 0);  // Red
        
      Arrays.setAll(imageX, i -> 0f);
      Arrays.setAll(imageY, i -> (float)(images[i].height) * -1);


      // Load the four images into the array
      for (int i = 0; i < 4; i++) {
        images[i] = loadImage("data/image" + (i + 1) + ".jpg");  // Replace "image" with the actual name and extension of your image files
        imageColors[i] = color(255);  // Set the default color of the images to white
      }

      // Set the colors of the images
      imageColors[0] = color(255, 0, 0);   // Red
      imageColors[1] = color(0, 255, 0);   // Green
      imageColors[2] = color(0, 0, 255);   // Blue

      // Shuffle the arrays of image positions and create the correct order
      /*Arrays.fill(imageX, 0f);
      Arrays.fill(imageY, -height);
      */
      order.addAll(Arrays.asList(0, 1, 2, 3));  // Add the indices of the images in the original order
      Collections.shuffle(Arrays.asList(imageX));
      Collections.shuffle(Arrays.asList(imageY));
      Collections.shuffle(order);

      // Set the initial position of the catcher
      catcherX = width / 2;
    }

    public void draw() {
      // background(255);  // Clear the background
      // add background 
      image(imgBackground, 0, 0);

      // Update the positions of the images
      for (int i = 0; i < imageY.length; i++) {
        imageY[i] += speed;

        // Reset the position of the image when it reaches the bottom of the screen
        if (imageY[i] > height) {
          imageY[i] = (float)(images[i].height) * -1;
          imageX[i] = random(width);
        }

        // Check if the catcher caught the image
        if (imageY[i] + images[i].height >= height) {
          if (caught.size() < order.size() && order.get(caught.size()) == i && imageColors[i] == catcherColor) {
            caught.add(i);
          } else {
            lives--;
            if (lives == 0) {
              gameOver();
            }
          }
        }
      }
  
      for (int i = 0; i < images.length; i++) {
        // push();
        strokeWeight(5);
        stroke(imageColors[i]);
        image(images[i], imageX[i], imageY[i]);
        // pop();
      }

      // Draw the catcher with the selected color
      drawCatcher();

      // Draw the lives
      drawLives();

      // Check if the player won
      if (caught.size() == order.size()) {
        gameWon();
      }
    }

    public void drawCatcher() {
      // Draw the catcher at the bottom center of the screen with the selected color
      float catcherWidth = 100;
      float catcherHeight = 20;
      float catcherY = height - catcherHeight;

      fill(catcherColor);
      rect(catcherX, catcherY, catcherWidth, catcherHeight);
    }

    public void drawLives() {
      // Draw the remaining lives at the top left of the screen
      String livesText = "Lives: " + lives;
      float livesX = 10;
      float livesY = 20;

      fill(0);
      textSize(20);
      text(livesText, livesX, livesY);
    }

    public void gameOver() {
      // Game over logic
      textAlign(CENTER, CENTER);
      textSize(50);
      fill(255, 0, 0);
      text("Game Over",width/2, height/2);
      noLoop();
    }

    public void gameWon() {
      // Game won logic
      textAlign(CENTER, CENTER);
      textSize(50);
      fill(0, 255, 0);
      text("You Won!", width/2, height/2);
      noLoop();
    }

    public void keyPressed() {
      // Move the catcher left or right when the corresponding arrow key is pressed
      if (keyCode == LEFT) {
        catcherX -= catcherSpeed;
      } else if (keyCode == RIGHT) {
        catcherX += catcherSpeed;
      } else if (key == '1') {
        catcherColor = color(255,0,0);  // red
      } else if (key == '2') {
        catcherColor = color(0,255,0);  // green
      } else if (key == '3') {
        catcherColor = color(0,0,255);  // blue
      } 
    }

    public void keyReleased() {
      // Stop the catcher from moving when the arrow keys are released
      if (keyCode == LEFT || keyCode == RIGHT) {
        catcherX = constrain(catcherX, 0, width);  // Constrain the catcher's X position within the screen bounds
      }
    }

}
