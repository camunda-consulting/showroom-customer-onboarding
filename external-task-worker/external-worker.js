const mailService = require('./mail.js');

const { ZBCLIENT } = require('zeebe-node');

const zbc = new ZBClient({
	camundaCloud: {
		clusterId: 'CAMUNDA CLUSTER ID',
		clientId: 'CAMUNDA CLIENT ID',
		clientSecret: 'CAMUNDA CLIENT SECRET',
	},
});

zbc.createWorker({
	taskType: 'emailService',
	taskHandler: (job, _, worker) => {
		let { application, mailBody, mailSubject } = job.variables;
		let email = application.applicant.email;

		mailService.sendMail(mailSubject, mailBody, email)
			.then(async () => await job.complete())
			.catch(async (err) => {
				console.log(err);
			})


		worker.log(`Sending email with message content: ${message_content}`)
		job.complete();
	},
	fetchVariable: ['application', 'mailBody', 'mailSubject'],
});
