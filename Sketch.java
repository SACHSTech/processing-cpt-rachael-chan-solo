import processing.core.PApplet;
import processing.core.PImage;
import java.util.ArrayList;
import java.util.Arrays;
// import java.util.Collections;

public class Sketch extends PApplet {
    // declaring variables for Catching Ingredients (Game 1)
    PImage imgBackground;
    PImage imgBasket;
    PImage[] imgBurgerImages = new PImage[5]; //Array will hold five images
    Float[] fltImageX = new Float[5]; // hold X positions of images
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
    Boolean[] blnBoxHide2 = {true, true, true, true, true}; 
    Integer[] intAnswerArr = {9, 9, 9, 9, 9};

    boolean blnFirstStage2 = true;
    PImage imgBackground3;
    PImage imgBurger3;
    int intBurgerX;
    int intBurgerY;
    PImage imgFireAlert;
    boolean blnFireAlertHide = false;

    boolean keyAPressed = false;
    boolean keySPressed = false;
    boolean keyDPressed = false;
    boolean keyWPressed = false;

    int intTime3;
    int intBoxShowTimer;
    


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


        int intRandomNo;
        // Integer[] intAnswerArr = {9, 9, 9, 9, 9};
    
        boolean blnAssign;
     
        for (int iCnt = 0; iCnt <= 4; iCnt++) {
            blnAssign = false;
            intRandomNo = (int)random(0, 5);
    
            while (blnAssign == false) {
                if (intAnswerArr[intRandomNo] == 9) {
                    intAnswerArr[intRandomNo] = iCnt;
                    blnAssign = true;
                }
                else {
                    intRandomNo = (int)random(0, 4);
                }
            } 
        }
    
        for (int iCnt = 0; iCnt < 5; iCnt++) {
            System.out.println(intAnswerArr[iCnt]);
        }


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

        
        imgBackground3 = loadImage("data/kitchen background 3.png");
        imgBackground3.resize(800, 600);
        imgBurger3 = loadImage("data/burger plate.png");
        imgBurger3.resize(150, 150);
        intBurgerX = 20;
        intBurgerY = 470;
        intTime3 = 1000;
        imgFireAlert = loadImage("data/fire alert.png");
        imgFireAlert.resize(600, 450);
        intBoxShowTimer = 300;
    }

    

    /**
     * Called repeatedly, anything drawn to the screen goes here
     */
    public void draw() {
        makeBurger();
        // fireOut();
        //System.out.println(makeBurger());
        //System.out.println(catchIngredients());
    }

    public int fireOut(){

        image(imgBackground3, 0, 0);
        image(imgBurger3, intBurgerX, intBurgerY);
        System.out.println(intBurgerX + " " + intBurgerY);

        if (mousePressed){
            System.out.println(mouseX);
            System.out.println(mouseY);
            // blnFireAlertHide = true;
        }


        if((intBurgerX == 345) && (intBurgerY == 259)){
            //display the "Oh no, the kitchen caught on fire! Click on the fire to put them out"


            while(blnFireAlertHide == false){
                // System.out.println(blnFireAlertHide);
                image(imgFireAlert, 0, 0);
            }

            if (mousePressed){
                System.out.println(mouseX);
                System.out.println(mouseY);
                blnFireAlertHide = true;
            }

            while(blnFireAlertHide == true){
                System.out.println("timer start");
                timer3();
            }
        }

        if (abs(intBurgerX - 345) < 35 && abs(intBurgerY - 259) < 35){
            intBurgerX = 345;
            intBurgerY = 259;
        }

        if(intBurgerX < 0){
            intBurgerX = 0;
        }

        if(intBurgerX > 650){
            intBurgerX = 650;
        }

        if(intBurgerY < 0){
            intBurgerY = 0;
        }

        if(intBurgerY > 450){
            intBurgerY = 450;
        }

        // if keyAPressed is true, the burger goes left
        if (keyAPressed) {
            intBurgerX--;
        }

        // if keyDPressed is true, the burger goes right
        if (keyDPressed) {
            intBurgerX++;
        }

        // if keyWPressed is true, the burger goes up
        if (keyWPressed) {
            intBurgerY--;
        }

        // if keySPressed is true, the burger goes down
        if (keySPressed) {
            intBurgerY++;
        }

        
        return 0;
    }

    public int makeBurger(){

        intBoxShowTimer += -1;

        if(intBoxShowTimer == 0){
            for(int i = 0; i < 5; i++){
                blnBoxHide2[i] = false;
            }
        }

        image(imgBackground2, 0, 0);


        for(int i = 0; i < 5; i++){

            if(blnImageHide2[intAnswerArr[i]] == false){
                image(imgBurgerImages2[intAnswerArr[i]], 70 + 140 * (i), 220);
                if(blnBoxHide2[i] == false){
                    image(imgBox2[intAnswerArr[i]], 70 + 140 * (i), 220);
                }
            }

            if (mousePressed) {
                if(dist(mouseX, mouseY, 70 + 140 * (i) + 35, 220) < 90){
                    blnBoxHide2[i] = true;
                    while(blnBoxHide2[i] = true){
                        if (intAnswerArr[i] == 0){
                            //image(imgPlate, 200, 300);
                            //fill(0, 0, 0);
                            //textSize(25);
                        // text("ITEM NEEDED:",315, 480);
                            text("I'm here!", 20, 20);
                            System.out.println("boxpressed");
                    }
                    //System.out.print(intAnswerArr[i]); 
                    System.out.print(i);
                    System.out.print(blnBoxHide2[i]);
                }
            }

            if (intAnswerArr[i] == 0){
                //image(imgPlate, 200, 300);
                //fill(0, 0, 0);
                //textSize(25);
            // text("ITEM NEEDED:",315, 480);
                text("I'm here!", 20, 20);
                System.out.println("boxpressed");
            } 

        }
        
        

        /*while(intBoxShowTimer <= 0){
            if (blnBoxHide2[0] == true){
                //image(imgPlate, 200, 300);
                //fill(0, 0, 0);
                //textSize(25);
            // text("ITEM NEEDED:",315, 480);
                text("I'm here!", 20, 20);
                System.out.println("boxpressed");
            } 
        }
         */

        


        image(imgPlate, 200, 300);
        fill(0, 0, 0);
        textSize(25);
        text("ITEM NEEDED:",315, 480);
  

        


/*         
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
                if(dist(mouseX, mouseY, 70 + 140 * (intStart) + 35, 220) < 90){
                    blnBoxHide2[intStart] = true;
                    System.out.print(intStart);    
                }
            }
            
           // int intAnswer = intStart;

        }
*/
        /*int intOrder[] = new int[5];
        for(int i = 0; i < 5; i++){
            intOrder[]
        }
        */
    }
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

    public void timer3(){

        intTime3 += -1;
        fill(255, 255, 255);
        rect(300, 500, 200, 40);
            
        fill(0, 0, 0);
        text("Time Left: ",315, 530);
            textSize(25);
        
        if(intTime3 > 900){
            fill(255, 0, 0);
            text("10",443, 530);
            textSize(25);
        }else if(intTime3 > 800){
            fill(255, 0, 0);
            text("9",445, 530);
            textSize(25);
        }else if(intTime3 > 700){
            fill(255, 0, 0);
            text("8",445, 530);
            textSize(25);
        }else if(intTime3 > 600){
            fill(255, 0, 0);
            text("7",445, 530);
            textSize(25);
        }else if(intTime3 > 500){
            fill(255, 0, 0);
            text("6",445, 530);
            textSize(25);
        }else if(intTime3 > 400){
            fill(255, 0, 0);
            text("5",445, 530);
            textSize(25);
        }else if(intTime3 > 300){
            fill(255, 0, 0);
            text("4",445, 530);
            textSize(25);
        }else if(intTime3 > 200){
            fill(255, 0, 0);
            text("3",445, 530);
            textSize(25);
        }else if(intTime3 > 100){
            fill(255, 0, 0);
            text("2",445, 530);
            textSize(25);
        }else if(intTime3 > 0){
            fill(255, 0, 0);
            text("1",445, 530);
            textSize(25);
        }else{
            fill(255, 0, 0);
            text("0",445, 530);
            textSize(25);
        }
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

        // if a is pressed, keyAPressed is set to true
        if (key == 'a') {
            keyAPressed = true;
        }
        // if d is pressed, keyDPressed is set to true
        else if (key == 'd') {
            keyDPressed = true;
        }
        // if s is pressed, keySPressed is set to true
        else if (key == 's') {
            keySPressed = true;
        }
        // if W is pressed, keyWPressed is set to true
        else if (key == 'w') {
            keyWPressed = true;
        }
    }


    /**
     * Detects when the key is released in order to control booleans that control direction of the blue ball and the speed of snowflakes
     * @param none
     * @return none
     */
    public void keyReleased() {
        // sets boolean keyAPressed to false if a is released
        if (key == 'a') {
            keyAPressed = false;
        }
        // sets boolean keyDPressed to false if d is released
        else if (key == 'd') {
            keyDPressed = false;
        }
        // sets boolean keySPressed to false if s if released
        else if (key == 's') {
            keySPressed = false;
        }
        // sets boolean keyWPressed to false if w if released
        else if (key == 'w') {
            keyWPressed = false;
        }
    }
      
      // define other methods down here.
    
    
    
}
    
   