package ru.nsu.krasnikov;

public class Baker {
    int experience; //inversely proportional to cooking time

    public Baker(int experience) {
        this.experience = experience;
    }

    public void producePizza(Order order){
        try {
            wait(1000L*experience);
        } catch (InterruptedException ignored){
        }
    }
}
