import java.io.*;
import java.util.Scanner;

public class Core {
    private static File file = new File("themelist.txt");

    /*
    TODO Sort word output alphabetic
    TODO Fix double spaces inside a command
    */

    public static void main(String[] args) throws IOException {
        checkFile();
        Scanner s = new Scanner(System.in);
        String input;
        String[] action;
        while (true) {
            System.out.println("Choose action: [add yourWord] or [list length letter] or [exit]");
            input = s.nextLine();
            input = input.trim();
            action = input.split(" ");
            if (action[0].equalsIgnoreCase("add")) {
                add(action);
            } else if (action[0].equalsIgnoreCase("list")) {
                if (action.length == 3) {
                    list(Integer.parseInt(action[1]), action[2]);
                } else {
                    list(Integer.parseInt(action[1]));
                }
            } else if (action[0].equalsIgnoreCase("exit")) {
                System.exit(-99);
            }
        }
    }

    public static void add(String... words) throws IOException {
        String word = "";
        for (int i = 1; i < words.length; i++) {
            word = word + " " + words[i];
        }
        word = word.trim();
        word = word.toLowerCase();
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        String line;
        boolean known = false;
        while ((line = br.readLine()) != null) {
            if (word.equalsIgnoreCase(line)) {
                known = true;
            }
        }
        if (!known && !word.equalsIgnoreCase("")) {
            FileWriter fileWriter = new FileWriter(file, true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write("\n" + word);
            bufferedWriter.close();
            fileWriter.close();
            System.out.println("Successful added \"" + word + "\" to the list");
        } else {
            System.err.println("Word is already in the list");
        }
    }

    public static void list(int l, String letter) throws IOException {
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        String line;
        while ((line = br.readLine()) != null) {
            if (line.length() == l) {
                if (line.contains(letter)) {
                    System.out.println(line);
                }
            }
        }
    }

    public static void list(int l) throws IOException {
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        String line;
        while ((line = br.readLine()) != null) {
            if (line.length() == l) {
                System.out.println(line);
            } else if (l == -1) {
                System.out.println(line);
            }
        }
    }

    public static void checkFile() throws IOException {
        if (!file.exists()) {
            System.err.println("No File found. Creating one");
            file.createNewFile();
        }
    }

}
