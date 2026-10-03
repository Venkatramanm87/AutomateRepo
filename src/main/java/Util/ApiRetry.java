package Util;

import io.github.resilience4j.retry.Retry;
import io.github.resilience4j.retry.RetryConfig;
import io.restassured.response.Response;
import java.time.Duration;
import java.util.concurrent.Callable;

public class ApiRetry {

public static Response execute(Callable<Response> apicall) throws Exception {
	RetryConfig retryConfig = RetryConfig.custom()
			.maxAttempts(3)
			.waitDuration(Duration.ofSeconds(2))
			.build();
	Retry retry = Retry.of("apiretry", retryConfig);
	return retry.executeCallable(apicall);
			
}
	
}
