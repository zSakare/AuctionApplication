package main.form.validator;

import java.util.ArrayList;
import java.util.List;

import main.form.NewAuctionForm;

public class NewAuctionFormValidator {
	
	public static List<FormError> validate(NewAuctionForm form) {
		List<FormError> errors = new ArrayList<FormError>();
		
		if (form.getTitle().length() > 100) {
			errors.add(new FormError("title", form.getTitle(), "Item name is too long, exceeds <" + "100" + "> characters"));
		}
		
		if (form.getDescription().length() > 750) {
			errors.add(new FormError("description", form.getDescription(), "Description is too long, exceeds <" + "750" + "> characters"));
		}
		
		if (form.getPostageDetails().isEmpty()) {
			errors.add(new FormError("postageDetails", form.getPostageDetails(), "Postage details must be filled in"));
		}
		
		if (form.getEndTime() < 3) {
			errors.add(new FormError("endTime", form.getEndTime().toString(), "Closing time must be greater than 3 minutes, you put <" + form.getEndTime() + "> minutes."));
		}
		
		if (form.getEndTime() > 60) {
			errors.add(new FormError("endTime", form.getEndTime().toString(), "Closing time must be less than 60 minutes, you put <" + form.getEndTime() + "> minutes."));
		}
		
		if (form.getReservePrice() <= 0) {
			errors.add(new FormError("reservePrice", form.getReservePrice().toString(), "Reserve Price must be more than 0. Input was <" + form.getReservePrice() + ">."));
		}
		
		if (form.getBiddingIncrement() <= 0) {
			errors.add(new FormError("biddingIncrements", form.getBiddingIncrement().toString(), "Bidding increment must be more than 0. Input was <" + form.getBiddingIncrement() + ">."));
		}
		
		if (form.getBiddingStartPrice() < 0) {
			errors.add(new FormError("biddingStartPrice", form.getBiddingStartPrice().toString(), "Starting price cannot be below 0. Input was <" + form.getBiddingStartPrice() + ">."));
		}
		
		if (form.getReservePrice() < form.getBiddingStartPrice()) {
			errors.add(new FormError("biddingStartPrice", form.getBiddingStartPrice().toString(), "Starting price cannot be greater than reserve price. Starting price was <" + form.getBiddingStartPrice() + "> compared to reserve of <" + form.getReservePrice() +">."));
		}
		
		return errors;
	}
}
