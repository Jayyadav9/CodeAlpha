package HotelReservationSystem;
import java.io.*;
import java.util.*;

class Room {
    int roomNumber;
    String category;
    double price;
    boolean isAvailable;

    Room(int roomNumber, String category, double price) {
        this.roomNumber = roomNumber;
        this.category = category;
        this.price = price;
        this.isAvailable = true;
    }

    public String toString() {
        return "Room " + roomNumber + " (" + category + ") - ₹" + price + " - " + (isAvailable ? "Available" : "Booked");
    }
}

class Booking {
    String customerName;
    int roomNumber;
    String category;
    String date;
    double amountPaid;

    Booking(String customerName, int roomNumber, String category, String date, double amountPaid) {
        this.customerName = customerName;
        this.roomNumber = roomNumber;
        this.category = category;
        this.date = date;
        this.amountPaid = amountPaid;
    }

    public String toString() {
        return customerName + " | Room: " + roomNumber + " (" + category + ") | Date: " + date + " | Paid: ₹" + amountPaid;
    }
}

public class HotelReservationSystem {
    static ArrayList<Room> rooms = new ArrayList<>();
    static ArrayList<Booking> bookings = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);
    static final String FILE_NAME = "bookings.txt";

    public static void main(String[] args) {
        initializeRooms();
        loadBookings();

        int choice;
        do {
            System.out.println("\n------ HOTEL RESERVATION SYSTEM ------");
            System.out.println("1. View Available Rooms");
            System.out.println("2. Book a Room");
            System.out.println("3. Cancel Booking");
            System.out.println("4. View All Bookings");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1: viewRooms(); break;
                case 2: bookRoom(); break;
                case 3: cancelBooking(); break;
                case 4: viewBookings(); break;
                case 5: saveBookings(); System.out.println("Thank you! Exiting..."); break;
                default: System.out.println("Invalid choice. Try again.");
            }
        } while (choice != 5);
    }

    static void initializeRooms() {
        rooms.add(new Room(101, "Standard", 1500));
        rooms.add(new Room(102, "Standard", 1500));
        rooms.add(new Room(201, "Deluxe", 2500));
        rooms.add(new Room(202, "Deluxe", 2500));
        rooms.add(new Room(301, "Suite", 4000));
        rooms.add(new Room(302, "Suite", 4000));
    }

    static void viewRooms() {
        System.out.println("\nAvailable Rooms:");
        for (Room room : rooms) {
            System.out.println(room);
        }
    }

    static void bookRoom() {
        System.out.print("Enter your name: ");
        sc.nextLine();
        String name = sc.nextLine();

        System.out.print("Enter room category (Standard/Deluxe/Suite): ");
        String category = sc.nextLine();

        for (Room room : rooms) {
            if (room.category.equalsIgnoreCase(category) && room.isAvailable) {
                System.out.println("Room available: " + room);
                System.out.print("Enter booking date (dd-mm-yyyy): ");
                String date = sc.nextLine();

                System.out.println("Simulating payment of ₹" + room.price + "...");
                System.out.println("Payment successful!");

                room.isAvailable = false;
                bookings.add(new Booking(name, room.roomNumber, room.category, date, room.price));
                System.out.println("Booking confirmed!");
                return;
            }
        }
        System.out.println("Sorry, no available rooms in selected category.");
    }

    static void cancelBooking() {
        System.out.print("Enter your name to cancel booking: ");
        sc.nextLine();
        String name = sc.nextLine();

        Booking toCancel = null;
        for (Booking booking : bookings) {
            if (booking.customerName.equalsIgnoreCase(name)) {
                toCancel = booking;
                break;
            }
        }

        if (toCancel != null) {
            bookings.remove(toCancel);
            for (Room room : rooms) {
                if (room.roomNumber == toCancel.roomNumber) {
                    room.isAvailable = true;
                    break;
                }
            }
            System.out.println("Booking cancelled for " + name);
        } else {
            System.out.println("No booking found for " + name);
        }
    }

    static void viewBookings() {
        if (bookings.isEmpty()) {
            System.out.println("No bookings yet.");
        } else {
            System.out.println("\nCurrent Bookings:");
            for (Booking b : bookings) {
                System.out.println(b);
            }
        }
    }

    static void saveBookings() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(FILE_NAME))) {
            for (Booking b : bookings) {
                pw.println(b.customerName + "," + b.roomNumber + "," + b.category + "," + b.date + "," + b.amountPaid);
            }
        } catch (IOException e) {
            System.out.println("Error saving bookings: " + e.getMessage());
        }
    }

    static void loadBookings() {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                Booking b = new Booking(parts[0], Integer.parseInt(parts[1]), parts[2], parts[3], Double.parseDouble(parts[4]));
                bookings.add(b);

                // Mark room as unavailable
                for (Room r : rooms) {
                    if (r.roomNumber == b.roomNumber) {
                        r.isAvailable = false;
                        break;
                    }
                }
            }
        } catch (FileNotFoundException e) {
            // file not found = no bookings yet
        } catch (IOException e) {
            System.out.println("Error loading bookings.");
        }
    }
}
