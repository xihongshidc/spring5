package com.dc.springTest.conversion;

import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.ConditionalGenericConverter;

import java.util.Collections;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 * Description:
 * Author: duancong
 * Date: 2023/10/25 10:23
 */
public class CustomPropertiesToStringConverter implements ConditionalGenericConverter {
	@Override
	public boolean matches(TypeDescriptor sourceType, TypeDescriptor targetType) {
		return Properties.class.equals(sourceType.getType())
				&& String.class.equals(targetType.getType());
	}

	@Override
	public Set<ConvertiblePair> getConvertibleTypes() {
		return Collections.singleton(new ConvertiblePair(Properties.class,String.class));
	}

	@Override
	public Object convert(Object source, TypeDescriptor sourceType, TypeDescriptor targetType) {
		Properties source1 = (Properties) source;
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("{");
		for (Map.Entry<Object, Object> objectObjectEntry : source1.entrySet()) {
			stringBuilder.append(objectObjectEntry.getKey()).append(":").append(objectObjectEntry.getValue()).append(",");
		}
		stringBuilder.append("}");
		return stringBuilder.toString();
	}
}
