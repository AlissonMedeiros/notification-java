package org.medeiros.application;

import org.springframework.test.context.ActiveProfilesResolver;

import java.util.Map;

public class ActiveProfileResolver implements ActiveProfilesResolver {
	@Override
	public String[] resolve(Class<?> aClass) {
		Map<String, String> env = System.getenv();
		if (env.containsKey("REMOVE_ENV")) {
			return new String[]{};
		}
		return new String[]{"test"};
	}
}
