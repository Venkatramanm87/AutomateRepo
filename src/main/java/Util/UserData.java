package Util;

public class UserData {
    private String name;
    private String job;

    // Default No-Arg Constructor (Required by Jackson for JSON deserialization)
    public UserData() {}

    // Parameterized Constructor (Fixes 'constructor undefined' error)
    public UserData(String name, String job) {
        this.name = name;
        this.job = job;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }
}