// @author Lucie
package fr.eni.encheres.dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import fr.eni.encheres.bo.Enchere;
import fr.eni.encheres.bo.Utilisateur;
import fr.eni.encheres.bo.ArticleVendu;
import fr.eni.encheres.dal.DALException;
import fr.eni.encheres.dal.DAO;


public class EncheresImpl implements DAO<Enchere> {

	String sqlInsertEnchere = "INSERT INTO encheres (no_utilisateur, no_article, date_enchere, montant_enchere)  VALUES (? , ? , ? , ?)";
	String sqlSelectEncheresByNoUtilisateur = "SELECT no_utilisateur, no_article, date_enchere, montant_enchere FROM encheres WHERE no_utilisateur = ? ";
	String sqlSelectEncheresByNoArticle = "SELECT no_utilisateur, no_article, date_enchere, montant_enchere FROM encheres WHERE no_article = ? ";
	String sqlSelectEncheresByNoUtilisateurEtNoArticle = "SELECT no_utilisateur, no_article, date_enchere, montant_enchere FROM encheres WHERE no_utilisateur = ? AND no_article = ? ";
	String sqlSelectAllEncheres = "SELECT no_utilisateur, no_article, date_enchere, montant_enchere FROM encheres";
	String sqlUpdateEnchere = "UPDATE encheres SET no_utilisateur = ?, no_article = ?, date_enchere = ?, montant_enchere = ? WHERE no_utilisateur = ? AND no_article = ?";
	String sqlDeleteEnchereByNoUtilisateurEtNoArticle = "DELETE FROM encheres WHERE no_utilisateur = ? AND no_article = ? ";
	
	
	@Override
	public void add(Enchere e) throws DALException {
			try (	
					Connection con = GetConnection.getConnexion();
					PreparedStatement pstmt = con.prepareStatement(sqlDeleteEnchereByNoUtilisateurEtNoArticle);		
				){
					
					pstmt.setInt(1, e.getUtilisateur().getNoUtilisateur());
					pstmt.setInt(2, e.getArticleVendu().getNoArticle());
					pstmt.setObject(3,(LocalDate) e.getDateEnchere());
					pstmt.setInt(4, e.getMontantEnchere());
					
					pstmt.execute();
			}catch(SQLException ex){
					throw new DALException(sqlDeleteEnchereByNoUtilisateurEtNoArticle, ex);
			}
	}
	
	public List<Enchere> selectEncheresByNoUtilisateur(int noUtilisateur) throws DALException {
		
		try(	
				Connection con = GetConnection.getConnexion();
				PreparedStatement pstmt = con.prepareStatement(sqlSelectEncheresByNoUtilisateur);
			){
				pstmt.setInt(1, noUtilisateur);
				try(ResultSet rs =  pstmt.executeQuery();){
					List<Enchere> encheres = new ArrayList<Enchere>();			
					Enchere e = null;
					if(rs.next()) {
						Utilisateur utilisateur = new Utilisateur(rs.getInt(1));
						ArticleVendu article = new ArticleVendu(rs.getInt(2));
						e = new Enchere(utilisateur, article, (LocalDate) rs.getObject(3), rs.getInt(4));
						encheres.add(e);
					}
					return encheres;	
				}
		} catch (SQLException ex) {
						throw new DALException(sqlSelectEncheresByNoUtilisateur, ex);
		}
			
	}
	
	public List<Enchere> selectEncheresByNoArticle(int noArticle) throws DALException {
		try(	
				Connection con = GetConnection.getConnexion();
				PreparedStatement pstmt = con.prepareStatement(sqlSelectEncheresByNoArticle);
			){
				pstmt.setInt(1, noArticle);
				try(ResultSet rs = pstmt.executeQuery();){
					List<Enchere> encheres = new ArrayList<Enchere>();			
					Enchere e = null;
					if(rs.next()) {
						Utilisateur utilisateur = new Utilisateur(rs.getInt(1));
						ArticleVendu article = new ArticleVendu(rs.getInt(2));
						e = new Enchere(utilisateur, article, (LocalDate) rs.getObject(3), rs.getInt(4));
						encheres.add(e);
					}
					return encheres;	
				}	
		}catch (SQLException ex) {
						throw new DALException(sqlSelectEncheresByNoArticle, ex);
		}
	}

	public List<Enchere> selectEncheresByNoUtilisateurEtNoArticle(int noUtilisateur, int noArticle)throws DALException {
		try(	
				Connection con = GetConnection.getConnexion();
				PreparedStatement pstmt = con.prepareStatement(sqlSelectEncheresByNoUtilisateurEtNoArticle);
			){
				pstmt.setInt(1, noUtilisateur);
				pstmt.setInt(2, noArticle);
				try(ResultSet rs =  pstmt.executeQuery();){
					List<Enchere> encheres = new ArrayList<Enchere>();			
					Enchere e = null;
					if(rs.next()) {
						Utilisateur utilisateur = new Utilisateur(rs.getInt(1));
						ArticleVendu article = new ArticleVendu(rs.getInt(2));
						e = new Enchere(utilisateur, article, (LocalDate) rs.getObject(3), rs.getInt(4));
						encheres.add(e);
					}
					return encheres;	
				}
		}	
		catch (SQLException ex) {
						throw new DALException(sqlSelectEncheresByNoUtilisateurEtNoArticle, ex);
		}
	}
	
	@Override
	public List<Enchere> getAll() throws DALException {
		try (
				Connection con = GetConnection.getConnexion();
				Statement stmt = con.createStatement();
				ResultSet rs =  stmt.executeQuery(sqlSelectAllEncheres);	
			){
				List<Enchere> encheres=new ArrayList<Enchere>();			
				Enchere e = null;
				
				while(rs.next()){
					Utilisateur utilisateur = new Utilisateur(rs.getInt(1));
					ArticleVendu article = new ArticleVendu(rs.getInt(2));
					e = new Enchere(utilisateur, article, (LocalDate) rs.getObject(3), rs.getInt(4));
					encheres.add(e);
				}
				return encheres;
		} catch (SQLException ex) {
				throw new DALException(ex.getMessage(), ex);
		}
	}
	
	@Override

	public void update(Enchere e) throws DALException {
			try (
					Connection con = GetConnection.getConnexion();
					PreparedStatement pstmt = con.prepareStatement(sqlUpdateEnchere);
				){
					pstmt.setInt(1, e.getUtilisateur().getNoUtilisateur());
					pstmt.setInt(2, e.getArticleVendu().getNoArticle());
					pstmt.setObject(3,(LocalDate) e.getDateEnchere());
					pstmt.setInt(4, e.getMontantEnchere());
					
					pstmt.execute();
			}catch (SQLException ex) {
					throw new DALException(sqlUpdateEnchere, ex);
			}
	}
	
	//à utiliser à la place de remove
	public void deleteEnchereByNoUtilisateurEtNoArticle(int noUtilisateur, int noArticle) throws DALException {
			try (	
					Connection con = GetConnection.getConnexion();
					PreparedStatement pstmt = con.prepareStatement(sqlDeleteEnchereByNoUtilisateurEtNoArticle);		
				){ 
					pstmt.setInt(1, noUtilisateur);
					pstmt.setInt(2, noArticle);
					
					pstmt.execute();
			}catch (SQLException ex) {
						throw new DALException(sqlDeleteEnchereByNoUtilisateurEtNoArticle, ex);
				}
	}

	@Override
	public void remove(Enchere utilisateur) throws DALException {
		// TODO Auto-generated method stub
		
	}

}
