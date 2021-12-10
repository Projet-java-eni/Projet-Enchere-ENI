package fr.eni.encheres.bo;

public interface MeHasMany<T> {
	void ajouter(T generique);
	void supprimer(T generique);
}
