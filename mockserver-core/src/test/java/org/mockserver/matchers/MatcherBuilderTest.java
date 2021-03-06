package org.mockserver.matchers;

import org.junit.Test;
import org.mockserver.model.*;

import static org.junit.Assert.assertTrue;

/**
 * @author jamesdbloom
 */
public class MatcherBuilderTest {

    private HttpRequest httpRequest = new HttpRequest()
            .withMethod("GET")
            .withPath("some_path")
            .withQueryStringParameter(new Parameter("queryStringParameterName", "queryStringParameterValue"))
            .withURL("http://www.example.com")
            .withBody(new StringBody("some_body", Body.Type.EXACT))
            .withHeaders(new Header("name", "value"))
            .withCookies(new Cookie("name", "value"));
    ;

    @Test
    public void shouldCreateMatcherThatMatchesAllFields() {
        // when
        HttpRequestMatcher httpRequestMapper = new MatcherBuilder().transformsToMatcher(httpRequest);

        // then
        assertTrue(httpRequestMapper.matches(httpRequest));
    }

    @Test
    public void shouldCreateMatcherThatIgnoresMethod() {
        // when
        HttpRequestMatcher httpRequestMapper = new MatcherBuilder().transformsToMatcher(
                new HttpRequest()
                        .withMethod("")
                        .withPath("some_path")
                        .withQueryStringParameter(new Parameter("queryStringParameterName", "queryStringParameterValue"))
                        .withURL("http://www.example.com")
                        .withBody(new StringBody("some_body", Body.Type.EXACT))
                        .withHeaders(new Header("name", "value"))
                        .withCookies(new Cookie("name", "value"))
        );

        // then
        assertTrue(httpRequestMapper.matches(httpRequest));
    }

    @Test
    public void shouldCreateMatcherThatIgnoresPath() {
        // when
        HttpRequestMatcher httpRequestMapper = new MatcherBuilder().transformsToMatcher(
                new HttpRequest()
                        .withMethod("GET")
                        .withPath("")
                        .withQueryStringParameter(new Parameter("queryStringParameterName", "queryStringParameterValue"))
                        .withURL("http://www.example.com")
                        .withBody(new StringBody("some_body", Body.Type.EXACT))
                        .withHeaders(new Header("name", "value"))
                        .withCookies(new Cookie("name", "value"))
        );

        // then
        assertTrue(httpRequestMapper.matches(httpRequest));
    }

    @Test
    public void shouldCreateMatcherThatIgnoresQueryString() {
        // when
        HttpRequestMatcher httpRequestMapper = new MatcherBuilder().transformsToMatcher(
                new HttpRequest()
                        .withMethod("GET")
                        .withPath("some_path")
                        .withQueryStringParameters()
                        .withURL("http://www.example.com")
                        .withBody(new StringBody("some_body", Body.Type.EXACT))
                        .withHeaders(new Header("name", "value"))
                        .withCookies(new Cookie("name", "value"))
        );

        // then
        assertTrue(httpRequestMapper.matches(httpRequest));
    }

    @Test
    public void shouldCreateMatcherThatIgnoresBodyParameters() {
        // when
        HttpRequestMatcher httpRequestMapper = new MatcherBuilder().transformsToMatcher(
                new HttpRequest()
                        .withMethod("GET")
                        .withPath("some_path")
                        .withQueryStringParameter(new Parameter("queryStringParameterName", "queryStringParameterValue"))
                        .withURL("http://www.example.com")
                        .withBody(new ParameterBody())
                        .withHeaders(new Header("name", "value"))
                        .withCookies(new Cookie("name", "value"))
        );

        // then
        assertTrue(httpRequestMapper.matches(httpRequest));
    }

    @Test
    public void shouldCreateMatcherThatIgnoresURL() {
        // when
        HttpRequestMatcher httpRequestMapper = new MatcherBuilder().transformsToMatcher(
                new HttpRequest()
                        .withMethod("GET")
                        .withPath("some_path")
                        .withQueryStringParameter(new Parameter("queryStringParameterName", "queryStringParameterValue"))
                        .withURL("")
                        .withBody(new StringBody("some_body", Body.Type.EXACT))
                        .withHeaders(new Header("name", "value"))
                        .withCookies(new Cookie("name", "value"))
        );

        // then
        assertTrue(httpRequestMapper.matches(httpRequest));
    }

    @Test
    public void shouldCreateMatcherThatIgnoresBody() {
        // when
        HttpRequestMatcher httpRequestMapper = new MatcherBuilder().transformsToMatcher(
                new HttpRequest()
                        .withMethod("GET")
                        .withPath("some_path")
                        .withQueryStringParameter(new Parameter("queryStringParameterName", "queryStringParameterValue"))
                        .withURL("http://www.example.com")
                        .withBody(new StringBody("", Body.Type.EXACT))
                        .withHeaders(new Header("name", "value"))
                        .withCookies(new Cookie("name", "value"))
        );

        // then
        assertTrue(httpRequestMapper.matches(httpRequest));
    }

    @Test
    public void shouldCreateMatcherThatIgnoresHeaders() {
        // when
        HttpRequestMatcher httpRequestMapper = new MatcherBuilder().transformsToMatcher(
                new HttpRequest()
                        .withMethod("GET")
                        .withPath("some_path")
                        .withQueryStringParameter(new Parameter("queryStringParameterName", "queryStringParameterValue"))
                        .withURL("http://www.example.com")
                        .withBody(new StringBody("some_body", Body.Type.EXACT))
                        .withHeaders()
                        .withCookies(new Cookie("name", "value"))
        );

        // then
        assertTrue(httpRequestMapper.matches(httpRequest));
    }

    @Test
    public void shouldCreateMatcherThatIgnoresCookies() {
        // when
        HttpRequestMatcher httpRequestMapper = new MatcherBuilder().transformsToMatcher(
                new HttpRequest()
                        .withMethod("GET")
                        .withPath("some_path")
                        .withQueryStringParameter(new Parameter("queryStringParameterName", "queryStringParameterValue"))
                        .withURL("http://www.example.com")
                        .withBody(new StringBody("some_body", Body.Type.EXACT))
                        .withHeaders(new Header("name", "value"))
                        .withCookies()
        );

        // then
        assertTrue(httpRequestMapper.matches(httpRequest));
    }
}
