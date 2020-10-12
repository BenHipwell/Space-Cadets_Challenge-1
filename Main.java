import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class Main {
  private static int NUM_LINES_TO_CHECK = 10;

  public static void main(String[] args) throws IOException {
    boolean found = false;

    while (!found) {
      URL url = getUrl();
      BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream())); //declares a new buffered reader to retrieve the html using the URL provided
      int readLines = 0;

      String line;
      // loops through the first lines of the html recieved, assuming any html is returned.
      while ((line = reader.readLine()) != null && readLines < NUM_LINES_TO_CHECK) { 
        if (line.contains("| Electronics and Computer Science | University of Southampton")) { //every ecs staff web page contains this within the header title, with their name just before
          if (!line.contains("People | Electronics and Computer Science | University of Southampton")) { //checks to see if a non-staff email has been entered, as the 'people' page is shown otherwise
            String name = line.substring(11, line.indexOf("|")); //cuts down the title to only store the name in the new String variable
            found = true; //ends the while loop as the name has been found
            System.out.println(name); //prints name to console
          }
        }

        readLines++;
      }

      if (!found) {
        System.out.println("Invalid member id, please try again");
      }
    }
  }

  static URL getUrl() throws MalformedURLException { //declares new method to retrieve the URL
    Scanner input = new Scanner(System.in); //creates a scanner object to be able to read the user's input
    System.out.println("Please enter the email id of the chosen staff member:");
    String id = input.nextLine(); //stores user's input
    URL url = new URL("https://www.ecs.soton.ac.uk/people/" + id); //concatenating the base url with the id provided by the user to make the new url

    return url;
  }
}
