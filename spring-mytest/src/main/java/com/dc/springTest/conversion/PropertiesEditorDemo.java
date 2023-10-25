package com.dc.springTest.conversion;

import org.springframework.beans.propertyeditors.PropertiesEditor;

/**
 * Description:{@link PropertiesEditor}
 * Author: duancong
 * Date: 2023/10/24 15:02
 * @see PropertiesEditor
 *
 */
public class PropertiesEditorDemo {
	public static void main(String[] args) {
		PropertiesEditor propertiesEditor = new PropertiesEditor();
		propertiesEditor.setAsText("PropertiesEditorDemo=test");
//		System.out.println(System.getProperty("line.separator"));
		System.out.println("\r");
		System.out.println("\n");
		System.out.println(propertiesEditor.getAsText());
	}
}
