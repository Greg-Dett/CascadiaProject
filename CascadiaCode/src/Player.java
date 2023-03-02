import java.util.ArrayList;

public class Player {
    public Player(String Name){
        this.Name=Name;
    }
    String Name;
    int cardnumber=0;
    ArrayList<Animal> CurrentAnimalCards= new ArrayList<Animal>();
    ArrayList<Habitat> CurrentHabitatCards= new ArrayList<Habitat>();
    Casscadia.StartTile StarterTile;

    public void setCurrentAnimalCardsandCurrentHabitatCards(Animal AnimalCard,Habitat HabitatCards) {
        CurrentAnimalCards.add(AnimalCard);
        CurrentHabitatCards.add(HabitatCards);
    }
    public  String toString(){
        String info;
        info=("Player Name:"+this.Name+"\n"+"Current Habitats in Hand: "+CurrentHabitatCards.get(cardnumber).getHabitatName()+"\n"+"Current Animal in Hand: "+CurrentAnimalCards.get(cardnumber).getAnimalName());
        return info;
    }

    public String getName(){
        return this.Name;
    }
}
