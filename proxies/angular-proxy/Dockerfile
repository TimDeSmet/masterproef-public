FROM node:10-slim

RUN apt-get update \
    && apt-get install -y wget gnupg \
    && wget -q -O - https://dl-ssl.google.com/linux/linux_signing_key.pub | apt-key add - \
    && sh -c 'echo "deb [arch=amd64] http://dl.google.com/linux/chrome/deb/ stable main" >> /etc/apt/sources.list.d/google.list' \
    && apt-get update \
    && apt-get install -y google-chrome-unstable fonts-ipafont-gothic fonts-wqy-zenhei fonts-thai-tlwg fonts-kacst fonts-freefont-ttf \
      --no-install-recommends \
    && rm -rf /var/lib/apt/lists/*

ENV NODE_ENV production
WORKDIR /usr/src/app
COPY ["package.json", "package-lock.json*", "npm-shrinkwrap.json*", "./"]
RUN npm install --production --silent \
    &&npm i puppeteer \
    &&groupadd -r pptruser && useradd -r -g pptruser -G audio,video pptruser \
    &&mkdir -p /home/pptruser/Downloads \
    &&chown -R pptruser:pptruser /home/pptruser \
    &&chown -R pptruser:pptruser node_modules 

USER pptruser
COPY . .
EXPOSE 3011
CMD node index.js