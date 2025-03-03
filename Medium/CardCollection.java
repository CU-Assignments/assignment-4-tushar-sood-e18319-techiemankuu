import java.util.*;

class CardCollection {
    private final HashMap<String, List<String>> cardMap;

    public CardCollection() {
        cardMap = new HashMap<>();
    }

    
    public void addCard(String symbol, String value) {
        cardMap.computeIfAbsent(symbol, k -> new ArrayList<>()).add(value);
    }

    public List<String> getCardsBySymbol(String symbol) {
        return cardMap.getOrDefault(symbol, Collections.emptyList());
    }

    
    public void displayAllCards() {
        if (cardMap.isEmpty()) {
            System.out.println("No cards stored.");
            return;
        }
        System.out.println("\nStored Cards:");
        for (Map.Entry<String, List<String>> entry : cardMap.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}


public class CardCollectionMain {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CardCollection collection = new CardCollection();

        // Adding sample cards
        collection.addCard("Hearts", "Ace");
        collection.addCard("Hearts", "King");
        collection.addCard("Spades", "Queen");
        collection.addCard("Diamonds", "Jack");
        collection.addCard("Clubs", "10");
        collection.addCard("Spades", "7");

        while (true) {
            System.out.println("\nCard Collection System");
            System.out.println("1. Search Cards by Symbol");
            System.out.println("2. Display All Cards");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine(); 
            switch (choice) {
                case 1 -> {
                    System.out.print("Enter symbol (Hearts, Spades, Diamonds, Clubs): ");
                    String symbol = scanner.nextLine();
                    List<String> cards = collection.getCardsBySymbol(symbol);
                    if (cards.isEmpty()) {
                        System.out.println("No cards found for symbol: " + symbol);
                    } else {
                        System.out.println("Cards of " + symbol + ": " + cards);
                    }
                }
                case 2 -> collection.displayAllCards();
                case 3 -> {
                    System.out.println("Exiting... Goodbye!");
                    return;
                }
                default -> System.out.println("Invalid choice! Please try again.");
            }
        }
    }
}
