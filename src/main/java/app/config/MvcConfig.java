package app.config;

import java.util.function.Function;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UrlPathHelper;

import app.util.HttpUtils;

@Configuration
public class MvcConfig extends WebMvcConfigurerAdapter {

	private UrlPathHelper urlPathHelper = new UrlPathHelper();

	/**
	 * thymeleaf-add-parameter-to-current-url
	 * {@linkplain http://stackoverflow.com/questions/27623405/thymeleaf-add-parameter-to-current-url}
	 */
	@Bean
	public Function<String, String> currentUrlWithoutParam() {
		return param -> {
			HttpServletRequest request = HttpUtils.getCurrentRequest();
			String path = urlPathHelper.getPathWithinApplication(request);
			return ServletUriComponentsBuilder.fromRequest(request).replacePath(path).replaceQueryParam(param)
					.scheme(null).host(null).build(false).toUriString();
		};
	}

	@Bean
	public Function<Iterable<String>, String> currentUrlWithoutParams() {
		return params -> {
			HttpServletRequest request = HttpUtils.getCurrentRequest();
			String path = urlPathHelper.getPathWithinApplication(request);
			ServletUriComponentsBuilder builder = ServletUriComponentsBuilder.fromRequest(request);
			builder.replacePath(path);
			for (String param : params) {
				builder.replaceQueryParam(param);
			}
			return builder.scheme(null).host(null).build(false).toUriString();
		};
	}

}
