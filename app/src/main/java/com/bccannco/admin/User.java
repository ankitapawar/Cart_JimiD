package com.bccannco.admin;

public class User
{
    String firstName, lastName;
    int age;

    public User(UserBuilder builder)
    {
        this.firstName = builder.first_Name;
        this.lastName = builder.last_Name;
        this.age = builder.age;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public int getAge()
    {
        return age;
    }

    public class UserBuilder
    {
        String first_Name, last_Name;
        int age;

        public UserBuilder setFirst_Name(String firstname)
        {
            this.first_Name = firstname;
            return  this;
        }

        public UserBuilder setLast_Name(String lastname)
        {
            this.last_Name = lastname;
            return  this;
        }

        public UserBuilder withOptionalAge(int age)
        {
            this.age = age;
            return this;
        }
        public User buildUser()
        {
            return new User(this);
        }
    }
}

//Here using above example user creat using below statement.


