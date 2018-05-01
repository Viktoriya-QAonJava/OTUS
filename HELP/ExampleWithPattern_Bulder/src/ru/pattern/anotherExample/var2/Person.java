package ru.pattern.anotherExample.var2;

//Реализация паттерна Bulder через внутренний класс
public class Person {

    private String lastName;
    private String firstName;
    private String middleName;
    private String salutation;
    private String suffix;
    private String streetAddress;
    private String city;
    private String state;
    private boolean isFemale;
    private boolean isEmployed;
    private boolean isHomeOwner;

    private Person() {
        // private constructor
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getSalutation() {
        return salutation;
    }

    public String getSuffix() {
        return suffix;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public boolean isFemale() {
        return isFemale;
    }

    public boolean isEmployed() {
        return isEmployed;
    }

    public boolean isHomeOwner() {
        return isHomeOwner;
    }

    public static Builder newBuilder() {
        return new Person().new Builder();
    }

    public class Builder {

        private Builder() {
            // private constructor
        }

        public Builder setLastName(String lastName) {
            Person.this.lastName = lastName;

            return this;
        }

        public Builder setFirstName(String firstName) {
            Person.this.firstName = firstName;

            return this;
        }

        public Builder setMiddleName(String middleName) {
            Person.this.middleName = middleName;

            return this;
        }

        public Builder setSalutation(String salutation) {
            Person.this.salutation = salutation;

            return this;
        }

        public Builder setSuffix(String suffix) {
            Person.this.suffix = suffix;

            return this;
        }

        public Builder setStreetAddress(String streetAddress) {
            Person.this.streetAddress = streetAddress;

            return this;
        }

        public Builder setCity(String city) {
            Person.this.city = city;

            return this;
        }

        public Builder setState(String state) {
            Person.this.state = state;

            return this;
        }

        public Builder setFemale(boolean isFemale) {
            Person.this.isFemale = isFemale;

            return this;
        }

        public Builder setEmployed(boolean isEmployed) {
            Person.this.isEmployed = isEmployed;

            return this;
        }

        public Builder setHomeOwner(boolean isHomeOwner) {
            Person.this.isHomeOwner = isHomeOwner;

            return this;
        }

        public Person build() {
            return Person.this;
        }

    }

}