var webpack = require('webpack');

module.exports = {
    entry: {
        app: './src/management/index.ts'
    },
    output: {
        filename: './dist/management/js/[name].js'
    },
    module: {
        loaders: [
            {
                test: /\.ts$/,
                loaders: ['ts-loader', 'angular2-template-loader'],
                exclude: /node_modules/
            },
            {
                test: /\.(html|css)$/,
                loader: 'raw-loader'
            }
        ]
    },
    plugins: [
        new webpack.optimize.UglifyJsPlugin({
            compress: { warnings: false }
        })
    ],
    resolve: {
        extensions: ['.js', '.ts']
    },
    devtool: 'source-map'
};