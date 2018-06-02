package com.newtouch.pojo;

public class Content {

	private ContentHead contentHead;
	private ContentBody contentBody;
	
	public Content() {}
	public Content(ContentHead contentHead, ContentBody contentBody) {
		this.contentHead = contentHead;
		this.contentBody = contentBody;
	}
	
	public ContentHead getContentHead() {
		return contentHead;
	}
	public void setContentHead(ContentHead contentHead) {
		this.contentHead = contentHead;
	}
	public ContentBody getContentBody() {
		return contentBody;
	}
	public void setContentBody(ContentBody contentBody) {
		this.contentBody = contentBody;
	}
	
	public String toXml() {
		StringBuilder xml = new StringBuilder("<Content>");
		xml.append("<root>");
		xml.append(contentHead.toXml());
		xml.append(contentBody.toXml());
		xml.append("</root>");
		xml.append("</Content>");
		return xml.toString();
	}
	
}
