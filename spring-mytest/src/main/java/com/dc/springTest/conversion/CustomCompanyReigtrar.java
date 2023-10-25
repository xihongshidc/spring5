package com.dc.springTest.conversion;

import com.dc.springTest.User;
import org.springframework.beans.PropertyEditorRegistrar;
import org.springframework.beans.PropertyEditorRegistry;
import org.springframework.beans.factory.config.CustomEditorConfigurer;

/**
 * Description:{@link CustomEditorConfigurer}
 * Author: duancong
 * Date: 2023/10/24 16:19
 */
public class CustomCompanyReigtrar implements PropertyEditorRegistrar {

	@Override
	public void registerCustomEditors(PropertyEditorRegistry registry) {
		registry.registerCustomEditor(User.Company.class,"company",new CustomCompanyPojoEditor());
	}

	public static void main(String[] args) {
		System.out.println(User.Company.class.getTypeName());
	}
}
