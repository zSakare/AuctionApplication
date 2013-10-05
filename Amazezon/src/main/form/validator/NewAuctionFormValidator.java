package main.form.validator;

import java.util.ArrayList;
import java.util.List;

import main.form.NewAuctionForm;

public class NewAuctionFormValidator {
	
	public static List<FormError> validate(NewAuctionForm form) {
		List<FormError> errors = new ArrayList<FormError>();
		
		Float reservePrice = new Float(-1);
		Float biddingStartPrice = new Float(-1);
		
		if (form.getTitle().isEmpty()) {
			errors.add(new FormError("title", form.getTitle(), "Title name is empty"));
		} else {
			if (form.getTitle().length() > 100) {
				errors.add(new FormError("title", form.getTitle(), "Title name is too long, exceeds <" + "100" + "> characters"));
			}
		}
		
		if (form.getDescription().length() > 750) {
			errors.add(new FormError("description", form.getDescription(), "Description is too long, exceeds <" + "750" + "> characters"));
		}
		
		if (form.getPostageDetails().isEmpty()) {
			errors.add(new FormError("postageDetails", form.getPostageDetails(), "Postage details must be filled in"));
		}
		try {
			reservePrice = Float.parseFloat(form.getReservePrice());
			if (reservePrice <= 0) {
				errors.add(new FormError("reservePrice", form.getReservePrice().toString(), "Reserve Price must be more than 0. Input was <" + form.getReservePrice() + ">."));
			}
		} catch (NumberFormatException e) {
			if (form.getReservePrice().isEmpty()) {
				errors.add(new FormError("reservePrice", form.getReservePrice().toString(), "Reserve Price was empty"));
			} else {
				errors.add(new FormError("reservePrice", form.getReservePrice().toString(), "Reserve Price is not a valid number"));
			}
		}
		
		try {
			biddingStartPrice = Float.parseFloat(form.getBiddingStartPrice());
			if (biddingStartPrice < 0) {
				errors.add(new FormError("biddingStartPrice", form.getBiddingStartPrice().toString(), "Starting price cannot be below 0. Input was <" + form.getBiddingStartPrice() + ">."));
			}
		} catch (NumberFormatException e) {
			if (form.getBiddingStartPrice().isEmpty()) {
				errors.add(new FormError("biddingStartPrice", form.getBiddingStartPrice().toString(), "Starting price cannot be empty"));
			} else {
				errors.add(new FormError("biddingStartPrice", form.getBiddingStartPrice().toString(), "Starting price is not a valid number"));
			}
		}
		
		try {
			Float biddingIncrement = Float.parseFloat(form.getBiddingIncrement());
			if (biddingIncrement <= 0) {
				errors.add(new FormError("biddingIncrements", form.getBiddingIncrement().toString(), "Bidding increment must be more than 0. Input was <" + form.getBiddingIncrement() + ">."));
			}
		} catch (NumberFormatException e) {
			if (form.getBiddingIncrement().isEmpty()) {
				errors.add(new FormError("biddingIncrements", form.getBiddingIncrement().toString(), "Bidding increment cannot be empty"));
			} else {
				errors.add(new FormError("biddingIncrements", form.getBiddingIncrement().toString(), "Bidding increment is not a valid number"));
			}
		}
		
		try {
			int endTime = Integer.parseInt(form.getEndTime());
			if (endTime < 3) {
				errors.add(new FormError("endTime", form.getEndTime().toString(), "Closing time must be greater than 3 minutes, you put <" + form.getEndTime() + "> minutes."));
			}
			
			if (endTime > 60) {
				errors.add(new FormError("endTime", form.getEndTime().toString(), "Closing time must be less than 60 minutes, you put <" + form.getEndTime() + "> minutes."));
			}
		} catch (NumberFormatException e) {
			
		}
		
		
		
		if (!reservePrice.equals(new Float(-1)) || !biddingStartPrice.equals(new Float(-1))) {
			if (reservePrice < biddingStartPrice) {
				errors.add(new FormError("biddingStartPrice", form.getBiddingStartPrice().toString(), "Starting price cannot be greater than reserve price. Starting price was <" + form.getBiddingStartPrice() + "> compared to reserve of <" + form.getReservePrice() +">."));
			}
		}
		
		return errors;
	}
}
