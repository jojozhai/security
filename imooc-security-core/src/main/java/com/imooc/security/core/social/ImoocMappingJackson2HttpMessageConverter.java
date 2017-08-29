/**
 * 
 */
package com.imooc.security.core.social;


import java.util.ArrayList;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

/**
 * @author zhailiang
 *
 */
public class ImoocMappingJackson2HttpMessageConverter extends MappingJackson2HttpMessageConverter {

	/**
	 * Construct a new {@link MappingJackson2HttpMessageConverter} using default configuration
	 * provided by {@link Jackson2ObjectMapperBuilder}.
	 */
	public ImoocMappingJackson2HttpMessageConverter() {
		super();
		List<MediaType> supportedMediaTypes = new ArrayList<>();
		supportedMediaTypes.add(MediaType.TEXT_HTML);
		setSupportedMediaTypes(supportedMediaTypes);
	}
	
}
