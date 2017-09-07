### GammyRay-Test


__UploadObjectToS3__: It creates an object that emits an event notification.

__S3EventProcessor__: The notification triggers this lambda that is traced by X-Ray.

####Building Step of Test in Java SDK:

1. Add dependencies of AWS Lambda Java SDK in build.gradle:  
- aws-java-sdk-core
- aws-lambda-java-core
- aws-java-sdk-s3
- aws-lambda-java-events

2. Add __buildZip__ task in build.gradle

3. Run gradle build and find .zip package in __/build/distributions__

4. Create two lambda functions individually on AWS Lambda

__Settings:__

- Runtime: Java8
- UploadObjectToS3 Handler: org.racelab.gammaray.test.UploadObjectToS3::handler
- S3EventProcessor Handler: org.racelab.gammaray.test.S3EventProcessor::handleRequest
- Upload __GrammaRay-Test.zip__ package to both functions
- Check __Enable active tracing__ in Advanced Settings

5. Create S3 Bucket 

- __Add Notification__ of Events in Advanced Settings
- Check __ObjectCreated(All)__ in Events
- Select __Send to Lambda Function__: S3EventProcessor 

6. Test UploadObjectToS3 Lambda function with a configured test event of a selected string.

####Outcome:

1. UploadObjectToS3 gives information of successful uploading.

2. A object of string is created in S3 bucket that triggers S3EventProcessor.

3. Cloudwatch has all logs of both lambdas.

4. X-ray tracks both function individually.

