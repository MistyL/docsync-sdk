/*
 * Copyright 2011-2016 Amazon.com, Inc. or its affiliates. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at:
 *
 *    http://aws.amazon.com/apache2.0
 *
 * This file is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES
 * OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and
 * limitations under the License.
 */

package s3.com.qiniu.services.s3.internal;


import java.util.ArrayList;
import java.util.List;

import s3.com.qiniu.services.s3.model.DeleteObjectsResult.DeletedObject;
import s3.com.qiniu.services.s3.model.MultiObjectDeleteException;
import s3.com.qiniu.services.s3.model.MultiObjectDeleteException.DeleteError;

/**
 * Service response for deleteObjects API call. Not exposed to clients directly,
 * but broken up into two classes to differentiate normal and exceptional
 * completion of the API.
 *
 * @see DeleteObjectsResult
 * @see MultiObjectDeleteException
 * @see AmazonS3Client#deleteObjects(com.amazonaws.services.s3.model.DeleteObjectsRequest)
 */
public class DeleteObjectsResponse {

    private List<DeletedObject> deletedObjects;
    private List<DeleteError> errors;

    public DeleteObjectsResponse() {
        this(new ArrayList<DeletedObject>(), new ArrayList<DeleteError>());
    }

    public DeleteObjectsResponse(List<DeletedObject> deletedObjects, List<DeleteError> errors) {
        this.deletedObjects = deletedObjects;
        this.errors = errors;
    }

    public List<DeletedObject> getDeletedObjects() {
        return deletedObjects;
    }

    public void setDeletedObjects(List<DeletedObject> deletedObjects) {
        this.deletedObjects = deletedObjects;
    }

    public List<DeleteError> getErrors() {
        return errors;
    }

    public void setErrors(List<DeleteError> errors) {
        this.errors = errors;
    }
}
