public class Animal {
    public Animal(Casscadia.Animalselect type){
    this.Animaltype=type;
    switch (type){
        case Hawk -> AnimalName="Hawk";
        case Elk -> AnimalName="Elk";
        case Fox -> AnimalName="Fox";
        case Bear -> AnimalName="Bear";
        case Salmon -> AnimalName="Salmon";
    }
}
    public String getAnimalName(){
        return this.AnimalName;
    }
    Casscadia.Animalselect Animaltype;
    String AnimalName;
}
