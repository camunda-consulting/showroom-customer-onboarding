$ENV:ZEEBE_ADDRESS = '';
$ENV:ZEEBE_CLIENT_ID = '';
$ENV:ZEEBE_CLIENT_SECRET = '';
$ENV:ZEEBE_AUTHORIZATION_SERVER_URL = '';

$ENV:DEMO_PATH = '';

${ENV:zeebe.client.cloud.clusterId} = $ENV:ZEEBE_ADDRESS.substring(0,36);
${ENV:zeebe.client.cloud.clientId} = $ENV:ZEEBE_CLIENT_ID;
${ENV:zeebe.client.cloud.clientSecret} = $ENV:ZEEBE_CLIENT_SECRET;

Set-Location $ENV:DEMO_PATH;
Start-Process 'http://localhost:8080/camunda/online/banking/index.html?lang=en';
mvn spring-boot:run '-Dmaven.test.skip=true';
