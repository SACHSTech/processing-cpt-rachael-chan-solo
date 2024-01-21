import processing.core.PApplet;
import processing.core.PImage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Sketch2 extends PApplet {
	
	PImage imgBackground;
  PImage[] imgBurgerImages = new PImage[5]; //Array will hold four images
  Float[] fltImageX = new Float [5]; // hold X positions of images
  Float[] fltImageY = new Float[5]; // hold y opsitions of the images
  Float[] fltSpeeds = {random(2, 5), random(2,5), random(2,5), 
                       random(2,5), random(2,5)};
  Boolean[] blnImageHide = {false, false, false, false, false};  


  ArrayList<Integer> order = new ArrayList<>();  // ArrayList to hold the correct order of the images
  ArrayList<Integer> caught = new ArrayList<>(); // ArrayList to hold the images caught by the catcher
  
  int intLives = 300;                                 // Number of lives

  int intCatcherX;
  int intSpeed = 3;


  /**
   * Called once at the beginning of execution, put your size all in this method
   */
  public void settings() {
	// put your size call here
    size(800, 600);
  }

  /** 
   * Called once at the beginning of execution.  Add initial set up
   * values here i.e background, stroke, fill etc.
   */
  public void setup() {
    imgBackground = loadImage("Kitchen.png");
    imgBackground.resize(800, 600);

    // Set the initial position of the catcher
    intCatcherX = width / 2;

    imgBurgerImages[0] = loadImage("data/burger bun.png");
    imgBurgerImages[0].resize(75, 75);
    imgBurgerImages[1] = loadImage("data/burger patty.png");
    imgBurgerImages[1].resize(75,75);
    imgBurgerImages[2] = loadImage("data/burger cheese.png");
    imgBurgerImages[2].resize(75, 75);
    imgBurgerImages[3] = loadImage("data/burger lettuce.png");
    imgBurgerImages[3].resize(75, 75);
    imgBurgerImages[4] = loadImage("data/burger sauce.png");
    imgBurgerImages[4].resize(75, 75);

    Arrays.setAll(fltImageX, i -> 0f);
    Arrays.setAll(fltImageY, i -> (float)(imgBurgerImages[i].height) * -1);

    order.addAll(Arrays.asList(0, 1, 2, 3, 4));
/* 
    Collections.shuffle(Arrays.asList(fltImageX));
    Collections.shuffle(Arrays.asList(fltImageY));
    Collections.shuffle(order);
*/

  }

  /**
   * Called repeatedly, anything drawn to the screen goes here
   */
  public void draw() {
    // draw background
    image(imgBackground, 0, 0);

    for (int i = 0; i < fltImageY.length; i++) {
      fltImageY[i] += fltSpeeds[i]; //intSpeed;

      // Reset the position of the image when it reaches the bottom of the screen
      if (fltImageY[i] > height) {
        fltImageY[i] = (float)(imgBurgerImages[i].height) * -1;
        // fltImageX[i] = random(width);

        fltImageX[i] = random(width) + (float)(imgBurgerImages[i].width) * (i + 1);

        blnImageHide[i] = false;

      }

      // Check if the catcher caught the image
      if (fltImageY[i] + imgBurgerImages[i].height >= height) {
        
        if (caught.size() < order.size() && order.get(caught.size()) == i) {
        // if (caught.size() < order.size() && order.get(caught.size()) == i) {
          caught.add(i);
        } else {
          intLives--;
          if (intLives == 0) {
            // gameOver();
          }
        }

        blnImageHide[i] = true;

      }
    }

    for (int i = 0; i < imgBurgerImages.length; i++) {
      image(imgBurgerImages[i], fltImageX[i], fltImageY[i]);
      // delay(3);
    }

    // Draw the catcher with the selected color
    drawCatcher();

    // Draw the lives
    drawLives();

    // Check if the player won
    if (caught.size() == order.size()) {
      // gameWon();
    }

  }

  public void drawCatcher(){
    fill(255, 255, 255);
    stroke(0, 0, 0);

    // Draw the catcher at the bottom center of the screen with the selected color
    float intCatcherWidth = 70;
    float intCatcherHeight = 15;
    float intCatcherY = height - intCatcherHeight - 100;  


    // rect(intCatcherX, 380, 60, 10);
    rect(intCatcherX, intCatcherY, intCatcherWidth, intCatcherHeight);
  }

  public void drawLives() {
    // Draw the remaining lives at the top left of the screen
    String livesText = "Lives: " + intLives;
    float fltLivesX = 10;
    float fltLivesY = 20;

    fill(0);
    textSize(20);
    text(livesText, fltLivesX , fltLivesY);
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
    if (intCatcherX < 0){
        intCatcherX = 0;
    }

    if (intCatcherX > 740){
        intCatcherX = 740;
    }
    
    // if LEFT is pressed, catcher moves to the left
    if (keyCode == LEFT) {
        intCatcherX -= 15;
    }
    // if RIGHT is pressed, catcher moves to the right
    else if (keyCode == RIGHT) {
        intCatcherX += 15;
    }
}
  
  // define other methods down here.
}

