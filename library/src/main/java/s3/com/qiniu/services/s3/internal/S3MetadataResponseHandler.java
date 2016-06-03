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

package s3.com.qiniu.services.s3.internal;

import core.com.qiniu.AmazonWebServiceResponse;
import core.com.qiniu.http.HttpResponse;
import s3.com.qiniu.services.s3.model.ObjectMetadata;

/**
 * S3 response handler that knows how to pull S3 object metadata out of a
 * response and unmarshall it into an S3ObjectMetadata object.
 */
public class S3MetadataResponseHandler extends AbstractS3ResponseHandler<ObjectMetadata> {

    /**
     * @see core.com.qiniu.http.HttpResponseHandler#handle(HttpResponse)
     */
    @Override
    public AmazonWebServiceResponse<ObjectMetadata> handle(HttpResponse response) throws Exception {
        ObjectMetadata metadata = new ObjectMetadata();
        populateObjectMetadata(response, metadata);

        AmazonWebServiceResponse<ObjectMetadata> awsResponse = parseResponseMetadata(response);
        awsResponse.setResult(metadata);
        return awsResponse;
    }

}
