package ai.jobiak.lambda.handler;

import org.springframework.cloud.function.adapter.aws.SpringBootRequestHandler;

import ai.jobiak.lambda.pojo.GoogleFiltersReq;
import ai.jobiak.lambda.pojo.GenericRes;

public class GoogleFiltersScrapeFunctionHandler extends SpringBootRequestHandler<GoogleFiltersReq, GenericRes<?>>{

}
