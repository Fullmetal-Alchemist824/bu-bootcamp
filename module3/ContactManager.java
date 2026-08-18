import java.util.*;

public class ContactManager {

    public static void main(String[] args) {

        HashMap<String, Contact> contacts = new HashMap<>();

        contacts.put("Ada Lovelace", new Contact("Ada Lovelace", "+1 617 555 0101"));
        contacts.put("Grace Hopper", new Contact("Grace Hopper", "+1 212 555 0102"));
        contacts.put("Alan Turing", new Contact("Alan Turing", "+1 415 555 0103"));
        contacts.put("Katherine Johnson", new Contact("Katherine Johnson", "+1 202 555 0104"));
        contacts.put("Tim Berners-Lee", new Contact("Tim Berners-Lee", "+1 303 555 0105"));

        Contact found = contacts.get("Ada Lovelace");
        Contact notFound = contacts.get("George Washington");

        if (found == null) {
            System.out.println("Contact not found.");
        } else {
            System.out.println(found);
        }

        if (notFound == null) {
            System.out.println("Contact not found.");
        } else {
            System.out.println("Found: " + notFound);
        }

        ArrayList<Contact> sorted = new ArrayList<>(contacts.values());

        sorted.sort((a, b) -> a.getName().compareTo(b.getName()));

        System.out.println("=== All Contacts ===");

        for (Contact contact : sorted) {
            System.out.println(contact);
        }
    }
}