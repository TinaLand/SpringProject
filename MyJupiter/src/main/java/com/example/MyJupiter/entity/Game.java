package com.example.MyJupiter.entity;

public class Game {
    private String name;
    private String developer;
    private String releaseTime;
    private String website;
    private double price;

    public  Game(Builder builder) {
        this.name = builder.name;
        this.developer = builder.developer;
        this.releaseTime = builder.releaseTime;
        this.website = builder.website;
        this.price = builder.price;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDeveloper() {
        return developer;
    }

    public void setDeveloper(String developer) {
        this.developer = developer;
    }

    public String getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(String releaseTime) {
        this.releaseTime = releaseTime;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    // innerclass
    public class Builder {
        private String name;
        private String developer;
        private String releaseTime;
        private String website;
        private double price;

        public void setName(String name) {
            this.name = name;
        }

        public void setDeveloper(String developer) {
            this.developer = developer;
        }

        public void setReleaseTime(String releaseTime) {
            this.releaseTime = releaseTime;
        }

        public void setWebsite(String website) {
            this.website = website;
        }

        public void setPrice(double price) {
            this.price = price;
        }
    }

    public Game build(Builder builder) {
        //Builder builder = new Builder();
        return new Game(builder);
    }
}
