const webpack = require('webpack');

const developmentMode = process.env.DEVELOPMENT_MODE === 'true';

const devTool = developmentMode ? 'cheap-module-eval-source-map' : 'source-map';

const productionPlugins = [
    new webpack.optimize.UglifyJsPlugin({
        compress: { warnings: false }
    })
];

var plugins = [];

if (!developmentMode) {
    plugins = plugins.concat(productionPlugins);
}

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
    plugins: plugins,
    resolve: {
        extensions: ['.js', '.ts']
    },
    devtool: devTool
};