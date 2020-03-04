FROM node:10-alpine
WORKDIR /usr/src/app
COPY package*.json ./
RUN npm install camunda-external-task-client-js
RUN npm install dotenv --save
RUN npm install nodemailer
RUN npm install --production
COPY . .
CMD [ "node", "external-worker.js" ]
