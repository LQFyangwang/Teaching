package com.gs.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.TagSupport;

public class MyTag2 extends TagSupport {

	private static final long serialVersionUID = 4337513100467939432L;

	private PageContext pageContext;
	
	private String value;
	private String other;
	
	public void setValue(String value) {
		this.value = value;
	}
	
	public void setOther(String other) {
		this.other = other;
	}
	
	@Override
	public int doStartTag() throws JspException {
		JspWriter out = pageContext.getOut();
		try {
			out.write(value);
			out.write("&nbsp;&nbsp;");
			if (other != null) {
				out.write(other);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return EVAL_BODY_INCLUDE;
	}

	@Override
	public int doEndTag() throws JspException {
		return EVAL_PAGE;
	}

	@Override
	public void setPageContext(PageContext pageContext) {
		this.pageContext = pageContext;
	}

}
