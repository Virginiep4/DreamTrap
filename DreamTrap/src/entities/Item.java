package entities;

public class Item  {
 private String nom;
 private float prix;
 private String description;
 private int acquis;
 private int id;
 private Character character;

 public Item(int id,String nom,float prix,String description,int acquis) {
	 this.id=id;
	 this.nom=nom;
	 this.prix=prix;
	 this.description=description;
	 this.acquis=acquis;
	 
 }
 public Item(String nom,float prix,String description,int acquis) {
	
	 this.nom=nom;
	 this.prix=prix;
	 this.description=description;
	 this.acquis=acquis;
	 
 }
 
 public boolean acheterItem(float prix) {
	 int etoile=character.getEtoiles();
     if (character.getEtoiles() >= prix) {
    	   etoile-= prix; // Déduire le prix des étoiles
    	 character.setEtoiles(etoile);
         return true;
     }
     return false; 
 }

 public boolean isAcquis() {
	 if (acquis==0) { return false;}
	 else {return true;}
    
 }
 

public int getId() {
	return id;
}


public String getDesciption() {
	return description;
}
public void setDesciption(String desciption) {
	this.description = desciption;
}
public int getAcquis() {
	return acquis;
}
public void setAcquis(int acquis) {
	this.acquis = acquis;
}
public String getNom() {
	return nom;
}

public float getPrix() {
	return prix;
}

@Override
public String toString() {
	return "Item [nom=" + nom + ", prix=" + prix + "]";
}

 
}
