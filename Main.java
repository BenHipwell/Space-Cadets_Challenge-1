import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class Main {

  public static void main(String[] args) throws IOException {
    boolean found = false;

    while (!found) {
      URL url = getUrl(); //creates a new URL object using the getUrl method
      BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream())); //declares a new buffered reader to retrieve the html using the URL provided

      for (int i = 0; i < 10; i++) { //loops through first 10 lines of the html received into the buffered reader
        String newLine = reader.readLine(); //reads in the next line of html to be checked
        if (newLine.contains("| Electronics and Computer Science | University of Southampton")) { //every ecs staff web page contains this within the header title, with their name just before
          if (newLine.contains("People | Electronics and Computer Science | University of Southampton")) { //checks to see if a non-staff email has been entered, as the 'people' page is shown otherwise
            System.out.println("Invalid member id, please try again");
            break;
          } else {
            String name = newLine.substring(11, newLine.indexOf("|")); //cuts down the title to only store the name in the new String variable
            found = true; //ends the while loop as the name has been found
            System.out.println(name); //prints name to console
          }
        }
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
