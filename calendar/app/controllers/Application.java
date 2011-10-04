package controllers;

import java.util.List;

import models.Calendar;
import models.Database;
import models.User;
import play.mvc.Controller;
import play.mvc.With;

@With(Secure.class)
public class Application extends Controller {

	@Check("isAdmin")
	public static void delete(Long id) {

	}

	public static void index() {
		User user = Database.getUser(Security.connected());
		List users = Database.getAllUsers();
		List calendars = user.getCalendarList();
		render(calendars, user, users);
	}

	public static void showCalendars(String email) {
		User user = Database.getUser(email);
		List calendars = user.getCalendarList();
		render(user, calendars);
	}

	public static void showEvents(String email, String calendarname) {
		User user = Database.getUser(Security.connected());
		List users = Database.getAllUsers();
		Calendar calendar = user.getCalendarByName(calendarname);
		List events = calendar.getEventsAsList();
		render(calendar, events);
	}

}