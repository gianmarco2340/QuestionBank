/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Session;

/**
 *
 * @author brato
 */
public class SessionApp {
    private static SessionApp instance;
    private String loggedUser;

    private SessionApp() {}

    public static SessionApp getInstance() {
        if (instance == null) {
            instance = new SessionApp();
        }
        return instance;
    }

    public String getLoggedUser() {
        System.out.println("User Session: " + loggedUser);
        return loggedUser;
    }

    public void setLoggedUser(String loggedUser) {
        this.loggedUser = loggedUser;
    }
    
}
