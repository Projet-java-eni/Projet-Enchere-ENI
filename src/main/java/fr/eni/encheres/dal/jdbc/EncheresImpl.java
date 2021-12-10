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
	String sqlSelectEncheresByNoUtilisateur = "SELECT no_utilisateur, no_article, date_enchere, montant_enchere FROM encheres WHERE no_utilisateur = ? ";
	String sqlSelectEncheresByNoArticle = "SELECT no_utilisateur, no_article, date_enchere, montant_enchere FROM encheres WHERE no_article = ? ";
	String sqlSelectEncheresByNoUtilisateurEtNoArticle = "SELECT no_utilisateur, no_article, date_enchere, montant_enchere FROM encheres WHERE no_utilisateur = ? AND no_article = ? ";
	String sqlSelectAllEncheres = "SELECT no_utilisateur, no_article, date_enchere, montant_enchere FROM encheres";
	String sqlUpdateEnchere = "UPDATE encheres SET no_utilisateur = ?, no_article = ?, date_enchere = ?, montant_enchere = ? FROM encheres WHERE no_utilisateur = ? AND no_article = ?";
	String sqlDeleteEnchereByNoUtilisateurEtNoArticle = "DELETE FROM encheres WHERE no_utilisateur = ? AND no_article = ? ";
	
	
	@Override
	public void add(Enchere e) throws DALException {
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
						e = new Enchere(rs.getInt(1), rs.getInt(2), rs.getDate(3), rs.getInt(4));
						encheres.add(e);
					}
					return encheres;	
				}
		} catch (SQLException ex) {
						throw new DALException(sqlSelectEncheresByNoUtilisateur, ex);
		}
			
	}
	
	@Override
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
						e = new Enchere(rs.getInt(1), rs.getInt(2), rs.getDate(3), rs.getInt(4));
						encheres.add(e);
					}
					return encheres;	
				}	
		}catch (SQLException ex) {
						throw new DALException(sqlSelectEncheresByNoArticle, ex);
		}
	}

	@Override
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
						e = new Enchere(rs.getInt(1), rs.getInt(2), rs.getDate(3), rs.getInt(4));
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
					e = new Enchere(rs.getInt(1), rs.getInt(2), rs.getDate(3), rs.getInt(4));
					encheres.add(e);
				}
				return encheres;
		} catch (SQLException ex) {
				throw new DALException(ex.getMessage(), ex);
		}
	}

	@Override
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
	public void update(Enchere e) throws DALException {
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


	@Override
	public void remove(Enchere utilisateur) throws DALException {
		// TODO Auto-generated method stub
		
	}

}
