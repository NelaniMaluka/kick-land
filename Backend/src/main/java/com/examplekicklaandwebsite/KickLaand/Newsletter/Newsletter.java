package com.examplekicklaandwebsite.KickLaand.Newsletter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Pattern;

@Entity
public class Newsletter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int newsletterId;

    @Pattern(regexp = "^\\+?[0-9\\-\\s]*$", message = "Please provide a valid phone number")
    public String email;

    public void setEmail(String email) {
        this.email = email;
    }

    public int getNewsletterId() {
        return newsletterId;
    }

    public void setNewsletterId(int newsletterId) {
        this.newsletterId = newsletterId;
    }

    public String getEmail() {
        return email;
    }
}
