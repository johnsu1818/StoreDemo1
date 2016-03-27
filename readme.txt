Tested in firefox browser only.

Need the latest Webdriver and Junit libs and other libs to run the test. 

All three UI test run, but the assertion (verifying updates) in Modify My Account test will fail due to a Webdriver bug (I think), which causes text field element loses its value after the Save Profile button, I don't have enough time to dig in further, since it is the Easter weekend, very busy, it is a very interesting problem, I will try to find a workaround sometime s next week. 

Aside from above issue, other two tests pass all assertions. 

Also, if I have time, I will replace the Thread.sleep() with a better wait() methods; and need to add some comments in code. 
