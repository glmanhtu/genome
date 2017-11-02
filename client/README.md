# exchange_expert_client
[![N|Solid](http://www.kclifesciences.org/wp-content/uploads/expert-exchange-large.gif)](https://github.com/yehnkay/exchange_expert_client)
The Expert Exchange is a support service for exchange goods. Our focus has been on delivering quality goods and services at competitively low prices with superior service.

----------
Documents
----------
### Tech

Project uses a number of open source projects to work properly:

* [AngularJS] - HTML enhanced for web apps!
* [SublimeText Editor] - is a sophisticated text editor for code, markup and prose.
* [node.js] - evented I/O for the backend
* [Gulp] - the streaming build system
* [Bower] - A package manager for the web

### Installation

Expert Exchange requires [Node.js](https://nodejs.org/) v4+ to run.

Download and extract the [latest pre-built release](https://github.com/yehnkay/exchange_expert_client).

Install the dependencies and devDependencies and start the server.

```sh
$ cd expert-exchange
$ npm install -d
```

For production environments...

```sh
$ npm install --production
$ npm run predeploy
$ NODE_ENV=production node app
```

### Plugins

Dillinger is currently extended with the following plugins
* Assana
* Slack
* Dropbox
* Github
* Google Drive
* OneDrive

Readmes, how to use them in your own application can be found here:

* [plugins/dropbox/README.md] [PlDb]
* [plugins/github/README.md] [PlGh]
* [plugins/googledrive/README.md] [PlGd]
* [plugins/onedrive/README.md] [PlOd]

### Development
Open your favorite Terminal and run these commands.

First Tab:
```sh
$ npm install
```

Second Tab:
```sh
$ gulp serve
```

**Free Software, Hell Yeah!**

[//]: # (These are reference links used in the body of this note and get stripped out when the markdown processor does its job. There is no need to format nicely because it shouldn't be seen. Thanks SO - http://stackoverflow.com/questions/4823468/store-comments-in-markdown-syntax)

   [SublimeText Editor]: <http://www.sublimetext.com/>
   [Bower]: <https://bower.io/>
   [node.js]: <http://nodejs.org>
   [AngularJS]: <http://angularjs.org>
   [Gulp]: <http://gulpjs.com>

   
