package controllers;

import models.PageDetails;
import models.Post;
import models.ProfileDetails;
import models.User;
import play.cache.Cache;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.index;
import views.html.profile;

public class Application extends Controller {
  
	public static Result index() {
		PageDetails pageDetails = getPageDetails();
		 User currentUser=(User) Cache.get("currentUser"); 
		if(currentUser!=null)
		  {
			  return ok(profile.render(pageDetails,"home")); 
		  }else{
			  return ok(index.render(pageDetails));
		  }	  
		
	  }
  

	
	public static PageDetails getPageDetails(){
		PageDetails pageDetails = (PageDetails) Cache.get("pageDetails");
		 if(pageDetails==null){
			 pageDetails= new PageDetails();
		 }
		 User currentUser=(User) Cache.get("currentUser");
		 if(currentUser!=null){
			 pageDetails.currentUserDetails=buildProfileDetails(currentUser);
		 }
		// pageDetails.visitCount=Visits.getVisitCount();
		 //pageDetails.membersCount=User.getMembers();
		 Cache.set("pageDetails", pageDetails);
		return pageDetails;
	}
	
	public static ProfileDetails buildProfileDetails(User currentUser){
		ProfileDetails userDetails= new ProfileDetails();
		userDetails.user=currentUser;
		userDetails.posts=Post.getPostByUser(currentUser);
		return userDetails;
	}
  
}