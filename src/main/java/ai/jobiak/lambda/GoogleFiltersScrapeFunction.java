package ai.jobiak.lambda;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import ai.jobiak.lambda.pojo.GenericRes;
import ai.jobiak.lambda.pojo.GoogleFiltersReq;
import ai.jobiak.lambda.service.GoogleFiltersScrapeService;

@Component
public class GoogleFiltersScrapeFunction implements Function<GoogleFiltersReq, GenericRes<?>> {

	private final GoogleFiltersScrapeService googleFiltersScrapeService;

	public GoogleFiltersScrapeFunction(final GoogleFiltersScrapeService googleFiltersScrapeService) {
		this.googleFiltersScrapeService = googleFiltersScrapeService;
	}

	@Override
	public GenericRes<?> apply(GoogleFiltersReq t) {
		return googleFiltersScrapeService.getGfKeywords(t);
	}

}
