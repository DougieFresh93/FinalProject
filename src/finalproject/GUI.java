
package finalproject;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
/**
 *
 * @author stone.douglas.g
 */
public class GUI extends JComponent{
    
   
     String URL = JOptionPane.showInputDialog(this  ,"Enter the RSS Feed URL:");
    
    public void paintComponent(Graphics g){
         
      
        
         //Graphics2D gfx = (Graphics2D) g;
         JOptionPane.showMessageDialog(this  ,readRSS(URL));
         
     }
    public static String readRSS(String url) {
         JOptionPane.setRootFrame(null);
        try {
            URL rss = new URL(url);
            BufferedReader read = new BufferedReader(new InputStreamReader(rss.openStream()));
            String source = " ";
            String line;
            //Reads lines from URL
            while ((line = read.readLine()) != null) {
                if (line.contains("<title>")) {
                    int fPos = line.indexOf("<title>");
                    String temp = line.substring(fPos);
                   //replaces title with blank
                     //sometimes depending on the size of 
                     //the blank some of the title might be cut off no idea why
                    temp = temp.replace("<title>", "                     ");
                    int lPos = line.indexOf("</title>");
                   //replaces blank with whats betwenn Opening and
                   //closing tags 
                    temp = temp.substring(0, lPos);
                    source += temp + "\n";
                }
            }
            read.close();
            return source;
            //Catches if URL is not a valid URL
        } catch (MalformedURLException ue) {
            System.out.println("Not a valid URL");
            //catches if something goes wrong reading RSS
        } catch (IOException ioe) {
            System.out.println("Error in reading RSS");
        }
        return null;
    }
}
