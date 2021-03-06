/*
 * Copyright 2010-2016 Amazon.com, Inc. or its affiliates. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License").
 * You may not use this file except in compliance with the License.
 * A copy of the License is located at
 *
 *  http://aws.amazon.com/apache2.0
 *
 * or in the "license" file accompanying this file. This file is distributed
 * on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */

package core.com.qiniu;

import java.io.InputStream;
import java.net.URI;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import core.com.qiniu.http.HttpMethodName;
import core.com.qiniu.util.AWSRequestMetrics;

/**
 * Default implementation of the {@linkplain com.amazonaws.Request} interface.
 * <p>
 * This class is only intended for internal use inside the AWS client libraries.
 * Callers shouldn't ever interact directly with objects of this class.
 */
public class DefaultRequest<T> implements Request<T> {

    /** The resource path being requested */
    private String resourcePath;

    /**
     * Map of the parameters being sent as part of this request.
     * <p>
     * Note that a LinkedHashMap is used, since we want to preserve the
     * insertion order so that members of a list parameter will still be ordered
     * by their indices when they are marshalled into the query string.
     */
    private Map<String, String> parameters = new LinkedHashMap<String, String>();

    /** Map of the headers included in this request */
    private Map<String, String> headers = new HashMap<String, String>();

    /** The service endpoint to which this request should be sent */
    private URI endpoint;

    /** The name of the service to which this request is being sent */
    private String serviceName;

    /**
     * The original, user facing request object which this internal request
     * object is representing
     */
    private final AmazonWebServiceRequest originalRequest;

    /** The HTTP method to use when sending this request. */
    private HttpMethodName httpMethod = HttpMethodName.POST;

    /** An optional stream from which to read the request payload. */
    private InputStream content;

    /** An optional time offset to account for clock skew */
    private int timeOffset;

    /** All AWS Request metrics are collected into this object. */
    private AWSRequestMetrics metrics;

    /**
     * Constructs a new DefaultRequest with the specified service name and the
     * original, user facing request object.
     *
     * @param serviceName The name of the service to which this request is being
     *            sent.
     * @param originalRequest The original, user facing, AWS request being
     *            represented by this internal request object.
     */
    public DefaultRequest(AmazonWebServiceRequest originalRequest, String serviceName) {
        this.serviceName = serviceName;
        this.originalRequest = originalRequest;
    }

    /**
     * Constructs a new DefaultRequest with the specified service name and no
     * specified original, user facing request object.
     *
     * @param serviceName The name of the service to which this request is being
     *            sent.
     */
    public DefaultRequest(String serviceName) {
        this(null, serviceName);
    }

    /**
     * Returns the original, user facing request object which this internal
     * request object is representing.
     *
     * @return The original, user facing request object which this request
     *         object is representing.
     */
    @Override
    public AmazonWebServiceRequest getOriginalRequest() {
        return originalRequest;
    }

    /**
     * @see com.amazonaws.Request#addHeader(String, String)
     */
    @Override
    public void addHeader(String name, String value) {
        headers.put(name, value);
    }

    /**
     * @see com.amazonaws.Request#getHeaders()
     */
    @Override
    public Map<String, String> getHeaders() {
        return headers;
    }

    /**
     * @see com.amazonaws.Request#setResourcePath(String)
     */
    @Override
    public void setResourcePath(String resourcePath) {
        this.resourcePath = resourcePath;
    }

    /**
     * @see com.amazonaws.Request#getResourcePath()
     */
    @Override
    public String getResourcePath() {
        return resourcePath;
    }

    /**
     * @see com.amazonaws.Request#addParameter(String,
     *      String)
     */
    @Override
    public void addParameter(String name, String value) {
        parameters.put(name, value);
    }

    /**
     * @see com.amazonaws.Request#getParameters()
     */
    @Override
    public Map<String, String> getParameters() {
        return parameters;
    }

    /**
     * @see com.amazonaws.Request#withParameter(String,
     *      String)
     */
    @Override
    public Request<T> withParameter(String name, String value) {
        addParameter(name, value);
        return this;
    }

    /**
     * @see com.amazonaws.Request#getHttpMethod()
     */
    @Override
    public HttpMethodName getHttpMethod() {
        return httpMethod;
    }

    /**
     * @see com.amazonaws.Request#setHttpMethod(com.amazonaws.http.HttpMethodName)
     */
    @Override
    public void setHttpMethod(HttpMethodName httpMethod) {
        this.httpMethod = httpMethod;
    }

    /**
     * @see com.amazonaws.Request#setEndpoint(URI)
     */
    @Override
    public void setEndpoint(URI endpoint) {
        this.endpoint = endpoint;
    }

    /**
     * @see com.amazonaws.Request#getEndpoint()
     */
    @Override
    public URI getEndpoint() {
        return endpoint;
    }

    /**
     * @see com.amazonaws.Request#getServiceName()
     */
    @Override
    public String getServiceName() {
        return serviceName;
    }

    /**
     * @see com.amazonaws.Request#getContent()
     */
    @Override
    public InputStream getContent() {
        return content;
    }

    /**
     * @see com.amazonaws.Request#setContent(InputStream)
     */
    @Override
    public void setContent(InputStream content) {
        this.content = content;
    }

    /**
     * @see com.amazonaws.Request#setHeaders(Map)
     */
    @Override
    public void setHeaders(Map<String, String> headers) {
        this.headers.clear();
        this.headers.putAll(headers);
    }

    /**
     * @see com.amazonaws.Request#setParameters(Map)
     */
    @Override
    public void setParameters(Map<String, String> parameters) {
        this.parameters.clear();
        this.parameters.putAll(parameters);
    }

    /**
     * @see com.amazonaws.Request#getTimeOffset
     */
    @Override
    public int getTimeOffset() {
        return timeOffset;
    }

    /**
     * @see Request#setTimeOffset(int)
     */
    @Override
    public void setTimeOffset(int timeOffset) {
        this.timeOffset = timeOffset;
    }

    /**
     * @see Request#setTimeOffset(int)
     */
    @Override
    public Request<T> withTimeOffset(int timeOffset) {
        setTimeOffset(timeOffset);
        return this;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(getHttpMethod()).append(" ");
        builder.append(getEndpoint()).append(" ");
        String resourcePath = getResourcePath();

        if (resourcePath == null) {
            builder.append("/");
        }
        else {
            if (!resourcePath.startsWith("/")) {
                builder.append("/");
            }
            builder.append(resourcePath);
        }
        builder.append(" ");
        if (!getParameters().isEmpty()) {
            builder.append("Parameters: (");
            for (String key : getParameters().keySet()) {
                String value = getParameters().get(key);
                builder.append(key).append(": ").append(value).append(", ");
            }
            builder.append(") ");
        }

        if (!getHeaders().isEmpty()) {
            builder.append("Headers: (");
            for (String key : getHeaders().keySet()) {
                String value = getHeaders().get(key);
                builder.append(key).append(": ").append(value).append(", ");
            }
            builder.append(") ");
        }

        return builder.toString();
    }

    @Override
    @Deprecated
    public AWSRequestMetrics getAWSRequestMetrics() {
        return metrics;
    }

    @Override
    @Deprecated
    public void setAWSRequestMetrics(AWSRequestMetrics metrics) {
        if (this.metrics == null) {
            this.metrics = metrics;
        } else {
            throw new IllegalStateException(
                    "AWSRequestMetrics has already been set on this request");
        }
    }
}
