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

	enum StoredStatements {
		GET_BY_ID("select no_utilisateur, pseudo, nom, prenom, email, telephone, rue, " +
				"code_postal, ville, credit, administrateur, actif from dbo.utilisateurs where no_utilisateur=?"),
		GET_BY_PSEUDO("select no_utilisateur, pseudo, nom, prenom, email, telephone, rue, " +
				"code_postal, ville, credit, administrateur, actif from dbo.utilisateurs where pseudo=?"),
		GET_BY_PSEUDO_AND_MDP("select no_utilisateur, pseudo, nom, prenom, email, telephone, rue, " +
				"code_postal, ville, credit, administrateur, actif from dbo.utilisateurs where pseudo=? and mot_de_passe=?"),
		SELECT_ALL("select no_utilisateur, pseudo, nom, prenom, email, telephone, rue, " +
				"code_postal, ville, credit, administrateur, actif from dbo.utilisateurs"),
		INSERT("INSERT INTO dbo.utilisateurs (pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur, actif) " +
				"VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"),
		UPDATE("UPDATE dbo.utilisateurs SET pseudo=?, nom=?, prenom=?, email=?, telephone=?, rue=?, code_postal=?, " +
				"ville=?, credit=?, administrateur=?, actif=? WHERE no_utilisateur=?"),
		DELETE("DELETE FROM dbo.utilisateurs WHERE no_utilisateur=?");

		private final String value;

		StoredStatements(String value) {
			this.value = value;
		}
	}


	@Override
	public Utilisateur getById(int id) throws DALException {
		try (PreparedStatement statement = GetConnection.getConnexion().prepareStatement(
				StoredStatements.GET_BY_ID.value)) {
			statement.setInt(1, id);
			try (ResultSet resultSet = statement.executeQuery()) {
				resultSet.next();
				return new Utilisateur(resultSet.getInt("no_utilisateur"),
						resultSet.getString("pseudo"), resultSet.getString("nom"), resultSet.getString("prenom"),
						resultSet.getString("email"), resultSet.getString("telephone"), resultSet.getString("rue"),
						resultSet.getString("code_postal"), resultSet.getString("ville"), resultSet.getInt("credit"),
						resultSet.getBoolean("administrateur"), resultSet.getBoolean("actif"));
			}
		} catch (SQLException e) {
			throw new DALException(e.getMessage(), e);
		}
	}

	@Override
	public Utilisateur getByPseudo(String pseudo) throws DALException {
		try (PreparedStatement statement = GetConnection.getConnexion().prepareStatement(
				StoredStatements.GET_BY_PSEUDO.value)) {
			statement.setString(1, pseudo);
			try (ResultSet resultSet = statement.executeQuery()) {
				resultSet.next();
				return new Utilisateur(resultSet.getInt("no_utilisateur"),
						resultSet.getString("pseudo"), resultSet.getString("nom"), resultSet.getString("prenom"),
						resultSet.getString("email"), resultSet.getString("telephone"), resultSet.getString("rue"),
						resultSet.getString("code_postal"), resultSet.getString("ville"), resultSet.getInt("credit"),
						resultSet.getBoolean("administrateur"), resultSet.getBoolean("actif"));
			}
		} catch (SQLException e) {
			throw new DALException(e.getMessage(), e);
		}
	}

	@Override
	public Utilisateur getByPseudoEtMotDePasse(String utilisateurPseudo, String motDePasse) throws DALException {
		try (PreparedStatement statement = GetConnection.getConnexion().prepareStatement(StoredStatements.GET_BY_PSEUDO_AND_MDP.value)) {

			statement.setString(1, utilisateurPseudo);
			statement.setString(2, motDePasse);

			try (ResultSet resultSet = statement.executeQuery()) {
				resultSet.next();
				return new Utilisateur(resultSet.getInt("no_utilisateur"),
						resultSet.getString("pseudo"), resultSet.getString("nom"), resultSet.getString("prenom"),
						resultSet.getString("email"), resultSet.getString("telephone"), resultSet.getString("rue"),
						resultSet.getString("code_postal"), resultSet.getString("ville"), resultSet.getInt("credit"),
						resultSet.getBoolean("administrateur"), resultSet.getBoolean("actif"));
			}
		} catch (SQLException e) {
			throw new DALException(e.getMessage(), e);
		}
	}

	@Override
	public List<Utilisateur> getAll() throws DALException {
		List<Utilisateur> utilisateurs = new ArrayList<>();
		try (PreparedStatement statement = GetConnection.getConnexion().prepareStatement(
				StoredStatements.SELECT_ALL.value)) {
			try (ResultSet resultSet = statement.executeQuery()) {
				while (resultSet.next()) {
					Utilisateur utilisateur = new Utilisateur(resultSet.getInt("no_utilisateur"),
							resultSet.getString("pseudo"), resultSet.getString("nom"), resultSet.getString("prenom"),
							resultSet.getString("email"), resultSet.getString("telephone"), resultSet.getString("rue"),
							resultSet.getString("code_postal"), resultSet.getString("ville"),
							resultSet.getInt("credit"), resultSet.getBoolean("administrateur"), resultSet.getBoolean("actif"));
					utilisateurs.add(utilisateur);
				}
			}
		} catch (SQLException e) {
			throw new DALException(e.getMessage(), e);
		}

		return utilisateurs;
	}

	@Override
	public void addUtilisateurSecurise(Utilisateur utilisateur, String motDePasse) throws DALException {
		try (PreparedStatement statement = GetConnection.getConnexion()
				.prepareStatement(StoredStatements.INSERT.value, new String[] { "no_utilisateur" })) {

			int i = 1;
			statement.setString(i++, utilisateur.getPseudo());
			statement.setString(i++, utilisateur.getNom());
			statement.setString(i++, utilisateur.getPrenom());
			statement.setString(i++, utilisateur.getEmail());
			statement.setString(i++, utilisateur.getTelephone());
			statement.setString(i++, utilisateur.getRue());
			statement.setString(i++, utilisateur.getCodePostal());
			statement.setString(i++, utilisateur.getVille());
			statement.setString(i++, motDePasse);
			statement.setInt(i++, utilisateur.getCredit());
			statement.setBoolean(i, utilisateur.isAdministrateur());

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
	public void update(Utilisateur utilisateur) throws DALException {
		try (PreparedStatement statement = GetConnection.getConnexion().prepareStatement(StoredStatements.UPDATE.value)) {
			statement.setString(1, utilisateur.getPseudo());
			statement.setString(2, utilisateur.getNom());
			statement.setString(3, utilisateur.getPrenom());
			statement.setString(4, utilisateur.getEmail());
			statement.setString(5, utilisateur.getTelephone());
			statement.setString(6, utilisateur.getRue());
			statement.setString(7, utilisateur.getCodePostal());
			statement.setString(8, utilisateur.getVille());
			statement.setInt(9, utilisateur.getCredit());
			statement.setBoolean(10, utilisateur.isAdministrateur());
			statement.setBoolean(11, utilisateur.isActif());

			statement.setInt(12, utilisateur.getNoUtilisateur());

			statement.executeUpdate();
		} catch (SQLException e) {
			throw new DALException(e.getMessage(), e);
		}

	}

	@Override
	public void remove(Utilisateur utilisateur) throws DALException {

		try (PreparedStatement statement = GetConnection.getConnexion()
				.prepareStatement(StoredStatements.DELETE.value)) {
			statement.setInt(1, utilisateur.getNoUtilisateur());
			statement.executeUpdate();
		} catch (SQLException e) {
			throw new DALException(e.getMessage(), e);
		}

	}

	@Override
	public void add(Utilisateur utilisateur) throws DALException {
		addUtilisateurSecurise(utilisateur, "");		
	}

}
