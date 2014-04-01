package com.tree;

/**
 * Cette classe représente un arbre d'objet de type générique T. 
 * </br>L'arbre est représenté par un simple noeud racine qui pointe vers une List<NodeMove<T>> représentant ses fils.
 * </br>Il n'y a pas de restriction sur le nombre de fils qu'un noeud particulier peut avoir.
 */


public class TreeMove<T> {
 
	/**
	 * Le noeud racine de l'arbre.
	 */
    private NodeMove<T> rootElement;
    
    /**
     * La sentinelle permettant de parcourir l'arbre pour l'IA.
     */
    private NodeMove<T> sentinel;
     
    /**
     * Constructeur de la classe
     */
    public TreeMove() {
        super();
    }
 
    /**
     * Renvoie le noeud racine de l'arbre
     * @return le noeud racine.
     */
    public NodeMove<T> getRootElement() {
        return this.rootElement;
    }
 
    /**
     * Modifie le noeud racine de l'arbre.
     * @param rootElement : NodeMove<T> le nouveau noeud racine.
     */
    public void setRootElement(NodeMove<T> rootElement) {
        this.rootElement = rootElement;
    }
     
    /**
     * Renvoie la sentinelle.
     * @return la sentinelle.
     */
    public NodeMove<T> getSentinel() {
		return sentinel;
	}

    /**
     * Modifie la sentinelle
     * @param sentinel : NodeMove<T> la nouvelle sentinelle.
     */
	public void setSentinel(NodeMove<T> sentinel) {
		this.sentinel = sentinel;
	}

}

