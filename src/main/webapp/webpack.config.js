module.exports = {
    entry: {
        app: './src/management-v2/index.ts'
    },
    output: {
        filename: './dist/management-v2/js/[name].js'
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
    resolve: {
        extensions: ['.js', '.ts']
    },
    devtool: 'source-map'
};