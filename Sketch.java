import processing.core.PApplet;
import processing.core.PImage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Sketch extends PApplet {
    // declaring variables for Catching Ingredients (Game 1)
    PImage imgBackground;
    PImage imgBasket;
    PImage[] imgBurgerImages = new PImage[5]; //Array will hold five images
    Float[] fltImageX = new Float [5]; // hold X positions of images
    Float[] fltImageY = new Float[5]; // hold y opsitions of the images
    Boolean[] blnImageHide = {false, false, false, false, false};  
    Float[] fltImageSpeed = {(float)random(3,4), (float)random(3,4),
                        (float)random(3,4), (float)random(3,4),
                        (float)random(3, 4)};
    

    ArrayList<Integer> order = new ArrayList<>();  // ArrayList to hold the correct order of the images
    ArrayList<Integer> caught = new ArrayList<>(); // ArrayList to hold the images caught by the catcher

  
    int intCatcherX;
    int intTime1;
    
    // declaring variables for making burger (Game 2)
    PImage imgBackground2;
    PImage imgPlate;
    PImage[] imgBurgerImages2 = new PImage[5]; // Array will hold five images`
    Boolean[] blnImageHide2 = {false, false, false, false, false}; 
    String strPlateList;
    int intStart = (int) random(0, 4);
    int addNumber = (int) random(2, 4);
    PImage[] imgBox2 = new PImage[5];
    Boolean[] blnBoxHide2 = {false, false, false, false, false}; 

    public void settings() {
        // size call
        size(800, 600);
    }

    public void setup() {
        imgBackground = loadImage("Kitchen.png");
        imgBackground.resize(800, 600);
        imgBasket = loadImage("data/basket.png");
        imgBasket.resize(150, 75);
        intTime1 = 1000;
        
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

        Arrays.setAll(fltImageX, i -> (float)random(width));
        Arrays.setAll(fltImageY, i -> (float)(imgBurgerImages[i].height) * -1);

        imgBackground2 = loadImage("data/game 2 background.jpg");
        imgBackground2.resize(800, 600);
        imgPlate = loadImage("data/plate.png");
        imgPlate.resize(400, 400);

        imgBurgerImages2[0] = loadImage("data/burger bun.png");
        imgBurgerImages2[0].resize(80, 80);
        imgBurgerImages2[1] = loadImage("data/burger patty.png");
        imgBurgerImages2[1].resize(100,100);
        imgBurgerImages2[2] = loadImage("data/burger cheese.png");
        imgBurgerImages2[2].resize(100, 100);
        imgBurgerImages2[3] = loadImage("data/burger lettuce.png");
        imgBurgerImages2[3].resize(100, 100);
        imgBurgerImages2[4] = loadImage("data/burger sauce.png");
        imgBurgerImages2[4].resize(80, 80);

        for(int i = 0; i < 5; i++){
            imgBox2[i] = loadImage("data/box2.png");
            imgBox2[i].resize(100,100);
        }
    
    }

    

    /**
     * Called repeatedly, anything drawn to the screen goes here
     */
    public void draw() {
        makeBurger();
        //System.out.println(makeBurger());
        //System.out.println(catchIngredients());
    }

    public int makeBurger(){
        image(imgBackground2, 0, 0);
        for(int i = 0; i < 5; i++){
            if(blnImageHide2[i] == false){
                image(imgBurgerImages2[intStart], 70 + 140 * (i), 220);
                if(blnBoxHide2[i] == false){
                    image(imgBox2[intStart], 70 + 140 * (i), 220);
                }
                intStart += addNumber;
                if (intStart > 4){
                    intStart = intStart - 5;
                }
            }

            if (mousePressed) {
                if(dist(mouseX, mouseY, 70+140*(intStart) + 35, 220) < 90){
                    blnBoxHide2[intStart] = true;
                    System.out.print(intStart);    
              }
            }
        }

        image (imgPlate, 200, 300);
        

        fill(0, 0, 0);
        textSize(25);
        text("ITEM NEEDED:",315, 480);
        return 0;
    }

    public int catchIngredients(){

        intTime1 += -1;

        // draw background
        image(imgBackground, 0, 0);


        for(int i = 0; i < imgBurgerImages.length; i++) {

            if (blnImageHide[i] == false){
                image(imgBurgerImages[i], fltImageX[i], fltImageY[i]);
                fltImageY[i] += fltImageSpeed[i]; 
            }

            if (fltImageY[i] < 20){
                break;
            }

            if(fltImageY[i] > 360 && fltImageY[i] < 400){
                if (abs(fltImageX[i] - (intCatcherX+40)) <= 75){
                    blnImageHide[i] = true;
                }
            }

            if (fltImageY[i] > height) {
                fltImageY[i] = (float) 21;
                fltImageX[i] = random(width);
                fltImageSpeed[i] = random(3,4);
            }

            if(blnImageHide[0] == true && blnImageHide[1] == true && blnImageHide[2] == true && blnImageHide[3] == true && blnImageHide[4] == true){
                return 1;
            }

            if(intTime1 <= 0){
                for (i = 0; i < imgBurgerImages.length; i++){
                    blnImageHide[i] = true;
                }
                return 2;
            }
        }

        // Draw the catcher 
        image(imgBasket, intCatcherX, 380);
        // draw the timer to show over the images
        timer1();

        return 0;

    }

    public void timer1(){
        fill(255, 255, 255);
        rect(300, 500, 200, 40);
            
        fill(0, 0, 0);
        text("Time Left: ",315, 530);
            textSize(25);
        
        if(intTime1 > 900){
            fill(255, 0, 0);
            text("10",443, 530);
            textSize(25);
        }else if(intTime1 > 800){
            fill(255, 0, 0);
            text("9",445, 530);
            textSize(25);
        }else if(intTime1 > 700){
            fill(255, 0, 0);
            text("8",445, 530);
            textSize(25);
        }else if(intTime1 > 600){
            fill(255, 0, 0);
            text("7",445, 530);
            textSize(25);
        }else if(intTime1 > 500){
            fill(255, 0, 0);
            text("6",445, 530);
            textSize(25);
        }else if(intTime1 > 400){
            fill(255, 0, 0);
            text("5",445, 530);
            textSize(25);
        }else if(intTime1 > 300){
            fill(255, 0, 0);
            text("4",445, 530);
            textSize(25);
        }else if(intTime1 > 200){
            fill(255, 0, 0);
            text("3",445, 530);
            textSize(25);
        }else if(intTime1 > 100){
            fill(255, 0, 0);
            text("2",445, 530);
            textSize(25);
        }else if(intTime1 > 0){
            fill(255, 0, 0);
            text("1",445, 530);
            textSize(25);
        }else{
            fill(255, 0, 0);
            text("0",445, 530);
            textSize(25);
        }
    }


    public void keyPressed() {
        if (intCatcherX < 0){
            intCatcherX = 0;
        }

        if (intCatcherX > 650){
            intCatcherX = 650;
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
    
   