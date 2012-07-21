package controllers;

import java.util.Map;

import models.Account;
import models.LoginDetails;
import models.PageDetails;
import models.Post;

import org.codehaus.jackson.JsonNode;

import play.cache.Cache;
import play.data.Form;
import play.libs.F.Callback;
import play.mvc.Result;
import play.mvc.WebSocket;
import views.html.profile;

public class UserController extends Application {

	public static WebSocket<JsonNode> createAccount() {
		
				
		return new WebSocket<JsonNode>() {
			@Override
			public void onReady(play.mvc.WebSocket.In<JsonNode> in,
					play.mvc.WebSocket.Out<JsonNode> out) {

				in.onMessage(new Callback<JsonNode>() {
					public void invoke(JsonNode event) {
					   
												
						System.out.println("event" +event.get(1).get("value").asText());
						System.out.println("fields" +event.toString());
						
											
					}
				});
			}
		};
	}

	public static Result displayCreatePost() {
		PageDetails pageDetails = getPageDetails();
		return ok(profile.render(pageDetails, "createPost"));
	}

	public static Result viewAllPost() {
		PageDetails pageDetails = getPageDetails();
		return ok(profile.render(pageDetails, "viewAllPost"));
	}

	public static Result submitPost() {
		Form<Post> postform = form(Post.class).bindFromRequest();
		Post post = buildPost(postform);
		post.save();
		return redirect(routes.Application.index());
	}

	private static Post buildPost(Form<Post> form) {
		Map<String, String> values = form.data();
		Account currentUser = (Account) Cache.get("currentUser");
		Post post = new Post(currentUser, values.get("title"),
				values.get("content"));
		return post;
	}

	public static Result login() {
		Form<LoginDetails> accountform = form(LoginDetails.class)
				.bindFromRequest();
		Account loginUser = Account.retrieveAccountByUsernameAndPassword(
				accountform.data().get("username"),
				accountform.data().get("password"));
		if (loginUser != null) {
			Cache.set("currentUser", loginUser);
		}

		return redirect(routes.Application.index());
	}

	public static Result logout() {
		Cache.set("currentUser", null, 0);
		PageDetails pageDetails = getPageDetails();
		pageDetails.currentUserDetails = null;
		return redirect(routes.Application.index());
	}

	private static Account buildAccount(Form<Account> form) {
		Map<String, String> values = form.data();
		Account account = new Account();
		account.fullname = values.get("firstname");
		account.email = values.get("email");
		account.password = values.get("password");
		return account;
	}
}
