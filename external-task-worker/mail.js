var mail = require('nodemailer');

const sendMail = async (subject, mailtext, toEmail) => {
  let transporter = mail.createTransport({
    host: process.env.SMTP_HOST,
    port: process.env.SMTP_PORT,
    secure: false, // true for 465, false for other ports
    auth: {
      user: process.env.SMTP_USER,
      pass: process.env.SMTP_PASSWORD
    }
  });

  process.env.NODE_TLS_REJECT_UNAUTHORIZED = "0";

  let info = await transporter.sendMail({
    from: process.env.MAIL_FROM,
    to: toEmail,
    subject: subject,
    text: mailtext
  }).catch((err) => {
    if(!toEmail.includes('@')){
      throw new Error(`The recipient's address '${toEmail}' does not contain a valid domain component (missing @).`);
    }else {
      throw new Error(err);
    }
  });

  console.log(`Message sent: ${info.messageId}`);
  return;
}

exports.sendMail = sendMail;
