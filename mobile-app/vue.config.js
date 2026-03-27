module.exports = {
  publicPath: './',
  devServer: {
    port: 8081,
    proxy: {
      '/api': {
        target: 'http://localhost:8082',
        changeOrigin: true
      }
    }
  },
  configureWebpack: {
    output: {
      filename: 'js/[name].js',
      chunkFilename: 'js/[name].js'
    }
  }
}
