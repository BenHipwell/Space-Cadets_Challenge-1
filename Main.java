import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class Main {
  private static int NUM_LINES_TO_CHECK = 10;
  private static String TITLE_TAG = "<title>";
  private static String BASE_URL = "https://www.ecs.soton.ac.uk/people/";

  public static void main(String[] args) throws IOException {
    Scanner input = new Scanner(System.in);
    boolean found = false;

    while (!found) {
      System.out.println("Please enter the email id of the chosen staff member:");
      String id = input.nextLine();
      String name = getProfessorNameFromId(id);

      if (name == null || name.isEmpty()) {
        System.out.println("Invalid member id, please try again");
      } else {
        System.out.println(name);
        found = true;
      }
    }
  }

  private static String getProfessorNameFromId(String id) throws MalformedURLException, IOException {
    URL url = new URL(BASE_URL + id);
    BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream())); //declares a new buffered reader to retrieve the html using the URL provided
    int readLines = 0;

    String line;
    // loops through the first lines of the html recieved, assuming any html is returned.
    while ((line = reader.readLine()) != null && readLines < NUM_LINES_TO_CHECK) { 
      // every ecs staff page contains this in the title, after their name. Non-staff pages also contain 'People' in the title.
      if (line.contains("| Electronics and Computer Science | University of Southampton") && !line.contains("People")) {
        // name starts immediately after the title tag in html.
        int nameStartIndex = line.indexOf(TITLE_TAG) + TITLE_TAG.length();
        int nameEndIndex = line.indexOf('|');
        String name = line.substring(nameStartIndex, nameEndIndex);

        return name;
      }

      readLines++;
    }

    return null;
  }
}
