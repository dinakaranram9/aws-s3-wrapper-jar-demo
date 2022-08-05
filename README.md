# aws-s3-wrapper-jar-demo

I have used aws-s3-wrapper - version 0.1.11 to upload and download the files from s3 buckets.

To use this jar - 
1. Add the following dependecy in pom.xml
      		<dependency>
                        <groupId>io.github.dinakaranram9</groupId>
                              <artifactId>aws-s3-wrapper</artifactId>
                        <version>0.1.11</version>
		      </dependency>
2. Add @EnableS3Bucket in main class that enables the application to create beans defined inside the jar.
3. Autowire S3Client from package com.dinakaran.awss3wrapper.upload
4. Function upload accepts Multipart file as parameter and returns the path in s3 bucket.
5. Function downlaod accepts the path of the file as parameter and returns the file from s3 bucket.
6. Add the following properties in application.properties file that enables to connect to s3 bucket 
        spring.s3.accessKey=XXX
        spring.s3.secret=XXX
        spring.s3.endpointUrl=XXX
        spring.s3.bucketName=XXX
        spring.s3.applicationPath=default/
        spring.s3.env=local/


The jar aws-s3-wrapper is under MIT license. Please feel free to contribute towards it if you like it or request for new features. I will try to implement new features in upcoming releases.
