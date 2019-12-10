const {
  Client,
  logger
} = require("camunda-external-task-client-js");
const got = require("got");

require('dotenv').config();

const mailService = require('./mail.js');

let second = 1000;
let minute = 60 * second;

const config = {
  baseUrl: "customer-onboarding/camunda/rest",
  asyncResponseTimeout: 5 * second,
  interval: 2 * second,
  lockDuration: 2 * second,
  use: logger,
  port: 8080,
  autoPoll: false
};

const client = new Client(config);

const request = async (method, path, options) => {
  const url = `${config.baseUrl}${path}`;

  options.protocol = 'http:';
  options.port = 8080;

  const newOptions = {
    method,
    ...options
  };

  if (client.engineService.interceptors) {
    newOptions = client.engineService.interceptors.reduce((config, interceptor) => {
      return interceptor(config);
    }, newOptions);
  }

  try {
    const {
      body
    } = await got(url, newOptions);
    return body;
  } catch (e) {
    console.log(e);
    throw e.response ? e.response : e;
  }
}

client.engineService.request = request.bind(client.engineService);

client.on('poll:error', (err) => console.log(`The error is:  ${err}`));

var emailServicehandler = async (task, taskService) => {
  // get the process variable 'score'
  let application = JSON.parse(task.variables.get("application"));
  let mailtext = task.variables.get("mailBody");
  let subject = task.variables.get("mailSubject");
  let email = application.applicant.email;

  mailService.sendMail(subject, mailtext, email)
  .then(async () => await taskService.complete(task))
  .catch(async (err) => {
      console.log(err);
      await taskService.handleFailure(task, {
        errorMessage: err.message,
        errorDetails: err.stack,
        retries: 0,
        retryTimeout: 1000
      });
    });

}

// do this because data generation in showcase lasts about 10 minutes

setTimeout(() => {
  client.start();
  client.subscribe("emailService", async ({task, taskService}) => emailServicehandler(task, taskService));
}, 10 * minute);
