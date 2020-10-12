import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class Main {
  private static int NUM_LINES_TO_CHECK = 10;
  private static String TITLE_TAG = "<title>";

  public static void main(String[] args) throws IOException {
    boolean found = false;

    while (!found) {
      URL url = getUrl();
      BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream())); //declares a new buffered reader to retrieve the html using the URL provided
      int readLines = 0;

      String line;
      // loops through the first lines of the html recieved, assuming any html is returned.
      while ((line = reader.readLine()) != null && readLines < NUM_LINES_TO_CHECK) { 
        // every ecs staff page contains this in the title, after their name. Non-staff pages also contain 'People' in the title.
        if (line.contains("| Electronics and Computer Science | University of Southampton") && !line.contains("People")) {
          int nameStartIndex = line.indexOf(TITLE_TAG) + TITLE_TAG.length();
          int nameEndIndex = line.indexOf('|');
          String name = line.substring(nameStartIndex, nameEndIndex); //cuts down the title to only store the name in the new String variable
          found = true; //ends the while loop as the name has been found
          System.out.println(name); //prints name to console
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
