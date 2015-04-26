package finalproject;

/**
 *
 * @author Doug, Brennan,
 */
import java.io.*;
import java.net.*;
import java.util.Scanner;
import javax.swing.JFrame;
import javax.swing.JComponent;

public class FinalProject {

    //Put this all into a GUI

    public static void main(String[] args) {
        GUI frame = new GUI();
        frame.setVisible(true);

        /* Scanner input = new Scanner(System.in);
         System.out.println("Enter the URL of the RSS feed you'd like to add");
         String URL = input.next();
         //URL to read
         System.out.println(readRSS(URL));*/
    }

    //Function that reads the RSS

    public static String readRSS(String url) {
        try {
            URL rss = new URL(url);
            String source;
            BufferedReader read = new BufferedReader(new InputStreamReader(rss.openStream()));
            source = " ";
            String line;
            //Reads lines from URL
            while ((line = read.readLine()) != null) {
                if (line.contains("<title>")) {
                    int fPos = line.indexOf("<title>");
                    String temp = line.substring(fPos);
                        //replaces title with blank
                    //sometimes depending on the size of
                    //the blank some of the title might be cut off no idea why
                    temp = temp.replace("<title>", "        ");
                    int lPos = line.indexOf("</title>");
                        //replaces blank with whats betwenn Opening and
                    //closing tags
                    temp = temp.substring(0, lPos);
                    source += temp + "\n";
                }

            }
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
