package com.company;

public class Joke {
    public String category;
    public String type;
    public String joke;
    public String setup;
    public String delivery;
    public Integer id;
    public Boolean safe;
    public String language;
    public Boolean nsfw;
    public Boolean religious;
    public Boolean political;
    public Boolean racist;
    public Boolean sexist;
    public Boolean explicit;

    public Integer getJokeLength() {
        if (this.joke != null) {
            return this.joke.length();
        } else {
            return this.setup.length() + delivery.length();
        }
    }

    public String toString() {
        if (this.joke != null) {
            return this.joke;
        } else {
            return this.setup + " " + delivery;
        }
    }

}
