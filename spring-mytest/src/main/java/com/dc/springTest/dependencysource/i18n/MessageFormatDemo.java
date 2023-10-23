package com.dc.springTest.dependencysource.i18n;

import org.springframework.context.support.StaticMessageSource;

import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Description:
 * Author: duancong
 * Date: 2023/10/21 17:20
 */
public class MessageFormatDemo {
	public static void main(String[] args) {
		int planet = 7;

		String event = "a disturbance in the Force";
//		String result = MessageFormat.format(
//				"At {1,time,long} on {1,date,full}, there was {2} on planet {0,number,integer}.",
//				planet, new Date(), event);

		final MessageFormat messageFormat = new MessageFormat("At {1,time,long} on {1,date,full}, there was {2} on planet {0,number,integer}.");
		final String result = messageFormat.format(new Object[]{planet, new Date(), event});

		System.out.println(result);
		//重置Local
		messageFormat.setLocale(Locale.ENGLISH);

		//重置MessageFormatPattern
		messageFormat.applyPattern("This is a Text: {0}, {1}, {2}");
		String format = messageFormat.format(new Object[]{"Hello word ", "test", new Date()});
		System.out.println(format);

		StaticMessageSource staticMessageSource = new StaticMessageSource();
		//重置format
		messageFormat.setFormat(1,new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
		String format1 = messageFormat.format(new Object[]{"Hello word ", new Date()});
		System.out.println(format1);



	}
}
