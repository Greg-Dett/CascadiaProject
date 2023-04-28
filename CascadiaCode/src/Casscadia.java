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
    static String turnInfo = "player 1";//this is the text that is displayed on the bottom right of the window

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
    int PlayerTurn = 0;//variable that stores which players turn it is
    int turns = 34;// keeps track of how many turns have happened
    int NumPLayers;// number of players in the game.INITIALISED IN InitialPrompt ON LINE 319
    static int animalIndex;
    boolean autoReshuffle=false; //check for an auto cull
    Image endGame=ImageIO.read(Casscadia.class.getResource("cascadiaPNGs/gameover.png")).getScaledInstance(512,650,Image.SCALE_SMOOTH);

    public static void main(String[] args) throws IOException {
        Casscadia hello = new Casscadia(); //new instance of the casscadia class which the whole game runs on
        hello.prePlayPrompt(); //gets data needed to start the game. SEE LINE 319
        hello.Turn();
        hello.play();

        //new JFRAME to run game
    }
    public void play(){
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
            if (turns==NumPLayers*20){
                for (int x=0;x<NumPLayers;x++) {
                    System.out.println(Players.get(x).Name+"'s Points");
                    Players.get(x).getPoints();
                    System.out.println(Players.get(x).surroundingtiles.size());
                    turns++;
                }}
            if (turns>(NumPLayers*20)){
                g.drawImage(endGame,0,0,this);
                frame.repaint();
                return;
            }



            //Displays the 4 tiles and animals tha you can choose from each turn
            for (int i = 0; i < 4; i++) {
                g.drawImage(AnimalCards.get(i).animalImage, 154 + (64 * i), 576, this);
                g.drawImage(HabitatCards.get(i).habitatImage, 154 + (64 * i), 518, this);
                for (int l = 0; l < HabitatCards.get(i).numPossibleAnimals; l++) {
                    g.drawImage(HabitatCards.get(i).possibleAnimals.get(l).possibleImage, 154 + (64 * i) + (15 * l), 518, this);
                }
            }

            //Displays the current board of the current players turn with all of his/her cards
            for (int z = 0; z < Players.get(PlayerTurn).allTiles.size(); z++) {
                g.drawImage(Players.get(PlayerTurn).allTiles.get(z).habitat.habitatImage, Players.get(PlayerTurn).allTiles.get(z).xPosition, Players.get(PlayerTurn).allTiles.get(z).yPosition, this);
                if (Players.get(PlayerTurn).allTiles.get(z).isAnimal == true) {
                    g.drawImage(Players.get(PlayerTurn).allTiles.get(z).animal.animalImage, Players.get(PlayerTurn).allTiles.get(z).xPosition+16, Players.get(PlayerTurn).allTiles.get(z).yPosition+16, this);
                } else {
                    for (int l = 0; l < Players.get(PlayerTurn).allTiles.get(z).habitat.numPossibleAnimals; l++) {
                        g.drawImage(Players.get(PlayerTurn).allTiles.get(z).habitat.possibleAnimals.get(l).possibleImage, Players.get(PlayerTurn).allTiles.get(z).xPosition + (l * 15), Players.get(PlayerTurn).allTiles.get(z).yPosition , this);
                    }
                }
            }



            ReshuffleCheck();
            if (autoReshuffle==true){
                AnimalCards.remove(0);
                AnimalCards.remove(1);
                AnimalCards.remove(2);
                AnimalCards.remove(3);
                Collections.shuffle(AnimalCards);
                autoReshuffle=false;
                frame.repaint();
            }
            //button that can be used if a reshuffle is available otherwise useless
            JButton yes = new JButton("Reshuffle");
            yes.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    if (Boolean.compare(canReshuffle, true) == 0) {
                        Collections.shuffle(AnimalCards);
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
                    if (Players.get(PlayerTurn).wildlifeTokens>0) {
                        useWildlifeToken = true;
                        currentAnimal = null;
                        CurrentText="Please choose an animal";
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
                    if (currentHabitat!=null) {
                        plusRotation();
                        try {
                            currentHabitat.rotateHabitat(currentRotation);
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
            numWildlifeTokens.setText(String.valueOf(Players.get(PlayerTurn).wildlifeTokens));

            add(numWildlifeTokens);
            //text box beside reshuffle button that displays whether you can reshuffle. SEE LINE 47
            JTextField CanReshuffle = new JTextField();
            CanReshuffle.setSize(new Dimension(50, 50));
            CanReshuffle.setBounds(100, 512, 50, 50);
            if (canReshuffle==true){
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

            if (currentHabitat != null && x < 8 && y < 8 && Players.get(PlayerTurn).Board[x][y] == null && Players.get(PlayerTurn).canPlace(x,y)) {//code to place a habitat card
              placeHabitat(x,y);
                frame.repaint();
                currentHabitat = null;
                currentRotation=0;
                CurrentText = ("Please place " + currentAnimal.AnimalName+" or use a wildlife token");
            } else if (currentAnimal != null && x < 8 && y < 8 && Players.get(PlayerTurn).Board[x][y] != null && Players.get(PlayerTurn).Board[x][y].isAnimal == false) { //code to place an animal card
                if (Players.get(PlayerTurn).Board[x][y].habitat.isPossible((currentAnimal)) == true) {

                    placeAnimal(Players.get(PlayerTurn),x,y);
                    frame.repaint();

                } else {
                    CurrentText=("You can't Place "+ currentAnimal.AnimalName+ " here");
                    int possiblecheck=0;
                    for (int i=0;i<Players.get(PlayerTurn).allTiles.size();i++){
                        if (Players.get(PlayerTurn).allTiles.get(i).habitat.isPossible(currentAnimal)==true&&Players.get(PlayerTurn).allTiles.get(i).isAnimal==false) {
                            possiblecheck++;
                        }

                    }
                    if (possiblecheck==0){
                        System.out.println("You couldn't place the animal");
                        currentAnimal = null;
                        plusPlayerTurn();
                    }
                    frame.repaint();
                }

            } else if (currentAnimal == null && useWildlifeToken==false) { //code to select current habitat
                if (e.getX() > 154 && e.getX() < 216 && e.getY() > 518 && e.getY() < 580) {
                    currentHabitat = HabitatCards.get(0);
                    animalIndex = 0;
                    CurrentText = (currentHabitat.getHabitatName() + " is selected, Please click on a tile to place or select another habitat");


                    frame.repaint();
                } else if (e.getX() > 218 && e.getX() < 280 && e.getY() > 518 && e.getY() < 580) {
                    currentHabitat = HabitatCards.get(1);
                    animalIndex = 1;
                    CurrentText = (currentHabitat.getHabitatName() + " is selected, Please click on a tile to place or select another habitat");
                    frame.repaint();
                } else if (e.getX() > 282 && e.getX() < 348 && e.getY() > 518 && e.getY() < 580) {
                    currentHabitat = HabitatCards.get(2);
                    animalIndex = 2;
                    CurrentText = (currentHabitat.getHabitatName() + " is selected, Please click on a tile to place or select another habitat");
                    frame.repaint();
                } else if (e.getX() > 350 && e.getX() < 412 && e.getY() > 518 && e.getY() < 580) {
                    currentHabitat = HabitatCards.get(3);
                    animalIndex = 3;
                    CurrentText = (currentHabitat.getHabitatName() + " is selected, Please click on a tile to place or select another habitat");
                    frame.repaint();
                }
            }
            else if (currentHabitat == null && useWildlifeToken==true) {// code when a wildlife token is used
                if (e.getX() > 154 && e.getX() < 186 && e.getY() > 576 && e.getY() < 608) {
                    currentAnimal = AnimalCards.get(0);
                    animalIndex = 0;
                    CurrentText = (currentAnimal.getAnimalName() + " is selected");
                    //if current habitat is not keystone
                    //button
                    //button rotates image 90 degrees
                    frame.repaint();
                } else if (e.getX() > 218 && e.getX() < 250 && e.getY() > 576 && e.getY() < 608) {
                    currentAnimal = AnimalCards.get(1);
                    animalIndex = 1;
                    CurrentText = (currentAnimal.getAnimalName() + " is selected");
                    frame.repaint();
                } else if (e.getX() > 282 && e.getX() < 304 && e.getY() > 576 && e.getY() < 608) {
                    currentAnimal = AnimalCards.get(2);
                    animalIndex = 2;
                    CurrentText = (currentAnimal.getAnimalName() + " is selected");
                    frame.repaint();
                } else if (e.getX() > 350 && e.getX() < 382 && e.getY() > 576 && e.getY() < 608) {
                    currentAnimal = AnimalCards.get(3);
                    animalIndex = 3;
                    CurrentText = (currentAnimal.getAnimalName() + " is selected");
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


    public void prePlayPrompt() throws IOException {   //Game Set-up

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

    public void placeAnimal(Player playerToMove,int x,int y) {
        playerToMove.Board[x][y].addAnimal(this.currentAnimal);
        for (int z = 0; z < playerToMove.allTiles.size(); z++) {
            if (playerToMove.allTiles.get(z).X == x && playerToMove.allTiles.get(z).Y == y) {
                playerToMove.allTiles.get(z).addAnimal(this.currentAnimal);
                playerToMove.allTiles.get(z).isAnimal = true;
            }
        }
        if (playerToMove.Board[x][y].habitat.habitat2 == Habitatselect.none) {
            playerToMove.wildlifeTokens++;
        }
        this.HabitatCards.remove(animalIndex);
        this.AnimalCards.remove(animalIndex);
        if (this.useWildlifeToken == true) {
            playerToMove.wildlifeTokens--;
            this.useWildlifeToken = false;
        }

        this.currentAnimal = null;

        this.plusPlayerTurn();

    }
    public void placeHabitat(int x,int y){
        BoardTile newtile = null;
        try {
            newtile = new BoardTile(this.currentHabitat, x, y,this.currentRotation);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        this.Players.get(PlayerTurn).Board[x][y] = newtile;
        this.Players.get(PlayerTurn).allTiles.add(newtile);
        this.currentAnimal = this.AnimalCards.get(animalIndex);
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
        if (PlayerTurn%2==0) {
            Collections.shuffle(this.HabitatCards);
            Collections.shuffle(this.AnimalCards);
        }
            this.CurrentText = "Select a Habitat and Animal or place a habitat and use a wildlife token to choose an animal";
            turns++;

    }
    public void plusRotation() {//code increments current rotation variable and resets to 0 once the number 4 is reached as the cards can only be rotated 4 times(360 degrees)
        if (this.currentRotation < 3) {
            this.currentRotation++;
        } else {
            currentRotation = 0;
        }

    }
    public void Bot(){







    }
    public Point corridorStrategy(Player currentPlayer, Habitat currentHabitat) throws IOException {
        int maxPoints=0;
        int x=0,y=0;
        for (int i=0;i<8;i++){
            for (int l=0;l<8;l++) {
                if (currentPlayer.canPlace(i,l)){
                for (int m=0;m<4;m++){
                    BoardTile temp=new BoardTile(currentHabitat,i,l,m);
                    currentPlayer.Board[i][l] = temp;
                    currentPlayer.allTiles.add(temp);
                    if (currentPlayer.HabitatPoints()>maxPoints){
                        maxPoints=currentPlayer.HabitatPoints();
                        x=i;
                        y=l;
                    }
                }
                currentPlayer.removeTile(i,l);
                }
            }
        }
        Point temp=new Point(x,y,maxPoints);
        return temp;
    }
    public int DisruptiveStrategy(Player targetPlayer) throws IOException {
        int habitatNumber=0;
        int maxPoints=0;
        for (int i=0;i<4;i++) {
              if (corridorStrategy(targetPlayer,HabitatCards.get(i)).Points>maxPoints){
                  maxPoints=corridorStrategy(targetPlayer,HabitatCards.get(i)).Points;
                  habitatNumber=i;
              }
        }
        return habitatNumber;
    }
    public Point wildlifePlacementStrategy(Player currentPlayer,Animal currentAnimal){
        int maxPoints=0;
        int x=0,y=0;
        for (int i=0;i<currentPlayer.allTiles.size();i++){
            if (currentPlayer.allTiles.get(i).habitat.isPossible(currentAnimal)){
                currentPlayer.findTile(currentPlayer.allTiles.get(i).X,currentPlayer.allTiles.get(i).Y).animal=currentAnimal;

                if ( maxPoints<currentPlayer.getAnimalPoints()) {
                    maxPoints = currentPlayer.getAnimalPoints();
                    x = currentPlayer.allTiles.get(i).X;
                    y = currentPlayer.allTiles.get(i).Y;
                }
                currentPlayer.allTiles.get(i).removeAnimal();
            }
        }
        Point temp=new Point(x,y,maxPoints);
        return temp;

    }


}

