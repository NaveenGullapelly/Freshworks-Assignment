# Freshworks-Assignment
Built a file-Based key-value data store that supports CRD(Create,Read,Delete) operations.This data store is meant to be used as a local storage for single process on one laptop.

The data store will support the following :

It can be initialized using an optional file path. If one is not provided, it will reliably create itself in a reasonable location on the laptop.
A new key-value pair can be added to the data store using the Create operation. The key is always a string - capped at 32chars. The value is always a JSON object - capped at 16KB.
If Create is invoked for an existing key, an appropriate error must be returned.
A Read operation on a key can be performed by providing the key, and receiving the value in response, as a JSON object.
A Delete operation can be performed by providing the key.
Every key supports setting a Time-To-Live property when it is created. This property is optional. If provided, it will be evaluated as an integer defining the number of seconds the key must be retained in the data store. Once the Time-To-Live for a key has expired, the key will no longer be available for Read or Delete operations.
Appropriate error responses must always be returned to a client if it uses the data store in unexpected ways or breaches any limits.
The file size never exceeds 1GB.
Go through the FileBasedStore.java file and ScreenshotOutput.jpeg files that are attached here with in order to understand clearly how the code works and how to perform operations in this.
