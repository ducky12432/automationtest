# automationtest

This suite of tests executes on the helloworld automation test site

these tests should be able to be run individually or sequentially as a Suite.

For the test Suite the first test till assert that the site is reachable
from there a new user will be created.

A demonstration of a valid and invalid login will also be demonstrated.

For this sample test the variables in the suites were hardcoded for simplicity of running.

This is not the best practise for a production CI environment.

In the case that these tests would be running as a part of the CI process we would set these parameters to be filled in during the maven build specifically for that individual run.

This would allow us to better control the input parameters across the test cases and maintain a clean and repeatable set of results.
