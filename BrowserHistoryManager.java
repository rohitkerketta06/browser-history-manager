
import java.io.*;
import java.util.*;

public class BrowserHistoryManager {

    private static final String FILE_NAME = "history.txt";

    // Stores all visited websites
    private LinkedList<String> historyList = new LinkedList<>();

    // Stores pages for Back and Forward navigation
    private Stack<String> backStack = new Stack<>();
    private Stack<String> forwardStack = new Stack<>();

    // Stores website names and their visit counts
    private HashMap<String, Integer> visitCountMap = new HashMap<>();

    // Stores the currently opened website
    private String currentPage = null;

    public static void main(String[] args) {

        BrowserHistoryManager browser = new BrowserHistoryManager();

        // Load previously saved history
        browser.loadHistoryFromFile();

        Scanner sc = new Scanner(System.in);
        int choice = 0;

        // Display menu until the user selects Exit
        while (choice != 6) {

            System.out.println("\n================================");
            System.out.println("      BROWSER HISTORY MANAGER");
            System.out.println("================================");
            System.out.println("1. Visit Page");
            System.out.println("2. Back");
            System.out.println("3. Forward");
            System.out.println("4. Show Full History");
            System.out.println("5. Show Visit Count");
            System.out.println("6. Save & Exit");
            System.out.println("--------------------------------");
            System.out.print("Enter your choice: ");

            // Check whether the user entered a number
            if (sc.hasNextInt()) {
                choice = sc.nextInt();
                sc.nextLine();
            } else {
                System.out.println("[ERROR] Please enter a number between 1 and 6.");
                sc.nextLine();
                continue;
            }

            // Perform operation based on user choice
            switch (choice) {

                case 1:
                    System.out.print("Enter URL: ");
                    String url = sc.nextLine().trim();
                    browser.visitPage(url);
                    break;

                case 2:
                    browser.goBack();
                    break;

                case 3:
                    browser.goForward();
                    break;

                case 4:
                    browser.showHistory();
                    break;

                case 5:
                    browser.showVisitCounts();
                    break;

                case 6:
                    browser.saveHistoryToFile();
                    System.out.println("\n[SUCCESS] History saved successfully!");
                    System.out.println("Thank you for using Browser History Manager.");
                    System.out.println("Program closed.");
                    break;

                default:
                    System.out.println("[ERROR] Invalid option. Please choose between 1 and 6.");
            }
        }

        sc.close();
    }

    // Check whether the entered URL is valid
    private boolean isValidUrl(String url) {

        if (url == null || url.isEmpty()) {
            return false;
        }

        int dotIndex = url.indexOf('.');

        return dotIndex > 0 &&
               dotIndex < url.length() - 1 &&
               !url.contains(" ");
    }

    // Add a new website to browsing history
    public void visitPage(String url) {

        if (!isValidUrl(url)) {
            System.out.println("[ERROR] Invalid URL. Please try again.");
            return;
        }

        // Save current page before opening a new page
        if (currentPage != null) {
            backStack.push(currentPage);
        }

        currentPage = url;

        // Clear forward history when a new page is visited
        forwardStack.clear();

        // Add website to history
        historyList.add(url);

        // Increase the visit count
        visitCountMap.put(
            url,
            visitCountMap.getOrDefault(url, 0) + 1
        );

        System.out.println("\n[SUCCESS] Page visited successfully!");
        System.out.println("Current Page: " + currentPage);
    }

    // Move to the previously visited website
    public void goBack() {

        if (backStack.isEmpty()) {
            System.out.println("[INFO] No previous page.");

            if (currentPage != null) {
                System.out.println("Current Page: " + currentPage);
            }

            return;
        }

        // Move current page to forward stack
        forwardStack.push(currentPage);

        // Get the previous page
        currentPage = backStack.pop();

        System.out.println("[SUCCESS] Back to: " + currentPage);
    }

    // Move to the next website
    public void goForward() {

        if (forwardStack.isEmpty()) {
            System.out.println("[INFO] No forward page.");

            if (currentPage != null) {
                System.out.println("Current Page: " + currentPage);
            }

            return;
        }

        // Save current page in back stack
        backStack.push(currentPage);

        // Get the next page
        currentPage = forwardStack.pop();

        System.out.println("[SUCCESS] Forward to: " + currentPage);
    }

    // Display all visited websites
    public void showHistory() {

        if (historyList.isEmpty()) {
            System.out.println("[INFO] Browsing history is empty.");
            return;
        }

        System.out.println("\n--------- BROWSING HISTORY ---------");

        for (int i = 0; i < historyList.size(); i++) {
            System.out.println((i + 1) + ". " + historyList.get(i));
        }

        System.out.println("------------------------------------");

        if (currentPage != null) {
            System.out.println("Current Page: " + currentPage);
        }
    }

    // Display the number of visits for each website
    public void showVisitCounts() {

        if (visitCountMap.isEmpty()) {
            System.out.println("[INFO] No pages visited yet.");
            return;
        }

        System.out.println("\n-------- WEBSITE VISIT COUNTS --------");

        for (Map.Entry<String, Integer> entry : visitCountMap.entrySet()) {

            String website = entry.getKey();
            int count = entry.getValue();

            System.out.println(website + " -> " + count +
                (count == 1 ? " visit" : " visits"));
        }

        System.out.println("--------------------------------------");
    }

    // Load browsing history from the file
    public void loadHistoryFromFile() {

        File file = new File(FILE_NAME);

        // Check whether the file exists
        if (!file.exists()) {
            return;
        }

        try (BufferedReader reader =
                 new BufferedReader(new FileReader(file))) {

            String line;

            // Read websites one by one
            while ((line = reader.readLine()) != null) {

                line = line.trim();

                if (!line.isEmpty() && isValidUrl(line)) {

                    // Restore previous pages
                    if (currentPage != null) {
                        backStack.push(currentPage);
                    }

                    currentPage = line;
                    historyList.add(line);

                    // Restore visit counts
                    visitCountMap.put(
                        line,
                        visitCountMap.getOrDefault(line, 0) + 1
                    );
                }
            }

            if (currentPage != null) {
                System.out.println("[INFO] Previous history loaded successfully.");
                System.out.println("Current Page: " + currentPage);
            }

        } catch (IOException e) {
            System.out.println("[ERROR] Error loading history: " + e.getMessage());
        }
    }

    // Save browsing history into the file
    public void saveHistoryToFile() {

        try (BufferedWriter writer =
                 new BufferedWriter(new FileWriter(FILE_NAME))) {

            // Write every visited website into the file
            for (String page : historyList) {
                writer.write(page);
                writer.newLine();
            }

        } catch (IOException e) {
            System.out.println("[ERROR] Error saving history: " + e.getMessage());
        }
    }
}
