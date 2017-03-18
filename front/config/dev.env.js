var merge = require('webpack-merge')
var prodEnv = require('./prod.env')

var requestUrl = require('./request.url.dev.json')
module.exports = merge(prodEnv, {
  NODE_ENV: '"development"',
  REQUEST_URL: JSON.stringify(requestUrl)
})
