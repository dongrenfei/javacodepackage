package com.steve.util;

import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class XSSFilter {
	private Vector vAllowedTags = null;
	private Vector vAllowedProtocols = null;
	private boolean debug = true;

	Vector vText = new Vector();

	public XSSFilter() {
		this.vAllowedTags = new Vector();
		this.vAllowedProtocols = new Vector();
	}

	public synchronized String doFilter(String strText) throws Exception {
		String result = "";
		try {
			strText = replaceText(strText, new String(""), "");
			strText = replaceText(strText, "%&\\s*\\{[^}]*(\\}\\s*;?|$)%", "");
			strText = replaceText(strText, "&", "&amp;");
			strText = replaceText(strText, "/&amp;([A-Za-z][A-Za-z0-9]*;)/", "&\001");
			strText = replaceText(strText, "/&amp;#([0-9]+;)/", "&#\001");
			strText = replaceText(strText, "/&amp;#[Xx]0*((?:[0-9A-Fa-f]{2})+;)/", "&#x\001");

			Pattern pattern = Pattern.compile("(<(?=[^a-zA-Z!/])|<[^>]*(>|$)|>)");
			Matcher matcher = pattern.matcher(strText);
			int start = 0;
			int end = 0;
			int previousIndex = 0;
			String tag = "";
			boolean blnFound = false;

			while (matcher.find()) {
				start = matcher.start();
				end = matcher.end();
				tag = filterHTML(strText.substring(start, end));
				this.vText.add(strText.substring(previousIndex, start) + tag);
				previousIndex = end;
				blnFound = true;
			}

			if (blnFound) {
				for (int i = 0; i < this.vText.size(); i++) {
					result = result + this.vText.get(i).toString();
				}
			} else {
				result = strText;
			}

		} catch (Exception e) {
			throw new Exception("Exception while processing text : " + e);
		}

		return result;
	}

	protected String filterHTML(String tag) throws Exception {
		String result = "";
		try {
			if (!tag.substring(0, 1).equals("<")) {
				result = "&gt;";
			} else if (tag.length() == 1) {
				result = "&lt;";
			} else {
				result = processTag(tag);
			}

		} catch (Exception e) {
			throw new Exception("Exception while processing text : " + e);
		}

		return result;
	}

	protected String processTag(String tag) throws Exception {
		try {
			Pattern p1 = Pattern.compile("^<\\s*(/\\s*)?([a-zA-Z0-9]+)([^>]*)>?$");
			Matcher m1 = p1.matcher(tag);
			String closingTag = null;
			String tagName = null;
			String attributes = null;

			if (m1.find()) {
				closingTag = m1.group(1);
				if (closingTag != null) {
					closingTag = closingTag.trim();
				}
				tagName = m1.group(2);
				if (tagName != null) {
					tagName = tagName.trim().toLowerCase();
				}
				attributes = m1.group(3);
				if (attributes != null) {
					attributes = attributes.trim();
				}
				if (!this.vAllowedTags.contains(tagName)) {
					return "";
				}
				if ((closingTag != null) && (!closingTag.equals(""))) {
					return "</" + tagName + ">";
				}
				Pattern p2 = Pattern.compile("\\s*/\\s*$");
				Matcher m2 = p2.matcher(attributes);

				String selfClosing = "";

				if (m2.find()) {
					selfClosing = "/";
					attributes = m2.replaceFirst("");
				}

				if ((attributes != null) && (!attributes.equals(""))) {
					attributes = filterAttributes(attributes);
					attributes = replaceText(attributes, "[<>]", "");
				}

				return "<" + tagName + attributes + selfClosing + ">";
			}

			return "";
		} catch (Exception e) {
			throw new Exception("Exception while processing text : " + e);
		}
	}

	protected String filterAttributes(String strAttributes) throws Exception {
		String strFilteredAttrs = "";
		try {
			Vector vAttrList = new Vector();
			String strAttributeName = "";
			String strAttributeValue = "";
			int mode = 0;
			boolean blnSkip = false;
			Matcher m1 = null;

			while (strAttributes.length() != 0) {
				boolean blnDone = false;

				switch (mode) {
				case 0:
					m1 = Pattern.compile("^([-a-zA-Z]+)").matcher(strAttributes);
					if (m1.find()) {
						strAttributeName = m1.group(1);
						if (strAttributeName != null) {
							strAttributeName = strAttributeName.trim().toLowerCase();
						}
						if ((strAttributeName.equals("style")) || (strAttributeName.startsWith("on"))) {
							blnSkip = true;
						}
						blnDone = true;
						mode = 1;
						strAttributes = m1.replaceFirst("");
					}

					break;
				case 1:
					m1 = Pattern.compile("^\\s*=\\s*").matcher(strAttributes);
					if (m1.find()) {
						blnDone = true;
						mode = 2;
						strAttributes = m1.replaceFirst("");
					} else {
						m1 = Pattern.compile("^\\s*").matcher(strAttributes);
						if (m1.find()) {
							blnDone = true;
							mode = 0;
							if (!blnSkip) {
								vAttrList.add(strAttributeName);
							}
							strAttributes = m1.replaceFirst("");
						}
					}
					break;
				case 2:
					m1 = Pattern.compile("^\"([^\"]*)\"").matcher(strAttributes);
					if (m1.find()) {
						strAttributeValue = filterProtocol(m1.group(1)).trim();
						if (!blnSkip) {
							vAttrList.add(strAttributeName + "=\"" + strAttributeValue + "\"");
						}
						blnDone = true;
						mode = 0;
						strAttributes = m1.replaceFirst("").trim();
					} else {
						m1 = Pattern.compile("^'([^']*)'").matcher(strAttributes);
						if (m1.find()) {
							strAttributeValue = filterProtocol(m1.group(1));
							if (!blnSkip) {
								vAttrList.add(strAttributeName + "='" + strAttributeValue + "'");
							}
							blnDone = true;
							mode = 0;
							strAttributes = m1.replaceFirst("");
						} else {
							m1 = Pattern.compile("^([^\\s\"']+)(\\s+|$)").matcher(strAttributes);
							if (m1.find()) {
								strAttributeValue = filterProtocol(m1.group(1));
								if (!blnSkip) {
									vAttrList.add(strAttributeName + "=\"" + strAttributeValue + "\"");
								}
								blnDone = true;
								mode = 0;
								strAttributes = m1.replaceFirst("");
							}
						}
					}
					break;
				}
				if (!blnDone) {
					m1 = Pattern.compile("^(\"[^\"]*(\"|$)|'[^']*('|$)|\\S)*\\s*").matcher(strAttributes);
					if (m1.find())
						strAttributes = m1.replaceFirst("").trim();
					mode = 0;
				}

			}

			if (mode == 1) {
				vAttrList.add(strAttributeName);
			}

			for (int i = 0; i < vAttrList.size(); i++) {
				strFilteredAttrs = strFilteredAttrs + " " + vAttrList.get(i);
			}

		} catch (Exception e) {
			throw new Exception("Exception while processing text : " + e);
		}

		return strFilteredAttrs;
	}

	protected String filterProtocol(String strAttrValue) throws Exception {
		try {
			String strOriginal = null;
			int intColonPosition = 0;
			String strProtocol = "";
			do {
				strOriginal = strAttrValue;
				intColonPosition = strAttrValue.indexOf(":");

				if (intColonPosition > -1) {
					strProtocol = strAttrValue.substring(0, intColonPosition);
					if (Pattern.matches("![/?#]!", strProtocol)) {
						break;
					}

					if (!this.vAllowedProtocols.contains(strProtocol.toLowerCase())) {
						strAttrValue = strAttrValue.substring(intColonPosition + 1);
					}
				}
			} while (!strOriginal.equals(strAttrValue));
		} catch (Exception e) {
			throw new Exception("Exception while processing text : " + e);
		}

		return strAttrValue;
	}

	protected String replaceText(String text, String strPattern, String replacementText) {
		Pattern pattern = Pattern.compile(strPattern);
		Matcher matcher = pattern.matcher(text);
		return matcher.replaceAll(replacementText);
	}

	public void setAllowedMarkup(String[] strAllowedMarkup) throws Exception {
		try {
			this.vAllowedTags = new Vector();
			for (int i = 0; i < strAllowedMarkup.length; i++)
				this.vAllowedTags.add(strAllowedMarkup[0].trim().toLowerCase());
		} catch (Exception e) {
			throw new Exception("Exception while setting allowed markup : " + e);
		}
	}

	public void setAllowedProtocols(String[] strAllowedProtocols) throws Exception {
		try {
			this.vAllowedProtocols = new Vector();
			for (int i = 0; i < strAllowedProtocols.length; i++)
				this.vAllowedProtocols.add(strAllowedProtocols[0].trim().toLowerCase());
		} catch (Exception e) {
			throw new Exception("Exception while setting allowed protocols : " + e);
		}
	}
}