FROM node:10.13-alpine
ENV NODE_ENV production
WORKDIR /usr/src/app
COPY ["package.json", "package-lock.json*", "npm-shrinkwrap.json*", "./"]
RUN npm install --production --silent \
    && npm install -g @angular/cli \
    && npm install --only=dev \
    && mv node_modules ./  
COPY . .
EXPOSE 4200
CMD ["ng", "serve", "--host=0.0.0.0", "--disable-host-check"]