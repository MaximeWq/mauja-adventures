package com.mauja.maujaadventures.modele;

import com.mauja.maujaadventures.modele.action.affiche.Affichable;

public abstract class Entite extends Affichable {

    private Position position;
    private Collision collision;
    private Dimension dimensions;

    /**
     * Constructeur de la classe Abstraite
     * @param position Position de l'entite
     * @param image Image que va posséder l'entite
     * @param collision Collision que l'entite va posséder
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    public Entite(Position position, String image, Collision collision){
        super(image);
        this.position = position;
        this.collision = collision;
        //this.dimensions = new Dimension((int) image.getLongueur(), (int) image.getHauteur());
        this.dimensions= new Dimension(32, 32);
    }
    /**
     * Getter de la position
     * @return Position de l'entite
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    public Position getPosition() { return position; }
    /**
     * Setter de la position
     * @param position Nouvelle position de l'entite
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    public void setPosition(Position position) { this.position = position; }
    /**
     * Getter de la collision
     * @return La zone de collision de l'entite
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    public Collision getCollision() { return collision; }
    /**
     * Setter de la collision
     * @param collision Nouvelle collision de l'entite
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    public void setCollision(Collision collision) { this.collision = collision; }
    /**
     * Getter de la dimension
     * @return La dimension (Hauteur et Largeur) de l'entite
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    public Dimension getDimensions() { return dimensions;}
    /**
     * Setter de la dimension
     * @param dimensions Nouvelle dimension (Hauteur et Largeur) de l'entite
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    public void setDimensions(Dimension dimensions) { this.dimensions = dimensions; }

    /**
     * Redéfinition du toString
     * @return chaîne que l'on veut afficher
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    @Override
    public String toString() {
        return "Position : " + position.toString() + ", "
                + "Dimensions : " + dimensions.toString() + ", "
                + "Collision : " + collision.toString();
    }

    /**
     * Redéfinition du hashCode
     * @return Hachage des attributs de Entite
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    @Override
    public int hashCode() {
        return 31*position.hashCode()+31*dimensions.hashCode()+31*collision.hashCode();
    }

    /**
     * Redéfinition du equals
     * @param obj Objet que l'on veut comparer
     * @return true si égalité sinon false
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    @Override
    public boolean equals(Object obj) {
        if(obj == null) return false;
        if(this == obj) return true;
        if (getClass() != obj.getClass()) return false;
        Entite autre = (Entite) obj;
        return equals(autre);
    }
    /**
     * Méthode equals
     * @param e Entité que l'on veut comparer
     * @return true si égalité sinon false
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    public boolean equals(Entite e) {
        boolean resultat = (position.equals(e.getPosition())) && (position.equals(e.getCollision())) &&
                (dimensions.equals(e.getDimensions()));
        return resultat;
    }
}
