package ai.jobiak.lambda.service;

import java.util.Locale;

import org.springframework.stereotype.Service;

import ai.jobiak.lambda.Jobiak;
import ai.jobiak.lambda.pojo.GenericRes;
import ai.jobiak.lambda.pojo.GoogleFiltersReq;

@Service
public class GoogleFiltersScrapeService extends Jobiak{
	 
	public GenericRes<?> getGfKeywords(final GoogleFiltersReq googleFiltersReq) {
	  return success(googleFiltersReq.getTitle().toUpperCase(Locale.ENGLISH));
	}
}
