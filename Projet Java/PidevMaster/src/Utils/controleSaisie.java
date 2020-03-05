/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import Utils.DataBase;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author asus
 */
public class controleSaisie {
    
         Connection cn =DataBase.getInstance().getConnection();
    
         public  boolean testnomprenom(String nom) {
		//************************ nom et prenom contiennent que des alphabets ***************************
		String masque = "^[a-zA-Z]+[a-z]";
		Pattern pattern = Pattern.compile(masque);
		Matcher controler = pattern.matcher(nom);
		return controler.matches();
	}

	

	/**
	 * ************************ Password 8
	 * chiffres**************************************************************
	 */
	public boolean testpassword(String password) {
		return (password.length() >= 8);
	}
        
        public boolean num(String num) {
		String masque = "[0-9]+";
		Pattern pattern = Pattern.compile(masque);
		Matcher controler = pattern.matcher(num);
		if (controler.matches()) {
			return true;
		}
		return false;
	}
        
        
//********************************Numero telephone contient 8 chiffres **************************************/

	public boolean GSM(String num) {
		String masque = "[0-9]{8}$";
		Pattern pattern = Pattern.compile(masque);
		Matcher controler = pattern.matcher(num);
		if (controler.matches()) {
			return true;
		}
		return false;
	}
        public boolean GSM2(String num) {
		String masque = "[0-9]{11}$";
		Pattern pattern = Pattern.compile(masque);
		Matcher controler = pattern.matcher(num);
		if (controler.matches()) {
			return true;
		}
		return false;
	}
//********************** test format mail *****************************************************************************

	public boolean mailformat(String mail) {
		String masque = "^[a-zA-Z]+[a-zA-Z0-9\\._-]*[a-zA-Z0-9]@[a-zA-Z]+"
				+ "[a-zA-Z0-9\\._-]*[a-zA-Z0-9]+\\.[a-zA-Z]{2,4}$";
		Pattern pattern = Pattern.compile(masque);
		Matcher controler = pattern.matcher(mail);
		return controler.matches();

	}
// *********************************** test login format ************************************************************

	public boolean testUsername(String login) {
		String masque = "^[a-zA-Z]+[a-zA-Z0-9]";
		Pattern pattern = Pattern.compile(masque);
		Matcher controler = pattern.matcher(login);
		return controler.matches();
	}
//********************************** test login inutilisé ***********************************************************

	
//************************************* convertion de String à Date  ********************************

	public java.util.Date convert(String date) throws ParseException {

		SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
		java.util.Date date1 = sdf1.parse(date);

		return date1;
	}
        
        
        // *********************************** test Adresse format ************************************************************

	public boolean testAdr(String adr) {
		String masque = "^[a-zA-Z]+[a-zA-Z0-9]";
		Pattern pattern = Pattern.compile(masque);
		Matcher controler = pattern.matcher(adr);
		return controler.matches();
	}
        
        //********************************Code Postale contient 4 chiffres **************************************/

	public boolean Poste(String num) {
		String masque = "[0-9]{4}$";
		Pattern pattern = Pattern.compile(masque);
		Matcher controler = pattern.matcher(num);
		if (controler.matches()) {
			return true;
		}
		return false;
	}
 //********************************Cin contient 8 chiffres **************************************/

	public boolean Cin(String num) {
		String masque = "[0-9]{8}$";
		Pattern pattern = Pattern.compile(masque);
		Matcher controler = pattern.matcher(num);
		if (controler.matches()) {
			return true;
		}
		return false;
	}
        
        
        //************************************* convertion de String à Date  ********************************

	public boolean datenais(java.sql.Date date) {

            java.sql.Date d=new Date(System.currentTimeMillis());
//System.out.println(((d.getTime()- date.getTime())/86400000)/365);
		return (((d.getTime()- date.getTime())/86400000)/365)>=18;
		
	}
        
        public int testdateEMB_dateEXP1(java.sql.Date d1, java.sql.Date d2) throws ParseException {
		//******************* test date embauche inférieur a la date expiration*****************************
		java.util.Date dateEMB = d1;
		java.util.Date dateEXP = d2;
		return dateEMB.compareTo(dateEXP);
	}
         
         
    
    
   	
        
       
       
       
	
        
        
        public  String filterBadWords(String str)
	{
		ArrayList<String> badWords = new ArrayList<String>();
	    badWords.add("bad1");
	    badWords.add("bad2");
	    badWords.add("bad3");
	    badWords.add("\\$\\$");
	    
	    for(int i = 0; i < badWords.size(); i++) {
	    	str = str.replaceAll("(?i)" + badWords.get(i), "**");
	    }
	    str = str.replaceAll("\\w*\\*{4}", "**");
	    return str;
	}
}
