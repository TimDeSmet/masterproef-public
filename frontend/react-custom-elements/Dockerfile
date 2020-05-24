FROM node:10.13-alpine
ENV NODE_ENV production
WORKDIR /usr/src/app
COPY ["package.json", "package-lock.json*", "npm-shrinkwrap.json*", "./"]
RUN npm install --production --silent && mv node_modules ./ 
COPY . .
RUN npm run build && npm install -g serve
EXPOSE 3001
CMD serve -l 3001 -s build  