package org.racelab.gammaray.test;

import com.amazonaws.handlers.RequestHandler2;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.s3.event.S3EventNotification.S3EventNotificationRecord;
import com.amazonaws.services.lambda.runtime.events.S3Event;


/**
 * Created by michaelzhang on 9/7/17.
 */
public class S3EventProcessor extends RequestHandler2 {

    public String handleRequest(S3Event s3event, Context context) {
        S3EventNotificationRecord record = s3event.getRecords().get(0);
        String content = record.getS3().getObject().toString();
        System.out.println(content);
        return content;
    }
}
