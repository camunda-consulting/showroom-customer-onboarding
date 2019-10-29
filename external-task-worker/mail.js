var mail = require('nodemailer');

const sendMail = async (subject, mailtext, toEmail) => {
  let transporter = mail.createTransport({
    host: "mail.camunda.com",
    port: 25,
    secure: false, // true for 465, false for other ports
    auth: {
      user: process.env.APP_USER,
      pass: process.env.APP_PW
    }
  });

  process.env.NODE_TLS_REJECT_UNAUTHORIZED = "0";

  let info = await transporter.sendMail({
    from: process.env.APP_FROM,
    to: toEmail,
    subject: subject,
    text: mailtext
  }).catch((err) => {
    throw new Error(err);
  });

  console.log(`Message sent: ${info.messageId}`);
  return;
}

exports.sendMail = sendMail;
