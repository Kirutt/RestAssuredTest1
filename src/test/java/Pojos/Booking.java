package Pojos;

public class Booking {
    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getAdditionalneeds() {
        return additionalneeds;
    }

    public void setAdditionalneeds(String additionalneeds) {
        this.additionalneeds = additionalneeds;
    }

    public int getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(int totalprice) {
        this.totalprice = totalprice;
    }

    public boolean isDepositpaid() {
        return depositpaid;
    }

    public void setDepositpaid(boolean depositpaid) {
        this.depositpaid = depositpaid;
    }

    public BookingDates getBookingdates() {
        return bookingdates;
    }

    public void setBookingdates(BookingDates bookingdates) {
        this.bookingdates = bookingdates;
    }
    public Booking(){}
    public Booking(String firstname,String lastname,String additionalneeds,int totalprice, boolean depositpaid,
                   BookingDates bookingdates){
        setFirstname(firstname);
        setLastname(lastname);
        setAdditionalneeds(additionalneeds);
        setTotalprice(totalprice);
        setDepositpaid(depositpaid);
        setBookingdates(bookingdates);

    }


    private String firstname;
    private String lastname;
    private  String additionalneeds;
    private int totalprice;
    private boolean depositpaid;
    private BookingDates bookingdates;
}
