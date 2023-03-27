/*
 *  Student number 21400044 -        Thomas Reynolds  -         gitID Reynolds21
 *  Student number 21305806 -        Greg Dettling    -         gitID Greg-Dett
 *  Student number 21463472 -        Ganto Badammoyun -         gitID Gantobadan
 * */

import javax.imageio.ImageIO;
import javax.management.StringValueExp;
import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;
import java.io.*;
import java.awt.*;
import java.awt.geom.*;


public class Casscadia implements ActionListener {


    public int NumhabitatTiles; //used to store number of habitat tiles that will be used depending on number of players//

    public Casscadia() throws IOException {
    }

    static String CurrentText = "Click any of the 4 habitats above to begin"; //this is the text that is displayed at the bottom of the window. SEE LINE 163
    static String turnInfo = "hello player 1";//this is the text that is displayed on the bottom right of the window

    @Override
    public void actionPerformed(ActionEvent e) {

    }


    enum Habitatselect {forest, wetland, river, mountain, prairie,none} // used when implementing habitat class. SEE HABITAT.JAVA//

    enum Animalselect {Hawk, Bear, Elk, Salmon, Fox, none} // used when implementing animal class. SEE ANIMAL.JAVA//

    ArrayList<Player> Players = new ArrayList<Player>(); //this is a list of the players playing the game
    ArrayList<Animal> AnimalCards = new ArrayList<Animal>();// this is the pot of animal cards from which the player selects one each rounds
    ArrayList<Habitat> HabitatCards = new ArrayList<Habitat>();// this is the pot of habitat cards from which the player selects one each rounds
    Habitat currentHabitat = null; //this is the current habitat the user has in his hand.
    Animal currentAnimal = null;//this is the current animal the user has in his hand.
    boolean useWildlifeToken=false;// boolean used when the user wants to use a wildlife token. SEE LINE 123
    boolean canReshuffle = false; // boolean value to check if a reshuffle is possible
    int currentRotation=0; //this variable is used when a tile is being rotated. SEE rotateHabitat ON LINE 157 IN HABITAT.JAVA
    int PlayerTurn = 1;//variable that stores which players turn it is
    int turns = 30;// keeps track of how many turns have happened
    int NumPLayers;// number of players in the game.INITIALISED IN InitialPrompt ON LINE 319
    static int animalIndex;
    boolean autoReshuffle=false; //check for an auto cull
    Image endGame=ImageIO.read(Casscadia.class.getResource("cascadiaPNGs/gameover.png")).getScaledInstance(512,650,Image.SCALE_SMOOTH);

    public static void main(String[] args) throws IOException {
        Casscadia hello = new Casscadia(); //new instance of the casscadia class which the whole game runs on
        hello.Initialprompt(); //gets data needed to start the game. SEE LINE 319
        hello.Turn();

        //new JFRAME to run game
        JFrame frame = new JFrame();
        frame.setBounds(10, 10, 512, 650);
        frame.setUndecorated(true);
        frame.setLocationRelativeTo(null);  //sets jframe to middle of screen
        JPanel pn = new JPanel() {


            @Override
            public void paint(Graphics g) {
                //draws square grid/board
                for (int y = 0; y < 8; y++) {
                    for (int x = 0; x < 8; x++) {

                        if (y % 2 != 0) {
                            g.drawRect(x * 64 + 32, y * 64, 64, 64);
                        } else {
                            g.drawRect(x * 64, y * 64, 64, 64);
                        }

                    }
                }
                //ends game after 40 turns
                if (hello.turns==hello.NumPLayers*20){
                    for (int x=0;x<hello.NumPLayers;x++) {
                        System.out.println(hello.Players.get(x).Name+"'s Points");
                        hello.Players.get(x).getPoints();
                        hello.turns++;
                    }}
                if (hello.turns>(hello.NumPLayers*20)){
                    g.drawImage(hello.endGame,0,0,this);
                    frame.repaint();
                    return;
                }



                //Displays the 4 tiles and animals tha you can choose from each turn
                for (int i = 0; i < 4; i++) {
                    g.drawImage(hello.AnimalCards.get(i).animalImage, 154 + (64 * i), 576, this);
                    g.drawImage(hello.HabitatCards.get(i).habitatImage, 154 + (64 * i), 518, this);
                    for (int l = 0; l < hello.HabitatCards.get(i).numPossibleAnimals; l++) {
                        g.drawImage(hello.HabitatCards.get(i).possibleAnimals.get(l).possibleImage, 154 + (64 * i) + (15 * l), 518, this);
                    }
                }

                //Displays the current board of the current players turn with all of his/her cards
                for (int z = 0; z < hello.Players.get(hello.PlayerTurn).allTiles.size(); z++) {
                    g.drawImage(hello.Players.get(hello.PlayerTurn).allTiles.get(z).habitat.habitatImage, hello.Players.get(hello.PlayerTurn).allTiles.get(z).xPosition, hello.Players.get(hello.PlayerTurn).allTiles.get(z).yPosition, this);
                    if (hello.Players.get(hello.PlayerTurn).allTiles.get(z).isAnimal == true) {
                        g.drawImage(hello.Players.get(hello.PlayerTurn).allTiles.get(z).animal.animalImage, hello.Players.get(hello.PlayerTurn).allTiles.get(z).xPosition+16, hello.Players.get(hello.PlayerTurn).allTiles.get(z).yPosition+16, this);
                    } else {
                        for (int l = 0; l < hello.Players.get(hello.PlayerTurn).allTiles.get(z).habitat.numPossibleAnimals; l++) {
                            g.drawImage(hello.Players.get(hello.PlayerTurn).allTiles.get(z).habitat.possibleAnimals.get(l).possibleImage, hello.Players.get(hello.PlayerTurn).allTiles.get(z).xPosition + (l * 15), hello.Players.get(hello.PlayerTurn).allTiles.get(z).yPosition , this);
                        }
                    }
                }


                hello.ReshuffleCheck();
                if (hello.autoReshuffle==true){
                    hello.AnimalCards.remove(0);
                    hello.AnimalCards.remove(1);
                    hello.AnimalCards.remove(2);
                    hello.AnimalCards.remove(3);
                    Collections.shuffle(hello.AnimalCards);
                    hello.autoReshuffle=false;
                    frame.repaint();
                }
                //button that can be used if a reshuffle is available otherwise useless
                JButton yes = new JButton("Reshuffle");
                yes.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (Boolean.compare(hello.canReshuffle, true) == 0) {
                            Collections.shuffle(hello.AnimalCards);
                            frame.repaint();
                        }
                    }
                });
                yes.setBounds(0, 512, 100, 50);
                frame.add(yes);

                //button that can be used to use a wildlife token if the current player has more than 0 wildlife tokens
                JButton wildlife_token = new JButton("Wildlife Token");
                wildlife_token.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (hello.Players.get(hello.PlayerTurn).wildlifeTokens>0) {
                            hello.useWildlifeToken = true;
                            hello.currentAnimal = null;
                            hello.CurrentText="Please choose an animal";
                            frame.repaint();
                        }
                    }
                });
                wildlife_token.setBounds(0, 562, 100, 50);
                frame.add(wildlife_token);

                //button used to rotate the tile that is currently selected otherwise of no tile is currently selected it will do nothing
                JButton rotate = new JButton("Rotate");
                rotate.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (hello.currentHabitat!=null) {
                            hello.plusRotation();
                            try {
                                hello.currentHabitat.rotateHabitat(hello.currentRotation);
                            } catch (IOException ex) {
                                throw new RuntimeException(ex);
                            }
                            frame.repaint();
                        }
                    }
                });
                rotate.setBounds(410, 562, 100, 50);
                frame.add(rotate);

                //text box at the bottom of the window that displays what is in the CurrentText variable. SEE LINE 28
                JTextField textbox = new JTextField();
                textbox.setSize(new Dimension(512, 38));
                textbox.setBounds(0, 612, 512, 38);
                textbox.setText(CurrentText);

                add(textbox);
                //text box at the bottom right of the window that displays what is in the turnInfo variable. SEE LINE 29
                JTextField turnInfoBox = new JTextField();
                turnInfoBox.setSize(new Dimension(102, 50));
                turnInfoBox.setBounds(410, 512, 102, 50);
                turnInfoBox.setText(turnInfo);

                add(turnInfoBox);
                //text box beside ethe wildlife token button that displays number of wildlife tokens the current player has. SEE LINE 26 IN PLAYER.JAVA
                JTextField numWildlifeTokens = new JTextField();
                numWildlifeTokens.setSize(new Dimension(50, 50));
                numWildlifeTokens.setBounds(100, 562, 50, 50);
                numWildlifeTokens.setText(String.valueOf(hello.Players.get(hello.PlayerTurn).wildlifeTokens));

                add(numWildlifeTokens);
                //text box beside reshuffle button that displays whether you can reshuffle. SEE LINE 47
                JTextField CanReshuffle = new JTextField();
                CanReshuffle.setSize(new Dimension(50, 50));
                CanReshuffle.setBounds(100, 512, 50, 50);
                if (hello.canReshuffle==true){
                    CanReshuffle.setText("Yes");
                }
                else {CanReshuffle.setText("No");}
                add(CanReshuffle);
            }
        };

        frame.add(pn);
        frame.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {//code that controls what happens when the mouse if pressed at different locations in the window
                int x;  //used to find x co-ordinate of the tile you have clicked on the grid
                int y = Math.floorDiv((e.getY()), 64);//used to find y co-ordinate of the tile you have clicked on the grid
                if (e.getX()<32){
                    x=9;
                }
                else if (y%2!=0){
                    x = Math.floorDiv(e.getX()-32, 64);
                }
                else{x = Math.floorDiv(e.getX(), 64);}

                if (hello.currentHabitat != null && x < 8 && y < 8 && hello.Players.get(hello.PlayerTurn).Board[x][y] == null && hello.Players.get(hello.PlayerTurn).canPlace(x,y)) {//code to place a habitat card
                    BoardTile newtile = null;
                    try {
                        newtile = new BoardTile(hello.currentHabitat, x, y,hello.currentRotation);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    hello.Players.get(hello.PlayerTurn).Board[x][y] = newtile;
                    hello.Players.get(hello.PlayerTurn).allTiles.add(newtile);
                    hello.currentAnimal = hello.AnimalCards.get(animalIndex);
                    frame.repaint();
                    hello.currentHabitat = null;
                    hello.currentRotation=0;
                    hello.CurrentText = ("Please place " + hello.currentAnimal.AnimalName+" or use a wildlife token");
                } else if (hello.currentAnimal != null && x < 8 && y < 8 && hello.Players.get(hello.PlayerTurn).Board[x][y] != null && hello.Players.get(hello.PlayerTurn).Board[x][y].isAnimal == false) { //code to place an animal card
                    if (hello.Players.get(hello.PlayerTurn).Board[x][y].habitat.isPossible((hello.currentAnimal)) == true) {
                        hello.Players.get(hello.PlayerTurn).Board[x][y].addAnimal(hello.currentAnimal);
                        for (int z = 0; z < hello.Players.get(hello.PlayerTurn).allTiles.size(); z++) {
                            if (hello.Players.get(hello.PlayerTurn).allTiles.get(z).X == x && hello.Players.get(hello.PlayerTurn).allTiles.get(z).Y == y) {
                                hello.Players.get(hello.PlayerTurn).allTiles.get(z).addAnimal(hello.currentAnimal);
                                hello.Players.get(hello.PlayerTurn).allTiles.get(z).isAnimal = true;
                            }
                        }
                        if (hello.Players.get(hello.PlayerTurn).Board[x][y].habitat.habitat2==Habitatselect.none){
                            hello.Players.get(hello.PlayerTurn).wildlifeTokens++;
                        }
                        hello.HabitatCards.remove(animalIndex);
                        hello.AnimalCards.remove(animalIndex);
                        if (hello.useWildlifeToken==true){
                            hello.Players.get(hello.PlayerTurn).wildlifeTokens--;
                            hello.useWildlifeToken=false;
                        }
                        frame.repaint();
                        hello.currentAnimal = null;

                        hello.plusPlayerTurn();

                    } else {
                        hello.CurrentText=("You can't Place "+ hello.currentAnimal.AnimalName+ " here");
                        int possiblecheck=0;
                        for (int i=0;i<hello.Players.get(hello.PlayerTurn).allTiles.size();i++){
                            if (hello.Players.get(hello.PlayerTurn).allTiles.get(i).habitat.isPossible(hello.currentAnimal)==true&&hello.Players.get(hello.PlayerTurn).allTiles.get(i).isAnimal==false) {
                                possiblecheck++;
                            }

                        }
                        if (possiblecheck==0){
                            System.out.println("You couldn't place the animal");
                            hello.currentAnimal = null;
                            hello.plusPlayerTurn();
                        }
                        frame.repaint();
                    }

                } else if (hello.currentAnimal == null && hello.useWildlifeToken==false) { //code to select current habitat
                    if (e.getX() > 154 && e.getX() < 216 && e.getY() > 518 && e.getY() < 580) {
                        hello.currentHabitat = hello.HabitatCards.get(0);
                        animalIndex = 0;
                        hello.CurrentText = (hello.currentHabitat.getHabitatName() + " is selected, Please click on a tile to place or select another habitat");


                        frame.repaint();
                    } else if (e.getX() > 218 && e.getX() < 280 && e.getY() > 518 && e.getY() < 580) {
                        hello.currentHabitat = hello.HabitatCards.get(1);
                        animalIndex = 1;
                        hello.CurrentText = (hello.currentHabitat.getHabitatName() + " is selected, Please click on a tile to place or select another habitat");
                        frame.repaint();
                    } else if (e.getX() > 282 && e.getX() < 348 && e.getY() > 518 && e.getY() < 580) {
                        hello.currentHabitat = hello.HabitatCards.get(2);
                        animalIndex = 2;
                        hello.CurrentText = (hello.currentHabitat.getHabitatName() + " is selected, Please click on a tile to place or select another habitat");
                        frame.repaint();
                    } else if (e.getX() > 350 && e.getX() < 412 && e.getY() > 518 && e.getY() < 580) {
                        hello.currentHabitat = hello.HabitatCards.get(3);
                        animalIndex = 3;
                        hello.CurrentText = (hello.currentHabitat.getHabitatName() + " is selected, Please click on a tile to place or select another habitat");
                        frame.repaint();
                    }
                }
                else if (hello.currentHabitat == null && hello.useWildlifeToken==true) {// code when a wildlife token is used
                    if (e.getX() > 154 && e.getX() < 186 && e.getY() > 576 && e.getY() < 608) {
                        hello.currentAnimal = hello.AnimalCards.get(0);
                        animalIndex = 0;
                        hello.CurrentText = (hello.currentAnimal.getAnimalName() + " is selected");
                        //if current habitat is not keystone
                        //button
                        //button rotates image 90 degrees
                        frame.repaint();
                    } else if (e.getX() > 218 && e.getX() < 250 && e.getY() > 576 && e.getY() < 608) {
                        hello.currentAnimal = hello.AnimalCards.get(1);
                        animalIndex = 1;
                        hello.CurrentText = (hello.currentAnimal.getAnimalName() + " is selected");
                        frame.repaint();
                    } else if (e.getX() > 282 && e.getX() < 304 && e.getY() > 576 && e.getY() < 608) {
                        hello.currentAnimal = hello.AnimalCards.get(2);
                        animalIndex = 2;
                        hello.CurrentText = (hello.currentAnimal.getAnimalName() + " is selected");
                        frame.repaint();
                    } else if (e.getX() > 350 && e.getX() < 382 && e.getY() > 576 && e.getY() < 608) {
                        hello.currentAnimal = hello.AnimalCards.get(3);
                        animalIndex = 3;
                        hello.CurrentText = (hello.currentAnimal.getAnimalName() + " is selected");
                        frame.repaint();
                    }

                }


            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        frame.setVisible(true);

    }


    public void Initialprompt() throws IOException {   //Game Set-up

        System.out.println("Enter number of players (2-4): ");
        Scanner NumPlayersScanner = new Scanner(System.in);  // Create a Scanner object
        NumPLayers=NumPlayersScanner.nextInt();
        if (NumPLayers < 2 || NumPLayers > 4) {
            throw new IllegalArgumentException("Incorrect number of users");
        }
        switch (NumPLayers) {//stores number of habitat tiles neccessary, have not implemented use of this variable yet. SEE LINE 89//
            case 2:
                NumhabitatTiles = 43;
            case 3:
                NumhabitatTiles = 63;
            case 4:
                NumhabitatTiles = 83;
        }

        CreateTiles();

        for (int i = 0; i < NumPLayers; i++) {
            Scanner PlayerNameScanner = new Scanner(System.in);  // Create a Scanner object
            System.out.println("Enter the name for player " + (i + 1) + ":");//User inputs Name
            String userName = PlayerNameScanner.nextLine();
            Players.add(new Player(userName));
            System.out.println("Player " + (i + 1) + "'s name is: " + userName + "\n");  // Output user input
        }

        Collections.shuffle(Players);
        System.out.println("Order of Players:");
        for (int i = 0; i < NumPLayers; i++) {
            System.out.println(Players.get(i).getName());//Shows order of PLayers
        }
        Random rand = new Random();
        int scoring = rand.nextInt(3);
        System.out.println("SCORING RULES");
        System.out.println("For Bear Scoring, score points based on the total number of pairs of bears.\n" +
                "Once a pair is scored, all the bears adjacent to the bears in this pair must be\n" +
                "excluded from further scoring.\n");
        System.out.println("For Fox Scoring,Score points for the number of unique wildlifes (including\n" +
                "a fox) adjacent to it. Find the score in the table using the number as input. Each fox is\n" +
                "independently scored.\n");
        System.out.println("or Elk Scoring, score points for each straight line of adjacent elk. Lines can have\n" +
                "any direction."+"Any elk part of a line that is already scored is removed from further scoring\n");
        System.out.println("For Hawk Scoring, you score points for the total number of Hawks with \n" +
                " no hawks adjacent to them\n");
        System.out.println("For Salmon Scoring, score points for runs of salmon. In a run, a Salmon is adjacent to\n" +
                "no more than two Salmon.\n");

    }

    public void CreateTiles() throws IOException {          //Creates Array lists fo animal and habitat Tiles needed to play Casscadia
        for (int i = 0; i < 20; i++) {//adding animal tiles to list
            AnimalCards.add(new Animal(Animalselect.Hawk));
            AnimalCards.add(new Animal(Animalselect.Bear));
            AnimalCards.add(new Animal(Animalselect.Elk));
            AnimalCards.add(new Animal(Animalselect.Salmon));
            AnimalCards.add(new Animal(Animalselect.Fox));
        }
        for (int l = 0; l < 5; l++) {//adding keystone tiles to list//
            HabitatCards.add(new Habitat(Habitatselect.forest, Habitatselect.none));
            HabitatCards.add(new Habitat(Habitatselect.wetland, Habitatselect.none));
            HabitatCards.add(new Habitat(Habitatselect.river, Habitatselect.none));
            HabitatCards.add(new Habitat(Habitatselect.mountain, Habitatselect.none));
            HabitatCards.add(new Habitat(Habitatselect.prairie, Habitatselect.none));
        }
        int m=0;
        while (m<NumhabitatTiles-25){//creates random habitat tiles with more than 1 habitat on them
            Random rand = new Random();
            int selectingHabitat1 = rand.nextInt(4);
            int selectingHabitat2 = rand.nextInt(4);
            if (selectingHabitat1!=selectingHabitat2){
                Habitat newpick = new Habitat(Casscadia.Habitatselect.values()[selectingHabitat1],Casscadia.Habitatselect.values()[selectingHabitat2]);
                HabitatCards.add(newpick);
                m++;
            }
        }


    }

    public void Turn() {//code that at he beginning of the game which shuffles cards and checks if a reshuffle is available
        Collections.shuffle(HabitatCards);
        int animalReshuffleCheck = 0;
        Collections.shuffle(AnimalCards);
        for (int l = 0; l < 4; l++) {
            animalReshuffleCheck = 0;
            for (int i = 0; i < 2; i++) {
                if (AnimalCards.get(l).getAnimalName().equals(AnimalCards.get(i).AnimalName)) {
                    animalReshuffleCheck++;     //checks how many of each animal appear each turn
                }

            }
            if (animalReshuffleCheck == 4 || animalReshuffleCheck == 3) {
                canReshuffle = true;
            }
        }

    }

    public void ReshuffleCheck() {
        int animalReshuffleCheck = 0;
        for (int l = 0; l < 2; l++) {
            animalReshuffleCheck = 0;
            for (int i = 0; i < 4; i++) {
                if (AnimalCards.get(l).getAnimalName().equals(AnimalCards.get(i).AnimalName)) {
                    animalReshuffleCheck++;     //checks how many of each animal appear each turn
                }

            }
            if (animalReshuffleCheck == 4) {
                System.out.println("Animals automatically culled");
                this.autoReshuffle=true;
            } else if (animalReshuffleCheck == 3) {
                canReshuffle=true;
            } else {
                canReshuffle=false;
            }
        }

    }

    public void plusPlayerTurn() { //shuffles cards and moves on to the next player after every turn
        if (this.PlayerTurn < NumPLayers - 1) {
            this.PlayerTurn++;
        } else {
            PlayerTurn = 0;
        }
        if (turns==38||turns==39){
            this.turnInfo = (this.Players.get(this.PlayerTurn).getName() + "'s final turn");
        }
        else {
            if (turns< this.NumPLayers){
                this.turnInfo = (this.Players.get(this.PlayerTurn).getName() + "'s first turn");}
            else {
                this.turnInfo = (this.Players.get(this.PlayerTurn).getName() + "'s turn");
            }
        }
        Collections.shuffle(this.HabitatCards);
        Collections.shuffle(this.AnimalCards);
        this.CurrentText="Select a Habitat and Animal or place a habitat and use a wildlife token to choose an animal";
        turns++;
    }
    public void plusRotation() {//code increments current rotation variable and resets to 0 once the number 4 is reached as the cards can only be rotated 4 times(360 degrees)
        if (this.currentRotation < 3) {
            this.currentRotation++;
        } else {
            currentRotation = 0;
        }

    }}
