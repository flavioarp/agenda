package agenda.controller.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/**
 * Adapts a HttpServletRequestWrapper to support printing the contents of a
 * HttpServletRequest
 */
public class MyHttpServletRequestWrapper extends HttpServletRequestWrapper {

	public MyHttpServletRequestWrapper(HttpServletRequest request) {
		super(request);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		HttpServletRequest request = (HttpServletRequest) getRequest();

		StringBuilder sb = new StringBuilder();

		sb.append("Request Method = [" + request.getMethod() + "], ");
		sb.append("Request URL Path = [" + request.getRequestURL() + "], ");

		String[] fieldIds = { "headers", "parameters" };

		for (String fieldId : fieldIds) {
			Boolean areHeaders = fieldId.equals("headers");

			Function<String, String> mapper = (areHeaders)
					? (headerName) -> headerName + " : " + Collections.list(request.getHeaders(headerName))
					: (p) -> p + " : " + Arrays.asList(request.getParameterValues(p));

			ArrayList<String> names = Collections
					.list((areHeaders) ? request.getHeaderNames() : request.getParameterNames());

			String s = names.stream().map(mapper).collect(Collectors.joining(", "));

			sb.append("Request " + fieldId + ": " + ((s.isEmpty() ? "NONE," : "[" + s + "],")));
		}

		return sb.toString();
	}
}
