import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.*;
import java.util.Scanner;

public class Core {
    private static String fileName = "themelist.txt";
    private static File file = new File(fileName);

    /*
    TODO Sort word output alphabetic
    TODO Fix double spaces inside a command
    TODO Fix list command without letter parameter
    */

    public static void main(String[] args) throws IOException, AWTException {
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
            } else if (l == -1) {
                System.out.println(line);
            }
        }
    }

    public static void insert(String word) throws AWTException {
        Robot robot = new Robot();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.err.println("starting output");
        robot.keyPress(KeyEvent.VK_T);
        robot.delay(1);
        robot.keyRelease(KeyEvent.VK_T);
        robot.delay(5);
        for (char code : word.toCharArray()) {
            int keyCode = KeyEvent.getExtendedKeyCodeForChar(code);
            robot.keyPress(keyCode);
            robot.delay(1);
            robot.keyRelease(keyCode);
        }
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.delay(1);
        robot.keyRelease(KeyEvent.VK_ENTER);
    }

    public static void checkFile() throws IOException {
        if (!file.exists()) {
            System.err.println("No File found. Creating one");
            file.createNewFile();
        }
    }


}
