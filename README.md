# aws-s3-wrapper-jar-demo


I have used aws-s3-wrapper - version 0.1.11 to upload and download the files from s3 buckets.

To use this jar - 
1. Add @EnableS3Bucket in main class that enables the application to create beans defined inside the jar.
2. Autowire S3Client from package com.dinakaran.awss3wrapper.upload
3. Function upload accepts Multipart file as parameter and returns the path in s3 bucket.
4. Function downlaod accepts the path of the file as parameter and returns the file from s3 bucket.

The jar aws-s3-wrapper is under MIT license. Please feel free to contribute towards it if you like it or request for new features. I will try to implement new features in upcoming releases.
