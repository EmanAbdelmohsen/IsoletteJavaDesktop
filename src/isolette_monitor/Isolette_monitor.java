/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package isolette_monitor;
import application.Main;
/**
 *
 * @author Rehab Abdelmohsen
 */
public class Isolette_monitor {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //starts a new instance of the Isolette GUI
        frm_main frame = new frm_main();
        frame.show();
        
        //console messages (for testing purposes only)
        //Main.main(args);
    }
    
}
