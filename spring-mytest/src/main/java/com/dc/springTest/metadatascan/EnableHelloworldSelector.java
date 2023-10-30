package com.dc.springTest.metadatascan;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * Description:
 * Author: duancong
 * Date: 2023/10/29 13:49
 */
public class EnableHelloworldSelector implements ImportSelector {
	@Override
	public String[] selectImports(AnnotationMetadata importingClassMetadata) {
		return new String[]{"com.dc.springTest.metadatascan.EnableHellworldConfiguration"};
	}
}
