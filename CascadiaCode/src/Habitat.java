public class Habitat {
    public Habitat(Casscadia.Habitatselect ex1, Casscadia.Habitatselect ex2){
    this.habitat1=ex1;
    this.habitat2=ex2;
        switch (habitat1){
            case forest -> HabitatName="Forest";
            case mountain -> HabitatName="mountain";
            case river -> HabitatName="river";
            case wetland -> HabitatName="wetland";
            case prairie -> HabitatName="prairie";
        }
}
    public String getHabitatName(){
        return HabitatName;
    }
    String HabitatName;

    Casscadia.Habitatselect habitat1;
    Casscadia.Habitatselect habitat2;
}
