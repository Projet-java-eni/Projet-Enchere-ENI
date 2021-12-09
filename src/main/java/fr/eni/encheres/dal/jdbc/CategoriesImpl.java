package fr.eni.encheres.dal.jdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.eni.encheres.bo.Categorie;
import fr.eni.encheres.bo.Utilisateur;
import fr.eni.encheres.dal.DALException;
import fr.eni.encheres.dal.DAO;

enum StoredStatements {
	GET_BY_ID("select no_categorie, libelle from dbo.utilisateurs where no_utilisateur=?"),
	SELECT_ALL("select no_categorie, libelle from dbo.utilisateurs"),
	INSERT("INSERT INTO dbo.utilisateurs (libelle) VALUES (?)"),
	UPDATE("UPDATE dbo.utilisateurs SET libelle=? WHERE no_categorie=?"),
	DELETE("DELETE FROM dbo.utilisateurs WHERE no_categorie=?");
	private String value; 
	StoredStatements(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return this.value;
	}
}

public class CategoriesImpl implements DAO<Categorie> {

	@Override
	public Categorie getById(int id) throws DALException {
		
		try (PreparedStatement statement = GetConnection.getConnexion().prepareStatement(StoredStatements.GET_BY_ID.getValue())) {
			
			statement.setInt(1, id);
			
			try (ResultSet resultSet = statement.executeQuery()) {
				
				resultSet.next();
				
				return new Categorie(resultSet.getInt("no_categorie"),
						resultSet.getString("libelle"));
			}
			
		} catch (SQLException e) {
			throw new DALException(e.getMessage(), e);
		}
	}

	
	@Override
	public List<Categorie> getAll() throws DALException {
		
		List<Categorie> categories = new ArrayList<>();
		
		try (PreparedStatement statement = GetConnection.getConnexion().prepareStatement(
				StoredStatements.SELECT_ALL.getValue())) {
			
			try (ResultSet resultSet = statement.executeQuery()) {
				
				while (resultSet.next()) {
					Categorie categorie = new Categorie(
							resultSet.getInt("no_categorie"),
							resultSet.getString("libelle")
					);
					
					categories.add(categorie);
				}
			}
			
		} catch (SQLException e) {
			
			throw new DALException(e.getMessage(), e);
		}

		return categories;
	}

	@Override
	public void add(Categorie categorie) throws DALException {
	
		try (PreparedStatement statement = GetConnection.getConnexion()
				.prepareStatement(StoredStatements.INSERT.getValue(), new String[] { "no_categorie" })) {

			statement.setString(1, categorie.getLibelle());

			statement.executeUpdate();
			
			try (ResultSet resultSet = statement.getGeneratedKeys()) {
				resultSet.next();
				int idUtilisateur = resultSet.getInt(1);
				categorie.setId(idUtilisateur);
			}
		} catch (SQLException e) {
			throw new DALException(e.getMessage(), e);
		}

	}

	@Override
	public void update(Categorie utilisateur) throws DALException {
		
		try (PreparedStatement statement = GetConnection.getConnexion().prepareStatement(StoredStatements.UPDATE.getValue())) {
			
			statement.setString(1, utilisateur.getLibelle());

			statement.setInt(2, utilisateur.getId());

			statement.executeUpdate();
		} catch (SQLException e) {
			throw new DALException(e.getMessage(), e);
		}
		
	}

	@Override
	public void remove(Categorie utilisateur) throws DALException {
		
		try (PreparedStatement statement = GetConnection.getConnexion()
				.prepareStatement(StoredStatements.DELETE.getValue())) {
			
			statement.setInt(1, utilisateur.getId());
			statement.executeUpdate();
		
		} catch (SQLException e) {
			throw new DALException(e.getMessage(), e);
		}
		
	}
	
}
