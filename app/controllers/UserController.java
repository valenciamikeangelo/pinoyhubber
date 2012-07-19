package controllers;

import java.util.Map;

import models.LoginDetails;
import models.User;
import play.cache.Cache;
import play.data.Form;
import play.mvc.Result;

public class UserController extends Application {

	
	
	public static Result createAccount(){
		Form<User> accountform = form(User.class).bindFromRequest();
		
		if(accountform.hasErrors()) {
		    return badRequest("");
		} else {
			//User account =buildAccount(accountform);
			User account =accountform.get();
			account.save();
			Cache.set("currentUser",account);
			return redirect(routes.Application.index());
		}
	
		
		
	}
	
	
	public static Result login(){
		Form<LoginDetails> accountform = form(LoginDetails.class).bindFromRequest();
		User loginUser= User.retrieveAccountByUsernameAndPassword(accountform.data().get("username"), accountform.data().get("password"));
		if(loginUser!=null){
        	session("user", loginUser.fullname);
    		session("useremail", loginUser.email);
    		Cache.set("currentUser",loginUser);
    	}
       
		return redirect(routes.Application.index());
	}
	
	
	public static Result logout(){
		session().remove("user");
		Cache.set("currentUser", null, 0);
        return redirect(routes.Application.index());
	}
	
	
	
	private static User buildAccount(Form<User> form){
		Map<String,String> values= form.data();
		User account = new User();
		account.fullname=values.get("firstname");
		account.email=values.get("email");
		account.password=values.get("password");
		return account;
	}
}
