import processing.core.PApplet;
import processing.core.PImage;
import java.util.Arrays;

/**
 * Freddy's Burgeria - Make a Burger Game
 * @author Rachael Chan
 * 
 */
public class Sketch extends PApplet {
    // declaring variables Catching Ingredients (Game 1)
    PImage imgBackground;
    PImage imgBasket;
    PImage[] imgBurgerImages = new PImage[5];
    // hold X positions of images
    Float[] fltImageX = new Float[5]; 
    // hold y positions of the images
    Float[] fltImageY = new Float[5];
    Boolean[] blnImageHide = {false, false, false, false, false}; 
    // array will generate the speeds of the images 
    Float[] fltImageSpeed = {(float)random(3,4), (float)random(3,4),
                        (float)random(3,4), (float)random(3,4),
                        (float)random(3, 4)}; 

    int intCatcherX;
    int intTime1;
    
    // declaring variables for making burger (Game 2)
    PImage imgBackground2;
    PImage imgPlate;
    PImage[] imgBurgerImages2 = new PImage[5]; 
    Boolean[] blnImageHide2 = {false, false, false, false, false}; 
    int intStart = (int) random(0, 4);
    int addNumber = (int) random(2, 4);
    PImage[] imgBox2 = new PImage[5];
    Boolean[] blnBoxHide2 = {true, true, true, true, true}; 
    Integer[] intAnswerArr = {9, 9, 9, 9, 9};
    int intOrder = 0;
    int intBoxShowTimer;
    int intLife = 3;
    PImage imgHeartLife;

    boolean blnFirstStage2 = true;
    PImage imgBackground3;
    PImage imgBurger3;
    int intBurgerX;
    int intBurgerY;

    // declaring variables for the fire out game (game 3)
    PImage imgFireAlert;
    boolean blnFireAlertHide;

    boolean keyAPressed = false;
    boolean keySPressed = false;
    boolean keyDPressed = false;
    boolean keyWPressed = false;

    int intTime3;
    PImage[] imgFire = new PImage [15];
    Boolean[] blnHideFire = {false, false, false, false, false, false, false, false, false, false, false, false, false, false, false};
    Float[] fltFireX = {random(20, 730), random(20, 730), random(20, 730), random(20, 730), random(20, 730), random(20, 730), random(20, 730), random(20, 730), random(20, 730), random(20, 730), random(20, 730), random(20, 730), random(20, 730), random(20, 730), random(20, 730)};
    Float[] fltFireY = {random(20, 480), random(20, 480), random(20, 480), random(20, 480), random(20, 480), random(20, 480), random(20, 480), random(20, 480), random(20, 480), random(20, 480), random(20, 480), random(20, 480), random(20, 480), random(20, 480), random(20, 480)};
    
    boolean blnFireIsOut = false;

    // declaring variables for the instruction images and its controls
    PImage imgGameStart;
    boolean blnGameStartShow = true;
    PImage imgInstructions1;
    boolean blnInstructions1Show = false;
    PImage imgInstructions2;
    boolean blnInstructions2Show = false;
    PImage imgInstructions3;
    boolean blnInstructions3Show = false;
    PImage imgLose1;
    boolean blnLose1Show = false;
    PImage imgLose2;
    boolean blnLose2Show = false;
    PImage imgLose3;
    boolean blnLose3Show = false;
    PImage imgWin;
    boolean blnWinShow = false;
    boolean blnIsDoneInstructions1 = false;
    boolean blnIsDoneInstructions2 = false;
    boolean blnIsDoneInstructions3 = false;
    boolean blnGameStarted = false;


    public void settings() {
        // size call
        size(800, 600);
    }

    public void setup() {
        // load images needed for the first game
        imgBackground = loadImage("Kitchen.png");
        imgBackground.resize(800, 600);
        imgBasket = loadImage("data/basket.png");
        imgBasket.resize(150, 75);

        // set 10 second count down timer
        intTime1 = 600;
        
        // Set the initial position of the catcher
        intCatcherX = width / 2;

        // load images into the imgBurgerImages[] array
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

        // set the initial positions of the images in game 1
        Arrays.setAll(fltImageX, i -> (float)random(width));
        Arrays.setAll(fltImageY, i -> (float)(imgBurgerImages[i].height) * -1);

        // load images for game 2
        imgBackground2 = loadImage("data/game 2 background.jpg");
        imgBackground2.resize(800, 600);
        imgPlate = loadImage("data/plate.png");
        imgPlate.resize(400, 400);
        imgHeartLife = loadImage("data/red heart.png");
        imgHeartLife.resize(30, 40);

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

        // set the variables for randomizing the order of the topping in game 2
        int intRandomNo;
        boolean blnAssign;
     
        for (int intCnt = 0; intCnt <= 4; intCnt++) {
            blnAssign = false;
            intRandomNo = (int)random(0, 5);
    
            while (blnAssign == false) {
                if (intAnswerArr[intRandomNo] == 9) {
                    intAnswerArr[intRandomNo] = intCnt;
                    blnAssign = true;
                }
                else {
                    intRandomNo = (int)random(0, 4);
                }
            } 
        }
    
        for (int intCnt = 0; intCnt < 5; intCnt++) {
            System.out.println(intAnswerArr[intCnt]);
        }

        // load the images for the boxes in game 2
        for(int intCount = 0; intCount < 5; intCount++){
            imgBox2[intCount] = loadImage("data/box2.png");
            imgBox2[intCount].resize(100,100);
        }

        // set 3 intial lives for game 2
        intLife = 3;

        // set the 5 second timer
        intBoxShowTimer = 300;


        // load the images for game 3
        imgBackground3 = loadImage("data/kitchen background 3.png");
        imgBackground3.resize(800, 600);
        imgBurger3 = loadImage("data/burger plate.png");
        imgBurger3.resize(150, 150);
        imgFireAlert = loadImage("data/fire alert 2.png");
        imgFireAlert.resize(600, 450);

        // set initial positions for the burger in game 3
        intBurgerX = 20;
        intBurgerY = 470;

        // set time for the timer in game 3
        intTime3 = 600;

        //set the initial state of the fire alert to false
        blnFireAlertHide = false;

        // load the images for the imgFire[] array
        for(int i = 0; i < 15; i++){
            imgFire[i] = loadImage("data/fire 2.png");
            imgFire[i].resize(120,120);
        }

        // load general instruction and result images for the game
        imgGameStart = loadImage("data/front page.png");
        imgInstructions1 = loadImage("data/game 1 instructions.png");
        imgInstructions2 = loadImage("data/game 2 instructions.png");
        imgInstructions3 = loadImage("data/game 3 instructions.png");
        imgLose1 = loadImage("data/vlose 1.png");
        imgLose1.resize(800,600);
        imgLose2 = loadImage("data/vlose 2.png");
        imgLose2.resize(800,600);
        imgLose3 = loadImage("data/vlose 3.png");
        imgLose3.resize(800,600);
        imgWin = loadImage("data/vwin.png");
        imgWin.resize(800,600);
    }

    

    /**
     * Called repeatedly, anything drawn to the screen goes here
     */
    public void draw() {
        gameRun();
    }

    /**
     * Creates the start of the game with the game start page
     * @param none
     * @return none
     */
    public void gameRun(){
        if(blnGameStartShow == true){
            // show the starting page
            image(imgGameStart, 0, 0);
        }

        if(key == 'b'){
            // if b is pressed, the starting page will no longer show
            blnGameStartShow = false;
            // if b is pressed, the blnInstructions1Show changes to true to control the instructions page in the catchIngredients() method
            blnInstructions1Show = true;
            // if b is pressed, blnGameStarted is set to true to set off the next if statement
            blnGameStarted = true;
        }

        if (blnGameStarted) {
            // if blnGameStarted is true, the catchIngredients() method will be called
            catchIngredients();
        }
    }

    /**
     * Creates the first game where the user has to catch the 5 ingredients of the burger using the arrow keys
     * @param none
     * @return 1 returns if the user has won the game, 2 returns if the user has lost the game, and 0 returns if the game is still going
     */
    public int catchIngredients(){

        if(blnInstructions1Show = true){
            // the instructions for the game will be displayed
            image(imgInstructions1, 0, 0);
        }

        if(key == 'n'){
            // changes the blnINstructions1Show to false in order to not show the instructions page
            blnInstructions1Show = false;
            // changes the blnIsDoneInstructions1 to true to signal instructions are done, and the game will start running
            blnIsDoneInstructions1 = true;
        }

        if(blnIsDoneInstructions1 == true){
            // controls the timer in timer1()
            intTime1 += -1;

            // draw the kitchen table background
            image(imgBackground, 0, 0);

            // for loop loops through all of the images in the imgBurgerImages array
            for(int intCounter = 0; intCounter < imgBurgerImages.length; intCounter++) {

                if (blnImageHide[intCounter] == false){
                    // if the image is not hidden, create the image of a topping from the imgBurgerImages[] array in a location controlled by fltImageX[] and fltImageY[] arrays
                    image(imgBurgerImages[intCounter], fltImageX[intCounter], fltImageY[intCounter]);
                    // makes the image go further down the screen with every loop
                    fltImageY[intCounter] += fltImageSpeed[intCounter]; 
                }

                // if the previous image has not went down the screen by 20 yet, the next image will not be generated yet
                if (fltImageY[intCounter] < 20){
                    break;
                }

                if(fltImageY[intCounter] > 360 && fltImageY[intCounter] < 400){
                    // if the image touches the catcher, the image will be hidden
                    if (abs(fltImageX[intCounter] - (intCatcherX + 40)) <= 75){
                        // hides the image
                        blnImageHide[intCounter] = true;
                    }
                }

                // runs if the image goes off the page in the y-axis
                if (fltImageY[intCounter] > height) {
                    // image is put in the y-axis at 21
                    fltImageY[intCounter] = (float) 21;
                    // a random number is generated for the location of the image in the x-axis
                    fltImageX[intCounter] = random(width);
                    // a random number is generated from 3 - 4 for the speed of the image
                    fltImageSpeed[intCounter] = random(3,4);
                }

                // runs if all the images are hidden
                if(blnImageHide[0] == true && blnImageHide[1] == true && blnImageHide[2] == true && blnImageHide[3] == true && blnImageHide[4] == true){
                    // instructions for the second game makeBurger() is shown
                    image(imgInstructions2, 0, 0);
                    // sets the blnInstructions2Show boolean to true in order to let instructions be shown in the makeBurger() method
                    blnInstructions2Show = true;
                    // calls the makeBurger() method
                    makeBurger();
                    return 1;
                }

                // runs if the time runs out
                if(intTime1 <= 0){
                    // the losing screen is displayed
                    image(imgLose1, 0, 0);
                    return 2;
                }
            }

            // draw the catcher 
            image(imgBasket, intCatcherX, 380);

            // draw the timer to show over the images
            timer1();
        }

        // placeholder return for when the game is going on
        return 0;

    }

    /**
     * Creates the second game where the user has to memorize the order of the ingredients displayed and click on them in order to make the burger
     * @param none
     * @return 1 returns if the user has won the game, 2 returns if the user has lost the game, and 0 returns if the game is still going
     */
    public int makeBurger(){

        if(blnInstructions2Show == true){
            // show instructions for game
            image(imgInstructions2, 0, 0);
        }

        // runs when t is pressed
        if(key == 't'){
            // makes the method stop showing instructions for the game
            blnInstructions2Show = false;
            // changes the blnIsDoneInstructions2 to true to signal instructions are done, and the game will start running
            blnIsDoneInstructions2 = true;
        }

        if(blnIsDoneInstructions2 == true){
            // controls the timer for the box show at the beginning of the game so that each time it loops it goes down by 1
            intBoxShowTimer += -1;

            // draws the kitchen table background
            image(imgBackground2, 0, 0);

            // creates a white rectangle for the timer
            fill(255, 255, 255);
            rect(300, 30, 200, 40);
            
            // creates the text "Time Left: within the white rectangle"
            fill(0, 0, 0);
            text("Time Left: ",315, 60);
                textSize(25);

            // until 1 second has passed, show "5" in the white rectangle
            if(intBoxShowTimer > 240){
                fill(255, 0, 0);
                text("5",445, 60);
                textSize(25);
            // until 2 seconds have passed, show "4" in the white rectangle
            }else if(intBoxShowTimer > 180){
                fill(255, 0, 0);
                text("4",445, 60);
                textSize(25);
            // until 3 seconds have passed, show "3" in the white rectangle
            }else if(intBoxShowTimer > 120){
                fill(255, 0, 0);
                text("3",445, 60);
                textSize(25);
            // until 4 seconds have passed, show "2" in the white rectangle
            }else if(intBoxShowTimer > 60){
                fill(255, 0, 0);
                text("2",445, 60);
                textSize(25);
            // until 5 seconds have passed, show "1" in the white rectangle
            }else if(intBoxShowTimer > 0){
                fill(255, 0, 0);
                text("1",445, 60);
                textSize(25);
            // when 5 seconds have passed, show "Boxes Closed" in the white rectangle
            }else{
                fill(255, 255, 255);
                rect(300, 30, 200, 40);
                fill(0, 0, 0);
                text("Boxes Closed",315, 60);
                textSize(25);
            }
            
            // runs if the timer count is 0
            if(intBoxShowTimer == 0){
                // covers all the images with boxes
                for(int i = 0; i < 5; i++){
                    blnBoxHide2[i] = false;
                }
            }

        
            if (intLife > 2) {
                // image will be generated if there are 3 lives
                image(imgHeartLife, 650, 10);
            }

            if (intLife > 1) {
                // image will be generated if there are 2 lives
                image(imgHeartLife, 700, 10);
            }

            if (intLife > 0) {
                // image will be generated if there is 1 life
                image(imgHeartLife, 750, 10);
            }


            for(int i = 0; i < 5; i++){
                // if image is not hidden, it will be drawn
                if(blnImageHide2[intAnswerArr[i]] == false){
                    image(imgBurgerImages2[intAnswerArr[i]], 70 + 140 * (i), 220);
                    // if box is not hidden, it will be drawn on top of the image
                    if(blnBoxHide2[i] == false){
                        image(imgBox2[intAnswerArr[i]], 70 + 140 * (i), 220);
                    }
                }

                // create plate to display the next topping needed
                image(imgPlate, 200, 300);
                fill(0, 0, 0);
                // display "ITEM NEEDED:"" on the plate
                textSize(25);
                text("NEXT ITEM NEEDED:",285, 460);            

                // displays the next topping needed to be clicked on the plate
                if (intOrder < 5) {
                    image(imgBurgerImages2[intOrder], 355, 480);
                }

                if (mousePressed) {
                    // runs if the user clicks on a box
                    if(dist(mouseX, mouseY, 70 + 140 * (i) + 35, 220) < 90){

                        // runs if the box clicked on is the correct box
                        if ((intOrder == 0 && intAnswerArr[i] == 0) ||
                            (intOrder == 1 && intAnswerArr[i] == 1) ||
                            (intOrder == 2 && intAnswerArr[i] == 2) ||
                            (intOrder == 3 && intAnswerArr[i] == 3) ||
                            (intOrder == 4 && intAnswerArr[i] == 4)) {                        
                            System.out.println(intAnswerArr[i]);
                            // hides the box that is clicked on 
                            blnBoxHide2[i] = true;
                            // increases the count to move onto the next topping
                            intOrder ++;
                            delay(300);
                        }
                        // runs if the box clicked on is the wrong box
                        else if (intOrder != intAnswerArr[i]) {
                                System.out.println("wrong order");
                                System.out.println(intLife);
                                // 1 life is taken away
                                intLife = intLife - 1;
                                delay(300);
                        }

                        // runs if there are no more lives
                        if(intLife == 0){
                            // stops loop from running
                            blnIsDoneInstructions2 = false;
                            // blnLose2Show to true allows for the next if statement to run
                            blnLose2Show = true;
                            return 2;
                        }

                    }
                }

                if(blnLose2Show == true){
                    // lose screen will be shown
                    image(imgLose2, 0, 0);
                }

                // runs if all the boxes are hidden
                if(blnBoxHide2[0] == true && blnBoxHide2[1] == true && blnBoxHide2[2] == true && blnBoxHide2[3] == true && blnBoxHide2[4] == true && intBoxShowTimer < -5){
                    // sets the blnInstructions3Show boolean to true in order to let instructions be shown in the fireOut() method
                    blnInstructions3Show = true;
                    // calls the fireOut() method
                    fireOut();
                    return 1;
                }

            }
        }

        // placeholder return for when the game is still going
        return 0;

    }

    /**
     * Creates the third game where the user has to place the burger in the oven and put out the kitchen fire
     * @param none
     * @return 1 returns if the user has won the game, 2 returns if the user has lost the game, and 0 returns if the game is still going
     */
    public int fireOut(){
        blnInstructions2Show = false;
        if(blnInstructions3Show == true){
            // draw the instructions for the game
            image(imgInstructions3, 0, 0);
        }

        // runs if u is pressed
        if(key == 'u'){
            // stops the instructions from showing
            blnInstructions3Show = false;
            // makes the game start running in the next if statement
            blnIsDoneInstructions3 = true;
        }

        if(blnIsDoneInstructions3 == true){
            // kitchen background is drawn onto the screen
            image(imgBackground3, 0, 0);
            // burger is drawn onto the screen and location is controlled by the user with the wasd keys through intBurgerX and intBurgerY
            image(imgBurger3, intBurgerX, intBurgerY);

            // runs when burger is put into the oven at (345, 259)
            if((intBurgerX == 345) && (intBurgerY == 259)){

                if(blnFireAlertHide == false){
                    // draw the fire alert
                    image(imgFireAlert, 120, 40);
                }


                if(blnFireAlertHide == true){
                    // call the timer3() method which draws the countdown timer to put out the fire
                    timer3();

                    for(int i = 0; i < 15; i++){
                        // runs if the fire is not hidden
                        if(blnHideFire[i] == false){
                            // fire image is drawn to the screen
                            image(imgFire[i], fltFireX[i], fltFireY[i]);
                        }

                        if (mousePressed) {
                            // runs if fire is pressed on
                            if(dist(mouseX, mouseY, fltFireX[i] + 30, fltFireY[i] + 60) < 50){
                                // hides the fire
                                blnHideFire[i] = true;
                            }
                        }

                        // runs if all the fire images are clicked on before the timer runs out
                        if(blnHideFire[0] == true && blnHideFire[1] == true && blnHideFire[2] == true && blnHideFire[3] == true && blnHideFire[4] == true && blnHideFire[5] == true && blnHideFire[6] == true && blnHideFire[7] == true && blnHideFire[8] == true && blnHideFire[9] == true && blnHideFire[10] == true && blnHideFire[11] == true && blnHideFire[12] == true && blnHideFire[13] == true && blnHideFire[14] == true && intTime3 > 0){
                            // clears background
                            image(imgBackground3, 0, 0);
                            image(imgBurger3, intBurgerX, intBurgerY);
                            // sets the blnFireIsOut boolean to true so the win screen can be displayed
                            blnFireIsOut = true;
                        }

                        // runs if time runs out and all fire images are not clicked on 
                        if(intTime3 < 0 && blnFireIsOut == false){
                            // clears background
                            image(imgBackground3, 0, 0);
                            image(imgBurger3, intBurgerX, intBurgerY);
                            // losing screen is displayed
                            image(imgLose3, 0, 0);
                            return 2;
                        }
                    }
                
                }
            }

            // if the burger is close to the oven, it will get moved to the proper position in the oven
            if (abs(intBurgerX - 345) < 35 && abs(intBurgerY - 259) < 35){
                intBurgerX = 345;
                intBurgerY = 259;
            }

            // restricts the burger movement so it cannot go past the left side of the screen
            if(intBurgerX < 0){
                intBurgerX = 0;
            }

            // restricts the movement of the burger so it cannot go past the right side of the screen
            if(intBurgerX > 650){
                intBurgerX = 650;
            }

            // restricts the movement of the burger so it cannot go past the top of the screen
            if(intBurgerY < 0){
                intBurgerY = 0;
            }

            // restricts the movement of the burger so it cannot go past the bottom of the screen
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
        }

        // runs when all fires are put out
        if (blnFireIsOut == true){
            // draws winning screen
            image(imgWin, 0, 0);
            return 1;
        }

        // placeholder return while game is still running
        return 0;
    }


    /**
     * Creates the timer counting down from 10 in the fireOut() game
     * @param none
     * @return none
     */
    public void timer3(){
        // with every loop, intTime3 goes down by 1
        intTime3 += -1;

        // create white rectangle
        fill(255, 255, 255);
        rect(300, 500, 200, 40);
        
        // place text "Time Left:" in black within the white rectangle
        fill(0, 0, 0);
        text("Time Left: ",315, 530);
        textSize(25);
        
        // until 1 second has passed, show "10" in the white rectangle
        if(intTime3 > 540){
            fill(255, 0, 0);
            text("10",443, 530);
            textSize(25);
        // until 2 seconds have passed, show "9" in the white rectangle
        }else if(intTime3 > 480){
            fill(255, 0, 0);
            text("9",445, 530);
            textSize(25);
        // until 3 seconds have passed, show "8" in the white rectangle
        }else if(intTime3 > 420){
            fill(255, 0, 0);
            text("8",445, 530);
            textSize(25);
        // until 4 seconds have passed, show "7" in the white rectangle
        }else if(intTime3 > 360){
            fill(255, 0, 0);
            text("7",445, 530);
            textSize(25);
        // until 5 seconds have passed, show "6" in the white rectangle
        }else if(intTime3 > 300){
            fill(255, 0, 0);
            text("6",445, 530);
            textSize(25);
        // until 6 seconds have passed, show "5" in the white rectangle
        }else if(intTime3 > 240){
            fill(255, 0, 0);
            text("5",445, 530);
            textSize(25);
        // until 7 seconds have passed, show "4" in the white rectangle
        }else if(intTime3 > 180){
            fill(255, 0, 0);
            text("4",445, 530);
            textSize(25);
        // until 8 seconds have passed, show "3" in the white rectangle
        }else if(intTime3 > 120){
            fill(255, 0, 0);
            text("3",445, 530);
            textSize(25);
        // until 9 seconds have passed, show "2" in the white rectangle
        }else if(intTime3 > 60){
            fill(255, 0, 0);
            text("2",445, 530);
            textSize(25);
        // until 10 seconds have passed, show "1" in the white rectangle
        }else if(intTime3 > 0){
            fill(255, 0, 0);
            text("1",445, 530);
            textSize(25);
        // when 10 seconds have passed, show "0" in the white rectangle
        }else{
            fill(255, 0, 0);
            text("0",445, 530);
            textSize(25);
        }
    }

    /**
     * Creates the timer counting down from 10 in the catchIngredients() game
     * @param none
     * @return none
     */
    public void timer1(){
        // create a white rectangle 
        fill(255, 255, 255);
        rect(300, 500, 200, 40);
         
        // place text "Time Left:" in black within the white rectangle
        fill(0, 0, 0);
        text("Time Left: ",315, 530);
        textSize(25);
        
        // until 1 second has passed, show "10" in the white rectangle
        if(intTime1 > 540){
            fill(255, 0, 0);
            text("10",443, 530);
            textSize(25);
        // until 2 seconds have passed, show "9" in the white rectangle
        }else if(intTime1 > 480){
            fill(255, 0, 0);
            text("9",445, 530);
            textSize(25);
        // until 3 seconds have passed, show "8" in the white rectangle
        }else if(intTime1 > 420){
            fill(255, 0, 0);
            text("8",445, 530);
            textSize(25);
        // until 4 seconds have passed, show "7" in the white rectangle
        }else if(intTime1 > 360){
            fill(255, 0, 0);
            text("7",445, 530);
            textSize(25);
        // until 5 seconds have passed, show "6" in the white rectangle
        }else if(intTime1 > 300){
            fill(255, 0, 0);
            text("6",445, 530);
            textSize(25);
        // until 6 seconds have passed, show "5" in the white rectangle
        }else if(intTime1 > 240){
            fill(255, 0, 0);
            text("5",445, 530);
            textSize(25);
        // until 7 seconds have passed, show "4" in the white rectangle
        }else if(intTime1 > 180){
            fill(255, 0, 0);
            text("4",445, 530);
            textSize(25);
        // until 8 seconds have passed, show "3" in the white rectangle
        }else if(intTime1 > 120){
            fill(255, 0, 0);
            text("3",445, 530);
            textSize(25);
        // until 9 seconds have passed, show "2" in the white rectangle
        }else if(intTime1 > 60){
            fill(255, 0, 0);
            text("2",445, 530);
            textSize(25);
        // until 10 seconds have passed, show "1" in the white rectangle
        }else if(intTime1 > 0){
            fill(255, 0, 0);
            text("1",445, 530);
            textSize(25);
        // when 10 seconds have passed, show "0" in the white rectangle
        }else{
            fill(255, 0, 0);
            text("0",445, 530);
            textSize(25);
        }
    }

    /**
     * Detects when a key is pressed in order to control booleans that control direction of the burger and the fire alert in the fireOut() game and the catcher in the catchIngredients() game
     * @param none
     * @return none
     */
    public void keyPressed() {
        // restricts the catcher to not move beyond the left side of the screen
        if (intCatcherX < 0){
            intCatcherX = 0;
        }

        // restricts the catcher to not move beyond the right side of the screen
        if (intCatcherX > 650){
            intCatcherX = 650;
        }
        
        // if LEFT is pressed, the catcher moves to the left
        if (keyCode == LEFT) {
            intCatcherX -= 25;
        }
        // if RIGHT is pressed, the catcher moves to the right
        else if (keyCode == RIGHT) {
            intCatcherX += 25;
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
        // if w is pressed, keyWPressed is set to true
        else if (key == 'w') {
            keyWPressed = true;
        }

        // if f is pressed, the fire alert in the fireOut() game will be hidden
        if(key == 'f'){
            blnFireAlertHide = true;
        }
    }


    /**
     * Detects when the key is released in order to control booleans that control direction of the burger
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
   
}
    
   