# automationtest

This suite of tests executes on the helloworld automation test site.

These tests should be able to be run individually or sequentially as a Suite.

Order of execution if done manually would be SiteUp, Register, Login, FailLogin.

For this Test Suite the first test will assert that the site is reachable,
from there a new user will be created.

A demonstration of a valid and invalid login will also be demonstrated.

All of these tests could be condensed to a single class but for clarity and demonstration purposes I kept them separate.

Running the Register tests assumes that CAPTCHA is not present on the page.

There are multiple ways around CAPTCHA.
 You could either disable it on the test server,
  or better yet query the server service and compare that the intended CAPTCHA is what is being displayed.

Using a Facebook login will be very similar to the normal login with the
 exception that you will need a facebook test account.

You would also otherwise assert that the information in each field was filled in properly instead of filling it in.

For this sample test the variables in the suites were hardcoded for simplicity of running manually.

This is not the best practise for a production CI environment.

In the case that these tests would be running as a part of the CI process
 we would set these parameters to be filled in during the maven build specifically for that individual run.

This would allow us to better control the input parameters
 across the test cases and maintain a clean and repeatable set of results.
