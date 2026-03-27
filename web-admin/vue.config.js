module.exports = {
  publicPath: './',
  devServer: {
    port: 8083,
    historyApiFallback: true,
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
