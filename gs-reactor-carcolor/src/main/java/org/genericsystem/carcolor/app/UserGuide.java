package org.genericsystem.carcolor.app;

import org.genericsystem.reactor.gscomponents.FlexDiv;
import org.genericsystem.reactor.gscomponents.HtmlTag.HtmlHyperLink;
import org.genericsystem.reactor.gscomponents.HtmlTag.HtmlLi;
import org.genericsystem.reactor.gscomponents.HtmlTag.HtmlUl;
import org.genericsystem.reactor.gscomponents.Modal.ModalWithDisplay;

import org.genericsystem.carcolor.app.UserGuide.TextContent;
import org.genericsystem.reactor.annotations.Children;
import org.genericsystem.reactor.annotations.SetText;
import org.genericsystem.reactor.annotations.Style;

@Children(path = FlexDiv.class, value = { HtmlHyperLink.class, TextContent.class })
@Style(path = FlexDiv.class, name = "border-radius", value = "30px")
public class UserGuide extends ModalWithDisplay {

	@Children(value = { HtmlUl.class, HtmlUl.class, HtmlUl.class, FlexDiv.class })
	@Children(path = HtmlUl.class, pos = 0, value = { HtmlLi.class, HtmlLi.class, HtmlLi.class })
	@Children(path = HtmlUl.class, pos = 1, value = { HtmlLi.class, HtmlLi.class, HtmlLi.class })
	@Children(path = HtmlUl.class, pos = 2, value = { HtmlLi.class, HtmlLi.class, HtmlLi.class, HtmlLi.class })
	@SetText(path = HtmlUl.class, pos = 0, value = "How to use CarColor Demo")
	@SetText(path = { HtmlUl.class, HtmlLi.class }, pos = { 0, 0 }, value = "Insert Car model")
	@SetText(path = { HtmlUl.class, HtmlLi.class }, pos = { 0, 1 }, value = "Select color in the ComboBox")
	@SetText(path = { HtmlUl.class, HtmlLi.class }, pos = { 0, 2 }, value = "Use \"add Button\" to update data")
	@SetText(path = HtmlUl.class, pos = 1, value = "Color Management")
	@SetText(path = { HtmlUl.class, HtmlLi.class }, pos = { 1, 0 }, value = "Add new color of any CSS color style (ex : Black, rgb(0,0,0), #000000, ...)")
	@SetText(path = { HtmlUl.class, HtmlLi.class }, pos = { 1, 1 }, value = "Select car in the ComboBox")
	@SetText(path = { HtmlUl.class, HtmlLi.class }, pos = { 1, 2 }, value = "Use \"add Button\" to update data")
	@SetText(path = HtmlUl.class, pos = 2, value = "General Tips")
	@SetText(path = { HtmlUl.class, HtmlLi.class }, pos = { 2, 0 }, value = "Click \"Add Button\" to add an entry in the cache")
	@SetText(path = { HtmlUl.class, HtmlLi.class }, pos = { 2, 1 }, value = "Click the \"Remove Button\" to delete the entry in your cache")
	@SetText(path = { HtmlUl.class, HtmlLi.class }, pos = { 2, 2 }, value = "Click \"Save Button\" to persist the cache")
	@SetText(path = { HtmlUl.class, HtmlLi.class }, pos = { 2, 3 }, value = "Click \"Cancel Button\" to release the cache")
	@Style(path = FlexDiv.class, name = "text-align", value = "center")
	@SetText(path = FlexDiv.class, value = "To plenty enjoy the power of GS-REACTOR, go to Learning / Get Started")
	@Style(name = "padding", value = "35px")
	public static class TextContent extends FlexDiv {
	}
}
