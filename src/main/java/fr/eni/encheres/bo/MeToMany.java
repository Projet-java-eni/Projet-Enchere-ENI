package fr.eni.encheres.bo;

public interface MeToMany<T> {
	void ajouter(T generique);
	void supprimer(T generique);
}
