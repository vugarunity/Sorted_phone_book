import java.util.ArrayList;
import java.util.HashMap;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

class PhoneBook {
    private HashMap<String, ArrayList<Integer>> phoneBook = new HashMap<>();

    public void add(String name, Integer phoneNum) {
        if (phoneBook.containsKey(name)) {
            phoneBook.get(name).add(phoneNum);
        } else {
            ArrayList<Integer> phoneNumbers = new ArrayList<>();
            phoneNumbers.add(phoneNum);
            phoneBook.put(name, phoneNumbers);
        }
    }

    public ArrayList<Integer> find(String name) {
        return phoneBook.getOrDefault(name, new ArrayList<>());
    }

    public HashMap<String, ArrayList<Integer>> getPhoneBook() {
        return phoneBook;
    }

    public void sortByNumberOfPhones() {
        for (ArrayList<Integer> numbers : phoneBook.values()) {
            Collections.sort(numbers, Collections.reverseOrder());
        }

        List<Map.Entry<String, ArrayList<Integer>>> entries = new ArrayList<>(phoneBook.entrySet());

        Collections.sort(entries, new Comparator<Map.Entry<String, ArrayList<Integer>>>() {
            @Override
            public int compare(Map.Entry<String, ArrayList<Integer>> o1, Map.Entry<String, ArrayList<Integer>> o2) {
                int compareSize = Integer.compare(o2.getValue().size(), o1.getValue().size());
                if (compareSize != 0) {
                    return compareSize;
                } else {
                    return o1.getKey().compareTo(o2.getKey());
                }
            }
        });

        HashMap<String, ArrayList<Integer>> sortedPhoneBook = new HashMap<>();

        for (Map.Entry<String, ArrayList<Integer>> entry : entries) {
            sortedPhoneBook.put(entry.getKey(), entry.getValue());
        }

        phoneBook = sortedPhoneBook;
    }

    public void printSortedPhoneBook() {
        System.out.println("Sorted phone book:");
        for (Map.Entry<String, ArrayList<Integer>> entry : phoneBook.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

    public static void main(String[] args) {
        PhoneBook pb = new PhoneBook();
        
        pb.add("Alice", 123456);
        pb.add("Alice", 789012);
        pb.add("Bob", 789012);

        pb.sortByNumberOfPhones();
        pb.printSortedPhoneBook();
    }
}
