package ld1port;

import java.text.DateFormat;
import java.util.Date;

/**
 *
 * @author audri
 */
public class Student {

    private String name;
    private String surname;
    private Date birthday;
    private String cardId;
    private int course;
    private String phoneNumber;
    private boolean isFux;

    public Student(String name, String Surname, Date birthday) {
        this.name = name;
        this.surname = Surname;
        this.birthday = birthday;
    }

    public Student(String name, String surname, Date birthday, String cardId, int course, String phoneNumber, boolean isFux) {
        this(name, surname, birthday);
        this.cardId = cardId;
        this.course = course;
        this.phoneNumber = phoneNumber;
        this.isFux = isFux;
    }

    /**
     * Get the value of isFux
     *
     * @return the value of isFux
     */
    public boolean isFux() {
        return isFux;
    }

    /**
     * Set the value of isFux
     *
     * @param isFux new value of isFux
     */
    public void setIsFux(boolean isFux) {
        this.isFux = isFux;
    }

    /**
     * Get the value of phoneNumber
     *
     * @return the value of phoneNumber
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Set the value of phoneNumber
     *
     * @param phoneNumber new value of phoneNumber
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * Get the value of course
     *
     * @return the value of course
     */
    public int getCourse() {
        return course;
    }

    /**
     * Set the value of course
     *
     * @param course new value of course
     */
    public void setCourse(int course) {
        this.course = course;
    }

    /**
     * Get the value of cardId
     *
     * @return the value of cardId
     */
    public String getCardId() {
        return cardId;
    }

    /**
     * Set the value of cardId
     *
     * @param cardId new value of cardId
     */
    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    /**
     * Get the value of birthday
     *
     * @return the value of birthday
     */
    public Date getBirthday() {
        return birthday;
    }

    /**
     * Get the value of name
     *
     * @return the value of name
     */
    public String getName() {
        return name;
    }

    /**
     * Set the value of name
     *
     * @param name new value of name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get the value of Surname
     *
     * @return the value of Surname
     */
    public String getSurname() {
        return surname;
    }

    @Override
    public String toString() {
        return "Student{" + "name=" + name + ", surname=" + surname + ", birthday=" + 
                DateFormat.getDateInstance(DateFormat.SHORT).format(birthday) + 
                ", cardId=" + cardId + ", course=" + course + ", phoneNumber=" + 
                phoneNumber + ", isFux=" + isFux + '}';
    }
}
