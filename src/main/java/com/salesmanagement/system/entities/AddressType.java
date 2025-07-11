package com.salesmanagement.system.entities;


import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;

import java.util.Arrays;

@Getter
public enum AddressType {
    // Customer's main address
    HOME("Address", "Customer's primary residence address."),

    // Work address
    WORK("Work", "Address of place of business or office."),

    // Billing address
    BILLING("Invoicing", "Address for sending invoices and tax documents."),

    // Shipping address
    SHIPPING("Shipping", "Address for delivery of products and orders."),

    // Temporary or vacation address
    TEMPORARY("Temporary", "Temporary or short stay address."),

    // Address of family member or emergency contact
    EMERGENCY("Emergency", "Emergency contact address."),

    // Other address not categorized
    OTHER("Other", "Additional address not categorized in the above types.");

    @JsonCreator
    public static AddressType fromString(String value) {
        return Arrays.stream(AddressType.values())
                .filter(v -> v.name().equalsIgnoreCase(value.trim()) || v.displayName.equalsIgnoreCase(value.trim()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid address type: " + value));
    }



    //Getter
    private final String displayName;
    private final String description;

    AddressType(String displayName, String description){
        this.displayName=displayName;
        this.description=description;
    }

    //Method to obtain the type by name(case insensitive)
    public static AddressType fromDisplayName(String displayName){
        for( AddressType type:values()){
            if(type.displayName.equalsIgnoreCase(displayName)){return type;}
        }
        throw new IllegalArgumentException("Type of address Incorrect: "+displayName);
    }
    //Method to validate if a string is a valid type
    public static boolean isValidType(String typeName){
        try{
            //convert string to given type enum
            valueOf(typeName.toUpperCase());
            return true;
        }catch(IllegalArgumentException e){
            return false;
        }
    }

    //Method to get all display names
    public static String[] getAllDisplayNames(){
        AddressType[]types= values();
        String[]displayNames= new String[types.length];
        for(int i=0;i<types.length;i++){
            displayNames[i]=types[i].displayName;
        }
        return displayNames;
    }
    //Custom toString method
    @Override
    public String toString(){
        return displayName;
    }

    //Method to obtain complete information
    public String getFullInfo(){
        return displayName + ": "+ description;
    }

    //Method to check the specific type
    public boolean isHome(){
        return this==HOME;
    }
    public boolean isWork(){
        return this==WORK;
    }
    public boolean isBilling() {
        return this == BILLING;
    }

    public boolean isShipping() {
        return this == SHIPPING;
    }

    public boolean isTemporary() {
        return this == TEMPORARY;
    }

    public boolean isEmergency() {
        return this == EMERGENCY;
    }

    public boolean isOther() {
        return this == OTHER;
    }

    //Method to check if it's a commercial's direction
    public boolean isCommercial(){
        return this == WORK ||this==BILLING;
    }
    //Method to check if it's a personal's direction
    public boolean isPersona(){
        return this==HOME|| this==TEMPORARY || this==EMERGENCY;
    }

    //Method to check if it's a shipping's direction
    public boolean isDelivery(){
        return this==SHIPPING || this==HOME;
    }

}

