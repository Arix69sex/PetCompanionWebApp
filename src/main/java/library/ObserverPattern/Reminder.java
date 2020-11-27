package library.ObserverPattern;

import java.util.List;

//Clase que representa un patrón de diseño tipo Observer
//Se encarga de mantener el estado de subscripción de nuestros usuarios

public class Reminder {

    private List<Object> premiumUsers;

    public void addPremiumUser(Object user){
        premiumUsers.add(user);
    }

    public void removeNonPremiumUser(Object user){
        premiumUsers.remove(user);
    }

    public void sendReminder(){
        //sends reminders for recommended services to use
    }
}
