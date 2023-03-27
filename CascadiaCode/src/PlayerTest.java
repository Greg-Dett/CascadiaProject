import static org.junit.jupiter.api.Assertions.*;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    @org.junit.jupiter.api.Test
    void bearpointsA() throws IOException {
        Player TestPlayer=new Player("test");
        Habitat testhabitat=new Habitat(Casscadia.Habitatselect.prairie, Casscadia.Habitatselect.none);
        Animal testBear=new Animal(Casscadia.Animalselect.Bear);

        BoardTile test1=new BoardTile(testhabitat,3,2,0);
        test1.animal=testBear;
        BoardTile test2=new BoardTile(testhabitat,4,2,0);
        test2.animal=testBear;
        TestPlayer.Board[3][2]=test1;
        TestPlayer.Board[4][2]=test2;
        TestPlayer.allTiles.add(test1);
        TestPlayer.allTiles.add(test2);
        assertEquals(TestPlayer.BearpointsA(),4);
    }

    @org.junit.jupiter.api.Test
    void foxPoints2() throws IOException {
        Player TestPlayer=new Player("test");
        Habitat testhabitat=new Habitat(Casscadia.Habitatselect.prairie, Casscadia.Habitatselect.none);

        Animal testBear=new Animal(Casscadia.Animalselect.Bear);
        Animal testelk = new Animal(Casscadia.Animalselect.Elk);
        Animal testhawk = new Animal(Casscadia.Animalselect.Hawk);
        Animal testfox = new Animal(Casscadia.Animalselect.Fox);
        TestPlayer.Board[3][3].animal=testfox;
        TestPlayer.Board[3][3].isAnimal=true;

        BoardTile test1=new BoardTile(testhabitat,3,2,0);
        test1.animal=testBear;
        BoardTile test2=new BoardTile(testhabitat,4,2,0);
        test2.animal=testelk;
        TestPlayer.Board[3][2]=test1;
        TestPlayer.Board[4][2]=test2;
        TestPlayer.allTiles.add(test1);
        TestPlayer.allTiles.add(test2);
        assertEquals(2,TestPlayer.FoxPointsA());
    }
    @org.junit.jupiter.api.Test
    void foxPoints0() throws IOException {
        Player TestPlayer=new Player("test");
        Animal testfox = new Animal(Casscadia.Animalselect.Fox);
        TestPlayer.Board[3][3].animal=testfox;
        TestPlayer.Board[3][3].isAnimal=true;

        assertEquals(0,TestPlayer.FoxPointsA());
    }

    @org.junit.jupiter.api.Test
    void elkPoints2() throws IOException {
        Player TestPlayer1 = new Player("test");
        Habitat testhabitat = new Habitat(Casscadia.Habitatselect.prairie, Casscadia.Habitatselect.none);
        Animal testelk = new Animal(Casscadia.Animalselect.Elk);

        BoardTile test1 = new BoardTile(testhabitat, 3, 2, 0);
        test1.animal = testelk;
        test1.isAnimal = true;
        TestPlayer1.Board[3][2] = test1;
        TestPlayer1.allTiles.add(test1);
        assertEquals(2,TestPlayer1.ElkPointsA());
        ;
    }

    @org.junit.jupiter.api.Test
    void elkPoints5() throws IOException {
        Player TestPlayer1=new Player("test");
        Habitat testhabitat=new Habitat(Casscadia.Habitatselect.prairie, Casscadia.Habitatselect.none);
        Animal testelk=new Animal(Casscadia.Animalselect.Elk);

        BoardTile test1=new BoardTile(testhabitat,3,2,0);
        test1.animal=testelk;
        test1.isAnimal=true;
        BoardTile test2=new BoardTile(testhabitat,4,2,0);
        test2.animal=testelk;
        test2.isAnimal=true;
        TestPlayer1.Board[3][2]=test1;
        TestPlayer1.Board[4][2]=test2;
        TestPlayer1.allTiles.add(test1);
        TestPlayer1.allTiles.add(test2);
        assertEquals(5,TestPlayer1.ElkPointsA());
    }
    @org.junit.jupiter.api.Test
    void elkPoints9() throws IOException {
        Player TestPlayer1=new Player("test");
        Habitat testhabitat=new Habitat(Casscadia.Habitatselect.prairie, Casscadia.Habitatselect.none);
        Animal testelk=new Animal(Casscadia.Animalselect.Elk);

        BoardTile test1=new BoardTile(testhabitat,3,2,0);
        test1.animal=testelk;
        test1.isAnimal=true;
        BoardTile test2=new BoardTile(testhabitat,4,2,0);
        test2.animal=testelk;
        test2.isAnimal=true;
        BoardTile test3=new BoardTile(testhabitat,5,2,0);
        test3.animal=testelk;
        test3.isAnimal=true;
        TestPlayer1.Board[3][2]=test1;
        TestPlayer1.Board[4][2]=test2;
        TestPlayer1.Board[5][2]=test3;
        TestPlayer1.allTiles.add(test1);
        TestPlayer1.allTiles.add(test2);
        TestPlayer1.allTiles.add(test3);
        assertEquals(9,TestPlayer1.ElkPointsA());
    }
    @org.junit.jupiter.api.Test
    void elkPoints13() throws IOException {
        Player TestPlayer1=new Player("test");
        Habitat testhabitat=new Habitat(Casscadia.Habitatselect.prairie, Casscadia.Habitatselect.none);
        Animal testelk=new Animal(Casscadia.Animalselect.Elk);

        BoardTile test1=new BoardTile(testhabitat,3,2,0);
        test1.animal=testelk;
        test1.isAnimal=true;
        BoardTile test2=new BoardTile(testhabitat,4,2,0);
        test2.animal=testelk;
        test2.isAnimal=true;
        BoardTile test3=new BoardTile(testhabitat,5,2,0);
        test3.animal=testelk;
        test3.isAnimal=true;
        BoardTile test4=new BoardTile(testhabitat,6,2,0);
        test4.animal=testelk;
        test4.isAnimal=true;
        TestPlayer1.Board[3][2]=test1;
        TestPlayer1.Board[4][2]=test2;
        TestPlayer1.Board[5][2]=test3;
        TestPlayer1.Board[6][2]=test3;
        TestPlayer1.allTiles.add(test1);
        TestPlayer1.allTiles.add(test2);
        TestPlayer1.allTiles.add(test3);
        TestPlayer1.allTiles.add(test4);
        assertEquals(9,TestPlayer1.ElkPointsA());
    }

    @org.junit.jupiter.api.Test
    void hawkpoints2() throws IOException {
        Player TestPlayer1 = new Player("test");
        Habitat testhabitat = new Habitat(Casscadia.Habitatselect.prairie, Casscadia.Habitatselect.none);
        Animal testhawk = new Animal(Casscadia.Animalselect.Hawk);

        BoardTile test1 = new BoardTile(testhabitat, 3, 2, 0);
        test1.animal = testhawk;
        test1.isAnimal = true;
        TestPlayer1.Board[3][2] = test1;
        TestPlayer1.allTiles.add(test1);
        assertEquals(2,TestPlayer1.HawkpointsA());

    }
    @org.junit.jupiter.api.Test
    void hawkpoints5() throws IOException {
        Player TestPlayer1 = new Player("test");
        Habitat testhabitat = new Habitat(Casscadia.Habitatselect.prairie, Casscadia.Habitatselect.none);
        Animal testhawk = new Animal(Casscadia.Animalselect.Hawk);

        BoardTile test1 = new BoardTile(testhabitat, 3, 2, 0);
        test1.animal = testhawk;
        test1.isAnimal = true;
        TestPlayer1.Board[3][2] = test1;
        TestPlayer1.allTiles.add(test1);
        BoardTile test2 = new BoardTile(testhabitat, 2, 5, 0);
        test2.animal = testhawk;
        test2.isAnimal = true;
        TestPlayer1.Board[2][5] = test2;
        TestPlayer1.allTiles.add(test2);
        assertEquals(5,TestPlayer1.HawkpointsA());

    }
    @org.junit.jupiter.api.Test
    void hawkpoints8() throws IOException {
        Player TestPlayer1 = new Player("test");
        Habitat testhabitat = new Habitat(Casscadia.Habitatselect.prairie, Casscadia.Habitatselect.none);
        Animal testhawk = new Animal(Casscadia.Animalselect.Hawk);

        BoardTile test1 = new BoardTile(testhabitat, 3, 2, 0);
        test1.animal = testhawk;
        test1.isAnimal = true;
        TestPlayer1.Board[3][2] = test1;
        TestPlayer1.allTiles.add(test1);
        BoardTile test2 = new BoardTile(testhabitat, 2, 5, 0);
        test2.animal = testhawk;
        test2.isAnimal = true;
        TestPlayer1.Board[2][5] = test2;
        TestPlayer1.allTiles.add(test2);
        BoardTile test3 = new BoardTile(testhabitat, 4, 5, 0);
        test3.animal = testhawk;
        test3.isAnimal = true;
        TestPlayer1.Board[4][5] = test3;
        TestPlayer1.allTiles.add(test3);
        assertEquals(8,TestPlayer1.HawkpointsA());

    }
}