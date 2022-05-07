package com.mikkimesser.domain;

public enum Language {
    Spanish("Spanish"),
    Italian("Italian"),
    German("German"),
    Russian("Russian"),
    Kiswahili("Kiswahili");

    public final String name;

    Language(String name)
    {
        this.name = name;
    }
}
