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
    int startNum;//number to choose which starter tile you get
    StarterTIle startingTile=new StarterTIle(startNum);// players starting tile
    BoardTile[][] Board=new BoardTile[8][8]; //board with tiles on it
    ArrayList<BoardTile> allTiles=new ArrayList<BoardTile>(); //all tiles the user has placed
    int wildlifeTokens=1;

    public String getName(){
        return this.Name;
    }
    public boolean canPlace(int X,int Y){ //multiple if statements that check surrounding tiles of a tile to see if it can be placed
        if (X==7&&Y==7) {
            if (Board[X - 1][Y] == null && Board[X][Y - 1] == null) {
                return false;
            }
            else {
                return true;
            }
        }
        else if (X==0&&Y==7) {
            if (Board[X + 1][Y] == null && Board[X][Y - 1] == null&& Board[X+1][Y - 1] == null) {
                return false;
            }
            else {
                return true;
            }
        }
        else if (X==0&&Y==0) {
            if (Board[X + 1][Y] == null && Board[X][Y + 1] == null) {
                return false;
            }
            else {
                return true;
            }
        }
        else if (X==7&&Y==0) {
            if (Board[X - 1][Y] == null && Board[X][Y + 1] == null && Board[X+-1][Y + 1] == null) {
                return false;
            }
            else {
                return true;
            }
        }
        else if (Y==0) {
            if (Board[X - 1][Y] == null && Board[X+1][Y] == null && Board[X][Y + 1] == null && Board[X-1][Y + 1] == null) {
                return false;
            }
            else {
                return true;
            }
        }
        else if (Y==7) {
            if (Board[X - 1][Y] == null && Board[X+1][Y] == null && Board[X][Y-1] == null && Board[X+1][Y-1] == null) {
                return false;
            }
            else {
                return true;
            }
        }
        else if (X==0) {
            if (Y % 2 == 0) {
                if (Board[X + 1][Y] == null && Board[X][Y - 1] == null && Board[X - 1][Y - 1] == null && Board[X - 1][Y + 1] == null && Board[X][Y + 1] == null) {
                    return false;
                } else {
                    return true;
                }
            }
            else {  if (Board[X + 1][Y] == null && Board[X][Y - 1] == null && Board[X][Y + 1] == null) {
                return false;
            } else {
                return true;
            }}
        }
        else if (X==7) {
            if (Y % 2 == 0) {
                if (Board[X][Y - 1] == null && Board[X - 1][Y - 1] == null && Board[X - 1][Y + 1] == null && Board[X][Y + 1] == null) {
                    return false;
                } else {
                    return true;
                }
            }
            else {  if (Board[X - 1][Y] == null && Board[X][Y - 1] == null && Board[X][Y + 1] == null) {
                return false;
            } else {
                return true;
            }}
        }
        else if (Y%2==0){
            if (Board[X-1][Y-1]==null&&Board[X-1][Y]==null&&Board[X-1][Y+1]==null&&Board[X][Y-1]==null&&Board[X][Y+1]==null){
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



    public ArrayList<BoardTile> checkPair(BoardTile tileToCheck) {
        int X=tileToCheck.X;
        int Y=tileToCheck.Y;
        ArrayList<BoardTile> occuredTiles=new ArrayList<BoardTile>();
        int originalX = X;
        int originalY = Y;
        if (Board[X][Y]==null||Board[X][Y].animal==null){
            return null;
        }
        if (Y % 2 == 0) {
            Y = Y - 1;
            X = X - 1;
            for (int i = 0; i < 3; i++) {
                Y += i;
                for (int l = 0; l < 2; l++) {
                    X += l;
                    if (X < 0 || X > 8 || Y < 0 || Y > 8) {}
                    else if (Board[X][Y]==null||Board[X][Y].animal==null){}
                    else if (X == originalX && Y == originalY) {
                        if (Board[X + 1][Y]==null||Board[X+1][Y].animal==null){}
                        else if (Board[originalX][originalY].animal.AnimalName == Board[X + 1][Y].animal.AnimalName) {
                            occuredTiles.add(findTile(X+1,Y));
                        }
                    } else if (Board[originalX][originalY].animal.AnimalName == Board[X][Y].animal.AnimalName) {
                        occuredTiles.add(findTile(X,Y));
                    }
                    X=originalX-1;
                }
                Y=originalY-1;
            }

        } else {
            Y = Y - 1;
            for (int i = 0; i < 3; i++) {
                Y += i;
                for (int l = 0; l < 2; l++) {
                    X += l;
                    if (X < 0 || X > 8 || Y < 0 || Y > 8) {
                    }
                    else if (Board[X][Y]==null||Board[X][Y].animal==null) {}
                    else if (X == originalX && Y == originalY) {
                        if (Board[X - 1][Y]==null||Board[X-1][Y].animal==null){}
                        else if (Board[originalX][originalY].animal.AnimalName == Board[X - 1][Y].animal.AnimalName) {
                            occuredTiles.add(findTile(X-1,Y));
                        }
                    } else if (Board[originalX][originalY].animal.AnimalName == Board[X][Y].animal.AnimalName) {
                        occuredTiles.add(findTile(X,Y));
                    }
                    X=originalX;
                }
                Y=originalY-1;
            }
        }
        return occuredTiles;
    }
    public BoardTile findTile(int x,int y){
        for (int i=0;i<allTiles.size();i++){
            if (allTiles.get(i).X==x&&allTiles.get(i).Y==y){
                return allTiles.get(i);
            }
        }
        return null;
    }

    public int linechecker(int x,int y)
    {
        Animal animal=Board[x][y].animal;
        ArrayList<BoardTile> longestline=new ArrayList<>();
        ArrayList<BoardTile> tempLine=new ArrayList<>();

        int numRight=0,numLeft=0,numUpLeft=0,numUpRight=0,numDownLeft=0,numDownRight=0;

        if (Board[x+1][y]==null||Board[x+1][y].isAnimal==false)
        {
        }
        else if(Board[x+1][y].animal.AnimalName==animal.AnimalName)
        {
            for (int i = 0; i < 8 - x; i++)
            {
                if (Board[x + i][y]==null||Board[x + i][y].isAnimal==false||Board[x + i][y].animal.AnimalName != animal.AnimalName)
                {
                    break;
                }
                else {numRight++;
                    longestline.add(Board[x+i][y]);}
            }
        }
        if (Board[x-1][y]==null||Board[x-1][y].isAnimal==false)
        {
        }
        else if (Board[x-1][y].animal.AnimalName==animal.AnimalName)
        {
            for (int i = 0; i < x; i++)
            {
                if (Board[x - i][y]==null||Board[x - i][y].isAnimal==false||Board[x - i][y].animal.AnimalName != animal.AnimalName)
                {
                    break;
                }
                else {numLeft++;
                    tempLine.add(Board[x-1][i]);}
            }
            if (tempLine.size()>longestline.size())
            {
                longestline.clear();
                longestline.addAll(tempLine);
                tempLine.clear();
            }
        }
        if (y%2==0)
        {
            if (Board[x][y-1]==null||Board[x][y-1].isAnimal==false)
            {
            }
            else if (Board[x][y-1].isAnimal==false)
            {
            }
            else if(Board[x][y-1].animal.AnimalName==animal.AnimalName)
            {
                int l = x, i = y;
                while (l < 8 && i >= 0) {
                    if (Board[l][i]==null||Board[l][i].isAnimal==false||Board[l][i].animal.AnimalName != animal.AnimalName)
                    {
                        break;
                    }
                    else if (Board[l][i].animal.AnimalName == animal.AnimalName)
                    {
                        numUpRight++;
                        tempLine.add(Board[l][i]);
                    }
                    if (Board[l][i-1]==null||Board[l][i-1].isAnimal==false||Board[l][i-1].animal.AnimalName != animal.AnimalName)
                    {
                        break;
                    }
                    else if (Board[l][i - 1].animal.AnimalName == animal.AnimalName)
                    {
                        numUpRight++;
                        tempLine.add(Board[l][i-1]);
                    }
                    i -= 2;
                    l++;
                }
                if (tempLine.size()>longestline.size())
                {
                    longestline.clear();
                    longestline.addAll(tempLine);
                    tempLine.clear();
                }
            }
            if (Board[x-1][y-1]==null)
            {
            }
            else if (Board[x-1][y-1].isAnimal==false)
            {
            }
            else if(Board[x-1][y-1].animal.AnimalName==animal.AnimalName)
            {
                numUpLeft++;
                tempLine.add(Board[x][y]);
                int l = x-1, i = y-1;
                while (l > 0 && i >= 0)
                {
                    if (Board[l][i]==null||Board[l][i].isAnimal==false||Board[l][i].animal.AnimalName != animal.AnimalName)
                    {
                        break;
                    }
                    else if (Board[l][i].animal.AnimalName == animal.AnimalName)
                    {
                        numUpLeft++;
                        tempLine.add(Board[l][i]);
                    }
                    if (Board[l][i-1]==null||Board[l][i-1].isAnimal==false||Board[l][i-1].animal.AnimalName != animal.AnimalName)
                    {
                        break;
                    }
                    else if (Board[l][i - 1].animal.AnimalName == animal.AnimalName)
                    {
                        numUpLeft++;
                        tempLine.add(Board[l][i-1]);
                    }
                    i -= 2;
                    l--;
                }
                if (tempLine.size()>longestline.size())
                {
                    longestline.clear();
                    longestline.addAll(tempLine);
                    tempLine.clear();
                }
            }
            if (Board[x][y+1]==null)
            {
            }
            else if (Board[x][y+1].isAnimal==false)
            {
            }
            else if(Board[x][y+1].animal.AnimalName==animal.AnimalName)
            {
                int l = x, i = y;
                while (l < 8 && i < 8)
                {
                    if (Board[l][i]==null||Board[l][i].isAnimal==false||Board[l][i].animal.AnimalName != animal.AnimalName)
                    {
                        break;
                    }
                    else if (Board[l][i].animal.AnimalName == animal.AnimalName)
                    {
                        numDownRight++;
                        tempLine.add(Board[l][i]);
                    }
                    if (Board[l][i+1]==null||Board[l][i+1].isAnimal==false||Board[l][i+1].animal.AnimalName != animal.AnimalName)
                    {
                        break;
                    }
                    else if (Board[l][i + 1].animal.AnimalName == animal.AnimalName)
                    {
                        numDownRight++;
                        tempLine.add(Board[l][i+1]);
                    }
                    i += 2;
                    l++;
                }
                if (tempLine.size()>longestline.size())
                {
                    longestline.clear();
                    longestline.addAll(tempLine);
                    tempLine.clear();
                }
            }
            if (Board[x-1][y+1]==null)
            {
            }
            else if (Board[x-1][y+1].isAnimal==false)
            {
            }
            else if(Board[x-1][y+1].animal.AnimalName==animal.AnimalName)
            {
                numDownLeft++;
                tempLine.add(Board[x][y]);
                int l = x-1, i = y+1;
                while (l > 0 && i < 8)
                {
                    if (Board[l][i]==null||Board[l][i].isAnimal==false||Board[l][i].animal.AnimalName != animal.AnimalName)
                    {
                        break;
                    }
                    else if (Board[l][i].animal.AnimalName == animal.AnimalName)
                    {
                        numDownLeft++;
                        tempLine.add(Board[l][i]);
                    }
                    if (Board[l][i+1]==null||Board[l][i+1].isAnimal==false||Board[l][i+1].animal.AnimalName != animal.AnimalName)
                    {
                        break;
                    }
                    else if (Board[l][i + 1].animal.AnimalName == animal.AnimalName)
                    {
                        numDownLeft++;
                        tempLine.add(Board[l][i+1]);
                    }
                    i += 2;
                    l--;
                }
            }
            if (tempLine.size()>longestline.size())
            {
                longestline.clear();
                longestline.addAll(tempLine);
                tempLine.clear();
            }
        }
        else if (y%2!=0)
        {
            if (Board[x+1][y-1]==null)
            {
            }
            else if (Board[x+1][y-1].isAnimal==false)
            {
            }
            else if (Board[x+1][y-1].animal.AnimalName==animal.AnimalName)
            {
                int l = x+1, i = y-1;
                while (l < 8 && i >= 0)
                {
                    if (Board[l][i]==null||Board[l][i].isAnimal==false||Board[l][i].animal.AnimalName != animal.AnimalName)
                    {
                        break;
                    }
                    else if (Board[l][i].animal.AnimalName == animal.AnimalName)
                    {
                        numUpRight++;
                        tempLine.add(Board[l][i]);
                    }
                    if (Board[l][i-1]==null||Board[l][i-1].isAnimal==false||Board[l][i-1].animal.AnimalName != animal.AnimalName)
                    {
                        break;
                    }
                    else if (Board[l][i - 1].animal.AnimalName == animal.AnimalName)
                    {
                        numUpRight++;
                        tempLine.add(Board[l][i-1]);
                    }
                    i -= 2;
                    l++;
                }
                if (tempLine.size()>longestline.size())
                {
                    longestline.clear();
                    longestline.addAll(tempLine);
                    tempLine.clear();
                }
            }
            if (Board[x][y-1]==null)
            {
            }
            else if (Board[x][y-1].isAnimal==false)
            {
            }
            else if(Board[x][y-1].animal.AnimalName==animal.AnimalName)
            {
                int l = x, i = y;
                while (l > 0 && i >= 0)
                {
                    if (Board[l][i]==null||Board[l][i].isAnimal==false||Board[l][i].animal.AnimalName != animal.AnimalName)
                    {
                        break;
                    }
                    else if (Board[l][i].animal.AnimalName == animal.AnimalName)
                    {
                        numUpLeft++;
                        tempLine.add(Board[l][i]);
                    }
                    if (Board[l][i-1]==null||Board[l][i-1].isAnimal==false||Board[l][i-1].animal.AnimalName != animal.AnimalName)
                    {
                        break;
                    }
                    else if (Board[l][i - 1].animal.AnimalName == animal.AnimalName)
                    {
                        numUpLeft++;
                        tempLine.add(Board[l][i-1]);
                    }
                    i -= 2;
                    l--;
                }
                if (tempLine.size()>longestline.size())
                {
                    longestline.clear();
                    longestline.addAll(tempLine);
                    tempLine.clear();
                }
            }
            if(Board[x+1][y+1]==null)
            {
            }
            else if (Board[x+1][y+1].isAnimal==false)
            {
            }
            else if (Board[x+1][y+1].animal.AnimalName==animal.AnimalName)
            {
                int l = x+1, i = y+1;
                while (l < 8 && i < 8)
                {
                    if (Board[l][i]==null||Board[l][i].isAnimal==false||Board[l][i].animal.AnimalName != animal.AnimalName)
                    {
                        break;
                    }
                    else if (Board[l][i].animal.AnimalName == animal.AnimalName)
                    {
                        numDownRight++;
                    }
                    if (Board[l][i+1]==null||Board[l][i+1].isAnimal==false||Board[l][i+1].animal.AnimalName != animal.AnimalName)
                    {
                        break;
                    }
                    else if (Board[l][i + 1].animal.AnimalName == animal.AnimalName)
                    {
                        numDownRight++;
                    }
                    i += 2;
                    l++;
                }
                if (tempLine.size()>longestline.size())
                {
                    longestline.clear();
                    longestline.addAll(tempLine);
                    tempLine.clear();
                }
            }
            if (Board[x][y+1]==null)
            {
            }
            else if (Board[x][y+1].isAnimal==false)
            {
            }
            else if (Board[x][y+1].animal.AnimalName==animal.AnimalName)
            {
                numDownLeft++;
                int l = x, i = y;
                while (l > 0 && i < 8)
                {
                    if (Board[l][i]==null||Board[l][i].isAnimal==false||Board[l][i].animal.AnimalName != animal.AnimalName)
                    {
                        break;
                    }
                    if (Board[l][i].animal.AnimalName == animal.AnimalName)
                    {
                        numDownLeft++;
                    }
                    if (Board[l][i+1]==null||Board[l][i-1].isAnimal==false||Board[l][i+1].animal.AnimalName != animal.AnimalName)
                    {
                        break;
                    }
                    else if (Board[l][i + 1].animal.AnimalName == animal.AnimalName)
                    {
                        numDownLeft++;
                    }
                    i += 2;
                    l--;
                }
            }
            if (tempLine.size()>longestline.size())
            {
                longestline.clear();
                longestline.addAll(tempLine);
                tempLine.clear();
            }
        }
        for (int removeanimal=0;removeanimal<longestline.size();removeanimal++)
        {
            Board[longestline.get(removeanimal).X][longestline.get(removeanimal).Y].removeAnimal();
        }
        return longestline.size();}

    public int BearpointsA(){
        int numPairs=0;
        for (int i=0;i<8;i++) {
            for (int l=0;l<8;l++){
                if (Board[i][l]==null){}
                else if (checkPair(Board[i][l])==null){}
                else if(Board[i][l].animal.AnimalName=="Bear"&&checkPair(Board[i][l]).size() == 1 && checkPair(checkPair(Board[i][l]).get(0)).size() == 1) {
                    numPairs++;
                }
            }}
        switch(numPairs){
            case 2 -> {
                return 4;
            }
            case 4-> {
                return 11;
            }
            case 6->{
                return 19;
            }
        }
        if (numPairs>=8){return 27;}
        return 0;
    }

    public int ElkPointsA()
    {
        int totalpoints=0;
        for (int i=0;i<8;i++)
        {
            for (int l=0;l<8;l++)
            {
                if (Board[l][i]==null||Board[l][i].isAnimal==false)
                {
                }
                else if (Board[l][i].animal.AnimalName=="Elk")
                {
                    switch (linechecker(l,i)){
                        case 1->{totalpoints+=2;}
                        case 2->{totalpoints+=5;}
                        case 3->{totalpoints+=9;}
                        case 4->{totalpoints+=15;}
                    }
                }
            }
        }
        return totalpoints;
    }

    public int FoxPointsA(){
        int animals[]={0,0,0,0,0};
        int totalPoints=0;

        for (int i=0;i<8;i++) {
            for (int l=0;l<8;l++){
                if (Board[l][i]==null){}
                else if (Board[l][i].isAnimal==false){}
                else if(Board[l][i].animal.AnimalName=="Fox") {
                    int numdifferent=0;
                    ArrayList<BoardTile> foxcheck=getSurrounding(Board[l][i]);
                    if (foxcheck.size()==0){}
                    else {
                        for (int z = 0; z < foxcheck.size(); z++) {
                            switch (foxcheck.get(z).animal.AnimalName) {
                                case "Hawk" -> animals[0]++;
                                case "Elk" -> animals[1]++;
                                case "Fox" -> animals[2]++;
                                case "Bear" -> animals[3]++;
                                case "Salmon" -> animals[4]++;
                            }
                        }
                        for (int k = 0; k < 5; k++) {
                            if (animals[k] != 0) {
                                numdifferent++;
                            }

                        }
                        switch (numdifferent) {
                            case 1 -> totalPoints += 1;
                            case 2 -> totalPoints += 2;
                            case 3 -> totalPoints += 3;
                            case 4 -> totalPoints += 4;
                            case 5 -> totalPoints += 5;
                        }
                        for (int x = 0; x < 5; x++) {
                            animals[x] = 0;
                        }
                    }
                }
            }
        }
        return totalPoints;
    }

    public ArrayList<BoardTile> getSurrounding(BoardTile tileToCheck) {
        int X=tileToCheck.X;
        int Y=tileToCheck.Y;
        ArrayList<BoardTile> occuredTiles=new ArrayList<BoardTile>();
        int originalX = X;
        int originalY = Y;
        if (Board[X][Y]==null||Board[X][Y].animal==null){
            return null;
        }
        if (Y % 2 == 0) {
            Y = Y - 1;
            X = X - 1;
            for (int i = 0; i < 3; i++) {
                Y += i;
                for (int l = 0; l < 2; l++) {
                    X += l;
                    if (X < 0 || X > 8 || Y < 0 || Y > 8) {}
                    else if (Board[X][Y]==null||Board[X][Y].animal==null){}
                    else if (X == originalX && Y == originalY) {
                        if (Board[X+1][Y]==null||Board[X+1][Y].isAnimal==false) {}
                        else {occuredTiles.add(findTile(X + 1, Y));}


                    } else  {
                        occuredTiles.add(findTile(X,Y));
                    }
                    X=originalX-1;
                }
                Y=originalY-1;
            }

        } else {
            Y = Y - 1;
            for (int i = 0; i < 3; i++) {
                Y += i;
                for (int l = 0; l < 2; l++) {
                    X += l;
                    if (X < 0 || X > 8 || Y < 0 || Y > 8) {
                    }
                    else if (Board[X][Y]==null||Board[X][Y].animal==null) {}
                    else if (X == originalX && Y == originalY) {
                        if (Board[X-1][Y]==null||Board[X-1][Y].isAnimal==false) {}
                        else{ occuredTiles.add(findTile(X - 1, Y));}

                    } else{
                        occuredTiles.add(findTile(X,Y));
                    }
                    X=originalX;
                }
                Y=originalY-1;
            }
        }
        return occuredTiles;
    }

}