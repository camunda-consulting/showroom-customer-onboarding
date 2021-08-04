const {Storage} = require('@google-cloud/storage');
const shouldUpload = process.env.mode == "demo";
const shouldDownload = process.env.mode == "run";

process.env.GOOGLE_APPLICATION_CREDENTIALS = "auth/bucketAuth.json"


  async function downloadFile(
    bucketName = 'showroom-database',
    srcFilename = 'testdb.h2.db',
    destFilename = '/root/testdb.h2.db'
  ) {
    const storage = new Storage();

    const options = {
      destination: destFilename,
      validation: "md5"
    };

    await storage
      .bucket(bucketName)
      .file(srcFilename)
      .download(options);

    console.log(
      `gs://${bucketName}/${srcFilename} downloaded to ${destFilename}.`
    );
  }

async function uploadFile (
  bucketName = 'showroom-database',
  srcFilename = '/root/testdb.h2.db',
  destFilename = 'testdb.h2.db'
) {
  const storage = new Storage();

  const options = {
    gzip: true,
    validation: "md5",
    metadata: {
        cacheControl: 'public, max-age=31536000',
      }
  };

  await storage
    .bucket(bucketName)
    .upload(srcFilename, options);

  console.log(
    `${destFilename} uploaded to gs://${bucketName}/${srcFilename}.`
  );
}

async function uploadToDatabase() {
  console.log("upload database");
  uploadFile().catch((err) => console.log(err));
}

async function downloadFromDatabase() {
  console.log("download database");
  downloadFile().catch((err) => console.log(err));
}

if(shouldUpload){
  //uploadToDatabase();
	console.log("-------------UPLOAD TO DATABASE-------------");
} else if (shouldDownload) {
  //downloadFromDatabase();
	console.log("-------------DOWNLOAD FROM DATABASE-------------");
}
