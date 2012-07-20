package models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import play.data.validation.Constraints.Required;
import play.db.ebean.Model;


@Entity
public class Account extends Model {

	@Id
	@GeneratedValue
	public Long accountId;
	@Required
	@Column(unique=true)
	public String email;
	@Required
    public String password;
	@Required
    public String fullname;
    public String nickname;
    public String about;
    public String role;
    public String company;
    public boolean isAdmin;
    public boolean isOnline;
    public Account(){
    	
    }
    
    public Account(String email, String password, String fullname, boolean isAdmin) {
		super();
		this.email = email;
		this.password = password;
		this.fullname = fullname;
		this.isAdmin = isAdmin;
	}

    
    public static long getMembers() {
		List<Account> members =find.all();
		return  members.size();
	}

    public static Account retrieveAccountByUsernameAndPassword(String email, String password) {
		String oql = "email = :email and password= :password";
    	List<Account> members = find.query()
    			.select("*")
    			.where(oql)
    			.setParameter("email", email)
    			.setParameter("password",password)
    			.findList();
		if(members.size()>0){
			return members.get(0);
		}
			
		return null;
	}
    
	public static Finder<String,Account> find = new Finder<String,Account>(
    		String.class, Account.class
    	  ); 
}
