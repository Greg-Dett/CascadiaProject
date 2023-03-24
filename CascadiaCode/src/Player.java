import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Player {

    public Player(String Name) throws IOException {
        this.Name=Name;
        Random rand = new Random();
        this.startNum = rand.nextInt(4);
        allTiles.add(startingTile.up);
        allTiles.add(startingTile.left);
        allTiles.add(startingTile.right);
        Board[3][3]=startingTile.up;
        Board[3][4]=startingTile.left;
        Board[4][4]=startingTile.right;

    }
    String Name;
    int startNum;
    StarterTIle startingTile=new StarterTIle(startNum);
    ArrayList<Animal> CurrentAnimalCards= new ArrayList<Animal>();
    ArrayList<Habitat> CurrentHabitatCards= new ArrayList<Habitat>();
    BoardTile[][] Board=new BoardTile[8][8];
    ArrayList<BoardTile> allTiles=new ArrayList<BoardTile>();
    int wildlifeTokens=1;

    public String getName(){
        return this.Name;
    }
    public boolean canPlace(int X,int Y){

        if (Y%2==0){
            if (Board[X-1][Y]==null&&Board[X+1][Y]==null&&Board[X][Y-1]==null&&Board[X-1][Y-1]==null&&Board[X-1][Y+1]==null&&Board[X][Y+1]==null){
                return false;
            }
            else {return true;}
        }
        else{
            if (Board[X-1][Y]==null&&Board[X+1][Y]==null&&Board[X][Y-1]==null&&Board[X+1][Y-1]==null&&Board[X+1][Y+1]==null&&Board[X][Y+1]==null){
                return false;
            }
            else {return true;}

        }
    }
}
