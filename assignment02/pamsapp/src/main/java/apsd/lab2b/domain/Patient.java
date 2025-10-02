package apsd.lab2b.domain;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Patient {
    private long id;
    private String firstName;
    private String lastName;
    private String phoneNo;
    private String email;
    private MailingAddress mailingAddress;
    private LocalDate dateOfBirth;

    public Patient() {

    }
    
    public Patient(long id, String firstName, String lastName, String phoneNo, String email, MailingAddress mailingAddress,
            LocalDate dateOfBirth) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNo = phoneNo;
        this.email = email;
        this.mailingAddress = mailingAddress;
        this.dateOfBirth = dateOfBirth;
    }
    
    @JsonProperty("id")
    public long getId() {
        return id;
    }

    @JsonProperty("firstName") 
    public String getFirstName() {
        return firstName;
    }

    @JsonProperty("lastName") 
    public String getLastName() {
        return lastName;
    }

    @JsonProperty("phoneNo") 
    public String getPhoneNo() {
        return phoneNo;
    }

    @JsonProperty("email") 
    public String getEmail() {
        return email;
    }

    @JsonProperty("mailingAddress") 
    public MailingAddress getMailingAddress() {
        return mailingAddress;
    }

    @JsonProperty("dateOfBirth")
    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    @JsonProperty("age")
    public int getAge() {
        if (dateOfBirth == null) {
            return 0;
        }
        return LocalDate.now().getYear() - dateOfBirth.getYear();
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setMailingAddress(MailingAddress mailingAddress) {
        this.mailingAddress = mailingAddress;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
}