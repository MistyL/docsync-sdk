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

package s3.com.qiniu.services.s3.model;

import core.com.qiniu.AmazonWebServiceRequest;
import core.com.qiniu.HttpMethod;
import s3.com.qiniu.services.s3.AmazonS3;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * Contains options to generate a pre-signed URL for an Amazon S3 resource.
 * </p>
 * <p>
 * Pre-signed URLs allow clients to form a URL for an Amazon S3 resource and
 * sign it with the current AWS security credentials. A pre-signed URL may be
 * passed around for other users to access the resource without providing them
 * access to an account's AWS security credentials.
 * </p>
 *
 * @see AmazonS3#generatePresignedUrl(GeneratePresignedUrlRequest)
 */
public class GeneratePresignedUrlRequest extends AmazonWebServiceRequest {

    /**
     * The HTTP method (GET, PUT, DELETE, HEAD) to be used in this request and
     * when the pre-signed URL is used
     */
    private HttpMethod method;

    /** The name of the bucket involved in this request */
    private String bucketName;

    /** The key of the object involved in this request */
    private String key;

    /**
     * The optional Content-Type header that will be sent when the presigned URL
     * is accessed
     */
    private String contentType;

    /**
     * The optional Content-MD5 header that will be sent when the presigned URL
     * is accessed
     */
    private String contentMd5;

    /**
     * An optional expiration date at which point the generated pre-signed URL
     * will no longer be accepted by Amazon S3. If not specified, a default
     * value will be supplied.
     */
    private Date expiration;

    /**
     * An optional map of additional parameters to include in the pre-signed
     * URL. Adding additional request parameters enables more advanced
     * pre-signed URLs, such as accessing Amazon S3's torrent resource for an
     * object, or for specifying a version ID when accessing an object.
     */
    private Map<String, String> requestParameters = new HashMap<String, String>();

    /**
     * Optional field that overrides headers on the response.
     */
    private ResponseHeaderOverrides responseHeaders;

    /**
     * Optional customer-provided server-side encryption key to use as part of
     * the generated pre-signed URL.
     */
    private SSECustomerKey sseCustomerKey;

    /**
     * Creates a new request for generating a pre-signed URL that can be used as
     * part of an HTTP GET request to access the Amazon S3 object stored under
     * the specified key in the specified bucket.
     *
     * @param bucketName The name of the bucket containing the desired Amazon S3
     *            object.
     * @param key The key under which the desired Amazon S3 object is stored.
     */
    public GeneratePresignedUrlRequest(String bucketName, String key) {
        this(bucketName, key, HttpMethod.GET);
    }

    /**
     * <p>
     * Creates a new request for generating a pre-signed URL that can be used as
     * part of an HTTP request to access the specified Amazon S3 resource.
     * </p>
     * <p>
     * When specifying an HTTP method, you <b>must</b> send the pre-signed URL
     * with the same HTTP method in order to successfully use the pre-signed
     * URL.
     * </p>
     *
     * @param bucketName The name of the Amazon S3 bucket involved in the
     *            operation.
     * @param key The key of the Amazon S3 object involved in the operation.
     * @param method The HTTP method (GET, PUT, DELETE, HEAD) to be used in the
     *            request when the pre-signed URL is used.
     */
    public GeneratePresignedUrlRequest(String bucketName, String key, HttpMethod method) {
        this.bucketName = bucketName;
        this.key = key;
        this.method = method;
    }

    /**
     * The HTTP method (GET, PUT, DELETE, HEAD) to be used in this request. The
     * same HTTP method <b>must</b> be used in the request when the pre-signed
     * URL is used.
     *
     * @return The HTTP method (GET, PUT, DELETE, HEAD) to be used in this
     *         request and when the pre-signed URL is used.
     */
    public HttpMethod getMethod() {
        return method;
    }

    /**
     * Sets the HTTP method (GET, PUT, DELETE, HEAD) to be used in this request.
     * The same HTTP method <b>must</b> be used in the request when the
     * pre-signed URL is used.
     *
     * @param method The HTTP method (GET, PUT, DELETE, HEAD) to be used in this
     *            request.
     */
    public void setMethod(HttpMethod method) {
        this.method = method;
    }

    /**
     * Sets the HTTP method (GET, PUT, DELETE, HEAD) to be used in this request,
     * and returns this request object to enable additional method calls to be
     * chained together.
     * <p>
     * The same HTTP method <b>must</b> be used in the request when the
     * pre-signed URL is used.
     *
     * @param method The HTTP method (GET, PUT, DELETE, HEAD) to be used in this
     *            request.
     * @return The updated request object, so that additional method calls can be
     *         chained together.
     */
    public GeneratePresignedUrlRequest withMethod(HttpMethod method) {
        setMethod(method);
        return this;
    }

    /**
     * Returns the name of the bucket involved in this request.
     *
     * @return the name of the bucket involved in this request.
     */
    public String getBucketName() {
        return bucketName;
    }

    /**
     * Sets the name of the bucket involved in this request.
     *
     * @param bucketName the name of the bucket involved in this request.
     */
    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    /**
     * Sets the name of the bucket involved in this request, and returns this
     * request object to enable additional method calls to be chained together.
     *
     * @param bucketName the name of the bucket involved in this request.
     * @return The updated request object, so that additional method calls can be
     *         chained together.
     */
    public GeneratePresignedUrlRequest withBucketName(String bucketName) {
        setBucketName(bucketName);
        return this;
    }

    /**
     * Returns the key of the object involved in this request.
     *
     * @return The key of the object involved in this request.
     */
    public String getKey() {
        return key;
    }

    /**
     * Sets the key of the object involved in this request.
     *
     * @param key the key of the object involved in this request.
     */
    public void setKey(String key) {
        this.key = key;
    }

    /**
     * Sets the key of the object involved in this request, and returns this
     * request object to enable additional method calls to be chained together.
     *
     * @param key the key of the object involved in this request.
     * @return The updated request object, so that additional method calls can be
     *         chained together.
     */
    public GeneratePresignedUrlRequest withKey(String key) {
        setKey(key);
        return this;
    }

    /**
     * The expiration date at which point the new pre-signed URL will no longer
     * be accepted by Amazon S3. If not specified, a default value will be
     * supplied.
     *
     * @return The expiration date at which point the new pre-signed URL will no
     *         longer be accepted by Amazon S3.
     */
    public Date getExpiration() {
        return expiration;
    }

    /**
     * Sets the expiration date at which point the new pre-signed URL will no
     * longer be accepted by Amazon S3. If not specified, a default value will
     * be supplied.
     *
     * @param expiration The expiration date at which point the new pre-signed
     *            URL will no longer be accepted by Amazon S3.
     */
    public void setExpiration(Date expiration) {
        this.expiration = expiration;
    }

    /**
     * Sets the expiration date at which point the new pre-signed URL will no
     * longer be accepted by Amazon S3, and returns this request object to
     * enable additional method calls to be chained together.
     * <p>
     * If not specified, a default value will be supplied.
     *
     * @param expiration The expiration date at which point the new pre-signed
     *            URL will no longer be accepted by Amazon S3.
     * @return The updated request object, so that additional method calls can be
     *         chained together.
     */
    public GeneratePresignedUrlRequest withExpiration(Date expiration) {
        setExpiration(expiration);
        return this;
    }

    /**
     * Adds an additional request parameter to be included in the pre-signed
     * URL. Adding additional request parameters enables more advanced
     * pre-signed URLs, such as accessing Amazon S3's torrent resource for an
     * object, or for specifying a version ID when accessing an object.
     *
     * @param key The name of the request parameter, as it appears in the URL's
     *            query string (e.g. versionId).
     * @param value The (optional) value of the request parameter being added.
     */
    public void addRequestParameter(String key, String value) {
        requestParameters.put(key, value);
    }

    /**
     * Returns the complete map of additional request parameters to be included
     * in the pre-signed URL.
     *
     * @return The complete map of additional request parameters to be included
     *         in the pre-signed URL.
     */
    public Map<String, String> getRequestParameters() {
        return requestParameters;
    }

    /**
     * Returns the headers to be overridden in the service response.
     *
     * @return the headers to be overridden in the service response.
     */
    public ResponseHeaderOverrides getResponseHeaders() {
        return responseHeaders;
    }

    /**
     * Sets the headers to be overridden in the service response.
     *
     * @param responseHeaders The headers to be overridden in the service
     *            response.
     */
    public void setResponseHeaders(ResponseHeaderOverrides responseHeaders) {
        this.responseHeaders = responseHeaders;
    }

    /**
     * Sets the headers to be overridden in the service response and returns
     * this object, for method chaining.
     *
     * @param responseHeaders The headers to be overridden in the service
     *            response.
     * @return This {@link GeneratePresignedUrlRequest} for method chaining.
     */
    public GeneratePresignedUrlRequest withResponseHeaders(ResponseHeaderOverrides responseHeaders) {
        setResponseHeaders(responseHeaders);
        return this;
    }

    /**
     * Gets the expected content-type of the request. The content-type is
     * included in the signature.
     *
     * @return The expected content-type
     */
    public String getContentType() {
        return contentType;
    }

    /**
     * Sets the expected content-type of the request. The content-type is
     * included in the signature.
     *
     * @param contentType The expected content-type
     */
    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    /**
     * Sets the expected content-type of the request and returns this object,
     * for method chaining.
     *
     * @param contentType The expected content-type
     * @return This {@link GeneratePresignedUrlRequest} for method chaining.
     */
    public GeneratePresignedUrlRequest withContentType(String contentType) {
        setContentType(contentType);
        return this;
    }

    /**
     * Gets the expected content-md5 header of the request. This header value
     * will be included when calculating the signature, and future requests must
     * include the same content-md5 header value to access the presigned URL.
     *
     * @return The expected content-md5 header value.
     */
    public String getContentMd5() {
        return contentMd5;
    }

    /**
     * Sets the expected content-md5 header of the request. This header value
     * will be included when calculating the signature, and future requests must
     * include the same content-md5 header value to access the presigned URL.
     *
     * @param contentMd5 The expected content-md5 header value.
     */
    public void setContentMd5(String contentMd5) {
        this.contentMd5 = contentMd5;
    }

    /**
     * Sets the expected content-md5 header of the request and returns this
     * object, for method chaining.
     *
     * @param contentMd5 The expected content-md5 header value.
     * @return This {@link GeneratePresignedUrlRequest} for method chaining.
     */
    public GeneratePresignedUrlRequest withContentMd5(String contentMd5) {
        this.contentMd5 = contentMd5;
        return this;
    }

    /**
     * Returns the customer-provided server-side encryption key to use as part
     * of the generated pre-signed URL.
     *
     * @return The customer-provided server-side encryption key to use as part
     *         of the generated pre-signed URL.
     */
    public SSECustomerKey getSSECustomerKey() {
        return sseCustomerKey;
    }

    /**
     * Sets the customer-provided server-side encryption key to use as part of
     * the generated pre-signed URL.
     *
     * @param sseKey The customer-provided server-side encryption key to use as
     *            part of the generated pre-signed URL.
     */
    public void setSSECustomerKey(SSECustomerKey sseKey) {
        this.sseCustomerKey = sseKey;
    }

    /**
     * Sets the customer-provided server-side encryption key to use as part of
     * the generated pre-signed URL, and returns the updated request object so
     * that additional method calls can be chained together.
     *
     * @param sseKey The customer-provided server-side encryption key to use as
     *            part of the generated pre-signed URL.
     * @return This updated request object so that additional method calls can be
     *         chained together.
     */
    public GeneratePresignedUrlRequest withSSECustomerKey(SSECustomerKey sseKey) {
        setSSECustomerKey(sseKey);
        return this;
    }
}
