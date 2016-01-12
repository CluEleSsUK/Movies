package com.patrickmcclurg.skymovies.model;

public class Rating {

    int ratingLevel;

    public Rating(String label) {
        convertLabelToNumber(label);
    }

    //string ratings are converted to integers for cleaner comparison
    private void convertLabelToNumber(String label) {
        switch (label) {
            case "U":
                ratingLevel = 1;
                break;
            case "PG":
                ratingLevel = 2;
                break;
            case "12":
                ratingLevel = 3;
                break;
            case "15":
                ratingLevel = 4;
                break;
            case "18":
                ratingLevel = 5;
                break;
            default:
                ratingLevel = 0;
        }
    }

    public int getRatingLevel() {
        return ratingLevel;
    }
}
