// @author Lucie
package fr.eni.encheres.dal.jdbc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.eni.encheres.bo.Enchere;
import fr.eni.encheres.dal.DALException;
import fr.eni.encheres.dal.EncheresDAO;

public class EncheresImpl implements EncheresDAO {

	String sqlInsertEnchere = "INSERT INTO encheres (no_utilisateur, no_article, date_enchere, montant_enchere)  VALUES (? , ? , ? , ?)";
	String sqlSelectEnchereByNoUtilisateur = "SELECT no_utilisateur, no_article, date_enchere, montant_enchere FROM encheres WHERE no_utilisateur = ? ";
	String sqlSelectEnchereByNoArticle = "SELECT no_utilisateur, no_article, date_enchere, montant_enchere FROM encheres WHERE no_article = ? ";
	String sqlSelectEnchereByNoUtilisateurEtNoArticle = "SELECT no_utilisateur, no_article, date_enchere, montant_enchere FROM encheres WHERE no_utilisateur = ? AND no_article = ? ";
	String sqlSelectAllEncheres = "SELECT no_utilisateur, no_article, date_enchere, montant_enchere FROM encheres";
	String sqlUpdateEnchere = "UPDATE encheres SET no_utilisateur = ?, no_article = ?, date_enchere = ?, montant_enchere = ? FROM encheres WHERE no_utilisateur = ? AND no_article = ?";
	String sqlDeleteEnchereByNoUtilisateurEtNoArticle = "DELETE FROM encheres WHERE no_utilisateur = ? AND no_article = ? ";
	
	
	@Override
	public void insertEnchere(Enchere e) throws DALException {
			try (	
					Connection con = GetConnection.getConnexion();
					PreparedStatement pstmt = con.prepareStatement(sqlDeleteEnchereByNoUtilisateurEtNoArticle);		
				){
					pstmt.setInt(1, e.getNoUtilisateur());
					pstmt.setInt(2, e.getNoArticle());
					pstmt.setDate(3, (Date) e.getDateEnchere());
					pstmt.setInt(4, e.getMontantEnchere());
					
					pstmt.execute();
			}catch(SQLException ex){
					throw new DALException(sqlDeleteEnchereByNoUtilisateurEtNoArticle, ex);
			}
	}
	
	@Override
	public Enchere selectEnchereByNoUtilisateur(int noUtilisateur) throws DALException {
		
		try(	
				Connection con = GetConnection.getConnexion();
				PreparedStatement pstmt = con.prepareStatement(sqlSelectEnchereByNoUtilisateur);
			){
				pstmt.setInt(1, noUtilisateur);
				try(ResultSet rs =  pstmt.executeQuery();){
						Enchere enchereAAfficher = null;
						if(rs.next()) {
						enchereAAfficher = new Enchere(rs.getInt(1), rs.getInt(2), rs.getDate(3), rs.getInt(4));
						}
						return enchereAAfficher;	
				}
		} catch (SQLException ex) {
						throw new DALException(sqlSelectEnchereByNoUtilisateur, ex);
		}
			
	}
	
	@Override
	public Enchere selectEnchereByNoArticle(int noArticle) throws DALException {
		try(	
				Connection con = GetConnection.getConnexion();
				PreparedStatement pstmt = con.prepareStatement(sqlSelectEnchereByNoArticle);
			){
				pstmt.setInt(1, noArticle);
				try(ResultSet rs = pstmt.executeQuery();){
						Enchere enchereAAfficher = null;
						if(rs.next()) {
						enchereAAfficher = new Enchere(rs.getInt(1), rs.getInt(2), rs.getDate(3), rs.getInt(4));
						}
						return enchereAAfficher;	
				}	
		}catch (SQLException ex) {
						throw new DALException(sqlSelectEnchereByNoArticle, ex);
		}
	}

	@Override
	public Enchere selectEnchereByNoUtilisateurEtNoArticle(int noUtilisateur, int noArticle)throws DALException {
		try(	
				Connection con = GetConnection.getConnexion();
				PreparedStatement pstmt = con.prepareStatement(sqlSelectEnchereByNoUtilisateurEtNoArticle);
			){
				pstmt.setInt(1, noUtilisateur);
				pstmt.setInt(2, noArticle);
				try(ResultSet rs =  pstmt.executeQuery();){
					Enchere enchereAAfficher = null;
					if(rs.next()) {
						enchereAAfficher = new Enchere(rs.getInt(1), rs.getInt(2), rs.getDate(3), rs.getInt(4));
					}
					return enchereAAfficher;	
				}
		}	
		catch (SQLException ex) {
						throw new DALException(sqlSelectEnchereByNoUtilisateurEtNoArticle, ex);
		}
	}
	
	@Override
	public List<Enchere> selectAllEncheres() throws DALException {
		try (
				Connection con = GetConnection.getConnexion();
				Statement stmt = con.createStatement();
				ResultSet rs =  stmt.executeQuery(sqlSelectAllEncheres);	
			){
				List<Enchere> encheres=new ArrayList<Enchere>();			
				Enchere e = null;
				
				while(rs.next()){
					e = new Enchere(rs.getInt(1), rs.getInt(2), rs.getDate(3), rs.getInt(4));
					encheres.add(e);
				}
				return encheres;
		} catch (SQLException ex) {
				throw new DALException(ex.getMessage(), ex);
		}
	}

	@Override
	public void deleteEnchereByNoUtilisateurEtNoArticle(int noUtilisateur, int noArticle)throws DALException {
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
	public void updateEnchere(Enchere e) throws DALException {
			try (
					Connection con = GetConnection.getConnexion();
					PreparedStatement pstmt = con.prepareStatement(sqlUpdateEnchere);
				){
					pstmt.setInt(1, e.getNoUtilisateur());
					pstmt.setInt(2, e.getNoArticle());
					pstmt.setDate(3, (Date) e.getDateEnchere());
					pstmt.setInt(4, e.getMontantEnchere());
					
					pstmt.execute();
			}catch (SQLException ex) {
					throw new DALException(sqlUpdateEnchere, ex);
			}
	}
}
