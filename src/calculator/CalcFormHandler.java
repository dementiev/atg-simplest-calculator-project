package calculator;

import java.util.Date;

import javax.servlet.http.Cookie;

import atg.droplet.DropletException;
import atg.droplet.GenericFormHandler;
import atg.servlet.DynamoHttpServletRequest;
import atg.servlet.DynamoHttpServletResponse;

public class CalcFormHandler extends GenericFormHandler {
	private String number1;
	private String number2;
	private String resultNumber;
	private String mValidateSuccessURL;
	private String mValidateErrorURL;

	public void setNumber1(String number1) {
		this.number1 = number1;
	}

	public String getNumber1() {
		return number1;
	}

	public void setNumber2(String number2) {
		this.number2 = number2;
	}

	public String getNumber2() {
		return number2;
	}

	public void setResultNumber(String resultNumber) {
		this.resultNumber = resultNumber;
	}

	public String getResultNumber() {
		return resultNumber;
	}

	public boolean isResultMoreThanZero() {
		if (Integer.parseInt(getNumber1()) * Integer.parseInt(getNumber2()) > 0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Returns address of page to redirect to upon success
	 * 
	 * @return String
	 */
	public String getValidateSuccessURL() {
		return mValidateSuccessURL;
	}

	/**
	 * Sets the page the handleValidate() method redirects to when validation is
	 * successful.
	 * 
	 * @param validateSuccessURL
	 *            The redirection page name
	 */
	public void setValidateSuccessURL(String validateSuccessURL) {
		mValidateSuccessURL = validateSuccessURL;
	}

	/**
	 * Returns address of page to redirect to if error occurs
	 * 
	 * @return String
	 */
	public String getValidateErrorURL() {
		return mValidateErrorURL;
	}

	/**
	 * Sets the page the handleValidate() method redirects to in case of error.
	 * 
	 * @param validateErrorURL
	 *            The redirection page name
	 */
	public void setValidateErrorURL(String validateErrorURL) {
		mValidateErrorURL = validateErrorURL;
	}

	// **** Form Handlers ****
	/**
	 * Resets the form (sets the userAnswer property to null).
	 */
	public boolean handleCancel(DynamoHttpServletRequest request,
			DynamoHttpServletResponse response) throws java.io.IOException {
		setNumber1("");
		setNumber2("");
		return true;
	}

	public boolean handleCalculate(DynamoHttpServletRequest request,
			DynamoHttpServletResponse response) throws java.io.IOException {
		// If any form errors were generated, abort...
		if (getFormError()) {
			if (getValidateErrorURL() != null) {
				response.sendLocalRedirect(getValidateErrorURL(), request);
				return false;
			}
			return true;
		}

		// Otherwise, redirect to form submission success page,
		// setting the calculation result to Cookie(on the result page should
		// get it from cookie)
		int num1 = 0;
		int num2 = 0;

		try {
			num1 = Integer.parseInt(getNumber1().trim());
			num2 = Integer.parseInt(getNumber2().trim());
		} catch (NumberFormatException e) {
			addFormException(new DropletException(
					"Please, enter valid numbers."));
			if (getValidateErrorURL() != null) {
				response.sendLocalRedirect(getValidateErrorURL(), request);
				return false;
			}
			return true;
		}

		setResultNumber(String.valueOf(num1 * num2));

		/** Add resultNumber to cookies */
		Cookie cookie = null;
//		Cookie[] cookies = request.getCookies();
		/*
		 * if (cookies != null) { for (int i = 0; i < cookies.length; i++) { if
		 * (cookies[i].getName().equals("resultNumber")) { cookie = cookies[i]; } } }
		 * if (cookie == null) {
		 */
		cookie = new Cookie("resultNumber", getResultNumber());
//		cookie.setPath(request.getContextPath());
		cookie.setMaxAge(1000);
		response.addCookie(cookie);
		// }
		if (getValidateSuccessURL() != null) {
			response.sendLocalRedirect(getValidateSuccessURL(), request);
			return false;
		}
		return true;
	}
}
