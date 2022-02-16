package com.amirkenesbay.parser.model;

public class Search {
    private String snippet;

    public Search(String snippet) {
        this.snippet = snippet;
    }

    public String getSnippet() {
        return snippet;
    }

    public void setSnippet(String snippet) {
        this.snippet = snippet;
    }

    @Override
    public String toString() {
        return snippet + "\n";
    }
}
