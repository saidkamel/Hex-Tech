/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;
import java.awt.*;
import java.awt.TrayIcon.MessageType;

/**
 *
 * @author sa3do
 */
public class Notification {
    public static void main(String sujet , String contenu) throws AWTException {
        if (SystemTray.isSupported()) {
            Notification td = new Notification();
            td.displayTray(sujet,contenu);
        } else {
            System.err.println("System tray not supported!");
        }
    }

    public void displayTray(String sujet, String contenu) throws AWTException {
        //Obtain only one instance of the SystemTray object
        SystemTray tray = SystemTray.getSystemTray();

        //If the icon is a file
        Image image = Toolkit.getDefaultToolkit().createImage("icon.gif");
        //Alternative (if the icon is on the classpath):
        //Image image = Toolkit.getDefaultToolkit().createImage(getClass().getResource("icon.png"));

        TrayIcon trayIcon = new TrayIcon(image, "Tray Demo");
        //Let the system resize the image if needed
        trayIcon.setImageAutoSize(true);
        //Set tooltip text for the tray icon
        trayIcon.setToolTip("System tray icon demo");
        tray.add(trayIcon);

        trayIcon.displayMessage(sujet, contenu, MessageType.INFO);
        
    }
}
