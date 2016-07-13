package com.steve.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

public class ServicelinkEncoding {
	private static final Logger logger = Logger.getLogger(ServicelinkEncoding.class);

	/**
	 * encodeForHTML()
	 * 
	 * This method encodes data that will be inserted into the HTML body
	 * somewhere. This includes inside normal tags like div, p, b, td, etc.
	 * 
	 * @param originalString
	 * @return encoded String
	 */
	public static String encodeForHTML(String originalString) {
		logger.debug("encodeForHTML()");
		if (originalString == null || originalString.length() == 0)
			return originalString;
		StringBuffer outputStringBuffer = new StringBuffer();
		char[] chars = originalString.toCharArray();
		for (Character ch : chars) {
			if (ch == '&' || ch == '<' || ch == '>' || ch == '"' || ch == '\'') {
				outputStringBuffer.append("&#" + ((int) (ch)) + ";");
			} else {
				outputStringBuffer.append(ch);
			}
		}
		if (logger.isDebugEnabled()) {
			logger.debug("Original String: " + originalString);
			logger.debug("Encoded String: " + outputStringBuffer.toString());
		}
		return outputStringBuffer.toString();
	}

	/**
	 * decodeForHTML()
	 * 
	 * This method decodes data previously encoded by encodeForHTML().
	 * 
	 * @param originalString
	 * @return decoded String
	 */
	public static String decodeForHTML(String originalString) {
		logger.debug("decodeForHTML()");
		if (originalString == null || originalString.length() == 0)
			return originalString;

		if (logger.isDebugEnabled()) {
			logger.debug("Original String: " + originalString);
			logger.debug("Decoded String: " + originalString.replaceAll("&#39;", "'").replaceAll("&#34;", "\"")
					.replaceAll("&#38;", "&").replaceAll("&#60;", "<").replaceAll("&#62;", ">"));
		}
		return originalString.replaceAll("&#39;", "'").replaceAll("&#34;", "\"").replaceAll("&#38;", "&")
				.replaceAll("&#60;", "<").replaceAll("&#62;", ">");
	}

	/**
	 * encodeForHTMLAtttribute()
	 * 
	 * This method encodes data that will be inserted into an HTML attribute
	 * value. It encodes a String so that all the non alphanumeric characters
	 * get converted to &#x<ASCII Value> format.
	 * 
	 * @param originalString
	 * @return encoded String
	 */
	public static String encodeForHTMLAttribute(String originalString) {
		logger.debug("encodeForHTMLAttribute()");
		if (originalString == null || originalString.length() == 0)
			return originalString;
		return encode("&#x", originalString);
	}

	/**
	 * decodeForHTMLAttribute()
	 * 
	 * This method decodes data previously encoded by encodeForHTMLAttribute().
	 * 
	 * @param originalString
	 * @return decoded String
	 */
	public static String decodeForHTMLAttribute(String encodedString) {
		logger.debug("decodeForHTMLAttribute()");
		if (encodedString == null || encodedString.length() == 0)
			return encodedString;
		return decode("&#x", encodedString);
	}

	/**
	 * encodeForJavaScript()
	 * 
	 * This method encodes data that will be inserted into a JavaScript variable
	 * value. It encodes a String so that all the non alphanumeric characters
	 * get converted to \x<ASCII Value> format.
	 * 
	 * @param originalString
	 * @return encoded String
	 */
	public static String encodeForJavaScript(String originalString) {
		logger.debug("encodeForJavaScript()");
		if (originalString == null || originalString.length() == 0)
			return originalString;
		return encode("\\x", originalString);
	}

	/**
	 * decodeForJavaScript()
	 * 
	 * This method decodes data previously encoded by encodeForJavaScript().
	 * 
	 * @param originalString
	 * @return decoded String
	 */
	public static String decodeForJavaScript(String encodedString) {
		logger.debug("decodeForJavaScript()");
		if (encodedString == null || encodedString.length() == 0)
			return encodedString;
		return decode("\\\\x", encodedString);
	}

	/**
	 * encodeForCSS()
	 * 
	 * This method encodes variable data that will be inserted into a cascading
	 * style sheet value. It encodes a String so that all the non alphanumeric
	 * characters get converted to \<ASCII Value> format.
	 * 
	 * @param originalString
	 * @return encoded String
	 */
	public static String encodeForCSS(String originalString) {
		logger.debug("encodeForCSS()");
		if (originalString == null || originalString.length() == 0)
			return originalString;
		return encode("\\", originalString);
	}

	/**
	 * decodeForCSS()
	 * 
	 * This method decodes data previously encoded by encodeForCSS().
	 * 
	 * @param originalString
	 * @return decoded String
	 */
	public static String decodeForCSS(String encodedString) {
		logger.debug("decodeForCSS()");
		if (encodedString == null || encodedString.length() == 0)
			return encodedString;
		return decode("\\\\", encodedString);
	}

	/**
	 * encodeForURL()
	 * 
	 * This method encodes variable data that will be inserted into a URL. It
	 * encodes a String so that all the non alphanumeric characters get
	 * converted to %<ASCII Value> format.
	 * 
	 * @param originalString
	 * @return encoded String
	 */
	public static String encodeForURL(String originalString) {
		logger.debug("encodeForURL()");
		if (originalString == null || originalString.length() == 0)
			return originalString;
		return encode("%", originalString);
	}

	/**
	 * decodeForURL()
	 * 
	 * This method decodes data previously encoded by encodeForURL().
	 * 
	 * @param originalString
	 * @return decoded String
	 */
	public static String decodeForURL(String encodedString) {
		logger.debug("decodeForURL()");
		if (encodedString == null || encodedString.length() == 0)
			return encodedString;
		return decode("%", encodedString);
	}

	/**
	 * encode()
	 * 
	 * The common encoding function that will vary depending on the encoding
	 * pattern.
	 * 
	 * @param String
	 *            pattern
	 * @param String
	 *            originalString
	 * @return String encodedString
	 */
	private static String encode(String pattern, String originalString) {
		StringBuffer outputStringBuffer = new StringBuffer();
		String hexstr = "";
		char[] chars = originalString.toCharArray();

		for (Character ch : chars) {
			if (Character.isLetterOrDigit(ch) || Character.isWhitespace(ch)) {
				// Don't need to encode alphanumeric or whitespace
				outputStringBuffer.append(ch);
				continue;
			}
			// Convert special characters to a hex string
			hexstr = Integer.toHexString((int) (ch));
			if (pattern.equals("&#x")) {
				// Only encodeForHTMLAttribute needs a semicolon
				// appended to the encoded string
				outputStringBuffer.append(pattern + hexstr + ";");
			} else {
				outputStringBuffer.append(pattern + hexstr);
			}
		}

		if (logger.isDebugEnabled()) {
			logger.debug("encode():Original String: " + originalString);
			logger.debug("encode():Encoded String: " + outputStringBuffer.toString());
		}
		return outputStringBuffer.toString();
	}

	private static String decode(String pattern, String encodedString) {
		if (logger.isDebugEnabled()) {
			logger.debug("decode():Original String: " + encodedString);
		}

		String replacablePattern = pattern;
		if (pattern.equals("\\\\")) {
			replacablePattern = "\\";
		} else if (pattern.equals("\\\\x")) {
			replacablePattern = "\\x";
		}

		Pattern p = null;
		if(pattern.equals("&#x"))
		{
			// Only encodeForHTMLAttribute has a semicolon
			//   appended to the encoded string
			p=Pattern.compile(pattern + "\\p{XDigit}{2}"+";");
		}
		else
		{
			p=Pattern.compile(pattern + "\\p{XDigit}{2}");
		}
		
		Matcher matcher = p.matcher(encodedString);
		while (matcher.find()) {
			String encodedStringForSplChar = matcher.group();
			// The encoded String representation minus the encoding prefix 
			//   is the hex String value
			String hexValueStr = 
				encodedStringForSplChar.replace(replacablePattern, "");
			if(pattern.equals("&#x"))
			{
				// Only encodeForHTMLAttribute has a semicolon appended 
				//  to the encoded string - remove the semicolon.
				hexValueStr=hexValueStr.replace(";", "");
			}

			try {
				// Convert the hex String to an integer,
				//   the integer to a char,
				//   then the char to a String character.
				int intValue = Integer.parseInt(hexValueStr, 16);
				char c = (char) intValue;
				String originalChar = Character.toString(c);
				encodedString = 
					encodedString.replace(encodedStringForSplChar, originalChar);
			} catch (NumberFormatException e) {
				logger.error("Exception while converting " + hexValueStr
						+ " to Number ");
				continue;
			}
		}
		if (logger.isDebugEnabled()) {
			logger.debug("decode():Decoded String: " + encodedString);
		}
		return encodedString;
	}
}