import java.util.concurrent.locks.ReentrantLock;


class TicketBookingSystem {
    private static final int TOTAL_SEATS = 10;
    private final boolean[] seats = new boolean[TOTAL_SEATS]; 
    private final ReentrantLock lock = new ReentrantLock(); 

    
    public synchronized boolean bookSeat(int seatNumber, String user) {
        if (seatNumber < 0 || seatNumber >= TOTAL_SEATS) {
            System.out.println(user + " tried to book an invalid seat number: " + seatNumber);
            return false;
        }
        if (!seats[seatNumber]) { 
            seats[seatNumber] = true;
            System.out.println(user + " successfully booked seat " + seatNumber);
            return true;
        } else {
            System.out.println(user + " tried to book an already booked seat: " + seatNumber);
            return false;
        }
    }

    public void displaySeats() {
        System.out.print("Seat Status: ");
        for (int i = 0; i < TOTAL_SEATS; i++) {
            System.out.print((seats[i] ? "[X]" : "[ ]") + " ");
        }
        System.out.println();
    }
}


class BookingThread extends Thread {
    private final TicketBookingSystem system;
    private final int seatNumber;
    private final String user;

    public BookingThread(TicketBookingSystem system, int seatNumber, String user, int priority) {
        this.system = system;
        this.seatNumber = seatNumber;
        this.user = user;
        setPriority(priority); 
    }

    @Override
    public void run() {
        system.bookSeat(seatNumber, user);
    }
}


public class TicketBookingSystemMain {
    public static void main(String[] args) {
        TicketBookingSystem system = new TicketBookingSystem();

        // Creating Threads (VIP users get higher priority)
        Thread vip1 = new BookingThread(system, 2, "VIP User 1", Thread.MAX_PRIORITY);
        Thread vip2 = new BookingThread(system, 5, "VIP User 2", Thread.MAX_PRIORITY);
        Thread user1 = new BookingThread(system, 2, "Regular User 1", Thread.MIN_PRIORITY);
        Thread user2 = new BookingThread(system, 5, "Regular User 2", Thread.MIN_PRIORITY);
        Thread user3 = new BookingThread(system, 7, "Regular User 3", Thread.NORM_PRIORITY);

        
        vip1.start();
        vip2.start();
        user1.start();
        user2.start();
        user3.start();

        
        try {
            vip1.join();
            vip2.join();
            user1.join();
            user2.join();
            user3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        
        system.displaySeats();
    }
}
