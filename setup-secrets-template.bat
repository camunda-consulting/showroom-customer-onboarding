set ZEEBE_ADDRESS=
set ZEEBE_CLIENT_ID=
set ZEEBE_CLIENT_SECRET=
set ZEEBE_AUTHORIZATION_SERVER_URL=

set DEMO_PATH=

set zeebe.client.cloud.clusterId=%ZEEBE_ADDRESS:~0, 36%
set zeebe.client.cloud.clientId=%ZEEBE_CLIENT_ID%
set zeebe.client.cloud.clientSecret=%ZEEBE_CLIENT_SECRET%
cd %DEMO_PATH%
start cmd.exe /c "mvn spring-boot:run  -Dmaven.test.skip=true"
start http://localhost:8080/camunda/online/banking/index.html?lang=en
exit