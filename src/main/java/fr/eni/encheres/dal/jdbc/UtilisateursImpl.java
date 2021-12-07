package fr.eni.encheres.dal.jdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.eni.encheres.bo.Utilisateur;
import fr.eni.encheres.dal.DALException;
import fr.eni.encheres.dal.UtilisateursDAO;

public class UtilisateursImpl implements UtilisateursDAO {

	@Override
	public Utilisateur getUtilisateurById(int id) throws DALException {
		try (PreparedStatement statement = GetConnection.getConnexion().prepareStatement(
				"select no_utilisateur, pseudo, nom, prenom, email, telephone, rue, code_postal, ville, credit, administrateur "
						+ "from dbo.utilisateurs " + "where no_utilisateur=?")) {
			statement.setInt(1, id);
			try (ResultSet resultSet = statement.executeQuery()) {
				resultSet.next();
				Utilisateur utilisateur = new Utilisateur(resultSet.getInt("no_utilisateur"),
						resultSet.getString("pseudo"), resultSet.getString("nom"), resultSet.getString("prenom"),
						resultSet.getString("email"), resultSet.getString("telephone"), resultSet.getString("rue"),
						resultSet.getString("code_postal"), resultSet.getString("ville"), resultSet.getInt("credit"),
						resultSet.getBoolean("administrateur"));
				return utilisateur;
			}
		} catch (SQLException e) {
			throw new DALException(e.getMessage(), e);
		}
	}

	@Override
	public List<Utilisateur> getAllUtilisateurs() throws DALException {
		List<Utilisateur> utilisateurs = new ArrayList<>();
		try (PreparedStatement statement = GetConnection.getConnexion().prepareStatement(
				"select no_utilisateur, pseudo, nom, prenom, email, telephone, rue, code_postal, ville, credit, administrateur "
						+ "from dbo.utilisateurs ")) {
			try (ResultSet resultSet = statement.executeQuery()) {
				while (resultSet.next()) {
					Utilisateur utilisateur = new Utilisateur(resultSet.getInt("no_utilisateur"),
							resultSet.getString("pseudo"), resultSet.getString("nom"), resultSet.getString("prenom"),
							resultSet.getString("email"), resultSet.getString("telephone"), resultSet.getString("rue"),
							resultSet.getString("code_postal"), resultSet.getString("ville"),
							resultSet.getInt("credit"), resultSet.getBoolean("administrateur"));
					utilisateurs.add(utilisateur);
				}
			}
		} catch (SQLException e) {
			throw new DALException(e.getMessage(), e);
		}

		return utilisateurs;
	}

	@Override
	public void addUtilisateur(Utilisateur utilisateur) throws DALException {
		try (PreparedStatement statement = GetConnection.getConnexion()
				.prepareStatement("INSERT INTO dbo.utilisateurs "
						+ "(pseudo, nom, prenom, email, telephone, rue, code_postal, ville, credit, administrateur) "
						+ "VALUES (?,?,?,?,?,?,?,?,?,?)", new String[] { "id_panier" })) {

			int i = 1;
			statement.setString(i++, utilisateur.getPseudo());
			statement.setString(i++, utilisateur.getNom());
			statement.setString(i++, utilisateur.getPrenom());
			statement.setString(i++, utilisateur.getEmail());
			statement.setString(i++, utilisateur.getTelephone());
			statement.setString(i++, utilisateur.getRue());
			statement.setString(i++, utilisateur.getCodePostal());
			statement.setString(i++, utilisateur.getVille());
			statement.setInt(i++, utilisateur.getCredit());
			statement.setBoolean(i++, utilisateur.isAdministrateur());

			statement.executeUpdate();
			try (ResultSet resultSet = statement.getGeneratedKeys()) {
				resultSet.next();
				int idUtilisateur = resultSet.getInt(1);
				utilisateur.setNoUtilisateur(idUtilisateur);
			}
		} catch (SQLException e) {
			throw new DALException(e.getMessage(), e);
		}
	}

	@Override
	public void updateUtilisateur(Utilisateur utilisateur) throws DALException {
		try (PreparedStatement statement = GetConnection.getConnexion().prepareStatement("UPDATE dbo.utilisateurs "
				+ "SET pseudo=?, nom=?, prenom=?, email=?, telephone=?, rue=?, code_postal=?, ville=?, credit=?, administrateur=? "
				+ "WHERE no_utilisateur=?")) {
			int i = 1;
			statement.setString(i++, utilisateur.getPseudo());
			statement.setString(i++, utilisateur.getNom());
			statement.setString(i++, utilisateur.getPrenom());
			statement.setString(i++, utilisateur.getEmail());
			statement.setString(i++, utilisateur.getTelephone());
			statement.setString(i++, utilisateur.getRue());
			statement.setString(i++, utilisateur.getCodePostal());
			statement.setString(i++, utilisateur.getVille());
			statement.setInt(i++, utilisateur.getCredit());
			statement.setBoolean(i++, utilisateur.isAdministrateur());

			statement.setInt(i++, utilisateur.getNoUtilisateur());

			statement.executeUpdate();
		} catch (SQLException e) {
			throw new DALException(e.getMessage(), e);
		}

	}

	@Override
	public void removeUtilisateur(Utilisateur utilisateur) throws DALException {

		try (PreparedStatement statement = GetConnection.getConnexion()
				.prepareStatement("DELETE FROM dbo.utilisateurs WHERE no_utilisateur=?")) {
			statement.setInt(1, utilisateur.getNoUtilisateur());
			statement.executeUpdate();
		} catch (SQLException e) {
			throw new DALException(e.getMessage(), e);
		}

	}

}
