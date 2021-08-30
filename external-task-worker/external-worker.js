const { ZBClient } = require('zeebe-node');

const mailService = require('./mail.js');

const client = new ZBClient ();

client.createWorker({
	taskType: 'emailService',
	taskHandler: (job, _, worker) => {
		let { application, mailBody, mailSubject } = job.variables;
		let email = application.applicant.email;

		mailService.sendMail(mailSubject, mailBody, email)
			.then(async () => await job.complete())
			.catch(async (err) => {
				console.log(err);
				await job.fail(err.message, 0)
			})


		worker.log(`Sending email with message content: ${message_content}`)
		job.complete();
	},
	fetchVariable: ['application', 'mailBody', 'mailSubject'],
});
