FROM node:10-alpine
WORKDIR /usr/src/app
COPY package*.json ./
COPY *.js ./
RUN npm install --production
CMD [ "node", "external-worker.js" ]
