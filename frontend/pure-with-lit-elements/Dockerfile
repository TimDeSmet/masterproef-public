FROM node:10.13-alpine
ENV NODE_ENV production
WORKDIR /usr/src/app
COPY ["package.json", "package-lock.json*", "npm-shrinkwrap.json*", "./"]
COPY . .
RUN npm install --production --silent && npm install -g bower && npm install -g polymer-cli --unsafe-perm
EXPOSE 8081
CMD polymer serve --hostname 0.0.0.0