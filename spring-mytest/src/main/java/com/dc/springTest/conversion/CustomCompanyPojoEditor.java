package com.dc.springTest.conversion;

import com.dc.springTest.User;

import java.beans.PropertyEditorSupport;

/**
 * Description:
 * Author: duancong
 * Date: 2023/10/24 16:12
 */
public class CustomCompanyPojoEditor extends PropertyEditorSupport {

	public CustomCompanyPojoEditor() {
	}

	@Override
	public String getAsText() {
		return super.getAsText();
	}

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		User.Company company = new User.Company();
		company.setCompany(text);
		setValue(company);
	}
}
