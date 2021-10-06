package it.spaceapps21.latitude.covid19.virusvaccines.classes;

public class ReccomendationElement {

    private String description;
    private int imageID;

    public ReccomendationElement(String description, int imageID){
        this.description = description;
        this.imageID = imageID;
    }

    public int getImageID(){return imageID;}
    public String getDescription(){return description;}

}
