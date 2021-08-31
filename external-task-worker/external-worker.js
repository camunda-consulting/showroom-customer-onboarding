const { ZBClient } = require('zeebe-node');

const mailService = require('./mail.js');

const client = new ZBClient();

client.createWorker({
	taskType: 'emailService',
	taskHandler: (job, _, worker) => {
		let { application, mailBody, mailSubject } = job.variables;
		let email = application.applicant.email;
		worker.log(`Sending email with message content: ${mailBody}`)

		mailService.sendMail(mailSubject, mailBody, email)
			.then(async () => await job.complete())
			.catch(async (err) => {
				console.log(err);
				await job.fail(err.message, 0)
			})
	},
	fetchVariable: ['application', 'mailBody', 'mailSubject'],
});
