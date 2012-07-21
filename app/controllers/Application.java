package controllers;

import models.PageDetails;
import models.Post;
import models.ProfileDetails;
import models.Account;
import play.cache.Cache;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.index;
import views.html.profile;

public class Application extends Controller {
  
	public static Result index() {
		PageDetails pageDetails = getPageDetails();
		 Account currentUser=(Account) Cache.get("currentUser"); 
		if(currentUser!=null)
		  {
			  return ok(profile.render(pageDetails,"home")); 
		  }else{
			  pageDetails.pageTitle="Welcome to Pinoy IT Hubber";
			  return ok(index.render(pageDetails));
		  }	  
		
	  }
  

	
	public static PageDetails getPageDetails(){
		PageDetails pageDetails = (PageDetails) Cache.get("pageDetails");
		 if(pageDetails==null){
			 pageDetails= new PageDetails();
		 }
		 Account currentUser=(Account) Cache.get("currentUser");
		 if(currentUser!=null){
			 pageDetails.currentUserDetails=buildProfileDetails(currentUser);
		 }
		// pageDetails.visitCount=Visits.getVisitCount();
		 //pageDetails.membersCount=User.getMembers();
		 Cache.set("pageDetails", pageDetails);
		return pageDetails;
	}
	
	public static ProfileDetails buildProfileDetails(Account currentUser){
		ProfileDetails userDetails= new ProfileDetails();
		userDetails.user=currentUser;
		userDetails.posts=Post.getPostByUser(currentUser);
		return userDetails;
	}
  
	
	
}