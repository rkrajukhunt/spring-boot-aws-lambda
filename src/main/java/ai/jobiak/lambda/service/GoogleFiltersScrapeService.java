package ai.jobiak.lambda.service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.google.common.collect.Lists;

import ai.jobiak.lambda.Jobiak;
import ai.jobiak.lambda.pojo.GenericRes;
import ai.jobiak.lambda.pojo.GoogleFiltersReq;

@Service
public class GoogleFiltersScrapeService extends Jobiak {

	@Autowired
	WebClient webClient;

	String url = "https://www.google.com/search?q=%s+,+%s&ibp=htl;jobs";
	String baseXpath = "//*[@id='choice_box_root']/div";
	String titleXpath = "//*[@class='RFo6mf']/span";
	String titleXpathIn = "//*[@class='C1bqac']";

	public GenericRes<?> getGfKeywords(final GoogleFiltersReq gfReq) {
		String title = gfReq.getTitle().replaceAll(" ", "+");
		String location = gfReq.getTitle().replaceAll(" ", "+");

		try {
			String searchUrl = String.format(url, title, location);
			HtmlPage page = webClient.getPage(searchUrl);
			List<HtmlElement> choiceBoxDivs = page.getByXPath(baseXpath);
			Map<String, List<String>> catMap = new LinkedHashMap<String, List<String>>();

			if (!choiceBoxDivs.isEmpty()) {
				List<HtmlElement> titleSpans = choiceBoxDivs.get(0).getByXPath(titleXpath);
				for (HtmlElement titleSpan : titleSpans) {
					List<HtmlElement> titles = titleSpan.getByXPath(titleXpathIn);
					for (HtmlElement t : titles) {
						catMap.put(t.asText(), Lists.newArrayList());
						catMap.put(t.asText(), Lists.newArrayList());
					}
				}
				List<String> titleKeys = new ArrayList<>(catMap.keySet());
				List<HtmlElement> options = choiceBoxDivs.get(1).getByXPath("//*[@class='VSsw6']/div");
				int i = 0;
				for (HtmlElement titleSpan : options) {
					String titleAttr = titleSpan.getAttribute("data-facet");
					List<HtmlElement> titles = titleSpan.getByXPath("//*[@class='rqmJFb']/span");
					System.out.println("::titleKeys.get(i)::" + titleKeys.get(i));
					for (HtmlElement t : titles) {
						if (t.hasAttribute("data-facet") && t.getAttribute("data-facet").equalsIgnoreCase(titleAttr)) {
							if (!t.asText().equalsIgnoreCase("__placeholder__")
									&& !t.asText().equalsIgnoreCase("all")) {
								if(t.asText().length() > 0)
									catMap.get(titleKeys.get(i)).add(t.asText());
							}
						}
					}
					i++;
				}
			}
			return success(catMap);

		} catch (Exception e) {
			return error("could not find response");
		}
	}
}
