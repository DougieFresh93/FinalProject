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
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author stone.douglas.g
 */
public class GUI extends JComponent {

    public void paintComponent(Graphics g) {
        Graphics2D gfx = (Graphics2D) g;
        JTextField input = new JTextField(20);
        input= new JTextField(20);
        input.addActionListener(this);

        //input = new JTextArea(5, 20);
        input.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(input);
         
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
                    temp = temp.replace("<title>", "          ");
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
