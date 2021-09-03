package com.example.autofine.models;

import com.example.autofine.models.CarData;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.math.BigDecimal;
import java.util.Date;

public class Fine {

    @SerializedName("id")
    @Expose
    private Long  id;

    @SerializedName("carDataId")
    @Expose
    private CarData carDataId;

    @SerializedName("dateOfViolation")
    @Expose
    private Date dateOfViolation;

    @SerializedName("typeOfViolation")
    @Expose
    private String typeOfViolation;


    @SerializedName("decreeNumber")
    @Expose
    private String decreeNumber;

    @SerializedName("amountOfTheFine")
    @Expose
    private BigDecimal amountOfTheFine;

    @SerializedName("payment")
    @Expose
    private Boolean payment;

//    @SerializedName("image")
//    @Expose
//    private byte[] image;
    @SerializedName("image")
    @Expose
    private String image;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CarData getCarDataId() {
        return carDataId;
    }

    public void setCarDataId(CarData carDataId) {
        this.carDataId = carDataId;
    }

    public Date getDateOfViolation() {
        return dateOfViolation;
    }

    public void setDateOfViolation(Date dateOfViolation) {
        this.dateOfViolation = dateOfViolation;
    }

    public String getTypeOfViolation() {
        return typeOfViolation;
    }

    public void setTypeOfViolation(String typeOfViolation) {
        this.typeOfViolation = typeOfViolation;
    }

    public String getDecreeNumber() {
        return decreeNumber;
    }

    public void setDecreeNumber(String decreeNumber) {
        this.decreeNumber = decreeNumber;
    }

    public BigDecimal getAmountOfTheFine() {
        return amountOfTheFine;
    }

    public void setAmountOfTheFine(BigDecimal amountOfTheFine) {
        this.amountOfTheFine = amountOfTheFine;
    }

    public Boolean getPayment() {
        return payment;
    }

    public void setPayment(Boolean payment) {
        this.payment = payment;
    }

//    public byte[] getImage() {
//        return image;
//    }
//
//    public void setImage(byte[] image) {
//        this.image = image;
//    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
