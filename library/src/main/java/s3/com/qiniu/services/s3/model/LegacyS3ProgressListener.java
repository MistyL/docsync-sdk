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

/**
 * A proxy class that wraps the deprecated S3 progress listener and implements
 * the new {@link core.com.qiniu.event.ProgressListener} interface.
 *
 * @see ProgressListener
 * @see core.com.qiniu.event.ProgressListener
 */
public class LegacyS3ProgressListener implements core.com.qiniu.event.ProgressListener {

    @SuppressWarnings("deprecation")
    private final core.com.qiniu.event.ProgressListener listener;

    @SuppressWarnings("deprecation")
    public LegacyS3ProgressListener(final core.com.qiniu.event.ProgressListener listener) {
        this.listener = listener;
    }

    @SuppressWarnings("deprecation")
    public core.com.qiniu.event.ProgressListener unwrap() {
        return listener;
    }

    @Override
    @SuppressWarnings("deprecation")
    public void progressChanged(core.com.qiniu.event.ProgressEvent progressEvent) {
        if (listener == null)
            return;
        listener.progressChanged(transform(progressEvent));
    }

    @SuppressWarnings("deprecation")
    private ProgressEvent transform(core.com.qiniu.event.ProgressEvent event) {
        return new ProgressEvent(event.getEventCode(), event.getBytesTransferred());
    }

}
